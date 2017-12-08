package com.example.ei1057.appcliente;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.text.Editable;
import android.text.TextWatcher;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Pantalla_Inicial extends AppCompatActivity implements LoginDialog.OnMyDialogResult {

    private static final String TAG = "Client";

    Button searchButton;
    EditText IDguide;
    ListView ListGuides;
    ArrayList<DataModel> data;
    CustomAdapter customAdapter;
    Session sessionClient;
    List<ScanResult> scan;

    private DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla__inicial);

        String service = Context.WIFI_SERVICE;
        final WifiManager wifiManager = (WifiManager) getSystemService(service);

        final IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.RSSI_CHANGED_ACTION);
        filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);



        //Check whether wifi is enable or not
        if (!wifiManager.isWifiEnabled()){
            if (wifiManager.getWifiState() != WifiManager.WIFI_STATE_ENABLING){
                Toast.makeText(getApplicationContext(), "Wifi desactivada. Habilitando wifi.", Toast.LENGTH_LONG).show();
                wifiManager.setWifiEnabled(true);
                //Log.e("MyWifiApplicationLOG","Activating wifi");
            }
        }

        /*
        wifiManager.startScan();

        final BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override public void onReceive(Context context, Intent intent) {
                //wifiManager.startScan();
                scan = wifiManager.getScanResults();
            }
        }; */


        /*registerReceiver(new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context c, Intent intent)
            {
                scan.addAll(wifiManager.getScanResults());
                for(ScanResult result : scan) {
                    Toast.makeText(getApplicationContext(), " RSSI: " + result.level + "  SSID: " + result.SSID, Toast.LENGTH_LONG).show();
                }
            }
        }, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)); */

        IDguide = (EditText) findViewById(R.id.IDguide);
        searchButton = (Button) findViewById(R.id.SearchButton);
        ListGuides = (ListView) findViewById(R.id.ListGuides);


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
        scan = new ArrayList<>();


        customAdapter = new CustomAdapter(Pantalla_Inicial.this, data, scan);
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
                        + dataModel.getGuideId(), Toast.LENGTH_SHORT);
                toast.show();


                LoginDialog login = LoginDialog.newInstance("DialogLogin");
                login.show(getSupportFragmentManager(), "Login");
            }
        });


    }


    @Override
    public void finish(Session session) {
        sessionClient = session;
        Toast toast = Toast.makeText(getApplicationContext(), "Name: "+ sessionClient.getName() + "Password: "
                + sessionClient.getPassword(), Toast.LENGTH_LONG);
        toast.show();
        //Aqui hay que llamar a la base de datos
        //Comproamos la contraseña y si coinciden registramos el client en la base de datos
        if (sessionClient.getPassword().equals("1234")) {
            Intent intent = new Intent(Pantalla_Inicial.this, Second_Screen.class);
            startActivity(intent);
        } else {
            sessionClient = null;
        }
    }

    /*
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
    }*/


    //AsyncTask Class
    private class AsyncThread extends AsyncTask<Void, DataModel, Boolean> {
        protected Boolean doInBackground(Void... params) {
            data.add(new DataModel("Josema", "depape"));
            data.add(new DataModel("Alberto", "SSID2"));
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