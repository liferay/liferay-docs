# Indexing Entries

<div class="learn-path-step">
    <p>Enabling Search and Indexing for Entries<br>Step 2 of 5</p>
</div>

To control how Entry objects are translated into search engine documents,
create two classes in the new search package. The classes were explained in
more detail for Guestbooks:

1.  `ModelDocumentContributor`: The main searchable fields for Entries are
    _Name_ and _Message_. The Guestbook name associated with the entry is
    indexed, too.

2.  `ModelIndexerWriterContributor` configures the batch indexing behavior for
    Entries. This code is executed when Entries are re-indexed from the
    Search administration section of the Control Panel.

## Implementing `ModelDocumentContributor`

Create `EntryModelDocumentContributor` and populate it with these contents:

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
translated. The site locale is appended to the field (e.g., `title_en_US`), so
the field gets passed to the search engine and goes through the right analysis
and
[tokenization](https://www.elastic.co/guide/en/elasticsearch/reference/2.4/analysis-tokenizers.html). 

## Implementing `ModelIndexerWriterContributor`

Create `EntryModelIndexerWriterContributor` and populate it with these
contents:

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

Once the re-indexing behavior is in place, move on to the code for controlling
how Entry documents are queried from the search engine.
