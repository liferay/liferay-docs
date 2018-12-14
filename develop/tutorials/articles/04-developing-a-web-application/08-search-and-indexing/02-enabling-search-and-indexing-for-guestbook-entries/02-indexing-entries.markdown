# Indexing Entries [](id=indexing-entries)

<div class="learn-path-step">
    <p>Enabling Search and Indexing for Entries<br>Step 2 of 5</p>
</div>

To control how Entry objects are translated into search engine documents,
create these classes in the search package:

1.  `ModelDocumentContributor`: The main searchable fields for Entries are
    _Name_ and _Message_. The Guestbook name associated with the entry is
    indexed, too.

2.  `ModelIndexerWriterContributor` configures the batch indexing behavior for
    Entries. This code is executed when Entries are re-indexed from the
    Search administration section of the Control Panel.

3.  A new interface, `EntryBatchReindexer`, with its implementation,
    `EntryBatchReindexerImpl`. These classes contain code to ensure that Entries
    are re-indexed when their Guestbook is updated.

## Implementing `ModelDocumentContributor` [](id=implementing-modeldocumentcontributor)

Create `EntryModelDocumentContributor` and populate it with this:

    @Component(
            immediate = true,
            property = "indexer.class.name=com.liferay.docs.guestbook.model.Entry",
            service = ModelDocumentContributor.class
    )
    public class EntryModelDocumentContributor
        implements ModelDocumentContributor<Entry> {

        @Override
        public void contribute(Document document, Entry entry) {
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
        EntryModelDocumentContributor.class);

        @Reference
        private GuestbookLocalService _guestbookLocalService;

    }

As with Guestbooks, add the localized values for fields that might be
translated. The Site locale is appended to the field (e.g., `title_en_US`), so
the field gets passed to the search engine and goes through the right analysis
and
[tokenization](https://www.elastic.co/guide/en/elasticsearch/reference/2.4/analysis-tokenizers.html). 

## Implementing `ModelIndexerWriterContributor` [](id=implementing-modelindexerwritercontributor)

Create `EntryModelIndexerWriterContributor` and populate it with this:

    @Component(
            immediate = true,
            property = "indexer.class.name=com.liferay.docs.guestbook.model.Entry",
            service = ModelIndexerWriterContributor.class
    )
    public class EntryModelIndexerWriterContributor
        implements ModelIndexerWriterContributor<Entry> {

        @Override
        public void customize(
            BatchIndexingActionable batchIndexingActionable,
            ModelIndexerWriterDocumentHelper modelIndexerWriterDocumentHelper) {

            batchIndexingActionable.setPerformActionMethod((Entry entry) -> {
                Document document = modelIndexerWriterDocumentHelper.getDocument(
        entry);

                batchIndexingActionable.addDocuments(document);
                
            });
        }

        @Override
        public BatchIndexingActionable getBatchIndexingActionable() {
            return dynamicQueryBatchIndexingActionableFactory.getBatchIndexingActionable(
        entryLocalService.getIndexableActionableDynamicQuery());
        }

        @Override
        public long getCompanyId(Entry entry) {
            return entry.getCompanyId();
        }

        @Reference
        protected DynamicQueryBatchIndexingActionableFactory
        dynamicQueryBatchIndexingActionableFactory;

        @Reference
        protected EntryLocalService entryLocalService;

    }

The interesting work is done in the `customize` method, where all Entries are
retrieved and added to a collection. 

## Implementing `EntryBatchReindexer` [](id=implementing-entrybatchreindexer)

Create a new interface class, `EntryBatchReindexer`, with one method called
`reindex`:


    package com.liferay.docs.guestbook.search;

    public interface EntryBatchReindexer {

        public void reindex(long guestbookId, long companyId);

    }

Then create the implementation class, `EntryBatchReindexerImpl`:

    @Component(immediate = true, service = EntryBatchReindexer.class)
    public class EntryBatchReindexerImpl implements EntryBatchReindexer {

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

            batchIndexingActionable.setPerformActionMethod((Entry entry) -> {
                Document document = indexerDocumentBuilder.getDocument(entry);

                batchIndexingActionable.addDocuments(document);
            });

            batchIndexingActionable.performActions();
            
        }

        @Reference(target = "(indexer.class.name=com.liferay.docs.guestbook.model.Entry)")
        protected IndexerDocumentBuilder indexerDocumentBuilder;

        @Reference(target = "(indexer.class.name=com.liferay.docs.guestbook.model.Entry)")
        protected IndexerWriter<Entry> indexerWriter;

    }

    The `reindex` method of the interface is called when a Guestbook is updated.
    The entry documents are re-indexed to include the update Guestbook title.

Once the re-indexing behavior is in place, move on to the code for controlling
how Entry documents are queried from the search engine.
