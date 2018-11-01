# Indexing Guestbooks [](id=indexing-guestbooks)

<div class="learn-path-step">
    <p>Enabling Search and Indexing for Guestbooks<br>Step 3 of 6</p>
</div>

To control how Guestbook objects are translated into search engine documents,
create two classes in the new search package:

1.  Implement a `ModelDocumentContributor` to select the Guestbook entity's
    fields that populate a search document indexed by the search engine.
    The main searchable field for guestbooks is the guestbook name. 

2.  `ModelIndexerWriterContributor` configures the batch indexing behavior for
    Guestbooks. This code is executed when Guestbooks are re-indexed from the
    Search administration section of the Control Panel.

## Implementing `ModelDocumentContributor` [](id=implementing-modeldocumentcontributor)

Create `GuestbookModelDocumentContributor` and populate it with these contents:


    @Component(
            immediate = true,
            property = "indexer.class.name=com.liferay.docs.guestbook.model.Guestbook",
            service = ModelDocumentContributor.class
    )
    public class GuestbookModelDocumentContributor
        implements ModelDocumentContributor<Guestbook> {

        @Override
        public void contribute(Document document, Guestbook guestbook) {
            try {
                document.addDate(Field.MODIFIED_DATE, guestbook.getModifiedDate());

                Locale defaultLocale = PortalUtil.getSiteDefaultLocale(
        guestbook.getGroupId());

                String localizedTitle = LocalizationUtil.getLocalizedName(
        Field.TITLE, defaultLocale.toString());

                document.addText(localizedTitle, guestbook.getName());
            } catch (PortalException pe) {
                if (_log.isWarnEnabled()) {
                    _log.warn(
        "Unable to index guestbook " + guestbook.getGuestbookId(), pe);
                }
            }
        }

        private static final Log _log = LogFactoryUtil.getLog(
        GuestbookModelDocumentContributor.class);

    }

Because @product@ supports localization, you should too. The above code gets 
the default locale from the site by passing the `Guestbook`'s group ID to 
the `getSiteDefaultLocale` method, then using it to get the localized name
of the Guestbook's title field. The retrieved site locale is appended to the
field (e.g., `title_en_US`), so the field gets passed to the search engine
and goes through the right analysis and
[tokenization](https://www.elastic.co/guide/en/elasticsearch/reference/2.4/analysis-tokenizers.html). 

## Implementing `ModelIndexerWriterContributor` [](id=implementing-modelindexerwritercontributor)

Create `GuestbookModelIndexerWriterContributor` and populate it with these
contents:

    @Component(
            immediate = true,
            property = "indexer.class.name=com.liferay.docs.guestbook.model.Guestbook",
            service = ModelIndexerWriterContributor.class
    )
    public class GuestbookModelIndexerWriterContributor
        implements ModelIndexerWriterContributor<Guestbook> {

        @Override
        public void customize(
            BatchIndexingActionable batchIndexingActionable,
            ModelIndexerWriterDocumentHelper modelIndexerWriterDocumentHelper) {

            batchIndexingActionable.setPerformActionMethod((Guestbook guestbook) -> {
                Document document = modelIndexerWriterDocumentHelper.getDocument(
        guestbook);

                batchIndexingActionable.addDocuments(document);
            });
        }

        @Override
        public BatchIndexingActionable getBatchIndexingActionable() {
            return dynamicQueryBatchIndexingActionableFactory.getBatchIndexingActionable(
        guestbookLocalService.getIndexableActionableDynamicQuery());
        }

        @Override
        public long getCompanyId(Guestbook guestbook) {
            return guestbook.getCompanyId();
        }

    	@Override
        public void modelIndexed(Guestbook guestbook) {
            entryBatchReindexer.reindex(
        guestbook.getGuestbookId(), guestbook.getCompanyId());
        }

        @Reference
        protected DynamicQueryBatchIndexingActionableFactory
        dynamicQueryBatchIndexingActionableFactory;

    	@Reference
        protected EntryBatchReindexer entryBatchReindexer;
    
        @Reference
        protected GuestbookLocalService guestbookLocalService;

    }

First look at the `customize` method. Configure the batch indexing behavior for
the entity's documents by calling `BatchIndexingActionable` methods. This code
uses the Guestbook's actionable dynamic query helper method to retrieve all
Guestbooks in the virtual instance (identified by the Company ID). Service
Builder generated this query method for you when you built the services. Each
Guestbook's document is then retrieved and added to a collection.

When you write the indexing classes for Entries, you'll add the Guestbook title
to the Entry document. Thus, you must provide a way to update the indexed Entry
documents if a Guestbook title is changed.  The `modelIndexed` method is used
to call a `reindex`  method form an interface that will be created later for
Entries. 

Once the re-indexing behavior is in place, move on to the code for controlling
how Guestbook documents are queried from the search engine.
