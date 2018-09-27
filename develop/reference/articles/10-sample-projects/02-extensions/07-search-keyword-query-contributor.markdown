# Search Keyword Query Contributor [](id=search-keyword-query-contributor)

## What does this sample do when it's deployed? [](id=what-does-this-sample-do-when-its-deployed)

This sample allows searching for blog entries based on the caption value of the
blog image. You can demo this sample by completing the following steps:

1.  Add the *Search Bar* and *Search Results* widgets to a page.
2.  Add a blog entry filling the field caption located below the blog's image and
    above the blog's title.
3.  Try to search by the entry on the search bar portlet using caption value.
    There are no search results returned.
4.  Deploy the module.
5.  Try to search by the entry on the search bar portlet using caption value. The
    matching blog entry is displayed in the search results.

## What API(s) and/or code components does this sample highlight? [](id=what-apis-and-or-code-components-does-this-sample-highlight)

This sample leverages the
[KeywordQueryContributor](@app-ref@/portal-search/latest/javadocs/com/liferay/portal/search/spi/model/query/contributor/KeywordQueryContributor.html)
API.

## How does this sample leverage the API(s) and/or code component? [](id=how-does-this-sample-leverage-the-apis-and-or-code-component)

This sample conveys the recommended approach to adding a field, which may
contribute to the relevance of the search, in keyword queries.

To achieve this goal, you need to create a component that implements the
`com.liferay.portal.search.spi.model.query.contributor.KeywordQueryContributor`.

Specifically, you must implement the `contribute` method, which is invoked when
a user enters something in the search bar:

    @Override
    public void contribute(
        String keywords, BooleanQuery booleanQuery,
        KeywordQueryContributorHelper keywordQueryContributorHelper) {

        SearchContext searchContext =
            keywordQueryContributorHelper.getSearchContext();

        queryHelper.addSearchLocalizedTerm(
            booleanQuery, searchContext, Field.CAPTION, true);
    }

This method uses Declarative Services to get a reference for the `QueryHelper`
to invoke the `addSearchLocalizedTerm` method.

Also, it is important to highlight the `@Component` annotation that registers a
new service to OSGi:

    @Component(
        immediate = true,
        property = "indexer.class.name=com.liferay.calendar.model.Calendar",
        service = KeywordQueryContributor.class
    )

You can implement similar functionality by using a `ModelPreFilterContributor`.
To help you make an informed decision about which to use, information about both
options is listed below:

To choose between implementing a `KeywordQueryContributor` or a
`ModelPreFilterContributor`, consider these below items:

- Filters are cached and don't influence the score; therefore, they're faster
  than queries.
- A query is usually something unpredictable that the user types, while filters
  help users narrow down search results. For example, using facets.

For more information, read
[Elasticsearch's documentation](https://www.elastic.co/guide/en/elasticsearch/guide/master/_queries_and_filters.html).

## Where Is This Sample? [](id=where-is-this-sample)

There are three different versions of this sample, each built with a different
build tool:

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/7.1/gradle/extensions/search-keyword-query-contributor)
- [Liferay Workspace](https://github.com/liferay/liferay-blade-samples/tree/7.1/liferay-workspace/extensions/search-keyword-query-contributor)
- [Maven](https://github.com/liferay/liferay-blade-samples/tree/7.1/maven/extensions/search-keyword-query-contributor)