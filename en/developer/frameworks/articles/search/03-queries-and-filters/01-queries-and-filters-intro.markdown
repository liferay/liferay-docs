---
header-id: search-queries-and-filters
---

# Search Queries and Filters

[TOC levels=1-4]

To get sensible results from the search engine, you must provide a sensible
query. 

## Queries and Filters in Liferay's Search API

Elasticsearch and Solr do not make API level distinctions between queries and
filters. In the past, Liferay's API explicitly provided two sets of APIs, one
for queries and one for filters. Both APIs lived in `portal-kernel` (the 7.1
source code for filters is 
[here](https://github.com/liferay/liferay-portal/tree/7.1.x/portal-kernel/src/com/liferay/portal/kernel/search/filter)).

In @product-ver@, there's a new way of querying and filtering via Liferay's
Search API, and the APIs for it live in the `portal-search-api` module. Instead
of calling specific filter APIs, you'll now construct a query and add it to the
search request, specifying it as a filter using the
`SearchRequestBuilder.postFilterQuery(Query)` method. See the
[7.2 Query APIs](https://github.com/liferay/liferay-portal/tree/7.2.x/modules/apps/portal-search/portal-search-api/src/main/java/com/liferay/portal/search/query).

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
A query might ask _Does the document's content field field contain the words
"Liferay", "Content", or "Management", and how relevant is the content of the
document to the searched keywords?_

| **Hint:** Filtering is faster than querying, since the documents matching a
| filter can be cached. Queries not only match documents but also calculate
| scores. We recommend using filtering and querying together: filters to reduce
| the number of matched documents, queries for the final examination.

## Supported Query Types

Liferay's Search API supports the following types of queries:

### Full Text Queries

[Full text
queries](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/full-text-queries.html)
are high-level queries usually used for querying full text fields like the
`content` field of a Blogs Entry. How terms are matched depends on the query
type.

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

### Term Queries

[Term
queries](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/term-level-queries.html)
look for exact matching on keyword fields and indexed terms.

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

### Compound Queries

[Compound
queries](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/compound-queries.html)
are often used to wrap other queries. They're commonly used to switch from
query to filter context.

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

### Joining Queries

The concept of a join doesn't work well in a distributed index. [Joining
queries](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/joining-queries.html)
allow similar behavior in the search index, such as using the [`nested`
datatype](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/nested.html)
to index an array of objects that can be queried independently, using the
`NestedQuery`.

```java
NestedQuery
```

Nested Query: Query nested objects as if they each had a separate document in
the index.

### Geo Queries

In Elasticsearch, you can index latitude/longitude pairs and geographic shapes.
[Geo
queries](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/geo-queries.html)
let you query for these points and shapes.

```java
GeoBoundingBoxQuery
GeoDistanceQuery
GeoDistanceRangeQuery
GeoPolygonQuery
GeoShapeQuery
```

A common Geo Query is the `GeoDistanceQuery`, used to find documents within a
certain distance of a geographic point (latitude/longitude). 

### Specialized Queries

These
[queries](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/specialized-queries.html)
don't fit into another group:

```java
MoreLikeThisQuery
PercolateQuery
ScriptQuery
```
* **More Like This:** Use a document to query for similar documents.
* **Percolate:** Match individual documents against indexed queries (for alerting to
  new documents of interest, or automatically categorizing documents).
* **Script:** Filter based on a script. 

### Other Queries

`MatchAllQuery` Matches all documents in the index.

The proper search query is entirely context- and search engine-specific, so you
should read the Query documentation straight from
[Elasticsearch](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/query-dsl.html) 
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

Here's the generalized approach for querying and filtering search documents in
your own search code:

1.  Instantiate and construct the query object.
2.  Add the query to the search request---the method you use determines whether
    the context is filtering or querying (or both in the same request).
3.  Execute the search request.
4.  Process the search response.

These steps are covered in more detail (with examples) 
[in the next article](/docs/7-2/frameworks/-/knowledge_base/f/building-search-queries-and-filters).

## Search Queries in Liferay's Code

The APIs for creating queries are best exemplified in Liferay's own test cases.
For example, [BaseTermsQueryTestCase](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/portal-search/portal-search-test-util/src/main/java/com/liferay/portal/search/test/util/query/BaseTermsQueryTestCase.java) constructs a search request containing
a `TermsQuery` using the `Queries` API:

```java
TermsQuery termsQuery = queries.terms(Field.USER_NAME);
```

This code executes the search request with the terms query constructed above
in a query context.

Other query test cases are also available to reference in the `portal-search`
module's [source code](https://github.com/liferay/liferay-portal/tree/7.2.x/modules/apps/portal-search/portal-search-test-util/src/main/java/com/liferay/portal/search/test/util/query).

## External References

* <https://www.elastic.co/guide/en/elasticsearch/reference/7.x/query-dsl.html>
* <https://lucene.apache.org/solr/guide/7_1/query-syntax-and-parsing.html>

## Search Engine Connector Support

* Elasticsearch 6: Yes
* Solr 7: No* (Only the "legacy" query types from
  `com.liferay.portal.kernel.search.*` are supported as of @product@ Beta 2.)

## New/Related APIs

Package | Provided by Artifact | Notes |
-----------|:--------------------:|:--------:|
`com.liferay.portal.search.query.*` | com.liferay.portal.search.api | Most of the provided [query types](https://github.com/liferay/liferay-portal/tree/7.2.x/modules/apps/portal-search/portal-search-api/src/main/java/com/liferay/portal/search/query) are new as of 7.2
`com.liferay.portal.search.filter.*` | com.liferay.portal.search.api | Some of the provided [filter types](https://github.com/liferay/liferay-portal/tree/7.2.x/modules/apps/portal-search/portal-search-api/src/main/java/com/liferay/portal/search/filter) are new as of 7.2

