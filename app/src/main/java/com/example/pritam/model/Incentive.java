
package com.example.pritam.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Incentive implements Serializable {

    @SerializedName("bronze")
    @Expose
    private String bronze;
    @SerializedName("silver")
    @Expose
    private String silver;
    @SerializedName("gold")
    @Expose
    private String gold;

    public String getBronze() {
        return bronze;
    }

    public void setBronze(String bronze) {
        this.bronze = bronze;
    }

    public String getSilver() {
        return silver;
    }

    public void setSilver(String silver) {
        this.silver = silver;
    }

    public String getGold() {
        return gold;
    }

    public void setGold(String gold) {
        this.gold = gold;
    }

    @Override
    public String toString() {
        return "Incentive{" +
                "bronze='" + bronze + '\'' +
                ", silver='" + silver + '\'' +
                ", gold='" + gold + '\'' +
                '}';
    }
}
