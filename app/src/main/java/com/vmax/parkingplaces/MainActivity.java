package com.vmax.parkingplaces;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    private List<ParkingPojo> contactList;
    ParkingAdapter adapter;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        countriesList();
        frameLayout = findViewById(R.id.frameLayout);
        Picasso.with(this).load("https://github.com/Thirupathi76/SearchMap/raw/master/sss.jpg").
                into((ImageView) findViewById(R.id.image));
//        ((ImageView)findViewById(R.id.image)).setImageURI(new URI(this).toURL());
        contactList = new ArrayList<>();
        ApiService api = RetroClient.getApiService();

        Log.e("UrL", api.toString());
        Call<List<ParkingPojo>> call = api.getParking();

        call.enqueue(new Callback<List<ParkingPojo>>() {
            @Override
            public void onResponse(Call<List<ParkingPojo>> call, Response<List<ParkingPojo>> response) {
                //Dismiss Dialog

                Log.e("Response", String.valueOf(response.code()));
                if (response.isSuccessful()) {

                    contactList = response.body();
                    adapter = new ParkingAdapter(MainActivity.this, contactList);
                    listView.setAdapter(adapter);
                    for (int i = 0; i < contactList.size(); i++) {
                        contactList.get(i).setSelected(false);
                    }
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            if (adapter.selectedPosition() != -1) {
                                ParkingPojo itemModel = adapter.getSelectedItem();
                                String cityName = itemModel.getParking_name();
                                Toast.makeText(MainActivity.this, "Selected City is: " + cityName, Toast.LENGTH_SHORT).show();
                            }
                            contactList.get(position).setSelected(true);
                            adapter.notifyDataSetChanged();

                        }
                    });

                } else
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<ParkingPojo>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
       /* Call<ParkingList> call = api.getParking1();
        call.enqueue(new Callback<ParkingList>() {
            @Override
            public void onResponse(Call<ParkingList> call, Response<ParkingList> response) {
                //Dismiss Dialog
                Log.e("Response", String.valueOf(response.code()));
                if (response.isSuccessful()) {
                    contactList = response.body().getParking();
                    adapter = new ParkingAdapter(MainActivity.this, contactList);
                    listView.setAdapter(adapter);
                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ParkingList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    private void countriesList() {
        Locale[] locales = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<String>();
        for (Locale locale : locales) {
            String country = locale.getDisplayCountry();
            if (country.trim().length() > 0 && !countries.contains(country)) {
                countries.add(country);
            }
            System.out.println(country);
        }
        System.out.println("size" + countries.size());
        Collections.sort(countries);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Call<ParkingList> call = null;
//        Call<List<ParkingPojo>> call = null;
        ApiService api = RetroClient.getApiService();
        String which = "";
        String send = "";

        switch (item.getItemId()) {

            case R.id.action_parking1:
                frameLayout.setVisibility(View.GONE);
                call = api.getParking1();
                which = "Parking1";
                break;
            case R.id.action_parking2:
                frameLayout.setVisibility(View.GONE);
                call = api.getParking2();
                which = "Parking";
                break;

            case R.id.action_parking3:
                frameLayout.setVisibility(View.GONE);
                call = api.getParking3();
                which = "Parking";
                break;
            case R.id.action_feedback:
                send = "sth";
                listView.setVisibility(View.GONE);
                frameLayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.frameLayout, new FeedbackFragment()).commit();
                break;
            case R.id.action_single:
                send = "sth";
                listView.setVisibility(View.GONE);
                frameLayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.frameLayout, new SingleItemList()).commit();
                break;
            case R.id.action_recycler:
                send = "sth";
                startActivity(new Intent(MainActivity.this, RecyclerAct.class));
                break;

            default:
                break;
        }
        if (!"sth".equals(send)) {
            final String sth = which;
            call.enqueue(new Callback<ParkingList>() {
                @Override
                public void onResponse(Call<ParkingList> call, Response<ParkingList> response) {
                    //Dismiss Dialog
                    Log.e("Response", String.valueOf(response.code()));
                    if (response.isSuccessful()) {
                        if ("Parking".equals(sth))
                            contactList = response.body().getContacts();

                        else
                            contactList = response.body().getParking();
                        adapter = new ParkingAdapter(MainActivity.this, contactList);
                        listView.setAdapter(adapter);
                    } else {
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ParkingList> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                }
            });
        }

        return true;
    }
}
