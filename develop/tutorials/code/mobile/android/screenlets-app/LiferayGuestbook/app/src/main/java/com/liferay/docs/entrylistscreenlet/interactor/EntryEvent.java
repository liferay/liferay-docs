package com.liferay.docs.entrylistscreenlet.interactor;

import com.liferay.docs.model.EntryModel;
import com.liferay.mobile.screens.base.list.interactor.ListEvent;

public class EntryEvent extends ListEvent<EntryModel> {

    private EntryModel entry;

    public EntryEvent() {
        super();
    }

    public EntryEvent(EntryModel entry) {
        this.entry = entry;
    }

    @Override
    public String getListKey() {
        return entry.getMessage();
    }

    @Override
    public EntryModel getModel() {
        return entry;
    }
}