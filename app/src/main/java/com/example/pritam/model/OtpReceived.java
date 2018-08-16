
package com.example.pritam.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtpReceived {

    @SerializedName("otpRecived")
    @Expose
    private List<OtpRecived> otpRecived = null;

    public List<OtpRecived> getOtpRecived() {
        return otpRecived;
    }

    public void setOtpRecived(List<OtpRecived> otpRecived) {
        this.otpRecived = otpRecived;
    }

}
