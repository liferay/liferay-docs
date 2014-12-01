# Enabling Search and Indexing

To implement search and indexing functionality for a custom entity in a
data-driven application, you need to follow these three steps:

1. Create an `*Indexer` class in your portlet project and register this class in
   your project's `liferay-portlet.xml` file.

2. Update your custom entity's service layer so that the index is updated upon
   the creation, modification, or deletion of an entity.

3. Provide a mechanism to actually perform a search. For example, you could
   create one JSP in your portlet project for entering search queries and
   another JSP for displaying the search results. Or you could simply configure
   Liferay's Search portlet to search for your custom entities.

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
figure out where the indexer class entry should be added. Re-deploy your project
after you've added the `<indexer-class>` element to `liferay-portlet.xml` so
that Liferay registers your indexer with your portlet.

If you'd like to review the contents of your portal's indexer registry, you can
execute the follow Grovvy script from Liferay's Script console. This script uses
Liferay's `IndexerRegistryUtil` class to retrieve a list of registered indexers.
Then it prints the associated portlet ID and classnames for each indexer. To
access the Script console, go to *Admin* &rarr; *Control Panel*, then click on
*Server Administration*, then *Script*. Select *Groovy* for the language, then
enter the following script. Caution! Before executing any script on a production
Liferay instance, make sure to test it first locally. Review the script, then
click *Execute*.

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
name of your entity.

## Providing a Search Mechanism
