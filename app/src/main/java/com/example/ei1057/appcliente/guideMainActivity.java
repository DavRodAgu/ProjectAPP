package com.example.ei1057.appcliente;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class guideMainActivity extends AppCompatActivity {
    ImageButton createSession;
    ListView SessionList;
    ArrayAdapter<String> adapter;
    private ArrayList<String> sessions = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_main);

        guideLogin login = guideLogin.newInstance("Login");
        login.show(getSupportFragmentManager(), "Login");

        createSession = (ImageButton) findViewById(R.id.createSession);
        SessionList = (ListView) findViewById(R.id.sessions_List);

        //Aqui creamos una sesion, habra que subirla a la base de datos y luego que se
        //muestre en el listView
        createSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateSessionDialog create = CreateSessionDialog.newInstance("Create");
                create.show(getSupportFragmentManager(), "Create");
            }
        });

        //Hay que obtener las sesiones de un guia para mostrarlas en listView
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, sessions);

        SessionList.setAdapter(adapter);

        SessionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast toast = Toast.makeText(getApplicationContext(), "Session" + id, Toast.LENGTH_LONG);
                toast.show();
                Intent myIntent = new Intent(guideMainActivity.this, SessionActivity.class);
                //myIntent.putExtra("key", value); //Optional parameters
                guideMainActivity.this.startActivity(myIntent);
            }
        });
        sessions.add("Castellon");
        sessions.add("Sevilla");
    }

    @Override
    public void onBackPressed() {
        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Do you want to leave?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        //Borrar usuario de la base de datos si pulsa si
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        //No hacemos nada
                    }
                })
                .show();
    }
}
