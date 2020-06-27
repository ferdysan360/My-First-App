package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class NextActivity extends AppCompatActivity implements View.OnClickListener {

    MySurfaceView mySurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        findViewById(R.id.button_start).setOnClickListener(this);
        findViewById(R.id.button_stop).setOnClickListener(this);

        try {
            mySurfaceView = (MySurfaceView) (findViewById(R.id.surfaceView));
        }
        catch (Exception e) {
            Log.e("Error: ", "Failed to create; " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_start:
                try {
                    mySurfaceView.startThread();
                    //Log.d("Error: ", "Start pressed;");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.button_stop:
                mySurfaceView.stopThread();
                //Log.d("Error: ", "Stop pressed;");
                break;
        }
    }
}