# Search [](id=search)

@product@ sites often feature lots of content split over lots of asset types.
Web content articles, documents and media files, and blogs entries are just a few
examples. Most content types in @product@ are *assets*. Under the hood, assets
use the
[Asset API](/develop/tutorials/-/knowledge_base/7-1/asset-framework) 
and have an 
[Indexer class](/develop/tutorials/-/knowledge_base/7-1/introduction-to-liferay-search#indexers).
Any content that has these features can be searched. 

![Figure 1: The Type Facet configuration lists the searchable out-of-the-box asset types.](../../images/search-assets.png)

## Searching the Index, not the Database [](id=searching-the-index-not-the-database)

@product@ stores its data in a database. Instead of directly searching the
database when you use the Search functionality, instead you're searching in a
search engine's index. Using a search engine like
[Elasticsearch](https://www.elastic.co/products/elasticsearch) 
lets you convert searchable entities into *documents*. Adding, deleting, and
updating entities from the database triggers corresponding activity in the
search engine's index. When you enter a search term, nothing happens in the
database, because the documents are already indexed on the Elasticsearch server,
and that's where the search is executed.

It's worth adding the complexity of a search engine, rather than searching the
database directly, for performance reasons and for some of the features that
search engines provide, like algorithms that give you the ability to use
relevancy scores. For more technical details, see the [Introduction to
Search](/develop/tutorials/-/knowledge_base/7-1/introduction-to-liferay-search)
developer article.

## Elasticsearch [](id=elasticsearch)

The default search engine used by @product@ is Elasticsearch, which is backed by
the Lucene search library. There's an Elasticsearch server embedded in @product@
bundles, which is handy for testing and development purposes. Production
environments must install a separate, remote Elasticsearch server (or even
better, cluster of servers). For information on how to set up Elasticsearch for
@product@ in production, read the [deployment
guide](/discover/deployment/-/knowledge_base/7-1/installing-a-search-engine).

## Search Features [](id=product-search-features)

Searching is simple and straightforward. Find a search bar (there's one embedded
in every page by default), enter a term, and click *Enter*.

![Figure 2: There's a search bar embedded on all pages by default.](../../images/search-bar.png)

+$$$

**Search Modularization:** If you're upgrading from an earlier @product@ version, you'll
expect the embedded search bar to be the entry into the Search application.
However, the search functionality is modularized now, and the Search Bar is its
own widget now. See the 
[article on new search features](/discover/portal/-/knowledge_base/7-1/whats-new-with-search) 
for more information.

$$$

A results page is displayed. If there are hits to search engine documents,
you'll see them as search results in the right hand column. In the left hand
column you'll see search facets.

![Figure 3: Results are displayed in the Search Results portlet.](../../images/search-results.png)

The search bar, search results, and search facets make up three powerful
features in the search UI.

### Search Bar [](id=search-bar)

The search bar is simple: it's where you enter *search terms*. Search terms are
the text you send to the search engine to match against the documents in the
index. The documents that are returned are where this gets interesting.

### Search Results and Relevance [](id=search-results-and-relevance)

The search term is processed by an algorithm in the search engine, and search
results are returned to users in order of relevance. Relevance is determined by
a document's *score*, generated against the search query. The higher the score,
the more relevant a document is considered. The particular relevance algorithm
used is dependent on [algorithms provided by the search engine
(Elasticsearch by default)](https://www.elastic.co/guide/en/elasticsearch/guide/current/relevance-intro.html#relevance-intro).

### Search Facets [](id=search-facets)

Facets are a core feature of the Search functionality.

![Figure 4: *Site* and *Type* are two of the facet sets you'll encounter. They let you drill down to results that contain the search terms you entered.](../../images/search-faceted-search.png)

Facets allow users of the Search application to filter search results. Think of
facets as buckets that hold similar search results. You might want to see the
results in all the buckets, but after scanning the results, you might decide
that the results of just one bucket better represent what you're looking for. So
what facets are included in @product by default?

- Category
- Folder
- Site
- Tag
- Type
- User
- Modified
- Custom

You've probably used something similar on any number of sites. You search for an
item, are presented with a list of results, and a list of buckets you can click
to further drill down into the search results, without entering additional
search terms.  Search facets work the same way in @product@. Facets are, of
course,
[configurable](/discover/portal/-/knowledge_base/7-1/configuring-facets).
