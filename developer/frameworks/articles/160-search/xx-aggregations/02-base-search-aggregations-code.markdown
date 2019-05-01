---
header-id: creating-aggregations-in-low-level-search-calls
---

# Creating Aggregations

[TOC levels=1-4]

Each aggregation has a different purpose and should be processed differently
once returned from the search engine, but the way you'll add the aggregation
information to the search request is quite similar between all aggregations.

## Instantiate and Construct the Aggregation

1.  Use the `com.liferay.portal.search.aggregation.Aggregations` to instantiate the aggregation you'll
construct. For example,

    ```java
    PercentilesAggregation percentilesAggregation =
        aggregations.percentiles("percentiles", Field.PRIORITY);
    ```

    To discover what fields each aggregation must have (e.g., `Sting name, String
    field` in the case of the above `PercentilesAggregation`),LINK TO
    AGGREGATIONS INTERFACE

2.  Build out the aggregation to get the desired response. This will look
    different for each aggregation type, but you can understand the
    functionality by visiting Elasticsearch's documentation on the aggregation
    type (for example, 
    [Percentiles Aggregations](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/search-aggregations-metrics-percentile-aggregation.html))
    and looking at the setters in Liferay's corresponding interface.

    For example, use `setPercentilesMethod(PercentilesMethod.HDR)` to method to
    use the 
    [High Dynamic Range Histogram](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/search-aggregations-metrics-percentile-aggregation.html#_hdr_histogram)
    for calculating the percentiles.

    ```java
    percentilesAggregation.setPercentilesMethod(PercentilesMethod.HDR);
    ```
Once the aggregation itself is in good shape, feed it to the search query.

## Build the Search Query

2.  Get an instance of `com.liferay.portal.search.searcher.SearchRequestBuilder`
    from the `SearchRequestBuilderFactory` service:

    ```java
    SearchRequestBuilder searchRequestBuilder = 
        searchRequestBuilderFactory.getSearchRequestBuilder();
    ```

3.  Get a`com.liferay.portal.search.searcher.SearchRequest` instance from the
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
is dependent on the type of aggregation and your specific use case. A separate
article will be written to demonstrate how to process the response.
