package com.example.pritam.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CommonRequest implements Serializable {
    @SerializedName("vehicleId")
    private String vehicleId;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Override
    public String toString() {
        return "CommonRequest{" +
                "vehicleId='" + vehicleId + '\'' +
                '}';
    }
}
