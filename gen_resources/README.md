### Connector Structure Details

Below is an introduction to the folder structure for the MI Connector. It outlines what each folder and subfolder typically contains and how they relate to the overall connector development process:
```
.
├── generated
│   ├── org.wso2.mi.connector.pubsub
│   │   ├── docs
│   │   ├── gen_resource
│   │   ├── src
│   │   │   ├── main
│   │   │   │   ├──assembly
│   │   │   │   ├── java
│   │   │   │   │    ├── org.wso2.carbon.pubsub
│   │   │   │   │    │                   ├── com.salesforce.eventbus.protobuf
│   │   │   │   │    │                         (java client)
│   │   │   │   │    ├── java files(class mediators)
│   │   ├── resources
│   │   │   ├── functions
│   │   │   ├── config
│   │   │   ├── outputschema
│   │   │   ├── uischema
│   │   │   ├── icon
│   │   │   └── connector.xml
│   │   ├── pom.xml
│   │   ├── README.md
│   └── ...

```
- **generated/**
    - Contains files and folders automatically generated during the connector creation/build process.

- **org.wso2.mi.connector.PubSub/**
    - **docs/**: Documentation specific to the connector.
    - **gen_resource/**: Any auto-generated or resource-based artifacts (e.g., stubs, configs) used by the connector build.
    - **src/main/**
        - **assembly/**: Contains the assembly descriptor files (e.g., how to package the connector).
        - **java/org.wso2.carbon.pubsub/com.salesforce.eventbus.protobuf**:
            - Auto-generated Java client files based on proto files.
        - **java/org.wso2.carbon.pubsub/**:
            - Java mediator classes and any other custom connector logic.
    - **resources/**
        - **functions/**: Scripts or function definitions that the connector may use.
        - **config/**: Configuration files (e.g., property files needed for runtime).
        - **outputschema/**: Defines the output schema artifacts for the connector.
        - **uischema/**: Files related to graphical UI representation of connector parameters.
        - **icon/**: Stores the connector icon or branding images.
        - **connector.xml**: Primary connector definition that WSO2 Micro Integrator uses to recognize and load the connector.
    - **pom.xml**: Maven project file controlling how the connector is built.
    - **README.md**: Instructions and general information about the connector.