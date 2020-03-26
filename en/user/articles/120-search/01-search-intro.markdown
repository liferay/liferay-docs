---
header-id: search
---

# Search

[TOC levels=1-4]

Sites often feature lots of content split over lots of asset types.  Web content
articles, documents and media files, and blogs entries are just a few examples.
Most content types are *assets*. Under the hood, assets use the
[Asset API](/docs/7-2/frameworks/-/knowledge_base/f/asset-framework) 
and have an Indexer class. Any content that has these features can be
searched. 

![Figure 1: The Type Facet configuration lists the searchable out-of-the-box asset types.](../../images/search-assets.png)

## Elasticsearch

The default search engine is Elasticsearch, which is backed by the Lucene
search library. There's an Elasticsearch server embedded in all bundles, which
is handy for testing and development purposes. Production environments must
install a separate, remote Elasticsearch server (or even better, cluster of
servers).  For information on how to install Elasticsearch, read the 
[deployment guide](/docs/7-2/deploy/-/knowledge_base/d/elasticsearch).

## Search Features

Searching is simple and straightforward. Find a search bar (there's one embedded
in every page by default), enter a term, and click *Enter*.

![Figure 2: There's a search bar embedded on all pages by default.](../../images/search-bar.png)

After search is triggered, a results page appears. If there are hits to search
engine documents, they appear as search results in the right hand column. In the
left hand column are search facets.

![Figure 3: Results are displayed in the Search Results portlet.](../../images/search-results.png)

The search bar, search results, and search facets make up three powerful
features in the search UI.

### Search Bar

The search bar is simple: it's where you enter *search terms*. Search terms are
the text you send to the search engine to match against the documents in the
index. 

### Search Results and Relevance

The search term is processed by an algorithm in the search engine, and search
results are returned to users in order of relevance. Relevance is determined by
a document's *score*, generated against the search query. The higher the score,
the more relevant a document is considered. The particular relevance algorithm
used is dependent on 
[algorithms provided by the search engine (Elasticsearch by default)](https://www.elastic.co/guide/en/elasticsearch/reference/current/relevance-intro.html#relevance-intro).

### Search Facets

Facets allow users of the Search application to filter search results. Think of
facets as buckets that hold similar search results. You might want to see the
results in all the buckets, but after scanning the results, you might decide
that the results of just one bucket better represent what you want. So what
facets are included out of the box?

- Category
- Folder
- Site
- Tag
- Type
- User
- Modified
- Custom

![Figure 4: *Site* and *Type* are two of the facet sets you'll encounter. They let you drill down to results that contain the search terms you entered.](../../images/search-faceted-search.png)

You've probably used something similar on any number of sites. You search for an
item, are presented with a list of results, and a list of buckets you can click
to further drill down into the search results, without entering additional
search terms. Search facets work the same way. Facets are, of course,
[configurable](/docs/7-2/user/-/knowledge_base/u/facets).
