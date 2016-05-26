# Enabling Search and Indexing for Guestbook Entries [](id=enabling-search-and-indexing-for-guestbook-entries)

To enable search in the Guestbook portlet for guestbook entries, you need to
follow these four steps:

1. Create an `EntryIndexer` class that extends Liferay's `BaseIndexer` abstract
   class.

2. Register the `EntryIndexer` in the guestbook-portlet project's
   `liferay-portlet.xml` file.

3. Update the `addEntry`, `updateEntry`, and `deleteEntry` methods of
   `EntryLocalServiceImpl` to invoke the guestbook entry indexer.

4.  Update the Guestbook portlet's user interface to display a search bar into
    which users can enter search terms and a JSP to display search results after
    the search terms are submitted.

In this section, you'll follow the first three steps: creating an indexer,
registering an indexer, and updating the service layer to invoke the indexer.
You'll follow the last step, updating the Guestbook portlet's user interface, in
the next section.

## Understanding Search and Indexing with Lucene [](id=understanding-search-and-indexing-with-lucene)

Under the hood, Liferay uses Lucene, a Java search library, to implement its
search and indexing functionality. Lucene works by converting searchable
entities into *documents*. Lucene documents are not documents in the ordinary
English sense of the word. Rather, they are Lucene constructs that correspond to
searchable entities. After you implement an indexer for guestbook entries, a
document will be created for each guestbook entry. When you implement the
guestbook entry indexer, you'll specify which guestbook entry fields should be
added to each guestbook entry document. All the guestbook entry documents are
added to an index. When a Lucene index is searched, a *hits* object is returned
that contains pointers to the documents that match the search query. Searching
for guestbook entries via an index is faster than searching for entities
in the database.

## Creating an Entry Indexer [](id=creating-an-entry-indexer)

Create a new package in your guestbook-project's `docroot/WEB-INF/src` folder
called `com.liferay.docs.guestbook.search`. In this package, create a new class
called `EntryIndexer` that extends
`com.liferay.portal.kernel.search.BaseIndexer`. Replace the default contents of
`EntryIndexer.java` with the following code:

    package com.liferay.docs.guestbook.search;

    import java.util.ArrayList;
    import java.util.Collection;
    import java.util.Locale;

    import javax.portlet.PortletURL;

    import com.liferay.docs.guestbook.model.Entry;
    import com.liferay.docs.guestbook.service.EntryLocalServiceUtil;
    import com.liferay.docs.guestbook.service.permission.GuestbookPermission;
    import com.liferay.docs.guestbook.service.persistence.EntryActionableDynamicQuery;
    import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
    import com.liferay.portal.kernel.dao.orm.DynamicQuery;
    import com.liferay.portal.kernel.exception.PortalException;
    import com.liferay.portal.kernel.exception.SystemException;
    import com.liferay.portal.kernel.search.BaseIndexer;
    import com.liferay.portal.kernel.search.Document;
    import com.liferay.portal.kernel.search.Field;
    import com.liferay.portal.kernel.search.SearchContext;
    import com.liferay.portal.kernel.search.SearchEngineUtil;
    import com.liferay.portal.kernel.search.Summary;
    import com.liferay.portal.kernel.util.GetterUtil;
    import com.liferay.portal.security.permission.ActionKeys;
    import com.liferay.portal.security.permission.PermissionChecker;

    public class EntryIndexer extends BaseIndexer {
            
            public static final String[] CLASS_NAMES = { Entry.class.getName() };
            
            public static final String PORTLET_ID = "guestbook-portlet";
            
            public EntryIndexer() {
                    
                    setPermissionAware(true);
            }

            @Override
            public String[] getClassNames() {
                    
                    return CLASS_NAMES;
            }

            @Override
            public String getPortletId() {
                    
                    return PORTLET_ID;
            }

            @Override
            public boolean hasPermission(PermissionChecker permissionChecker,
                            String entryClassName, long entryClassPK, String actionId)
                            throws Exception {

                    return GuestbookPermission.contains(permissionChecker, entryClassPK,
                                    ActionKeys.VIEW);
            }

            @Override
            protected void doDelete(Object obj) throws Exception {
                    
                    Entry entry = (Entry)obj;
                    
                    deleteDocument(entry.getCompanyId(), entry.getEntryId());
            }

            @Override
            protected Document doGetDocument(Object obj) throws Exception {
                    
                    Entry entry = (Entry)obj;

                    Document document = getBaseModelDocument(PORTLET_ID, entry);

                    document.addDate(Field.MODIFIED_DATE, entry.getModifiedDate());
                    document.addText(Field.CONTENT, entry.getMessage());
                    document.addText(Field.TITLE, entry.getName());
                    document.addText("email", entry.getEmail());
                    document.addKeyword(Field.GROUP_ID, getSiteGroupId(entry.getGroupId()));
                    document.addKeyword(Field.SCOPE_GROUP_ID, entry.getGroupId());
                    
                    return document;
            }

            @Override
            protected Summary doGetSummary(Document document, Locale locale,
                            String snippet, PortletURL portletURL) throws Exception {
                    
                    Summary summary = createSummary(document);

                    summary.setMaxContentLength(200);

                    return summary;
            }

            @Override
            protected void doReindex(Object obj) throws Exception {
                    
                    Entry entry = (Entry)obj;

                    Document document = getDocument(entry);

                    SearchEngineUtil.updateDocument(
                            getSearchEngineId(), entry.getCompanyId(), document);
            }

            @Override
            protected void doReindex(String className, long classPK) throws Exception {

                    Entry entry = EntryLocalServiceUtil.getEntry(classPK);

                    doReindex(entry);
            }

            @Override
            protected void doReindex(String[] ids) throws Exception {

                    long companyId = GetterUtil.getLong(ids[0]);

                    reindexEntries(companyId);
            }

            @Override
            protected String getPortletId(SearchContext searchContext) {
                    
                    return PORTLET_ID;
            }
            
            protected void reindexEntries(long companyId) throws PortalException,
                            SystemException {

                    final Collection<Document> documents = new ArrayList<Document>();

                    ActionableDynamicQuery actionableDynamicQuery = new EntryActionableDynamicQuery() {

                            @Override
                            protected void addCriteria(DynamicQuery dynamicQuery) {
                            }

                            @Override
                            protected void performAction(Object object) throws PortalException {
                                    Entry entry = (Entry) object;

                                    Document document = getDocument(entry);

                                    documents.add(document);
                            }

                    };

                    actionableDynamicQuery.setCompanyId(companyId);

                    actionableDynamicQuery.performActions();

                    SearchEngineUtil.updateDocuments(getSearchEngineId(), companyId,
                                    documents);
            }

    }

