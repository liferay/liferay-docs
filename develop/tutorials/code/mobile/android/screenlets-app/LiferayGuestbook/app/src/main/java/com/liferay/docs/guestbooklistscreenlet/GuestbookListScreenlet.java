package com.liferay.docs.guestbooklistscreenlet;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import com.liferay.docs.guestbooklistscreenlet.interactor.GuestbookListInteractorImpl;
import com.liferay.docs.liferayguestbook.R;
import com.liferay.docs.model.GuestbookModel;
import com.liferay.mobile.screens.base.list.BaseListScreenlet;
import com.liferay.mobile.screens.cache.OfflinePolicy;
import com.liferay.mobile.screens.context.LiferayServerContext;

import java.util.Locale;


public class GuestbookListScreenlet extends BaseListScreenlet<GuestbookModel, GuestbookListInteractorImpl> {

    private long _groupId;
    private OfflinePolicy _offlinePolicy;

    public GuestbookListScreenlet(Context context) {
        super(context);
    }

    public GuestbookListScreenlet(Context context, AttributeSet attributes) {
        super(context, attributes);
    }

    public GuestbookListScreenlet(Context context, AttributeSet attributes, int defaultStyle) {
        super(context, attributes, defaultStyle);
    }

    @Override
    public void loadingFromCache(boolean success) {
        if (getListener() != null) {
            getListener().loadingFromCache(success);
        }
    }

    @Override
    public void retrievingOnline(boolean triedInCache, Exception e) {
        if (getListener() != null) {
            getListener().retrievingOnline(triedInCache, e);
        }
    }

    @Override
    public void storingToCache(Object object) {
        if (getListener() != null) {
            getListener().storingToCache(object);
        }
    }

    @Override
    protected View createScreenletView(Context context, AttributeSet attributes) {

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attributes, R.styleable.GuesbookListScreenlet, 0, 0);
        Integer offlinePolicy = typedArray.getInteger(
                R.styleable.GuesbookListScreenlet_offlinePolicy,
                OfflinePolicy.REMOTE_ONLY.ordinal());
        _offlinePolicy = OfflinePolicy.values()[offlinePolicy];
        _groupId = typedArray.getInt(R.styleable.GuesbookListScreenlet_groupId,
                (int) LiferayServerContext.getGroupId());
        typedArray.recycle();

        return super.createScreenletView(context, attributes);
    }

    @Override
    protected void loadRows(GuestbookListInteractorImpl interactor, int startRow,
                            int endRow, Locale locale) throws Exception {

        interactor.loadRows(startRow, endRow, locale, _groupId);
    }

    @Override
    protected GuestbookListInteractorImpl createInteractor(String actionName) {
        return new GuestbookListInteractorImpl(getScreenletId(), _offlinePolicy);
    }
}
