package com.example.forhue_app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
// language in Landing page
public class language extends AppCompatActivity {
    private ImageButton button;
    Spinner spinner;
    public static final String[] languages = {"Select Language", "Arabic (اللغة العربية)", "Chinese (普通话)", "English", "Hindi (हिंदुई)", "Portuguese (português)", "Tagalog"};
    TextView chooseLang, settings,def_lang,brightness, font_size,
            small, medium, large, save, cam_home,upload_home, settings_home;
    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language2);
        //text
        chooseLang= findViewById(R.id.textView12);
        settings= findViewById(R.id.textView4);
        def_lang = findViewById(R.id.textView10);
        brightness = findViewById(R.id.textView6);
        font_size = findViewById(R.id.textView5);
        small = findViewById(R.id.textView7);
        medium = findViewById(R.id.textView8);
        large = findViewById(R.id.textView9);
        save = findViewById(R.id.textView11);
        cam_home =  findViewById(R.id.textView2);
        upload_home = findViewById(R.id.textView13);
        //settings_home = findViewById(R.id.textView3);

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLang = parent.getItemAtPosition(position).toString();
                if (selectedLang.equals("Arabic (اللغة العربية)")){
                    setLocal(language.this,"ar" );
                    /*selected language*/
                    try {
                        button = (ImageButton) findViewById(R.id.imageButton7);
                        button.setVisibility(View.VISIBLE);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(language.this, Home.class);
                                startActivity(intent);
                            }
                        });
                    }  catch (Exception e) {
                            e.printStackTrace();
                        }

                }
                else if (selectedLang.equals("Chinese (普通话)")){
                    setLocal(language.this,"zh" );
                    /*selected language*/
                    try {
                        button = (ImageButton) findViewById(R.id.imageButton7);
                        button.setVisibility(View.VISIBLE);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(language.this, Home.class);
                                startActivity(intent);
                            }
                        });
                    }  catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if (selectedLang.equals("English")){
                    setLocal(language.this,"en" );
                    /*selected language*/
                    try {
                        button = (ImageButton) findViewById(R.id.imageButton7);
                        button.setVisibility(View.VISIBLE);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(language.this, Home.class);
                                startActivity(intent);
                            }
                        });
                    }  catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                else if (selectedLang.equals("Hindi (हिंदुई)")){
                    setLocal(language.this,"hi" );
                    /*selected language*/
                    try {
                        button = (ImageButton) findViewById(R.id.imageButton7);
                        button.setVisibility(View.VISIBLE);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(language.this, Home.class);
                                startActivity(intent);
                            }
                        });
                    }  catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if (selectedLang.equals("Portuguese (português)")){
                    setLocal(language.this,"pt" );
                    /*selected language*/
                    try {
                        button = (ImageButton) findViewById(R.id.imageButton7);
                        button.setVisibility(View.VISIBLE);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(language.this, Home.class);
                                startActivity(intent);
                            }
                        });
                    }  catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if (selectedLang.equals("Tagalog")){
                    setLocal(language.this,"tl" );
                    /*selected language*/
                    try {
                        button = (ImageButton) findViewById(R.id.imageButton7);
                        button.setVisibility(View.VISIBLE);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(language.this, Home.class);
                                startActivity(intent);
                            }
                        });
                    }  catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(), "YOU HAVE TO SELECT A LANGUAGE", Toast.LENGTH_LONG).show();

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

        chooseLang.setText(R.string.choose_lang);
        settings.setText(R.string.settings);
        def_lang.setText(R.string.def_lang);
        brightness.setText(R.string.brightness);
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
