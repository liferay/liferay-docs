# Customizing Liferay Search [](id=customizing-liferay-search)

There are several extension points available for users to customize.  The most
obvious is the ability to add a new search engine adapter.

## Adding a new Search Engine Adapter [](id=adding-a-new-search-engine-adapter)

To add a new search engine adapter, developers must create the following
components and publish them to Liferay's OSGi registry:

1. Implement a new IndexSearcher that should convert the Liferay Search objects
   to the underlying search engine’s dialects:
    - QueryTranslator: Translates Liferay Queries to the native search engine's
      queries.
    - FilterTranslator: Translates Liferay filters into native search engine's
      filters.
    - GroupByTranslator: Translates the GroupBy aggregation to the search
      engine's group by top hits aggregation.
    - StatsTranslator: Translates Stats request to the appropriate search
      engine's statistics aggregation.
    - SuggesterTranslator: Translates suggestion requests to the appropriate
      search engine's suggester API.

2. Implement a new IndexWriter that should
    - Convert the Liferay Document to a format understood by the underlying
      search engine's Document Format.
    - Use the search engine’s API to update, add, and delete documents.

3. Implement a new SearchEngineConfigurator that should extend
   `AbstractSearchEngineConfigurator` to perform proper wiring.

4. Implement a new SearchEngine that
    - Should extend from `BaseSearchEngine` to perform any search engine
      specific initialization.
    - Should be published to Liferay's OSGi registry along with the property
      `search.engine.id=[searchEngineId]`.

## Customizing IndexerRequestBufferOverflowHandler [](id=customizing-indexerrequestbufferoverflowhandler)

`IndexerRequestBufferOverflowHandler` controls how the search infrastructure
handles situations where buffered indexer requests has exceeded the configured
maximum buffer size.

To customize, implement an `IndexerRequestBufferOverflowHandler` and publish it
to Liferay's OSGi registry.

## Customizing HitsProcessors [](id=customizing-hitsprocessors)

`com.liferay.portal.kernel.hits.HitsProcessor` objects are held in a
`com.liferay.portal.kernel.hits.HitsProcessorRegistry`. To add a new
HitsProcessor, simply implement the interface and publish to the OSGi registry
with the property `sort.order`.
