package com.liferay.docs.guestbooklistscreenlet;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import com.liferay.docs.guestbooklistscreenlet.interactor.GuestbookListInteractor;
import com.liferay.docs.liferayguestbook.R;
import com.liferay.docs.model.GuestbookModel;
import com.liferay.mobile.screens.base.list.BaseListScreenlet;
import com.liferay.mobile.screens.context.LiferayServerContext;

public class GuestbookListScreenlet extends
        BaseListScreenlet<GuestbookModel, GuestbookListInteractor> {

    public GuestbookListScreenlet(Context context) {
        super(context);
    }

    public GuestbookListScreenlet(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GuestbookListScreenlet(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GuestbookListScreenlet(Context context, AttributeSet attrs, int defStyleAttr,
                                  int defStyleRes) {
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
    protected void loadRows(GuestbookListInteractor interactor) {
        interactor.start(0);
    }

    @Override
    protected GuestbookListInteractor createInteractor(String actionName) {
        return new GuestbookListInteractor();
    }
}
