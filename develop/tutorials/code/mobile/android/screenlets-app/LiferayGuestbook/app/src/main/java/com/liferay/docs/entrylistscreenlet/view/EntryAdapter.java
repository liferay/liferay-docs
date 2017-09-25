package com.liferay.docs.entrylistscreenlet.view;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.liferay.docs.liferayguestbook.R;
import com.liferay.docs.model.EntryModel;
import com.liferay.mobile.screens.base.list.BaseListAdapter;
import com.liferay.mobile.screens.base.list.BaseListAdapterListener;


public class EntryAdapter extends BaseListAdapter<EntryModel, EntryAdapter.EntryViewHolder> {

    public EntryAdapter(int layoutId, int progressLayoutId, BaseListAdapterListener listener) {
        super(layoutId, progressLayoutId, listener);
    }

    @NonNull
    @Override
    public EntryViewHolder createViewHolder(View view, BaseListAdapterListener listener) {
        return new EntryAdapter.EntryViewHolder(view, listener);
    }

    @Override
    protected void fillHolder(EntryModel entry, EntryViewHolder holder) {
        holder.bind(entry);
    }

    public class EntryViewHolder extends BaseListAdapter.ViewHolder {

        private final TextView message;
        private final TextView name;

        public EntryViewHolder(View view, BaseListAdapterListener listener) {
            super(view, listener);

            message = (TextView) view.findViewById(R.id.entry_message);
            name = (TextView) view.findViewById(R.id.entry_name);
        }

        public void bind(EntryModel entry) {
            message.setText(entry.getMessage());
            name.setText(entry.getName());
        }

    }
}