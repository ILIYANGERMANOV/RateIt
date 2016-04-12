package com.gcode.rateit.integration;

import android.app.Activity;
import android.content.Intent;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.gcode.rateit.R;
import com.gcode.rateit.extras.MyDebugger;
import com.gcode.rateit.extras.utils.SharedPrefUtils;
import com.gcode.rateit.extras.values.Keys;

public class FacebookIntegrationHelper implements FacebookCallback<LoginResult> {
    LoginButton mFBLoginButton;
    AccessTokenTracker mAccessTokenTracker;

    private CallbackManager mCallbackManager;
    private boolean mIsActive = false;

    public FacebookIntegrationHelper(Activity activity) {
        mFBLoginButton = (LoginButton) activity.findViewById(R.id.facebook_login_button);
    }

    public boolean isActive() {
        return mIsActive;
    }

    public void init() {
        mCallbackManager = CallbackManager.Factory.create();
        mFBLoginButton.setReadPermissions("user_birthday");
        mFBLoginButton.registerCallback(mCallbackManager, this);
        mAccessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                SharedPrefUtils.saveToPreferences(Keys.PREF_FB_ACCESS_TOKEN, currentAccessToken.getToken());
            }
        };
        mIsActive = true;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void stopTracking() {
        mAccessTokenTracker.stopTracking();
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        AccessToken accessToken = loginResult.getAccessToken();
        MyDebugger.log("user_id", accessToken.getUserId());
        MyDebugger.log("Access_token", accessToken.getToken());
        SharedPrefUtils.saveToPreferences(Keys.PREF_FB_USER_ID, accessToken.getUserId());
        SharedPrefUtils.saveToPreferences(Keys.PREF_FB_ACCESS_TOKEN, accessToken.getToken());
        Profile profile = Profile.getCurrentProfile();
        if (profile != null) {
            MyDebugger.log("first name", profile.getFirstName());
            MyDebugger.log("last name", profile.getLastName());
            MyDebugger.log("profile id", profile.getId());
        } else {
            MyDebugger.log("fb profile is null");
        }
    }

    @Override
    public void onCancel() {
        MyDebugger.log("onCancel()");
    }

    @Override
    public void onError(FacebookException error) {
        MyDebugger.log("onError()", error.getMessage());
    }
}
