package com.example.forhue_app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.opencv.android.OpenCVLoader;

import java.util.Locale;

public class Home2 extends AppCompatActivity {

    static {
        if(OpenCVLoader.initDebug()){
            Log.d("MainActivity: ","Opencv is loaded");
        }
        else {
            System.loadLibrary("jniLibs");
        }
    }
    ImageButton opensett;
    ImageButton returnsett;
    Button button2;

    Spinner spinner;
    private SeekBar seekBar;
    private int brightness;
    private ContentResolver contentResolver;
    private Window window;
    public static final String[] languages = {"", "Arabic (اللغة العربية)", "Chinese (普通话)", "English", "Hindi (हिंदुई)", "Portuguese (português)", "Tagalog"};
    TextView chooseLang, settings,def_lang,brightness2, font_size,
            small, medium, large, save, cam_home,upload_home, settings_home;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fh_setting3);


        /* refresh button ---------------------------------------------*/
        button2 = findViewById(R.id.button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home2.this,Home.class);
                startActivity(intent);
            }
        });
        /*screen brightness------------------------------------------------*/
        seekBar = findViewById(R.id.bright_bar);
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
                layoutParams.screenBrightness = brightness / (float)300;
                window.setAttributes(layoutParams);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        /* language button ---------------------------------------------*/
        settings = findViewById(R.id.textView4);
        def_lang = findViewById(R.id.textView10);
        brightness2 = findViewById(R.id.textView6);

        cam_home = findViewById(R.id.textView2);
        upload_home = findViewById(R.id.textView13);
        //settings_home = findViewById(R.id.textView3);
        //languages --------------------------------------------------
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLang = parent.getItemAtPosition(position).toString();
                if (selectedLang.equals("Arabic (اللغة العربية)")) {
                    setLocal(Home2.this, "ar");
                    // finish();
                } else if (selectedLang.equals("Chinese (普通话)")) {
                    setLocal(Home2.this, "zh");
                    //finish();
                } else if (selectedLang.equals("English")) {
                    setLocal(Home2.this, "en");

                } else if (selectedLang.equals("Hindi (हिंदुई)")) {
                    setLocal(Home2.this, "hi");

                } else if (selectedLang.equals("Portuguese (português)")) {
                    setLocal(Home2.this, "pt");

                } else if (selectedLang.equals("Tagalog")) {
                    setLocal(Home2.this, "tl");
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    } //end of onCreate --


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
