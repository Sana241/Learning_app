package com.example.learningproject;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class BookAsyncLoader extends AsyncTaskLoader<String> {

    String queryString;

    public BookAsyncLoader(@NonNull Context context, String inputString) {
        super(context);
        queryString = inputString;
    }

    @Nullable
    @Override
    public String loadInBackground() {
        NetworkUtils networkUtils = new NetworkUtils();
        return networkUtils.getBookInfo(queryString);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        onForceLoad();
    }
}
