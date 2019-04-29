# Search Queries and Filters

As the ancient wisdom holds, 

> A search without a query is like an answer without a question.

To get sensible results from the search engine, you must provide a sensible
query. 

## Queries and Filters in Liferay's Search API

Elasticsearch and Solr do not make API level distinctions between queries and
filters. In the past, Liferay's API explicitly provided two sets of APIs, one
for queries and one for filters. Both APIs lived in `portal-kernel` (the 7.1
source code for filters is 
[here](https://github.com/liferay/liferay-portal/tree/7.1.x/portal-kernel/src/com/liferay/portal/kernel/search/filter).

In @product-ver@, there's a new way of querying and filtering via Liferay's
Search API, and the APIs for it live in the `portal-search-api` module. Instead
of calling specific filter APIs, you'll now construct a query and add it to the
search request, specifying it as a filter using the
`SearchRequestBuilder.postFilterQuery(Query)` method.(see the
7.2 Query APIs
[here](https://github.com/liferay/liferay-portal/tree/7.2.x/modules/apps/portal-search/portal-search-api/src/main/java/com/liferay/portal/search/query).

| **Note**: Support for the legacy
| `com.liferay.portal.kernel.search.Query.getPreBooleanFilter()` is only present
| in the new search request builder and assembler implementation to allow for
| backwards compatibility with the `Indexer` framework's handling of queries.
| The older approach encourages some practices that are not ideal:
| 
| - Wrapping a `BooleanQuery` with another `BooleanQuery`. 
| 
| - Some queries shouldn't have filters according to Elasticsearch's API.

Despite the more unified filtering and querying code, you should understand the
functional difference between filtering and querying:

*Filters* ask a yes or no question for every document. A filter might ask _is
the status field equal to staging or live?_

*Queries* ask the same yes or no question AND how well a document matches the
specified criteria. This is the concept of 
[relevance scoring](https://www.elastic.co/guide/en/elasticsearch/guide/current/scoring-theory.html). 
A query might ask _does the document's content field field contain the words
"Liferay", "Content", or "Management", and how relevant is the content of the
document to the searched keywords?_

| **Hint:** Filtering is faster than querying, since the documents matching a
| filter can be easily cached. Queries not only match documents but also calculate
| scores. We recommend using filtering and querying together: filters to reduce
| the number of matched documents, queries for the final examination.

## Supported Query Types

Liferay's Search API supports the following types of queries:

### Full Text Queries

Full text queries are high-level queries usually used for querying full text
fields like the `content` field of a Blogs Entry. How terms are matched depends
on the query type.

*Supported Full Text Queries*

    ```java
    CommonTermsQuery
    MatchPhraseQuery
    MatchPhrasePrefixQuery
    MatchQuery
    MultiMatchQuery
    SimpleStringQuery
    StringQuery
    ```

Here are some common full text queries:

* Match Query: A full text query, scored by relevance.
* Multi Match Query: Execute a `MatchQuery` over several fields.
* String Query: Use Lucene query syntax.

<!--
TODO: Brief description for each.
-->

### Term Queries

Term queries look for exact matching on keyword fields and indexed terms.

```java
    ExistsQuery
    FuzzyQuery
    IdsQuery
    PrefixQuery
    RangeQuery    
    RegexpQuery
    TermQuery
    TermsQuery
    TermRangeQuery
    TermsSetQuery
    WildcardQuery
```

Here are some common term queries:

* Wildcard Query: Wildcard (`*` and `?`) matching on keyword fields and indexed terms
* Fuzzy Query: Scrambles characters in input before matching

<!--
TODO: Cover the rest of Term Queries
-->

### Compound Queries

Compound queries are often used to wrap other queries. They're commonly used to
switch from query to filter context.

<!--
TODO: Intro.
-->

```java
    BooleanQuery
    BoostingQuery
    ConstantScoreQuery
    DisMaxQuery
    FunctionScoreQuery
```

Here are some common compound queries:

* Boolean Query: Allows a combo of several query types. Individual queries are
  as clauses with `SHOULD` | `MUST` | `MUST_NOT` | `FILTER`
* Constant Score Query: Wraps another query, switches it to filter mode, and gives all
    returned documents a constant relevance score. <!--How does this give a
    filter context to another query? Doesn't matter whether postFilterQuery is
    called?-->
<!-- * DisMaxQuery-->

<!--
TODO: Cover the rest of Compound Queries
-->

### Joining Queries

The concept of a join doesn't work well in a distributed index. Joining queries
allow similar behavior in the search index, such as using the 
[`nested` datatype](https://www.elastic.co/guide/en/elasticsearch/reference/current/nested.html)
to index an array of objects that can be queried independently, using the
`NestedQuery`.

```java
    NestedQuery
```
* Nested Query: Query nested objects as if they each had a separate document in
    the index.

## Geo Queries

In Elasticsearch, you can index latitude/longitude pairs, and geographic shapes.
Geo queries let you query for these points and shapes.

```java
    GeoBoundingBoxQuery
    GeoDistanceQuery
    GeoDistanceRangeQuery
    GeoPolygonQuery
    GeoShapeQuery
```

A common Geo Query is the `GeoDistanceQuery`, used to find documents within a
certain distance of a geographic point (latitude/longitude). 

<!--
TODO: Cover Geo Queries
-->

### Specialized Queries

These queries don't fit into another group:

```java
    MoreLikeThisQuery
    PercolateQuery
    ScriptQuery
```
* More Like This: Use a document to query for similar documents.
* Percolate: Match individual documents against indexed queries (for alerting to
    new documents of interest, or automatically categorizing documents).
* Script: Filter based on a script. 

### Other Queries

`MatchAllQuery` Matches all documents in the index.

___

The proper search query is entirely context- and search engine-specific, so you
should read the Query documentation straight from
[Elasticsearch](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/query-dsl.html) 
or 
[Solr](https://lucene.apache.org/solr/guide/7_1/query-syntax-and-parsing.html)
to determine which queries are available and what they do.

All the recommended and supported queries and filters are found in the
`portal-search-api` module's `com.liferay.portal.search.query` and
`com.liferay.portal.search.filter` packages.

Legacy queries and filters, which are still supported but moving towards
deprecation, are found in the `com.liferay.portal.kernel.search.*` packages
provided by `portal-kernel`.

## Using Queries

The generalized approach for querying and filtering search documents in your own
search code is like this:

1.  Instantiate and construct the query object
2.  Add the query to the search request--the method you use will determine
    whether the context is filtering or querying.
3.  Process the search response

These steps are covered in more detail (with examples) 
[in the next article](LINK to query/filter code article).

## Search Queries in Liferay's Code

The new APIs for creating queries were introduced in @product-ver@, but already
there are a couple usages in Liferay's own code: for example the new 
[Change Tracking Service](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/change-tracking/change-tracking-service/src/main/java/com/liferay/change/tracking/service/impl/CTEntryLocalServiceImpl.java) 
constructs a complex search request containing a `TermsQuery` using the new
`Queries` API:

```java
    TermsQuery termsQuery = _queries.terms("field");
```

This code executes the search request with the terms query constructed above
in a query context.

## External References
* <https://www.elastic.co/guide/en/elasticsearch/reference/6.5/query-dsl.html>
* <https://lucene.apache.org/solr/guide/7_1/query-syntax-and-parsing.html>

## Search Engine Connector Support

* Elasticsearch 6: Yes
* Solr 7: No* (Only the "legacy" query types from `com.liferay.portal.kernel.search.*` are supported as of @product@ Beta 2.)

## New/Related APIs

Package | Provided by Artifact | Notes |
-----------|:--------------------:|:--------:|
`com.liferay.portal.search.query.*` | com.liferay.portal.search.api | Most of the provided [query types](https://github.com/liferay/liferay-portal/tree/7.2.x/modules/apps/portal-search/portal-search-api/src/main/java/com/liferay/portal/search/query) are new as of @product_ver@
`com.liferay.portal.search.filter.*` | com.liferay.portal.search.api | Some of the provided [filter types](https://github.com/liferay/liferay-portal/tree/7.2.x/modules/apps/portal-search/portal-search-api/src/main/java/com/liferay/portal/search/filter) are new as of @product_ver@

<!--What do these do? We're recommending setting queries into the
SearchRequestBuilder.postFilterQuery(matchAllQuery) method, so why talk about
the filters? Commenting out for now, add back if necessary-->

<!--
## Supported Filter Types

Although the recommended approach is to filter in search requests with
`SearchRequestBuilder.postFilterQuery(Query)`, the  Search API supports the
following types of filters: from `portal-kernel`.

### Term Filters

These filters are provided by the `com.liferay.portal.kernel.search.filter`
package.

* TermFilter
* TermsFilter
* PrefixFilter
* ExistsFilter
* MissingFilter
* RangeTermFilter


TODO: Cover Term Filters


### Compound Filters

This filter is provided by the `com.liferay.portal.kernel.search.filter`
package.

* BooleanFilter:

TODO: Cover BooleanFilter

### Geo Filters

Geolocation filters filter documents based on the latitude and longitude fields.
These filters are provided by the `com.liferay.portal.kernel.search.filter`
package.

* GeoDistanceFilter
* GeoDistanceRangeFilter
* GeoBoundingBoxFilter
* GeoPolygonFilter

TODO: Cover Geo Filters

### Other Filters

These filters don't fit into the other categories.

* QueryFilter: Turns any query into a filter. E.g., can a BooleanQuery into a BooleanFilter
* MatchAllFilter: Matches all documents
-->
