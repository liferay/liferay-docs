package com.liferay.docs.liferayguestbook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.liferay.docs.liferayguestbook.R;
import com.liferay.docs.liferayguestbook.model.GuestbookModel;

import java.util.List;

public class GuestbooksAdapter extends ArrayAdapter<GuestbookModel> {

    public GuestbooksAdapter(Context context, List<GuestbookModel> guestbooks) {
        super(context, 0, guestbooks);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;

        LayoutInflater inflater = LayoutInflater.from(getContext());

        if (view == null) {
            view = inflater.inflate(R.layout.item_guestbook, parent, false);

            holder = new ViewHolder();

            holder.guestbookName = (TextView)view.findViewById(R.id.guestbook_name);

            view.setTag(holder);
        }
        else {
            holder = (ViewHolder)view.getTag();
        }

        GuestbookModel guestbook = getItem(position);

        holder.guestbookName.setText(guestbook.getGuestbookName());

        return view;
    }

    class ViewHolder {

        TextView guestbookName;
    }
}