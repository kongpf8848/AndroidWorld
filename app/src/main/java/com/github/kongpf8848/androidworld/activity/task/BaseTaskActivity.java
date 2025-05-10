package com.github.kongpf8848.androidworld.activity.task;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.databinding.ViewDataBinding;

import com.github.kongpf8848.androidworld.R;


import com.github.kongpf8848.androidworld.activity.BaseActivity;


import java.util.List;

/**
 * Created by pengf on 2017/5/5.
 * 当调用到onNewIntent(intent)的时候，需要在onNewIntent()中使用setIntent(intent)
 * 赋值给Activity新的Intent.否则，后续的getIntent()都是得到老的Intent。
 */

public abstract class BaseTaskActivity<T extends ViewDataBinding> extends BaseActivity<T> {
    private static final String TAG = "BaseTaskActivity";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_task:
                showTaskInfo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void showTaskInfo() {
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfoList = am.getRunningTasks(10);
        for (ActivityManager.RunningTaskInfo runningTaskInfo : runningTaskInfoList) {
            if (runningTaskInfo.id > 5) {
                Log.d(TAG, "==========================================================");
                Log.d(TAG, "Task id: " + runningTaskInfo.id);
                Log.d(TAG, "Task description: " + runningTaskInfo.description);
                Log.d(TAG, "Task number of activities: " + runningTaskInfo.numActivities);
                Log.d(TAG, "Task topActivity: " + runningTaskInfo.topActivity);
                Log.d(TAG, "Task baseActivity: " + runningTaskInfo.baseActivity.toString());

            }
        }
    }
}
