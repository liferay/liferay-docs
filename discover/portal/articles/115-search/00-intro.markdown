# Search [](id=search)

Sites built on @product@ often feature lots of content split over lots of asset
types. Web content articles, documents and media files, and blogs entries are
just a few examples. Most content types in @product@ are *assets*. Under the
hood, assets use the [Asset
API](/develop/tutorials/-/knowledge_base/7-0/asset-framework) and have an
[Indexer
class](/develop/tutorials/-/knowledge_base/7-0/introduction-to-liferay-search#indexers).
Any content that has these features can be searched in the @product@ Search
application. 

![Figure 1: There are many searchable out-of-the-box asset types.](../../images/search-assets.png)

## Searching the Index, not the Database [](id=searching-the-index-not-the-database)

@product@ stores its data in a database. You might incorrectly assume that
you're directly searching the database when you use @product@'s Search
application. @product@ instead leverages a search engine for its search
capabilities. Using a search engine like
[Elasticsearch](https://www.elastic.co/products/elasticsearch) lets you convert
searchable entities into *documents*. Documents are created and added
to the *index* at the same time they're added to the database. They're also
updated whenever the database is updated, and deleted from the index when the
backing entity is deleted from the database. When you enter a search term in
@product@, nothing happens in the database, because the documents are already
indexed on the Elasticsearch server, and that's where the search is executed.

It's worth adding the complexity of a search engine, rather than searching the
database directly, for performance reasons and for some of the features that
search engines provide, like algorithms that give you the ability to use
relevancy scores. For more technical details, see the [Introduction to
Search](/develop/tutorials/-/knowledge_base/7-0/introduction-to-liferay-search)
developer article.

## Leveraging Elasticsearch in @product@ [](id=leveraging-elasticsearch-in-product)

The default search engine used by @product@ is Elasticsearch, which is backed by
the Lucene search library. There's an Elasticsearch server embedded in @product@
bundles, which is handy for testing and development purposes. Production
environments must install a separate, remote Elasticsearch server (or even
better, cluster of servers). For information on how to set up Elasticsearch for
@product@ in production, read the [deployment
guide](/discover/deployment/-/knowledge_base/7-0/installing-a-search-engine).

## @product@ Search Features [](id=product-search-features)

Searching is simple and straghtforward. Find a search portlet (there's one
embedded in every page by default), enter a term in its search bar, and click
*Enter*.

![Figure 2: There's a search bar embedded on all @product@ pages by default.](../../images/search-bar.png)

A results page is displayed. If there are hits to search engine documents,
you'll see them as search results in the right hand column. In the left hand
column you'll see search facets.

![Figure 3: There's a search bar embedded on all @product@ pages by default.](../../images/search-results.png)

The search bar, search results, and search facets make up three powerful
features in @product@'s search UI.

### Search Bar [](id=search-bar)

The search bar is simple: it's where you enter *search terms*. Search terms are
the text you send to the search engine to match against the documents in the
index. The documents that are returned are where this gets interesting.

### Search Results and Relevance [](id=search-results-and-relevance)

The search term is processed by an algorithm in the search engine, and search
results are returned to users in order of relevance. Relevance is determined by
a document's *score*, generated against the search query. The higher the score,
the more relevant a document is considered. The particular relevance algorithm
used is dependent on the [algorithms are provided by the search engine
(Elasticsearch by default)](https://www.elastic.co/guide/en/elasticsearch/guide/current/relevance-intro.html#relevance-intro).

In short, answers to questions like those below determine the relevance score of a hit
(matching document): 

- How many times does the search term appear in a document's field?
- How many times does the search term appear in the same field of all the other
  documents in the index?
- How long is the field where the term appears?

If the search term appears with greater frequency in the field of one document
than is the case for the same field in other documents, the score will be
higher. However, if it's a long field (like a *content* field for a Blogs Entry
document) then the presence of the search term is discounted. Its presence in a
shorter field (like a *title* field) produces a higher relevance score. See [the
Search Results article](/discover/portal/-/knowledge_base/7-0/search-results)
for a longer discussion of relevance.

### Search Facets [](id=search-facets)

Facets are a core feature of the @prodcut@ Search application.

![Figure 4: *Sites* and *Asset Entries* are two of the facet sets you'll encounter. They let you drill down to results that contain the search terms you entered.](../../images/search-faceted-search.png)

Facets allow users of the Search application to filter search results. Think of
facets as buckets that hold similar search results. You might want to see the
results in all the buckets, but after scanning the results, you might decide
that the results of just one bucket better represent what you're looking for. So
what facets are included in @product by default?

- Site
- Asset type
- Asset tag
- Asset category
- Folder
- User
- Modified date

You've probably used something similar on any number of sites, especially with
online commerce. You search for an item, are presented with a list of results,
and a list of buckets you can click to further refine the search results,
without entering additional search terms. Search facets work the same way in
@product@. Facets are, of course,
[configurable](/discover/portal/-/knowledge_base/7-0/configuring-facets).
