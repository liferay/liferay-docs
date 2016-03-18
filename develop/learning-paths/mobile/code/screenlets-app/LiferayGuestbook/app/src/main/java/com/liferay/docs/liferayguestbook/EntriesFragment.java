package com.liferay.docs.liferayguestbook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.liferay.docs.getentriesscreenlet.GetEntriesListener;
import com.liferay.docs.getentriesscreenlet.GetEntriesScreenlet;
import com.liferay.docs.model.EntryModel;

import java.util.List;

public class EntriesFragment extends Fragment implements GetEntriesListener {

    private long _guestbookId;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_entries, container, false);
        _guestbookId = getArguments().getLong("guestbookId");

        GetEntriesScreenlet getEntriesScreenlet =
                (GetEntriesScreenlet) view.findViewById(R.id.getentries_screenlet);
        getEntriesScreenlet.setListener(this);
        getEntriesScreenlet.setGuestbookId(_guestbookId);

        return view;
    }

    @Override
    public void onGetEntriesSuccess(List<EntryModel> entries) {
    }

    @Override
    public void onGetEntriesFailure(Exception e) {
        Toast.makeText(getActivity(), "Couldn't get entries " + e.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClicked(final EntryModel entry) {
    }

}