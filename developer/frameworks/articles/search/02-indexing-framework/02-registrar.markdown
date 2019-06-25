# Search Service Registration

To register model entities with Liferay's search framework, `SearchRegistrar`s
use the [search framework's
registry](https://github.com/liferay/liferay-portal/tree/7.1.1-ga2/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/registrar)
to define certain things about your model entity's
[`ModelSearchDefinition`](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/registrar/ModelSearchDefinition.java):
which fields are used by default to retrieve documents from the search engine,
and which optional search services are registered for your entity (for example,
the `ModelIndexWriterContributor` for you entity. Registration occurs as soon as
the Component is activated (during portal startup or deployment of the bundle).
Search and indexing code relies on Search APIs and SPIs:

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

```java
@Component(immediate = true, service = {})
public class FooEntrySearchRegistrar {

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceRegistration = modelSearchRegistrarHelper.register(
			FooEntry.class, bundleContext,
			modelSearchDefinition -> {
				modelSearchDefinition.setDefaultSelectedFieldNames(
					Field.ASSET_TAG_NAMES, Field.COMPANY_ID, Field.CONTENT,
					Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK,
					Field.GROUP_ID, Field.MODIFIED_DATE, Field.SCOPE_GROUP_ID,
					Field.TITLE, Field.UID);
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

	@Deactivate
	protected void deactivate() {
		_serviceRegistration.unregister();
	}

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

}
```
