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

package org.wso2.carbon.sfpubsubconnector;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.ByteString;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for converting String values to various Java types, including arrays and objects.
 */
public class TypeConverter {

    /**
     * Converts a String value to the specified target type.
     *
     * @param value The String value to convert
     * @param targetType The Class representing the target type
     * @param <T> The generic type parameter
     * @return The converted value, or null if conversion is not supported or fails
     */
    @SuppressWarnings("unchecked")
    public static <T> T convert(String value, Class<T> targetType) {
        if (value == null) {
            return null;
        }

        try {
            // Handle basic types
            if (String.class.equals(targetType)) {
                return (T) value;
            } else if (Long.class.equals(targetType) || long.class.equals(targetType)) {
                return (T) Long.valueOf(value);
            } else if (Integer.class.equals(targetType) || int.class.equals(targetType)) {
                return (T) Integer.valueOf(value);
            } else if (Double.class.equals(targetType) || double.class.equals(targetType)) {
                return (T) Double.valueOf(value);
            } else if (Float.class.equals(targetType) || float.class.equals(targetType)) {
                return (T) Float.valueOf(value);
            } else if (Boolean.class.equals(targetType) || boolean.class.equals(targetType)) {
                return (T) Boolean.valueOf(value);
            } else if (Short.class.equals(targetType) || short.class.equals(targetType)) {
                return (T) Short.valueOf(value);
            } else if (Byte.class.equals(targetType) || byte.class.equals(targetType)) {
                return (T) Byte.valueOf(value);
            } else if (Character.class.equals(targetType) || char.class.equals(targetType)) {
                return value.isEmpty() ? null : (T) Character.valueOf(value.charAt(0));
            } else if (BigDecimal.class.equals(targetType)) {
                return (T) new BigDecimal(value);
            } else if (BigInteger.class.equals(targetType)) {
                return (T) new BigInteger(value);
            } else if (LocalDate.class.equals(targetType)) {
                return (T) LocalDate.parse(value);
            } else if (LocalDateTime.class.equals(targetType)) {
                return (T) LocalDateTime.parse(value);
            } else if (byte[].class.equals(targetType)) {
                return (T) Base64.getDecoder().decode(value);
            }

            // Handle Arrays
            if (targetType.isArray()) {
                return (T) convertToArray(value, targetType.getComponentType());
            }

            // Handle Object (User-Defined Class)
            if (Object.class.equals(targetType) || !targetType.isPrimitive()) {
                return (T) convertToObject(value, targetType);
            }
        } catch (Exception e) {
            System.err.println("Error converting '" + value + "' to " + targetType.getName() + ": " + e.getMessage());
        }

        return null;
    }

    /**
     * Converts a comma-separated String value to an array of the specified component type.
     *
     * @param value The String value to convert (comma-separated)
     * @param componentType The Class representing the component type of the array
     * @return The converted array, or null if conversion fails
     */
    @SuppressWarnings("unchecked")
    private static Object convertToArray(String value, Class<?> componentType) {
        String[] parts = value.split("\\s*,\\s*"); // Split by commas, trim spaces

        if (componentType.equals(int.class)) {
            return Arrays.stream(parts).mapToInt(Integer::parseInt).toArray();
        } else if (componentType.equals(long.class)) {
            return Arrays.stream(parts).mapToLong(Long::parseLong).toArray();
        } else if (componentType.equals(double.class)) {
            return Arrays.stream(parts).mapToDouble(Double::parseDouble).toArray();
        } else if (componentType.equals(float.class)) {
            return Arrays.stream(parts).map(Float::parseFloat).toArray();
        } else if (componentType.equals(boolean.class)) {
            return Arrays.stream(parts).map(Boolean::parseBoolean).toArray();
        } else if (componentType.equals(short.class)) {
            return Arrays.stream(parts).map(Short::parseShort).toArray();
        } else if (componentType.equals(byte.class)) {
            return Arrays.stream(parts).map(Byte::parseByte).toArray();
        } else if (componentType.equals(String.class)) {
            return parts;
        } else if (componentType.equals(BigDecimal.class)) {
            return Arrays.stream(parts).map(BigDecimal::new).toArray(BigDecimal[]::new);
        } else if (componentType.equals(BigInteger.class)) {
            return Arrays.stream(parts).map(BigInteger::new).toArray(BigInteger[]::new);
        } else if (componentType.equals(LocalDate.class)) {
            return Arrays.stream(parts).map(LocalDate::parse).toArray(LocalDate[]::new);
        } else if (componentType.equals(LocalDateTime.class)) {
            return Arrays.stream(parts).map(LocalDateTime::parse).toArray(LocalDateTime[]::new);
        }

        // Handle Object[] for user-defined classes
        return convertToObjects(value, componentType).toArray((Object[]) Array.newInstance(componentType, 0));
    }

