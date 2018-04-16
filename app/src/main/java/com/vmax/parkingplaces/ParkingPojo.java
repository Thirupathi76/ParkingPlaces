package com.vmax.parkingplaces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
 * Created by welcome on 1/30/2018.
 */

public class ParkingPojo {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("parking_name")
    @Expose
    private String parking_name;

    @SerializedName("parking_area")
    @Expose
    private String parking_area;

    @SerializedName("parking_distance")
    @Expose
    private String parking_distance;

    @SerializedName("parking_capacity")
    @Expose
    private String parking_capacity;

    @SerializedName("dist_gadde")
    @Expose
    private String dist_gadde;

    @SerializedName("dist_bus_point")
    @Expose
    private String dist_bus_point;

    @SerializedName("parking_status")
    @Expose
    private String parking_status;

    @SerializedName("ulbid")
    @Expose
    private String ulbid;

    @SerializedName("ulbname")
    @Expose
    private String ulbname;

    public String getUlbid() {
        return ulbid;
    }

    public String getUlbname() {
        return ulbname;
    }

    public String getTanker_status() {
        return tanker_status;
    }


    @SerializedName("tanker_status")
    @Expose
    private String tanker_status;



    Boolean isSelected;

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParking_name() {
        return parking_name;
    }

    public void setParking_name(String parking_name) {
        this.parking_name = parking_name;
    }

    public String getParking_area() {
        return parking_area;
    }

    public void setParking_area(String parking_area) {
        this.parking_area = parking_area;
    }

    public String getParking_distance() {
        return parking_distance;
    }

    public void setParking_distance(String parking_distance) {
        this.parking_distance = parking_distance;
    }

    public String getParking_capacity() {
        return parking_capacity;
    }

    public void setParking_capacity(String parking_capacity) {
        this.parking_capacity = parking_capacity;
    }

    public String getDist_gadde() {
        return dist_gadde;
    }

    public void setDist_gadde(String dist_gadde) {
        this.dist_gadde = dist_gadde;
    }

    public String getDist_bus_point() {
        return dist_bus_point;
    }

    public void setDist_bus_point(String dist_bus_point) {
        this.dist_bus_point = dist_bus_point;
    }

    public String getParking_status() {
        return parking_status;
    }

    public void setParking_status(String parking_status) {
        this.parking_status = parking_status;
    }

}
