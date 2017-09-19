package com.liferay.docs.liferayguestbook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.liferay.docs.entrylistscreenlet.EntryListScreenlet;
import com.liferay.docs.model.EntryModel;
import com.liferay.mobile.screens.base.list.BaseListListener;

import java.util.List;

public class EntriesFragment extends Fragment implements BaseListListener<EntryModel> {

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
        long guestbookId = getArguments().getLong("guestbookId");

        EntryListScreenlet screenlet = (EntryListScreenlet) view.findViewById(R.id.entrylist_screenlet);
        screenlet.setListener(this);
        screenlet.setGuestbookId(guestbookId);

        return view;
    }

    @Override
    public void onListPageFailed(int startRow, Exception e) {
        Toast.makeText(getActivity(), "Page request failed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onListPageReceived(int startRow, int endRow, List<EntryModel> entries, int rowCount) {

    }

    @Override
    public void onListItemSelected(EntryModel entry, View view) {

    }

    @Override
    public void error(Exception e, String userAction) {

    }

}