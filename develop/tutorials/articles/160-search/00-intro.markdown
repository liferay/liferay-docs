# Search [](id=introduction-to-liferay-search)

Liferay stores its information in a database, so why not search the database
directly? Why add the complexity of a search engine? First, because database
table merges are expensive! Documents in a search index often contain searchable
fields from multiple tables in the database. Traversing the date in this way
takes too long.

In addition to the performance problem, search engines provide access to
additional features, like relevance and scoring. By contrast, databases do not
support features like fuzzy searching or relevancy. Moreover, search engines can
apply algorithms such as "More Like This" to return similar content. Search
engines also support geolocation, faceting of search results, and multi-lingual
searching.

This section contains information on extending Liferay's search functionality,
enabling your custom entities to be indexed and searched in @product@, and
configuring the developer-friendly embedded Elasticsearch server to suit your
needs. First, some basic search concepts.

## Basic Search Concepts [](id=basic-search-concepts)

**Indexing**: During indexing, a document is sent to the search engine. This
document contains a collection of fields of various types (string, etc.). The
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

## Mapping Definitions [](id=mapping-definitions)

Most search engines can be semi-intelligent in automatically deciphering how to
process documents passed to them. However, there are many instances where it's
desirable to explicitly configure how a field should be processed.

Mappings allow users to control how a search engine processes a given field.
For instance, for all field names that end in "es_ES", we want to process the
field values as Spanish, removing any common Spanish words like "si".

In Elasticsearch and Solr, the two supported search engines for Liferay Portal,
we define mappings using `liferay-type-mappings.json` and `schema.xml`,
respectively.

The Elasticsearch mapping JSON file can be seen in the @product@ source code,
in the `portal-search-elasticsearch6`  module:

    portal-search-elasticsearch6-impl/src/main/resources/META-INF/mappings/liferay-type-mappings.json

The Solr `schema.xml` can be seen in the `portal-search-solr7` module's source code:

    portal-search-solr7-impl/src/main/resources/META-INF/resources/schema.xml

Access the Solr 7 module's source code form the `liferay-portal` repo 
[here](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/portal-search-solr7/portal-search-solr7-impl/src/main/resources/META-INF/resources/schema.xml)

These are default mapping files that are shipped with the product. You can
further customize these mappings to fit your needs. For example, you might want
to use a special analyzer for a custom inventory number field.

## Liferay Search Infrastructure [](id=liferay-search-infrastructure)

Search engines already provide native APIs. Why does Liferay provide search
infrastructure to wrap the search engine? Liferay's search infrastructure does
several things:

1. Index documents with the fields Liferay needs (`entryClassName`,
   `entryClassPK`, `assetTagNames`, `assetCategories`, `companyId`, `groupId`,
   staging status, etc.).

2. Apply the right filters to search queries (e.g., for scoping
   results).

3.  Apply permission checking on the results.

4.  Summarizing returned results.

That's just a taste of Liferay's Search Infrastructure. Continue reading to
learn more.
