package com.example.forhue_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Module extends AppCompatActivity {

    ImageButton returnbutton;
    ImageButton arrownext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_help);

        returnbutton = findViewById(R.id.return_manual);
        returnbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Module.this,Home.class);
                startActivity(intent);
            }
        });

        arrownext = findViewById(R.id.imageButtonarrow);
        arrownext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(Module.this, Module2.class);
                    startActivity(intent);

            }
        });
    }
}
