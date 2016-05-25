package com.liferay.docs.getguestbooksscreenlet;

import com.liferay.docs.model.GuestbookModel;
import java.util.List;

public interface GetGuestbooksListener {

    void onGetGuestbooksFailure(Exception e);

    void onGetGuestbooksSuccess(List<GuestbookModel> guestbooks);

    void onItemClicked(GuestbookModel guestbook);
}
