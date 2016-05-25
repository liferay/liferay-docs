package com.liferay.docs.getguestbooksscreenlet;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.liferay.docs.getguestbooksscreenlet.interactor.GetGuestbooksInteractor;
import com.liferay.docs.getguestbooksscreenlet.interactor.GetGuestbooksInteractorImpl;
import com.liferay.docs.getguestbooksscreenlet.view.GetGuestbooksViewModel;
import com.liferay.docs.liferayguestbook.R;
import com.liferay.docs.model.GuestbookModel;
import com.liferay.mobile.screens.base.BaseScreenlet;
import com.liferay.mobile.screens.context.LiferayServerContext;
import com.liferay.mobile.screens.util.LiferayLogger;

import java.util.List;

public class GetGuestbooksScreenlet extends BaseScreenlet<GetGuestbooksViewModel, GetGuestbooksInteractor>
        implements GetGuestbooksListener {

    private GetGuestbooksListener _listener;
    private int _groupId;
    private boolean _autoLoad;

    public GetGuestbooksScreenlet(Context context) {
        super(context);
    }

    public GetGuestbooksScreenlet(Context context, AttributeSet attributes) {
        super(context, attributes);
    }

    public GetGuestbooksScreenlet(Context context, AttributeSet attributes, int defaultStyle) {
        super(context, attributes, defaultStyle);
    }

    public void onGetGuestbooksSuccess(List<GuestbookModel> guestbooks) {
        getViewModel().showFinishOperation(null, guestbooks);

        if (_listener != null) {
            _listener.onGetGuestbooksSuccess(guestbooks);
        }
    }

    public void onGetGuestbooksFailure(Exception e) {
        getViewModel().showFailedOperation(null, e);

        if (_listener != null) {
            _listener.onGetGuestbooksFailure(e);
        }
    }

    @Override
    public void onItemClicked(final GuestbookModel guestbook) {
        if (_listener != null) {
            _listener.onItemClicked(guestbook);
        }
    }

    public GetGuestbooksListener getListener() {
        return _listener;
    }

    public void setListener(GetGuestbooksListener listener) {
        _listener = listener;
    }

    public int getGroupId() {
        return _groupId;
    }

    public void setGroupId(int groupId) {
        _groupId = groupId;
    }

    @Override
    protected View createScreenletView(Context context, AttributeSet attributes) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attributes,
                R.styleable.GetGuestbooksScreenlet, 0, 0);

        int layoutId = typedArray.getResourceId(R.styleable.GetGuestbooksScreenlet_layoutId,
                getDefaultLayoutId());
        _autoLoad = typedArray.getBoolean(R.styleable.GetGuestbooksScreenlet_autoLoad, true);

        _groupId = typedArray.getInt(R.styleable.GetGuestbooksScreenlet_groupId,
                (int) LiferayServerContext.getGroupId());

        LiferayLogger.i("The Group ID is: " + _groupId);

        View view = LayoutInflater.from(context).inflate(layoutId, null);

        typedArray.recycle();

        return view;
    }

    @Override
    protected GetGuestbooksInteractor createInteractor(String actionName) {
        return new GetGuestbooksInteractorImpl(getScreenletId());
    }

    @Override
    protected void onUserAction(String userActionName,
                                GetGuestbooksInteractor interactor, Object... args) {

        try {
            interactor.getGuestbooks(_groupId);
        }
        catch (Exception e) {
            onGetGuestbooksFailure(e);
        }
    }

    @Override
    protected void onScreenletAttached() {
        if (_autoLoad) {
            performUserAction();
        }
    }
}
