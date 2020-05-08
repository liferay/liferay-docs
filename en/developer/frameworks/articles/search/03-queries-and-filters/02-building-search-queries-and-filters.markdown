---
header-id: building-search-queries-and-filters
---

# Building Search Queries and Filters

[TOC levels=1-4]

Each filter and query has a [different
purpose](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/query-dsl.html),
but the way you'll add the information to the search request is similar between
all queries and filters.

## Queries

The complete dummy code snippet for building the below Query is available for
your copying and pasting convenience at the end of this section.

### Build the Search Query

1.  Use the `com.liferay.portal.search.query.Queries` interface to instantiate
    the query you'll construct. For example,

    ```java
    TermsQuery rootFolderQuery = _queries.terms("folderId");
    MatchQuery titleQuery = queries.match("title_en_US", keywords);
    ```

    To discover what fields each query must have (e.g., `String field` in the
    case of the above `com.liferay.portal.search.query.TermsQuery`), see the
    [`Queries`](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/portal-search/portal-search-api/src/main/java/com/liferay/portal/search/query/Queries.java)
    interface. Note that you'd need `keywords` to use the above code: `String
    keywords = "Home"`, for example.

2.  Build out the query to get the desired response. This looks different
    for each query type, but Elasticsearch's documentation on the query type
    explains it (for example, 
    [Terms Query](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/query-dsl-terms-query.html)).

    For a Terms Query, you might just add a value to match:

    ```java
    rootFolderQuery.addValues("0");
    ```

    This TermsQuery construct matches Documents with a `folderId` of `0`, which
    is used to signify a root folder (as opposed to a subfolder).

3.  You might want to wrap queries. For example, use the queries constructed
    above as MUST clauses in a `BooleanQuery` wrapper:

    ```java
    BooleanQuery booleanQuery = _queries.booleanQuery();
    booleanQuery.addMustQueryClauses(rootFolderQuery, titleQuery);
    ```

    Now the query you constructed will return only root folders matching (via
    full text search, since it's a `MatchQuery`) the keywords with the localized
    title field.

Once the query itself is in good shape, feed it to the search request.

### Build the Search Request

1.  Get a service Reference to `com.liferay.portal.search.legacy.searcher.SearchRequestBuilderFactory`, then get an instance of `com.liferay.portal.search.searcher.SearchRequestBuilder`
    from the  service:

    ```java
    @Reference
    SearchRequestBuilderFactory _searchRequestBuilderFactory;

    SearchRequestBuilder searchRequestBuilder = _searchRequestBuilderFactory.getSearchRequestBuilder();
    ```

    Before building the request with the query, make sure the request builder
enables empty search and sets the `companyId` and `keywords` into the Search
Context:

    ```java
    searchRequestBuilder.emptySearchEnabled(true);

    searchRequestBuilder.withSearchContext(
                searchContext -> searchContext.setCompanyId(companyId);
                searchContext -> searchContext.setKeywords(keywords);
            );
    ```

2.  Get a `com.liferay.portal.search.searcher.SearchRequest` instance from the
    builder, then add the query to it and run its `build` method:

    ```java
    SearchRequest searchRequest = searchRequestBuilder.query(booleanQuery).build();
    ```

<!-- I'm not sure this still applies; please advise -->
3.  To instead use the constructed query in a filter context, call the
    `postFilterQuery` method:

    ```java
    SearchRequest searchRequest = searchRequestBuilder.postFilterQuery(termsQuery).build();
    ```

    When constructing a search request, you'll often find it necessary to chain
    the builder methods together:

<!-- Though we should still show this -->
    ```java
    SearchRequest searchRequest = 
        searchRequestBuilder.postFilterQuery(myQuery1).query(myQuery2).build();
    ```

    Doing this allows you to add filters and queries (and anything else from
    the builder API) to the same request in one fell swoop.

### Execute the Search Request

1.  Perform a search using the `com.liferay.portal.search.searcher.Searcher`
    service and the `SearchRequest` to get a
    `com.liferay.portal.search.searcher.SearchResponse`:

    ```java
    SearchResponse _searcher.search(searchRequest);
    ```

2.  To satisfy the dependencies of the example code here, get a reference to
    `com.liferay.portal.search.searcher.SearchRequestBuilderFactory`,
    `com.liferay.portal.search.searcher.Searcher`, and
    `com.liferay.portal.search.query.Queries`:

    ```java
    @Reference
    protected Queries _queries;

    @Reference
    protected Searcher _searcher;

    @Reference
    SearchRequestBuilderFactory _searchRequestBuilderFactory;
    ```

    Here's the complete code snippet:

    ```java
    MatchQuery titleQuery = queries.match("title_en_US", keywords);

    TermsQuery rootFolderQuery = _queries.terms("folderId");
    rootFolderQuery.addValues("0");

    BooleanQuery booleanQuery = _queries.booleanQuery();
    booleanQuery.addMustQueryClauses(rootFolderQuery, titleQuery);

    SearchRequestBuilder searchRequestBuilder = _searchRequestBuilderFactory.getSearchRequestBuilder();

    searchRequestBuilder.emptySearchEnabled(true);

    searchRequestBuilder.withSearchContext(
                searchContext -> searchContext.setCompanyId(companyId);
                searchContext -> searchContext.setKeywords(keywords);
            );

    SearchRequest searchRequest = searchRequestBuilder.query(booleanQuery).build();

    SearchResponse _searcher.search(searchRequest);

    @Reference
    protected Queries _queries;

    @Reference
    protected Searcher _searcher;

    @Reference
    protected SearchRequestBuilderFactory _searchRequestBuilderFactory;
    ```

### Process the Search Response

What you'll do with the `SearchResponse` returned by the `_searcher.search` call
is dependent on the type of query and your specific use case. See the
documentation on searching programmatically for more information.

## Filters

Filters as a distinct API-level object in @product@ are going away. It's best to
mirror the APIs of the search engine, and neither supported search engine makes
an API level distinction between queries and filters. In recognition of this,
there's a new way to perform post-filtering, which is filtering the returned
search documents at the end of the search request (after calculating any
aggregations). Add the filter to the query using the `postFilterQuery` method in
the request builder:

```java 
SearchRequestBuilder.postFilterQuery(Query);
```

As you can see, this takes a `Query` object, not a `Filter`. Therefore,
construct the `Query` as in the previous section, and specify it as
a post-filter while building the request. All of the legacy `Filter` objects
from `portal-kernel` can now be constructed as queries, per the above
query-building documentation.

### Legacy Filters in Legacy Search Calls

Constructing the filters found in `portal-kernel`'s
`com.liferay.portal.kernel.search.filter` package is demonstrated by this `new`
term filter:

```java
TermFilter termFilter = new TermFilter("fieldName", "filterValue");
```

Filters are added in legacy search calls by going through the `Indexer`
framework's `postProcessContextBooleanFilter` method, which is invoked while the
search framework is constructing the main search query. See the 
[`UserIndexer`'s `addContextQueryParams` method](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/users-admin/users-admin-impl/src/main/java/com/liferay/users/admin/internal/search/UserIndexer.java), 
which is called in the overridden `postProcessContextBooleanFilter` to add the
filter logic.

## Discovering Indexed Fields

To find the fields to use in your Queries, navigate to *Control Panel* &rarr;
*Configuration* &rarr; *Search* in a running portal. From there, open the Field Mappings tab and
browse the mappings for the `liferay-[companyId]` index. Scroll to the
[`properties`](https://www.elastic.co/guide/en/elasticsearch/reference/current/properties.html)
section of the mapping.

A summary of the text fields that are localized can be found
[here](/docs/7-2/user/-/knowledge_base/u/building-search-queries-and-filters).