    private static List<Object> convertToObjects(String value, Class<?> targetType) {
        List<Object> convertedObjects = new ArrayList<>();
        List<Map<String, String>> objects = parseJsonLikeString(value);

        try {
            // Look for the Builder nested class
            Class<?> builderClass = null;
            for (Class<?> nestedClass : targetType.getDeclaredClasses()) {
                if (nestedClass.getSimpleName().equals("Builder")) {
                    builderClass = nestedClass;
                    break;
                }
            }

            if (builderClass != null) {
                Method builderCreationMethod = getBuilderCreationMethod(targetType, builderClass);
                if (builderCreationMethod != null) {
                    for (Map<String, String> object : objects) {
                        ObjectMapper mapper = new ObjectMapper();
                        String json = mapper.writeValueAsString(object);
                        Object builtObject = convertToObject(json, targetType);
                        convertedObjects.add(builtObject);
                    }
                }
            }

            return convertedObjects;

        } catch (Exception e) {
            System.err.println("Error converting events to " + targetType.getName() + ": " + e.getMessage());
            e.printStackTrace();
            return convertedObjects;
        }
    }

    private static Object getValueObject(String value, Object kValue, Class<?> expectedType) {
        if (value != null && !expectedType.isAssignableFrom(kValue.getClass())) {
            if (expectedType == Integer.class || expectedType == int.class) {
                kValue = Integer.parseInt(kValue.toString());
            } else if (expectedType == Long.class || expectedType == long.class) {
                kValue = Long.parseLong(kValue.toString());
            } else if (expectedType == Double.class || expectedType == double.class) {
                kValue = Double.parseDouble(kValue.toString());
            } else if (expectedType == Float.class || expectedType == float.class) {
                kValue = Float.parseFloat(kValue.toString());
            } else if (expectedType == Boolean.class || expectedType == boolean.class) {
                kValue = Boolean.parseBoolean(kValue.toString());
            } else if (expectedType == String.class) {
                kValue = kValue.toString();
            }else if (expectedType == ByteString.class) {
                kValue = ByteString.copyFromUtf8((String) kValue);
            } else if (expectedType == Arrays.class) {
                kValue = convertToArray((String) kValue, expectedType.getComponentType());
            } else if (expectedType == Object.class || !expectedType.isPrimitive()) {
                kValue = convertToObject((String) kValue, expectedType);
            } else {
                throw new IllegalArgumentException("Unsupported type conversion for: " + expectedType);
            }
            //todo: add support for other types nested message types, arrays, etc.
        }
        return kValue;
    }

    private static Method findSetterMethod(Class<?> builderClass, String fieldName) {
        List<Method> methods = new ArrayList<>();
        for (Method method : builderClass.getMethods()) {
            if (method.getName().equalsIgnoreCase("set" + normalize(fieldName)) ||
                    method.getName().equalsIgnoreCase("with" + normalize(fieldName)) ||
                    method.getName().equalsIgnoreCase("addAll" + normalize(fieldName))) {
                if (method.getReturnType() == builderClass &&
                        method.getParameterTypes().length == 1) {
                    methods.add(method);
                    if (methods.size() == 2) {
                        break;
                    }
                }
            }
        }
        if (methods.size() == 2) {
            for (Method method: methods) {
                if (!method.getParameterTypes()[0].getName().endsWith("$Builder")) {
                    return method;
                }
            }
        } else if (methods.size() == 1) {
            return methods.get(0);
        }
        return null;
    }

    private static String normalize(String input) {
        return input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    }

