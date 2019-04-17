# Search Queries and Filters

As the ancient wisdom holds, 

> A search without a query is like an answer without a question.

To get sensible results from the search engine, you must provide a sensible
query. 

## Queries and Filters, at the Search Engine Level

Elasticsearch and Solr do not make API level distinctions between queries and
filters. Since they're each used in different circumstances, Liferay's API
explicitly provides two sets of APIs, one for queries and one for filters. Both
APIs are in the `portal-search-api` module.

*Filters* ask a yes or no question for every document. A filter might ask _is
the status field equal to staging or live?_

*Queries* ask the same yes or no question AND how well a document matches the
specified criteria. This is the concept of 
[relevance scoring](https://www.elastic.co/guide/en/elasticsearch/guide/current/scoring-theory.html). 
A query might ask _does the document's content field field contain the words
"Liferay", "Content", or "Management", and how relevant is the content of the
document to the searched keywords?_

Filters are much faster than queries, since the documents matching a filter can
be easily cached. Queries not only match documents but also calculate scores.
Liferay uses filters and queries together so that filters can reduce the number
of matched documents before the query examines them for scoring.

## Supported Query Types

Liferay's Search API supports the following types of queries:

### Full Text Queries

* Match Query: Full text matching, scored by relevance.
* Multi Match Query: `MatchQuery` over several fields.
* String Query: Uses Lucene query syntax

    ```java
    CommonTermsQuery
    MatchPhraseQuery
    MatchPhrasePrefixQuery
    MatchQuery
    MultiMatchQuery
    SimpleStringQuery
    StringQuery
    ```
<!--
TODO: Brief desription for each.
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

* Wildcard Query: Wildcard (`*` and `?`) matching on keyword fields and indexed terms
* Fuzzy Query: Scrambles characters in input before matching

<!--
TODO: Cover the rest of Term Queries
-->

### Compound Queries

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

* Boolean Query: Allows a combo of several query types. Individual queries are
  as clauses with `SHOULD` | `MUST` | `MUST_NOT` | `FILTER`
* DisMaxQuery

<!--
TODO: Cover the rest of Compound Queries
-->

### Joining Queries

<!--
TODO: Intro.
-->

```java
    NestedQuery
```
<!--
TODO: Cover Joining Queries
-->

## Geo Queries

<!--
TODO: Intro.
-->

```java
    GeoBoundingBoxQuery
    GeoDistanceQuery
    GeoDistanceRangeQuery
    GeoPolygonQuery
    GeoShapeQuery
```

<!--
TODO: Cover Geo Queries
-->

### Specialized Queries

<!--
TODO: Intro.
-->

```java
    MoreLikeThisQuery
    PercolateQuery
    ScriptQuery
```
<!--
TODO: Cover Specialized Queries
-->

### Other Queries

* `MatchAllQuery`: Matches all documents

## Supported Filter Types

Liferay's Search API supports the following types of filters:

### Term Filters

Note: These filters are provided by the `com.liferay.portal.kernel.search.filter` package.

* TermFilter
* TermsFilter
* PrefixFilter
* ExistsFilter
* MissingFilter
* RangeTermFilter

<!--
TODO: Cover Term Filters
-->

### Compound Filters

Note: This filter is provided by the `com.liferay.portal.kernel.search.filter` package.

* BooleanFilter:

<!--
TODO: Cover BooleanFilter
-->

### Geo Filters

Note: These filters are provided by the `com.liferay.portal.kernel.search.filter` package.

Geolocation filters help filter documents based on the latitude
and longitude fields.

* GeoDistanceFilter
* GeoDistanceRangeFilter
* GeoBoundingBoxFilter
* GeoPolygonFilter

<!--
TODO: Cover Geo Filters
-->

### Other Filters

<!--
TODO: Intro Other Filters
-->

* QueryFilter: Turns any query into a filter. E.g., can a BooleanQuery into a BooleanFilter
* MatchAllFilter: Matches all documents

The proper search query is entirely context- and search engine-specific, so you
should read the Query documentation straight from
[Elasticsearch](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/query-dsl.html) 
or 
[Solr](https://lucene.apache.org/solr/guide/7_1/query-syntax-and-parsing.html)
to determine which queries are available and what they do.

All the supported queries and filters found in the `portal-search-api` module's
`com.liferay.portal.search.query` and `com.liferay.portal.search.filter`
packages.

Legacy queries and filters can be found in the `com.liferay.portal.kernel.search.*` packages provided by `portal-kernel`.

## Using Queries and Filters

The generalized approach for querying and filtering search documents in your own
search code is like this:

1.  Instantiate and construct the query or filter object
2.  Add the query or filter to the search request
3.  Process the search response

These steps are covered in more detail (with examples) 
[here](LINK to query/filter code article).

## Search Queries in Liferay's Code

The new APIs for creating queries were introduced in @product-ver@, but already
there are a couple usages in Liferay's own code: for example the new 
[Change Tracking Service](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/change-tracking-service/src/main/java/com/liferay/change/tracking/service/impl/CTEntryLocalServiceImpl.java) 
constructs a complex search request containing a `TermsQuery` using the new
`Queries` API:

```java
    TermsQuery termsQuery = _queries.terms("field");
```

## External References
* <https://www.elastic.co/guide/en/elasticsearch/reference/6.5/query-dsl.html>
* <https://lucene.apache.org/solr/guide/7_1/query-syntax-and-parsing.html>

## Search Engine Connector Support
* Elasticsearch 6: Yes
* Solr 7: No* (Only the "legacy" query types from `com.liferay.portal.kernel.search.*` are supported as of @product@ Beta 2.)

## New/Related APIs

API (FQCN) | Provided by Artifact | Notes |
-----------|:--------------------:|:--------:|
`com.liferay.portal.search.query.*` | com.liferay.portal.search.api | The whole ["query" package](https://github.com/liferay/liferay-portal/tree/7.2.x/modules/apps/portal-search/portal-search-api/src/main/java/com/liferay/portal/search/query) is new as of @product_ver@
`com.liferay.portal.search.filter.*` | com.liferay.portal.search.api | The whole ["filter" package](https://github.com/liferay/liferay-portal/tree/7.2.x/modules/apps/portal-search/portal-search-api/src/main/java/com/liferay/portal/search/filter) is new as of @product_ver@
