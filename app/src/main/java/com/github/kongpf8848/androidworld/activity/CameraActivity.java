package com.github.kongpf8848.androidworld.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.github.kongpf8848.androidworld.R;
import com.github.kongpf8848.androidworld.databinding.ActivityCameraBinding;
import com.github.kongpf8848.androidworld.databinding.ActivityContentProviderBinding;
import com.github.kongpf8848.androidworld.model.PhoneContact;

import java.util.ArrayList;
import java.util.List;

public class CameraActivity extends BaseActivity<ActivityCameraBinding> {

    private static final String TAG = "CameraActivity";


    @Override
    protected int getLayoutId() {
        return R.layout.activity_camera;
    }

    @Override
    protected void onCreateEnd(@Nullable Bundle savedInstanceState) {
        super.onCreateEnd(savedInstanceState);
        binding.button1.setOnClickListener(this::onButton1);

    }

    void onButton1(View view) {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            String[] cameraList = cameraManager.getCameraIdList();
            for (String cameraId : cameraList) {
                CameraCharacteristics characteristics = cameraManager.getCameraCharacteristics(cameraId);
                int face = characteristics.get(CameraCharacteristics.LENS_FACING);
                String faceStr="";
                if(face==CameraCharacteristics.LENS_FACING_BACK){
                    faceStr="后置";
                }else if(face==CameraCharacteristics.LENS_FACING_FRONT){
                    faceStr="前置";
                }else if(face==CameraCharacteristics.LENS_FACING_EXTERNAL){
                    faceStr="外置";
                }
                Log.d(TAG, "onButton1() called with: cameraId =" + cameraId + ",face:" + faceStr);
            }
        } catch (CameraAccessException e) {
            throw new RuntimeException(e);
        }
    }


}
