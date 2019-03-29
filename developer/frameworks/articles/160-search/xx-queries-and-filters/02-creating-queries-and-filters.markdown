---
header-id: using-queries-and-filters-in-low-level-search-calls
---

# Using Queries and Filters in Low Level Search Calls

[TOC levels=1-4]

Each filter and query has a different purpose, but the way you'll add the
information to the search request is quite similar between all queries and
filters.

## Queries

The complete dummy code snippet for building the below Query is available for
your copying and pasting convenience at the end of this section.

### Instantiate and Construct the Query

1.  Use the `com.liferay.portal.search.query.Queries` interface to instantiate
    the query you'll construct. For example,

    ```java
    TermsQuery termsQuery = queries.terms("fieldName");
    ```

    To discover what fields each query must have (e.g., `String field` in
    the case of the above `TermsQuery`), DOCUMENT OR LINK TO QUERIES INTERFACE?

2.  Build out the query to get the desired response. This will look different
    for each query type, but you can understand the functionality by visiting
    Elasticsearch's documentation on the query type (for example, 
    [Terms Query](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/query-dsl-terms-query.html)).

    For a Terms Query, you might just add terms to the query:

    ```java
    termsQuery.addValues("value1", "value2");
    ```

Once the query itself is in good shape, feed it to the search query.

### Build the Search Query

2.  Get an instance of `com.liferay.portal.search.searcher.SearchRequestBuilder`
    from the `SearchRequestBuilderFactory` service:

    ```java
    SearchRequestBuilder searchRequestBuilder = 
        searchRequestBuilderFactory.getSearchRequestBuilder();
    ```

3.  Get a`com.liferay.portal.search.searcher.SearchRequest` instance from the
    builder, then add the query to it and run its `build` method:

    ```java
    SearchRequest searchRequest =
    searchRequestBuilder.query(termsQuery).build();
    ```
### Execute the Search Query

1.  Perform a search using the `Searcher` service and the `SearchRequest` to get
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

SearchRequestBuilder searchRequestBuilder = 
    searchRequestBuilderFactory.getSearchRequestBuilder();

SearchRequest searchRequest =

searchRequestBuilder.query(termsQuery).build();

SearchResponse searcher.search(searchRequest);

@Reference
protected Searcher searcher;

@Reference
SearchRequestBuilderFactory searchRequestBuilderFactory;
```

### Process the response

What you'll do with the `SearchResponse` returned by the `searcher.search` call
is dependent on the type of query and your specific use case. See [ANOTHER
ARTICLE]() for more information.

<!--
## Filters

### Instantiate and Construct the Filter

1.  ?????????? to instantiate the filter you'll
construct. For example,

    ```java
    PercentilesAggregation percentilesAggregation =
        aggregations.percentiles("percentiles", Field.PRIORITY);
    ```

    To discover what fields each filter must have (e.g., `String name, String
    field` in the case of the above `PercentilesAggregation`), DOCUMENT OR LINK TO
    filterS INTERFACE?

2.  Build out the filter to get the desired response. This will look different
    for each filter type, but you can understand the functionality by visiting
    Elasticsearch's documentation on the filter type (for example, 
    [Percentiles filters](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/search-aggregations-metrics-percentile-aggregation.html))
    and looking at the setters in Liferay's corresponding interface.

    For example, use `setPercentilesMethod(PercentilesMethod.HDR)` to method to
    use the 
    [High Dynamic Range Histogram](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/search-aggregations-metrics-percentile-aggregation.html#_hdr_histogram)
    for calculating the percentiles.

    ```java
    percentilesAggregation.setPercentilesMethod(PercentilesMethod.HDR);
    ```
Once the filter itself is in good shape, feed it to the search query.

### Build the Search Query

1.  Get an instance of `com.liferay.portal.search.searcher.SearchRequestBuilder`
    from the `SearchRequestBuilderFactory` service:

    ```java
    SearchRequestBuilder searchRequestBuilder = 
        searchRequestBuilderFactory.getSearchRequestBuilder();
    ```

2.  Get a`com.liferay.portal.search.searcher.SearchRequest` instance from the
    builder, then add the filter to it and run its `build` method:

    ```java
    SearchRequest searchRequest =
    searchRequestBuilder.addAggregation(percentilesAggregation).build();
    ```
### Execute the Search Query

1.  Perform a search using the `Searcher` service and the `SearchRequest` to get
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

### Process the response

What you'll do with the `SearchResponse` returned by the `searcher.search` call
is dependent on the type of filter and your specific use case. See [ANOTHER
ARTICLE]() for more information.
-->
