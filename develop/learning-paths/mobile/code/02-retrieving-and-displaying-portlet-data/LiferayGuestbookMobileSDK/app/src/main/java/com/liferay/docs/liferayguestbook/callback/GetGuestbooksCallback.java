package com.liferay.docs.liferayguestbook.callback;

import android.widget.Toast;

import com.liferay.docs.liferayguestbook.activity.MainActivity;
import com.liferay.mobile.android.task.callback.typed.GenericAsyncTaskCallback;
import com.liferay.docs.liferayguestbook.model.GuestbookModel;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetGuestbooksCallback extends GenericAsyncTaskCallback<List<GuestbookModel>> {

    private MainActivity _activity;

    public GetGuestbooksCallback(MainActivity activity) {
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