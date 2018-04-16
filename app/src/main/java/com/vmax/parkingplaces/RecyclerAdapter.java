package com.vmax.parkingplaces;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by welcome on 2/8/2018.
 */

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<ParkingPojo> dataSet;
    private Context context;
    private int lastCheckedPosition = -1;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView parking_name, area_text, text_capacity, text_dist_current_loc, text_parking_status
                , text_dist_gadde, text_dist_bus;
        

        public MyViewHolder(View itemView) {
            super(itemView);
            parking_name =  itemView.findViewById(R.id.parking_name);
            area_text =  itemView.findViewById(R.id.area_text);
            text_capacity =  itemView.findViewById(R.id.text_capacity);
            text_parking_status =  itemView.findViewById(R.id.text_parking_status);
            text_dist_current_loc =  itemView.findViewById(R.id.text_dist_current_loc);
            text_dist_bus =  itemView.findViewById(R.id.text_dist_busPoint);
            text_dist_gadde =  itemView.findViewById(R.id.text_dist_gadde);
        }
    }

    public RecyclerAdapter(Context context, List<ParkingPojo> data) {
        this.dataSet = data;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_list, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        holder.parking_name.setText(dataSet.get(listPosition).getParking_name());
        holder.area_text.setText(dataSet.get(listPosition).getParking_area());
        holder.text_capacity.setText(dataSet.get(listPosition).getParking_capacity());
        holder.text_dist_bus.setText(dataSet.get(listPosition).getDist_bus_point());
        holder.text_dist_current_loc.setText(dataSet.get(listPosition).getParking_distance());
        holder.text_dist_gadde.setText(dataSet.get(listPosition).getDist_gadde());
        holder.text_parking_status.setText(dataSet.get(listPosition).getParking_status());
        holder.text_parking_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Status won't be updated at this moment!", Toast.LENGTH_SHORT).show();
            }
        });
        final ParkingPojo pojo = dataSet.get(listPosition);
        holder.parking_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastCheckedPosition = Integer.parseInt(pojo.getId());

                notifyItemRangeChanged(0, dataSet.size());
                holder.parking_name.setTextColor(Color.RED);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}
