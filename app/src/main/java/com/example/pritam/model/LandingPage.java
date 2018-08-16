
package com.example.pritam.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LandingPage implements Serializable {

    @SerializedName("storesCount")
    @Expose
    private String storesCount;
    @SerializedName("unverified")
    @Expose
    private String unverified;
    @SerializedName("storesAdded")
    @Expose
    private String storesAdded;
    @SerializedName("explorerRank")
    @Expose
    private String explorerRank;
    @SerializedName("paytmEarned")
    @Expose
    private String paytmEarned;
    @SerializedName("incentive")
    @Expose
    private Incentive incentive;

    public String getStoresCount() {
        return storesCount;
    }

    public void setStoresCount(String storesCount) {
        this.storesCount = storesCount;
    }

    public String getUnverified() {
        return unverified;
    }

    public void setUnverified(String unverified) {
        this.unverified = unverified;
    }

    public String getStoresAdded() {
        return storesAdded;
    }

    public void setStoresAdded(String storesAdded) {
        this.storesAdded = storesAdded;
    }

    public String getExplorerRank() {
        return explorerRank;
    }

    public void setExplorerRank(String explorerRank) {
        this.explorerRank = explorerRank;
    }

    public String getPaytmEarned() {
        return paytmEarned;
    }

    public void setPaytmEarned(String paytmEarned) {
        this.paytmEarned = paytmEarned;
    }

    public Incentive getIncentive() {
        return incentive;
    }

    public void setIncentive(Incentive incentive) {
        this.incentive = incentive;
    }

    @Override
    public String toString() {
        return "LandingPage{" +
                "storesCount='" + storesCount + '\'' +
                ", unverified='" + unverified + '\'' +
                ", storesAdded='" + storesAdded + '\'' +
                ", explorerRank='" + explorerRank + '\'' +
                ", paytmEarned='" + paytmEarned + '\'' +
                ", incentive=" + incentive +
                '}';
    }
}
