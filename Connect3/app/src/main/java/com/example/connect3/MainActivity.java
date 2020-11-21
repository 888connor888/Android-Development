package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        counter.animate().scaleX(0f).scaleY(0f);
        counter.setImageResource(R.drawable.yellow);
        counter.animate().rotationXBy(1800).scaleX(1f).scaleY(1f).setDuration(1000);
    }
}