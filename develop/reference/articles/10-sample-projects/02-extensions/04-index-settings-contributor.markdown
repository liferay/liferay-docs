# Index Settings Contributor

This sample shows how to add a custom type mapping on Liferay Portal.

### What does this sample do when it's deployed? [](id=what-does-this-sample-do-when-its-deployed)

After you deploy the sample, open your Liferay Portal and go to "Control Panel -> Configuration -> Search -> Reindex all search indexes." click on the "Execute" button.
After that, the properties you defined on you `.json` file will be added to Liferay's search engine.

### What Api(S) and/or code components does this sample highlight? [](id=what-apis-and-or-code-components-does-this-sample-highlight)

This sample leverages the IndexSettingsContributor API.

### How does this sample leverage the api(s) and/or code component? [](id=how-does-this-sample-leverage-the-apis-and-or-code-component)

Liferay's search engine provides an API to define custom mappings. To use it:

1. Define the new mapping.
In this sample, the mapping is defined on `META-INF/mappings/resources/index-type-mappings.json` file.
Notice that the default document on Liferay is called `LiferayDocumentType`.
The mapping's features can be found at [elastic search's docs](https://www.elastic.co/guide/en/elasticsearch/reference/current/mapping.html).

2. Put the mapping into Elasticsearch.
The `IndexSettingsContributor` components are invoked during the reindexing and receive a `TypeMappingsHelper` as a hook to add new mappings.

The `ResourceUtil` is a utility class that read's the `.json` file.

The `IndexSettingsContributor` is a class that allows the addition of type mappings on Liferay's search engine.

    @Override
    public void contribute(
        String indexName, TypeMappingsHelper typeMappingsHelper) {
        try {
            String mappings = ResourceUtil.readResouceAsString(
                "META-INF/resources/mappings/index-type-mappings.json");

            typeMappingsHelper.addTypeMappings(indexName, mappings);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

On the `ResourceUtil.readResouceAsString` parameter you should pass the path for the `.json` that contains the properties to be added.

Also, it is important to highlight the `@Component` annotation that register a new service to the OSGI:

    @Component(
    	immediate = true,
    	service = com.liferay.portal.search.elasticsearch6.settings.IndexSettingsContributor.class
    )

## Where Is This Sample? [](id=where-is-this-sample)

There are three different versions of this sample, each built with a different build tool:

- [Gradle](https://github.com/luanmaoski/liferay-blade-samples/tree/blade-sample-index-settings-contributor/gradle/extensions/index-settings-contributor)
- [Liferay Workspace](https://github.com/luanmaoski/liferay-blade-samples/tree/blade-sample-index-settings-contributor/liferay-workspace/extensions/index-settings-contributor)
- [Maven](https://github.com/luanmaoski/liferay-blade-samples/tree/blade-sample-index-settings-contributor/maven/extensions/index-settings-contributor)