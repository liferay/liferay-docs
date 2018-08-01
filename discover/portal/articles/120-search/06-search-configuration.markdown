# Configuring Search

Come on, what kind of a title is _Configuring Search_? That could mean lots of
different things: 

- System scoped search configuration
- Reindexing to make sure the search indexes are current with the database
- Tweaking the search widgets added to pages
- Creating new search pages
- Configuring the connectors that let @product@ and the search engine
    communicate

It's true, _Configuring Search_ means all those things. This article provides a
high level overview of what search behavior is configurable out of the box, and
importantly, _where_ to find search configuration options.

## System Scoped Search Configuration

System scoped search configurations are primarily found in 
[System Settings](/discover/portal/-/knowledge_base/7-1/system-settings)
System Settings.

1.  Go to Control Panel &rarr; configuration &rarr; System Settings.

2.  Click the *Search* category under the Platform section.

    Alternatively, search for *Search*.

![Figure x: ](../../images/search-category-system-settings.png)

These system scoped configurations are available in System Settings:

**Default Keyword Query**
: `DefaultKeywordQueryContributor` automatically adds `description`, `userName`,
and `title` fields to the keyword search query. Specify the entry class names
`DefaultKeywordQueryContributor` should ignore.

**Default Search Result Permission Filter**
: Configure *post-filtering permission checking* (database permission checking
that occurs after the results are returned from the search index). Read 
[here](/discover/portal/-/knowledge_base/7-1/search-results-behavior#final-permissions-checking) 
for more information.

**Index Status Manager**
: Enable *Index Read Only* to suspend all indexing operations and writes to the
search engine. Searches return only the documents already indexed. This is
useful for speeding up large data imports, but it should be disabled and a full
reindex executed once the import is finished.

**Indexer Writer Helper**
: Setting Index Commit Immediately to true (the default) may negatively impact
search engine performance, as each write request forces the search engine to
refresh the index reader, potentially flushing transactions to disk. The default
behavior is to commit immediately for index writing on individual assets (e.g.
add blog, update blog) but delay commits for bulk index writing operations (e.g.
index all users, index all form entries) until all entries have been sent to the
search engine. Setting this to false changes the behavior for individual index
operations, and may cause applications like Asset Publisher to exhibit a delayed
response when showing newly added content. See
https://www.elastic.co/guide/en/elasticsearch/guide/current/near-real-time.html
for more information.

<!-- **Index Registry**
Settings: Buffered (true by default), Buffered Execution Mode (DEFAULT by
default), Maximum Buffer Size (10000 by default), Minimum Buffer Availability
Percentage.
-->
<!-- Need more info -->

**Index Query Preprocessor**
: Fields with names matching the patterns set here are treated as non-analyzed
keyword fields. Instead of scored full text queries, matching is performed by
non-scored wildcard queries. This is a resource intensive operation that
degrades search engine performance as indexes grow larger. For substring
matching, relying on the
[ngram tokenizer](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/analysis-ngram-tokenizer.html) 
usually performs better.

**Reindex**
: Use the Indexing Batch Sizes property to set the number of documents indexed
per batch for model types that support batch indexing. Defaults to 10000. For
models with large documents, decreasing this value may improve stability when
executing a full reindex.

**Permission Checker**
: Configure *pre-filtering permission checking* (permission checking on the
search index) behavior. See 
[here](/discover/portal/-/knowledge_base/7-1/search-results-behavior#initial-permissions-checking) 
for more information.

**Elasticsearch 6**
: Configure the connection between @product@ and Elasticsearch 6. See
[here](/discover/deployment/-/knowledge_base/7-1/configuring-the-liferay-elasticsearch-connector) 
for more information.

**Search Web**
: Revert the default search experience from using the new Search Widgets to the
classic Search Portlet that was standard in past releases. See 
[here](/discover/portal/-/knowledge_base/7-1/configuring-search-pages#legacy-search-experience)
for more information.

In addition to the System Settings for Search, the action of recreating the
search indexes is a system scoped action. 

1.  Go to Control Panel &rarr; Configuration &rarr; Search.

2.  Reindex one of the following:

    - All indexable assets
    - An individual indexable asset
    - All spell check indexes

Portal properties are system scoped configurations as well. The 
[Lucene Search](https://docs.liferay.com/portal/7.1-latest/propertiesdoc/portal.properties.html#Lucene%20Search) 
portal properties are available for configuring low level search behavior.
Review the properties and their descriptions and determine if they apply to your
search requirements.

## Site Scoped Search Configuration

The only available site configuration option for search is the use of
[Search Pages](/discover/portal/-/knowledge_base/7-1/configuring-search-pages).

Search pages contain the search widgets used to search for content within a
particular site.

## Widget Scoped Search Configuration

Several search widgets are available, and each one has its own configuration
options:

**Search Results**
:  Configure how search results are displayed. Read 
[here](/discover/portal/-/knowledge_base/7-1/search-results) for more
information.

**Search Bar**
: Configure the behavior of how search keywords are processed. See
[here](/discover/portal/-/knowledge_base/7-1/searching-for-assets#configuring-the-search-bar) 
for more information.

**Search Facets**
: Configure each facet's behavior and URL parameters. See
[here](/discover/portal/-/knowledge_base/7-1/facets) for more information.

**Search Options**
: This is a special case, where configuring this widget defines page scoped
behavior. Add the Search Option widget to a page, and define two booleans for
the Search Page: 

- Allow Empty Searches: By default, failure to enter a keyword returns no
    results. Enabling this ensure that _all_ results are returned when no
    keyword is entered in the Search Bar.

- Basic Facet Selection: By default, facet counts are recalculated after each
    facet selection. Enable this to turn off facet recounting.

**Search Suggestions**
: Suggest better queries and spell check queries. See
[here](/discover/portal/-/knowledge_base/7-1/searching-for-assets#search-suggestions) 
for more information.

**Search Insights**
: Add this to the search page to inspect the full query string that's
constructed by the backend search code when the User enters a keyword. Only
useful for testing and development.

