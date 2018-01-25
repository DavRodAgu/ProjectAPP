package com.example.ei1057.appcliente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class main_Screen extends AppCompatActivity {

    Button guideButton;
    Button touristButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__screen);

        guideButton = (Button) findViewById(R.id.guide_button);
        touristButton = (Button) findViewById(R.id.tourist_button);

        guideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(main_Screen.this, guideMainActivity.class);
                //myIntent.putExtra("key", value); //Optional parameters
                main_Screen.this.startActivity(myIntent);
            }
        });

        touristButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(main_Screen.this, Pantalla_Inicial.class);
                //myIntent.putExtra("key", value); //Optional parameters
                main_Screen.this.startActivity(myIntent);
            }
        });
    }
}
