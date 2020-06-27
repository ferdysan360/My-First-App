package com.example.myfirstapp;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class MyGLSurfaceView extends GLSurfaceView {
    private MyGLRenderer mRenderer;
    public MyGLSurfaceView(Context context) {
        super(context);
        mRenderer = new MyGLRenderer();
        setEGLContextClientVersion(2);
        setRenderer(mRenderer);
    }
}
