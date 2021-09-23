package com.example.whitediamond.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PL_Bookings {
    @SerializedName("gameName")
    @Expose
    private String gameName;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("numberSelected")
    @Expose
    private String numberSelected;
    @SerializedName("pointUsed")
    @Expose
    private int pointUsed;

    @SerializedName("userId")
    @Expose
    private UserPojo userId;

    @SerializedName("winningProb")
    @Expose
    public boolean winningProb;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumberSelected() {
        return numberSelected;
    }

    public void setNumberSelected(String numberSelected) {
        this.numberSelected = numberSelected;
    }

    public int getPointUsed() {
        return pointUsed;
    }

    public void setPointUsed(int pointUsed) {
        this.pointUsed = pointUsed;
    }

    public UserPojo getUserId() {
        return userId;
    }

    public void setUserId(UserPojo userId) {
        this.userId = userId;
    }

    public boolean getWinningProb() {
        return winningProb;
    }

    public void setWinningProb(boolean winningProb) {
        this.winningProb = winningProb;
    }
}
