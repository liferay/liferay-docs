# Generating Results Summaries

The Search application and the Asset Publisher application display results
retrieved from the search engine. Control the display by implementing a
`ModelSummaryContributor`.

Create a `EntryModelSummarySummaryContributor`:

    @Component(
            immediate = true,
            property = "indexer.class.name=com.liferay.docs.guestbook.model.Entry",
            service = ModelSummaryContributor.class
    )
    public class EntryModelSummaryContributor implements ModelSummaryContributor {

        @Override
        public Summary getSummary(
            Document document, Locale locale, String snippet) {

            Summary summary = createSummary(document);

            summary.setMaxContentLength(128);

            return summary;
        }

        private Summary createSummary(Document document) {
            String prefix = Field.SNIPPET + StringPool.UNDERLINE;

            String title = document.get(prefix + Field.TITLE, Field.CONTENT);
            String content = document.get(prefix + Field.CONTENT, Field.CONTENT);

            return new Summary(title, content);
        }

    }

First override `getSummary`, and set the maximum summary length on the summary
returned. The value `200` is a Liferay standard. Control the summary creation
in a utility method called `createSummary`. Guestbooks only included the title
in the summary, but Entries will use the title and the content (the Entry
message field) to populate the summary.

Create summaries by combining key parts of the entity's data.

Once the search and indexing logic is in place, update the service layer so
`add`, `update`, and `delete` service calls trigger the new logic.
