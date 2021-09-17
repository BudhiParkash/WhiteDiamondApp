package com.example.whitediamond.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GamePojo {

    @SerializedName("gameTitle")
    @Expose
    public String gameTitle;
    @SerializedName("logoLink")
    @Expose
    public String logoLink;
    @SerializedName("StopTime")
    @Expose
    public String StopTime;

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public String getLogoLink() {
        return logoLink;
    }

    public void setLogoLink(String logoLink) {
        this.logoLink = logoLink;
    }

    public String getStopTime() {
        return StopTime;
    }

    public void setStopTime(String stopTime) {
        StopTime = stopTime;
    }
}
