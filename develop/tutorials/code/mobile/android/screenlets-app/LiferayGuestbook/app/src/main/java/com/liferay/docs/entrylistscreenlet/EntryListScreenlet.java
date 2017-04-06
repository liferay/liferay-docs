package com.liferay.docs.entrylistscreenlet;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import com.liferay.docs.entrylistscreenlet.interactor.EntryListInteractor;
import com.liferay.docs.liferayguestbook.R;
import com.liferay.docs.model.EntryModel;
import com.liferay.mobile.screens.base.list.BaseListScreenlet;
import com.liferay.mobile.screens.context.LiferayServerContext;

public class EntryListScreenlet extends BaseListScreenlet<EntryModel, EntryListInteractor> {

    private long guestbookId;

    public EntryListScreenlet(Context context) {
        super(context);
    }

    public EntryListScreenlet(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EntryListScreenlet(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public EntryListScreenlet(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void error(Exception e, String userAction) {
        if (getListener() != null) {
            getListener().error(e, userAction);
        }
    }

    @Override
    protected View createScreenletView(Context context, AttributeSet attributes) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attributes,
                R.styleable.GuestbookListScreenlet, 0, 0);
        groupId = typedArray.getInt(R.styleable.GuestbookListScreenlet_groupId,
                (int) LiferayServerContext.getGroupId());
        typedArray.recycle();

        return super.createScreenletView(context, attributes);
    }

    @Override
    protected void loadRows(EntryListInteractor interactor) {
        interactor.start(guestbookId);
    }

    @Override
    protected EntryListInteractor createInteractor(String actionName) {
        return new EntryListInteractor();
    }

    public long getGuestbookId() {
        return guestbookId;
    }

    public void setGuestbookId(long guestbookId) {
        this.guestbookId = guestbookId;
    }
}
