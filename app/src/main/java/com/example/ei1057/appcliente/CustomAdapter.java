package com.example.ei1057.appcliente;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomAdapter extends ArrayAdapter<DataModel> {

    private ArrayList<DataModel> data;
    private static final String TAG = "Client";

    private static class rowHolder {
        TextView guideID;
        ImageView signal;
    }

    public CustomAdapter(Context context, ArrayList<DataModel> data) {
        super(context, R.layout.row_layout, data);
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        rowHolder holder;

        if (v == null) {
            holder = new rowHolder();

            LayoutInflater inflater;
            inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.row_layout, parent, false);

            holder.guideID = (TextView)v.findViewById(R.id.GuideName);
            holder.signal = (ImageView)v.findViewById(R.id.Signal);

            Log.e(TAG, "Creating ListView");

            v.setTag(holder);
        } else {
            holder = (rowHolder) convertView.getTag();
        }


        holder.guideID.setText(data.get(position).getGuideID());
        Log.e(TAG, "Created");

        return v;
    }
}






