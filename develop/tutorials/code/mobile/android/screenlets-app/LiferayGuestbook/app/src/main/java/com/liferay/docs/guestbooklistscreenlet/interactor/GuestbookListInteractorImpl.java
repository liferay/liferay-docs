package com.liferay.docs.guestbooklistscreenlet.interactor;

import android.util.Pair;

import com.liferay.docs.model.GuestbookModel;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.v7.guestbook.GuestbookService;
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


public class GuestbookListInteractorImpl extends BaseListInteractor<GuestbookModel, BaseListInteractorListener<GuestbookModel>> {

    private long _groupId;

    @Override
    protected BaseListCallback<GuestbookModel> getCallback(Pair<Integer, Integer> rowsRange, Locale locale) {
        return new BaseListCallback<GuestbookModel>(getTargetScreenletId(), rowsRange, locale) {
            @Override
            public GuestbookModel createEntity(Map<String, Object> stringObjectMap) {
                return new GuestbookModel(stringObjectMap);
            }
        };
    }

    @Override
    protected void getPageRowsRequest(Session session, int startRow, int endRow, Locale locale)
            throws Exception {
        new GuestbookService(session).getGuestbooks(_groupId, startRow, endRow);
    }

    @Override
    protected void getPageRowCountRequest(Session session) throws Exception {
        new GuestbookService(session).getGuestbooksCount(_groupId);
    }

    private enum GUESTBOOK_LIST implements CachedType {GUESTBOOK, GUESTBOOK_COUNT}

    public GuestbookListInteractorImpl(int targetScreenletId, OfflinePolicy offlinePolicy) {
        super(targetScreenletId, offlinePolicy);
    }

    public void loadRows(int startRow, int endRow, Locale locale, long groupId)
            throws Exception {

        _groupId = groupId;

        processWithCache(startRow, endRow, locale);
    }

    @Override
    protected String getContent(GuestbookModel object) {
        return new JSONObject(object.getValues()).toString();
    }

    @Override
    protected GuestbookModel getElement(TableCache tableCache) throws JSONException {
        return new GuestbookModel(JSONUtil.toMap(new JSONObject(tableCache.getContent())));
    }

    @Override
    protected void storeToCache(BaseListEvent event, Object... args) {
        storeRows(String.valueOf(_groupId), GUESTBOOK_LIST.GUESTBOOK,
                GUESTBOOK_LIST.GUESTBOOK_COUNT, _groupId, null, event);
    }

    @Override
    protected boolean cached(Object... args) throws Exception {
        final int startRow = (int) args[0];
        final int endRow = (int) args[1];
        final Locale locale = (Locale) args[2];

        return recoverRows(String.valueOf(_groupId), GUESTBOOK_LIST.GUESTBOOK,
                GUESTBOOK_LIST.GUESTBOOK_COUNT, _groupId, null, locale, startRow, endRow);
    }
}
