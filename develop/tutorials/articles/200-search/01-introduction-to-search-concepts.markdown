# Introduction to Liferay Search [](id=introduction-to-liferay-search)

Liferay stores its information in a database. If you need to search for data,
why not search the database directly? Why add the complexity of a search
engine? Database table merges are expensive! Documents in a search index often
contain searchable fields from multiple tables in the database.

Searching with a search engine provides access to features such as relevance
and scoring. Database searches do not support features like fuzzy searching or
any type of relevancy. Moreover, when searching with a search engine, you can
apply algorithms such as "More Like This" to obtain similar content. Search engines also support geolocation, faceting of search results, and multi-lingual searching.

## Basic Search Concepts [](id=basic-search-concepts)

**Indexing**: During indexing, a document is sent to the search engine. This
document contains a collection of fields of various types (string, etc). The
search engine processes each field within the document. For each field, the
search engine determines whether it needs to simply store the field or if it
needs to undertake special analysis (index time analysis). Index time analysis
can be configured for each field (see Mapping Definitions).

For fields requiring analysis, the search engine first tokenizes the value to
obtain individual words or tokens. Following tokenization, the search engine
passes each token through a series of analyzers. Analyzers perform different
functions.  Some remove common words or stop words (e.g., "the", "and", "or")
while others perform operations like lowercasing all characters.

**Searching**: Searching involves sending a search query and obtaining results
(a.k.a. hits) from the search engine. The search query may be comprised of both
queries and filters (more on this later). Each query or filter specifies a
field to search within and the value to match against. Upon receiving the
search query, the search engine iterates through each field within the nested
queries and filters. During this process, the engine may perform special
analysis prior to executing the query (search time analysis). Search time
analysis can be configured for each field (see Mapping Definitions).

### Mapping Definitions [](id=mapping-definitions)

Most search engines can be semi-intelligent in automatically deciphering how to
process documents passed to them. However, there are many instances where it's
desirable to explicitly configure how a field should be processed.

Mappings allow users to control how a search engine processes a given field.
For instance, for all field names that end in "es_ES", we want to process the
field values as Spanish, removing any common Spanish words like "si".

In Elasticsearch and Solr, the two supported search engines for Liferay Portal,
we define mappings using `liferay-type-mappings.json` and `schema.xml`,
respectively.

