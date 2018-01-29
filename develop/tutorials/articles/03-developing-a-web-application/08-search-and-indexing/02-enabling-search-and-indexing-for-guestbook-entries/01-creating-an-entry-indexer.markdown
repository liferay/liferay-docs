# Creating an Entry Indexer [](id=creating-an-entry-indexer)

<div class="learn-path-step">
    <p>Enabling Search and Indexing for Guestbook Entries<br>Step 1 of 2</p>
</div>

The `EntryIndexer` class you'll complete here is very similar to the 
`GuestbookIndexer` class you completed in the previous section. Therefore, the 
instructions here only point out differences between the indexing of guestbooks 
and entries. 

Follow these steps to create the entry indexer:

1.  In the `com.liferay.docs.guestbook.search` package of your 
    `guestbook-service` module project's `src/main/java` folder, create a new   
    class called `EntryIndexer` that extends 
    `com.liferay.portal.kernel.search.BaseIndexer`. Replace the default contents
    of `EntryIndexer.java` with the following code:

        package com.liferay.docs.guestbook.search;

        @Component(immediate = true, service = Indexer.class)
        public class EntryIndexer extends BaseIndexer<Entry> {

            public static final String CLASS_NAME = Entry.class.getName();

            public EntryIndexer() {

                setDefaultSelectedFieldNames(
                    Field.COMPANY_ID, Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK,
                    Field.UID, Field.SCOPE_GROUP_ID, Field.GROUP_ID);
                setDefaultSelectedLocalizedFieldNames(Field.TITLE, Field.CONTENT);
                setFilterSearch(true);
                setPermissionAware(true);
            }

            @Override
            public String getClassName() {

                return CLASS_NAME;
            }

            @Override
            public boolean hasPermission(
                PermissionChecker permissionChecker, String entryClassName,
                long entryClassPK, String actionId)
                throws Exception {

                return EntryPermission.contains(
                    permissionChecker, entryClassPK, ActionKeys.VIEW);
            }

            @Override
            public void postProcessContextBooleanFilter(
                BooleanFilter contextBooleanFilter, SearchContext searchContext)
                throws Exception {

                addStatus(contextBooleanFilter, searchContext);
            }

            @Override
            public void postProcessSearchQuery(
                BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
                SearchContext searchContext)
                throws Exception {

                addSearchLocalizedTerm(searchQuery, searchContext, "guestbookName", false);
                addSearchLocalizedTerm(searchQuery, searchContext, Field.TITLE, false);
                addSearchLocalizedTerm(searchQuery, searchContext, Field.CONTENT, false);
            }

            @Override
            protected void doDelete(Entry entry)
                throws Exception {

                deleteDocument(entry.getCompanyId(), entry.getEntryId());
            }

            @Override
            protected Document doGetDocument(Entry entry)
                throws Exception {

                Document document = getBaseModelDocument(CLASS_NAME, entry);
                document.addDate(Field.MODIFIED_DATE, entry.getModifiedDate());
                document.addText("email", entry.getEmail());

                Locale defaultLocale =
                    PortalUtil.getSiteDefaultLocale(entry.getGroupId());
                String localizedTitle = LocalizationUtil.getLocalizedName(
                    Field.TITLE, defaultLocale.toString());
                String localizedMessage = LocalizationUtil.getLocalizedName(
                    Field.CONTENT, defaultLocale.toString());

                document.addText(localizedTitle, entry.getName());
                document.addText(localizedMessage, entry.getMessage());

                long guestbookId = entry.getGuestbookId();
                Guestbook guestbook = _guestbookLocalService.getGuestbook(guestbookId);
                String guestbookName= guestbook.getName();
                String localizedGbName = LocalizationUtil.getLocalizedName(
                    "guestbookName", defaultLocale.toString());

                document.addText(localizedGbName, guestbookName);

                return document;
            }

    This is not all the code, but it contains the heart of the functionality: 
    the `doGetDocument` method and its helper methods. The email, date, 
    localized title, and message fields (based on the site's default language) 
    are indexed. Finally, you get the entry's guestbook and index the localized 
    version of the `guestbookName` field. Always support localization where 
    possible--this ensures your entities are searchable in any language. 

2.  The rest of the code is very similar to the `GuestbookIndexer`. Paste in the 
    following code to finish the entry indexer class: 

            @Override
            protected Summary doGetSummary(
                Document document, Locale locale, String snippet,
                PortletRequest portletRequest, PortletResponse portletResponse)
                throws Exception {

                Summary summary = createSummary(document);

                summary.setMaxContentLength(200);

                return summary;
            }

            @Override
            protected void doReindex(Entry entry)
                throws Exception {

                Document document = getDocument(entry);
                indexWriterHelper.updateDocument(
                    getSearchEngineId(), entry.getCompanyId(), document,
                    isCommitImmediately());
            }

            @Override
            protected void doReindex(String className, long classPK)
                throws Exception {

                Entry entry = _entryLocalService.getEntry(classPK);
                doReindex(entry);
            }

            @Override
            protected void doReindex(String[] ids)
                throws Exception {

                long companyId = GetterUtil.getLong(ids[0]);
                reindexEntries(companyId);
            }

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
                                        "Unable to index entry " + entry.getEntryId(),
                                        pe);
                                }
                            }
                        }
                    });
                indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());
                indexableActionableDynamicQuery.performActions();
            }

            private static final Log _log = LogFactoryUtil.getLog(EntryIndexer.class);

            @Reference
            protected IndexWriterHelper indexWriterHelper;

            @Reference
            private EntryLocalService _entryLocalService;

            @Reference
            private GuestbookLocalService _guestbookLocalService;

        }

As with the guestbook, you must update the entry's service layer to support
indexing when its service methods are called. That's your next step. 
