package com.vmax.parkingplaces;


import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/*
 * Created by user on 27-Dec-17.
 */

public class SingleItemList extends Fragment {

    private ListView listview;
    View mRootview;
    private static int lastClickId = -1;
    List<String> list = new ArrayList<>();
    List<ParkingPojo> list_pojo = new ArrayList<ParkingPojo>();

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        mRootview = inflater.inflate(R.layout.single_list, null);
        
        listview = mRootview.findViewById(R.id.list_single);
        Call<ParkingList> call = null;
//        Call<List<ParkingPojo>> call = null;
        ApiService api = RetroClient.getMuncipalService();
        call = api.getMunicipal("09");
        call.enqueue(new Callback<ParkingList>() {
            @Override
            public void onResponse(Call<ParkingList> call, Response<ParkingList> response) {
                list_pojo = response.body().getUlbs();
                for (int i = 0; i< list_pojo.size(); i++){
                    list.add(list_pojo.get(i).getUlbname());
                    Log.e("list id "+i, list_pojo.get(i).getUlbid());
                    /*if (!("").equals(list_pojo.get(i).getTanker_status()))
                    Log.e("list tanker "+i, list_pojo.get(i).getTanker_status());*/
                }
                listview.setAdapter(new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_checked, list));
            }

            @Override
            public void onFailure(Call<ParkingList> call, Throwable t) {

            }
        });
//        listView.setAdapter(new SingleListAdapter(getActivity(), list));
//        listview.setChoiceMode(listview.CHOICE_MODE_SINGLE);

        //--	text filtering
//        listview.setTextFilterEnabled(true);


        listview.setAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_checked, list));


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                CheckedTextView item = (CheckedTextView) view;
                Toast.makeText(getActivity(), list.get(position) + " checked : " +
                        item.isChecked(), Toast.LENGTH_SHORT).show();
            }
        });

        return mRootview;
    }

    public static int getCurrentSelectedItemId() {
        return lastClickId;
    }

    void list(){
        /*list.add("Sachin1");
        list.add("Sachin2");
        list.add("Sachin3");
        list.add("Sachin4");
        list.add("Sachin5");
        list.add("Sachin6");
        list.add("Sachin7");
        list.add("Sachin8");
        list.add("Sachin9");
        list.add("Sachin11");
        list.add("Sachin10");
        list.add("Sachin12");
        list.add("Sachin14");*/
    }
}
