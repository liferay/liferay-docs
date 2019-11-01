---
header-id: search-service-registration
---

# Search Service Registration

[TOC levels=1-4]

The search framework must know about your entity and how to handle it during a
search request. To register model entities with Liferay's search framework,
`SearchRegistrar`s use the 
[search framework's registry](https://github.com/liferay/liferay-portal/tree/7.2.0-ga1/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/registrar)
to define certain things about your model entity's
[`ModelSearchDefinition`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/registrar/ModelSearchDefinition.java):
the default fields used to retrieve documents from the search engine,
and the optional search services registered for your entity (for example,
the `ModelIndexWriterContributor` for you entity). Registration occurs as soon
as the Component is activated (during portal startup or deployment of the
bundle). 

A Registrar is required so the container knows about your implementation.

1.  First, declare the class a component and create the class declaration:

    ```java
    @Component(immediate = true, service = {})
    public class FooEntrySearchRegistrar {
    ```

2.  Next write the `activate` method, annotated with the OSGi annotation
    `@Activate`. On activation of this component, call the
    `ModelSearchRegistrarHelper.register` method and use the call to build out
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
    code, there's another to be aware of:

    To select all locales all the time when searching for your model entity,
    pass `true` to `setSelectAllLocales`:

    ```java
    modelSearchDefinition.setSelectAllLocales(true);
    ```

    Technically, there's another setter in `ModelSearchDefinition` that takes a
    boolean, `setSearchResultPermissionFilterSuppressed`. However, this is
    intended for internal consumption.

3.  Write a corresponding `deactivate` method:

    ```java
	@Deactivate
	protected void deactivate() {
		_serviceRegistration.unregister();
	}
    ```

4.  Get references to the services needed in the class. For the search services
    you're providing, specify them by entering the FQCN of your model entity in
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
