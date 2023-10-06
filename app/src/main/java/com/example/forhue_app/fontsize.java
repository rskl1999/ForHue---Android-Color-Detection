package com.example.forhue_app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class fontsize extends AppCompatActivity {

    SeekBar seekBarfont;
    TextView chooseLang, settings,def_lang,brightness2, font_size,
             save, cam_home,upload_home, settings_home;
    SharedPreferences prefs;
   // private static final int progress = 24;  // Default font-size 24sp
    int textSize = 24;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fh_setting2);
        seekBarfont = findViewById(R.id.font_bar);

        //text
       // chooseLang= findViewById(R.id.textView12);

        settings= findViewById(R.id.textView4);
        def_lang = findViewById(R.id.textView10);
        brightness2 = findViewById(R.id.textView6);
        font_size = findViewById(R.id.textView5);

       // save = findViewById(R.id.textView11);
      //  cam_home =  findViewById(R.id.textView2);
      //  upload_home = findViewById(R.id.textView13);
       // settings_home = findViewById(R.id.textView3);
        //prefs = getPreferences(MODE_PRIVATE);

        seekBarfont.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressNew = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textSize = textSize + (progress - progressNew);
                progressNew = progress;
                def_lang.setTextSize(textSize);
                brightness2.setTextSize(textSize);
                font_size.setTextSize(textSize);
              /**
               *  SharedPreferences.Editor ed = prefs.edit();
               *                 ed.putInt("fontSize", progress);
               *                 ed.apply();
               * def_lang.setTextSize(textSize);
                brightness2.setTextSize(textSize);
                font_size.setTextSize(textSize);
                cam_home.setTextSize(textSize);
                upload_home.setTextSize(textSize);
                save.setTextSize(progress);
                cam_home.setTextSize(progress);
                upload_home.setTextSize(progress);
                settings_home.setTextSize(progress); **/

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
               // int textSize = prefs.getInt("fontSize", 24); // put your default value here like 16

               // def_lang.setTextSize(textSize);
              //  brightness2.setTextSize(textSize);
               // font_size.setTextSize(textSize);
            }

        });
    }


}
