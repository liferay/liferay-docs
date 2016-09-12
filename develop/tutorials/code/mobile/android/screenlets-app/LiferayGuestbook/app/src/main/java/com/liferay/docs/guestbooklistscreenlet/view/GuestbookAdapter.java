package com.liferay.docs.guestbooklistscreenlet.view;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.liferay.docs.liferayguestbook.R;
import com.liferay.docs.model.GuestbookModel;
import com.liferay.mobile.screens.base.list.BaseListAdapter;
import com.liferay.mobile.screens.base.list.BaseListAdapterListener;

public class GuestbookAdapter extends
        BaseListAdapter<GuestbookModel, GuestbookAdapter.GuestbookViewHolder> {

    public GuestbookAdapter(int layoutId, int progressLayoutId, BaseListAdapterListener listener) {
        super(layoutId, progressLayoutId, listener);
    }

    @NonNull
    @Override
    public GuestbookViewHolder createViewHolder(View view, BaseListAdapterListener listener) {
        return new GuestbookAdapter.GuestbookViewHolder(view, listener);
    }

    @Override
    protected void fillHolder(GuestbookModel entry, GuestbookViewHolder holder) {
        holder.bind(entry);
    }

    public class GuestbookViewHolder extends BaseListAdapter.ViewHolder {

        private final TextView _name;

        public GuestbookViewHolder(View view, BaseListAdapterListener listener) {
            super(view, listener);

            _name = (TextView) view.findViewById(R.id.guestbook_name);
        }

        public void bind(GuestbookModel entry) {
            _name.setText(entry.getName());
        }

    }
}