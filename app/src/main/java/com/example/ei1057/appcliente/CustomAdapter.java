package com.example.ei1057.appcliente;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomAdapter extends ArrayAdapter<DataModel> implements Filterable {

    private Activity context;
    private ArrayList<DataModel> data;      // original data
    private ArrayList<DataModel> mdata;      // displayed data
    private static final String TAG = "Client";

    private static class rowHolder {
        TextView guideID;
        ImageView signal;
    }

    public CustomAdapter(Activity context, ArrayList<DataModel> data) {
        super(context, R.layout.row_layout, data);
        this.context = context;
        this.data = data;
        this.mdata = data;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View v = inflater.inflate(R.layout.row_layout, null, true);

        rowHolder holder = new rowHolder();
        holder.guideID = (TextView)v.findViewById(R.id.GuideName);
        //holder.signal = (ImageView)v.findViewById(R.id.Signal);

        DataModel datum = mdata.get(position);

        holder.guideID.setText(datum.getGuideId());
        // insert if-else sequence every one selecting corresponding image


        // this section creates a problem
        /*
        if (holder == null) {
            holder = new rowHolder();

            LayoutInflater inflater;
            inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.row_layout, parent, false);

            holder.guideID = (TextView)v.findViewById(R.id.GuideName);
            //holder.signal = (ImageView)v.findViewById(R.id.Signal);

            Log.e(TAG, "Creating ListView");

            convertView.setTag(holder);
        } else {
            holder = (rowHolder) convertView.getTag();
        }


        holder.guideID.setText(data.get(position).getGuideID());
        */
        Log.e(TAG, "Created");

        return v;
    }

    // filter for the listview
    public Filter getFilter(){
        Filter filter = new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults results = new FilterResults();  // Holds the results of a filtering operation in values
                ArrayList<DataModel> filteredData = new ArrayList<>();

                // saves the original data in data
                if(data == null)
                    data = new ArrayList<>(mdata);

                if(charSequence == null || charSequence.length() == 0){
                    // set the original result to return
                    results.count = data.size();
                    results.values = data;
                } else {
                    String constraint = charSequence.toString().toLowerCase();
                    for(int i = 0; i < data.size(); i++){
                        String datum = data.get(i).getGuideId().toLowerCase();
                        if(datum.startsWith(constraint)){
                            filteredData.add(new DataModel(data.get(i).getGuideId(), data.get(i).getRSSI()));
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

