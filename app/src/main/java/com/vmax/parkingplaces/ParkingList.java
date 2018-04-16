package com.vmax.parkingplaces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by welcome on 1/30/2018.
 */

public class ParkingList {

    @SerializedName("data")
    @Expose
    private List<ParkingPojo> contacts = new ArrayList<>();
    @SerializedName("ulblist")
    @Expose
    private List<ParkingPojo> ulbs = new ArrayList<>();

    private List<ParkingPojo> parking = new ArrayList<>();

    /**
     * @return The contacts
     */

    public List<ParkingPojo> getContacts() {
        return contacts;
    }

    public List<ParkingPojo> getUlbs() {
        return ulbs;
    }

    public List<ParkingPojo> getParking() {
        return parking;
    }


}
