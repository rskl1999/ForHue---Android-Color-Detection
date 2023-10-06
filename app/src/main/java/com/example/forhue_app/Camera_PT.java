package com.example.forhue_app;

import static org.opencv.core.CvType.CV_8UC4;
import static org.opencv.imgproc.Imgproc.cvtColor;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instances;
import weka.core.SerializationHelper;

public class Camera_PT extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2{

    private static String TAG = "OpenCV_Log";
    public static final int CAMERA_REQUEST_CODE = 102;
    CameraBridgeViewBase cameraBridgeViewBase;
    private Mat mSave;
    private Mat mRgba;
    private Mat mRgba2;
    private Mat save_mat;
    private Mat mGray;
    private Mat mHsv;
    private Mat mRgbaT;
    private Mat mRgbaF;

    //call for flip button
    ImageButton flip_Cam;
    //0 - back camera, 1- front camera, with back cam being initialized first
    private int mCameraId=0;
    //call take picture button
    ImageButton buttonCapture;
    ImageButton refresh;
    private int take_image = 0;
    ImageButton pause, resume;
    ImageButton home_button;
    TextView colorid;
    //Reso
    ImageButton resolution;
    private ListView reso_list;
    private int show_reso_list = 0;
    String currentPhotoPath;
    TextView widthdim;
    TextView heightdim;
    // Camera cam = null;// has to be static, otherwise onDestroy() destroys it
    public static android.hardware.Camera camera;
    public static android.hardware.Camera.Parameters params;
    private boolean isFlashOn = true;
    private boolean hasFlash;
    ImageButton flash;
    //TTS
    TextToSpeech tts;
    ImageButton b1;
    String result1;
    String liveC;
    //Set languages
    public static final String[] languages = {"Switch language:","Arabic (اللغة العربية)", "Chinese (普通话)", "English", "Hindi (हिंदुई)", "Portuguese (português)", "Tagalog"};
    ListView listView;
    ImageButton langicon;
    private int show_lang_list = 0;
    String cameraId = null; // Usually front camera is at 0 position.
    int fw = 0;
    int fh = 0;
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