The Elasticsearch mapping JSON file can be seen here:
[https://github.com/liferay/liferay-portal/blob/master/modules/apps/foundation/portal-search/portal-search-elasticsearch/src/main/resources/META-INF/mappings/liferay-type-mappings.json](https://github.com/liferay/liferay-portal/blob/master/modules/apps/foundation/portal-search/portal-search-elasticsearch/src/main/resources/META-INF/mappings/liferay-type-mappings.json)

The Solr `schema.xml` can be seen here:
[https://github.com/liferay/liferay-portal/blob/master/modules/apps/portal-search-solr/portal-search-solr/src/main/resources/META-INF/resources/schema.xml](https://github.com/liferay/liferay-portal/blob/master/modules/apps/portal-search-solr/portal-search-solr/src/main/resources/META-INF/resources/schema.xml)

These are default mapping files that are shipped with the product. You can
further customize these mappings to fit your needs. For example, you might want
to use a special analyzer for a custom inventory number field.

### Liferay Search Infrastructure [](id=liferay-search-infrastructure)

Search engines already provide native APIs. Why does Liferay provide search
infrastructure to wrap search engines? Liferay's search infrastructure ensures
that documents are indexed with fields Liferay needs:

`entryClassName`, `entryClassPK`, `assetTagNames`, `assetCategories`, `companyId`, `groupId`, staging status, etc.

Liferay's search infrastructure ensures that the proper set of filters are
added to search queries to scope results. Liferay's search infrastructure also
provides capabilities like permission checking and creating hit summaries for
display.

## Liferay Search API [](id=liferay-search-api)

Liferay Portal's Search API allows users to build a search query, execute it,
and obtain search hits that match the query.

### Queries and Filters [](id=queries-and-filters)

Elasticsearch and Solr do not make API level distinctions between queries and
filters. However, Liferay's API explicitly provides two sets of APIs, one for
queries and one for filters.

A *filter* asks a yes or no question for every document. A *query* asks the
same yes or no question AND how well (score) a document matches the specified
criteria. For instance, a filter might ask is the status field equal to staging
or live. A query might ask if the document's content field field contains the
words "Liferay", "Content", "Management", and how relevant the content of the
document is to the search terms.

With respect to performance, filters are much faster since the documents that
match a filter can be easily cached. Queries not only match documents but also
calculate scores. Liferay uses filters and queries together so that filters can
reduce the number of matched documents before the query examines them for
scoring.

Liferay's Search API supports the following types of queries:

Full text queries:

- MatchQuery: Full text matching, scored by relevance.
- MultiMatchQuery: MatchQuery over several fields.
- StringQuery: Uses Lucene query syntax

Term queries:

- TermQuery: Exact matching on keyword fields and indexed terms
- TermRangeQuery: TermQuery with a range
- WildcardQuery: Wildcard (* and ?) matching on keyword fields and indexed terms
- FuzzyQuery: Scrambles characters in input before matching

Compound queries:

- BooleanQuery: Allows a combo of several query types. Individual queries are
  added as clauses with SHOULD | MUST | MUST_NOT.
- DisMaxQuery

Other queries:

- MoreLikeThisQuery
- MatchAllQuery: Matches all documents

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

### Aggregations [](id=aggregations)

Aggregations help summarize search results. Individual aggregations can be used
to create more complex aggregations. Facets are a type of aggregation. In
addition to facets, Liferay also provides group by and statistics aggregations.

Facets:

- Date Range Facet
- Modified Date Facet
- MultiValue Facet
- Range Facet
- Scope Facet
- Simple Facet

Statistics:

Stats provides general statistics for a desired field within the returned
search results:

- count
- max
- mean
- min
- missing
- standard deviation
- sum
- sum of squares

GroupBy:

GroupBy is a powerful feature that allows you to group search results based on
a particular field. For example, suppose you wish to group the search results
based on the asset type (e.g., web content article, document, blog post, etc.).
To do so, you would create a search query that contains a GroupBy aggregation
with the field "entryClassName".

Other attributes you can specify:

- The maximum number of results in each group
- Special sorting for the grouped results

### Indexers [](id=indexers)

There is an Indexer for each asset in the portal (e.g., DLFileEntryIndexer).
This allows each asset to control what fields are indexed and what filters are
applied to the search query.

Generally, when you create an asset that requires indexing, you would implement
a new Indexer by extending `com.liferay.portal.kernel.search.BaseIndexer<T>`.

For more information, consult the Javadocs for
`com.liferay.portal.kernel.search.Indexer<T>` and
`com.liferay.portal.kernel.search.BaseIndexer<T>`: [https://docs.liferay.com/portal/7.0/javadocs](https://docs.liferay.com/portal/7.0/javadocs)

### IndexerPostProcessor [](id=indexerpostprocessor)

The IndexerPostProcessor allows developers to customize

- Search queries before they are sent to the search engine
- Documents before they are sent to the search engine
- Summaries for results before they are returned to the end users

This is the preferred way to customize existing Indexers.

Follow these steps to add a new IndexerPostProcessor:

1. Implement the interface
   `com.liferay.portal.kernel.search.IndexerPostProcessor`.
2. Publish it to the OSGi registry with the property `indexer.class.name`

`postProcessContextQueryBooleanFilter` allows the developer to customize the
filters created by the `Indexer.getFacetBooleanFilter`. These filters are
generally applied to the fields:

- entryClassName
- relatedClassName
- relatedEntryClassNames
- permissions related fields (e.g., roleId, groupId, etc.).

`postProcessFullQuery` allows the developer to customize the overall search
query which includes

- Filters for any default facets, including those for
    - asset category ids
    - asset tag names
    - entry class names
    - folderIds
    - groupIds
    - layoutUUIDs
    - userId

- The keyword search queries. By default, this includes searches for the
  fields
    - description
    - title
    - userName
    - keyword
    - searchable Expando fields
    - localized fields for assetCategoryTitles

### HitsProcessor [](id=hitsprocessor)

`com.liferay.portal.kernel.search.HitsProcessor` allows developers to
preprocess the results from the search engine before they are returned to the
user. This allows for features like

- spell checking
- suggesting related queries
- indexing search queries that have returned high quality search results

HitsProcessors are stored in a HitsProcessorRegistry and sorted by their
`sort.order`. Essentially, we have a chain of responsibility held by the
HitsProcessorRegistry.

By default, the HitsProcessor order is:

1. `CollatedSpellCheckHitsProcessor`
    - Performs a spell check if the minimum score for search results is less than a given threshold
    - Number of results defined in portal.properties
      (index.search.collated.spell.check.result.scores.threshold)

2. `AlternateKeywordQueryHitsProcessor`
    - Automatically issue a query using the suggested keywords from the `CollatedSpellCheckHitsProcessor`.

3. `QueryIndexingHitsProcessor`
    - If query indexing is enabled (`index.search.query.indexing.enabled` in
      `portal.properties`), then index the search query if the number of hits
      has exceeded a configured quantity
      (`index.search.query.indexing.threshold` in `portal.properties`).

4. `QuerySuggestionHitsProcessor`
    - If number of results returned has not met a given threshold
      (`index.search.query.suggestion.scores.threshold` in `portal.properties`),
      then suggest other potential queries that previous searches have yielded
      more results (`index.search.query.suggest.max` in `portal.properties`).

### Suggestions [](id=suggestions)

Suggestions are a powerful feature where the search engine can suggest
"similar" results for a given query. For instance, suppose you have a blog
entry with the title "Liferay Portal Content Management" and you would like to
find other content with similar titles.

`com.liferay.portal.kernel.search.IndexSearcher` provides methods to access
suggestion capabilities. It implements
`com.liferay.portal.kernel.search.suggest.QuerySuggester`.

The QuerySuggester provides facilities for

- Spell Checking
- Related search queries
- General Suggester requests

#### Spell Checking [](id=spell-checking)

For Elasticsearch, spell checking heavily relies on the suggester API:
    - Dictionary words are analyzed by their language specific analyzer and indexed.
    - `TermSuggester` is used to provide suggestions for words based on
      specific `StringDistance` algorithms.

Solr's implementation is less sophisticated. Solr's implementation of
`Suggester` is much less flexible.  Solr's spell checking algorithm is based
strictly on NGrams and does not handle Asian languages very well.

#### Similar Search Queries [](id=similar-search-queries)

Like spell checking, similar search queries has a more robust implementation in
Elasticsearch. The Elasticsearch implementation uses phrase suggesters on
indexed keyword search queries.

Solr's similar search queries implementation is again based on tokenized
NGrams.

#### Other Suggesters [](id=other-suggesters)

You can also send custom Suggester requests and get SuggesterResults back from the search engine by calling `QuerySuggester.suggest(SearchContext, Suggester)`.

## Search Adapter API [](id=search-adapter-api)

Search adapters convert Liferay Portal's API to the underlying search engine's
API. This pluggable architecture allows customers to more easily integrate with
other search engines. Liferay ships with two adapters: an Elasticsearch adapter
and a Solr adapter.

The search adapter API has 2 primary interfaces:

- `IndexSearcher`: invoked for all search operations
- `IndexWriter`: used when adding, updating, or deleting documents from the
  search engine.

## Transactional Search [](id=transactional-search)

Search engines do not operate within a traditional JTA/JTS transaction. In
place of "real" transactions, Liferay buffers indexing operations (delete,
update) until either the surrounding transaction has been committed or we have
exceeded the max buffer size. The buffered indexer requests are abandoned in
the event of transaction rollback. This gives us some semblance of
transactional control, except in scenarios where we have large batches of
commits (e.g., exceeds `maxBufferSize`).

When `maxBufferSize` has been exceeded, the search infrastructure executes
buffered indexer requests to free up space in the buffer.

Buffered `IndexerRequests` always execute in FIFO order. There is no collation
of `IndexerRequests` in the buffer.

You can activate / deactivate and set the buffer size by configuring
`com.liferay.portal.search.configuration.IndexerRegistryConfiguration`. By
default, the buffering is activated and the max buffer size is 200.

For a list of buffered methods, see `com.liferay.portal.kernel.search.Indexer`.
All methods annotated with `@Bufferable` are subject to potential buffering.
