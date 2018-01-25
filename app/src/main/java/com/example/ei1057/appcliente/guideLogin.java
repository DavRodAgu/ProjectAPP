package com.example.ei1057.appcliente;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;


public class guideLogin extends DialogFragment {
    static guideLogin newInstance(String title) {
        guideLogin dialog = new guideLogin();

        Bundle args = new Bundle();
        args.putString("Title", title);
        dialog.setArguments(args);

        return dialog;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setCancelable(false);
        return loginDialog();
    }

    public AlertDialog loginDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.login_dialog, null);

        builder.setView(v);

        final EditText name = (EditText) v.findViewById(R.id.name_input);
        final EditText password = (EditText) v.findViewById(R.id.password_input);


        builder.setTitle("Login");

        //Aqui comprobamos si el login del guia es correcto o no
        builder.setPositiveButton("Login", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name_input = name.getText().toString();
                String password_input = password.getText().toString();

                if(name_input.equals("") || password_input.equals("")) {
                    dialog.dismiss();
                } else {
                    //Session session = new Session(name_input, password_input);
                    dialog.dismiss();
                }
            }
        });
        //Si pulsa el boton registrar registramos al guia en la base de datos
        /*builder.setNegativeButton("Register", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });*/

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return builder.create();
    }
}
