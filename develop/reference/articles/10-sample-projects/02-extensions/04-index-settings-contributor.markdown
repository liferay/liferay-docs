# Index Settings Contributor

The Index Settings Contributor sample demonstrates how to add a custom type
mapping to @product@. You can demo this sample by completing the following
steps:

1.  Navigate to the *Control Panel* &rarr; *Configuration* &rarr; *Search* menu.

2.  Click *Execute* for the *Reindex all search indexes* action.

    All properties defined in your `.json` file are added to @product@'s search
    engine. This sample adds the following index properties:

    - `sampleDate`
    - `sampleDouble`
    - `sampleLong`
    - `sampleText`

    You'll verify this next.

3.  Find your @product@'s instance ID. This can be found in the *Control Panel*
    &rarr; *Configuration* &rarr; *Virtual Instances* menu.

4.  Navigate to the following URL:

        http://localhost:9200/liferay-[INSTANCE_ID]/_mapping/LiferayDocumentType?pretty

    Be sure to insert your instance ID into the URL.

5.  Verify the added properties are listed.

    ![Figure 1: This sample added four new index properties.](../../../images/index-settings-contributor.png)

## What API(s) and/or code components does this sample highlight?

This sample leverages the
[IndexSettingsContributor](@app-ref@/foundation/latest/javadocs/com/liferay/portal/search/elasticsearch/settings/IndexSettingsContributor.html)
API.

## How does this sample leverage the API(s) and/or code component?

Liferay's search engine provides an API to define custom mappings. To use it,
follow these fundamental steps:

1.  Define the new mapping. In this sample, the mapping is defined in the
    `META-INF/mappings/resources/index-type-mappings.json` file. Notice that the
    default document for @product@ is called `LiferayDocumentType`. The
    mapping's features can be found in
    [Elasticsearch's docs](https://www.elastic.co/guide/en/elasticsearch/reference/current/mapping.html).

2.  Inject the mapping into Elasticsearch. The `IndexSettingsContributor` class'
    components are invoked during the reindexing stage and receive a
    `TypeMappingsHelper` as a hook to add new mappings.

This sample has two classes:

- `ResourceUtil`: reads the `.json` file.

- `IndexSettingsContributor`: allows the addition of type mappings on
  @product@'s search engine.

The `IndexSettingsContributor`'s `contribute` method adds the type mappings:

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

For the `ResourceUtil.readResouceAsString` parameter, you should pass the path
for the `.json` file that contains the properties to be added.

Also, it is important to highlight the `IndexSettingsContributor`'s `@Component`
annotation that registers a new service to the OSGi container:

    @Component(
    	immediate = true,
    	service = com.liferay.portal.search.elasticsearch6.settings.IndexSettingsContributor.class
    )

This sample demonstrates the essentials needed to contribute your own index
settings.

## Where Is This Sample?

There are three different versions of this sample, each built with a different
build tool:

- [Gradle](https://github.com/liferay/liferay-blade-samples/blob/7.1/gradle/extensions/index-settings-contributor)
- [Liferay Workspace](https://github.com/liferay/liferay-blade-samples/blob/7.1/liferay-workspace/extensions/index-settings-contributor)
- [Maven](https://github.com/liferay/liferay-blade-samples/blob/7.1/maven/extensions/index-settings-contributor)
