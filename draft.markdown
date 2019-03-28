# Statistical Aggregations

Support for 
[GroupBy](https://github.com/liferay/liferay-portal/blob/7.2.x/portal-kernel/src/com/liferay/portal/kernel/search/GroupBy.java) 
and 
[Stats](https://github.com/liferay/liferay-portal/blob/7.2.x/portal-kernel/src/com/liferay/portal/kernel/search/Stats.java) 
aggregations were introduced in 7.0.

Cardinality Aggregations extend @product@'s metrics aggregation capabilities,
providing an approximate (i.e., statistical) count of distinct values returned
by a search query. For example, you could compute a count of distinct values of
the _tag_ field. Refer to the 
[Elasticsearch documentation](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/search-aggregations-metrics-cardinality-aggregation.html) 
for more details.

While this functionality was available in the past directly in the portal kernel
code, it's been extracted and re-implemented in 
[`StatsRequest`](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/portal-search/portal-search-api/src/main/java/com/liferay/portal/search/stats/StatsRequest.java) 
and 
[`StatsResponse`](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/portal-search/portal-search-api/src/main/java/com/liferay/portal/search/stats/StatsResponse.java), 
both introduced in the `com.liferay.portal.search.api` module to avoid modifying
`portal-kernel`. `StatsRequest` provides the same statistical features that the
legacy `com.liferay.portal.kernel.search.Stats` does, and adds the new
cardinality option.

### `StatsRequest`

The `StatsRequest` Provides a map of field names and the metric aggregations
that are to be computed for each field.

1. Get a reference to `com.liferay.portal.search.searcher.SearchRequestBuilderFactory`:

```java
        @Reference
        SearchRequestBuilderFactory searchRequestBuilderFactory;
```

2. Get an instance of `com.liferay.portal.search.searcher.SearchRequestBuilder`:

```java
        SearchRequestBuilder searchRequestBuilder = searchRequestBuilderFactory.getSearchRequestBuilder();
```

3. Get a`com.liferay.portal.search.searcher.SearchRequest` instance from the builder:

```java
        SearchRequest searchRequest = searchRequestBuilder.build();
```

4. Get a reference to `com.liferay.portal.search.stats.StatsRequestBuilderFactory`:

```java
        @Reference
        StatsRequestBuilderFactory statsRequestBuilderFactory;
```

5. Get a `com.liferay.portal.search.stats.StatsRequestBuilder` instance and
   build `com.liferay.portal.search.stats.StatsRequest` with the desired
   metrics:

```java
        StatsRequestBuilder statsRequestBuilder = statsRequestBuilderFactory.getStatsRequestBuilder();

        StatsResponse expectedStatsResponse = statsResponseBuilder.cardinality(
            31
        ).count(
            31
        ).field(
            field
        ).max(
            31
        ).mean(
            16
        ).min(
            1
        ).sum(
            496
        ).sumOfSquares(
            10416
        ).build();
```

6. Set `StatsRequest` on the `SearchRequest`:

```java
        searchRequest.statsRequests(statsRequest);
```

7. Get a reference to `com.liferay.portal.search.searcher.Searcher`:

```java
        @Reference
        protected Searcher searcher;
```

8. Perform a search using `Searcher` and `SearchRequest` to get
   `com.liferay.portal.search.searcher.SearchResponse`:

```java
        SearchResponse searcher.search(searchRequest);
```

**Example:** https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/portal-search/portal-search-test-util/src/main/java/com/liferay/portal/search/test/util/stats/BaseStatisticsTestCase.java#L128

### `StatsResponse`

Contains the metrics aggregations computed by the search engine for a given
field.

1. Get the map containing the metrics aggregations computed by the search engine:

```java
        Map<String, StatsResponse> map = searchResponse.getStatsResponseMap();
```

2. Get the `StatsResponse` for a given field:

```java
        StatsResponse statsResponse = map.get(field);
```

3. Get the desired metric, for example _cardinality_:

```java
        statsResponse.getCardinality();
```

**Example:** https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/portal-search/portal-search-test-util/src/main/java/com/liferay/portal/search/test/util/stats/BaseStatisticsTestCase.java#L128

<!-->
### Using the Legacy `Stats` Object

If you prefer, you can continue using the legacy
`com.liferay.portal.kernel.search.Stats` object:

1. Create a `Stats` instance with the desired metrics:
```java
        Stats stats = new Stats() {
            {
                setCount(true);
                setField(field);
                setMax(true);
                setMean(true);
                setMin(true);
                setSum(true);
                setSumOfSquares(true);
            }
        };
```
3. Set `Stats` on the `SearchContext`:
```java
        searchRequestBuilder.withSearchContext(searchContext -> searchContext.addStats(stats));
```

**Example:** https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/portal-search/portal-search-test-util/src/main/java/com/liferay/portal/search/test/util/stats/BaseStatisticsTestCase.java#L42
</!-->

## External References

* https://www.elastic.co/guide/en/elasticsearch/reference/6.5/search-aggregations-metrics.html
* https://www.elastic.co/guide/en/elasticsearch/reference/6.5/search-aggregations-metrics-cardinality-aggregation.html
* https://lucene.apache.org/solr/guide/7_5/the-stats-component.html

## Search Engine Connector Support

* Elasticsearch 6: Yes
* Solr 7: Yes

## New/Related APIs

These are the relevant APIs for building Statistics Aggregations:
 
API (FQCN) | Provided by Artifact |
---------: | :------------------: |
`com.liferay.portal.search.searcher.SearchRequestBuilder#statsRequests(StatsRequest... statsRequests)` | `com.liferay.portal.search.api`
`com.liferay.portal.search.searcher.SearchResponse#getStatsResponseMap()` | `com.liferay.portal.search.api`
**`com.liferay.portal.search.stats.StatsRequest`** |	`com.liferay.portal.search.api`
`com.liferay.portal.search.stats.StatsRequestBuilder` |	`com.liferay.portal.search.api`
`com.liferay.portal.search.stats.StatsRequestBuilderFactory` |	`com.liferay.portal.search.api`
**`com.liferay.portal.search.stats.StatsResponse`** |	`com.liferay.portal.search.api`
`com.liferay.portal.kernel.search.Stats` | `portal-kernel`

## Deprecated APIs

* SearchSearchRequest#getStats()
* SearchSearchRequest#setStats(Map<String, Stats> stats)

