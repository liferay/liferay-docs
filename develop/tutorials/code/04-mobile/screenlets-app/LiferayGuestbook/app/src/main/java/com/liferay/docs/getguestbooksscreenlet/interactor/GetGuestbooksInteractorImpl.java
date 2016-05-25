package com.liferay.docs.getguestbooksscreenlet.interactor;

import com.liferay.docs.getguestbooksscreenlet.GetGuestbooksListener;
import com.liferay.docs.getguestbooksscreenlet.event.GetGuestbooksEvent;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.v62.guestbook.GuestbookService;
import com.liferay.mobile.screens.base.interactor.BaseRemoteInteractor;
import com.liferay.mobile.screens.context.SessionContext;
import com.liferay.mobile.screens.util.LiferayLogger;

public class GetGuestbooksInteractorImpl extends BaseRemoteInteractor<GetGuestbooksListener>
        implements GetGuestbooksInteractor {

    public GetGuestbooksInteractorImpl(int targetScreenletId) {
        super(targetScreenletId);
    }

    public void onEvent(GetGuestbooksEvent event) {
        if (!isValidEvent(event)) {
            return;
        }

        if (event.isFailed()) {
            getListener().onGetGuestbooksFailure(event.getException());
        }
        else {
            getListener().onGetGuestbooksSuccess(event.getGuestbooks());
        }
    }

    public void getGuestbooks(long groupId) {

        Session session = SessionContext.createSessionFromCurrentSession();
        session.setCallback(new GetGuestbooksCallback(getTargetScreenletId()));
        GuestbookService service = new GuestbookService(session);

        try {
            service.getGuestbooks(groupId);
        } catch (Exception e) {
            LiferayLogger.e("Could not get Guestbooks", e);
        }
    }

}