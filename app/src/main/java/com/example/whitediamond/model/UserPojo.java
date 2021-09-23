package com.example.whitediamond.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserPojo {
    @SerializedName("user_name")
    @Expose
    public String user_name;
    @SerializedName("phoneNum")
    @Expose
    public long phoneNum;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("passw")
    @Expose
    public String passw;
    @SerializedName("dimondPoint")
    @Expose
    public int dimondPoint;
    @SerializedName("subAdmin")
    @Expose
    public boolean subAdmin;
    @SerializedName("refferId")
    @Expose
    public int refferId;
    @SerializedName("refferBy")
    @Expose
    public int refferBy;

    @SerializedName("_id")
    @Expose
    public String id;

    public UserPojo(String user_name, String email, String passw, int dimondPoint, int refferBy) {
        this.user_name = user_name;
        this.email = email;
        this.passw = passw;
        this.dimondPoint = dimondPoint;
        this.refferBy = refferBy;
    }

    public UserPojo(String email, String passw) {
        this.email = email;
        this.passw = passw;
    }

    public UserPojo(String id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public long getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(long phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    public int getDimondPoint() {
        return dimondPoint;
    }

    public void setDimondPoint(int dimondPoint) {
        this.dimondPoint = dimondPoint;
    }

    public boolean getSubAdmin() {
        return subAdmin;
    }

    public void setSubAdmin(boolean subAdmin) {
        this.subAdmin = subAdmin;
    }

    public int getRefferId() {
        return refferId;
    }

    public void setRefferId(int refferId) {
        this.refferId = refferId;
    }

    public int getRefferBy() {
        return refferBy;
    }

    public void setRefferBy(int refferBy) {
        this.refferBy = refferBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
