# Indexing Model Entities

Model entities are searchable when their data fields are sent to the search
engine, to be later used during a search.

## Contributing Model Entity Fields to the Index

Write a [`ModelDocumentContributor`
class](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/index/contributor/ModelDocumentContributor.java)
to control how model entity fields are indexed in search engine documents.

Declare the Component's `indexer.class.name` and its service type as a
`ModelDocumentContributor.class`:

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
`com.liferay.portal.kerne.Document.add()` method appropriate for the data type
the index should use (e.g., `addText` for fields that should be searched using a
[full text search
strategy](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/text.html)).

```java
	@Override
	public void contribute(Document document, FooEntry fooEntry) {

		document.addDate(Field.DISPLAY_DATE, fooEntry.getDisplayDate());
		document.addDate(Field.MODIFIED_DATE, fooEntry.getModifiedDate());
		document.addText(Field.SUBTITLE, fooEntry.getSubtitle());
```

For fields that should be
[localized](/docs/7-2/deploy/-/knowledge_base/d/localization), index a field for
each locale in the site. Many times you'll want to localize the title and
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
	}
}
```

The `contribute` method is called each time the `add` and `update` methods in
the entity's service layer are called.

<!-- This section needs help! -->
## Configure Re-Indexing and Batch Indexing Behavior

[`ModelIndexerWriterContributor`](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/index/contributor/ModelIndexerWriterContributor.java) 
classes configure the re-indexing and batch re-indexing behavior for the model
entity. This class's `customize` method is called when a re-index is triggered
from the Search administrative application found in Control Panel &rarr;
Configuration &rarr; Search, or when a CRUD operation is made on the entity,
_if_ the `modelIndexed` method is implemented in the contributor.

The bulk of the work is in the `customize` method. This code
uses the actionable dynamic query helper method to retrieve all
Guestbooks in the virtual instance (identified by the Company ID). Service
Builder generated this query method for you when you built the services. Each
Guestbook's document is then retrieved and added to a collection.

```java
@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.foo.model.FooEntry",
	service = ModelIndexerWriterContributor.class
)
public class FooEntryModelIndexerWriterContributor
	implements ModelIndexerWriterContributor<FooEntry> {
```

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

```java
@Override
public BatchIndexingActionable getBatchIndexingActionable() {
    return _dynamicQueryBatchIndexingActionableFactory.
        getBatchIndexingActionable(
            _fooEntryLocalService.getIndexableActionableDynamicQuery());
}
```

```java
	@Override
	public long getCompanyId(FooEntry fooEntry) {
		return fooEntry.getCompanyId();
	}


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

	@Reference
	private FooEntryLocalService _fooEntryLocalService;

	@Reference
	private DynamicQueryBatchIndexingActionableFactory
		_dynamicQueryBatchIndexingActionableFactory;

}
```

## Contribute Fields to Every Document

`DocumentContributor` classes (without any `indexer.class.name`  component
property or type parameter) contribute certain fields to every index document,
regardless of what base entity is being indexed. For example, the
[`GroupedModelDocumentContributor`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/portal-search/portal-search/src/main/java/com/liferay/portal/search/internal/contributor/document/GroupedModelDocumentContributor.java)
contains logic to contribute `GROUP_ID` and `SCOPE_GROUP_ID` fields for all
documents with a backing entity that's also a `GroupedModel`.
