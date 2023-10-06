package com.example.forhue_app;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import org.opencv.android.OpenCVLoader;

import java.util.Locale;

public class Home extends AppCompatActivity{

    static {
        if(OpenCVLoader.initDebug()){
            Log.d("MainActivity: ","Opencv is loaded");
        }
        else {
            System.loadLibrary("jniLibs");
        }
    }
     ImageButton buttonCamera;
     ImageButton buttonSettings;
     ImageButton buttonUpl;
     ImageButton opensett;
     ImageButton returnsett;
     ImageButton module1;
     ImageButton module2;
     ListView camera_options;

     private SeekBar seekBar;
     private int brightness;
     private ContentResolver contentResolver;
     private Window window;
    //Set languages
    //public static final String[] languages = {"Arabic (اللغة العربية)", "Chinese (普通话)", "English", "Hindi (हिंदुई)", "Portuguese (português)", "Tagalog"};

    private int show_lang_list = 0;
    Spinner spinner;
    public static final String[] languages = {"Pick a language:", "Arabic (اللغة العربية)", "Chinese (普通话)", "English", "Hindi (हिंदुई)", "Portuguese (português)", "Tagalog"};
    TextView chooseLang, settings,def_lang,brightness2, font_size,
            small, medium, large, save, cam_home,upload_home, settings_home;
    TextView title1, title2,title3, title4, title5,title6, title7, title8,
            title9, title10, title11,title12, title13, title14,title15, manual, manual2;
    TextView caption1, caption2, caption3, caption4, caption5, caption6,
            caption8, caption9, caption10, caption11, caption12,
            caption13, caption14, caption15, caption16, caption17, caption18;
    TextView caption19, caption20, caption21, caption22, caption23, caption24,
            caption25, caption26, caption27, caption28, caption29,
            caption30, caption31, caption32, caption33, caption34;
    Button button2;
    private BottomSheetBehavior bottomSheetBehavior;
   DrawerLayout drawerLayout;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

