package com.github.kongpf8848.androidworld.activity.task;

import com.github.kongpf8848.androidworld.R;
import com.github.kongpf8848.androidworld.databinding.ActivityTaskBinding;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;


public class TaskActivity extends BaseTaskActivity<ActivityTaskBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_task;
    }

    @Override
    protected void onCreateEnd(@Nullable Bundle savedInstanceState) {
        findViewById(R.id.button1).setOnClickListener(this::onButton1);
        findViewById(R.id.button2).setOnClickListener(this::onButton2);
        findViewById(R.id.button3).setOnClickListener(this::onButton3);
        findViewById(R.id.button4).setOnClickListener(this::onButton4);
        findViewById(R.id.button5).setOnClickListener(this::onButton5);
    }

    public void onButton1(View v) {
        View view = getWindow().getDecorView();
        Log.d("JACK8", "decorView:" + view + "," + (view instanceof FrameLayout));
        startActivity(new Intent(this, StandardActivity.class));
    }

    public void onButton2(View v) {
        startActivity(new Intent(this, SingleTopActivity.class));
    }

    public void onButton3(View v) {
        startActivity(new Intent(this, SingleTaskActivity.class));

    }

    public void onButton4(View v) {
        startActivity(new Intent(this, SingleInstanceActivity.class));
    }

    public void onButton5(View v) {
        startActivity(new Intent(this, SingleInstanceActivity.class));
    }


}
