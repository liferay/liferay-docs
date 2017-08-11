# Using Service Builder in a Maven Project [](id=using-service-builder-in-a-maven-project)

Liferay's
[Service Builder](/develop/tutorials/-/knowledge_base/7-0/what-is-service-builder)
is a model-driven service generation tool that is frequently used by many Liferay
module projects. If you have a Liferay Maven project, you may be wondering if
Service Builder works with your Maven modules; the answer is a resounding yes!

The easiest way to add Service Builder to your Maven project is to create a
new Maven project using Liferay's provided Service Builder archetype. You can
learn how to generate a Service Builder Maven project by visiting the
[Service Builder Template](/develop/reference/-/knowledge_base/7-0/using-the-service-builder-template)
tutorial. In some cases, this may not be possible due to a number of reasons:

- You're updating a legacy Maven project to follow OSGi modular architecture.
- You have a pre-existing modular Maven project and don't want to start over.

Time to get started!

1.  Apply the Service Builder plugin in your Maven project's `pom.xml` file:

        <build>
            <plugins>
                <plugin>
                    <groupId>com.liferay</groupId>
                    <artifactId>com.liferay.portal.tools.service.builder</artifactId>
                    <version>1.0.160</version>
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
    configurations, etc. You can reference all the configurable Service Builder
    properties in the
    [Service Builder with Maven](/develop/reference/-/knowledge_base/7-0/service-builder-with-maven)
    reference article. Also, visit the
    [Defining an Object-Relational Map with Service Builder](/develop/tutorials/-/knowledge_base/7-0/defining-an-object-relational-map-with-service-builder)
    tutorial for more information on defining a `service.xml` file to configure
    Service Builder.

2.  Apply the WSDD Builder plugin declaration directly after the Service Builder
    plugin declaration:

        <plugin>
            <groupId>com.liferay</groupId>
            <artifactId>com.liferay.portal.tools.wsdd.builder</artifactId>
            <version>1.0.8</version>
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
`com.liferay.portal.tools.wsdd.builder` plugins in your project's POM. To run
Service Builder, see the
[Running Service Builder and Understanding the Generated Code](/develop/tutorials/-/knowledge_base/7-0/running-service-builder-and-understanding-the-generated-code)
tutorial for instructions.
