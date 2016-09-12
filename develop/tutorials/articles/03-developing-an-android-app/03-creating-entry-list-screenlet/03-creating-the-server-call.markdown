# Creating Entry List Screenlet's Server Call [](id=creating-entry-list-screenlets-server-call)

Recall that Interactors are components that make a Screenlet's server call. Also 
recall that Interactors are made up of an event, a callback, one or more 
listeners, and an Interactor class. As with Guestbook List Screenlet's 
Interactor, you only need to create the Interactor class because the list 
Screenlet framework provides default implementations of the other Interactor 
components. 

This article walks you through the steps required to create Entry List 
Screenlet's Interactor class. Because this Screenlet's Interactor class is so 
similar to that of Guestbook List Screenlet, these steps aren't explained in 
detail. Focus is instead placed on the few places in the code where the 
Interactor classes diverge. For a full explanation of the code, see 
[the article on creating Guestbook List Screenlet's server call](/develop/tutorials/-/knowledge_base/7-0/creating-guestbook-list-screenlets-server-call). 

## Creating the Interactor Class [](id=creating-the-interactor-class)

First, create a new package called `interactor` in 
`com.liferay.docs.entrylistscreenlet`. Create a new class called 
`EntryListInteractorImpl` in the `interactor` package. Replace this class's 
content with the following: 

    package com.liferay.docs.entrylistscreenlet.interactor;

    import android.util.Pair;

    import com.liferay.docs.model.EntryModel;
    import com.liferay.mobile.android.service.Session;
    import com.liferay.mobile.android.v7.entry.EntryService;
    import com.liferay.mobile.screens.base.list.interactor.BaseListCallback;
    import com.liferay.mobile.screens.base.list.interactor.BaseListEvent;
    import com.liferay.mobile.screens.base.list.interactor.BaseListInteractor;
    import com.liferay.mobile.screens.base.list.interactor.BaseListInteractorListener;
    import com.liferay.mobile.screens.cache.CachedType;
    import com.liferay.mobile.screens.cache.OfflinePolicy;
    import com.liferay.mobile.screens.cache.tablecache.TableCache;
    import com.liferay.mobile.screens.util.JSONUtil;

    import org.json.JSONException;
    import org.json.JSONObject;

    import java.util.Locale;
    import java.util.Map;


    public class EntryListInteractorImpl extends 
        BaseListInteractor<EntryModel, BaseListInteractorListener<EntryModel>> {

        private long _groupId;
        private long _guestbookId;

        @Override
        protected BaseListCallback<EntryModel> getCallback(Pair<Integer, Integer> rowsRange, Locale locale) {
            return new BaseListCallback<EntryModel>(getTargetScreenletId(), rowsRange, locale) {
                @Override
                public EntryModel createEntity(Map<String, Object> stringObjectMap) {
                    return new EntryModel(stringObjectMap);
                }
            };
        }

        @Override
        protected void getPageRowsRequest(Session session, int startRow, int endRow, Locale locale) throws Exception {
            new EntryService(session).getEntries(_groupId, _guestbookId, startRow, endRow);
        }

        @Override
        protected void getPageRowCountRequest(Session session) throws Exception {
            new EntryService(session).getEntriesCount(_groupId, _guestbookId);
        }

        private enum ENTRY_LIST implements CachedType {ENTRY, ENTRY_COUNT}

        public EntryListInteractorImpl(int targetScreenletId, OfflinePolicy offlinePolicy) {
            super(targetScreenletId, offlinePolicy);
        }

        public void loadRows(int startRow, int endRow, Locale locale, long groupId, long guestbookId)
            throws Exception {

            _groupId = groupId;
            _guestbookId = guestbookId;

            processWithCache(startRow, endRow, locale);
        }

        @Override
        protected String getContent(EntryModel object) {
            return new JSONObject(object.getValues()).toString();
        }

        @Override
        protected EntryModel getElement(TableCache tableCache) throws JSONException {
            return new EntryModel(JSONUtil.toMap(new JSONObject(tableCache.getContent())));
        }

        @Override
        protected void storeToCache(BaseListEvent event, Object... args) {
            storeRows(String.valueOf(_groupId), ENTRY_LIST.ENTRY,
                ENTRY_LIST.ENTRY_COUNT, _groupId, null, event);
        }

        @Override
        protected boolean cached(Object... args) throws Exception {
            final int startRow = (int) args[0];
            final int endRow = (int) args[1];
            final Locale locale = (Locale) args[2];

            return recoverRows(String.valueOf(_groupId), ENTRY_LIST.ENTRY,
                ENTRY_LIST.ENTRY_COUNT, _groupId, null, locale, startRow, endRow);
        }
    }

Besides using entries instead of guestbooks, this class is almost identical to 
`GuestbookListInteractorImpl`. The only other differences are due to the service 
calls that retrieve the entries and number of entries from a guestbook in the 
Guestbook portlet. These calls require a guestbook ID (`_guestbookId`) in 
addition to the group ID (`_groupId`) that guestbook service calls require. The 
entry service calls, made in `getPageRowsRequest` and `getPageRowCountRequest`, 
require an `EntryService` instance. The `getEntries` method retrieves a 
guestbook's entries, and the `getEntriesCount` method retrieves the number of 
entries in a guestbook. 

Nicely done! Now that Entry List Screenlet has an Interactor, you must create 
the Screenlet class. The next article shows you how to do this. 
