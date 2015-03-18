package com.liferay.docs.liferayguestbook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.liferay.docs.liferayguestbook.R;
import com.liferay.docs.liferayguestbook.model.EntryModel;

import java.util.List;

public class EntriesAdapter extends ArrayAdapter<EntryModel> {

    public EntriesAdapter(Context context, List<EntryModel> entries) {
        super(context, 0, entries);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;

        LayoutInflater inflater = LayoutInflater.from(getContext());

        if (view == null) {
            view = inflater.inflate(R.layout.item_entry, parent, false);

            holder = new ViewHolder();

            holder.entryMessage = (TextView)view.findViewById(R.id.entry_message);
            holder.entryName = (TextView)view.findViewById(R.id.entry_name);

            view.setTag(holder);
        }
        else {
            holder = (ViewHolder)view.getTag();
        }

        EntryModel entry = getItem(position);

        holder.entryMessage.setText(entry.getMessage());
        holder.entryName.setText(entry.getName());

        return view;
    }

    class ViewHolder {

        TextView entryMessage, entryName;
    }
}