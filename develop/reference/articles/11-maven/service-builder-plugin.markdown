# Service Builder Plugin [](id=service-builder-with-maven)

The Service Builder Gradle lets you generate a service layer defined in a
[Service Builder](/develop/tutorials/-/knowledge_base/7-0/what-is-service-builder)
`service.xml` file. Visit the
[Using Service Builder in a Maven Project](/develop/tutorials/-/knowledge_base/7-0/using-service-builder-in-a-maven-project)
tutorial to learn more about applying Service Builder to your Maven project.

## Usage [](id=usage)

To use the plugin, include it in your `pom.xml` file:

    <build>
        <plugins>
        ...
            <plugin>
                <groupId>com.liferay</groupId>
                <artifactId>com.liferay.portal.tools.service.builder</artifactId>
                <version>1.0.160</version>
                <configuration>
                </configuration>
            </plugin>
        ...
        </plugins>
    </build>

## Goals [](id=goals)

The plugin adds one Maven goal to your project:

Name | Description
---- | -----------
`service-builder:build` |  Runs the Liferay Service Builder.

## Available Parameters [](id=available-parameters)

You can set the following parameters in the `<configuration>` section of the
POM:

Parameter Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`apiDirName` | `String` | `"../portal-kernel/src"` | A directory where the service API Java source files are generated.
`autoImportDefaultReferences` | `boolean` | `true` | Whether to automatically add default references, like `com.liferay.portal.ClassName`, `com.liferay.portal.Resource` and `com.liferay.portal.User`, to the services. 
`autoNamespaceTables` | `boolean` | `null` | Whether to prefix table names by the namespace specified in the `service.xml` file.
`beanLocatorUtil` | `String` | `"com.liferay.portal.kernel.bean.PortalBeanLocatorUtil"` | The fully qualified class name of a bean locator class to use in the generated service classes.
`buildNumber` | `long` | `1` | A specific value to assign the `build.number` property in the `service.properties` file.
`buildNumberIncrement` | `boolean` | `true` | Whether to automatically increment the `build.number` property in the `service.properties` file by one at every service generation.
`hbmFileName` | `String` | `"src/META-INF/portal-hbm.xml"` | A Hibernate Mapping file to generate.
`implDirName` | `String` | `"src"` | A directory where the service Java source files are generated.
`inputFileName` | `String` | `"service.xml"` | The project's `service.xml` file.
`modelHintsConfigs` | `String` | `"classpath*:META-INF/portal-model-hints.xml, META-INF/portal-model-hints.xml, classpath*:META-INF/ext-model-hints.xml, classpath*:META-INF/portlet-model-hints.xml"` | Paths to the [model hints](/develop/tutorials/-/knowledge_base/7-0/customizing-model-entities-with-model-hints) files for Liferay Service Builder to use in generating the service layer.
`modelHintsFileName` | `String` | `"src/META-INF/portal-model-hints.xml"` | A model hints file for the project.
`osgiModule` | `boolean` | `null` | Whether to generate the service layer for OSGi modules.
`pluginName` | `String` | `null` | If specified, a plugin can enable additional generation features, such as `Clp` class generation, for non-OSGi modules.
`propsUtil` | `String` | `"com.liferay.portal.util.PropsUtil"` | The fully qualified class name of the service properties util class to generate.
`readOnlyPrefixes` | `String` | `"fetch, get, has, is, load, reindex, search"` | Prefixes of methods to consider read-only.
`resourceActionsConfigs` | `String` | `"META-INF/resource-actions/default.xml, resource-actions/default.xml"` | Paths to the [resource actions](/develop/tutorials/-/knowledge_base/7-0/adding-permissions-to-resources) files for Liferay Service Builder to use in generating the service layer.
`resourcesDirName` | `String` | `"src"` | A directory where the service non-Java files are generated.
`springFileName` | `String` | `"src/META-INF/portal-spring.xml"` | A service Spring file to generate.
`springNamespaces` | `String` | `"beans"` | Namespaces of Spring XML Schemas to add to the service Spring file.
`sqlDirName` | `String` | `"../sql"` | A directory where the SQL files are generated.
`sqlFileName` | `String` | `"portal-tables.sql"` | A name (relative to `sqlDir`) for the file in which the SQL table creation instructions are generated.
`sqlIndexesFileName` | `String` | `"indexes.sql"` | A name (relative to `sqlDir`) for the file in which the SQL index creation instructions are generated.
`sqlSequencesFileName` | `String` | `"sequences.sql"` | A name (relative to `sqlDir`) for the file in which the SQL sequence creation instructions are generated.
`targetEntityName` | `String` | `null` | If specified, it's the name of the entity for which Liferay Service Builder should generate the service.
`testDirName` | `String` | `"test/integration"` | If specified, it's a directory where integration test Java source files are generated.
