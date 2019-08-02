# Indexing Model Entities

Model entities are searchable when their data fields are indexed by the search
engine. 

## Contributing Model Entity Fields to the Index

Write a `ModelDocumentContributor` class to control how model entity fields are
indexed in search engine documents.

**Extension Point (SPI):** [`com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor<T>`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/index/contributor/ModelDocumentContributor.java)

Declare the Component's `indexer.class.name` and its service type as a
`ModelDocumentContributor` class:

```java
@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.foo.model.FooEntry",
	service = ModelDocumentContributor.class
)
public class FooEntryModelDocumentContributor
	implements ModelDocumentContributor<FooEntry> {
```

Implement the `contribute` method, calling the
`com.liferay.portal.kernel.Document.add()` method appropriate for the data type
the index should use (e.g., `addText` for fields that should be searched using a
[full text search strategy](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/text.html)).

```java
@Override
public void contribute(Document document, FooEntry fooEntry) {

    document.addDate(Field.DISPLAY_DATE, fooEntry.getDisplayDate());
    document.addDate(Field.MODIFIED_DATE, fooEntry.getModifiedDate());
    document.addText(Field.SUBTITLE, fooEntry.getSubtitle());
```

For fields that should be
[localized](/docs/7-2/deploy/-/knowledge_base/d/localization), index a field for
each locale in the Site. Many times you'll want to localize the title and
content fields, for example:

```java
for (Locale locale :
        LanguageUtil.getAvailableLocales(fooEntry.getGroupId())) {

    String languageId = LocaleUtil.toLanguageId(locale);

    document.addText(
        LocalizationUtil.getLocalizedName(Field.CONTENT, languageId),
        content);
    document.addText(
        LocalizationUtil.getLocalizedName(Field.TITLE, languageId),
        fooEntry.getTitle());
}
```

The above strategy is a good one: loop through the available locales in the
site, then use `com.liferay.portal.kernel.util.LocalizationUtil` to add the
localized field name to the document.

The `contribute` method is called each time the `add` and `update` methods in
the entity's service layer are called.

## Configure Re-Indexing and Batch Indexing Behavior

`ModelIndexerWriterContributor` classes configure the re-indexing and batch
re-indexing behavior for the model entity. This class's `customize` method is
called when a re-index is triggered from the Search administrative application
found in Control Panel &rarr; Configuration &rarr; Search, or when a CRUD
operation is made on the entity, _if_ the `modelIndexed` method is implemented
in the contributor.

**Extension Point (SPI):** [`com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/index/contributor/ModelIndexerWriterContributor.java)

The bulk of the work is in the `customize` method. This code uses the
actionable dynamic query helper method to retrieve all existing Foo entities in
the virtual instance (identified by the Company ID). If you're using Service
Builder, this query method was generated for you when you built the services.
Each Foo Entry document is then retrieved and added to a collection.

1.  First set up the component and class declarations:

    ```java
    @Component(
        immediate = true,
        property = "indexer.class.name=com.liferay.foo.model.FooEntry",
        service = ModelIndexerWriterContributor.class
    )
    public class FooEntryModelIndexerWriterContributor
        implements ModelIndexerWriterContributor<FooEntry> {
    ```

2.  Write the `customize` method:

    ```java
    @Override
    public void customize(
        BatchIndexingActionable batchIndexingActionable,
        ModelIndexerWriterDocumentHelper modelIndexerWriterDocumentHelper) {

        batchIndexingActionable.setAddCriteriaMethod(
            dynamicQuery -> {
                Property displayDateProperty = PropertyFactoryUtil.forName(
                    "displayDate");

                dynamicQuery.add(displayDateProperty.lt(new Date()));

                Property statusProperty = PropertyFactoryUtil.forName("status");

                Integer[] statuses = {
                    WorkflowConstants.STATUS_APPROVED,
                    WorkflowConstants.STATUS_IN_TRASH
                };

                dynamicQuery.add(statusProperty.in(statuses));
            });
        batchIndexingActionable.setPerformActionMethod(
            (FooEntry fooEntry) -> {
                Document document =
                    modelIndexerWriterDocumentHelper.getDocument(fooEntry);

                batchIndexingActionable.addDocuments(document);
            });
    }
    ```

3.  Override `getBatchIndexingActionable`:

    ```java
    @Override
    public BatchIndexingActionable getBatchIndexingActionable() {
        return _dynamicQueryBatchIndexingActionableFactory.
            getBatchIndexingActionable(
                _fooEntryLocalService.getIndexableActionableDynamicQuery());
    }
    ```

4.  Override `getcompanyId`, getting the ID from your entity:

    ```java
	@Override
	public long getCompanyId(FooEntry fooEntry) {
		return fooEntry.getCompanyId();
	}
    ```

5.  Override `getIndexerWriterMode`:

    ```java
	@Override
	public IndexerWriterMode getIndexerWriterMode(FooEntry fooEntry) {
		int status = fooEntry.getStatus();

		if ((status == WorkflowConstants.STATUS_APPROVED) ||
			(status == WorkflowConstants.STATUS_IN_TRASH) ||
			(status == WorkflowConstants.STATUS_DRAFT)) {

			return IndexerWriterMode.UPDATE;
		}

		return IndexerWriterMode.DELETE;
	}
    ```

    `com.liferay.portal.search.spi.model.index.contributor.helper.IndexerWriterMode`
    defines the following index-writing options:

    - `IndexerWriterMode.DELETE`: instructs the search framework to delete the
        given document in the search index without re-creating it
    - `IndexerWriterMode.PARTIAL_UPDATE`, `IndexerWriterMode.UPDATE`: instructs the
        search framework to update the given document in the search index.
    - `IndexerWriterMode.SKIP`: instructs the search framework to not write
        anything to the search index.

    The default is `IndexerWriterMode.UPDATE`.

6.  Add the services referenced:

    ```java
	@Reference
	private FooEntryLocalService _fooEntryLocalService;

	@Reference
	private DynamicQueryBatchIndexingActionableFactory
		_dynamicQueryBatchIndexingActionableFactory;
    ```

## Contribute Fields to Every Document

`DocumentContributor` classes (without any `indexer.class.name`  component
property or type parameter) contribute certain fields to every index document,
regardless of what base entity is being indexed. For example, the
[`GroupedModelDocumentContributor`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/portal-search/portal-search/src/main/java/com/liferay/portal/search/internal/contributor/document/GroupedModelDocumentContributor.java)
contains logic to contribute `GROUP_ID` and `SCOPE_GROUP_ID` fields for all
documents with a backing entity that's also a `GroupedModel`.
