package com.android.ipshita.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Ipshita on 04-10-2016.
 */
public class NewsLoader extends AsyncTaskLoader<List<news>> {

    private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<news> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        return QueryUtils.extractNewsFeed(mUrl);
    }
}

