package com.vmax.parkingplaces;


import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by welcome on 12/26/2017.
 */

public class ParkingAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;
    private String list_pos;
    private List<ParkingPojo> arrayList;
    private int lastCheckedPosition = -1;

    public ParkingAdapter(Context context, List<ParkingPojo> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

        inflater = LayoutInflater.from(context);


    }

    public ParkingPojo getSelectedItem(){
        ParkingPojo model = arrayList.get(lastCheckedPosition);
        return model;
    }
    public int selectedPosition(){
        return lastCheckedPosition;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public ParkingPojo getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;
        final ParkingPojo item = getItem(i);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.list_parking, null);

            viewHolder.parking_name = (TextView) view.findViewById(R.id.parking_name);
            viewHolder.area_text = (TextView) view.findViewById(R.id.area_text);
            viewHolder.text_capacity = (TextView) view.findViewById(R.id.text_capacity);
            viewHolder.text_parking_status = (TextView) view.findViewById(R.id.text_parking_status);
            viewHolder.text_dist_current_loc = (TextView) view.findViewById(R.id.text_dist_current_loc);
            viewHolder.text_dist_bus = (TextView) view.findViewById(R.id.text_dist_busPoint);
            viewHolder.text_dist_gadde = (TextView) view.findViewById(R.id.text_dist_gadde);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lastCheckedPosition = Integer.parseInt(item.getId());
                    Log.e("Clicked pos", String.valueOf(lastCheckedPosition));
//                    notifyItemRangeChanged(0, itemModels.size());
                }
            });
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }


        if (arrayList.size()!=0) {

            if (arrayList.get(i).getSelected()){
                viewHolder.parking_name.setTextColor(Color.RED);
                viewHolder.area_text.setTextColor(Color.RED);
                viewHolder.text_capacity.setTextColor(Color.RED);
                viewHolder.text_dist_current_loc.setTextColor(Color.RED);
                viewHolder.text_parking_status.setTextColor(Color.RED);
                viewHolder.text_dist_gadde.setTextColor(Color.RED);
                viewHolder.text_dist_bus.setTextColor(Color.RED);
            }
            else {

                viewHolder.parking_name.setTextColor(Color.BLUE);
                viewHolder.area_text.setTextColor(Color.BLUE);
                viewHolder.text_capacity.setTextColor(Color.BLUE);
                viewHolder.text_dist_current_loc.setTextColor(Color.BLUE);
                viewHolder.text_parking_status.setTextColor(Color.BLUE);
                viewHolder.text_dist_gadde.setTextColor(Color.BLUE);
                viewHolder.text_dist_bus.setTextColor(Color.BLUE);
            }
            viewHolder.parking_name.setText(item.getParking_name());
            viewHolder.area_text.setText(item.getParking_area());
            viewHolder.text_capacity.setText(item.getParking_capacity());
            viewHolder.text_dist_current_loc.setText(item.getParking_distance());
            viewHolder.text_parking_status.setText(item.getParking_status());
            viewHolder.text_dist_gadde.setText(item.getDist_gadde());
            viewHolder.text_dist_bus.setText(item.getDist_bus_point());

        }

        return view;
    }


    private class ViewHolder {

        TextView parking_name, area_text, text_capacity, text_dist_current_loc, text_parking_status
                , text_dist_gadde, text_dist_bus;

    }

}
