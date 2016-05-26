package com.liferay.docs.getguestbooksscreenlet.interactor;

import com.liferay.docs.getguestbooksscreenlet.GetGuestbooksListener;
import com.liferay.mobile.screens.base.interactor.Interactor;

public interface GetGuestbooksInteractor extends Interactor<GetGuestbooksListener> {

    void getGuestbooks(long groupId);
}