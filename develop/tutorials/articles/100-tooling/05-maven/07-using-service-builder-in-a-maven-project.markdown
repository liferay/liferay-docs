# Using Service Builder in a Maven Project

Liferay's
[Service Builder](/develop/tutorials/-/knowledge_base/7-0/what-is-service-builder)
is a model-driven code generation tool that is frequently used by many Liferay
module projects. If you have a Liferay Maven project, you may be wondering if
Service Builder works with your Maven modules; the answer is a resounding yes!

The easiest way to configure a Maven project with Service Builder is to create a
new Maven project using Liferay's provided Service Builder archetype. You can
learn how to generate a Service Builder Maven project by visiting the
[Generating New Projects Using Archetypes](/develop/tutorials/-/knowledge_base/7-0/generating-new-projects-using-archetypes)
tutorial. In some cases, this may not be possible due to a number of reasons:

- Updating legacy Maven project to follow OSGi modular architecture.
- Have pre-existing modular Maven project and don't want to start over.

In this tutorial, you'll learn how to configure Service Builder in a Liferay
Maven modular project. Time to get started!

1.  Apply the Service Builder plugin in your Maven project's `pom.xml` file:

        <build>
            <plugins>
                <plugin>
                    <groupId>com.liferay</groupId>
                    <artifactId>com.liferay.portal.tools.service.builder</artifactId>
                    <version>1.0.105</version>
                    <configuration>
                        <apiDirName>../blade.servicebuilder.api/src/main/java</apiDirName>
                        <autoImportDefaultReferences>true</autoImportDefaultReferences>
                        <autoNamespaceTables>true</autoNamespaceTables>
                        <buildNumberIncrement>true</buildNumberIncrement>
                        <hbmFileName>src/main/resources/META-INF/module-hbm.xml</hbmFileName>
                        <implDirName>src/main/java</implDirName>
                        <inputFileName>service.xml</inputFileName>
                        <modelHintsFileName>src/main/resources/META-INF/portlet-model-hints.xml</modelHintsFileName>
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

    Service Builder is applied by specifying its `groupId`, `artifactId`, and
    `version`. The `configuration` tag used above is an example of what a
    Service Builder project's configuration could look like. All the
    configuration attributes above should be modified to fit your project.

    The above code configures Service Builder for a `blade.servicebuilder.svc`
    module. When running Service Builder with this configuration, the project's
    API classes are generated in the `blade.servicebuilder.api` module's
    `src/main/java` folder. You can also specify your hibernate module mappings,
    implementation directory name, model hints file, Spring configurations, SQL
    configurations, etc. You can reference the configuration properties used in
    the POM example below.

    - `apiDirName`: The folder to generate the service API Java source files.
    - `autoImportDefaultReferences`: Whether to automatically add default
      references like `com.liferay.portal.ClassName`,
      `com.liferay.portal.Resource`, and `com.liferay.portal.User`, to the
      services.
    - `autoNamespaceTables`: Whether to prefix table names by the namespace
      specified in the `service.xml` file.
    - `buildNumberIncrement`: Whether to automatically increment the
      `build.number` property in the `service.properties` file by one at every
      service generation.
    - `hbmFileName`: The Hibernate Mapping file to generate.
    - `implDirName`: The directory where the service Java source files are
      generated.
    - `inputFileName`: The project's `service.xml` file.
    - `modelHintsFileName`: The model hints file for the project.
    - `osgiModule`: Whether to generate the service layer for OSGi modules.
    - `propsUtil`: The fully qualified class name of the service properties util
      class to generate.
    - `resourcesDirName`: The directory where the service non-Java files are
      generated.
    - `springFileName`: The service Spring file to generate.
    - `springNamespaces`: Namespaces of Spring XML Schemas to add to the service
      Spring file.
    - `sqlDirName`: The directory where the SQL files are generated.
    - `sqlFileName`: The name (relative to `sqlDir`) for the file in which the
      SQL table creation instructions are generated.
    - `testDirName`: The directory where integration test Java source files are
      generated.

    <!-- The above listing may be better for a Developer Reference section of
    configuring the Service Builder plugin.

    Visit the
    [Defining an Object-Relational Map with Service Builder](/develop/tutorials/-/knowledge_base/7-0/defining-an-object-relational-map-with-service-builder)
    tutorial for more information on defining a `service.xml` file to configure
    Service Builder.

2.  Apply the WSDD Builder plugin declaration directly after the Service Builder
    plugin declaration:

        <plugin>
            <groupId>com.liferay</groupId>
            <artifactId>com.liferay.portal.tools.wsdd.builder</artifactId>
            <version>1.0.3</version>
            <configuration>
                <inputFileName>service.xml</inputFileName>
                <outputDirName>src/main/java</outputDirName>
                <serverConfigFileName>src/main/resources/server-config.wsdd</serverConfigFileName>
            </configuration>
        </plugin>

    The WSDD Builder is necessary to generate your project's remote services.
    Visit the
    [Creating Remote Services](/develop/tutorials/-/knowledge_base/7-0/creating-remote-services)
    tutorial for more information on WSDD (Web Service Deployment Descriptor).
    Similar to the Service Builder configuration, the `service.xml` file is set
    to define your project's remote services. Also, the `outputDirName` defines
    where the `*_deploy.wsdd` and `*_undeploy.wsdd` files are generated. Make
    sure to define your
    [`server-config.wsdd` file](http://axis.apache.org/axis/java/reference.html#Global_Axis_Configuration),
    as well.

Terrific! You've successfully configured your Maven project to use Service
Builder by applying the `com.liferay.portal.tools.service.builder` and
`com.liferay.portal.tools.wsdd.builder` modules in your project's POM. To run
Service Builder, see the
[Running Service Builder and Understanding the Generated Code](/develop/tutorials/-/knowledge_base/7-0/running-service-builder-and-understanding-the-generated-code)
tutorial for instructions.
