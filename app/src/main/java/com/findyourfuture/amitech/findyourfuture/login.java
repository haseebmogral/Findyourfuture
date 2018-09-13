package com.findyourfuture.amitech.findyourfuture;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.widget.ProfilePictureView;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;


public class login extends AppCompatActivity implements View.OnClickListener {


    LinearLayout l1, l2;
    Button btnsub;
    Animation uptodown, downtoup;
    public static EditText username;
    public static EditText password;
    private TextView textView;
    String firstName, lastName, gender, email, freinds;
    private FirebaseAuth mAuth;



    private Button fakefbloginbutton ;
    private CallbackManager callbackManager;
    private LoginButton originalfbbutton;

    Button signup;


    //google login
    private static final String TAG = "GPlusFragent";
    private int RC_SIGN_IN = 0;
    private GoogleApiClient mGoogleApiClient;
    private SignInButton signInButton;
    private Button signOutButton;
    private Button disconnectButton;
    private LinearLayout signOutView;
    private TextView mStatusTextView;
    private ProgressDialog mProgressDialog;
    private ImageView imgProfilePic;
    //button progress
    private int progress = 0;
    private static final String MANUAL_PROGRESS_AMOUNT_KEY = "manualProgressAmount";
    private static final String FIXED_PROGRESS_PERCENTAGE_KEY = "fixedTimeProgressPercentage";
    private static final String CONFIGURATION_CHANGE_KEY = "configurationChange";
    private int fixedTimeProgressPercentage = 0;
    public static final int sweepDuration = 5000;
    private boolean hasConfigurarationChanged = false;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        signup=(Button)findViewById(R.id.signup);
        signup.setOnClickListener(this);

        callbackManager = CallbackManager.Factory.create();
        originalfbbutton = (LoginButton) findViewById(R.id.originalfbbutton);
        fakefbloginbutton=(Button)findViewById(R.id.fakefbbutton);
        fakefbloginbutton.setOnClickListener(this);

        originalfbbutton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(login.this, "start main screen", Toast.LENGTH_SHORT).show();


                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {

                                Toast.makeText(login.this, response.toString(), Toast.LENGTH_SHORT).show();
                                try {


                                    Log.i("Response", response.toString());

                                    email = response.getJSONObject().getString("email");
                                    firstName = response.getJSONObject().getString("first_name");
                                    lastName = response.getJSONObject().getString("last_name");
                                    gender = response.getJSONObject().getString("gender");

//                                        if (object.has("freinds")){
//
//                                            JSONObject friend = object.getJSONObject("friends");
//                                            JSONArray data = friend.getJSONArray("data");
//                                            for (int i=0;i<data.length();i++){
//
//                                                Toast.makeText(login.this, friend.toString(), Toast.LENGTH_SHORT).show();
//
//                                                Log.i("idddd",data.getJSONObject(i).getString("id"));
//                                            }
//


                                    Profile profile = Profile.getCurrentProfile();
                                    String id = profile.getId();
                                    String link = profile.getLinkUri().toString();
                                    Log.i("Link", link);
                                    if (Profile.getCurrentProfile() != null) {
                                        Log.i("Login", "ProfilePic" + Profile.getCurrentProfile().getProfilePictureUri(200, 200));
                                    }

                                    Log.i("Login" + "Email", email);
                                    Log.i("Login" + "FirstName", firstName);
                                    Log.i("Login" + "LastName", lastName);
                                    Log.i("Login" + "Gender", gender);


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,email,first_name,last_name,gender");
                request.setParameters(parameters);
                request.executeAsync();
            }


            @Override
            public void onCancel() {
                Toast.makeText(login.this, "cancelled by user", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(login.this, "try again", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(MANUAL_PROGRESS_AMOUNT_KEY, progress);
        outState.putInt(FIXED_PROGRESS_PERCENTAGE_KEY, fixedTimeProgressPercentage);
        outState.putBoolean(CONFIGURATION_CHANGE_KEY, true);
    }


    @Override
    public void onClick(View v) {

       if (v==signup){

            Intent i=new Intent(login.this,Home.class);
            startActivity(i);
        }
    }
}
