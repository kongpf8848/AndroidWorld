
package com.github.kongpf8848.androidworld.swipeback;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.customview.widget.ViewDragHelper;


import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class SwipeBackActivity extends AppCompatActivity implements SwipeBackLayout.SwipeListener {

    protected SwipeBackLayout mSwipeBackLayout;
    private boolean mOverrideExitAniamtion = false;
    public static final int SMOOTH_WIDTH = 50;
    private boolean mIsFinishing;
    private final int ALPHA_COLOR = 0x60ffffff;
    private boolean canSwipeBack = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        onSwipeBackStart(savedInstanceState);
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O && isTranslucentOrFloating()) {
            fixOrientation();
        }
        super.onCreate(savedInstanceState);

        mSwipeBackLayout = new SwipeBackLayout(this);
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        mSwipeBackLayout.setEdgeSize(dip2px(SMOOTH_WIDTH));
        mSwipeBackLayout.setEnableGesture(canUseSwipeBackLayout());
        mSwipeBackLayout.setSwipeListener(this);
        onSwipeBackEnd(savedInstanceState);
    }

    protected void onSwipeBackStart(Bundle savedInstanceState){

    }
    protected void onSwipeBackEnd(Bundle savedInstanceState){

    }

    public void setCanSwipeBack(boolean canSwipeBack) {
        this.canSwipeBack = canSwipeBack;
        mSwipeBackLayout.setEnableGesture(canSwipeBack);
    }


    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (canUseSwipeBackLayout()) {
            if (mSwipeBackLayout != null) {
                mSwipeBackLayout.attachToActivity(this);
            }
        }
    }

    @Override
    public <T extends View> T findViewById(int id) {
        T v = super.findViewById(id);
        if (v != null)
            return v;
        if (canUseSwipeBackLayout()) {
            if (mSwipeBackLayout != null) {
                mSwipeBackLayout.findViewById(id);
            }
        }
        return null;
    }


    public void setOverrideExitAniamtion(boolean override) {
        if (canUseSwipeBackLayout()) {
            if (mSwipeBackLayout != null) {
                mOverrideExitAniamtion = override;
            }
        }
    }

    private void scrollToFinishActivity() {
        if (mSwipeBackLayout != null) {
            mSwipeBackLayout.scrollToFinishActivity();
        }
    }

    @Override
    public void finish() {
        if (canUseSwipeBackLayout()) {
            if (mOverrideExitAniamtion && !mIsFinishing) {
                scrollToFinishActivity();
                mIsFinishing = true;
                return;
            }
            mIsFinishing = false;
            onScrollStateChange(ViewDragHelper.STATE_IDLE,1.0f);
        }
        super.finish();
    }


    public boolean canUseSwipeBackLayout() {
        return canSwipeBack;
    }

    @Override
    public void onScrollStateChange(int state, float scrollPercent) {

    }

    @Override
    public void onEdgeTouch(int edgeFlag) {

    }

    @Override
    public void onScrollOverThreshold() {

    }

    public static int dip2px(float dpValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O && isTranslucentOrFloating()) {
            return;
        }
        super.setRequestedOrientation(requestedOrientation);
    }

    private boolean isTranslucentOrFloating() {
        boolean isTranslucentOrFloating = false;
        try {
            int[] styleableRes = (int[]) Class.forName("com.android.internal.R$styleable").getField("Window").get(null);
            final TypedArray ta = obtainStyledAttributes(styleableRes);
            Method m = ActivityInfo.class.getMethod("isTranslucentOrFloating", TypedArray.class);
            m.setAccessible(true);
            isTranslucentOrFloating = (boolean) m.invoke(null, ta);
            m.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isTranslucentOrFloating;
    }
    private boolean fixOrientation() {
        try {
            Field field = Activity.class.getDeclaredField("mActivityInfo");
            field.setAccessible(true);
            ActivityInfo o = (ActivityInfo) field.get(this);
            o.screenOrientation =ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;
            field.setAccessible(false);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
