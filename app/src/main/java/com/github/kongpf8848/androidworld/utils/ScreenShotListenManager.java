package com.github.kongpf8848.androidworld.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ScreenShotListenManager {

    private static final String TAG = "ScreenShotListenManager";

    private static final String[] MEDIA_PROJECTIONS = {
            MediaStore.Images.ImageColumns.DATA,
            MediaStore.Images.ImageColumns.DATE_ADDED
    };

    private static final String[] KEYWORDS = {
            "screenshot", "screen_shot", "screen-shot", "screen shot",
            "screencapture", "screen_capture", "screen-capture", "screen capture",
            "screencap", "screen_cap", "screen-cap", "screen cap"
    };

    private final static List<String> sHasCallbackPaths = new ArrayList<>();
    private Context mContext;
    private OnScreenShotListener mListener;

    private MediaContentObserver mInternalObserver;
    private MediaContentObserver mExternalObserver;
    private Handler mHandler;
    private boolean isListened;

    private ScreenShotListenManager(Context context) {

        if (context == null) {
            throw new IllegalArgumentException("The context must not be null.");
        }

        mContext = context;

        HandlerThread handlerThread = new HandlerThread("Screen_Observer");
        handlerThread.start();
        mHandler = new Handler(handlerThread.getLooper());
    }

    public static ScreenShotListenManager newInstance(Context context) {
        return new ScreenShotListenManager(context);
    }


    public void setListener(OnScreenShotListener listener) {
        mListener = listener;
    }

    public void startListen() {
        if (isListened) {
            return;
        }
        Log.d(TAG, "startListen() called");
        mInternalObserver = new MediaContentObserver(MediaStore.Images.Media.INTERNAL_CONTENT_URI, mHandler);
        mExternalObserver = new MediaContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, mHandler);
        mContext.getContentResolver().registerContentObserver(
                MediaStore.Images.Media.INTERNAL_CONTENT_URI,
                true,
                mInternalObserver
        );
        mContext.getContentResolver().registerContentObserver(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                true,
                mExternalObserver
        );
        isListened=true;
    }


    public void stopListen() {
        if(!isListened){
            return;
        }
        Log.d(TAG, "stopListen() called");
        if (mInternalObserver != null) {
            try {
                mContext.getContentResolver().unregisterContentObserver(mInternalObserver);
            } catch (Exception e) {
                e.printStackTrace();
            }
            mInternalObserver = null;
        }
        if (mExternalObserver != null) {
            try {
                mContext.getContentResolver().unregisterContentObserver(mExternalObserver);
            } catch (Exception e) {
                e.printStackTrace();
            }
            mExternalObserver = null;
        }
    }

    private void handleMediaContentChange(Uri contentUri) {
        Cursor cursor = null;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Bundle bundle = new Bundle();
                bundle.putStringArray(ContentResolver.QUERY_ARG_SORT_COLUMNS, new String[]{MediaStore.Images.ImageColumns.DATE_ADDED});
                bundle.putInt(ContentResolver.QUERY_ARG_SORT_DIRECTION, ContentResolver.QUERY_SORT_DIRECTION_DESCENDING);
                bundle.putInt(ContentResolver.QUERY_ARG_LIMIT, 1);
                bundle.putInt(ContentResolver.QUERY_ARG_OFFSET, 0);
                cursor = mContext.getContentResolver().query(
                        contentUri,
                        MEDIA_PROJECTIONS,
                        bundle, null
                );
            } else {
                cursor = mContext.getContentResolver().query(
                        contentUri,
                        MEDIA_PROJECTIONS,
                        null,
                        null,
                        MediaStore.Images.ImageColumns.DATE_ADDED + " desc limit 1"
                );
            }
            if (cursor == null) {
                return;
            }
            if (!cursor.moveToFirst()) {
                return;
            }

            int dataIndex = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            int dateAddedIndex = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATE_ADDED);
            String data = cursor.getString(dataIndex);
            long dateAdded=0L;
            if(dateAddedIndex>=0){
                dateAdded = cursor.getLong(dateAddedIndex);
            }
            Log.d(TAG, "handleMediaContentChange():"+data+",dateAdded:"+dateAdded+","+(System.currentTimeMillis()-dateAdded*1000));
            handleMediaRowData(data,dateAdded);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
    }

    private void handleMediaRowData(String data,long dataAdded) {
        if (checkScreenShot(data)) {
            //排除掉重复回调
            //只取1秒之内的截屏
            if (mListener != null && !checkCallback(data) && ((System.currentTimeMillis()-dataAdded*1000)<1500)) {
                mListener.onShot(data);
            }
        }
    }


    private boolean checkScreenShot(String data) {

        if (TextUtils.isEmpty(data)) {
            return false;
        }
        data = data.toLowerCase();
        for (String keyWork : KEYWORDS) {
            if (data.contains(keyWork)) {
                return true;
            }
        }
        return false;
    }


    private boolean checkCallback(String imagePath) {

        if (sHasCallbackPaths.contains(imagePath)) {
            return true;
        }

        if (sHasCallbackPaths.size() >= 20) {
            for (int i = 0; i < 5; i++) {
                sHasCallbackPaths.remove(0);
            }
        }
        sHasCallbackPaths.add(imagePath);
        return false;
    }

    public interface OnScreenShotListener {
        void onShot(String imagePath);
    }


    private class MediaContentObserver extends ContentObserver {
        private final Uri mContentUri;

        public MediaContentObserver(Uri contentUri, Handler handler) {
            super(handler);
            mContentUri = contentUri;
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            handleMediaContentChange(mContentUri);
        }
    }
}
