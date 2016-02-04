package com.liferay.docs.callback;

import android.widget.Toast;

import com.liferay.docs.liferayguestbook.EntriesFragment;
import com.liferay.docs.model.EntryModel;
import com.liferay.mobile.android.callback.typed.GenericCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetEntriesCallback extends GenericCallback<List<EntryModel>> {

    private EntriesFragment _fragment;

    public GetEntriesCallback(EntriesFragment fragment) {
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