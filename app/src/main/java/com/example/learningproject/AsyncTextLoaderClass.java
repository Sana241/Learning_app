package com.example.learningproject;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.lang.ref.WeakReference;
import java.util.Random;

public class AsyncTextLoaderClass extends AsyncTaskLoader<String> {

    public AsyncTextLoaderClass(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public String loadInBackground() {
        Random random = new Random();
        int secTime = (random.nextInt(11)) * 300;
        try {
            Thread.sleep(secTime);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        return "Awake from sleep after " + secTime + " milliSeconds.";
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        onForceLoad();
    }
}
