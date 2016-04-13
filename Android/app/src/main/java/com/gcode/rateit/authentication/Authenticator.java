package com.gcode.rateit.authentication;

import com.gcode.rateit.data.UserInfo;

public class Authenticator {
    private static Authenticator mInstance;

    private boolean mIsAuthenticated;
    private UserInfo mUserInfo;

    public static synchronized Authenticator getInstance() {
        if (mInstance == null) {
            mInstance = new Authenticator();
        }
        return mInstance;
    }

    public boolean isAuthenticated() {
        return mIsAuthenticated;
    }

    public UserInfo getUserInfo() {
        return mUserInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        mUserInfo = userInfo;
    }
}
