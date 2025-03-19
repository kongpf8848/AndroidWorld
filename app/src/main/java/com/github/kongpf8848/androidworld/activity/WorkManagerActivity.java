package com.github.kongpf8848.androidworld.activity;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.github.kongpf8848.androidworld.R;
import com.github.kongpf8848.androidworld.databinding.ActivityWorkerBinding;
import com.github.kongpf8848.androidworld.worker.DownloadWorker;

import java.util.concurrent.TimeUnit;

public class WorkManagerActivity extends BaseActivity<ActivityWorkerBinding> {

    private static final String TAG = "WorkManagerActivity";


    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.getAction())) {
                long downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                DownloadManager downloadManager = (DownloadManager) getApplicationContext().getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = downloadManager.getUriForDownloadedFile(downloadId);
                Log.d(TAG, "download_id:" + downloadId + ",download_uri:" + uri);
                try {
                    Intent installIntent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setDataAndType(uri, "application/vnd.android.package-archive");
                    Log.d(TAG, "download_id 11");
                    if ((Build.VERSION.SDK_INT >= 24)) {
                        installIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    }
                    installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Log.d(TAG, "download_id 22");
                    context.startActivity(installIntent);
                    Log.d(TAG, "download_id 66");
                } catch (Exception e) {
                    Log.d(TAG, "download_id 77:" + e);
                    e.printStackTrace();
                }
            }
        }
    };


    @Override
    protected int getLayoutId() {
        return R.layout.activity_worker;
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    @Override
    protected void onCreateEnd(@Nullable Bundle savedInstanceState) {
        super.onCreateEnd(savedInstanceState);
        binding.button1.setOnClickListener((v) -> {
            doWork1();
        });
        binding.button2.setOnClickListener((v) -> {
            doDownload();
        });
        registerReceiver(receiver, new IntentFilter(
                DownloadManager.ACTION_DOWNLOAD_COMPLETE
        ));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(receiver);
    }

    private void doWork1() {
        Constraints constraints = new Constraints.Builder().setRequiredNetworkType(NetworkType.UNMETERED).setRequiresBatteryNotLow(true).build();

        WorkRequest downloadRequest = new OneTimeWorkRequest.Builder(DownloadWorker.class).addTag("upload").setInputData(new Data.Builder().putString("IMAGE_URL", "xxx.jpg").build()).setConstraints(constraints).setBackoffCriteria( // 重试策略
                BackoffPolicy.LINEAR, 10, TimeUnit.SECONDS).build();
        WorkManager.getInstance(getApplicationContext())
                .enqueue(downloadRequest);

    }

    private void doDownload() {
        DownloadManager downloadManager = (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);
        String url = "http://hbfile.huabanimg.com/android/huaban-android.apk";
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url))
                //设置通知栏标题
                .setTitle("文件下载")
                .setDescription("正在下载新包")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                //设置保存路径（Android 10+推荐）
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "huaban-android.apk")
                //设置网络类型（WiFi或移动网络）
                .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        //加入下载队列
        downloadManager.enqueue(request);
    }


}
