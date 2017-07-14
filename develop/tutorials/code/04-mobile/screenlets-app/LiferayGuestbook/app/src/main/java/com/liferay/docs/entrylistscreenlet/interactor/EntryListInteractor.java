package com.liferay.docs.entrylistscreenlet.interactor;

import com.liferay.docs.model.EntryModel;
import com.liferay.mobile.android.v62.entry.EntryService;
import com.liferay.mobile.screens.base.list.interactor.BaseListInteractor;
import com.liferay.mobile.screens.base.list.interactor.BaseListInteractorListener;
import com.liferay.mobile.screens.base.list.interactor.Query;

import org.json.JSONArray;
import java.util.Map;

public class EntryListInteractor extends
        BaseListInteractor<BaseListInteractorListener<EntryModel>, EntryEvent> {

    @Override
    protected JSONArray getPageRowsRequest(Query query, Object... args) throws Exception {

        long guestbookId = (long) args[0];
        return new EntryService(getSession()).getEntries(groupId, guestbookId,
                query.getStartRow(), query.getEndRow());
    }

    @Override
    protected Integer getPageRowCountRequest(Object... args) throws Exception {

        long guestbookId = (long) args[0];
        return new EntryService(getSession()).getEntriesCount(groupId, guestbookId);
    }

    @Override
    protected EntryEvent createEntity(Map<String, Object> stringObjectMap) {
        EntryModel entry = new EntryModel(stringObjectMap);
        return new EntryEvent(entry);
    }

    @Override
    protected String getIdFromArgs(Object... args) {
        return String.valueOf(args[0]);
    }
}