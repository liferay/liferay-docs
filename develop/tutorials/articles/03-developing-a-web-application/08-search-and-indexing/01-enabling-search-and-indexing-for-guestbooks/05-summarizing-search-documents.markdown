# Generating Results Summaries

The Search application and the Asset Publisher application must display results
retrieved from the search engine. Control the summarized content by implementing
a `ModelSummaryContributor`.

A summary is a condensed, text-based version of the entity's document that can
be displayed generically. You create it by combining key parts of the entity's
data so users can browse through search results to find the entity they want.

Create a `GuestbookModelSummarySummaryContributor`:

    @Component(
            immediate = true,
            property = "indexer.class.name=com.liferay.docs.guestbook.model.Guestbook",
            service = ModelSummaryContributor.class
    )
    public class GuestbookModelSummaryContributor
        implements ModelSummaryContributor {

        @Override
        public Summary getSummary(
            Document document, Locale locale, String snippet) {

            Summary summary = createSummary(document);

            summary.setMaxContentLength(200);

            return summary;
        }

        private Summary createSummary(Document document) {
            String prefix = Field.SNIPPET + StringPool.UNDERLINE;

            String title = document.get(prefix + Field.TITLE, Field.TITLE);

            return new Summary(title, StringPool.BLANK);
        }

    }

First override `getSummary`, and set the maximum summary length on the summary
returned. The value `200` is a Liferay standard. Control the summary creation in
a utility method called `createSummary`. Create a `prefix` variable using two
constants, `Field.SNIPPET` and `Stringpool.UNDERLINE`. The `snippet_title` field
is returned from the `document.get` call, and added to the summary. Using the
snippet field provides two benefits:

1.  Snippet text can be highlighted so matching keywords are emphasized.

2.  Snippet text can be automatically shortened by the Search application so a
    sensible portion of the field's text is displayed in the search results. 

Guestbook titles are likely short, so only the highlighting behavior is likely
to be useful for the title field of Guestbooks. For longer fields (like some
`content` fields), the clipping behavior is more useful.  Additional
highlighting behavior can be configured via the `index.search.highlight.*`
properties in
[portal.properties](https://docs.liferay.com/portal/7.1-latest/propertiesdoc/portal.properties.html#Lucene%20Search).

Create summaries by combining key parts of the entity's data so users can browse
through search results to find the entity they want.

Once all the search and indexing logic is in place, update the service layer so
`add`, `update`, and `delete` service calls trigger the new logic.
