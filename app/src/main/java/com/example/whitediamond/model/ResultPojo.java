package com.example.whitediamond.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultPojo {
    @SerializedName("_id")
    @Expose
    public String _id;
    @SerializedName("gName")
    @Expose
    public String gName;
    @SerializedName("winnerNumber")
    @Expose
    public String winnerNumber;
    @SerializedName("dateOfResult")
    @Expose
    public String dateOfResult;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public String getWinnerNumber() {
        return winnerNumber;
    }

    public void setWinnerNumber(String winnerNumber) {
        this.winnerNumber = winnerNumber;
    }

    public String getDateOfResult() {
        return dateOfResult;
    }

    public void setDateOfResult(String dateOfResult) {
        this.dateOfResult = dateOfResult;
    }


}
