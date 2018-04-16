package com.vmax.parkingplaces;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by welcome on 2/26/2018.
 */

public class SingleListAdapter extends BaseAdapter{

    Context context;
    ArrayList<String> list;
    private LayoutInflater inflater;
    int row_index = -1;
    public SingleListAdapter(Context activity, ArrayList<String> list) {
        this.list = list;
        context = activity;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (view == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.single_list_item, null);
            viewHolder.parking_name = view.findViewById(R.id.checkedTextView);
            viewHolder.row_linearlayout = view.findViewById(R.id.row_linearlayout);
            viewHolder.parking_name.setText(list.get(position));
            if (position == SingleItemList.getCurrentSelectedItemId()) {
                view.setBackgroundColor(Color.BLUE);
            }
            viewHolder.parking_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    viewHolder.parking_name.setChecked(true);
                }
            });
            viewHolder.row_linearlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    row_index=position;
                    notifyDataSetChanged();
                    if(row_index==position){
                        viewHolder.row_linearlayout.setBackgroundColor(Color.BLUE);
                        viewHolder.parking_name.setTextColor(Color.RED);
                    }
                    else
                    {
                        viewHolder.row_linearlayout.setBackgroundColor(Color.MAGENTA);
                        viewHolder.parking_name.setTextColor(Color.RED);
                    }
                }
            });


        view.setTag(viewHolder);
    } else {
        viewHolder = (ViewHolder) view.getTag();
    }
        return view;
    }
        private class ViewHolder {

            CheckedTextView parking_name;
            LinearLayout row_linearlayout;

        }
}
