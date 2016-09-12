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