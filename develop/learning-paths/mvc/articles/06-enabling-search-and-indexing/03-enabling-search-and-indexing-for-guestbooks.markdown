# Enabling Search and Indexing for Guestbooks [](id=enabling-search-and-indexing-for-guestbooks)

To enable search and indexing for guestbook entities, you need to follow these
three steps:

1. Create an `GuestbookIndexer` class that extends Liferay's `BaseIndexer`
   abstract class.

2. Register the `GuestbookIndexer` in the guestbook-portlet project's
   `liferay-portlet.xml` file.

3. Update the `addGuestbook`, `updateGuestbook`, and `deleteGuestbook` methods
   of `GuestbookLocalServiceImpl` to invoke the guestbook indexer.

In this section, you'll follow these three steps. Notice that you're not
updating the user interface of either the Guestbook portlet or the Guestbook
Admin portlet. You're only adding back-end functionality that will allow
guestbooks to take advantage of Liferay's asset framework. You'll explore the
sets of functionality provided by Liferay's asset framework in the next learning
path, and you'll update your guestbook-portlet project to use some of them.

## Creating a Guestbook Indexer

In the `com.liferay.docs.guestbook.search` package of your guestbook-project's
`docroot/WEB-INF/src` folder, create a new class called `GuestbookIndexer` that
extends `com.liferay.portal.kernel.search.BaseIndexer`. Replace the default
contents of `GuestbookIndexer.java` with the following code:

    package com.liferay.docs.guestbook.search;

    import java.util.ArrayList;
    import java.util.Collection;
    import java.util.Locale;

    import javax.portlet.PortletURL;

    import com.liferay.docs.guestbook.model.Guestbook;
    import com.liferay.docs.guestbook.service.GuestbookLocalServiceUtil;
    import com.liferay.docs.guestbook.service.permission.GuestbookPermission;
    import com.liferay.docs.guestbook.service.persistence.GuestbookActionableDynamicQuery;
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

    public class GuestbookIndexer extends BaseIndexer {
            
            public static final String[] CLASS_NAMES = { Guestbook.class.getName() };
            
            public static final String PORTLET_ID = "guestbook-portlet";
            
            public GuestbookIndexer() {
                    
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
                            String guestbookClassName, long guestbookClassPK, String actionId)
                            throws Exception {

                    return GuestbookPermission.contains(permissionChecker, guestbookClassPK,
                                    ActionKeys.VIEW);
            }

            @Override
            protected void doDelete(Object obj) throws Exception {
                    
                    Guestbook guestbook = (Guestbook)obj;
                    
                    deleteDocument(guestbook.getCompanyId(), guestbook.getGuestbookId());
            }

            @Override
            protected Document doGetDocument(Object obj) throws Exception {
                    
                    Guestbook guestbook = (Guestbook)obj;

                    Document document = getBaseModelDocument(PORTLET_ID, guestbook);

                    document.addDate(Field.MODIFIED_DATE, guestbook.getModifiedDate());
                    document.addText(Field.TITLE, guestbook.getName());
                    document.addKeyword(Field.GROUP_ID, getSiteGroupId(guestbook.getGroupId()));
                    document.addKeyword(Field.SCOPE_GROUP_ID, guestbook.getGroupId());
                    
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
                    
                    Guestbook guestbook = (Guestbook)obj;

                    Document document = getDocument(guestbook);

                    SearchEngineUtil.updateDocument(
                            getSearchEngineId(), guestbook.getCompanyId(), document);
            }

            @Override
            protected void doReindex(String className, long classPK) throws Exception {

                    Guestbook guestbook = GuestbookLocalServiceUtil.getGuestbook(classPK);

                    doReindex(guestbook);
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

                    ActionableDynamicQuery actionableDynamicQuery = new GuestbookActionableDynamicQuery() {

                            @Override
                            protected void addCriteria(DynamicQuery dynamicQuery) {
                            }

                            @Override
                            protected void performAction(Object object) throws PortalException {
                                    Guestbook guestbook = (Guestbook) object;

                                    Document document = getDocument(guestbook);

                                    documents.add(document);
                            }

                    };

                    actionableDynamicQuery.setCompanyId(companyId);

                    actionableDynamicQuery.performActions();

                    SearchEngineUtil.updateDocuments(getSearchEngineId(), companyId,
                                    documents);
            }

    }

This class is nearly identical to the `EntryIndexer` class that you created
earlier. Note that the main searchable field for guestbooks is the guestbook
name, as evidenced by the following line in the `doGetDocument` method:

    document.addText(Field.TITLE, guestbook.getName());

