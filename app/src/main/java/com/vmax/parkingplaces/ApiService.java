package com.vmax.parkingplaces;



import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/*
 * Created by welcome on 1/30/2018.
 */

public interface ApiService {

    /*
    Retrofit get annotation with our URL
    And our method that will return us the List of ContactList
    http://pratikbutani.x10.mx/json_data.json
    */
    /*public ApiService(Context context, String url){
    }*/
    public static final String ROOT_URL = "https://github.com/Thirupathi76/SearchMap/raw/master/";
    String url1 = "parking_1.json";
    String url2 = "parking_2.json";
    String url3 = "parking_4.json";
    String dist_id = "ulblist.php";
    public static String sone = "parking.json";
    @GET(url1)
    Call<ParkingList> getParking1();
    @GET(url2)
    Call<ParkingList> getParking2();
    @GET(url3)
    Call<ParkingList> getParking3();

    @GET(RetroClient.MAIN_URL+"/"+dist_id)
    Call<ParkingList> getMunicipal(@Query("distid") String dist_order);

    @GET(RetroClient.ROOT_URL+"/"+url1)
    Call<List<ParkingPojo>> getParking();

    @POST("missing_persons.php")
    @FormUrlEncoded
    Call<ResponseBody> savePost(@Field("person_name") String name,
                                @Field("person_type") String person_type,
                                @Field("age") String age,
                                @Field("last_seen") String last_seen,
                                @Field("cantact_no") String cantact_no,
                                @Field("description") String description,
                                @Field("imei_no") String imei_no,
                                @Field("lat") String lat,
                                @Field("lang") String lang,
                                @Field("submission_date") String submission_date,
                                @Part MultipartBody.Part file);

    /*@POST("feedback.php")
    Call<FeedbackPojo> savePost(@Body FeedbackPojo login);*/

@POST("feedback.php")
    @FormUrlEncoded
    Call<ResponseBody> missingPerson(@Field("person_type") String name,
                                @Field("person_name") String email,
                                @Field("age") String message,
                                @Field("last_seen") String imei,
                                @Field("mobile") String mobile,
                                @Part MultipartBody.Part file);

    /*@POST("feedback.php")
    Call<FeedbackPojo> savePost(@Body FeedbackPojo login);*/
}
