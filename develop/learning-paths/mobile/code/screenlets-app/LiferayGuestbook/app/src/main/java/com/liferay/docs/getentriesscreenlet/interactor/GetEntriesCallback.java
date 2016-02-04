package com.liferay.docs.getentriesscreenlet.interactor;

import com.liferay.docs.model.EntryModel;
import com.liferay.docs.getentriesscreenlet.event.GetEntriesEvent;
import com.liferay.mobile.screens.base.interactor.BasicEvent;
import com.liferay.mobile.screens.base.interactor.InteractorAsyncTaskCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetEntriesCallback extends InteractorAsyncTaskCallback<List<EntryModel>> {

    public GetEntriesCallback(int targetScreenletId) {
        super(targetScreenletId);
    }

    @Override
    protected BasicEvent createEvent(int targetScreenletId, List<EntryModel> entries) {
        return new GetEntriesEvent(targetScreenletId, entries);
    }

    @Override
    protected BasicEvent createEvent(int targetScreenletId, Exception e) {
        return new GetEntriesEvent(targetScreenletId, e);
    }

    @Override
    public List<EntryModel> transform(Object obj) throws Exception {
        List<EntryModel> entries = new ArrayList<EntryModel>();

        JSONArray jsonArray = (JSONArray)obj;

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);

            entries.add(new EntryModel(json));
        }

        return entries;
    }
}