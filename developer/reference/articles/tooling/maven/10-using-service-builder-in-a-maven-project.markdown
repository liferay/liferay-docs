# Using Service Builder in a Maven Project

The easiest way to add
[Service Builder](/develop/frameworks/-/knowledge_base/7-2/what-is-service-builder)
to your Maven project is to create a new Maven project using Liferay's provided
Service Builder archetype; it's configured in the new project by default. You
can learn how to generate a Service Builder Maven project by visiting the
[Service Builder Template](/developer/reference/-/knowledge_base/7-2/service-builder-template)
article.

In some cases, you should not use this template due to a number of reasons:

- You're updating a legacy Maven project to follow OSGi modular architecture.
- You have a pre-existing modular Maven project and don't want to start over.

For these cases, you can configure Service Builder in your project manually.
Follow the instructions below to configure Service Builder in your Maven
project!

1.  Apply the Service Builder plugin in your Maven project's `pom.xml` file:

    ```xml
    <build>
        <plugins>
            <plugin>
                <groupId>com.liferay</groupId>
                <artifactId>com.liferay.portal.tools.service.builder</artifactId>
                <version>1.0.276</version>
                <configuration>
                    <apiDirName>../basic-api/src/main/java</apiDirName>
                    <autoImportDefaultReferences>true</autoImportDefaultReferences>
                    <autoNamespaceTables>true</autoNamespaceTables>
                    <buildNumberIncrement>true</buildNumberIncrement>
                    <hbmFileName>src/main/resources/META-INF/module-hbm.xml</hbmFileName>
                    <implDirName>src/main/java</implDirName>
                    <inputFileName>service.xml</inputFileName>
                    <modelHintsFileName>src/main/resources/META-INF/portlet-model-hints.xml</modelHintsFileName>
                    <mergeModelHintsConfigs>src/main/resources/META-INF/portlet-model-hints.xml</mergeModelHintsConfigs>
                    <osgiModule>true</osgiModule>
                    <propsUtil>com.liferay.blade.samples.servicebuilder.service.util.PropsUtil</propsUtil>
                    <resourcesDirName>src/main/resources</resourcesDirName>
                    <springFileName>src/main/resources/META-INF/spring/module-spring.xml</springFileName>
                    <springNamespaces>beans,osgi</springNamespaces>
                    <sqlDirName>src/main/resources/META-INF/sql</sqlDirName>
                    <sqlFileName>tables.sql</sqlFileName>
                    <testDirName>src/main/test</testDirName>
                </configuration>
            </plugin>
        </plugins>
    </build>
    ```

    The `configuration` tag used above is an example of what a Service Builder
    project's configuration could look like. All the configuration attributes
    above should be modified to fit your project.

    The above code configures Service Builder for a sample `basic-service`
    module. When running Service Builder with this configuration, the project's
    API classes are generated in the `basic-api` module's `src/main/java`
    folder. You can also specify your hibernate module mappings, implementation
    directory name, model hints file, Spring configurations, SQL configurations,
    etc. You can reference all the configurable Service Builder properties in
    the
    [Service Builder Plugin](/developer/reference/-/knowledge_base/7-2/service-builder-with-maven)
    reference article. Also, visit the
    [Defining an Object-Relational Map with Service Builder](/developer/frameworks/-/knowledge_base/7-2/defining-an-object-relational-map-with-service-builder)
    tutorial for more information on defining a `service.xml` file to configure
    Service Builder.

2.  Apply the WSDD Builder plugin declaration directly after the Service Builder
    plugin declaration:

    ```xml
    <plugin>
        <groupId>com.liferay</groupId>
        <artifactId>com.liferay.portal.tools.wsdd.builder</artifactId>
        <version>1.0.10</version>
        <configuration>
            <inputFileName>service.xml</inputFileName>
            <outputDirName>src/main/java</outputDirName>
            <serverConfigFileName>src/main/resources/server-config.wsdd</serverConfigFileName>
        </configuration>
    </plugin>
    ```

    The WSDD Builder is required to generate your project's remote services.
    Visit the
    [Creating Remote Services](/developer/frameworks/-/knowledge_base/7-2/creating-remote-services)
    tutorial for more information on WSDD (Web Service Deployment Descriptor).
    See the
    [WSDD Builder Plugin](/developer/reference/-/knowledge_base/7-2/wsdd-builder-plugin)
    article for more information on the configuration properties.

Terrific! You've successfully configured your Maven project to use Service
Builder by applying the Service Builder and WSDD Builder plugins in your
project's POM. To run Service Builder, see the
[Running Service Builder and Understanding the Generated Code](/developer/frameworks/-/knowledge_base/7-2/running-service-builder)
article for instructions.
