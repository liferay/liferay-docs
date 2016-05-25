package com.liferay.docs.getentriesscreenlet.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.liferay.docs.getentriesscreenlet.GetEntriesScreenlet;
import com.liferay.docs.model.EntryModel;
import com.liferay.mobile.screens.base.BaseScreenlet;
import com.liferay.mobile.screens.util.LiferayLogger;
import com.liferay.mobile.screens.viewsets.defaultviews.LiferayCrouton;

import java.util.ArrayList;
import java.util.List;

public class GetEntriesView extends ListView implements GetEntriesViewModel,
        AdapterView.OnItemClickListener {

    private List<EntryModel> _entries = new ArrayList<>();
    private BaseScreenlet _screenlet;

    public GetEntriesView(Context context) {
        this(context, null);
    }

    public GetEntriesView(Context context, AttributeSet attributes) {
        this(context, attributes, 0);
    }

    public GetEntriesView(Context context, AttributeSet attributes, int defaultStyle) {
        super(context, attributes, defaultStyle);

        ArrayAdapter adapter = new ArrayAdapter<EntryModel>(
                getContext(),
                android.R.layout.simple_list_item_2,
                android.R.id.text1, _entries) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(_entries.get(position).getMessage());
                text2.setText(_entries.get(position).getName());

                text1.setTypeface(Typeface.DEFAULT_BOLD);
                text2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);

                return view;
            }
        };
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
    public void showFinishOperation(final String userAction,
                                    final List<EntryModel> entries) {
        LiferayLogger.i("Got the Entries!");

        _entries.clear();
        _entries.addAll(entries);

        ((ArrayAdapter) getAdapter()).notifyDataSetChanged();
    }

    @Override
    public void showFailedOperation(String actionName, Exception e) {
        LiferayLogger.e("Could not get Entries", e);
        LiferayCrouton.error(getContext(), "Could not get Entries", e);
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

        ((GetEntriesScreenlet) getParent()).onItemClicked(_entries.get(position));
    }
}