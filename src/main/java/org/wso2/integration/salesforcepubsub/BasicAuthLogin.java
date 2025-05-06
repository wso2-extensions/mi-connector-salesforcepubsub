/*
 *  Copyright (c) 2025, WSO2 LLC. (https://www.wso2.com).
 *
 *  WSO2 LLC. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.wso2.integration.salesforcepubsub;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.stream.Collectors;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

/**
 * This class handles the login process to Salesforce using Basic Authentication.
 * It constructs a SOAP request to the Salesforce login endpoint, sends the request, and receives the response
 * include session.
 */
public class BasicAuthLogin {

    public static class LoginResponse {
        public final String sessionId;
        public final String instanceUrl;
        public final String tenantId;

        public LoginResponse(String sessionId, String instanceUrl, String tenantId) {
            this.sessionId = sessionId;
            this.instanceUrl = instanceUrl;
            this.tenantId = tenantId;
        }

        @Override
        public String toString() {
            return "Session ID: " + sessionId + "\n" +
                    "Server URL: " + instanceUrl + "\n" +
                    "Org ID: " + tenantId;
        }
    }

    public static class PartnerNamespaceContext implements NamespaceContext {
        @Override
        public String getNamespaceURI(String prefix) {
            if ("soapenv".equals(prefix)) {
                return "http://schemas.xmlsoap.org/soap/envelope/";
            } else if ("sf".equals(prefix)) {
                return "urn:partner.soap.sforce.com";
            } else {
                return null;
            }
        }

        @Override
        public String getPrefix(String uri) { return null; }
        @Override
        public Iterator<String> getPrefixes(String uri) { return null; }
    }

    public LoginResponse login(String username, String password, String token, String apiVersion) throws Exception {
        String fullPassword = password + (token != null ? token : "");
        String endpoint = "https://login.salesforce.com/services/Soap/u/" + apiVersion;

        String soapBody = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                + "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\""
                + " xmlns:sf=\"urn:partner.soap.sforce.com\">"
                + "<soapenv:Body>"
                + "<sf:login>"
                + "<sf:username>" + username + "</sf:username>"
                + "<sf:password>" + fullPassword + "</sf:password>"
                + "</sf:login>"
                + "</soapenv:Body>"
                + "</soapenv:Envelope>";

        HttpURLConnection conn = (HttpURLConnection) new URL(endpoint).openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        conn.setRequestProperty("SOAPAction", "login");

        try (OutputStream os = conn.getOutputStream()) {
            os.write(soapBody.getBytes());
        }

        int status = conn.getResponseCode();
        if (status == 200) {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(conn.getInputStream());

            XPath xpath = XPathFactory.newInstance().newXPath();
            xpath.setNamespaceContext(new PartnerNamespaceContext());

            String sessionId = xpath.evaluate("//sf:sessionId", doc);
            String serverUrl = xpath.evaluate("//sf:serverUrl", doc);
            String instanceUrl = serverUrl.substring(0, serverUrl.indexOf("/services"));
            String orgId     = xpath.evaluate("//sf:userInfo/sf:organizationId", doc);

            if (sessionId == null || sessionId.isEmpty()) {
                throw new IOException("sessionId is null");
            }

            return new LoginResponse(sessionId, instanceUrl, orgId);
        } else {
            String errorBody;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()))) {
                errorBody = reader.lines().collect(Collectors.joining("\n"));
            }
            throw new IOException("error response from Salesforce: " + errorBody);
        }
    }
}
