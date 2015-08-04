package com.liferay.docs.liferayguestbook.callback;

import android.widget.Toast;

import com.liferay.docs.liferayguestbook.activity.MainActivity;
import com.liferay.mobile.android.task.callback.typed.GenericAsyncTaskCallback;
import com.liferay.docs.liferayguestbook.model.EntryModel;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetEntriesCallback extends GenericAsyncTaskCallback<List<EntryModel>> {

    private MainActivity.EntriesFragment _fragment;

    public GetEntriesCallback(MainActivity.EntriesFragment fragment) {
        _fragment = fragment;
    }

    @Override
    public void onFailure(Exception e) {
        String message = "Couldn't get entries " + e.getMessage();

        Toast.makeText(_fragment.getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess(List<EntryModel> entries) {
        _fragment.reloadEntries(entries);
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