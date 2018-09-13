package com.findyourfuture.amitech.findyourfuture;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity implements View.OnClickListener {
    Button signup;
    EditText uname,email,pasword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signup=(Button)findViewById(R.id.signup);
        signup.setOnClickListener(this);

        Drawable background = getResources().getDrawable( R.drawable.editextround );
        int color = Color.RED;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            signup.setBackground(getBackgroundDrawable(color, background));
        }
    }

    @Override
    public void onClick(View v) {

    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static RippleDrawable getBackgroundDrawable(int pressedColor, Drawable backgroundDrawable)
    {
            return new RippleDrawable(getPressedState(pressedColor), backgroundDrawable, null);

    }

    public static ColorStateList getPressedState(int pressedColor)
    {
        return new ColorStateList(new int[][]{ new int[]{}},new int[]{pressedColor});
    }
}