Since the guestbook name is stored in the title field of a guestbook document,
it's searchable by default. Remember that Liferay's `LuceneIndexSearcher` only
searches the title, content, and description fields.

For guestbook entries, we indexed the entry message, entry name (the name
entered by the user who created the entry), and entry email. However, only the
entry name and entry user fields were searchable by default:

    document.addText(Field.CONTENT, entry.getMessage());
    document.addText(Field.TITLE, entry.getName());
    document.addText("email", entry.getEmail());

The rest of the `GuestbookIndexer` class is very similar to the `EntryIndexer`
class. If you have questions about any of the methods in the `GuestbookIndexer`
class, please refer to the explanation for the `EntryIndexer` class that
appeared earlier in this learning path.

## Registering a Guestbook Indexer

Next, you need to register your guestbook indexer with the Guestbook Admin
portlet. Add the following line to your guestbook-portlet project's
`docroot/WEB-INF/liferay-portlet.xml` file inside the `<portlet>` element for
the Guestbook Admin portlet, after the `<icon>/icon.png</icon>` element:

    <indexer-class>com.liferay.docs.guestbook.search.GuestbookIndexer</indexer-class>

The next time you deploy your project, Liferay will register your indexer with
the Guestbook Admin portlet. Now you need to update the Guestbook service layer
to use the Guestbook indexer.

## Handling Indexing in the Guestbook Service Layer

You need to make sure that whenever a guestbook is added, updated, or deleted,
its index is updated accordingly. To accomplish this, you need to update each of
the `addGuestbook`, `updateGuestbook`, and `deleteGuestbook` service methods for
guestbooks. Open `GuestbookLocalServiceImpl` and add the following lines to the
`addGuestbook` method after the call to `resourceLocalService.addResources` but
before the return statement:

    Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
                    Guestbook.class);

    indexer.reindex(guestbook);

Next, add the same two lines to the `updateGuestbook` method, after the call to
`resourceLocalServiceUtil.updateResources` but before the return statement.

Finally, add the following two lines to the `deleteGuestbook` method after the
call to `resourceLocalService.deleteResource` but before the return statement:

    Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
                    Guestbook.class);

    indexer.delete(guestbook);

Use *Ctrl-Shift-T* to add and organize the required imports. These updates are
virtually identical to the ones you made to the Guestbook entry service layer.
If you have questions about them, please refer to the explanation for the
Guestbook entry service layer update that appeared earlier in this learning
path.

## Registering Indexers To Different Portlets In The Same Project

When you wrote the `EntryIndexer` and `GuestbookIndexer` classes, you specified
a portlet ID for each indexer. It's a Liferay convention to specify portlet IDs
just once in a class called `PortletKeys` and to reference the portlet IDs
defined in this class wherever else they are required in the project.

Create a new class called `PortletKeys` that extends
`com.liferay.portal.util.PortletKeys` in the `com.liferay.docs.guestbook.util`
package in the `docroot/WEB-INF/src` folder. Replace the contents of the newly
created file with the following code:

    package com.liferay.docs.guestbook.util;

    public class PortletKeys extends com.liferay.portal.util.PortletKeys {

            public static final String GUESTBOOK = "guestbook_WAR_guestbookportlet";

            public static final String GUESTBOOK_ADMIN = "guestbookadmin_WAR_guestbookportlet";

    }

Now you need to update your indexer classes to use these portlet IDs. Open
`EntryIndexer.java` and find the following line:

    public static final String PORTLET_ID = "guestbook-portlet";

Replace it with the following line:

    public static final String PORTLET_ID = PortletKeys.GUESTBOOK;

Next, open `GuestbookIndexer.java` and find the following line:

    public static final String PORTLET_ID = "guestbook-portlet";

Replace it with the following line:

    public static final String PORTLET_ID = PortletKeys.GUESTBOOK_ADMIN;

Now when the guestbook-portlet project is deployed, your indexers will be
registered to distinct portlets (the Guestbook portlet and the Guestbook Admin
portlet) in the guestbook-portlet project.

Let's recap what you've accomplished so far. Whenever guestbooks are added,
updated, or deleted, their indexes are updated. With your indexers in place,
your guestbook and guestbook entry entities are all set to take advantage of
Liferay's asset framework. We'll explore Liferay's asset framework in the next
learning path.

## Next Steps

[Asset-Enabling Custom Entities](/develop/learning-paths/-/knowledge_base/6-2/asset-enabling-custom-entities)
