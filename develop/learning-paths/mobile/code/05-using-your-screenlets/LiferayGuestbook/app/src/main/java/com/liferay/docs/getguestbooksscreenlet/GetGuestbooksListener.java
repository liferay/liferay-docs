package com.liferay.docs.getguestbooksscreenlet;

import java.util.List;

public interface GetGuestbooksListener {

    void onGetGuestbooksFailure(Exception e);

    void onGetGuestbooksSuccess(List<GuestbookModel> guestbooks);

    void onItemClicked(GuestbookModel guestbook);
}