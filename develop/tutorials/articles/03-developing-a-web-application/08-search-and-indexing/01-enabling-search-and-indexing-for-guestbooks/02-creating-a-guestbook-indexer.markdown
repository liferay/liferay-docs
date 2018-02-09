# Creating a Guestbook Indexer [](id=creating-a-guestbook-indexer)

<div class="learn-path-step">
    <p>Enabling Search and Indexing for Guestbooks<br>Step 2 of 3</p>
</div>

First, update your `build.gradle` to have all of the necessary imports.

1.  Open the `build.gradle` file in your `guestbook-service` project.

2.  Add the following line below the other imports:

        compileOnly group: "com.liferay", name: "com.liferay.registry.api", version: "1.0.0"
        compileOnly group: "javax.portlet", name: "portlet-api", version: "2.0"
        compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"

3.  Save the file and run `Refresh Gradle Project`.

Now that you have the additional dependencies configured, follow these steps to create the indexer for guestbooks:

1.  Create a new package in the `guestbook-service` module project's
    `src/main/java` folder called `com.liferay.docs.guestbook.search`. In this
    package, create a new class called `GuestbookIndexer` that extends
    `com.liferay.portal.kernel.search.BaseIndexer` with `Guestbook` as a type 
    argument. Add an `@Component` annotation to declare that the 
    `GuestbookIndexer` class provides an implementation of the `Indexer` 
    service. Also, define the `CLASS_NAME` variable by getting the name of the 
    `Guestbook` model class. This is necessary to override the `getClassName` 
    method from `BaseIndexer`. @product@ uses this method to determine the 
    object this Indexer indexes: 

        @Component(
            immediate = true,
            service = Indexer.class
        )
        public class GuestbookIndexer extends BaseIndexer<Guestbook> {

            public static final String CLASS_NAME = Guestbook.class.getName();
        }

2.  Add the `GuestbookIndexer` constructor: 

        public GuestbookIndexer() {
            setDefaultSelectedFieldNames(
                Field.ASSET_TAG_NAMES, Field.COMPANY_ID, Field.CONTENT,
                Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK, Field.GROUP_ID,
                Field.MODIFIED_DATE, Field.SCOPE_GROUP_ID, Field.TITLE, Field.UID);
            setPermissionAware(true);
            setFilterSearch(true);
        }

    This constructor does several things:

    - Sets the default selected field names. These fields are used to retrieve 
      results documents from the search engine. 
    - Sets the default selected localized field names. This ensures that the 
      localized version of the field is searched and returned. 
    - Makes the search results permissions-aware at search time, as well as in 
      the index. Without this, a search query returns *all* matching guestbooks 
      regardless of the user's permissions on the resource. 
    - Sets filter search to `true`, enabling a document-by-document check of the 
      search results' `VIEW` permissions. This is redundant most of the time, 
      but safeguards against unexpected problems like the search index becoming 
      stale, or if permission inheritance doesn't happen fast enough. Most of 
      @product@'s internal apps use this setting. If not set, the indexer relies 
      on the permissions information indexed in the search engine. 

3.  Since you extend the abstract class `BaseIndexer` instead of implementing 
    the `Indexer` interface directly, you must override its abstract methods. In 
    the `getClassName` method, return the `CLASS_NAME` constant defined at the 
    top of the class: 

        @Override
        public String getClassName() {
            return CLASS_NAME;
        }

    This returns `com.liferay.docs.guestbook.model.Guestbook`. 

4.  Next, you must override `hasPermission`. Call the `contains` method of the
    `GuestbookPermission` helper class that you created in an earlier Learning
    Path section: 

        @Override
        public boolean hasPermission(
                PermissionChecker permissionChecker, String entryClassName, 
                long entryClassPK, String actionId) 
            throws Exception {

            return GuestbookPermission.contains(
                permissionChecker, entryClassPK, ActionKeys.VIEW);
        }

    Here, you ensure that the `VIEW` permission on guestbooks can be used to
    find and display appropriate search results. 

5.  Override the `postProcessContextBooleanFilter` method: 

        @Override
        public void postProcessContextBooleanFilter(
                BooleanFilter contextBooleanFilter, SearchContext searchContext)
        throws Exception {
            addStatus(contextBooleanFilter, searchContext);
        }

    This method is invoked while the main search query is being constructed. The 
    base implementation of `addStatus` in `BaseIndexer` adds the workflow status 
    to the filter. This ensures that entities with the status `STATUS_IN_TRASH` 
    aren't added to the query. You'll learn more about workflow later. 

6.  Override `postProcessSearchQuery` to add clauses to the ongoing search 
    query. It's best to add the localized value of any full text fields that 
    might contribute to search relevance. By specifying the localized search 
    term, you ensure that the regular search term has the locale appended (e.g., 
    `title_en_US`). For the guestbook entity, add the title field 
    (`Field.TITLE`): 

        @Override
        public void postProcessSearchQuery(
            BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
            SearchContext searchContext)
            throws Exception {

            addSearchLocalizedTerm(searchQuery, searchContext, Field.TITLE, false);
        }

7.  Override the `doDelete()` method, which deletes the document corresponding 
    to the `Guestbook` object parameter. Call `BaseIndexer`'s `deleteDocument` 
    method with the guestbook's company ID and guestbook ID: 

        @Override
        protected void doDelete(Guestbook guestbook) throws Exception {
            deleteDocument(guestbook.getCompanyId(), guestbook.getGuestbookId());
        }

