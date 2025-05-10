package com.github.kongpf8848.androidworld.activity.task;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.github.kongpf8848.androidworld.R;
import com.github.kongpf8848.androidworld.databinding.ActivityTaskTemplateBinding;

/**
 * Created by pengf on 2017/4/16.
 * 总结：
 * SingleInstance
 * 启动时会创建一个新的Task，而且此Activity是这个Task中的唯一成员，这个activity启动的任何其他activity都将在另外的task中打开
 */

public class SingleInstanceActivity extends BaseTaskActivity<ActivityTaskTemplateBinding> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_task_template;
    }

    @Override
    protected void onCreateEnd(Bundle savedInstanceState) {
        Button btn_info = (Button) findViewById(R.id.btn_info);
        btn_info.setText(LaunchMode.SINGLE_INSTANCE.toString());
        btn_info.setOnClickListener(v -> {
            this.startActivity(new Intent(this, TestActivity.class));
        });
    }
}
