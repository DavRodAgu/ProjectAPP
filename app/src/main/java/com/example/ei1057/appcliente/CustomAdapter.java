package com.example.ei1057.appcliente;


import android.app.Activity;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;


//public class CustomAdapter extends ArrayAdapter<DataModel> {
public class CustomAdapter extends ArrayAdapter<DataModel> implements Filterable {

    private Activity context;
    private ArrayList<DataModel> data; //original data
    private ArrayList<DataModel> mdata;      // displayed data
    private List<ScanResult> results;
    private static final String TAG = "Client";

    private static class rowHolder {
        TextView guideID;
        ImageView signal;
    }

    /*public CustomAdapter(Context context, ArrayList<DataModel> data) {
        super(context, R.layout.row_layout, data);
        this.data = data;
    }*/

    public CustomAdapter(Activity context, ArrayList<DataModel> data, List<ScanResult> results) {
        super(context, R.layout.row_layout, data);
        //this.context = context;
        this.data = data;
        this.mdata = data;
        this.results = results;
    }

    @Override
    public int getCount() {
        return mdata.size();
    }

    @Override
    public DataModel getItem(int position) {
        return mdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    /*
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View v = inflater.inflate(R.layout.row_layout, parent, true);

        rowHolder holder = new rowHolder();
        holder.guideID = (TextView)v.findViewById(R.id.GuideName);

        DataModel datum = mdata.get(position);

        holder.guideID.setText(datum.getGuideId());
        // insert if-else sequence every one selecting corresponding image

        return v;
    }*/


    @NonNull
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
            holder.signal = (ImageView) v.findViewById(R.id.Signal);



            Log.e(TAG, "Creating ListView");

            v.setTag(holder);
        } else {
            holder = (rowHolder) convertView.getTag();
        }
        DataModel datum = mdata.get(position);

        //holder.guideID.setText(data.get(position).getGuideId());
        holder.guideID.setText(datum.getGuideId());
        //Hay que para cada wifi comprobar el RSSI
        Log.e(TAG, "Llega aqui" + "Tamaño results: " + results.size());
        for(ScanResult result : results) {
            Log.e(TAG, "Llega aqui" + "Tamaño results: " + results.size());
            if(result.SSID.equals(datum.getSSID())) {
                Log.e(TAG, "Llega aqui");
                if (result.level >= -70 && result.level < -85) {
                    Log.e(TAG, "Llega aqui 1");
                    holder.signal.setImageResource(R.mipmap.ic_wifi_high);
                } else if (result.level >= -85 && result.level < -100) {
                    Log.e(TAG, "Llega aqui 2");
                    holder.signal.setImageResource(R.mipmap.ic_wifi_mid);
                } else if (result.level >= -100 ) {
                    Log.e(TAG, "Llega aqui 3");
                    holder.signal.setImageResource(R.mipmap.ic_wifi_low);
                }
            } else {
                holder.signal.setImageResource(R.mipmap.ic_wifi_low);
                Log.e(TAG, "Llega aqui 4");
            }
        }
        Log.e(TAG, "Created");

        return v;
    }



    // filter for the listview
    @NonNull
    public Filter getFilter(){
        Filter filter = new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults results = new FilterResults();  // Holds the results of a filtering operation in values
                ArrayList<DataModel> filteredData = new ArrayList<>();

                // saves the original data in data
                if(data == null)
                    data = new ArrayList<>(mdata);

                if(charSequence == null || charSequence.length() == 0) {
                    // set the original result to return
                    results.count = data.size();
                    results.values = data;
                } else {
                    String constraint = charSequence.toString().toLowerCase();
                    for(int i = 0; i < data.size(); i++) {
                        String datum = data.get(i).getGuideId().toLowerCase();
                        if(datum.startsWith(constraint)) {
                            filteredData.add(new DataModel(data.get(i).getGuideId(), data.get(i).getSSID()));
                        }
                    }
                    // set the filtered result to return
                    results.count = filteredData.size();
                    results.values = filteredData;
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mdata = (ArrayList<DataModel>) filterResults.values; // has the filtered values
                notifyDataSetChanged(); // notifies the data with new filtered values
            }
        };
        return filter;
    }
}






