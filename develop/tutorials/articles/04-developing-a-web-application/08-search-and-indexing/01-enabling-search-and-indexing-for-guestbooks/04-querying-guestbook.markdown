# Querying for Guestbook Documents [](id=querying-for-guestbook-documents)

<div class="learn-path-step">
    <p>Enabling Search and Indexing for Guestbooks<br>Step 4 of 6</p>
</div>

The code is in place for for indexing Guestbooks to the search engine. Next,
you'll code the behavior necessary for querying the indexed documents.

Implement two interfaces:

1.  `KeywordQueryContributor` contributes clauses to the ongoing search query.

2.  `ModelPreFilterContributor` controls how search results are filtered before
    they're returned from the search engine.

## Implementing `KeywordQueryContributor` [](id=implementing-keywordquerycontributor)

Create `GuestbookKeywordQueryContributor`: 

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

This class adds Guestbook fields to the search query constructed by the Search
application in @product@. Later, when you asset enable Guestbooks, this code
will allow indexed Guestbooks to be searched from the Search application when
a keyword is entered into the search bar. Use the query helper to add search
terms to the query that allow Guestbooks to be found. Here it's important to
note that adding the localized search term is important. Since the localized
Guestbook title was indexed, you must retrieve the localized value from the
search engine.

Once the query code is in place, define how returned Guestbook documents are
summarized. 
