# Creating an Entry Indexer [](id=creating-an-entry-indexer)

Follow these steps to create the entry indexer:

1.  Create a new package in your guestbook-api module project's `src/main/java` 
    folder called `com.liferay.docs.guestbook.search`. In this package, create a 
    new class called `EntryIndexer` that extends 
    `com.liferay.portal.kernel.search.BaseIndexer` and includes the following 
    imports:

        package com.liferay.docs.guestbook.search;
    <!-- perhaps move the imports to last step and say resolve all imports... -->
        import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
        import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
        import com.liferay.portal.kernel.exception.PortalException;
        import com.liferay.portal.kernel.log.Log;
        import com.liferay.portal.kernel.log.LogFactoryUtil;
        import com.liferay.portal.kernel.search.BaseIndexer;
        import com.liferay.portal.kernel.search.Document;
        import com.liferay.portal.kernel.search.Field;
        import com.liferay.portal.kernel.search.IndexWriterHelperUtil;
        import com.liferay.portal.kernel.search.Indexer;
        import com.liferay.portal.kernel.search.SearchContext;
        import com.liferay.portal.kernel.search.Summary;
        import com.liferay.portal.kernel.search.filter.BooleanFilter;
        import com.liferay.portal.kernel.security.permission.ActionKeys;
        import com.liferay.portal.kernel.security.permission.PermissionChecker;
        import com.liferay.portal.kernel.util.GetterUtil;
        import com.liferay.portal.kernel.util.HtmlUtil;
        import com.liferay.portal.kernel.util.StringPool;
        import com.liferay.docs.guestbook.model.Entry;
        import com.liferay.docs.guestbook.service.EntryLocalService;
        import java.util.Locale;
        import javax.portlet.PortletRequest;
        import javax.portlet.PortletResponse;
        import org.osgi.service.component.annotations.Component;
        import org.osgi.service.component.annotations.Reference;

        @Component(
          immediate = true,
          service = Indexer.class)
        public class EntryIndexer extends BaseIndexer<Entry> {
          
        }

2.  Next add the `EntryIndexer` constructor to the class. This constructor calls 
    `setPermissionAware(true)` so that the indexer takes permissions into 
    account when returning search results. Without this call, the indexer would 
    return *all* guestbook entries that match a search query, regardless of the 
    guestbook entry permissions:

        public EntryIndexer() {
          setDefaultSelectedFieldNames(
            Field.COMPANY_ID, Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK,
            Field.UID, Field.DESCRIPTION);
          setDefaultSelectedLocalizedFieldNames(Field.TITLE);
          setFilterSearch(true);
          setPermissionAware(true);
        }
    
    Next, since you're extending the `BaseIndexer` abstract class which, in turn,
    implements the `Indexer` interface, you need to override or provide 
    implementations for the following methods.

3.  Override the `getClassName` method. This returns the `CLASS_NAME` constants. 
    You'll define this at the bottom of the class in a later step.
      
      @Override
      public String getClassName() {
        return CLASS_NAME;
      }

4.  Add the `hasPermission` method override next. This calls the `contains` 
    method of the `GuestbookPermission` helper class that you created in an 
    earlier Learning Path:
      
      @Override
      public boolean hasPermission(
        PermissionChecker permissionChecker, String entryClassName,
        long entryClassPK, String actionId)
          throws Exception {
        Entry entry =
            _entryLocalService.getEntry(entryClassPK);
        return permissionChecker.hasPermission(entry.getGroupId(), Entry.class.getName(),
            entry.getEntryId(), ActionKeys.VIEW);
      }
      
5.  Next, override the `postProcessContextBooleanFilter` method:
      
      @Override
      public void postProcessContextBooleanFilter(
        BooleanFilter contextBooleanFilter, SearchContext searchContext)
          throws Exception {
        addStatus(contextBooleanFilter, searchContext);
      }
      
6.  Override the `doDelete()` method. `doDelete` is responsible for deleting the 
    document that corresponds to the object parameter. To implement it, you have 
    to cast the object to a guestbook entry and then call the `deleteDocument` 
    method of `BaseIndexer`, passing the entry's company ID and entry ID as 
    parameters:

      @Override
      protected void doDelete(Entry entry)
        throws Exception {
        deleteDocument(entry.getCompanyId(), entry.getEntryId());
      }
      
7.  Add the `doGetDocument` method override. The `doGetDocument` method 
    specifies which fields to add to the Lucene document corresponding to a 
    guestbook entry. First, you cast the object parameter to a guestbook entry. 
    Then you call the `getBaseModelDocument` method of `BaseIndexer` to get the 
    base document corresponding to the entry. Then you add all the entry fields 
    that you'd like to be indexed to the document. You have to use the 
    appropriate method for adding fields to be indexed. E.g., use 
    `document.addDate` to add a date field to the document, use 
    `document.addText` to add a text field to the document, etc:
      
        @Override
        protected Document doGetDocument(Entry entry)
          throws Exception {
          Document document = getBaseModelDocument(CLASS_NAME, entry);
              document.addDate(Field.MODIFIED_DATE, entry.getModifiedDate());
              document.addText(Field.CONTENT, entry.getMessage());
              document.addText(Field.TITLE, entry.getName());
              document.addText("email", entry.getEmail());
              document.addKeyword(Field.GROUP_ID, getSiteGroupId(entry.getGroupId()));
              document.addKeyword(Field.SCOPE_GROUP_ID, entry.getGroupId());

              return document;
        }

        +$$$

        **Note:** By default, only three document fields are searched by Liferay's
        `LuceneIndexSearcher`: `content`, `description`, and `title`. If you want other
        fields to be searched, you can add a custom facet to your `SearchContext`.
        Please see the [Faceted Search in Liferay](/develop/tutorials/-/knowledge_base/6-2/faceted-search-and-customized-search-filtering) 
        tutorial for more details.

        $$$

