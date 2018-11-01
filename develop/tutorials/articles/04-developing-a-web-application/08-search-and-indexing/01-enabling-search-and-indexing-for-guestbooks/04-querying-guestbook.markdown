# Querying for Guestbook Documents [](id=querying-for-guestbook-documents)

<div class="learn-path-step">
    <p>Enabling Search and Indexing for Guestbooks<br>Step 4 of 6</p>
</div>

The code is in place for for indexing Guestbooks the search engine. Next code
the behavior necessary for querying the indexed documents.

Implement two interfaces:

1.  `KeywordQueryContributor`  contributes clauses to the ongoing search query.

2.  `ModelPreFilterContributor` controls how search results are filtered before
    they're returned from the search engine.

## Implementing `KeywordQueryContributor` [](id=implementing-keywordquerycontributor)

Create `GuestbookKeywordQueryContributor` and populate it with these
contents:

    @Component(
            immediate = true,
            property = "indexer.class.name=com.liferay.docs.guestbook.model.Guestbook",
            service = KeywordQueryContributor.class
    )
    public class GuestbookKeywordQueryContributor
        implements KeywordQueryContributor {

        @Override
        public void contribute(
            String keywords, BooleanQuery booleanQuery,
            KeywordQueryContributorHelper keywordQueryContributorHelper) {

            SearchContext searchContext =
        keywordQueryContributorHelper.getSearchContext();

            queryHelper.addSearchLocalizedTerm(
        booleanQuery, searchContext, Field.TITLE, false);
        }

        @Reference
        protected QueryHelper queryHelper;

    }

Get the `SearchContext` object, which contains lots of information about the
ongoing search query. Use the query helper to add search terms. Adding the
localized search term is important. Since the localized Guestbook title was
indexed, you must retrieve the localized value from the search engine.

## Implementing `ModelPreFilterContributor` [](id=implementing-modelprefiltercontributor)

Create `GuestbookModelPreFilterContributor` and populate it with these contents:

    @Component(
            immediate = true,
            property = "indexer.class.name=com.liferay.docs.guestbook.model.Guestbook",
            service = ModelPreFilterContributor.class
    )
    public class GuestbookModelPreFilterContributor
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
add the workflow status of the Guestbook to the query. You'll learn more about
workflow in a later section.

Once the query code is in place, define how returned Guestbook documents are
summarized. 
