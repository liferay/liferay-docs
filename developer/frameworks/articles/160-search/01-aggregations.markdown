# Aggregations

* Target Audience: Developers
* Article Type: Developer Reference

## External References
https://www.elastic.co/guide/en/elasticsearch/reference/current/search-aggregations.html#_structuring_aggregations

## Search Engine Connector Support
* Elasticsearch 6: Yes
* Solr 7: No

## New/Related APIs
Listed those which are relevant for developers.

API (FQCN) | Provided by Artifact | Notes |
-----------|:--------------------:|:--------:|
`com.liferay.portal.search.aggregation.*` | com.liferay.portal.search.api | The whole ["aggregation" package](https://github.com/liferay/liferay-portal/tree/7.2.x/modules/apps/portal-search/portal-search-api/src/main/java/com/liferay/portal/search/aggregation) is new as of @product_ver@

Aggregations take a search query and provide aggregated data based on it.
Aggregations can be composed to provide complex data summaries.

**Note from Russ:  I know it's a diverse feature set, but we should provide some use cases for
these aggregations. If a developer isn't sure what these can be used for they
won't know whether they are needed for their goals.** 

@product@ has an API that exposes 
[Elasticsearch's native Aggregation functionality](LINK TO ES DOCS). 

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

All the supported aggregations are found in the `portal-search-api` module's
`com.liferay.portal.search.aggregation` package.

In addition to these aggregations, other aggregation-like features are present
in the @product@searhc API:

**Group By** is a powerful feature that allows you to group search results based on
a particular field. For example, suppose you wish to group the search results
based on the asset type (e.g., web content article, document, blog post, etc.).
To do so, you would create a search query that contains a GroupBy aggregation
with the field "entryClassName".

Other attributes you can specify:

- The maximum number of results in each group
- Special sorting for the grouped results

**Facets** act as bucket aggregations, holding results that share a certain
characteristic.

## Using Aggregations

**Note from Russ: we probably don't want to demonstrate how to use every one of
tese, but we should demonstrate the pattern and point out any best practices
that we can. We could show one or two code snippets examples per aggregation
type (bucket, metric, pipeline).**


