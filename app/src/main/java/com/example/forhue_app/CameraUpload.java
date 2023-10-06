package com.example.forhue_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropFragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Locale;

import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instances;
import weka.core.SerializationHelper;


public class CameraUpload extends AppCompatActivity {
        ImageView iv = null;
        ImageButton pickImg;
        ImageButton backToCam;
        ImageButton backToHome;
        ImageButton grabfocus;
        Bitmap bitmap;
        Button identify;

    TextToSpeech tts;
    TextView e1, color;
    ImageButton b1;

    ActivityResultLauncher<String> mGetContent;
    Intent store = null;

    //Set languages
    public static final String[] languages = {"Arabic (اللغة العربية)", "Chinese (普通话)", "English", "Hindi (हिंदुई)", "Portuguese (português)", "Tagalog"};
    ListView listupload;
    ListView ttsOptions;
    //ImageButton langicon;
    private int show_lang_list = 0;

    private final int CODE_IMG_GALLERY = 1;
    private final String SAMPLE_CROPPED_IMG_NAME = "SampleCropImg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_camera);

        backToHome = findViewById(R.id.imageButton6);
        iv = findViewById(R.id.image_display);

        pickImg = findViewById(R.id.gallery_butt);
        pickImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // mGetContent.launch("image/*");
              //  CropImage.activity(null).setGuidelines(CropImageView.Guidelines.ON).start(this);
               // Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
               // startActivityForResult(intent, 100);
               // store = intent;
                startActivityForResult(new Intent().setAction(Intent.ACTION_GET_CONTENT).setType("image/*"), CODE_IMG_GALLERY);
            }
        });

        /* go back to home */
        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CameraUpload.this,Home.class);
                startActivity(intent);
            }
        });

        e1 = findViewById(R.id.textView15);


        identify = findViewById(R.id.button2);
        //grabfocus = findViewById(R.id.butto);

        try {
            b1 = findViewById(R.id.imageButton2);
            ttsOptions =findViewById(R.id.ttsoptions);
            ttsLanguages(ttsOptions, b1);
        }catch (Exception e) {
            e.printStackTrace();
        }

        //end of TTS

        listupload = findViewById(R.id.lang_upl);
        //create an arrayAdapter
        ArrayAdapter<String> langAdapter = new ArrayAdapter<String>(this, R.layout.reso_items, languages);
        listupload.setAdapter(langAdapter);

    } // END OF ONCREATE

    public void ttsLanguages(ListView ttsOptions, ImageButton b1){
        //create an arrayAdapter
        ArrayAdapter<String> ttsAdapter = new ArrayAdapter<String>(this, R.layout.reso_items, languages);
        ttsOptions.setAdapter(ttsAdapter);
        //TTS
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (show_lang_list == 0) { //when button is clicked
                    ttsOptions.setVisibility(View.VISIBLE);
                    //setlang.setAdapter(langarrayAdapter);
                    show_lang_list = 1;
                } else {
                    ttsOptions.setVisibility(View.INVISIBLE);
                    //setlang.setAdapter(langnull_arrayAdapter);
                    show_lang_list = 0;
                }
            }
        });

        ttsOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedLang = parent.getItemAtPosition(position).toString();
                if (show_lang_list == 0) {
                    show_lang_list = 1;
                } else {
                    switch (selectedLang) {
                        case "Arabic (اللغة العربية)":
                            try {
                                tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                                    @Override
                                    public void onInit(int i) {
                                        if(i==TextToSpeech.SUCCESS){
                                            tts.setLanguage(Locale.forLanguageTag("ar"));
                                            tts.setSpeechRate(1.0f);
                                            tts.speak(e1.getText().toString(),TextToSpeech.QUEUE_ADD, null);
                                        }
                                    }
                                });
                                Toast.makeText(getApplicationContext(), "TTS Arabic Selected", Toast.LENGTH_LONG).show();
                                ttsOptions.setVisibility(View.INVISIBLE);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case "Chinese (普通话)":
                            try {
                                tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                                    @Override
                                    public void onInit(int i) {
                                        if(i==TextToSpeech.SUCCESS){
                                            tts.setLanguage(Locale.CHINESE);
                                            tts.setSpeechRate(1.0f);
                                            tts.speak(e1.getText().toString(),TextToSpeech.QUEUE_ADD, null);
                                        }
                                    }
                                });
                                Toast.makeText(getApplicationContext(), "TTS Chinese Selected", Toast.LENGTH_LONG).show();
                                ttsOptions.setVisibility(View.INVISIBLE);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;

                        case "English":

                            //modelName[0] = "rgb_en_new2-5.model";
                            try {
                                tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                                    @Override
                                    public void onInit(int i) {
                                        if(i==TextToSpeech.SUCCESS){
                                            tts.setLanguage(Locale.US);
                                            tts.setSpeechRate(1.0f);
                                            tts.speak(e1.getText().toString(),TextToSpeech.QUEUE_ADD, null);
                                        }
                                    }
                                });
                                Toast.makeText(getApplicationContext(), "TTS English Selected", Toast.LENGTH_LONG).show();
                                ttsOptions.setVisibility(View.INVISIBLE);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;

                        case "Hindi (हिंदुई)":
                            try {
                                tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                                    @Override
                                    public void onInit(int i) {
                                        if(i==TextToSpeech.SUCCESS){
                                            tts.setLanguage(Locale.forLanguageTag("hi"));
                                            tts.setSpeechRate(1.0f);
                                            tts.speak(e1.getText().toString(),TextToSpeech.QUEUE_ADD, null);
                                        }
                                    }
                                });
                                Toast.makeText(getApplicationContext(), "TTS Hindi Selected", Toast.LENGTH_LONG).show();
                                ttsOptions.setVisibility(View.INVISIBLE);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case "Portuguese (português)":
                            //final Object[][] modelAndHeaderEN = {null};
                            try {
                                tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                                    @Override
                                    public void onInit(int i) {
                                        if(i==TextToSpeech.SUCCESS){
                                            tts.setLanguage(Locale.forLanguageTag("pt"));
                                            tts.setSpeechRate(1.0f);
                                            tts.speak(e1.getText().toString(),TextToSpeech.QUEUE_ADD, null);
                                        }
                                    }
                                });
                                Toast.makeText(getApplicationContext(), "TTS Portuguese Selected", Toast.LENGTH_LONG).show();
                                ttsOptions.setVisibility(View.INVISIBLE);
                                //onPause();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            break;
                        case "Tagalog":
                            try {
                                tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                                    @Override
                                    public void onInit(int i) {
                                        if(i==TextToSpeech.SUCCESS){
                                            tts.setLanguage(Locale.forLanguageTag("fil"));
                                            tts.setSpeechRate(1.0f);
                                            tts.speak(e1.getText().toString(),TextToSpeech.QUEUE_ADD, null);
                                        }
                                    }
                                });
                                Toast.makeText(getApplicationContext(), "TTS Tagalog Selected", Toast.LENGTH_LONG).show();
                                ttsOptions.setVisibility(View.INVISIBLE);
                                //onPause();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        default:
                            //Toast.makeText(getApplicationContext(), "COLOR LANGUAGE CHANGED", Toast.LENGTH_LONG).show();
                            ttsOptions.setVisibility(View.INVISIBLE);
                            break;
                    }
                    show_lang_list = 1;
                }
            }
        });
    }
    public static Bitmap resize(Bitmap bitmap, int maxWidth, int maxHeight) {
        Bitmap image = bitmap;

        if (maxHeight > 0 && maxWidth > 0) {
            int width = image.getWidth();
            int height = image.getHeight();
            float ratioBitmap = (float) width / (float) height;
            float ratioMax = (float) maxWidth / (float) maxHeight;
            int finalWidth = maxWidth;
            int finalHeight = maxHeight;
            if (ratioMax > 1) { // if set not square ratio, landscape
                finalWidth = Math.round(((float) maxHeight * ratioBitmap));
            } else { //if set square, portrait
                finalHeight = Math.round(((float) maxWidth / ratioBitmap)); //lessens if greater,
            }
            return image = Bitmap.createScaledBitmap(image, finalWidth, finalHeight, false);
        }
        return image;
    }
    private String knnProcess(Bitmap bitmap,final Object[][] modelAndHeader)  {

        String sample = null;
        final Classifier[] knnmodel = {null};
        final String[] prediction = {null};
        final Instances[] m_modelHeader = {null};
        //final Object[][] modelAndHeader = {null};
        Bitmap resizedBitmap = resize(bitmap,650,650);
        // Mat imageMat = new Mat();
        //Utils.bitmapToMat(resizedBitmap, imageMat);
        int r,g,b;

        //if (take_knn == 0) {take_knn = 1; else {
           // for (int k = 0; k<=modelAndHeader.length; k++)

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

            //nominalAtt.addElement(new Attribute("Hex", (FastVector) null));
            //nominalAtt.addElement(new Attribute("Color Name", (FastVector) null));

            // 2. create Instances object
            Instances data = new Instances("Testing Data", numericAtt, 100); //always change attInfo when changing

                 //get RGB values of image=================================
            for(int y=0; y<resizedBitmap.getHeight() ; y++){
                for(int x=0; x< resizedBitmap.getWidth(); x++){
                    int colour = resizedBitmap.getPixel(x, y);

                    r = Color.red(colour);
                    g= Color.green(colour);
                    b = Color.blue(colour);
                    //int p = tmp.getRGB(x,y);
                 //Getting the A R G B values from the pixel value
                 //int a = (p>>24)&0xff;
               //   r = (p>>16)&0xff;
                 // g = (p>>8)&0xff;
                  //b = p&0xff;
                //  r = image.getIntComponent0(x, y);
                //g = image.getIntComponent1(x, y);
                // b = image.getIntComponent2(x, y);
                //  if(g > r*1.5 && g > b*1.5){
               //         image.setIntColor(x, y, 255,255,255);
                  // System.out.println("R: " + r + "G: " + g + "B: " + b);
                //  }
               //   hex = String.format("#%02x%02x%02x", r, g, b);  //correct conversion

                    // 3. fill with data----------------------------------------------------------
                    // first instance ++
                    double[] vals = new double[data.numAttributes()];
                    vals[0] = r;
                    vals[1] = g;
                    vals[2] = b;
                    vals[3] = nominalAtt.indexOf("val3");
                    //vals[0] = data.attribute(0).addStringValue(hex);
                    //vals[1] = data.attribute(1).addStringValue("A");
                    //add to instance
                    data.add(new DenseInstance(1.0, vals));
                    sample = "Colors data 1.0";
                }
            }
            if (data.classIndex() == -1) data.setClassIndex(data.numAttributes() - 1);
            //set class index for comparison testing
            data.setClassIndex(data.numAttributes() - 1);
            // System.out.println( "Instantiated:" + data);
            sample = "Colors1.0";
            //end of loop--------------------------------------------

            //modelAndHeader[0] = SerializationHelper.readAll(getAssets().open("rgbupload_en_k-5.model"));

            if (modelAndHeader[0].length != 2) {
                throw new Exception("[InputMappedClassifier] serialized model file "
                        + "does not seem to contain both a model and "
                        + "the instances header used in training it!");
            } else {
                //setClassifier((Classifier) modelAndHeader[0]);
                knnmodel[0] = (Classifier) modelAndHeader[0][0];
                m_modelHeader[0] = (Instances) modelAndHeader[0][1]; }

           // for (int i = 0; i <= 5; i++){
            double value = knnmodel[0].classifyInstance(data.instance(0)); //insert testing data, working //0, original indedx
            //Get the name of the class value
            prediction[0] =  m_modelHeader[0].classAttribute().value((int)value);

            sample = "Colors2";
        }
        catch(Exception e) {
                     e.printStackTrace();
                 }


        return prediction[0];
    }  //end of method
   /** private static Bitmap focusSubject(Bitmap bitmap, int xOne, int xTwo, int yOne, int yTwo) {
        //Bitmap to Mat for processing
        Mat imageMat = new Mat();
        Utils.bitmapToMat(bitmap, imageMat);
       // System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Bitmap newBitmap = null;
        Bitmap processed3 = null;
        try{
        Rect rectangle = new Rect(xOne, yOne, xTwo, yTwo);
        Mat result = new Mat();
        Mat bgdModel = new Mat();
        Mat fgdModel = new Mat();
        Mat source = new Mat(1, 1, CvType.CV_8U, new Scalar(3));
        Imgproc.grabCut(imageMat, result, rectangle, bgdModel, fgdModel, 8, Imgproc.GC_INIT_WITH_RECT);
        //Imgproc.grabCut(src, result, rectangle, bgdModel, fgdModel, 8, Imgproc.GC_INIT_WITH_MASK);
        Core.compare(result, source, result, Core.CMP_EQ);

        Mat foreground = new Mat(imageMat.size(), CvType.CV_8UC3, new Scalar(255, 255, 255));
        imageMat.copyTo(foreground, result);

        newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.RGB_565);
        Utils.matToBitmap(foreground, newBitmap);
        processed3 = resize(newBitmap,500,500);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "ForHue-" + timeStamp + "_";
       // String filename = Environment.getExternalStorageDirectory().getPath() + "/DCIM/ForHue/" + imageFileName + ".jpg";
      //Imgcodecs.imwrite(filename, processed3);

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return processed3;
        //Imgcodecs.imwrite(fileNameWithCompletePath, foreground);
    }**/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //FOR CROPPING
        if (resultCode == RESULT_OK) {
            if (requestCode == CODE_IMG_GALLERY) {
                final Uri selectedUri = data.getData();
                if (selectedUri != null) {
                    startCrop(selectedUri);
                } else {
                    Toast.makeText(CameraUpload.this, "Cannot retrieve selected image", Toast.LENGTH_SHORT).show();
                }
            } else if (requestCode == UCrop.REQUEST_CROP) {
                try {
                    handleCropResult(data);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (resultCode == UCrop.RESULT_ERROR) {
            handleCropError(data);
        }

/** //end of IDENTIFY button

                grabfocus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Bitmap processed2 =  focusSubject(bitmap, 1, 400, 30, 400);
                       //Bitmap processed3 = resize(processed2,900,900);
                       // String text2 = knnProcess(processed2);
                        //color.setText(" " + text2);
                        iv.setImageBitmap(processed2);

                    }
                });
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
**/
    } //end of ActivityResult

    private void startCrop(@NonNull Uri uri){
        String destinationFileName = SAMPLE_CROPPED_IMG_NAME;
        destinationFileName += ".jpg";

        UCrop uCrop = UCrop.of(uri,Uri.fromFile(new File(getCacheDir(), destinationFileName)));
        //uCrop.withAspectRatio(1,1);
        //uCrop.withMaxResultSize(650,650); //450x450

        uCrop.withOptions(getCropOptions());
        uCrop.start(CameraUpload.this);

    }

    private void handleCropResult(@NonNull Intent result) {
        final Uri resultUri = UCrop.getOutput(result);

        if (resultUri != null) {
            //CameraUpload.startWithUri(CameraUpload.this, resultUri);
            //Intent intent = new Intent(CameraUpload.this, CameraUpload.class);
            //intent.setData(resultUri);
           /// CameraUpload.this.startActivity(intent);
            try {
                InputStream inputStream = getContentResolver().openInputStream(resultUri);
                bitmap = BitmapFactory.decodeStream(inputStream); //retrieve image
                iv.setImageBitmap(bitmap);
                final Object[][] modelAndHeader = {null};
                //Toast.makeText(getApplicationContext(),"Successful!",Toast.LENGTH_LONG).show();

                identify.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (show_lang_list == 0) { //when button is clicked
                            listupload.setVisibility(View.VISIBLE);
                            //setlang.setAdapter(langarrayAdapter);
                            show_lang_list = 1;
                        } else {
                            listupload.setVisibility(View.INVISIBLE);
                            //setlang.setAdapter(langnull_arrayAdapter);
                            //String text = knnProcess(bitmap);
                            //color = findViewById(R.id.textView15);
                            //color.setText(" " + text);
                            show_lang_list = 0;
                        }
                    }
                });

                listupload.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_SHORT).show();

                        String selectedLang = parent.getItemAtPosition(position).toString();

                        switch (selectedLang) {
                            case "Arabic (اللغة العربية)":
                                try {
                                    modelAndHeader[0] = SerializationHelper.readAll(getAssets().open("u2_rgb_ar_k-5.model"));
                                    String text = knnProcess(bitmap, modelAndHeader);
                                    color = findViewById(R.id.textView15);
                                    color.setText(" " + text);
                                    Toast.makeText(getApplicationContext(), "ARABIC COLOR IDENTIFIED", Toast.LENGTH_LONG).show();
                                    listupload.setVisibility(View.INVISIBLE);
                                    ttsOptions.setVisibility(View.VISIBLE);
                                    //onPause();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "Chinese (普通话)":

                                try {
                                    modelAndHeader[0] = SerializationHelper.readAll(getAssets().open("u2_rgb_zh_k-5.model"));
                                    String text = knnProcess(bitmap, modelAndHeader);
                                    color = findViewById(R.id.textView15);
                                    color.setText(" " + text);
                                    Toast.makeText(getApplicationContext(), "CHINESE COLOR IDENTIFIED", Toast.LENGTH_LONG).show();
                                    listupload.setVisibility(View.INVISIBLE);
                                    ttsOptions.setVisibility(View.VISIBLE);
                                    //onPause();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "English":
                                //modelName[0] = "rgb_en_new2-5.model";
                                try {
                                    modelAndHeader[0] = SerializationHelper.readAll(getAssets().open("u2_rgb_en_k-5.model"));
                                    String text = knnProcess(bitmap, modelAndHeader);
                                    color = findViewById(R.id.textView15);
                                    color.setText(" " + text);
                                    Toast.makeText(getApplicationContext(), "ENGLISH COLOR IDENTIFIED", Toast.LENGTH_LONG).show();
                                    listupload.setVisibility(View.INVISIBLE);
                                    ttsOptions.setVisibility(View.VISIBLE);
                                    //onPause();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "Hindi (हिंदुई)":
                                try {
                                    modelAndHeader[0] = SerializationHelper.readAll(getAssets().open("u2_rgb_hi_k-5.model"));
                                    String text = knnProcess(bitmap, modelAndHeader);
                                    color = findViewById(R.id.textView15);
                                    color.setText(" " + text);
                                    Toast.makeText(getApplicationContext(), "HINDI COLOR IDENTIFIED", Toast.LENGTH_LONG).show();
                                    listupload.setVisibility(View.INVISIBLE);
                                    ttsOptions.setVisibility(View.VISIBLE);
                                    //onPause();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "Portuguese (português)":
                                //final Object[][] modelAndHeaderEN = {null};
                                try {
                                    modelAndHeader[0] = SerializationHelper.readAll(getAssets().open("u2_rgb_pt_k-5.model"));
                                    String text = knnProcess(bitmap, modelAndHeader);
                                    color = findViewById(R.id.textView15);
                                    color.setText(" " + text);
                                    Toast.makeText(getApplicationContext(), "PORTUGUESE COLOR IDENTIFIED", Toast.LENGTH_LONG).show();
                                    listupload.setVisibility(View.INVISIBLE);
                                    ttsOptions.setVisibility(View.VISIBLE);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "Tagalog":
                                try {
                                    modelAndHeader[0] = SerializationHelper.readAll(getAssets().open("u2_rgb_tl_k-5.model"));
                                    String text = knnProcess(bitmap, modelAndHeader);
                                    color = findViewById(R.id.textView15);
                                    color.setText(" " + text);
                                    Toast.makeText(getApplicationContext(), "TAGALOG COLOR IDENTIFIED", Toast.LENGTH_LONG).show();
                                    listupload.setVisibility(View.INVISIBLE);
                                    ttsOptions.setVisibility(View.VISIBLE);
                                    //onPause();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            default:
                                Toast.makeText(getApplicationContext(), "COLOR LANGUAGE CHANGED", Toast.LENGTH_LONG).show();
                                listupload.setVisibility(View.INVISIBLE);
                                ttsOptions.setVisibility(View.VISIBLE);

                        }


                    }
                });

            } //end of IDENTIFY button
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            Toast.makeText(CameraUpload.this, "Cannot retrieve selected image", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    private void handleCropError(@NonNull Intent result) {
        final Throwable cropError = UCrop.getError(result);
        if (cropError != null) {

            Toast.makeText(CameraUpload.this, cropError.getMessage(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(CameraUpload.this, "Unexpected error", Toast.LENGTH_SHORT).show();
        }
    }

    public void onCropFinish(UCropFragment.UCropResult result) {
        switch (result.mResultCode) {
            case RESULT_OK:
                handleCropResult(result.mResultData);
                break;
            case UCrop.RESULT_ERROR:
                handleCropError(result.mResultData);
                break;
        }

    }





    private UCrop.Options getCropOptions(){
        UCrop.Options options = new UCrop.Options();
        options.setCompressionQuality(70);

        //UI
        options.setHideBottomControls(false);
        options.setFreeStyleCropEnabled(true);

        //colors
        //options.setStatusBarColor(getResources().getColor(R.color.teal_200));
        options.setToolbarColor(getResources().getColor(R.color.gray_400));

        options.setToolbarTitle("Cropping Portion");
        return options;
    }
}