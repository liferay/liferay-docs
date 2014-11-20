# Enabling Search and Indexing

To implement search and indexing functionality for a custom entity, you need to
follow these three steps:

1. Create an `*Indexer` class and register this class in your project's
   `liferay-portlet.xml` file.

2. Update your custom entity's service layer so that the index is updated upon
   the creation, modification, or deletion of an entity.

3. Provide a mechanism to actually perform a search. For example, you could
   create a JSP for entering search queries and another JSP for displaying the
   search results. Or you could simply configure Liferay's search portlet to
   search for your custom entities.

You'll explore each of these steps in the following sections.

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
figure out where the indexer class entry should be added.

## Implementing Indexing at the Service Layer