    private static Object convertToObject(String value, Class<?> targetType) {
        try {
            Class<?> builderClass = null;
            Map<String, String> fieldMaps = parseJsonObject(value);
            for (Class<?> nestedClass : targetType.getDeclaredClasses()) {
                if (nestedClass.getSimpleName().equals("Builder")) {
                    builderClass = nestedClass;
                    break;
                }
            }

            if (builderClass != null) {
                Method builderCreationMethod = getBuilderCreationMethod(targetType, builderClass);

                if (builderCreationMethod != null) {
                    Object builder = builderCreationMethod.invoke(null);
                    for (String key : fieldMaps.keySet()) {
                        Method setterMethod = findSetterMethod(builderClass, key);
                        if (setterMethod != null) {
                            Object kValue = fieldMaps.get(key);
                            Class<?> expectedType = setterMethod.getParameterTypes()[0];
                            if (expectedType.getName().endsWith("Iterable")) {
                                String arrayString = kValue.toString();
                                if (arrayString.startsWith("[")) {
                                    arrayString = arrayString.substring(1);
                                }
                                if (arrayString.endsWith("]")) {
                                    arrayString = arrayString.substring(0, arrayString.length() - 1);
                                }
                                String[] parts = arrayString.split("\\s*,\\s*");
                                List<String> valueList = Arrays.asList(parts);
                                builder = setterMethod.invoke(builder, valueList);
                            } else  {
                                kValue = getValueObject(value, kValue, expectedType);
                                builder = setterMethod.invoke(builder, kValue);
                            }
                        }
                    }

                    Method buildMethod = builderClass.getMethod("build");
                    return buildMethod.invoke(builder);
                    }
            }

            Constructor<?> constructor = targetType.getDeclaredConstructor(String.class);
            constructor.setAccessible(true);
            return constructor.newInstance(value);

        } catch (Exception e) {
            System.err.println("Error converting '" + value + "' to " + targetType.getName() + ": " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private static Method getBuilderCreationMethod(Class<?> targetType, Class<?> builderClass) {
        try {
            return targetType.getMethod("newBuilder");
        } catch (NoSuchMethodException e) {
            for (Method method : targetType.getMethods()) {
                if ((method.getName().equals("builder") ||
                        method.getName().startsWith("newBuilder")) &&
                        Modifier.isStatic(method.getModifiers()) &&
                        method.getReturnType() == builderClass) {
                    return method;
                }
            }
            return null;
        }
    }

    private static List<Map<String, String>> parseJsonLikeString(String jsonLikeString) {
        List<Map<String, String>> resultList = new ArrayList<>();

        try {
            // Remove leading and trailing whitespace and brackets
            jsonLikeString = jsonLikeString.trim();
            if (jsonLikeString.startsWith("[")) {
                jsonLikeString = jsonLikeString.substring(1);
            }
            if (jsonLikeString.endsWith("]")) {
                jsonLikeString = jsonLikeString.substring(0, jsonLikeString.length() - 1);
            }

            // Split the string into individual object strings
            List<String> objectStrings = splitJsonObjects(jsonLikeString);

            // Parse each object string
            for (String objectString : objectStrings) {
                Map<String, String> objectMap = parseJsonObject(objectString);
                if (!objectMap.isEmpty()) {
                    resultList.add(objectMap);
                }
            }

        } catch (Exception e) {
            System.err.println("Error parsing JSON-like string: " + e.getMessage());
            e.printStackTrace();
        }

        return resultList;
    }

    private static List<String> splitJsonObjects(String input) {
        List<String> objects = new ArrayList<>();
        int bracketCount = 0;
        StringBuilder currentObject = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (c == '{') {
                bracketCount++;
                currentObject.append(c);
            } else if (c == '}') {
                bracketCount--;
                currentObject.append(c);

                if (bracketCount == 0) {
                    String objectStr = currentObject.toString().trim();
                    if (!objectStr.isEmpty()) {
                        objects.add(objectStr);
                    }
                    currentObject = new StringBuilder();
                }
            } else if (bracketCount > 0 || (c != ',' && !Character.isWhitespace(c))) {
                currentObject.append(c);
            }
        }

        if (currentObject.length() > 0) {
            String lastObjectStr = currentObject.toString().trim();
            if (!lastObjectStr.isEmpty()) {
                objects.add(lastObjectStr);
            }
        }

        return objects;
    }

    private static Map<String, String> parseJsonObject(String objectString) {
        Map<String, String> objectMap = new HashMap<>();

        try {
            // Remove leading and trailing whitespace and brackets
            if (objectString.startsWith("'{") && objectString.endsWith("}'")) {
                objectString = objectString.trim().replaceFirst("'\\{", "").replaceFirst("\\}'", "");
            } else {
                objectString = objectString.trim().replaceFirst("^\\{", "").replaceFirst("\\}$", "");
            }

            // Split by comma, handling nested structures
            List<String> keyValuePairs = splitKeyValuePairs(objectString);

            for (String pair : keyValuePairs) {
                // Split each pair into key and value
                int colonIndex = pair.indexOf(':');
                if (colonIndex != -1) {
                    String key = pair.substring(0, colonIndex)
                            .trim()
                            .replaceAll("^\"|\"$", "")  // Remove surrounding quotes
                            .replaceAll("^'|'$", "");   // Remove surrounding single quotes

                    String value = pair.substring(colonIndex + 1)
                            .trim()
                            .replaceAll("^\"|\"$", "")  // Remove surrounding quotes
                            .replaceAll("^'|'$", "");   // Remove surrounding single quotes

                    objectMap.put(key, value);
                }
            }
        } catch (Exception e) {
            System.err.println("Error parsing individual object: " + e.getMessage());
            e.printStackTrace();
        }

        return objectMap;
    }

    private static List<String> splitKeyValuePairs(String input) {
        List<String> pairs = new ArrayList<>();
        int bracketCount = 0;
        int squareBracketCount = 0;
        StringBuilder currentPair = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (c == '{') {
                bracketCount++;
                currentPair.append(c);
            } else if (c == '}') {
                bracketCount--;
                currentPair.append(c);
            } else if (c == '[') {
                squareBracketCount++;
                currentPair.append(c);
            } else if (c == ']') {
                squareBracketCount--;
                currentPair.append(c);
            } else if (c == ',' && bracketCount == 0 && squareBracketCount == 0) {
                pairs.add(currentPair.toString().trim());
                currentPair = new StringBuilder();
            } else {
                currentPair.append(c);
            }
        }

        if (currentPair.length() > 0) {
            pairs.add(currentPair.toString().trim());
        }

        return pairs;
    }
}