8.  Implement the `doGetDocument` method to select the entity's fields to build 
    a search document that's indexed by the search engine. The main searchable 
    field for guestbooks is the guestbook name, which is stored in a guestbook 
    search document's title field: 

        @Override
        protected Document doGetDocument(Guestbook guestbook)
            throws Exception {

            Document document = getBaseModelDocument(CLASS_NAME, guestbook);

            document.addDate(Field.MODIFIED_DATE, guestbook.getModifiedDate());

            Locale defaultLocale =
                PortalUtil.getSiteDefaultLocale(guestbook.getGroupId());
            String localizedField = LocalizationUtil.getLocalizedName(
                Field.TITLE, defaultLocale.toString());

            document.addText(localizedField, guestbook.getName());
            return document;
        }

    Because @product@ supports localization, you should too. The above code gets 
    the default locale from the site by passing the `Guestbook`'s group ID to 
    the `getSiteDefaultLocale` method, then using it to get the localized name
    of the guestbook's title field. The retrieved site locale is appended to the
    field (e.g., `title_en_US`), so the field gets passed to the search engine
    and goes through the right analysis and
    [tokenization](https://www.elastic.co/guide/en/elasticsearch/reference/2.4/analysis-tokenizers.html). 

9.  Implement the `doGetSummary` method to return a *summary*. A summary is a 
    condensed, text-based version of the entity that can be displayed 
    generically. You create it by combining key parts of the entity's data so 
    users can browse through search results to find the entity they want. Call 
    `BaseIndexer`'s `createSummary` method, then use 
    `summary.setMaxContentLength` to set the summary content's maximum size. 
    Most @product@ applications use a value of `200`, so it's a good idea to use 
    the same to ensure uniform result summaries: 
      
        @Override
        protected Summary doGetSummary(
            Document document, Locale locale, String snippet,
            PortletRequest portletRequest, PortletResponse portletResponse) {

            Summary summary = createSummary(document);
            summary.setMaxContentLength(200);
            return summary;
        }

10. Override the overloaded `doReindex` method, which gets called when an entity 
    is updated or a user explicitly triggers a reindex. The first `doReindex` 
    method takes a single object argument. Retrieve the associated document with 
    `BaseIndexer`'s `getDocument` method, then invoke `IndexWriterHelper`'s 
    `updateDocument` method to update (reindex) the document:

        @Override
        protected void doReindex(Guestbook guestbook)
            throws Exception {

            Document document = getDocument(guestbook);
            indexWriterHelper.updateDocument(
                getSearchEngineId(), guestbook.getCompanyId(), document,
                isCommitImmediately());
        }

11. The second `doReindex` method takes two arguments: a `className` string, and 
    a `classPK` long. In this method, you retrieve the guestbook corresponding 
    to the primary key by calling `GuestbookLocalService`'s `getGuestbook` 
    method, passing in the `classPK` parameter. Then pass the guestbook to the 
    first `doReindex` method (see above): 

        @Override
        protected void doReindex(String className, long classPK)
            throws Exception {

            Guestbook guestbook = _guestbookLocalService.getGuestbook(classPK);
            doReindex(guestbook);
        }

12. The third (and final) `doReindex` method indexes all entities in the current 
    @product@ instance (`companyId`). It takes a string array (`ids`) as an 
    argument. `GetterUtil.getLong(ids[0])` retrieves the first string in the 
    array, casts it to a `long`, stores it in a `companyId` variable, and passes 
    it as an argument to the `reindexGuestbooks` helper method: 

        @Override
        protected void doReindex(String[] ids)
            throws Exception {

            long companyId = GetterUtil.getLong(ids[0]);
            reindexGuestbooks(companyId);
        }

13. To reindex guestbooks, provide the helper method `reindexGuestbooks`. In 
    this method, use an actionable dynamic query helper method to retrieve all 
    the guestbooks in the @product@ instance. Service Builder generated this 
    query method for you when you built the services. Each guestbook's document 
    is then retrieved and added to a collection: 
      
        protected void reindexGuestbooks(long companyId)
          throws PortalException {

          final IndexableActionableDynamicQuery indexableActionableDynamicQuery =
            _guestbookLocalService.getIndexableActionableDynamicQuery();
            
          indexableActionableDynamicQuery.setCompanyId(companyId);
          
          indexableActionableDynamicQuery.setPerformActionMethod(

            new ActionableDynamicQuery.PerformActionMethod<Guestbook>() {
              @Override
              public void performAction(Guestbook guestbook) {
                try {
                  Document document = getDocument(guestbook);
                  indexableActionableDynamicQuery.addDocuments(document);
                }
                catch (PortalException pe) {
                  if (_log.isWarnEnabled()) {
                    _log.warn(
                      "Unable to index guestbook " +
                        guestbook.getGuestbookId(),
                      pe);
                  }
                }
              }
            });
          indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());
          indexableActionableDynamicQuery.performActions();
        }

13. Get the log for the guestbook model and add the necessary service references 
    at the bottom of the file:
 
        private static final Log _log =
          LogFactoryUtil.getLog(GuestbookIndexer.class);

        @Reference
        protected IndexWriterHelper indexWriterHelper;

        @Reference
        private GuestbookLocalService _guestbookLocalService;

14. Organize your imports ([CTRL]+[SHIFT]+O), and save the file. It will have
    errors. 

15. Export the `com.liferay.docs.guestbook.search` package in the 
    `guestbook-service` module's `bnd.bnd` file. The export section should look
    like this: 

        Export-Package:
          com.liferay.docs.guestbook.service.permission,\
          com.liferay.docs.guestbook.search

The guestbook indexer class is complete! Next, you can update the service layer. 
