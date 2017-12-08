package com.example.ei1057.appcliente;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Pantalla_InicialActivity extends AppCompatActivity {

    private static final String TAG = "Client";

    private Button searchButton;
    private EditText IDguide;
    private ListView listGuides;
    private ArrayList<DataModel> data;
    private CustomAdapter customAdapter;

    private DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla__inicial);


        searchButton = (Button) findViewById(R.id.SearchButton);
        listGuides = (ListView) findViewById(R.id.ListGuides);

        IDguide = (EditText) findViewById(R.id.IDguide);
        IDguide.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                customAdapter.getFilter().filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        data = new ArrayList<>();

        customAdapter = new CustomAdapter(Pantalla_InicialActivity.this, data);
        listGuides.setAdapter(customAdapter);



        //Listener
        searchButton.setOnClickListener(new View.OnClickListener(){ //Se podria utilizar un Budler en lugar de un AsyncTask
            public void onClick(View v) {
                customAdapter.clear();
                //Create asynctask
                //AsyncThread task = new AsyncThread();
                //task.execute();
            }
        });

        //Listener ListView
        listGuides.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataModel dataModel= data.get(position);

                // lanzar otro intent

                Toast toast = Toast.makeText(getApplicationContext(), "Click ListItem Number: " + position + "Name: "
                        + dataModel.getGuideId(), Toast.LENGTH_SHORT);
                toast.show();
            }
        });




    }

    @Override
    protected void onStart() {
        super.onStart();

        dbref = FirebaseDatabase.getInstance().getReference("guides");
        dbref.addValueEventListener(new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {
                data.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    DataModel guide = snapshot.getValue(DataModel.class);
                    data.add(guide);
                }
                customAdapter.notifyDataSetChanged();
            }

            public void onCancelled(DatabaseError databaseError) {

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
                Toast.makeText(Pantalla_InicialActivity.this, "Tarea finalizada!",
                        Toast.LENGTH_SHORT).show();
            }
        }

        protected void onCancelled() {
            Toast.makeText(Pantalla_InicialActivity.this, "Tarea cancelada!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}