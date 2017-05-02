package com.liferay.docs.guestbooklistscreenlet.interactor;

import com.liferay.docs.model.GuestbookModel;
import com.liferay.mobile.android.v62.guestbook.GuestbookService;
import com.liferay.mobile.screens.base.list.interactor.BaseListInteractor;
import com.liferay.mobile.screens.base.list.interactor.BaseListInteractorListener;
import com.liferay.mobile.screens.base.list.interactor.Query;

import org.json.JSONArray;

import java.util.Map;


public class GuestbookListInteractor extends
        BaseListInteractor<BaseListInteractorListener<GuestbookModel>, GuestbookEvent> {

    @Override
    protected JSONArray getPageRowsRequest(Query query, Object... args) throws Exception {

        return new GuestbookService(getSession()).getGuestbooks(groupId, query.getStartRow(),
                query.getEndRow());
    }

    @Override
    protected Integer getPageRowCountRequest(Object... args) throws Exception {

        return new GuestbookService(getSession()).getGuestbooksCount(groupId);
    }

    @Override
    protected GuestbookEvent createEntity(Map<String, Object> stringObjectMap) {
        GuestbookModel guestbook = new GuestbookModel(stringObjectMap);
        return new GuestbookEvent(guestbook);
    }

    @Override
    protected String getIdFromArgs(Object... args) {
        return String.valueOf(args[0]);
    }
}
