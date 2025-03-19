package com.github.kongpf8848.androidworld.worker;

import android.app.DownloadManager;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class DownloadWorker extends Worker {
    private static final String TAG = "DownloadWorker";

    public DownloadWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
    }

    @Override
    public Result doWork() {
        Log.i(TAG, "doWork:" + Thread.currentThread().getName());
        String url = getInputData().getString("DOWNLOAD_URL");
        Log.i(TAG, "DOWNLOAD_URL: " + url);
        doDownload(url);
        return Result.success();
    }

    private void doDownload(String url) {
        Log.i(TAG, "doDownload:" + url);
    }
}
