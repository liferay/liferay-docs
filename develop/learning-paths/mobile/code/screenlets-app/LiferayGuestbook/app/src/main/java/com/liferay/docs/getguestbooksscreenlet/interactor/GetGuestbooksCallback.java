package com.liferay.docs.getguestbooksscreenlet.interactor;


import com.liferay.docs.model.GuestbookModel;
import com.liferay.docs.getguestbooksscreenlet.event.GetGuestbooksEvent;
import com.liferay.mobile.screens.base.interactor.BasicEvent;
import com.liferay.mobile.screens.base.interactor.InteractorAsyncTaskCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetGuestbooksCallback extends InteractorAsyncTaskCallback<List<GuestbookModel>> {

    public GetGuestbooksCallback(int targetScreenletId) {
        super(targetScreenletId);
    }

    @Override
    protected BasicEvent createEvent(int targetScreenletId, List<GuestbookModel> guestbooks) {
        return new GetGuestbooksEvent(targetScreenletId, guestbooks);
    }

    @Override
    protected BasicEvent createEvent(int targetScreenletId, Exception e) {
        return new GetGuestbooksEvent(targetScreenletId, e);
    }

    @Override
    public List<GuestbookModel> transform(Object obj) throws Exception {
        List<GuestbookModel> guestbooks = new ArrayList<GuestbookModel>();

        JSONArray jsonArray = (JSONArray)obj;

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);

            guestbooks.add(new GuestbookModel(json));
        }

        return guestbooks;
    }

}
