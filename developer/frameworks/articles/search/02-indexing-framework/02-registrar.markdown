# Search Service Registration

The search framework must know about your entity and how to handle it during a
search request. To register model entities with Liferay's search framework,
`SearchRegistrar`s use the [search framework's
registry](https://github.com/liferay/liferay-portal/tree/7.1.1-ga2/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/registrar)
to define certain things about your model entity's
[`ModelSearchDefinition`](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/registrar/ModelSearchDefinition.java):
which fields are used by default to retrieve documents from the search engine,
and which optional search services are registered for your entity (for example,
the `ModelIndexWriterContributor` for you entity. Registration occurs as soon
as the Component is activated (during portal startup or deployment of the
bundle). Search and indexing code relies on Search APIs and SPIs:

```groovy
compileOnly group: "com.liferay", name: "com.liferay.portal.search.spi", version: "3.2.0"
compileOnly group: "com.liferay", name: "com.liferay.portal.search.api", version: "3.7.0"
```

| **APIs and SPIs:** SPIs are a special type of API. Generally, code inside a
| SPI module (e.g., `portal-search-spi`) is used to customize existing behavior,
| while API modules contain behavior you want to use. 
| 
| SPI example:
|  `ModelIndexerWriterContributor` lives in a SPI module because you're supposed
|  to implement it directly, defining your own indexing behavior.
| 
| API example: 
|  `SearchRequestBuilder` lives in a API module because its behavior is
| leveraged inside your code to build a search request.

A Registrar is required. Without it, all your indexing, searching, and summary
logic (covered in the other tutorials of this section) isn't worth the bytes
used to store it.

1.  First, declare the class a component and create the class declaration:

    ```java
    @Component(immediate = true, service = {})
    public class FooEntrySearchRegistrar {
    ```
2.  Next write the `activate` method, annotated with the OSGi annotation
    `@Activate`. On activation of this component, you want to call the
    `ModelSearchRegistrarHelper.register` method, and use the call to build out
    a `ModelSearchDefinition`:

    ```java
	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceRegistration = modelSearchRegistrarHelper.register(
			FooEntry.class, bundleContext,
			modelSearchDefinition -> {
				modelSearchDefinition.setDefaultSelectedFieldNames(
                    Field.ASSET_TAG_NAMES, Field.COMPANY_ID, 
                    Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK,
					Field.GROUP_ID, Field.MODIFIED_DATE, Field.SCOPE_GROUP_ID,
					Field.UID);
				modelSearchDefinition.setDefaultSelectedLocalizedFieldNames(
					Field.CONTENT, Field.TITLE);
				modelSearchDefinition.setModelIndexWriteContributor(
					modelIndexWriterContributor);
				modelSearchDefinition.setModelSummaryContributor(
					modelSummaryContributor);
				modelSearchDefinition.setModelVisibilityContributor(
					modelVisibilityContributor);
			});
	}
    ```

    On activation of the Component, a chain of search and indexing classes is
    registered for the Foo entity. In addition, set the default selected field names
    used to retrieve results documents from the search engine. Then set the
    contributors used to build a model search definition.

    In addition to the `ModelSearchDefinition` setter methods used in the above
    code, there are two more to be aware of:

    To select all locales all the time when searching for your model entity,
    pass `true` to `setSelectAllLocales`:

    ```java
    modelSearchDefinition.setSelectAllLocales(true);
    ```

3.  Write a corresponding `deactivate` method:

    ```java
	@Deactivate
	protected void deactivate() {
		_serviceRegistration.unregister();
	}
    ```

4.  Get references to the services needed in the class. For the search services
    you're providing, specify them by entering the FQCN ofyour model entity in
    the reference target's `indexer.class.name` property:

    ```java
	@Reference(
		target = "(indexer.class.name=com.liferay.foo.model.FooEntry)"
	)
	protected ModelIndexerWriterContributor<FooEntry>
		modelIndexWriterContributor;

	@Reference
	protected ModelSearchRegistrarHelper modelSearchRegistrarHelper;

	@Reference(
		target = "(indexer.class.name=com.liferay.foo.model.FooEntry)"
	)
	protected ModelSummaryContributor modelSummaryContributor;

	@Reference(
		target = "(indexer.class.name=com.liferay.foo.model.FooEntry)"
	)
	protected ModelVisibilityContributor modelVisibilityContributor;

	private ServiceRegistration<?> _serviceRegistration;
    ```

It's quite possible you'll want to write this class after first getting all the
search and indexing logic into place. How can you register a
`ModelIndexerWriterContributor` if you haven't written one yet?
