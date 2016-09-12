package com.liferay.docs.entrylistscreenlet;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import com.liferay.docs.entrylistscreenlet.interactor.EntryListInteractorImpl;
import com.liferay.docs.liferayguestbook.R;
import com.liferay.docs.model.EntryModel;
import com.liferay.mobile.screens.base.list.BaseListScreenlet;
import com.liferay.mobile.screens.cache.OfflinePolicy;
import com.liferay.mobile.screens.context.LiferayServerContext;

import java.util.Locale;


public class EntryListScreenlet extends BaseListScreenlet<EntryModel, EntryListInteractorImpl> {

    private long _groupId;
    private long _guestbookId;
    private OfflinePolicy _offlinePolicy;

    public EntryListScreenlet(Context context) {
        super(context);
    }

    public EntryListScreenlet(Context context, AttributeSet attributes) {
        super(context, attributes);
    }

    public EntryListScreenlet(Context context, AttributeSet attributes, int defaultStyle) {
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

    public long getGuestbookId() {
        return _guestbookId;
    }

    public void setGuestbookId(long guestbookId) {
        _guestbookId = guestbookId;
    }

    @Override
    protected View createScreenletView(Context context, AttributeSet attributes) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attributes, R.styleable.EntryListScreenlet, 0, 0);
        Integer offlinePolicy = typedArray.getInteger(
                R.styleable.EntryListScreenlet_offlinePolicy,
                OfflinePolicy.REMOTE_ONLY.ordinal());
        _offlinePolicy = OfflinePolicy.values()[offlinePolicy];
        _groupId = typedArray.getInt(R.styleable.EntryListScreenlet_groupId,
                (int) LiferayServerContext.getGroupId());
        typedArray.recycle();

        return super.createScreenletView(context, attributes);
    }

    @Override
    protected void loadRows(EntryListInteractorImpl interactor, int startRow,
                            int endRow, Locale locale) throws Exception {

        interactor.loadRows(startRow, endRow, locale, _groupId, _guestbookId);
    }

    @Override
    protected EntryListInteractorImpl createInteractor(String actionName) {
        return new EntryListInteractorImpl(getScreenletId(), _offlinePolicy);
    }
}