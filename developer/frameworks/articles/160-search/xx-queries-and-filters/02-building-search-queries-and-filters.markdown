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

<!--
## Filters: Write usage of filters
--->
