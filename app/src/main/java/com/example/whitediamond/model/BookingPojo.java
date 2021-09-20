package com.example.whitediamond.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingPojo {

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
    private String userId;

    @SerializedName("winningProb")
    @Expose
    public boolean winningProb;


    public BookingPojo(String gameName, String date, String numberSelected, int pointUsed, String userId) {
        this.gameName = gameName;
        this.date = date;
        this.numberSelected = numberSelected;
        this.pointUsed = pointUsed;
        this.userId = userId;
    }

    public boolean getWinningProb() {
        return winningProb;
    }

    public void setWinningProb(boolean winningProb) {
        this.winningProb = winningProb;
    }

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
