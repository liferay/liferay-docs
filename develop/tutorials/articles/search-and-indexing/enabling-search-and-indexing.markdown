# Enabling Search and Indexing

Liferay's search and indexing functionality is provided by Apache Lucene, a
Java-based search library. To implement search and indexing functionality for a
custom entity in a data-driven application, you need to follow these three
steps:

1. Create an `*Indexer` class in your portlet project and register this class in
   your project's `liferay-portlet.xml` file.

2. Update your custom entity's service layer so that the index is updated upon
   the creation, modification, or deletion of an entity.

3. Provide a mechanism to actually perform a search. For example, you could
   create one JSP in your portlet project for entering search queries and
   another JSP for displaying the search results. Or you could simply configure
   Liferay's Search portlet to search for your custom entities.

You'll explore each of these steps in the following sections. Before proceeding,
make sure you're familiar with the following search and indexing terminology.

- Liferay's search index contains a collection of *documents*. These documents
  are not documents in the ordinary English sense of the word. Rather, they are
  Java objects that correspond to entities that have been saved to the database.

- A document contains a collection of *fields* and their values. A document's
  fields represent the metadata about each document. Some typical fields are
  *title*, *content*, *description*, *create date*, *modified date*, *tags*,
  etc.

- A field can either *single-valued* or *multi-valued*. A single-valued field
  can have only one term. A multi-valued field can have multiple terms. A *term*
  is a single value that can be searched which does not contain any whitespace
  characters.

