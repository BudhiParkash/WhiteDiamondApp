package com.example.whitediamond.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangePasswordPojo {

    @SerializedName("oldPass")
    @Expose
    private String oldPass;

    @SerializedName("newPass")
    @Expose
    private String newPass;

    public ChangePasswordPojo(String oldPass, String newPass) {
        this.oldPass = oldPass;
        this.newPass = newPass;

    }

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }
}
