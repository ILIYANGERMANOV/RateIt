package com.gcode.rateit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gcode.rateit.R;
import com.gcode.rateit.integration.FacebookIntegrationHelper;

public class LoginActivity extends AppCompatActivity {
    FacebookIntegrationHelper mFacebookIntegrationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mFacebookIntegrationHelper = new FacebookIntegrationHelper(this);
        mFacebookIntegrationHelper.init();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mFacebookIntegrationHelper.isActive()) {
            mFacebookIntegrationHelper.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFacebookIntegrationHelper.stopTracking();
    }
}
