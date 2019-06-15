---
header-id: indexing-framework
---

# Indexing Framework

[TOC levels=1-4]

Unless you're searching for model entities using database queries (not
recommended in most cases), each asset in @product@ must be indexed in the
search engine. The indexing code is specific to each asset, as the asset's
developers know what fields to index and what filters to apply to the search
query.

In past versions of @product@, when your asset required indexing, you would
implement a new Indexer by extending
`com.liferay.portal.kernel.search.BaseIndexer<T>`. @product-ver@ introduces
a new pattern that relies on 
[composition instead of inheritance](https://stackoverflow.com/questions/2399544/difference-between-inheritance-and-composition).
If you want to use the old approach, feel free to extend `BaseIndexer`. It's
still supported. 

## Search and Indexing Overview

Liferay's original Search API was built around the Lucene search and indexing
library. To this day, familiarity with Lucene will jump-start your understanding
of Liferay's Search API. However, starting with the 7.0 version of @product@,
the Search API is being reworked, so that the parts closely tied to Lucene are
becoming more generic. Elasticsearch support was added (in addition to Solr),
and most of the newer searching and indexing APIs aim to leverage/map
Elasticsearch APIs. This means that in many cases (for example the `Query`
types) there is a one-to-one mapping between the Liferay and Elasticsearch APIs.

In addition to the Elasticsearch centered APIs, Liferay's Search Infrastructure
includes additional APIs serving these purposes: 

-   Indexed documents include the fields needed by @product@ (e.g., 
    `entryClassName`, `entryClassPK`, `assetTagNames`, `assetCategories`, 
    `companyId`, `groupId`, staging status). 
-   It ensures the scope of returned search results is appropriate by applying 
    the right filters to search requests. 
-   It provides permission checking and hit summaries to display in the search 
    portlet. 

To understand how search and indexing code makes your custom models seamlessly
searchable, you must know how to influence each portion of the search and
indexing cycle: Indexing, Searching, and Returing Results.


