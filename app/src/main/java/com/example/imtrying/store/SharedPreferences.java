package com.example.imtrying.store;

import android.app.Application;

public class SharedPreferences extends Application {

    public static String STORE_NAME = "settings";

    public static String EMAIL_KEY = "email";
    public static String IS_SIGN_IN_KEY = "signing";
    public static String PASSWORD_KEY = "password";

    private String email = "";
    private String password = "";
    private Boolean signing = false;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getSigning() {
        return signing;
    }

    public void setSigning(Boolean signing) {
        this.signing = signing;
    }
}