    @SuppressLint({"MissingInflatedId", "WrongViewCast", "SuspiciousIndentation"})
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    setContentView(R.layout.camera_live);
    ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CAMERA}, 101);
    //for full screen
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    //PERMISSIONS -----------------------------****
    int MY_PERMISSIONS_REQUEST_CAMERA=0;
    // if camera permission is not given it will ask for it on device
    if (ContextCompat.checkSelfPermission(Camera_PT.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED){
    ActivityCompat.requestPermissions(Camera_PT.this, new String[] {Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
        }
    //if camera permission is not given, it will ask for it on device
    if (ContextCompat.checkSelfPermission(Camera_PT.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
    ActivityCompat.requestPermissions(Camera_PT.this, new String[]{ Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
        }
    //Write External
    if (ContextCompat.checkSelfPermission(Camera_PT.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
    ActivityCompat.requestPermissions(Camera_PT.this, new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_CAMERA);
        }
    // Read External
    if (ContextCompat.checkSelfPermission(Camera_PT.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
    ActivityCompat.requestPermissions(Camera_PT.this, new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_CAMERA);
        }
    else {
        // No explanation needed, we can request the permission.
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
    } //end of permissions-----------------------*/

        cameraBridgeViewBase = (CameraBridgeViewBase) findViewById(R.id.opencv_surface_view);
        cameraBridgeViewBase.setVisibility(SurfaceView.VISIBLE);
        cameraBridgeViewBase.setCameraPermissionGranted();
        cameraBridgeViewBase.setCvCameraViewListener(this);


        //Flip Cam (front/back) -----------------*/
        flip_Cam = findViewById(R.id.fliprotate);
        //flip cam is clicked
        flip_Cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        Toast.makeText(Camera_PT.this, "Camera changed view", Toast.LENGTH_SHORT).show();
        swapCamera();

            }
        });
        //Home --------------------------*/
        home_button = findViewById(R.id.home_button);
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        Intent intent_cam = new Intent(Camera_PT.this, Home.class);
        startActivity(intent_cam);
            }
        });
        //Pause ------------------------*/
        pause = findViewById(R.id.pauseButt);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        Toast.makeText(Camera_PT.this, "Framing has been stopped/paused", Toast.LENGTH_SHORT).show();
        onPause();
            }
        });
        //Resume -----------------------------------------------*/
        resume = findViewById(R.id.resumeButt);
        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        Toast.makeText(Camera_PT.this, "Camera framing resumed", Toast.LENGTH_SHORT).show();
        onResume();
            }
        });
        //TTS -----------------------------------------------*/
        colorid = findViewById(R.id.colorID);
        b1 = findViewById(R.id.imageButton);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
       tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                        if(i==TextToSpeech.SUCCESS){
            tts.setLanguage(Locale.US);
            tts.setSpeechRate(1.0f);
       tts.speak(colorid.getText().toString(),TextToSpeech.QUEUE_ADD, null);
                        }
                    }
                });
            }
        }); //end of tts

        buttonCapture = findViewById(R.id.capture_button);
       try {
           //CHANGE COLOR LANGUAGE --------------------*/
           listView = findViewById(R.id.set_lang);
           langicon = findViewById(R.id.langicon);
           switchLanguage(listView, langicon);

           //resolution --------------------
           resolution = findViewById(R.id.reso_button);
           reso_list = findViewById(R.id.set_reso);
           widthdim = findViewById(R.id.widthdim);
           heightdim = findViewById(R.id.heightdim);
           changeResolution(resolution, reso_list);

       } catch (Exception e){
           e.printStackTrace();
           }

    } //end of onCreate

    private void changeResolution(ImageButton resolution, ListView reso_list){

        camera = android.hardware.Camera.open();
        android.hardware.Camera.Parameters params = camera.getParameters();

        List<android.hardware.Camera.Size> sizes = params.getSupportedPreviewSizes();
        ArrayList<String> resolution_arraylist = new ArrayList();

        int frameHeight_S = 0;
         int frameWidth_S = 0;
        for(int i=0; i<sizes.size();i++){ //sort
            try {
            int frameWidth = (int) sizes.get(i).width;
            int frameHeight = (int) sizes.get(i).height;
            //convert int to string
            //String frameWidth_S = Integer.toString(frameWidth);
            //String frameHeight_S = Integer.toString(frameHeight);
            //Since it is rotated-----------------

            frameHeight_S = Integer.parseInt(Integer.toString(frameWidth));
            frameWidth_S = Integer.parseInt(Integer.toString(frameHeight));
            //append list
            //if(frameWidth_S <= 720 && frameHeight_S <= 720) {
            resolution_arraylist.add(frameWidth_S + "x" + frameHeight_S);
            Log.w("Camera2", frameWidth_S + "x" + frameHeight_S);


        }catch(Exception e){
                e.printStackTrace();
            }
        }

        //create an arrayAdapter
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,R.layout.reso_items, R.id.textView, resolution_arraylist);
        //for null arrayAdapter
        String null_array[] = {};
        ArrayAdapter<String> null_arrayAdapter = new ArrayAdapter<>(this, R.layout.reso_items,R.id.textView, null_array);
        reso_list.setAdapter(null_arrayAdapter);
        resolution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(show_reso_list==0){ //when button is clicked
                    try{
                    reso_list.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.INVISIBLE);
                    Toast.makeText(Camera_PT.this, "576 x 1280 is the recommended size for portrait capture", Toast.LENGTH_SHORT).show();
                    reso_list.setAdapter(arrayAdapter);
                    show_reso_list = 1; }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                } else{
                    reso_list.setVisibility(View.INVISIBLE);
                    reso_list.setAdapter(null_arrayAdapter);
                    show_reso_list = 0;
                }
            }
        });

        reso_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //this will give position of arrayAdapter we have clicked
               try {
                   int frameWidth2 = (int) sizes.get(position).width;
                   int frameHeight2 = (int) sizes.get(position).height;
                   String h = String.valueOf(frameWidth2); //needs string for settext
                   String w = String.valueOf(frameHeight2);
                    // mCamera is a Camera object
                    fw = Integer.parseInt(w);
                    fh = Integer.parseInt(h);
                   cameraBridgeViewBase.disableView();
                   cameraBridgeViewBase.setMaxFrameSize(frameWidth2, frameHeight2);
                   cameraBridgeViewBase.enableView();
                   //after changing resolution
                   //clear listview
                   reso_list.setAdapter(null_arrayAdapter);
                   show_reso_list = 0;
                   reso_list.setVisibility(View.INVISIBLE);
                   widthdim.setText(w);
                   heightdim.setText(h);

               }catch(Exception e){
                   e.printStackTrace();
               }
            }
        });
    }
    private void switchLanguage(ListView listView, ImageButton langicon){
        //create an arrayAdapter
        ArrayAdapter<String> langAdapter = new ArrayAdapter<String>(this, R.layout.reso_items, languages);
        listView.setAdapter(langAdapter);

        langicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (show_lang_list == 0) { //when button is clicked
                    listView.setVisibility(View.VISIBLE);
                    reso_list.setVisibility(View.INVISIBLE);
                    //setlang.setAdapter(langarrayAdapter);
                    show_lang_list = 1;
                } else {
                    listView.setVisibility(View.INVISIBLE);
                    //setlang.setAdapter(langnull_arrayAdapter);
                    show_lang_list = 0;
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_SHORT).show();

        String selectedLang = parent.getItemAtPosition(position).toString();
         if (show_lang_list == 0) {
         show_lang_list = 1;
         } else {
    switch (selectedLang) {
        case "Arabic (اللغة العربية)":
             try {
    Intent intent_cam = new Intent(Camera_PT.this, Camera_AR.class);
    startActivity(intent_cam);
    Toast.makeText(getApplicationContext(), "COLOR LANGUAGE CHANGED", Toast.LENGTH_LONG).show();
    listView.setVisibility(View.INVISIBLE);
            } catch (Exception e) {
             e.printStackTrace();
              }
              break;

       case "Chinese (普通话)":
            try {
    Intent intent_cam = new Intent(Camera_PT.this, Camera_ZH.class);
    startActivity(intent_cam);
    Toast.makeText(getApplicationContext(), "COLOR LANGUAGE CHANGED", Toast.LENGTH_LONG).show();
    listView.setVisibility(View.INVISIBLE);
           } catch (Exception e) {
             e.printStackTrace();
             }
             break;
      case "English":
             try {
    Intent intent_cam = new Intent(Camera_PT.this, Camera2.class);
    startActivity(intent_cam);
    Toast.makeText(getApplicationContext(), "COLOR LANGUAGE CHANGED", Toast.LENGTH_LONG).show();
    listView.setVisibility(View.INVISIBLE);
             } catch (Exception e) {
             e.printStackTrace();
             }
             break;
      case "Hindi (हिंदुई)":
             try {
    Intent intent_cam = new Intent(Camera_PT.this, Camera_HI.class);
    startActivity(intent_cam);
    Toast.makeText(getApplicationContext(), "COLOR LANGUAGE CHANGED", Toast.LENGTH_LONG).show();
    listView.setVisibility(View.INVISIBLE);
            } catch (Exception e) {
            e.printStackTrace();
            }
            break;
      case "Portuguese (português)":
            try {
    Intent intent_cam = new Intent(Camera_PT.this, Camera_PT.class);
    startActivity(intent_cam);
    Toast.makeText(getApplicationContext(), "COLOR LANGUAGE CHANGED", Toast.LENGTH_LONG).show();
    listView.setVisibility(View.INVISIBLE);
            } catch (Exception e) {
            e.printStackTrace();
            }
            break;
      case "Tagalog":
            try {
    Intent intent_cam = new Intent(Camera_PT.this, Camera_TL.class);
    startActivity(intent_cam);
    Toast.makeText(getApplicationContext(), "COLOR LANGUAGE CHANGED", Toast.LENGTH_LONG).show();
    listView.setVisibility(View.INVISIBLE);
           } catch (Exception e) {
           e.printStackTrace();
           }
           break;
     default:
    listView.setVisibility(View.INVISIBLE);
                    }
          show_lang_list = 1;
                }


            }
        });

    }

            @Override
    public void onPause(){
        super.onPause();
        cameraBridgeViewBase.disableView();
    }

    @Override
    public void onResume(){
        super.onResume();
        cameraBridgeViewBase.enableView();
        if(!OpenCVLoader.initDebug()){
            Toast.makeText(getApplicationContext(),"There is a problem in opencv",Toast.LENGTH_LONG).show();
        } else{
            mLoaderCallback.onManagerConnected(mLoaderCallback.SUCCESS);
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture){

    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        mRgba=new Mat(height,width, CV_8UC4);
        mGray= new Mat(height,width,CvType.CV_8UC1);
        mHsv = new Mat(width, height, CvType.CV_8UC3);
        mRgbaT = new Mat(height, width, CvType.CV_8UC4);
        mRgbaF = new Mat(fh, fw, CvType.CV_8UC4);
    }

    @Override
    public void onCameraViewStopped() {
        mRgba.release();
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {

        mRgba = inputFrame.rgba();
        mGray=inputFrame.gray();
        mRgbaF = inputFrame.rgba();
       //Core.transpose(mRgba,mRgbaT);
        //Rotate 90 degrees//
        //Imgproc.resize(mRgbaT,mRgbaF,mRgbaF.size(),0,0,0);
        //Core.flip(mRgbaF,mRgbaF,1);
        //  if(mCameraId==1){
        //    Core.rotate(mRgba,mRgba,Core.ROTATE_90_CLOCKWISE);
        //    Core.flip(mGray,mGray, -1);
        if(mCameraId==1){ //rotation problem: at front cam (==1), needs to rotate to 180 degree
             Core.flip(mRgba,mRgba,Core.ROTATE_90_COUNTERCLOCKWISE);
        }

    MatOfPoint corners = new MatOfPoint();
    Imgproc.goodFeaturesToTrack(mGray, corners, 10, 0.5, 20, new Mat(), 2, false);
            //Point[] cornersArr = corners.toArray();
            int R, G, B;

        //    for (int j = 0; j < corners.rows(); j++) {
        //       // Imgproc.circle(mRgba, cornersArr[i], 10, new Scalar(0,200,0), 2);
        //      Rect rectangle = Imgproc.boundingRect(corners);
        //      Imgproc.rectangle(mRgba2, rectangle.tl(), rectangle.br(), new Scalar(250, 254, 66, 1), 2); // mat, points (tl = top left),

       // }
        //convert mat rgb to mat hsv-----------------*/
        cvtColor(mRgba, mHsv, Imgproc.COLOR_RGB2HSV);

        //find scalar sum of (array elements) hsv
        Scalar mColorHsv = Core.sumElems(mHsv);

        //int pointCount = 320*240; //9:12
        //int pointCount = 540*720; //more accurate
        int pointCount = 600 * 800;
        //convert each pixel
        for (int i = 0; i < mColorHsv.val.length; i++) {
            mColorHsv.val[i] /= pointCount;
        }

        //convert hsv scalar to rgb scalar
        final Scalar mColorRgb = convertScalarHsv2Rgba(mColorHsv);

        // Log.d("intensity", "Color: #" + String.format("%02X", (int) mColorHsv.val[0])+ String.format("%02X", (int) mColorHsv.val[1])+ String.format("%02X", (int) mColorHsv.val[2]));
        //Get RGB Values
        R = (int) mColorRgb.val[0];
        G = (int) mColorRgb.val[1];
        B = (int) mColorRgb.val[2];

        final Object[][] modelAndHeaderEN = {null};
        try {
    modelAndHeaderEN[0] = SerializationHelper.readAll(getAssets().open("l2_rgb_pt_k-1.model"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        liveC = knnProcess1(R, G, B, modelAndHeaderEN);
        colorid.setText(liveC);

        //capture --------------------------
        try{
            captureCam(buttonCapture);
        }catch (Exception e){
            e.printStackTrace();
        }


   return mRgba;
    } //end of onCameraframe-----

    private void captureCam(ImageButton buttonCapture){
        buttonCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** if (take_image == 0) {take_image = 1; }else { take_image = 0;
                 while(take_image==1){ **///if (take_image == 0) {take_image = 1; }
                //else if(take_image == 1) {
        Toast.makeText(Camera_PT.this, "Took a picture", Toast.LENGTH_SHORT).show();
                //mRgbaT = mRgba.t();//Imgproc.resize(mRgbaT,mRgbaT,mRgba.size());
        //rotate 90 degree
        Core.flip(mRgbaF.t(), mRgbaF, 1);
        Imgproc.cvtColor(mRgbaF, mRgbaF, 4); //code 4

        //CREATE FOLDER
        File folder = new File(Environment.getExternalStorageDirectory().getPath()+ "/DCIM/ForHue");
        boolean success = true;
        if(!folder.exists()){
            success=folder.mkdirs();
                    }
        // Create an image file name WORKSSS!! but not much
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "ForHue-" + timeStamp + "_";
        //save in gallery
        String filename = Environment.getExternalStorageDirectory().getPath() + "/DCIM/ForHue/" + imageFileName + ".jpg";
        //save mat
        Imgcodecs.imwrite(filename, mRgbaF);
        // cvtColor(mRgba, mRgba, 4); //convert it first because imwrite outputs other color
        take_image = 0;
        //  }
        }
        });
    }

    //convert Mat hsv to scalar
    private Scalar convertScalarHsv2Rgba(Scalar hsvColor) {
        Mat pointMatRgba = new Mat();
        Mat pointMatHsv = new Mat(1, 1, CvType.CV_8UC3, hsvColor);
        cvtColor(pointMatHsv, pointMatRgba, Imgproc.COLOR_HSV2RGB);
        return new Scalar(pointMatRgba.get(0, 0));
    }

    private String knnProcess1(final int r, final int g, final int b,Object[][] modelAndHeader)  {
        final String[] prediction = {null};
        String sample = null;
        final Classifier[] knnmodel = {null};
        final Instances[] m_modelHeader = {null};
        try{
            // Create vector of attributes.
            FastVector numericAtt = new FastVector();
            FastVector nominalAtt = new FastVector();

            // Add numeric attributes/columns
            numericAtt.addElement(new Attribute("R"));
            numericAtt.addElement(new Attribute("G"));
            numericAtt.addElement(new Attribute("B"));

            //Add nominal/letters attributes/column
            nominalAtt.addElement("val3"); //label for att2-----------
            numericAtt.addElement(new Attribute("Color Names", nominalAtt));

            // 2. create Instances object
            final Instances data = new Instances("Testing Data", numericAtt, 1000); //always change attInfo when changing

            // 3. fill with data---------------------------------
            // first instance ++
            double[] vals = new double[data.numAttributes()];
             vals[0] = r;
             vals[1] = g;
             vals[2] = b;
             vals[3] = nominalAtt.indexOf("val3");

            //add to instance
            data.add(new DenseInstance(1.0, vals));
            if (data.classIndex() == -1) data.setClassIndex(data.numAttributes() - 1);
            //set class index for comparison testing
            data.setClassIndex(data.numAttributes() - 1);
            // System.out.println( "Instantiated:" + data);

            //KNN Weka part
            final String[] modelLang = {null};
            // modelAndHeader[0] = SerializationHelper.readAll(getAssets().open(modelName[0]));

            if (modelAndHeader[0].length != 2) {
                throw new Exception("[InputMappedClassifier] serialized model file "
                        + "does not seem to contain both a model and "
                        + "the instances header used in training it!");
            } else {
                //setClassifier((Classifier) modelAndHeader[0]);
                knnmodel[0] = (Classifier) modelAndHeader[0][0];
                m_modelHeader[0] = (Instances) modelAndHeader[0][1]; }
            double value = knnmodel[0].classifyInstance(data.instance(0)); //insert testing data, working
            //Get the name of the class value
            // prediction = data.instance(0).stringValue(0);
            prediction[0] = m_modelHeader[0].classAttribute().value((int)value);

        }
        catch(Exception e) {
        }

        return prediction[0];
    }  //end of method


    //flip camera---------------------------*/
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


