package com.github.kongpf8848.androidworld.utils;

import static android.view.WindowInsetsAnimation.Callback.DISPATCH_MODE_STOP;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class KeyboardUtils implements ViewTreeObserver.OnGlobalLayoutListener {

    private KeyboardListener listener;
    private int mTempKeyboardHeight;
    private int navigationBarHeight;
    private Activity activity;
    private View decorView;

    public KeyboardUtils(Activity activity, KeyboardListener listener) {
        this.activity = activity;
        this.listener = listener;
        decorView = activity.getWindow().getDecorView();
        navigationBarHeight = getNavigationBarHeight(activity);

    }

    public void enable() {
        if (activity != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                activity.getWindow().getDecorView().setWindowInsetsAnimationCallback(new WindowInsetsAnimation.Callback(DISPATCH_MODE_STOP) {
                    @NonNull
                    @Override
                    public WindowInsets onProgress(@NonNull WindowInsets windowInsets, @NonNull List<WindowInsetsAnimation> list) {

                        int imeHeight = windowInsets.getInsets(WindowInsetsCompat.Type.ime()).bottom;
                        int navHeight = windowInsets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom;
                        boolean hasNavigationBar = windowInsets.isVisible(WindowInsetsCompat.Type.navigationBars()) &&
                                windowInsets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom > 0;

                        listener.onKeyboardChange(true, hasNavigationBar ? Math.max(imeHeight - navHeight, 0) : imeHeight);

                        return windowInsets;
                    }
                });
            } else {
                activity.getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(this);
            }
        }
    }

    public void disable() {
        if (activity != null) {
            activity.getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
    }


    @Override
    public void onGlobalLayout() {
        if (activity != null) {
            Rect r = new Rect();
            FrameLayout contentView = decorView.findViewById(android.R.id.content);
            decorView.getWindowVisibleDisplayFrame(r);

            int screenHeight = contentView.getRootView().getHeight();
            Log.d("KeyboardUtils", "onGlobalLayout() called,navigationBarHeight:" + navigationBarHeight + ",screenHeight:" + screenHeight + ",rect:" + r);
            int keyboardHeight = screenHeight - r.bottom - navigationBarHeight;
            if (keyboardHeight != mTempKeyboardHeight) {
                mTempKeyboardHeight = keyboardHeight;
                boolean isPopup = false;
                if (keyboardHeight > navigationBarHeight) {
                    isPopup = true;
                }
                if (keyboardHeight < 0) {
                    keyboardHeight = 0;
                }
                if (listener != null) {
                    listener.onKeyboardChange(isPopup, keyboardHeight);
                }
            }
        }
    }

    private int getNavigationBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return context.getResources().getDimensionPixelSize(resourceId);
        } else {
            return 0;
        }
    }
}