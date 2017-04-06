package com.liferay.docs.guestbooklistscreenlet.interactor;

import com.liferay.docs.model.GuestbookModel;
import com.liferay.mobile.screens.base.list.interactor.ListEvent;

public class GuestbookEvent extends ListEvent<GuestbookModel> {

    private GuestbookModel guestbook;

    public GuestbookEvent() {
        super();
    }

    public GuestbookEvent(GuestbookModel guestbook) {
        this.guestbook = guestbook;
    }

    @Override
    public String getListKey() {
        return guestbook.getName();
    }

    @Override
    public GuestbookModel getModel() {
        return guestbook;
    }
}
