package com.android.testapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.android.testapp.R;

public class TestSurfaceView extends AppCompatActivity  implements  SurfaceHolder.Callback{

    SurfaceView mSurfaceView;
    SurfaceHolder mHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_surface_view);
        mSurfaceView= (SurfaceView) findViewById(R.id.sufaceview);
        mHolder=mSurfaceView.getHolder();
        mHolder.addCallback(this);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("rongqingyu","surface created");

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d("rongqingyu","surface changed format = "+format+" width="+width+"height="+height);
        mHolder.setSizeFromLayout();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d("rongqingyu","surface destoryed");


    }
}