8.  Override `doGetSummary`. It's easy to implement since you can call the 
    `createSummary` method of `BaseIndexer`. You also limit the maximum size of 
    the summary content using `summary.setMaxContentLength` since the maximum 
    content length is not set by the `Summary` constructor:
      
        @Override
        protected Summary doGetSummary(
          Document document, Locale locale, String snippet,
          PortletRequest portletRequest, PortletResponse portletResponse)
            throws Exception {
          
              Summary summary = createSummary(document);

              summary.setMaxContentLength(200);

              return summary;
        }
      
9.  Next you have to override the `doReindex` method. This method is overloaded 
    and you need to provide implementations for each overloaded method. The 
    first `doReindex` method takes a single object argument. You cast it to a 
    guestbook entry and then retrieve the Lucene document corresponding to the 
    entry using the `getDocument` method of `BaseIndexer` which takes an object 
    argument. Then you invoke the `updateDocument` method of `SearchEngineUtil` 
    to update (reindex) the document:
      
        @Override
        protected void doReindex(Entry entry)
          throws Exception {
          Document document = getDocument(entry);
          IndexWriterHelperUtil.updateDocument(getSearchEngineId(),
            entry.getCompanyId(), document, isCommitImmediately());
        }

10.  Next add the second `doReindex` method. This takes a `className` `String` 
    argument and a `classPK` `long` argument. To implement this method, you 
    retrieve the guestbook entry corresponding to the primary key by calling the 
    `getEntry` method of `EntryLocalServiceUtil`, passing in the `classPK` 
    parameter. Once you've retrieved the guestbook entry, you can invoke the 
    first `doReindex` method that you implemented, passing the entry as a 
    parameter:
      
        @Override
        protected void doReindex(String className, long classPK)
          throws Exception {
          Entry entry = _entryLocalService.getEntry(classPK);
          doReindex(entry);
        }
      
11.  Add the final third `doReindex` method. The third `doReindex` method takes 
    an array of `String`s, `ids`, as an argument. `ids` is a `String` array, so 
    you need to convert individual IDs from `String`s to `long`s. You retrieve 
    the `companyId` from the first ID and pass it as an argument to the 
    `reindexEntries` helper method:
      
        @Override
        protected void doReindex(String[] ids)
          throws Exception {
          long companyId = GetterUtil.getLong(ids[0]);
          reindexEntries(companyId);
        }
      
12.  Next, add the `reindexEntries` helper method. You use an actionable dynamic 
    query in the `reindexEntries` helper method to retrieve all the guestbook 
    entries in the specified company. Each entry's Lucene document is added to a 
    collection:
      
        protected void reindexEntries(long companyId)
          throws PortalException {

          final IndexableActionableDynamicQuery indexableActionableDynamicQuery =
            _entryLocalService.getIndexableActionableDynamicQuery();
            
          indexableActionableDynamicQuery.setCompanyId(companyId);
          
          indexableActionableDynamicQuery.setPerformActionMethod(
            new ActionableDynamicQuery.PerformActionMethod<Entry>() {
              @Override
              public void performAction(Entry entry) {
                try {
                  Document document = getDocument(entry);
                  indexableActionableDynamicQuery.addDocuments(document);
                }
                catch (PortalException pe) {
                  if (_log.isWarnEnabled()) {
                    _log.warn(
                      "Unable to index entry " +
                        entry.getEntryId(),
                      pe);
                  }
                }
              }
            });
          indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());
          indexableActionableDynamicQuery.performActions();
        }

13.  Define the constants at the bottom of the file and resolve any 
    imports:
      
        private static final Log _log =
          LogFactoryUtil.getLog(EntryIndexer.class);
        @Reference
        private EntryLocalService _entryLocalService;
        private static final String CLASS_NAME = Entry.class.getName();

14.  Include the following dependencies in the `guestbook-api` module's 
    `build.gradle`:
    
        compile group: "javax.portlet", name: "portlet-api", version: "2.0"
        compile group: "javax.servlet", name: "servlet-api", version: "2.5"

15.  Finally, export the the `com.liferay.docs.guestbook.search` package in the
    `guestbook-api` module's `bnd.bnd` file.

Your entry indexer class in complete! Next you can update the service layer.

<!-- Unfortunately, actionable dynamic queries have not been explained yet in
any learning path, so you'll have to explain what that is here. -Rich -->