package com.vmax.parkingplaces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by welcome on 2/1/2018.
 */

public class FeedbackPojo {

    public FeedbackPojo(String name, String email, String message, String mobile, String imei) {
        this.name = name;
        this.email = email;
        this.message = message;
        this.mobile = mobile;
        this.imei = imei;
    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }

    public String getMobile() {
        return mobile;
    }

    public String getImei() {
        return imei;
    }

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email_id")
    @Expose
    private String email;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("imei")
    @Expose
    private String imei;



}
