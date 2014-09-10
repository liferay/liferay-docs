# Creating and Registering an Entry Indexer

To enable search in the Guestbook portlet for guestbook entries, you need to
follow these three steps:

1. Create a `GuestbookIndexer` class and register it in the guestbook-portlet
   project's `liferay-portlet.xml` file.

2. Update the `addEntry`, `updateEntry`, and `deleteEntry` methods of
   `GuestbookLocalServiceImpl` to invoke the guestbook entry indexer.

3. Update the Guestbook portlet's user interface to display a search bar into
   which users can enter search terms and to display search results when the
   search terms are submitted.

In this section, you'll follow the first step: creating and registering an
indexer. You'll follow the last two steps in subsequent sections.

## Creating an Entry Indexer

Create a new package in your guestbook-project's `docroot/WEB-INF/src` folder
called `com.liferay.docs.guestbook.search`. In this package, create a new class
called `EntryIndexer` that extends
`com.liferay.portal.kernel.search.BaseIndexer`. Replace the default contents of
`EntryIndexer.java` with the following:

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

## Registering an Entry Indexer

