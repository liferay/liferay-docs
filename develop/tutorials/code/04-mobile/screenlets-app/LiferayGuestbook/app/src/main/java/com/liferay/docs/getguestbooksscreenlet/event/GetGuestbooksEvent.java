package com.liferay.docs.getguestbooksscreenlet.event;

import com.liferay.docs.model.GuestbookModel;
import com.liferay.mobile.screens.base.interactor.BasicEvent;

import java.util.List;

public class GetGuestbooksEvent extends BasicEvent {

    private List<GuestbookModel> _guestbooks;

    public GetGuestbooksEvent(int targetScreenletId, Exception e) {
        super(targetScreenletId, e);
    }

    public GetGuestbooksEvent(int targetScreenletId, List<GuestbookModel> guestbooks) {
        super(targetScreenletId);

        _guestbooks = guestbooks;
    }

    public List<GuestbookModel> getGuestbooks() {
        return _guestbooks;
    }

}