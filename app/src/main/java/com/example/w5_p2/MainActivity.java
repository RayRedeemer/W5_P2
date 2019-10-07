package com.example.w5_p2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}
