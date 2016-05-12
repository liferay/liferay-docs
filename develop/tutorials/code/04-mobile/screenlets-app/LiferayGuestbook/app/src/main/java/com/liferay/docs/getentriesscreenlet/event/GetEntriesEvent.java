package com.liferay.docs.getentriesscreenlet.event;

import com.liferay.docs.model.EntryModel;
import com.liferay.mobile.screens.base.interactor.BasicEvent;

import java.util.List;

public class GetEntriesEvent extends BasicEvent {

    private List<EntryModel> _entries;

    public GetEntriesEvent(int targetScreenletId, Exception e) {
        super(targetScreenletId, e);
    }

    public GetEntriesEvent(int targetScreenletId, List<EntryModel> entries) {
        super(targetScreenletId);

        _entries = entries;
    }

    public List<EntryModel> getEntries() {
        return _entries;
    }
}