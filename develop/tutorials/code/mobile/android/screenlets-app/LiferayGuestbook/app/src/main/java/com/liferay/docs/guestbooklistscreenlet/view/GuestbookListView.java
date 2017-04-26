package com.liferay.docs.guestbooklistscreenlet.view;

import android.content.Context;
import android.util.AttributeSet;

import com.liferay.docs.liferayguestbook.R;
import com.liferay.docs.model.GuestbookModel;
import com.liferay.mobile.screens.base.list.BaseListScreenletView;

public class GuestbookListView extends BaseListScreenletView<GuestbookModel,
        GuestbookAdapter.GuestbookViewHolder, GuestbookAdapter> {

    public GuestbookListView(Context context) {
        super(context);
    }

    public GuestbookListView(Context context, AttributeSet attributes) {
        super(context, attributes);
    }

    public GuestbookListView(Context context, AttributeSet attributes, int defaultStyle) {
        super(context, attributes, defaultStyle);
    }

    @Override
    protected GuestbookAdapter createListAdapter(int itemLayoutId, int itemProgressLayoutId) {
        return new GuestbookAdapter(itemLayoutId, itemProgressLayoutId, this);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.guestbook_row;
    }
}