package com.gcode.rateit.integration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gcode.rateit.R;
import com.gcode.rateit.extras.MyDebugger;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;


public class GooglePlusIntegrationHelper implements
        GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    public static final int GOOGLE_PLUS_LOGIN_REQ_CODE = 0;

    private AppCompatActivity mActivity;
    private SignInButton mLoginButton;
    private GoogleApiClient mGoogleApiClient;

    private boolean mIsActivated = false;

    public GooglePlusIntegrationHelper(AppCompatActivity appCompatActivity) {
        mActivity = appCompatActivity;
        mLoginButton = (SignInButton) appCompatActivity.findViewById(R.id.google_plus_login_button);
    }

    @SuppressWarnings("unused")
    public boolean isActivated() {
        return mIsActivated;
    }

    public void init() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(mActivity)
                .enableAutoManage(mActivity, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        mLoginButton.setSize(SignInButton.SIZE_STANDARD);
        mLoginButton.setScopes(gso.getScopeArray());
        mLoginButton.setOnClickListener(this);
        mIsActivated = true;
    }

    /**
     * Google plus login activity result
     *
     * @param requestCode default
     * @param resultCode  default
     * @param data        default
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mIsActivated && requestCode == GOOGLE_PLUS_LOGIN_REQ_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Signed in successfully, show authenticated UI.
                GoogleSignInAccount acct = result.getSignInAccount();
                MyDebugger.log("Google Plus login successful");
                MyDebugger.log("display name", acct.getDisplayName());
                MyDebugger.log("email", acct.getEmail());
                MyDebugger.log("id", acct.getId());
            } else {
                // Signed out, show unauthenticated UI.
                MyDebugger.log("Signed out from Google Plus");
            }
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        MyDebugger.log("onConnectionFailed()", connectionResult.getErrorMessage());
    }

    /**
     * Google plus login button clicked, login will be performed.
     *
     * @param v google plus button's view
     */
    @Override
    public void onClick(View v) {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        mActivity.startActivityForResult(signInIntent, GOOGLE_PLUS_LOGIN_REQ_CODE);
    }
}
