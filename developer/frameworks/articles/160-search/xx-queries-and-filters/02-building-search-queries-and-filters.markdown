---
header-id: building-search-queries-and-filters
---

# Building Search Queries and Filters

[TOC levels=1-4]

Each filter and query has a different purpose, but the way you'll add the
information to the search request is quite similar between all queries and
filters.

## Queries

The complete dummy code snippet for building the below Query is available for
your copying and pasting convenience at the end of this section.

### Build the Search Query

1.  Use the `com.liferay.portal.search.query.Queries` interface to instantiate
    the query you'll construct. For example,

    ```java
    TermsQuery termsQuery = queries.terms("fieldName");
    ```

    To discover what fields each query must have (e.g., `String field` in
    the case of the above `com.liferay.portal.search.query.TermsQuery`), LINK TO QUERIES INTERFACE

2.  Build out the query to get the desired response. This will look different
    for each query type, but you can understand the functionality by visiting
    Elasticsearch's documentation on the query type (for example, 
    [Terms Query](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/query-dsl-terms-query.html)).

    For a Terms Query, you might just add terms to the query:

    ```java
    termsQuery.addValues("value1", "value2");
    ```

Once the query itself is in good shape, feed it to the search request.

### Build the Search Request

2.  Get an instance of `com.liferay.portal.search.searcher.SearchRequestBuilder`
    from the `com.liferay.portal.search.legacy.searcher.SearchRequestBuilderFactory` service:

    ```java
    SearchRequestBuilder searchRequestBuilder = searchRequestBuilderFactory.getSearchRequestBuilder();
    ```

3.  Get a`com.liferay.portal.search.searcher.SearchRequest` instance from the
    builder, then add the query to it and run its `build` method:

    ```java
    SearchRequest searchRequest = searchRequestBuilder.query(termsQuery).build();
    ```
### Execute the Search Request

1.  Perform a search using the `com.liferay.portal.search.searcher.Searcher` service and the `SearchRequest` to get
   a `com.liferay.portal.search.searcher.SearchResponse`:

    ```java
    SearchResponse searcher.search(searchRequest);
    ```

2.  To satisfy the dependencies of the example code here, get a reference to
    `com.liferay.portal.search.searcher.SearchRequestBuilderFactory` and
    `com.liferay.portal.search.searcher.Searcher`:

    ```java
    @Reference
    protected Searcher searcher;

    @Reference
    SearchRequestBuilderFactory searchRequestBuilderFactory;
    ```

Here's the complete code snippet:

```java
    TermsQuery termsQuery = queries.terms("fieldName");

    termsQuery.addValues("value1", "value2");

    SearchRequestBuilder searchRequestBuilder = searchRequestBuilderFactory.getSearchRequestBuilder();

    SearchRequest searchRequest = searchRequestBuilder.query(termsQuery).build();

    SearchResponse searcher.search(searchRequest);

    @Reference
    protected Searcher searcher;

    @Reference
    SearchRequestBuilderFactory searchRequestBuilderFactory;
```

### Process the Search Response

What you'll do with the `SearchResponse` returned by the `searcher.search` call
is dependent on the type of query and your specific use case. See [ANOTHER
ARTICLE]() for more information.

## Filters

Filters as a distinct API-level object in @product@ are going away. It's best to
mirror the APIs of the search engine, and neither supported search engine makes
the API level distinction between queries and filters. In recognition of this,
there's a new way to perform post-filtering, which is filtering the returned
search documents at the end of the search request (after calculating any
aggregations). Simply add the filter to the query using the `postFilterQuery`
method in the request builder:

```java 
SearchRequestBuilder.postFilterQuery(Query)
```

As you can see, this takes a `Query` object, not a `Filter`. Therefore, simply
construct the `Query` as in the previous section, and specify it as a
post-filter while building the request. All of the legacy `Filter` objects form
`portal-kernel` are now able to be constructed as queries, as per the above
query-building documentation.

### Legacy Filters in Legacy Search Calls

```java
TermFilter termFilter=new TermFilter("fieldName", "filterValue")
```

indexer.search calls

Constructing the 

, just
like with, unscored results that are retunred form the
search enigine fading away.
Search engines support the construction of a query and The best way to
construct filters and add them to the
search request is using the  

Legacy `Filter`s are found in `com.liferay.portal.kernel.search` and are
instantiated and constructed using this format:

```java
TermFilter termFilter=new TermFilter("fieldName", "filterValue")
```

```java
public GeoDistanceFilter(
    String field, GeoLocationPoint pinGeoLocationPoint,
    GeoDistance geoDistance) {

    _field = field;
    _pinGeoLocationPoint = pinGeoLocationPoint;
    _geoDistance = geoDistance;
}
```

`_filterBuilders.dateRangeFilterBuilder()`
`new TermFilter("fieldName", "filterValue"))`
`SearchRequestBuilder.postFilterQuery(Query)`

`DateRangeFilterBuilderImpl` is `internal`; only our unit tests are able to `new DateRangeFilterBuilderImpl()`. devs would `_filterBuilders.dateRangeFilterBuilder()` instead (edited)

`TermFilter` is `portal-kernel`; uses legacy instantiation, `new TermFilter("fieldName", "filterValue"))`. I know the lack of consistency is bad; but at the end of the day, we’re abandoning the whole “Filters” thing anyway (more about this in a minute) so there’s little value in adding the `portal-kernel` legacy stuff to `FilterBuilders`.
We are abandoning the legacy `Filter`. From now on, the recommended way to do “post filter” queries (fully supported by search engines) is `SearchRequestBuilder.postFilterQuery(Query)` (when using `Searcher`) or `SearchSearchRequest.setPostFilterQuery(Query)` (when using Low Level Search API). You will notice we already have `Query` equivalents for all of the legacy `Filter` varieties, for instance: `Queries.dateRangeTerm`, `Queries.term`, `Queries.termsSet` (edited)




André de Oliveira   [5 days ago]
*[2.]*

`SearchRequestBuilder.query(Query)` (when using `Searcher`) and `searchSearchRequest.setQuery(Query)` (when using Low Level Search API) are equivalent. I know the lack of consistency is bad; we spent more time polishing API for the former than the latter, and for sure there’s still much room for improvement. Worth mentioning: in Low Level Search API, that’s pretty much the only entry point to define what you want to search; in `Searcher`, there’s multiple entry points and it will choose how to search in a “smart” way. If you use that specific method, under the hood it will just delegate to plain Low Level Search (bypassing the Indexer framework.) (edited)

André de Oliveira   [5 days ago]
If you invoke `Searcher` with a legacy `SearchContext`, via `com.liferay.portal.search.legacy.searcher.SearchRequestBuilderFactory.getSearchRequestBuilder(SearchContext)` it will use the Indexer framework just like the Search Page or Classic Search Portlet.
