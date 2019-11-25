package com.android.testapp.activity;

import android.app.ActivityManager;
import android.app.PictureInPictureParams;
import android.app.RemoteAction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.drawable.Icon;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Rational;

import com.android.testapp.R;

import java.util.ArrayList;

public class TestPictureInPictureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_picture_in_picture);
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.pptv.intent.action.SWITCH_PIP_MODE");
        BroadcastReceiver receiver =new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //onNewIntent(intent);
            }
        };
        mAm = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
/*
        mActivityManager.dismissPip();
        PlaybackControlsRow.PictureInPictureAction action =
        new PlaybackControlsRow.PictureInPictureAction(activity);
        registerReceiver(receiver,filter);
        MediaSession.setCallback();
        MediaSession.setMediaButtonReceiver();
        boolean supportPip = getPackageManager().hasSystemFeature(PackageManager.FEATURE_PICTURE_IN_PICTURE);
*/

        createMediaSession();
    }



    public static final int  MSG_FULL_SCREEN = 1000;
    public static  final int  MSG_MOVE_PIP_POSIITION = 1001;



    MediaSession mSession;
    ActivityManager mAm;
    Handler mhandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_FULL_SCREEN:
                    PictureInPictureParams prams= mPictureInPictureParamsBuilder.setSourceRectHint(new Rect(0,0,1920,1080)).build();
                    enterPictureInPictureMode(prams);
                    break;
                case MSG_MOVE_PIP_POSIITION:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private final PictureInPictureParams.Builder mPictureInPictureParamsBuilder = new PictureInPictureParams.Builder();


    public void pipMode(){
        Rational aspectRatio = new Rational(400, 600);
        mPictureInPictureParamsBuilder.setAspectRatio(aspectRatio).build();
        ArrayList actionlist = new ArrayList<RemoteAction>();
        actionlist.add(new RemoteAction(Icon.createWithResource(this,R.drawable.ic_launcher_background),"testtitle","testdesc",null));
        mPictureInPictureParamsBuilder.setActions(new ArrayList<RemoteAction>());
        enterPictureInPictureMode(mPictureInPictureParamsBuilder.build());
    }


    public void fullscreen(){
        Rational aspectRatio = new Rational(1920, 2080);
        mPictureInPictureParamsBuilder.setAspectRatio(aspectRatio).build();
        ArrayList actionlist = new ArrayList<RemoteAction>();
        actionlist.add(new RemoteAction(Icon.createWithResource(this,R.drawable.ic_launcher_background),"testtitle","testdesc",null));
        mPictureInPictureParamsBuilder.setActions(new ArrayList<RemoteAction>());
        enterPictureInPictureMode(mPictureInPictureParamsBuilder.build());
    }

    private void createMediaSession() {
        if (mSession == null) {
            mSession = new MediaSession(this, getString(R.string.app_name));
            mSession.setFlags(MediaSession.FLAG_HANDLES_MEDIA_BUTTONS |
                    MediaSession.FLAG_HANDLES_TRANSPORT_CONTROLS);
            mSession.setActive(true);
         setMediaController(new MediaController(this, mSession.getSessionToken()));
        }
    }



    @Override
    protected void onResume() {

        super.onResume();
    }


    @Override
    public void onUserLeaveHint () {
/*        PictureInPictureParams.Builder builder=new PictureInPictureParams.Builder();
        builder.setActions(new ArrayList<RemoteAction>());
        if (iWantToBeInPipModeNow()) {
            enterPictureInPictureMode();
        }*/
        enterPictureInPictureMode();

    }

    boolean iWantToBeInPipModeNow(){
        return false;
    }

    @Override
    protected void onPause() {
        if (isInPictureInPictureMode()) {
            // Continue playback
        } else {
            // Use existing playback logic for paused Activity behavior.
            requestVisibleBehind(false);
        }
        super.onPause();
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
        if (isInPictureInPictureMode) {
            // Hide the full-screen UI (controls, etc.) while in picture-in-picture mode.
        } else {
            // Restore the full-screen UI.

        }
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);
    }


}
