package com.example.whitediamond.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginPojo {


    @SerializedName("findUser")
    @Expose
    private UserPojo user;
    @SerializedName("token")
    @Expose
    public String tokens;

    public UserPojo getUser() {
        return user;
    }

    public void setUser(UserPojo user) {
        this.user = user;
    }

    public String getTokens() {
        return tokens;
    }

    public void setTokens(String tokens) {
        this.tokens = tokens;
    }
}
