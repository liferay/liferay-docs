package com.liferay.docs.getentriesscreenlet;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.liferay.docs.getentriesscreenlet.interactor.GetEntriesInteractor;
import com.liferay.docs.getentriesscreenlet.interactor.GetEntriesInteractorImpl;
import com.liferay.docs.getentriesscreenlet.view.GetEntriesViewModel;
import com.liferay.docs.model.EntryModel;
import com.liferay.docs.liferayguestbook.R;
import com.liferay.mobile.screens.base.BaseScreenlet;
import com.liferay.mobile.screens.context.LiferayServerContext;
import com.liferay.mobile.screens.util.LiferayLogger;

import java.util.List;

public class GetEntriesScreenlet extends BaseScreenlet<GetEntriesViewModel, GetEntriesInteractor>
        implements GetEntriesListener {

    private GetEntriesListener _listener;
    private int _groupId;
    private long _guestbookId;
    private boolean _autoLoad;

    public GetEntriesScreenlet(Context context) {
        super(context);
    }

    public GetEntriesScreenlet(Context context, AttributeSet attributes) {
        super(context, attributes);
    }

    public GetEntriesScreenlet(Context context, AttributeSet attributes, int defaultStyle) {
        super(context, attributes, defaultStyle);
    }

    public void onGetEntriesSuccess(List<EntryModel> entries) {
        getViewModel().showFinishOperation(null, entries);

        if (_listener != null) {
            _listener.onGetEntriesSuccess(entries);
        }
    }

    public void onGetEntriesFailure(Exception e) {
        getViewModel().showFailedOperation(null, e);

        if (_listener != null) {
            _listener.onGetEntriesFailure(e);
        }
    }

    @Override
    public void onItemClicked(final EntryModel entry) {
        if (_listener != null) {
            _listener.onItemClicked(entry);
        }
    }

    public GetEntriesListener getListener() {
        return _listener;
    }

    public void setListener(GetEntriesListener listener) {
        _listener = listener;
    }

    public int getGroupId() {
        return _groupId;
    }

    public void setGroupId(int groupId) {
        _groupId = groupId;
    }

    public long getGuestbookId() {
        return _guestbookId;
    }

    public void setGuestbookId(long guestbookId) {
        _guestbookId = guestbookId;
    }

    @Override
    protected View createScreenletView(Context context, AttributeSet attributes) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attributes,
                R.styleable.GetEntriesScreenlet, 0, 0);

        int layoutId = typedArray.getResourceId(R.styleable.GetEntriesScreenlet_layoutId,
                getDefaultLayoutId());
        _autoLoad = typedArray.getBoolean(R.styleable.GetEntriesScreenlet_autoLoad, true);

        _groupId = typedArray.getInt(R.styleable.GetEntriesScreenlet_groupId,
                (int) LiferayServerContext.getGroupId());

        LiferayLogger.i("The Group ID is: " + _groupId);

        View view = LayoutInflater.from(context).inflate(layoutId, null);

        typedArray.recycle();

        return view;
    }

    @Override
    protected GetEntriesInteractor createInteractor(String actionName) {
        return new GetEntriesInteractorImpl(getScreenletId());
    }

    @Override
    protected void onUserAction(String userActionName, GetEntriesInteractor interactor,
                                Object... args) {

        try {
            interactor.getEntries(_groupId, _guestbookId);
        }
        catch (Exception e) {
            onGetEntriesFailure(e);
        }
    }

    @Override
    protected void onScreenletAttached() {
        if (_autoLoad) {
            performUserAction();
        }
    }
}