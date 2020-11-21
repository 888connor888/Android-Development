package com.example.animations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView bartImageView = (ImageView) findViewById(R.id.imageView1);
        bartImageView.setX(-1000);
        bartImageView.animate().translationXBy(1000).rotation(3600).setDuration(2000);
    }

    boolean bartIsShowing = true;
    public void fadeBart(View view) {
        Log.i("Img","ImageView Tapped!");
        ImageView bartImageView = (ImageView) findViewById(R.id.imageView1);
        ImageView homerImageView = (ImageView) findViewById(R.id.imageView2);
        if(bartIsShowing) {
            bartIsShowing = false;
//            bartImageView.animate().alpha(0).setDuration(2000);
//            homerImageView.animate().alpha(1).setDuration(2000);
            bartImageView.animate().rotationBy(3600).scaleX(0f).scaleY(0f).setDuration(2000);
            homerImageView.animate().alpha(1).setDuration(2000);
        }
        else {
            bartIsShowing = true;
            homerImageView.animate().alpha(0).setDuration(2000);
//            bartImageView.animate().alpha(1).setDuration(2000);
            bartImageView.animate().rotationBy(3600).scaleX(1f).scaleY(1f).setDuration(2000);
        }
    }


}