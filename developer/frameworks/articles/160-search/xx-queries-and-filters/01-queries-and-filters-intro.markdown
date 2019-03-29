---
header-id: queries-and-filters
---

# Queries and Filters

[TOC levels=1-4]

As the ancient wisdom holds, 

> A search without a query is like an answer without a question.

To get sensible results from the search engine, you must provide a sensible
query. 

## The Liferay Search Application's Search Query

To see how the @product@ Search application constructs its main query string,
WHERE IS THIS DONE IN OUR CODE?

`com.liferay.portal.search.elasticsearch6.internal.query.ElasticsearchQueryTranslator`?

## Queries and Filters, at the Search Engine Level

Elasticsearch and Solr do not make API level distinctions between queries and
filters. Since they're each used in different circumstances, Liferay's API
explicitly provides two sets of APIs, one for queries and one for filters. Both
APIs are in the `portal-search-api` module.

*Filters* ask a yes or no question for every document. 

*Queries* ask the same yes or no question AND how well a document
matches the specified criteria. This is the concept of 
[relevance scoring](https://www.elastic.co/guide/en/elasticsearch/guide/current/scoring-theory.html).

For instance, a filter might ask _is the status field equal to staging or live?_
A query might ask _does the document's content field field contains the words
"Liferay", "Content", or "Management", and how relevant is the content of the
document to the searched keywords?_

Filters are much faster than queries, since the documents matching a filter can
be easily cached. Queries not only match documents but also calculate scores.
Liferay uses filters and queries together so that filters can reduce the number
of matched documents before the query examines them for scoring.

## Supported Query Types

Liferay's Search API supports the following types of queries:

Full text queries:

- MatchQuery: Full text matching, scored by relevance.
- MultiMatchQuery: MatchQuery over several fields.
- StringQuery: Uses Lucene query syntax

    Match Query
    Match Phrase Query
    Match Phrase Prefix Query
    Multi Match Query
    Common Terms Query
    Query String Query
    Simple Query String Query

- Term queries:

    Term Query
    Terms Query
    Terms Set Query
    Range Query
    Exists Query
    Prefix Query
    Wildcard Query
    Regexp Query
    Fuzzy Query
    Type Query
    Ids Query

- TermQuery: Exact matching on keyword fields and indexed terms
- TermRangeQuery: TermQuery with a range
- WildcardQuery: Wildcard (* and ?) matching on keyword fields and indexed terms
- FuzzyQuery: Scrambles characters in input before matching

Compound queries:
    Constant Score Query
    Bool Query
    Dis Max Query
    Function Score Query
    Boosting Query
- BooleanQuery: Allows a combo of several query types. Individual queries are
  as clauses with `SHOULD` | `MUST` | `MUST_NOT` | `FILTER`
- DisMaxQuery

Joining Queries:

    Nested Query
    Has Child Query
    Has Parent Query
    Parent Id Query

Geo Queries:

    GeoShape Query
    Geo Bounding Box Query
    Geo Distance Query
    Geo Polygon Query

Specialized Queries:

More Like This Query
Script Query
Percolate Query

Other queries:

- MoreLikeThisQuery
- MatchAllQuery: Matches all documents

## Supported Filter Types

Liferay's Search API supports the following types of filters:

Term filters:

- TermFilter
- TermsFilter
- PrefixFilter
- ExistsFilter
- MissingFilter
- RangeTermFilter

Compound filters:

- BooleanFilter

Geo filters: (Geolocation filters help filter documents based on the latitude
and longitude fields)

- GeoDistanceFilter
- GeoDistanceRangeFilter
- GeoBoundingBoxFilter
- GeoPolygonFilter

Other filters:

- QueryFilter: Turns any query into a filter. E.g., can a BooleanQuery into a BooleanFilter
- MatchAllFilter: Matches all documents

The proper search query is entirely context- and search engine-specific, so you
should read the Query documentation straight from
[Elasticsearch](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/query-dsl.html) 
or 
[Solr](https://lucene.apache.org/solr/guide/7_1/query-syntax-and-parsing.html)
To determine which queries are available and what they do.

All the supported queries and filters found in the `portal-search-api` module's
`com.liferay.portal.search.query` and `com.liferay.portal.search.filter`
packages.

ANYTHING LIKE THIS FOR QUERIES?
In addition to these aggregations, other aggregation-like features are present
in the @product@ search API:

## Using Queries and Filters

The generalized approach for querying and filtering search documents in your own
search code is like this:

1.  Instantiate and construct the query or filter object
2.  Add the query or filter to the search request
3.  Process the search response

These steps are covered in more detail (with examples) 
[here](LINK to query/filter code article).

## External References
* <https://www.elastic.co/guide/en/elasticsearch/reference/6.5/query-dsl.html>
* <https://lucene.apache.org/solr/guide/7_1/query-syntax-and-parsing.html>

## Search Engine Connector Support
* Elasticsearch 6: Yes
* Solr 7: Yes

## New/Related APIs
Listed those which are relevant for developers.

API (FQCN) | Provided by Artifact | Notes |
-----------|:--------------------:|:--------:|
`com.liferay.portal.search.query.*` | com.liferay.portal.search.api | The whole ["query" package](https://github.com/liferay/liferay-portal/tree/7.2.x/modules/apps/portal-search/portal-search-api/src/main/java/com/liferay/portal/search/query) is new as of @product_ver@
`com.liferay.portal.search.filter.*` | com.liferay.portal.search.api | The whole ["filter" package](https://github.com/liferay/liferay-portal/tree/7.2.x/modules/apps/portal-search/portal-search-api/src/main/java/com/liferay/portal/search/filter) is new as of @product_ver@

@product@ has an API that exposes 
[Elasticsearch's native Querying and Filtering functionality](LINK TO ES DOCS). 

Currently, these aggregation types are supported:

- [Bucketing aggregations](https://www.elastic.co/guide/en/elasticsearch/reference/current/search-aggregations-bucket.html) 
    create buckets of documents based on some criterion.  They support
    sub-aggregations.
    - Supported bucket aggregations include children aggregations, date
        histogram aggregations, date range aggregations, diversified sampler
        aggregations, filter aggregations, filters aggregations, geo distance
        aggregations, geo hash grid aggregations, global aggregations, histogram
        aggregations, missing aggregations, nested aggregations, range
        aggregations, reverse nested aggregations, sample aggregations,
        significant terms aggregations, significant text aggregations, and terms
        aggregations.  <!-- I left these out even though they're in the bucket
        aggregations package, because not sure if they're actually aggregations: Order,
        Range, IncludeExcludeClause,Bucket, BucketCountThresholds,
        CollectionMode -->
- [Metrics aggregations](https://www.elastic.co/guide/en/elasticsearch/reference/current/search-aggregations-metrics.html) 
    compute metrics over a set of documents.
    - Supported metrics aggregations include average aggregations, cardinality
      aggregations, extended stats aggregations, geo bounds aggregations, geo
        centroid aggregations, max aggregations, min aggregations, percentile
        ranks aggregations, percentiles aggregations, scripted metric
        aggregations, stats aggregations, sum aggregations, top hits
        aggregations, value count aggregations, and weighted average
        aggregations. 
        <!-- Class included in metrics package but not sure if it's an
        aggregation itself: PercentilesMethod -->
- [Pipeline aggregations](https://www.elastic.co/guide/en/elasticsearch/reference/current/search-aggregations-pipeline.html) 
    aggregate the output of other aggregations and their associated metrics.
    - Supported pipeline aggregations include average bucket pipeline
        aggregations, bucket metrics pipeline aggregations, bucket script
        pipeline aggregations, bucket selector pipeline aggregations, bucket
        sort pipeline aggregations, cumulative sum pipeline aggregations,
        derivative pipeline aggregations, extended stats bucket pipeline
        aggregations, max bucket pipeline aggregations, min bucket pipeline
        aggregations, moving function pipeline aggregations, percentiles bucket
        pipeline aggregations, pipeline aggregations, serial diff pipeline
        aggregations, stats bucket pipeline aggregations, and sum bucket
        pipeline aggregations. 
        <!-- PipelineAggregationResultTranslator, PipelineAggregationTranslator,
        PipelineAggregationVisitor, GapPolicy, -->

