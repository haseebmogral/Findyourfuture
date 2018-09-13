package com.findyourfuture.amitech.findyourfuture;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import com.facebook.AccessToken;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class welcomescreen extends AppCompatActivity implements View.OnClickListener {

    LinearLayout l1,l2;
    Button btnsub;
    Animation uptodown,downtoup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomescreen);

        btnsub = (Button)findViewById(R.id.buttonsub);
        l1 = (LinearLayout) findViewById(R.id.l1);
        l2 = (LinearLayout) findViewById(R.id.l2);
        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        l1.setAnimation(uptodown);
//        l2.setAnimation(downtoup);
        btnsub.setOnClickListener(this);




        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(),  //Or replace to your package name directly, instead getPackageName()  "com.your.app"
                    PackageManager.GET_SIGNATURES);

            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());

                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {

        }
    }

    @Override
    public void onClick(View view) {


            Intent intent=new Intent(welcomescreen.this,login.class);
            startActivity(intent);

    }
}
