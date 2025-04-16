package com.github.kongpf8848.androidworld;

import android.os.Handler;
import android.os.Looper;
import android.os.Messenger;
import android.os.Parcelable;

import java.io.Serializable;

public class Test {
    void aa(){
        Handler handler=new Handler();
        Messenger xx=new Messenger(handler);
        Looper.prepare();
        Looper.loop();
        Runtime.getRuntime().gc();

    }
}
