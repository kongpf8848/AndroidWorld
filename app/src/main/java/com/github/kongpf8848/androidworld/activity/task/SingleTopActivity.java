package com.github.kongpf8848.androidworld.activity.task;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.github.kongpf8848.androidworld.R;
import com.github.kongpf8848.androidworld.databinding.ActivityTaskTemplateBinding;


public class SingleTopActivity extends BaseTaskActivity<ActivityTaskTemplateBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_task_template;
    }

    @Override
    protected void onCreateEnd(Bundle savedInstanceState) {
        Button btn_info = (Button) findViewById(R.id.btn_info);
        btn_info.setText(LaunchMode.SINGLE_TOP.toString());
        btn_info.setOnClickListener(v -> {
            this.startActivity(new Intent(this, SingleTopActivity.class));
        });
    }


}
