package com.gcode.rateit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gcode.rateit.R;
import com.gcode.rateit.integration.FacebookIntegrationHelper;
import com.gcode.rateit.integration.GooglePlusIntegrationHelper;

public class LoginActivity extends AppCompatActivity {
    FacebookIntegrationHelper mFacebookIntegrationHelper;
    GooglePlusIntegrationHelper mGooglePlusIntegrationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mFacebookIntegrationHelper = new FacebookIntegrationHelper(this);
        mFacebookIntegrationHelper.init();
        mGooglePlusIntegrationHelper = new GooglePlusIntegrationHelper(this);
        mGooglePlusIntegrationHelper.init();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mFacebookIntegrationHelper.onActivityResult(requestCode, resultCode, data);
        mGooglePlusIntegrationHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFacebookIntegrationHelper.stopTracking();
    }
}
