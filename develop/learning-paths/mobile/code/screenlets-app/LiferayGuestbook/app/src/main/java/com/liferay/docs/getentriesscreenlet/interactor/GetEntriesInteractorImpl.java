package com.liferay.docs.getentriesscreenlet.interactor;

import com.liferay.docs.getentriesscreenlet.GetEntriesListener;
import com.liferay.docs.getentriesscreenlet.event.GetEntriesEvent;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.v62.entry.EntryService;
import com.liferay.mobile.screens.base.interactor.BaseRemoteInteractor;
import com.liferay.mobile.screens.context.SessionContext;
import com.liferay.mobile.screens.util.LiferayLogger;

public class GetEntriesInteractorImpl extends BaseRemoteInteractor<GetEntriesListener>
        implements GetEntriesInteractor {

    public GetEntriesInteractorImpl(int targetScreenletId) {
        super(targetScreenletId);
    }

    public void onEvent(GetEntriesEvent event) {
        if (!isValidEvent(event)) {
            return;
        }

        if (event.isFailed()) {
            getListener().onGetEntriesFailure(event.getException());
        }
        else {
            getListener().onGetEntriesSuccess(event.getEntries());
        }
    }

    public void getEntries(long groupId, long guestbookId) {

        Session session = SessionContext.createSessionFromCurrentSession();
        session.setCallback(new GetEntriesCallback(getTargetScreenletId()));
        EntryService service = new EntryService(session);

        try {
            service.getEntries(groupId, guestbookId);
        } catch (Exception e) {
            LiferayLogger.e("Could not get Entries", e);
        }
    }

}