package com.liferay.docs.getguestbooksscreenlet.view;

import com.liferay.docs.getguestbooksscreenlet.GuestbookModel;
import com.liferay.mobile.screens.base.view.BaseViewModel;

import java.util.List;

public interface GetGuestbooksViewModel extends BaseViewModel {

    void showFinishOperation(String userAction, List<GuestbookModel> guestbooks);
}
