package com.example.forhue_app;

import androidx.appcompat.app.AppCompatActivity;

public class file extends AppCompatActivity {


    @Override
    public static fileReads(){
        BufferedReader bReader;
        bReader = new BufferedReader(
                new InputStreamReader(ISR(R.raw.rgblist)));
    }
    public InputStream ISR(int resourceId) {
        InputStream iStream = getBaseContext().getResources().openRawResource(resourceId);
        return iStream;
    }
}
