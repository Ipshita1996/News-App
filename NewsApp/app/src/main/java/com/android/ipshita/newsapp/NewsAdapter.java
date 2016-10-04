package com.android.ipshita.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Ipshita on 26-09-2016.
 */
public class NewsAdapter extends ArrayAdapter<news> {

    public NewsAdapter(Context context, ArrayList<news> items){
        super( context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

// Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }


        news current = getItem(position);
        ViewHolder holder = new ViewHolder();
        holder.newsTitle = (TextView) listItemView.findViewById(R.id.title);
        holder.newsSubTitle = (TextView) listItemView.findViewById(R.id.stitle);
        holder.newsAuthor=(TextView) listItemView.findViewById(R.id.author);
        listItemView.setTag(holder);

        holder.newsTitle.setText(current.getTitle());
        holder.newsSubTitle.setText(current.getSubTitle());
        if (current.getHasAuthor()) {
            String[] author = current.getAuthor();
            StringBuilder authors = new StringBuilder();
            for (int i = 0; i < author.length; i++) {
                authors.append(author[i]);
                if ((i + 1) < author.length) {
                    authors.append("\n");
                }
            }
            holder.newsAuthor.setText(authors);
        } else {
            holder.newsAuthor.setVisibility(View.GONE);
        }

        return listItemView;

    }

    static class ViewHolder {
        TextView newsSubTitle;
        TextView newsTitle;
        TextView newsAuthor;
    }
}