package com.github.kongpf8848.androidworld.utils;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader;

public class PreloadLinearLayoutManager extends LinearLayoutManager {
    private int extraLayoutSpace = 800;

    public PreloadLinearLayoutManager(Context context) {
        super(context);
    }

    public PreloadLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public PreloadLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void calculateExtraLayoutSpace(RecyclerView.State state, int[] extraLayoutSpace) {
        super.calculateExtraLayoutSpace(state, extraLayoutSpace);
        // 设置前后方向的额外布局空间
        if (getOrientation() == VERTICAL) {
            extraLayoutSpace[0] = this.extraLayoutSpace; // 上方预加载区域
            extraLayoutSpace[1] = this.extraLayoutSpace; // 下方预加载区域
        } else {
            extraLayoutSpace[0] = this.extraLayoutSpace; // 左侧预加载区域
            extraLayoutSpace[1] = this.extraLayoutSpace; // 右侧预加载区域
        }
    }
}