At the top of the `EntryIndexer` class, you define a `CLASS_NAMES` String array
constant and a `PORTLET_ID` string constant. Using these constants throughout
the class ensures that you aren't using the wrong class name or portlet ID
anywhere. All of Liferay's `*Indexer` classes follow the convention of declaring
`CLASS_NAMES` and `PORTLET_ID` constants and using them throughout the class.

Next comes the `EntryIndexer` constructor. This constructor calls
`setPermissionAware(true)` so that the indexer takes permissions into account
when returning search results. Without this call, the indexer would return *all*
guestbook entries that match a search query, regardless of the guestbook entry
permissions.

Next, since you're extending the `BaseIndexer` abstract class which, in turn,
implements the `Indexer` interface, you need to override or provide
implementations for the following methods:

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

`getClassNames` and `getPortletId` are easy to implement: you just return the
`CLASS_NAMES` and `PORTLET_ID` constants that you defined earlier.

`hasPermission` is also easy to implement: you call the `contains` method of the
`GuestbookPermission` helper class that you created in an earlier learning path.

`doDelete` is responsible for deleting the document that corresponds to the
object parameter. To implement it, you have to cast the object to a guestbook
entry and then call the `deleteDocument` method of `BaseIndexer`, passing the
entry's company ID and entry ID as parameters.

`doGetDocument` is the method where you specify which fields to add to the
Lucene document corresponding to a guestbook entry. First, you cast the object
parameter to a guestbook entry. Then you call the `getBaseModelDocument` method
of `BaseIndexer` to get the base document corresponding to the entry. Then you
add all the entry fields that you'd like to be indexed to the document. You have
to use the appropriate method for adding fields to be indexed. E.g., use
`document.addDate` to add a date field to the document, use `document.addText`
to add a text field to the document, etc.

+$$$

**Note:** By default, only three document fields are searched by Liferay's
`LuceneIndexSearcher`: `content`, `description`, and `title`. If you want other
fields to be searched, you can add a custom facet to your `SearchContext`.
Please see the [Faceted Search in Liferay]() tutorial for more details.

$$$

`doGetSummary` must also be implemented. It's easy to implement since you can
call the `createSummary` method of `BaseIndexer`. You also limit the maximum
size of the summary content using `summary.setMaxContentLength` since the
maximum content length is not set by the `Summary` constructor.

`doReindex` is overloaded and you need to provide implementations for each
overloaded method. The first `doReindex` method takes a single object argument.
You cast it to a guestbook entry and then retrieve the Lucene document
corresponding to the entry using the `getDocument` method of `BaseIndexer` which
takes an object argument. Then you invoke the `updateDocument` method of
`SearchEngineUtil` to update (reindex) the document.

