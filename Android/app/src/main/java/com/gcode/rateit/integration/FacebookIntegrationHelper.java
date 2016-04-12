package com.gcode.rateit.integration;

import android.app.Activity;
import android.content.Intent;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.gcode.rateit.R;
import com.gcode.rateit.extras.MyDebugger;

public class FacebookIntegrationHelper {
    LoginButton mFBLoginButton;

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
        mFBLoginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                MyDebugger.log("user_id", loginResult.getAccessToken().getUserId());
                MyDebugger.log("Access_token", loginResult.getAccessToken().getToken());
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
        });
        mIsActive = true;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }


}
