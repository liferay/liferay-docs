---
header-id: creating-aggregations-in-low-level-search-calls
---

# Creating Aggregations

[TOC levels=1-4]

Each aggregation has a different purpose and should be processed differently
once returned from the search engine, but you add the aggregation
information to the search request in a similar way between all aggregations.

## Instantiate and Construct the Aggregation

1.  Use the `com.liferay.portal.search.aggregation.Aggregations` to instantiate
    the aggregation you'll construct. For example,

    ```java
    PercentilesAggregation percentilesAggregation =
        aggregations.percentiles("percentiles", Field.PRIORITY);
    ```

    To discover what fields each aggregation must have (e.g., `Sting name,
    String field` in the case of the above `PercentilesAggregation`), see the
    [`Aggregations` interface](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/portal-search/portal-search-api/src/main/java/com/liferay/portal/search/aggregation/Aggregations.java).

2.  Build out the aggregation to get the desired response. This looks
    different for each aggregation type, but Elasticsearch's documentation on the aggregation
    type explains it well (such as 
    [Percentiles Aggregations](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/search-aggregations-metrics-percentile-aggregation.html))
    combined with the setters in Liferay's corresponding interface.

    For example, use the `setPercentilesMethod(PercentilesMethod.HDR)` method to
    use the 
    [High Dynamic Range Histogram](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/search-aggregations-metrics-percentile-aggregation.html#_hdr_histogram)
    for calculating the percentiles.

    ```java
    percentilesAggregation.setPercentilesMethod(PercentilesMethod.HDR);
    ```

Once the aggregation itself is in good shape, feed it to the search query.

## Build the Search Query

1.  Get an instance of `com.liferay.portal.search.searcher.SearchRequestBuilder`
    from the `SearchRequestBuilderFactory` service:

    ```java
    SearchRequestBuilder searchRequestBuilder = 
        searchRequestBuilderFactory.builder();
    ```

2.  Get a `com.liferay.portal.search.searcher.SearchRequest` instance from the
    builder, then add the aggregation to it and run its `build` method:

    ```java
    SearchRequest searchRequest =
        searchRequestBuilder.addAggregation(percentilesAggregation).build();
    ```

## Execute the Search Query

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

## Process the response

What you'll do with the `SearchResponse` returned by the `searcher.search` call
depends on the type of aggregation and your specific use case. A separate
article will be written to demonstrate how to process the response.
