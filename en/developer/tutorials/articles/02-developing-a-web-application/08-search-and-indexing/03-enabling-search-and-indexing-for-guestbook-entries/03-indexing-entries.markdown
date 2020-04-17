---
header-id: indexing-entries
---

# Indexing Entries

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Enabling Search and Indexing for Entries</p><p>Step 2 of 5</p>
</div>

To control how `GuestbookEntry `objects are translated into search engine
documents, create these classes in the search package:

1.  `ModelDocumentContributor`: The main searchable fields for entries are
    _Name_ and _Message_. The Guestbook name associated with the entry is
    indexed, too.

2.  `ModelIndexerWriterContributor` configures the batch indexing behavior for
    entries. This code is executed when Entries are re-indexed from the
    Search administration section of the Control Panel.

3.  A new interface, `GuestbookEntryBatchReindexer`, with its implementation,
    `GuestbookEntryBatchReindexerImpl`. These classes contain code to ensure
    that entries are re-indexed when their Guestbook is updated.

## Implementing `ModelDocumentContributor`

Create `GuestbookEntryModelDocumentContributor` and populate it with this:

```java
@Component(
        immediate = true,
        property = "indexer.class.name=com.liferay.docs.guestbook.model.GuestbookEntry",
        service = ModelDocumentContributor.class
)
public class GuestbookEntryModelDocumentContributor
    implements ModelDocumentContributor<GuestbookEntry> {

    @Override
    public void contribute(Document document, GuestbookEntry entry) {
        try {
            Locale defaultLocale = PortalUtil.getSiteDefaultLocale(
    entry.getGroupId());

            document.addDate(Field.MODIFIED_DATE, entry.getModifiedDate());
            document.addText("entryEmail", entry.getEmail());

            String localizedTitle = LocalizationUtil.getLocalizedName(
    Field.TITLE, defaultLocale.toString());
            String localizedContent = LocalizationUtil.getLocalizedName(
    Field.CONTENT, defaultLocale.toString());

            document.addText(localizedTitle, entry.getName());
            document.addText(localizedContent, entry.getMessage());

            long guestbookId = entry.getGuestbookId();

            Guestbook guestbook = _guestbookLocalService.getGuestbook(
    guestbookId);

            String guestbookName = guestbook.getName();

            String localizedGbName = LocalizationUtil.getLocalizedName(
    Field.NAME, defaultLocale.toString());

            document.addText(localizedGbName, guestbookName);
        } catch (PortalException pe) {
            if (_log.isWarnEnabled()) {
                _log.warn("Unable to index entry " + entry.getEntryId(), pe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final Log _log = LogFactoryUtil.getLog(
    GuestbookEntryModelDocumentContributor.class);

    @Reference
    private GuestbookLocalService _guestbookLocalService;

}
```

As with Guestbooks, add the localized values for fields that might be
translated. The Site locale is appended to the field (e.g., `title_en_US`), so
the field gets passed to the search engine and goes through the right analysis
and
[tokenization](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/analysis-tokenizers.html). 

Use Ctrl-Shift-O to add the following imports and save the file: 

- `com.liferay.portal.kernel.search.Document`
- `com.liferay.portal.kernel.search.Field`

## Implementing `ModelIndexerWriterContributor`

Create `GuestbookEntryModelIndexerWriterContributor` and populate it with this:

```java
@Component(
        immediate = true,
        property = "indexer.class.name=com.liferay.docs.guestbook.model.GuestbookEntry",
        service = ModelIndexerWriterContributor.class
)
public class GuestbookEntryModelIndexerWriterContributor
    implements ModelIndexerWriterContributor<GuestbookEntry> {

    @Override
    public void customize(
        BatchIndexingActionable batchIndexingActionable,
        ModelIndexerWriterDocumentHelper modelIndexerWriterDocumentHelper) {

        batchIndexingActionable.setPerformActionMethod((GuestbookEntry entry) -> {
            Document document = modelIndexerWriterDocumentHelper.getDocument(
    entry);

            batchIndexingActionable.addDocuments(document);
            
        });
    }

    @Override
    public BatchIndexingActionable getBatchIndexingActionable() {
        return dynamicQueryBatchIndexingActionableFactory.getBatchIndexingActionable(
    guestbookEntryLocalService.getIndexableActionableDynamicQuery());
    }

    @Override
    public long getCompanyId(GuestbookEntry entry) {
        return entry.getCompanyId();
    }

    @Reference
    protected DynamicQueryBatchIndexingActionableFactory
    dynamicQueryBatchIndexingActionableFactory;

    @Reference
    protected GuestbookEntryLocalService guestbookEntryLocalService;

}
```

The interesting work is done in the `customize` method, where all entries are
retrieved and added to a collection. 

Use Ctrl-Shift-O to add an import for
`com.liferay.portal.kernel.search.Document` and save the file. 

## Implementing `GuestbookEntryBatchReindexer`

Create a new interface, `GuestbookEntryBatchReindexer`, with one method called
`reindex`:

```java
package com.liferay.docs.guestbook.search;

public interface GuestbookEntryBatchReindexer {

    public void reindex(long guestbookId, long companyId);

}
```

Then create the implementation class, `GuestbookEntryBatchReindexerImpl`:

```java
@Component(immediate = true, service = GuestbookEntryBatchReindexer.class)
public class GuestbookEntryBatchReindexerImpl implements GuestbookEntryBatchReindexer {

    @Override
    public void reindex(long guestbookId, long companyId) {
        BatchIndexingActionable batchIndexingActionable =
    indexerWriter.getBatchIndexingActionable();

        batchIndexingActionable.setAddCriteriaMethod(dynamicQuery -> {
            Property guestbookIdPropery = PropertyFactoryUtil.forName(
    "guestbookId");

            dynamicQuery.add(guestbookIdPropery.eq(guestbookId));
        });

        batchIndexingActionable.setCompanyId(companyId);

        batchIndexingActionable.setPerformActionMethod((GuestbookEntry entry) -> {
            Document document = indexerDocumentBuilder.getDocument(entry);

            batchIndexingActionable.addDocuments(document);
        });

        batchIndexingActionable.performActions();

    }

    @Reference(target = "(indexer.class.name=com.liferay.docs.guestbook.model.GuestbookEntry)")
    protected IndexerDocumentBuilder indexerDocumentBuilder;

    @Reference(target = "(indexer.class.name=com.liferay.docs.guestbook.model.GuestbookEntry)")
    protected IndexerWriter<GuestbookEntry> indexerWriter;

}
```

The `reindex` method of the interface is called when a Guestbook is updated.
The entry documents are re-indexed to include the update Guestbook title.

Use Ctrl-Shift-O to add the following imports, and save the file: 

- `com.liferay.portal.kernel.search.Document`
- `com.liferay.portal.kernel.dao.orm.Property`

You should notice that errors in the project go away at this point. 

Once the re-indexing behavior is in place, you can move on to the code for
controlling how `GuestbookEntry` documents are queried from the search engine.