The second `doReindex` method takes a `className` `String` argument and a
`classPK` `long` argument. To implement this method, you retrieve the guestbook
entry corresponding to the primary key by calling the `getEntry` method of
`EntryLocalServiceUtil`, passing in the `classPK` parameter. Once you've
retrieved the guestbook entry, you can invoke the first `doReindex` method that
you implemented, passing the entry as a parameter.

The third `doReindex` method takes an array of `String`s, `ids`, as an argument.
`ids` is a `String` array, so you need to convert individual IDs from `String`s to
`long`s. You retrieve the `companyId` from the first ID and pass it as an argument
to the `reindexEntries` helper method. You use an actionable dynamic query in
the `reindexEntries` helper method to retrieve all the guestbook entries in the
specified company. Each entry's Lucene document is added to a collection.
Finally, you call the `updateDocuments` method of `SearchEngineUtil`, passing
the document collection as an argument, to update (reindex) all the documents.

<!-- Unfortunately, actionable dynamic queries have not been explained yet in
any learning path, so you'll have to explain what that is here. -Rich -->

The last method you have to implement is the `getPortletId` method that takes a
`SearchContext` parameter. The portlet ID is always `guestbook-portlet`, so you
just return `PORTLET_ID`.

## Registering an Entry Indexer [](id=registering-an-entry-indexer)

Now that you've created an entry indexer, you need to register it with the
Guestbook portlet in `liferay-portlet.xml`. Add the following line to your
guestbook-portlet project's `docroot/WEB-INF/liferay-portlet.xml` file inside 
the `<portlet>` element for the Guestbook portlet, after the
`<icon>/icon.png</icon>` element:

    <indexer-class>com.liferay.docs.guestbook.search.EntryIndexer</indexer-class>

Liferay reads `liferay-portlet.xml` at deploy time. When it finds the
`<indexer-class>` element, it registers the specified indexer with your portlet.
Great! Now that you've registered your indexer, it's time to update the
Guestbook Entry service layer to use the indexer.

## Handling Indexing in the Entry Service Layer [](id=handling-indexing-in-the-entry-service-layer)

Whenever a guestbook entry is added, updated, or deleted, the guestbook entry
index should also be updated. To accomplish this, you'll update each of the
`addEntry`, `updateEntry`, and `deleteEntry` service methods for guestbook
entries. Open `EntryLocalServiceImpl` and add the following lines to the
`addEntry` method, after the call to `resourceLocalService.addResources` but
before the return statement:

    Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
                    Entry.class);

    indexer.reindex(entry);

Use *Ctrl-Shift-T* to add and organize imports. Here, you're retrieving an
instance of the `EntryIndexer` that you created earlier. How do you know that
you're obtaining an instance of the correct indexer? Since you registered
`EntryIndexer` in your guestbook-portlet project's `liferay-portlet.xml` file,
`EntryIndexer` is associated with the guestbook-portlet. Furthermore, you're
supplying `Entry.class` as an argument to the
`IndexerRegistryUtil.nullSafeGetIndexer` method. Your `EntryIndexer` class has a
`getClassNames` method which returns a `String` array containing
`Entry.class.getName()` as its only element. So
`IndexerRegistryUtil.nullSafeGetIndexer(Entry.class)` unambiguously specifies
your indexer.

Why do you call `IndexerRegistryUtil.nullSafeGetIndexer` instead of
`IndexerRegistryUtil.getIndexer`? `IndexerRegistryUtil.nullSafeGetIndexer`
returns a dummy indexer if no indexer matching the `Entry.class` argument could
be found. Returning a dummy indexer is safer than returning `null` since
returning `null` would throw exceptions that could render your portlet unusable.

Once you've obtained the correct indexer, calling `indexer.reindex(entry)`
updates the document in the index that corresponds to the updated guestbook
entry. Note that the `indexer.reindex(entry)` call occurs after the call to
`entryPersistence.update(entry)` since you want all of the entry's fields to
have been updated before the entry is indexed (or reindexed).

Next, add the following lines to the `updateEntry` method, after the call to
`resourceLocalServiceUtil.updateResources` but before the return statement:

    Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
                    Entry.class);

    indexer.reindex(entry);

These are the same two lines that you added to `addEntry`, and the rationale for
adding them is the same.

Finally, add the following lines to `deleteEntry`, after `entry =
deleteEntry(entryId)` but before the return statement:

    Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
                    Entry.class);

    indexer.delete(entry);

Here, you get the `EntryIndexer` the same way as in the `addEntry` and
`updateEntry` methods. The difference is that you want to remove the entry. So
you call `indexer.delete(entry)` instead of `indexer.reindex(entry)`.

That's all there is to updating your service layer to handle indexing! Since you
didn't add or remove any methods in `EntryLocalServiceImpl` or update any
method signatures, you don't need to re-run Service Builder. Your final step to
implementing search for Guestbook entries in the Guestbook portlet is to update
the user interface.
