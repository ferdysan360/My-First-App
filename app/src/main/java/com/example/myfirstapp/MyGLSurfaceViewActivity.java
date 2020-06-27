package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MyGLSurfaceViewActivity extends AppCompatActivity implements View.OnClickListener {
    private MyGLSurfaceView myGlSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myGlSurfaceView = new MyGLSurfaceView(this);
        setContentView(myGlSurfaceView);
    }

    @Override
    public void onClick(View v) {
        ;
    }
}