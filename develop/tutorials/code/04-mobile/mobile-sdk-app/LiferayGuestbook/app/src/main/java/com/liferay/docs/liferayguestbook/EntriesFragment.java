package com.liferay.docs.liferayguestbook;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.liferay.docs.callback.GetEntriesCallback;
import com.liferay.docs.model.EntryModel;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.v62.entry.EntryService;
import com.liferay.mobile.screens.context.LiferayServerContext;
import com.liferay.mobile.screens.context.SessionContext;

import java.util.ArrayList;
import java.util.List;

public class EntriesFragment extends ListFragment {

    private long _guestbookId;
    private List<EntryModel> _entries = new ArrayList<EntryModel>();
    private ArrayAdapter _adapter;

    public EntriesFragment() {
        // Required empty public constructor
    }

    public static EntriesFragment newInstance(long guestbookId) {
        EntriesFragment entriesFragment = new EntriesFragment();
        Bundle args = new Bundle();
        args.putLong("guestbookId", guestbookId);
        entriesFragment.setArguments(args);

        return entriesFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _guestbookId = getArguments().getLong("guestbookId");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        _adapter = new ArrayAdapter<EntryModel>(
                getContext(),
                android.R.layout.simple_list_item_2,
                android.R.id.text1,
                _entries) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(_entries.get(position).getMessage());
                text2.setText(_entries.get(position).getName());

                text1.setTypeface(Typeface.DEFAULT_BOLD);
                text2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);

                return view;
            }
        };

        setListAdapter(_adapter);

        getEntries(_guestbookId);
    }

    protected void getEntries(long guestbookId) {
        Session session = SessionContext.createSessionFromCurrentSession();
        GetEntriesCallback callback = new GetEntriesCallback(this);
        session.setCallback(callback);

        EntryService service = new EntryService(session);
        try {
            service.getEntries(LiferayServerContext.getGroupId(), guestbookId);
        }
        catch (Exception e) {
            String message = "Couldn't get entries " + e.getMessage();
            Toast.makeText(this.getActivity(), message, Toast.LENGTH_LONG).show();
        }
    }

    public void reloadEntries(List<EntryModel> entries) {
        _entries.clear();
        _entries.addAll(entries);
        _adapter.notifyDataSetChanged();
    }
}