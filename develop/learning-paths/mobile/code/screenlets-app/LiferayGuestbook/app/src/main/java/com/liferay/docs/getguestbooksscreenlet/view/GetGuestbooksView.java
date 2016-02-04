package com.liferay.docs.getguestbooksscreenlet.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.liferay.docs.getguestbooksscreenlet.GetGuestbooksScreenlet;
import com.liferay.docs.model.GuestbookModel;
import com.liferay.mobile.screens.base.BaseScreenlet;
import com.liferay.mobile.screens.util.LiferayLogger;
import com.liferay.mobile.screens.viewsets.defaultviews.LiferayCrouton;

import java.util.ArrayList;
import java.util.List;

public class GetGuestbooksView extends ListView implements GetGuestbooksViewModel, AdapterView.OnItemClickListener {

    private List<GuestbookModel> _guestbooks = new ArrayList<>();
    private BaseScreenlet _screenlet;

    public GetGuestbooksView(Context context) {
        this(context, null);
    }

    public GetGuestbooksView(Context context, AttributeSet attributes) {
        this(context, attributes, 0);
    }

    public GetGuestbooksView(Context context, AttributeSet attributes, int defaultStyle) {
        super(context, attributes, defaultStyle);

        ArrayAdapter adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1,
                _guestbooks
        );
        setAdapter(adapter);
        setOnItemClickListener(this);
    }

    @Override
    public void showStartOperation(String actionName) {
    }

    @Override
    public void showFinishOperation(String actionName) {
    }

    @Override
    public void showFinishOperation(final String userAction, final List<GuestbookModel> guestbooks) {
        LiferayLogger.i("Got the Guestbooks!");

        _guestbooks.clear();
        _guestbooks.addAll(guestbooks);

        ((ArrayAdapter) getAdapter()).notifyDataSetChanged();
    }

    @Override
    public void showFailedOperation(String actionName, Exception e) {
        LiferayLogger.e("Could not get Guestbooks", e);
        LiferayCrouton.error(getContext(), "Could not get Guestbooks", e);
    }

    @Override
    public BaseScreenlet getScreenlet() {
        return _screenlet;
    }

    @Override
    public void setScreenlet(BaseScreenlet screenlet) {
        _screenlet = screenlet;
    }

    @Override
    public void onItemClick(final AdapterView<?> parent, final View view,
                            final int position, final long id) {

        setItemChecked(position, true);
        ((GetGuestbooksScreenlet) getParent()).onItemClicked(_guestbooks.get(position));
    }
}