- A *phrase* is a series of terms separated by spaces. The only way to use a
  phrase as a term in a search is to surround it with double quotes ("). 

- The results of a search are called *hits*. Hits are pointers to documents that
  match the search query.

## Creating and Registering an Indexer Class

The indexer class that you need to create is responsible for creating the Lucene
documents that represent your custom entities. When creating an indexer class,
you need to decide which fields your documents should contain and how they
should be populated. It's recommended that your indexer class extend
`com.liferay.portal.kernel.search.BaseIndexer` or at least implement
`com.liferay.portal.kernel.search.Indexer`. This makes it possible to aggregate
your custom entities with native portal entities (assets) and allows your custom
entities to use existing portal frameworks such as faceted search. If you want
to asset-enable your custom entities, creating an indexer for them is a
necessary step. When creating an indexer class, you can refer to the indexer
classes that correspond to native Liferay assets. These include `BlogsIndexer`,
`JournalArticleIndexer`, `WikiPageIndexer`, etc. For another example, you can
refer to the [Search and Indexing](https://dev.liferay.com/develop/learning-paths/-/knowledge_base/6-2/enabling-search-and-indexing-for-guestbook-entries)
learning path.

If your indexer class extends `com.liferay.portal.kernel.search.BaseIndexer` (as
recommended), which implements `com.liferay.portal.kernel.search.Indexer`,
you need to override or provide implementations for the following methods:

- `public String[] getClassNames()`
- `public String getPortletId()`
- `protected void doDelete(Object obj)`
- `protected Document doGetDocument(Object obj)`
- `protected Summary doGetSummary(Document document, Locale locale,
        String snippet, PortletURL portletURL)`
- `protected void doReindex(Object obj)`
- `protected void doReindex(String className, long classPK) `
- `protected void doReindex(String[] ids)`
- `protected String getPortletId(SearchContext searchContext)`

It's also recommended to define a `public static final String[] CLASS_NAMES`
constant and a `public static final String PORTLET_ID` constant. These constants
should contain the names of the entity classes and the name of the portlet with
which your indexer should be associated. Liferay's `*Indexer` classes follow
this convention. Using these constants in your plugin ensures that you are
consistently using the correct class names and portlet ID throughout your
indexing code. The search and indexing learning path referenced above explains
how to override implement the required methods. Liferay's `*Indexer` classes
also provide good examples.

Once you've written an indexer class for your entity, you need to register this
class with Liferay. To do so, open your portlet project's
`docroot/WEB-INF/liferay-portlet.xml` file and add an entry like this:

    <indexer-class>[fully qualified class name of the indexer class]</indexer-class>

E.g.,

    <indexer-class>com.liferay.portlet.blogs.util.BlogsIndexer</indexer-class>

If you're working in a newly generated `liferay-portlet.xml` file, the entry
should be added just below the `<icon>` element inside of the `<portlet>`
element that corresponds to the portlet to which you're adding the indexer. If
you're working in a `liferay-portlet.xml` file with lots of entries, please
refer to the [DTD](http://www.liferay.com/dtd/liferay-portlet-app_6_2_0.dtd) to
figure out where the indexer class entry should be added. Re-deploy your project
after you've added the `<indexer-class>` element to `liferay-portlet.xml` so
that Liferay registers your indexer with your portlet.

If you'd like to review the contents of your portal's indexer registry, you can
execute the follow Groovy script from Liferay's Script console. This script uses
Liferay's `IndexerRegistryUtil` class to retrieve a list of registered indexers.
Then it prints the associated portlet ID and classnames for each indexer. To
access the Script console, go to *Admin* &rarr; *Control Panel*, then click on
*Server Administration*, then *Script*. Select *Groovy* for the language, then
enter the following script.

+$$$

**Caution:** Before executing any script on a production Liferay instance, make
sure to test it locally first.

$$$

Review the script, then click *Execute*.

    import com.liferay.portal.kernel.search.IndexerRegistryUtil;
    import com.liferay.portal.kernel.search.Indexer;

    import java.util.List;

    List<Indexer> indexers = IndexerRegistryUtil.getIndexers();

    for (Indexer indexer : indexers) {
        System.out.println("portletId: " + indexer.getPortletId());

        String[] classnames = indexer.getClassNames();

        for (String classname : classnames) {
            System.out.println("classname: " + classname);
        }

        System.out.println();
    }

Check your console to find a list of portlet IDs and their associated indexer
class names. Check that your portlet and the indexer class that you registered
in `liferay-portlet.xml` appear in the list.

## Implementing Indexing at the Service Layer

If you're creating a data-driven application, you've presumably written code for
adding, updating, and deleting your custom entities. If you want the documents
corresponding to your custom entities in Liferay's Lucene index to accurately
reflect the entities themselves, you need to make sure that you're instructing
your indexer to reindex any newly added or updated entities. When an entity is
deleted, you need to remove the corresponding document from the index. To obtain
an instance of your indexer class, use Liferay's `IndexerRegistryUtil` class.
This class includes a `getIndexer(...)` method as well as a
`nullSafeGetIndexer(...)` method. Both of these methods can take either a class
argument (e.g. `MyEntity.class`) or a string representing the class name (e.g.
`MyEntity`). If you use `getIndexer` and no indexer in the registry matches the
argument, `null` is returned. However, if you use `nullSafeGetIndexer` and no
indexer matches the argument, a dummy indexer is returned. Returning a dummy
indexer is safer than returning `null` since returning `null` might throw
exceptions that could render your portlet unusable.

Once you've obtained the indexer that corresponds to your entity, you need to
invoke the appropriate indexing operation. Whenever a new entity is added, a
corresponding document should be added to the index. Whenever an existing entity
is updated, the corresponding document should be updated. Both of these tasks,
indexing and reindexing, can be accomplished by invoking the `reindex` method of
your indexer. This method is overloaded. You can simply provide an object
argument consisting of the entity which needs to be indexed or reindexed. Or you
can provide the entity's class name and primary key. When an entity is deleted,
its corresponding document should be removed from the index. This task can be
accomplished by invoking the `delete` method of your indexer.  Like `reindex`,
`delete` is an overloaded method which can take either an object argument or the
entity's class name and primary key.

If you're using Service Builder for your portlet project's service layer, you
know that custom business logic should be added to your entity's
`[Entity]LocalServiceImpl` class. If you've integrated your application with
Liferay's permissions system, you probably have `add[Entity]`, `update[Entity]`,
and `delete[Entity]` methods which invoke various methods of
`resourceLocalService` to add, update, and delete entity resources. If you're
going to asset-enable your entity, you'll have to invoke various methods of
`assetEntryLocalService` and `assetLinkLocalService` to add, update, and delete
the asset entries that correspond to your entity and any associated asset links
(related assets). Just as you add, update, and delete permissions resources and
asset entries in the `add[Entity]`, `update[Entity]`, and `delete[Entity]`
methods of `[Entity]LocalServiceImpl`, you should also add, update, and delete
your entity's Lucene documents in `[Entity]LocalServiceImpl`. To do so, add the
following lines inside of your `add[Entity]` and `update{Entity]` methods:

    Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer([Entity].class);

    indexer.reindex(entry);

And add the following lines inside of your `delete[Entity]` method:

    Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer([Entity].class);

    indexer.delete(entry);

Of course, in both of the above snippets, replace `[Entity]` with the actual
name of your entity. Save your `[Entity]LocalServiceImpl.java` file and redeploy
your portlet project.

Use your portlet to add and update some custom entities to test your indexer. If
it's working correctly, your indexer should add documents to Liferay's index for
each entity that's added or updated. By default, Liferay's index data is stored
in the `[Liferay Home]/data/lucene` folder. This folder contains sub-folders
corresponding to each of your portal instances. For instance, if your default
portal instance has a company ID of `10154`, then there should be a folder
called `10154` in your `[Liferay Home]/data/lucene`. The contents of the `10154`
are in binary format; they're not human-readable. However, you can use a
third-party tool, such as Luke, to browse the Lucene indexes.

Luke is a development tool which allows you to browse and modify existing Lucene
indexes. It is provided as free and open-source software. You can obtain it from
Google Code here:
[https://code.google.com/p/luke](https://code.google.com/p/luke). Download the
the `.jar` file. To start Luke, open a command line or terminal, navigate to the
directory which contains the `.jar` file and run the following command:

    java -jar lukeall-3.5.0.jar

If you have a newer version of Luke, replace the version above with your
version. Once Luke is running, you want to check Liferay's Lucene indexes to
make sure that your custom entities have been indexed. Click *File* &rarr; *Open
Lucene Index* and enter the path to the folder of the portal instance whose
index you want to open. E.g., if your portal instance's company is `10154`,
enter the following path:

    [Liferay Home]/data/lucene/10154

Check the following boxes to make sure that you can open the indexes and that
you won't accidentally modify the indexes as you're browsing:

- Open in Read-Only mode
- Force unlock, if locked

Then click *OK*. If you click on Luke's Documents tab, you can browse all of the
indexed documents by document number. If you click on Luke's Search tab, you can
enter a search query. Click on *Search* and Luke will display a list of all
matching documents. Use Luke to check that the custom entities you added via
your portlet appear in Liferay's Lucene index. If you need to learn Lucene's
search query syntax, please refer to Lucene's
[documentation](http://lucene.apache.org/core/3_5_0/queryparsersyntax.html).
Note: Liferay 6.2 uses Lucene 3.5.0. Another helpful reference is
[http://www.lucenetutorial.com/lucene-query-syntax.html](http://www.lucenetutorial.com/lucene-query-syntax.html).

## Providing a Search Mechanism

Now that your indexer has been registered and the index is updated whenever an
entity is added, updated, or deleted, it's time to create a search mechanism.
The easiest way to allow users to search for your custom entities is via
Liferay's Search portlet. By default, Liferay's Search portlet only allows
searching for out-of-the-box Liferay assets:

- Users
- Bookmarks
- Bookmark folders
- Blog posts
- Documents and Media files
- Documents and Media folders
- Web Content files
- Web Content folders
- Message Board Messages
- Wiki pages

It's easy to configure the Search portlet to search for additional or custom
asset types. To do so, add the Search portlet to a page and open the
Configuration window. On the Setup tab, click on *Advanced* to reveal the Search
Configuration text area. Search through the JSON configuration for the following
block of code:

    "values": [
        "com.liferay.portal.model.User",
        "com.liferay.portlet.bookmarks.model.BookmarksEntry",
        "com.liferay.portlet.bookmarks.model.BookmarksFolder",
        "com.liferay.portlet.blogs.model.BlogsEntry",
        "com.liferay.portlet.documentlibrary.model.DLFileEntry",
        "com.liferay.portlet.documentlibrary.model.DLFolder",
        "com.liferay.portlet.journal.model.JournalArticle",
        "com.liferay.portlet.journal.model.JournalFolder",
        "com.liferay.portlet.messageboards.model.MBMessage",
        "com.liferay.portlet.wiki.model.WikiPage"
    ],

Append your entity to this list so that it looks like this:

    "values": [
        "com.liferay.portal.model.User",
        "com.liferay.portlet.bookmarks.model.BookmarksEntry",
        "com.liferay.portlet.bookmarks.model.BookmarksFolder",
        "com.liferay.portlet.blogs.model.BlogsEntry",
        "com.liferay.portlet.documentlibrary.model.DLFileEntry",
        "com.liferay.portlet.documentlibrary.model.DLFolder",
        "com.liferay.portlet.journal.model.JournalArticle",
        "com.liferay.portlet.journal.model.JournalFolder",
        "com.liferay.portlet.messageboards.model.MBMessage",
        "com.liferay.portlet.wiki.model.WikiPage",
        "your.package.path.YourCustomEntity"
    ],

Of course, replace the package path and entity name with your
package path and entity name. Then click *Save*. You can add any number of
custom entities to the Search portlet's configuration this way. For more
information on the JSON configuration of the Search portlet, please refer to the
Search portlet's documentation:
[Searching for Content in Liferay](https://dev.liferay.com/discover/portal/-/knowledge_base/6-2/searching-for-content-in-liferay).

However, you don't have to use Liferay's Search portlet. In this section, you'll
learn how to use Liferay's API to create a custom search mechanism in your own
portlet. You can create one JSP for entering a search query and another JSP for
displaying the search results. When implementing search and indexing in a custom
portlet, the following Liferay classes are important:

- `com.liferay.portal.kernel.search.SearchContext`
- `com.liferay.portal.kernel.search.SearchContextFactory`
- `com.liferay.portal.kernel.search.Indexer`
- `com.liferay.portal.kernel.search.IndexerRegistryUtil`
- `com.liferay.portal.kernel.search.BaseIndexer`
- `com.liferay.portal.kernel.search.SearchEngineUtil`
- `com.liferay.portal.kernel.search.Hits`
- `com.liferay.portal.kernel.search.Document`

To execute a search query in Liferay, you need a `SearchContext` object. The
search context provides such details as the company instance to search, the user
invoking the search, the locale, the timezone, etc. Since this class has a wide
variety of context properties to deal with, the most effective way to get an
instance of it is to call the `getInstance(HttpServletRequest request)` method
of `SearchContextFactory` like this:

    SearchContext searchContext = SearchContextFactory.getInstance(request);

Once you have a `SearchContext` object, you can populate various values such as
the keywords to search for, the pagination type, the start and end values, etc.
For example, you could use the following:

    searchContext.setKeywords(keywords);
    searchContext.setAttribute("paginationType", "more");
    searchContext.setStart(0);
    searchContext.setEnd(10);

The keywords value is the most important search context attribute since it
represents the term or phrase for which you're searching. To find additional
`SearchContext` attributes, please refer to the
[Javadocs](http://docs.liferay.com/portal/6.2/javadocs).

Earlier, you saw how to use `IndexerRegistryUtil` to obtain an instance of your
indexer. You can use any of the following four methods:

- `getIndexer(Class<?> clazz)`
- `getIndexer(String className)`
- `nullSafeGetIndexer(Class<?> clazz)`
- `nullSafeGetIndexer(String className)`

Remember that when you deployed your plugin project, Liferay registered your
indexer. When you invoke one of the above methods, Liferay returns an instance
of the indexer class that corresponds to the class or class name argument that
you supplied. Also, remember that your indexer class should extend
`com.liferay.portal.kernel.search.BaseIndexer` or at least implement
`com.liferay.portal.kernel.search.Indexer`. The `Indexer` interface defines a
`search(SearchContext searchContext)` method that returns a `Hits` object. The
`BaseIndexer` abstract class provides an implementation of this method that
invokes `SearchEngineUtil.search(...)`.

While it's possible to use a specific indexer to perform a search, it's also
possible to perform a search using `SearchEngineUtil.search(...)` directly.
`SearchEngineUtil` handles all the intricacies of the search engine
implementation. All traffic to and from the search engine implementation passes
through this class. If you're debugging a problem with your application's search
and indexing functionality, can be beneficial to enable debug level logging on
this class.

The result of invoking either an indexer's `search(SearchContext searchContext)`
method or of invoking `SearchEngineUtil.search(...)` directly is a `Hits`
object. A `Hits` object contains the Lucene documents that match the search
query. The Lucene documents, which are `Document` objects, can be retrieved from
the `Hits` object in either array or list form. For example, suppose you have an
indexer for an entity called `MyEntity`. Suppose further that you have a search
context object called `searchContext` that contains the `keywords` string
containing that phrase for which you want to search. You could invoke a search
to obtain a `Hits` object like this:

    Indexer indexer = IndexerRegistryUtil.getIndexer("MyEntity");

    try {
        Hits hits = indexer.search(searchContext);
    }
    catch (SearchException se) {
        // handle search exception
    }

If you want to use `SearchEngineUtil.search(...)` directly (instead of using an
indexer to search), you need to create your own search query and make sure that
your search context is configured appropriately. Indexers in Liferay are
associated with specific entities. When use use an indexer to search, only the
specified entities are searched. If you want to use
`SearchEngineUtil.search(...)` to search directly, you can explicitly specify
the entities whose documents you want to search by invoking the
`searchContext.setEntryClassNames` method of your search context. You can also
specify the portal instance and site or sites within which you want to search by
invoking the `searchContext.setCompanyId` and `searchContext.setGroupIds`
methods of your search context.

`SearchEngineUtil.search(...)` is an overloaded method. The easiest form of
this method to use is `SearchEngineUtil.search(SearchContext searchContext,
Query query)`. To use this method, you need to construct your own query. Liferay
provides the base `Query` interface as well as several others which extend it,
including the following:

- `BooleanQuery`
- `TermRangeQuery`
- `TermQuery`

Liferay also provides several query implementation and factory classes. Use
Liferay's query factory classes to instantiate query implementation classes. For
example, suppose you want to use Liferay's search engine to search for indexed
documents containing the term *liferay* in the *title* field. To do so, you
could use the following code:

    TermQuery termQuery = TermQueryFactoryUtil.create(searchContext, "title", "liferay");

    try {
        Hits hits = SearchEngineUtil.search(searchContext, termQuery);
    }
    catch (SearchException se) {
        // handle search exception
    }

If you want to search for indexed documents with field values within a certain
range, you can use term range queries. For example, suppose you wanted to
construct a query that searches for indexed documents that were modified on a
certain day, such as December 4, 2014. To construct such a query, you could use
the following code:

    TermRangeQuery termRangeQuery = TermRangeQueryFactoryUtil.create(searchContext, "modified", "201412040000000", "201412050000000", true, true);

    try {
        Hits hits = SearchEngineUtil.search(searchContext, termRangeQuery);
    }
    catch (SearchException se) {
        // handle search exception
    }

To programatically construct more complex queries, you can use boolean queries.
For example, suppose you wanted construct a query that searches for indexed
documents containing the term *liferay* in the *title* field but not *lucene* in
the *description* field and that were modified on December 4, 2014. To construct
such a query, you could use the following code:

    TermQuery termQuery1 = TermQueryFactoryUtil.create(searchContext, "title", "liferay");
    TermRangeQuery termRangeQuery = TermRangeQueryFactoryUtil.create(searchContext, "modified", "201412040000000", "201412050000000", true, false);
    TermQuery termQuery2 = TermQueryFactoryUtil.create(searchContext, "description", "lucene");

    BooleanQuery booleanQuery = BooleanQueryFactoryUtil.create(searchContext);

    booleanQuery.add(termQuery1, BooleanClauseOccur.MUST);
    booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
    booleanQuery.add(termQuery2, BooleanClauseOccur.MUST_NOT);

    try {
        Hits hits = SearchEngineUtil.search(searchContext, booleanQuery);
    }
    catch (SearchException se) {
        // handle search exception
    }

In addition to `BooleanQuery`, `TermRangeQuery`, and `TermQuery`, Liferay
provides a `StringQuery` implementation class. This kind of query implmentation
allows you to construct custom queries using Lucene's query syntax. Please refer
to the
[Faceted Search and Customized Search Filtering](/develop/tutorials/-/knowledge_base/6-2/faceted-search-and-customized-search-filtering)
tutorial for an example. Once you've performed a search and have obtained a hits
object, you can retrieve the corresponding documents in array form like this:

    Document[] docs = hits.getDocs();

Or you can retrieve the documents in list form:

    List<Document> docs = hits.toList();

To display the search results, you typically have to iterate over the array or
list of documents. Each document is essentially a hash map of the indexed fields
and their values. Please refer to the
[Search and Indexing Learning Path](https://dev.liferay.com/develop/learning-paths/-/knowledge_base/6-2/enabling-search-and-indexing)
for an explanation of how to create a portlet user interface that facilitates
searching and viewing search results. In the learning path example, a search bar
is added to the JSP that renders the main portlet view. When a search query is
submitted, the phrase entered by the user is submitted as a `keywords` string to
the JSP that renders the search results. The JSP that renders the search results
contains the code that creates and populates a search context, obtains an
indexer, uses the indexer to search, and retrieves the entities that correspond
to the hits resulting from the search. However, this code does not have to live
in the JSP. You could pull all of this logic out of the JSP and into a portlet
action method or into a portlet service method that's invoked by a portlet
action method.

In this tutorial, you've learned how to create an register an indexer for a
custom entity in your portlet project. You've learned how to update your service
layer so that your indexer is invoked whenever an add, update, or delete
operation is performed on a custom entity. And you've seen how to use Liferay's
search API to create a search context and how to actually perform a search and
obtain a list of search results. To explore more features of Liferay's search
API, please see the tutorial on Faceted Search and Customized Search Filtering.

## Related Topics

[Faceted Search and Customized Search Filtering](/develop/tutorials/-/knowledge_base/6-2/faceted-search-and-customized-search-filtering)
