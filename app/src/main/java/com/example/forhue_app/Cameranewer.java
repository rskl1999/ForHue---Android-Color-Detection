package com.example.forhue_app;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class Cameranewer extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {

    private static String TAG = "OpenCV_Log";
    public static final int CAMERA_REQUEST_CODE = 102;
    CameraBridgeViewBase cameraBridgeViewBase;
     Mat mat;
    private Mat mRgba;
    private Mat mGray;

    //call for flip button
    ImageButton flip_Cam;
    //0 - back camera, 1- front camera, with back cam being initialized first
    private int mCameraId=0;
    //call take picture button
    ImageButton buttonCapture;
    private int take_image = 0;
    ImageButton home_button;
    String currentPhotoPath;

    private final BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this)
    {   @Override
        public void onManagerConnected (int status){
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {
                    Log.v(TAG, "OpenCV Loaded");
                    cameraBridgeViewBase.enableView();
                } break;
                default: {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };

    public Cameranewer(){
        Log.i(TAG, "Instantiated new" + this.getClass());
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_live);
        ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CAMERA}, 101);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        int MY_PERMISSIONS_REQUEST_CAMERA = 0;


        //if camera permission is not given, it will ask for it on device
        if (ContextCompat.checkSelfPermission(Cameranewer.this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(Cameranewer.this, new String[]{
                    Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
        }
        //
        if (ContextCompat.checkSelfPermission(Cameranewer.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(Cameranewer.this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_CAMERA);
        }
        //
        if (ContextCompat.checkSelfPermission(Cameranewer.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(Cameranewer.this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_CAMERA);
        }
        //end of permissions-----------------------------------------

        cameraBridgeViewBase = (CameraBridgeViewBase) findViewById(R.id.opencv_surface_view);
        cameraBridgeViewBase.setVisibility(SurfaceView.VISIBLE);
        cameraBridgeViewBase.setCameraPermissionGranted();
        cameraBridgeViewBase.setCvCameraViewListener(this);

        //change camera [front/back]
        flip_Cam = findViewById(R.id.gallery_butt);
        //flip cam is clicked
        flip_Cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swapCamera();
            }
        });

        //home button
        home_button = findViewById(R.id.home_button);
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_cam = new Intent(Cameranewer.this, Home.class);
                startActivity(intent_cam);
            }
        });


    } //end of onCreate

    @Override
    public void onPause(){
        super.onPause();
        cameraBridgeViewBase.disableView();
       // if(mOpenCvCameraView != null){mOpenCvCameraView.disableView(); }
    }

    @Override
    public void onResume(){
        super.onResume();
        if(!OpenCVLoader.initDebug()){
            Toast.makeText(getApplicationContext(),"There is a problem in opencv",Toast.LENGTH_LONG).show();
        } else{
            mLoaderCallback.onManagerConnected(mLoaderCallback.SUCCESS);
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        // if(mOpenCvCameraView != null){ mOpenCvCameraView.disableView(); }
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        //mat = new Mat (width,height, CvType.CV_8UC4);
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
        return mRgba;
    }


    //flip camera
    private void swapCamera(){
        //first change mCameraId, if 0 change to 1, if 1 change to 0
        mCameraId = mCameraId^ 1;
        //disable current camera view
        cameraBridgeViewBase.disableView();
        //set Camera index
        cameraBridgeViewBase.setCameraIndex(mCameraId);
        //enable view
        cameraBridgeViewBase.enableView();

    }
}
