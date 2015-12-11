package com.liferay.docs.getentriesscreenlet.interactor;

import com.liferay.docs.getentriesscreenlet.GetEntriesListener;
import com.liferay.mobile.screens.base.interactor.Interactor;

public interface GetEntriesInteractor extends Interactor<GetEntriesListener> {

    void getEntries(long groupId, long guestbookId);
}