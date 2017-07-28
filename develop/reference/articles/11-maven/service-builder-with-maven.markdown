# Service Builder with Maven [](id=service-builder-with-maven)

You can add the Liferay
[Service Builder](/develop/tutorials/-/knowledge_base/7-0/what-is-service-builder)
plugin to your Maven project by applying it to the project's `pom.xml`. Visit
the [Using Service Builder in a Maven Project](/develop/tutorials/-/knowledge_base/7-0/using-service-builder-in-a-maven-project)
tutorial to apply Service Builder to your Maven project. The following
properties can be used with Service Builder: 

- `apiDirName`: Where to generate the service API Java source files.
- `autoImportDefaultReferences`: Set `true` to automatically add default
   references like `com.liferay.portal.ClassName`,
   `com.liferay.portal.Resource`, and `com.liferay.portal.User` to the
   services.
- `autoNamespaceTables`: Set `true` to prefix table names by the namespace
   specified in the `service.xml` file.
- `beanLocatorUtil`: The fully qualified class name of a bean locator class to
   use in the generated service classes.
- `buildNumber`: The specific value to assign the `build.number` property in the
   `service.properties` file.
- `buildNumberIncrement`: Set `true` to automatically increment the
   `build.number` property in the `service.properties` file by one at every
   service generation.
- `hbmFileName`: The Hibernate Mapping file to generate.
- `implDirName`: The directory where the service Java source files are
   generated.
- `inputFileName`: The project's `service.xml` file.
- `modelHintsConfigs`: Paths to the
   [model hints](/develop/tutorials/-/knowledge_base/7-0/customizing-model-entities-with-model-hints)
   files for Liferay Service Builder to use in generating the service layer.
- `modelHintsFileName`: The model hints file for the project.
- `osgiModule`: Set `true` to generate the service layer for OSGi modules.
- `pluginName`: If specified, a plugin can enable additional generation
   features, such as `Clp` class generation, for non-OSGi modules.
- `propsUtil`: The fully qualified class name of the service properties util
   class to generate.
- `readOnlyPrefixes`: Prefixes of methods to consider read-only.
- `resourceActionsConfigs`: Paths to the resource actions files for Liferay
   Service Builder to use in generating the service layer.
- `resourcesDirName`: The directory where the service non-Java files are
   generated.
- `springFileName`: A service Spring file to generate.
- `springNamespaces`: Namespaces of Spring XML Schemas to add to the service
   Spring file.
- `sqlDirName`: The directory where the SQL files are generated.
- `sqlFileName`: The name (relative to `sqlDir`) for the file in which the SQL
   table creation instructions are generated.
- `sqlIndexesFileName`: The name (relative to `sqlDir`) for the file in which
   the SQL index creation instructions are generated.
- `sqlSequencesFileName`: The name (relative to `sqlDir`) for the file in which
   the SQL sequence creation instructions are generated.
- `targetEntityName`: If specified, it's the name of the entity for which
   Liferay Service Builder should generate the service.
- `testDirName`: The directory where integration test Java source files are
   generated.

<!--
- `mergeModelHintsConfigs`: 
- `mergeReadOnlyPrefixes`: 
- `mergeResourceActionsConfigs`: 
-->

## Related Topics  [](id=related-topics)

[Running Service Builder and Understanding the Generated Code](/develop/tutorials/-/knowledge_base/7-0/running-service-builder-and-understanding-the-generated-code)

[Using Service Builder in a Maven Project](/develop/tutorials/-/knowledge_base/7-0/using-service-builder-in-a-maven-project)

[Service Builder Template](/develop/reference/-/knowledge_base/7-0/using-the-service-builder-template)
