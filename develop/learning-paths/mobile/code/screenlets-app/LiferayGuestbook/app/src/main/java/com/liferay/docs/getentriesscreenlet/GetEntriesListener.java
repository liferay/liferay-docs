package com.liferay.docs.getentriesscreenlet;

import com.liferay.docs.model.EntryModel;
import java.util.List;

public interface GetEntriesListener {

    void onGetEntriesFailure(Exception e);

    void onGetEntriesSuccess(List<EntryModel> entries);

    void onItemClicked(EntryModel entry);
}