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


public class LoginDialog extends DialogFragment {


    static LoginDialog newInstance(String title) {
        LoginDialog dialog = new LoginDialog();

        Bundle args = new Bundle();
        args.putString("Title", title);
        dialog.setArguments(args);

        return dialog;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
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

        builder.setPositiveButton("Login", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name_input = name.getText().toString();
                String password_input = password.getText().toString();

                if(name_input.equals("") || password_input.equals("")) {
                    dialog.dismiss();
                } else {
                    Session session = new Session(name_input, password_input);
                    ((Pantalla_Inicial)getActivity()).finish(session);
                    dialog.dismiss();
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return builder.create();
    }

    interface OnMyDialogResult{
        void finish(Session session);
    }
}