       drawerLayout= findViewById(R.id.drawerLayout);
       opensett = findViewById(R.id.opensettings);
                try {
         //FLOATING SETTINGS-------------------------*/

       seekBar = findViewById(R.id.bright_bar);
       brightness(seekBar);
       opensett.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {

       drawerLayout.openDrawer(GravityCompat.START);
       //Makes seekbar static upon touch
       seekBar.setOnTouchListener(new SeekBar.OnTouchListener() {
         @Override
         public boolean onTouch(View v, MotionEvent event) {
         int action = event.getAction();
         switch (action) {
                case MotionEvent.ACTION_DOWN:
                // Disallow Drawer to intercept touch events.
                // v.getParent().requestDisallowInterceptTouchEvent(true);
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
                break;

                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                // Allow Drawer to intercept touch events.
                // v.getParent().requestDisallowInterceptTouchEvent(false);
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNDEFINED);
                break;
                }

                                    // Handle SeekBar touch events.
                                    v.onTouchEvent(event);
                                    return true;
                                }
                            });
                            //if (drawerLayout != null){
                            //    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED); }

                            //drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                            //check android version
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                                if (android.provider.Settings.System.canWrite(getApplicationContext())) {
                                    //Toast.makeText(getApplicationContext(),"You can change the brightness", Toast.LENGTH_SHORT).show();
                                    //   Toast.makeText("You can change the brightness", Toast.LENGTH_SHORT).show();
                                } else {
                                    Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS);
                                    intent.setData(Uri.parse("package:" + getApplication().getPackageName()));
                                    startActivity(intent);
                                }
                            }
                        // bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        }
                    });
                    spinner = findViewById(R.id.spinner);
                    languageSettings(spinner);
                    camera_options = findViewById(R.id.cameralang);
                    cameraLanguages(camera_options);


                }catch (Exception e) {
                e.printStackTrace();
                }

            /*camera select---------------------------*/
            buttonCamera = (ImageButton) findViewById(R.id.camera_menu);
            buttonCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (show_lang_list == 0) { //when button is clicked
                        camera_options.setVisibility(View.VISIBLE);
                        show_lang_list = 1;
                    } else {
                        camera_options.setVisibility(View.INVISIBLE);
                        show_lang_list = 0;
                    }
                }
            });

            /*upload select---------------------*/
            buttonUpl = (ImageButton) findViewById(R.id.upload_menu);
            buttonUpl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                Intent intent_set = new Intent(Home.this, CameraUpload.class);
                startActivity(intent_set);
                }
            });

            // back to home --------------------*/
            returnsett = findViewById(R.id.return_sett);
            returnsett.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawerLayout.openDrawer(GravityCompat.END);
                }
            });
            module1 = (ImageButton) findViewById(R.id.module1);
            module1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Home.this, Module.class);
                    startActivity(intent);
                }
            });

            module2 = (ImageButton) findViewById(R.id.module2);
            module2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Home.this, Module.class);
                    startActivity(intent);
                }
            });
            /* refresh button ---------------------*/
            button2 = findViewById(R.id.button);
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Home.this, Home.class);
                    startActivity(intent);
                }
            });



} //end of onCreate --

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else{
        super.onBackPressed(); }

    }

    public void cameraLanguages(ListView camera_options){
        //create an arrayAdapter
        ArrayAdapter<String> langAdapter = new ArrayAdapter<String>(this, R.layout.reso_items, languages);
        camera_options.setAdapter(langAdapter);

        //CHANGE COLOR LANGUAGE ---------------------------*/
        camera_options.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
     Intent intent_cam = new Intent(Home.this, Camera_AR.class);
     startActivity(intent_cam);
     Toast.makeText(getApplicationContext(), "Arabic Color Detection", Toast.LENGTH_LONG).show();
     camera_options.setVisibility(View.INVISIBLE);
           } catch (Exception e) {
           e.printStackTrace();
           }
           break;
       case "Chinese (普通话)":
           try {
     Intent intent_cam = new Intent(Home.this, Camera_ZH.class);
     startActivity(intent_cam);
     Toast.makeText(getApplicationContext(), "Chinese Color Detection", Toast.LENGTH_LONG).show();
     camera_options.setVisibility(View.INVISIBLE);
           } catch (Exception e) {
            e.printStackTrace();
            }
           break;
        case "English":
           try {
     Intent intent_cam = new Intent(Home.this, Camera2.class);
     startActivity(intent_cam);
     Toast.makeText(getApplicationContext(), "English Color Detection", Toast.LENGTH_LONG).show();
     camera_options.setVisibility(View.INVISIBLE);
           } catch (Exception e) {
           e.printStackTrace();
           }
           break;
       case "Hindi (हिंदुई)":
           try {
     Intent intent_cam = new Intent(Home.this, Camera_HI.class);
     startActivity(intent_cam);
     Toast.makeText(getApplicationContext(), "Hindi Color Detection", Toast.LENGTH_LONG).show();
     camera_options.setVisibility(View.INVISIBLE);
           } catch (Exception e) {
               e.printStackTrace();
                }
           break;
      case "Portuguese (português)":
           try {
     Intent intent_cam = new Intent(Home.this, Camera_PT.class);
     startActivity(intent_cam);
     Toast.makeText(getApplicationContext(), "Portuguese Color Detection", Toast.LENGTH_LONG).show();
     camera_options.setVisibility(View.INVISIBLE);
           } catch (Exception e) {
           e.printStackTrace();
           }
           break;
      case "Tagalog":
           try {
     Intent intent_cam = new Intent(Home.this, Camera_TL.class);
     startActivity(intent_cam);
     Toast.makeText(getApplicationContext(), "Tagalog Color Detection", Toast.LENGTH_LONG).show();
     camera_options.setVisibility(View.INVISIBLE);
          } catch (Exception e) {
           e.printStackTrace();
            }
            break;
     default:
     camera_options.setVisibility(View.INVISIBLE);
            break;
                    }
                    show_lang_list = 1;
                }
            }
        });
    }
    public void brightness(SeekBar seekBar){
        /*screen brightness----------------*/
        contentResolver = getContentResolver();
        window = getWindow();
        seekBar.setMax(255);
        seekBar.setKeyProgressIncrement(1);

        try {
    brightness = android.provider.Settings.System.getInt(contentResolver, android.provider.Settings.System.SCREEN_BRIGHTNESS);
    seekBar.setProgress(brightness);
    } catch (android.provider.Settings.SettingNotFoundException e) {
    e.printStackTrace();
        }

    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
    brightness = progress;
    android.provider.Settings.System.putInt(contentResolver, android.provider.Settings.System.SCREEN_BRIGHTNESS, brightness);
    WindowManager.LayoutParams layoutParams = window.getAttributes();
    layoutParams.screenBrightness = brightness / (float) 300;
    window.setAttributes(layoutParams);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    public void languageSettings(Spinner spinner){
        /* language button ---------------------------*/
        settings = findViewById(R.id.textView4);
        def_lang = findViewById(R.id.textView10);
        brightness2 = findViewById(R.id.textView6);

        cam_home = findViewById(R.id.textView2);
        upload_home = findViewById(R.id.textView13);
        manual = findViewById(R.id.textView18);
        manual2 = findViewById(R.id.textViewM2a);
        title1 = findViewById(R.id.textView44);
        title2 = findViewById(R.id.textView17);
        title3 = findViewById(R.id.textView21);
        title4 = findViewById(R.id.textView26);
        title5 = findViewById(R.id.textView30);
        title6 = findViewById(R.id.textView32);
        title7 = findViewById(R.id.textView36);
        title8 = findViewById(R.id.textView39);
        title9 = findViewById(R.id.textViewM1);
        title10 = findViewById(R.id.textViewM2);
        title11 = findViewById(R.id.textViewM3);
        title12 = findViewById(R.id.textViewM4);
        title13 = findViewById(R.id.textViewM5);
        title14 = findViewById(R.id.textViewM6);
        title15 = findViewById(R.id.textViewM7);

        caption1 = findViewById(R.id.textView45);
        caption2 = findViewById(R.id.textView46);
        caption3 = findViewById(R.id.textView20);
        caption4 = findViewById(R.id.textView24);
        caption5 = findViewById(R.id.textView25);
        caption6 = findViewById(R.id.textView22);
        caption8 = findViewById(R.id.textView27);
        caption9 = findViewById(R.id.textView28);
        caption10 = findViewById(R.id.textView29);
        caption11 = findViewById(R.id.textView31);
        caption12 = findViewById(R.id.textView33);
        caption13 = findViewById(R.id.textView34);
        caption14 = findViewById(R.id.textView35);
        caption15 = findViewById(R.id.textView37);
        caption16 = findViewById(R.id.textView38);
        caption17 = findViewById(R.id.textView40);
        caption18 = findViewById(R.id.textView41);

        caption19 = findViewById(R.id.textViewMC19);
        caption20 = findViewById(R.id.textViewMC20);
        caption21 = findViewById(R.id.textViewMC21);
        caption22 = findViewById(R.id.textViewMC22);
        caption23 = findViewById(R.id.textViewMC23);
        caption24 = findViewById(R.id.textViewMC24);
        caption25 = findViewById(R.id.textViewMC25);
        caption26 = findViewById(R.id.textViewMC26);
        caption27 = findViewById(R.id.textViewMC27);
        caption28 = findViewById(R.id.textViewMC28);
        caption29 = findViewById(R.id.textViewMC29);
        caption30 = findViewById(R.id.textViewMC30);
        caption31 = findViewById(R.id.textViewMC31);
        caption32 = findViewById(R.id.textViewMC32);
        caption33 = findViewById(R.id.textViewMC33);
        caption34 = findViewById(R.id.textViewMC34);

        //settings_home = findViewById(R.id.textView3);
        //languages --------------------------

    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    String selectedLang = parent.getItemAtPosition(position).toString();
                if (selectedLang.equals("Arabic (اللغة العربية)")) {
                    setLocal(Home.this, "ar");
                    // finish();
                } else if (selectedLang.equals("Chinese (普通话)")) {
                    setLocal(Home.this, "zh");
                    //finish();
                } else if (selectedLang.equals("English")) {
                    setLocal(Home.this, "en");

                } else if (selectedLang.equals("Hindi (हिंदुई)")) {
                    setLocal(Home.this, "hi");

                } else if (selectedLang.equals("Portuguese (português)")) {
                    setLocal(Home.this, "pt");

                } else if (selectedLang.equals("Tagalog")) {
                    setLocal(Home.this, "fil");
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void setLocal(Activity activity, String langCode){
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //set strings from resources
        manual.setText(R.string.insman);
        manual2.setText(R.string.insman2);
        title1.setText(R.string.man_t1);
        title2.setText(R.string.man_t2);
        title3.setText(R.string.man_t3);
        title4.setText(R.string.man_t4);
        title5.setText(R.string.man_t5);
        title6.setText(R.string.man_t6);
        title7.setText(R.string.man_t7);
        title8.setText(R.string.man_t8);
        title9.setText(R.string.man_t9);
        title10.setText(R.string.man_t10);
        title11.setText(R.string.man_t11);
        title12.setText(R.string.man_t12);
        title13.setText(R.string.man_t13);
        title14.setText(R.string.man_t14);
        title15.setText(R.string.man_t15);

        caption1.setText(R.string.man_c1);
        caption2.setText(R.string.man_c2);
        caption3.setText(R.string.man_c3);
        caption4.setText(R.string.man_c4);
        caption5.setText(R.string.man_c5);
        caption6.setText(R.string.man_c6);
        caption8.setText(R.string.man_c8);
        caption9.setText(R.string.man_c9);
        caption10.setText(R.string.man_c10);
        caption11.setText(R.string.man_c11);
        caption12.setText(R.string.man_c12);
        caption13.setText(R.string.man_c13);
        caption14.setText(R.string.man_c14);
        caption15.setText(R.string.man_c15);
        caption16.setText(R.string.man_c16);
        caption17.setText(R.string.man_c17);
        caption18.setText(R.string.man_c18);

        caption19.setText(R.string.man_c19);
        caption20.setText(R.string.man_c20);
        caption21.setText(R.string.man_c21);
        caption22.setText(R.string.man_c22);
        caption23.setText(R.string.man_c23);
        caption24.setText(R.string.man_c24);
        caption25.setText(R.string.man_c25);
        caption26.setText(R.string.man_c26);
        caption27.setText(R.string.man_c27);
        caption28.setText(R.string.man_c28);
        caption29.setText(R.string.man_c29);
        caption30.setText(R.string.man_c30);
        caption31.setText(R.string.man_c31);
        caption32.setText(R.string.man_c32);
        caption33.setText(R.string.man_c33);
        caption34.setText(R.string.man_c34);

        chooseLang.setText(R.string.choose_lang);
        settings.setText(R.string.settings);
        def_lang.setText(R.string.def_lang);
        brightness2.setText(R.string.brightness);
        font_size.setText(R.string.font_size);
        small.setText(R.string.small);
        medium.setText(R.string.medium);
        large.setText(R.string.large);
        save.setText(R.string.save);
        cam_home.setText(R.string.cam_home);
        upload_home.setText(R.string.upload_home);
        settings_home.setText(R.string.settings_home);
    }



}
