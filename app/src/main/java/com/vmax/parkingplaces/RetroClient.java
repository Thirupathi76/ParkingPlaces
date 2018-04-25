package com.vmax.parkingplaces;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 * Created by welcome on 1/30/2018.
 */

public class RetroClient {

    public static final String ROOT_URL = "https://github.com/Thirupathi76/SearchMap/raw/master/Parkingplaces/";
    public static final String MAIN_URL = "http://website/folder/yourapi";

    private String url;
    /**
     * Get Retrofit Instance
     */

    private static Retrofit retrofit = null;

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    private static Retrofit getRetrofitMuncipalInstance() {
        return new Retrofit.Builder()
                .baseUrl(MAIN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    private static Retrofit getRetrofitFeedback() {
        return new Retrofit.Builder()
                .baseUrl("http://srishubakalyana.com/API/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getClient(String baseUrl) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    /**
     * Get API Service
     *
     * @return API Service
     */
    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }
    public static ApiService getMuncipalService() {
        return getRetrofitMuncipalInstance().create(ApiService.class);
    }
    public static ApiService sendFeedback() {
        return getRetrofitFeedback().create(ApiService.class);
    }
    /*public static ApiService sendFeedback() {
        return getClient("http://medaram.co.in/API/").create(ApiService.class);
    }*/

}
