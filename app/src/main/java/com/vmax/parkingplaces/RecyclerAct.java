package com.vmax.parkingplaces;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerAct extends AppCompatActivity {

    private List<ParkingPojo> contactList;
    private RecyclerAdapter adapter;
    private RecyclerView recyclerView;
    public static String sone = "parking.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        recyclerView = findViewById(R.id.recyclerView);
        Call<ParkingList> call = null;
//        Call<List<ParkingPojo>> call = null;
        ApiService api = RetroClient.getApiService();
        call = api.getParking3();

        call.enqueue(new Callback<ParkingList>() {
            @Override
            public void onResponse(Call<ParkingList> call, Response<ParkingList> response) {
                //Dismiss Dialog
                Log.e("Response", String.valueOf(response.code()));
                if (response.isSuccessful()) {

                        contactList = response.body().getContacts();
                   String data =  new Gson().toJson(response);
                   Log.e("data", data);
                    adapter = new RecyclerAdapter(RecyclerAct.this, contactList);

                    recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerAct.this));
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(RecyclerAct.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ParkingList> call, Throwable t) {
                Toast.makeText(RecyclerAct.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
