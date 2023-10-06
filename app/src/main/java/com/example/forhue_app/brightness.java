package com.example.forhue_app;

import android.os.Bundle;
import android.widget.SeekBar;

import android.content.Context;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatActivity;

public class brightness extends AppCompatActivity {
     SeekBar lightBar;
        Context context;
        int brightness;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.fh_setting);
            lightBar = findViewById(R.id.bright_bar);
            context = getApplicationContext();
            brightness =
                    Settings.System.getInt(context.getContentResolver(),
                            Settings.System.SCREEN_BRIGHTNESS, 0);
            lightBar.setProgress(brightness);
            lightBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    Settings.System.putInt(context.getContentResolver(),
                            Settings.System.SCREEN_BRIGHTNESS, progress);
                }
                @Override
                public void onStartTrackingTouch(SeekBar seekBar) { }
                @Override
                public void onStopTrackingTouch(SeekBar seekBar) { }
            });
        }

}
