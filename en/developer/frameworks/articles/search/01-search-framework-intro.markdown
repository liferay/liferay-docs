---
header-id: search
---

# Search

[TOC levels=1-4]

@product@ contains a search engine based on [Elasticsearch](https://www.elastic.co/products/elasticsearch). You can 
extend it, implement search for your own applications, and it's highly
configurable. 

## Basic Search Concepts 

**Indexing**: During indexing, a document is sent to the search engine. This
document contains fields of various types (string, etc.). The search engine
processes each field and determines whether to store the field or analyze it.
Index time analysis can be configured for each field (see Mapping Definitions).

For fields requiring analysis, the search engine first tokenizes the value to
obtain individual words or tokens. Then it passes each token through a series of
analyzers, which perform different functions. Some remove common words or stop
words (e.g., "the", "and", "or") while others perform operations like
lowercasing all characters.

**Searching**: Searching involves sending a search query and obtaining results
(a.k.a. hits) from the search engine. The search query might contain both
queries and filters (more on this later). Queries and filters specify a
field to search within and the value to match against. The search engine
iterates through each field within the nested queries and filters and may
perform special analysis prior to executing the query (search time analysis).
Search time analysis can be configured for each field (see Mapping Definitions).

## Mapping Definitions 

_Mappings_ control how a search engine processes a given field. For instance,
if a field name ends in "es_ES", it should be processed as Spanish, removing any
common Spanish words like "si".

In Elasticsearch and Solr, the two supported search engines for @product@,
mappings are defined in `liferay-type-mappings.json` and `schema.xml`,
respectively.

The Elasticsearch mapping JSON file is in the @product@ 
[source code](https://github.com/liferay/liferay-portal),
in the `portal-search-elasticsearch6`  module:

    portal-search-elasticsearch6-impl/src/main/resources/META-INF/mappings/liferay-type-mappings.json

The Solr `schema.xml` is in the `portal-search-solr7` module's source code:

    portal-search-solr7-impl/src/main/resources/META-INF/resources/schema.xml

Access the Solr 7 module's source code from the `liferay-portal` repository 
[here](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/portal-search-solr7/portal-search-solr7-impl/src/main/resources/META-INF/resources/schema.xml).

You can customize these mappings to fit your needs. For example, you might want
to use a special analyzer for a custom inventory number field.

## Liferay Search Infrastructure 

Search engines already provide native APIs, but Liferay wraps the engine with
a search infrastructure that does several things:

1. Index documents with the fields Liferay needs (`entryClassName`,
   `entryClassPK`, `assetTagNames`, `assetCategories`, `companyId`, `groupId`,
   staging status, etc.).

2. Apply the right filters to search queries (e.g., for scoping
   results).

3.  Apply permission checking on the results.

4.  Summarize returned results.

That's just a taste of Liferay's Search Infrastructure. Continue reading to
learn more.
