package com.zahwaoctavioliena.ppb.uas.Apiclient;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

public class Kasir implements Serializable {
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;

    public Kasir() {
    }

    public Kasir(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
