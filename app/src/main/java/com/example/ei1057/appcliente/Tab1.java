package com.example.ei1057.appcliente;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class Tab1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_second__screen, container, false);


        //Boton opcional
        Button disconnectButton = (Button) v.findViewById(R.id.disconnectButton);

        disconnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //En el caso que se pulse borramos el cliente de la sesion
                Toast toast = Toast.makeText(getContext(), "Disconnect Button pressed", Toast.LENGTH_SHORT);
                toast.show();

            }
        });

        return v;
    }
}
