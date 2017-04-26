package com.liferay.docs.entrylistscreenlet.view;

import android.content.Context;
import android.util.AttributeSet;

import com.liferay.docs.liferayguestbook.R;
import com.liferay.docs.model.EntryModel;
import com.liferay.mobile.screens.base.list.BaseListScreenletView;


public class EntryListView extends BaseListScreenletView<EntryModel,
        EntryAdapter.EntryViewHolder, EntryAdapter> {

    public EntryListView(Context context) {
        super(context);
    }

    public EntryListView(Context context, AttributeSet attributes) {
        super(context, attributes);
    }

    public EntryListView(Context context, AttributeSet attributes, int defaultStyle) {
        super(context, attributes, defaultStyle);
    }

    @Override
    protected EntryAdapter createListAdapter(int itemLayoutId, int itemProgressLayoutId) {
        return new EntryAdapter(itemLayoutId, itemProgressLayoutId, this);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.entry_row;
    }
}