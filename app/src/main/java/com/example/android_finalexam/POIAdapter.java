package com.example.android_finalexam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

// class for custom adapter which extends BaseAdapter class
public class POIAdapter extends BaseAdapter {

    ArrayList<POI> POIList = new ArrayList<>();
    LayoutInflater layoutInflater;

    // constructor for the POIAdapter class
    public POIAdapter(Context context, ArrayList<POI> POIList){
        this.POIList = POIList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    // implementing abstract methods
    @Override
    public int getCount() {
        return POIList.size();
    }

    @Override
    public Object getItem(int position) {
        return POIList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.poi, null);

            viewHolder = new ViewHolder();

            viewHolder.imgPOI = convertView.findViewById(R.id.imgPOI);
            viewHolder.tvPOI = convertView.findViewById(R.id.tvPOI);
            viewHolder.tvPrice = convertView.findViewById(R.id.tvPrice);
            viewHolder.tvBlank = convertView.findViewById(R.id.tvBlank);
        } else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        int imgPOIid = convertView.getResources().getIdentifier(POIList.get(position).getImage(),"mipmap",layoutInflater.getContext().getPackageName());
        viewHolder.imgPOI.setImageResource(imgPOIid);

        viewHolder.tvPOI.setText(POIList.get(position).getName());
        viewHolder.tvPrice.setText("$"+POIList.get(position).getPrice());
        viewHolder.tvBlank.setText("");

        return convertView;
    }

    // class that holds the view of list view
    private class ViewHolder{
        ImageView imgPOI;
        TextView tvPOI, tvPrice, tvBlank;
    }

}
