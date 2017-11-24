package com.example.ei1057.appcliente;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Pantalla_Inicial extends AppCompatActivity {

    private static final String TAG = "Client";

    Button searchButton;
    EditText IDguide;
    ListView ListGuides;
    ArrayList<DataModel> data;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla__inicial);

        searchButton = (Button) findViewById(R.id.SearchButton);
        IDguide = (EditText) findViewById(R.id.IDguide);
        ListGuides = (ListView) findViewById(R.id.ListGuides);

        data = new ArrayList<>();


        customAdapter = new CustomAdapter(getApplicationContext(), data);
        ListGuides.setAdapter(customAdapter);

        //Listener
        searchButton.setOnClickListener(new View.OnClickListener(){ //Se podria utilizar un Budler en lugar de un AsyncTask
            public void onClick(View v) {
                customAdapter.clear();
                //Create asynctask
                AsyncThread task = new AsyncThread();
                task.execute();
            }
        });

        //Listener ListView
        ListGuides.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataModel dataModel= data.get(position);

                Toast toast = Toast.makeText(getApplicationContext(), "Click ListItem Number: " + position + "Name: "
                        + dataModel.getGuideID(), Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }


    //AsyncTask Class
    private class AsyncThread extends AsyncTask<Void, DataModel, Boolean> {
        protected Boolean doInBackground(Void... params) {
            //Llamada a la base de datos
            return true;
        }

        protected void onProgressUpdate(DataModel... values) {
            data.add(values[0]);
            customAdapter.notifyDataSetChanged();
        }

        protected void onPostExecute(Boolean result) {
            if(result) {
                Toast.makeText(Pantalla_Inicial.this, "Tarea finalizada!",
                        Toast.LENGTH_SHORT).show();
            }
        }

        protected void onCancelled() {
            Toast.makeText(Pantalla_Inicial.this, "Tarea cancelada!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}