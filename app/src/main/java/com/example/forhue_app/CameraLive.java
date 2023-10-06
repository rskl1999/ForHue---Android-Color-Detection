package com.example.forhue_app;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraLive extends Activity implements CameraBridgeViewBase.CvCameraViewListener2 {

    private static final String TAG = "Home";

    private Mat mRgba;
    private Mat mGray;
    CameraBridgeViewBase mOpenCvCameraView;
        //call for flip button
     ImageButton flip_Cam;
        //0 - back camera, 1- front camera, with back cam being initialized first
    private int mCameraId=0;
        //call take picture button
    ImageButton cap_button;
    private int take_image = 0;
    ImageButton home_button;

    // private ImageButton buttonUpload;
    // Mat mat;

    private final BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this)

    {
        @Override
        public void onManagerConnected (int status){
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {
                    Log.i(TAG, "OpenCV loaded successfully");
                    mOpenCvCameraView.enableView();
                } break;
                default: {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };

 public CameraLive(){ Log.i(TAG, "instantiated new" + this.getClass());}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CAMERA}, 101);

        int MY_PERMISSIONS_REQUEST_CAMERA = 0;
        //if camera permission is not given, it will ask for it on device
        if (ContextCompat.checkSelfPermission(CameraLive.this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(CameraLive.this, new String[]{
                    Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
        }

        //
        if (ContextCompat.checkSelfPermission(CameraLive.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(CameraLive.this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_CAMERA);
        }
        //
        if (ContextCompat.checkSelfPermission(CameraLive.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(CameraLive.this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_CAMERA);
        }

        //if (! Python.isStarted()) {
        //    Python.start(new AndroidPlatform(this));}
        //create Python instance
        //Python py = Python.getInstance();

        setContentView(R.layout.camera_live);
        mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.opencv_surface_view);
        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
        mOpenCvCameraView.setCvCameraViewListener(this);

        flip_Cam = findViewById(R.id.gallery_butt);
        //flip cam is clicked
        flip_Cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swapCamera();
            }
        });

        cap_button = findViewById(R.id.capture_button);
        //if take_image==1, then take a pic
        cap_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (take_image == 0) {
                    take_image = 1;
                } else {
                    take_image = 0;
                }
            }
        });

        home_button = findViewById(R.id.home_button);
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_cam = new Intent(CameraLive.this, Home.class);
                startActivity(intent_cam);
            }
        });


    } //end of onCreate

    private void swapCamera(){
        //first change mCameraId, if 0 change to 1, if 1 change to 0
        mCameraId = mCameraId^ 1;
        //disable current camera view
        mOpenCvCameraView.disableView();
        //set Camera index
        mOpenCvCameraView.setCameraIndex(mCameraId);
        //enable view
        mOpenCvCameraView.enableView();

    }



    @Override
    protected void onPause(){
        super.onPause();
        if(mOpenCvCameraView !=null){
            mOpenCvCameraView.disableView();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(!OpenCVLoader.initDebug()){
            //if load success
            Log.d(TAG, "Opencv initialization is done");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        } else{
            //if not loaded
            Log.d(TAG,"Opencv is not loaded, try again");
        }
    }

    // @Override
    // public void onDestroy(){
    //    super.onDestroy();

    // }
    private final CameraBridgeViewBase.CvCameraViewListener2 cvCameraViewListener = new CameraBridgeViewBase.CvCameraViewListener2() {
    @Override
    public void onCameraViewStarted(int width, int height) {
         mRgba=new Mat(height,width, CvType.CV_8UC4);
         mGray= new Mat(height,width,CvType.CV_8UC1);
    }

    @Override
    public void onCameraViewStopped() {
        mRgba.release();
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        mRgba = inputFrame.rgba();
        mGray=inputFrame.gray();
        //but when we change cam from back to front, there is a rotation problem
        //front cam is rotated by 180
        //so when mCameraId is 1(front) rotate camera frame with 180 degree
        if(mCameraId==1){
            Core.flip(mRgba,mRgba,-1);
            Core.flip(mGray,mGray, -1);
        }

        //create function that will take picture
        //output is capture_pics which will change every capture
        //if input 1 -> 0
        take_image=take_picture_function_rgb(take_image,mRgba);
        return mRgba;
    }
 };

    private int take_picture_function_rgb(int take_image, Mat mRgba){
        if(take_image==1){
            //first add permission for writing in local storage
            Mat save_mat = new Mat();
            //rotate image by 90 degrees
            Core.flip(mRgba.t(), save_mat,1);
            //now convert image from RGBA to BGRA
            Imgproc.cvtColor(save_mat, save_mat,Imgproc.COLOR_RGBA2BGRA);
            //Create new folder named ForHue-------------------------------------------------------------
            File folder= new File(Environment.getExternalStorageDirectory().getPath()+"/ForHue");
            //check if folder exist, if not create
            boolean success = true;
            if(!folder.exists()){
                success=folder.mkdirs();
            }
            //create unique filename-------------------------------------------------
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            String currentDateandTime = sdf.format(new Date());
            //String imageFileName = "ForHue-" + currentDateandTime + "_";
            String filename=Environment.getExternalStorageDirectory().getPath()+"/ForHue"+currentDateandTime+".jpg"; //save to gallery
            //String filename = "/storage/emulated/0/DCIM/ForHue/" + imageFileName + ".jpg";
            Imgcodecs.imwrite(filename,save_mat);
            take_image=0;
        }

        return take_image;
    }

    private int take_picture_function_gray(int take_image, Mat mGray){
        if(take_image==1){
            //first add permission for writing in local storage
            Mat save_mat = new Mat();
            //rotate image by 90 degrees
            Core.flip(mGray.t(), save_mat,1);
            //now convert image from RGBA to BGRA
            //Imgproc.cvtColor(save_mat, save_mat,Imgproc.COLOR_RGBA2BGRA);
            //Create new folder named ForHue
            File folder= new File(Environment.getExternalStorageDirectory().getPath()+"/ForHue");
            //check if folder exist, if not create
            boolean success = true;
            if(!folder.exists()){
                success=folder.mkdirs();
            }
            //create unique filename
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            String currentDateandTime = sdf.format(new Date());
            String filename=Environment.getExternalStorageDirectory().getPath()+"/ForHue"+currentDateandTime+".jpg"; //save to gallery

            Imgcodecs.imwrite(filename,save_mat);
            take_image=0;
        }

        return take_image;
    }

    @Override
    public void onCameraViewStarted(int width, int height) {

    }

    @Override
    public void onCameraViewStopped() {

    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        return null;
    }
}

//CAMERA CAPTURE

//CAMERA SAVING

//KNN DETECTION OnCAMERAFRAME
