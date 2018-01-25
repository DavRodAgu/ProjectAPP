package com.example.ei1057.appcliente;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


//Esta sera la pestaña donde mostraremos los turistas que tiene un guia en una sesion
public class Tab1Session extends Fragment{
    ListView tourists;
    ArrayAdapter<String> adapter;
    private ArrayList<String> ListTourists = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_session, container, false);

        tourists = (ListView) v.findViewById(R.id.touristsList);

        //Podriamos poner un customAdapter para este listView pero para simplificarlo por ahora
        //he creado un listView simple

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, ListTourists);

        tourists.setAdapter(adapter);

        tourists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Quizas podriamos mostrar informacion de un turista al pulsar una linea
            }
        });
        //Para comprobar que funciona
        ListTourists.add("Pepe");

        return v;
    }
}
