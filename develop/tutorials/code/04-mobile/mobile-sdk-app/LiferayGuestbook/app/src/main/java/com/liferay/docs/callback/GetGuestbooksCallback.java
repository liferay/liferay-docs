package com.liferay.docs.callback;

import android.widget.Toast;

import com.liferay.docs.liferayguestbook.GuestbooksActivity;
import com.liferay.docs.model.GuestbookModel;
import com.liferay.mobile.android.callback.typed.GenericCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetGuestbooksCallback extends GenericCallback<List<GuestbookModel>> {

    private GuestbooksActivity _activity;

    public GetGuestbooksCallback(GuestbooksActivity activity) {
        _activity = activity;
    }

    @Override
    public void onFailure(Exception e) {
        String message = "Couldn't get guestbooks " + e.getMessage();

        Toast.makeText(_activity, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess(List<GuestbookModel> guestbooks) {
        _activity.reloadGuestbooks(guestbooks);
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