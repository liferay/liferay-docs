# Querying for Guestbook Documents

The code is in place for for indexing Guestbooks the search engine. Next code
the behavior necessary for querying the indexed documents.

Implement two classes:

1.  `GuestbookKeywordQueryContributor`  contributes clauses to the ongoing
search query.

2.  `GuestbookModelPreFilterContributor` controls how search results are filtered
before they're returned from the search engine.

## Implementing `KeywordQueryContributor`

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

Get the `SearchContext` object, which contains lots of information Adding the localized search term is important. Since the localized Guestbook
title was indexed, retrieve the localized value from the search engine.

## Implementing `ModelPreFilterContributor`

Create `GuestbookKeywordQueryContributor` and populate it with these
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
