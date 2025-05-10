package com.github.kongpf8848.androidworld.activity;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.github.kongpf8848.androidworld.R;
import com.github.kongpf8848.androidworld.databinding.ActivityCameraBinding;

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
