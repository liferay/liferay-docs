# Querying for Entry Documents

The code is in place for for indexing Entries the search engine. Next code
the behavior necessary for querying the indexed documents.

Implement two classes:

1.  `EntryKeywordQueryContributor`  contributes clauses to the ongoing
search query.

2.  `EntryModelPreFilterContributor` controls how search results are filtered
before they're returned from the search engine.

## Implementing `KeywordQueryContributor`

Create `EntryKeywordQueryContributor` and populate it with these
contents:

    @Component(
            immediate = true,
            property = "indexer.class.name=com.liferay.docs.guestbook.model.Entry",
            service = KeywordQueryContributor.class
    )
    public class EntryKeywordQueryContributor implements KeywordQueryContributor {

        @Override
        public void contribute(
            String keywords, BooleanQuery booleanQuery,
            KeywordQueryContributorHelper keywordQueryContributorHelper) {

            SearchContext searchContext =
        keywordQueryContributorHelper.getSearchContext();

            queryHelper.addSearchLocalizedTerm(
        booleanQuery, searchContext, Field.TITLE, false);
            queryHelper.addSearchLocalizedTerm(
        booleanQuery, searchContext, Field.CONTENT, false);
            queryHelper.addSearchLocalizedTerm(
        booleanQuery, searchContext, "entryEmail", false);
        }

        @Reference
        protected QueryHelper queryHelper;

    }

Adding the localized search terms is important. For all localized Entry fields
in the index, retrieve the localized value from the search engine.

## Implementing `ModelPreFilterContributor`

Create `EntryKeywordQueryContributor` and populate it with these
contents:

    @Component(
            immediate = true,
            property = "indexer.class.name=com.liferay.docs.guestbook.model.Entry",
            service = ModelPreFilterContributor.class
    )
    public class EntryModelPreFilterContributor
        implements ModelPreFilterContributor {

        @Override
        public void contribute(
            BooleanFilter booleanFilter, ModelSearchSettings modelSearchSettings,
            SearchContext searchContext) {

            workflowStatusModelPreFilterContributor.contribute(
        booleanFilter, modelSearchSettings, searchContext);
        }

        @Reference(target = "(model.pre.filter.contributor.id=WorkflowStatus)")
        protected ModelPreFilterContributor workflowStatusModelPreFilterContributor;

    }

You don't want results with a workflow status of `STATUS_IN_TRASH` queried, so
add the workflow status of the Entry to the query. You'll learn more about
workflow in a later section.

Once the query code is in place, define how returned Entry documents are
summarized. 
