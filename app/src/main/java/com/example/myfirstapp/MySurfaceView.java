package com.example.myfirstapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private DrawThread drawThread;
    private Paint paint;
    private Point location;

    // Variable declaration

    public MySurfaceView(Context context) {
        super(context);
        initialize();
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize();
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    private void initialize() {
        getHolder().addCallback(this);
        setFocusable(true);
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(1);
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        paint.setStyle(Paint.Style.FILL);
        location = new Point(200, 200);
    }

    public void startThread() {
        drawThread = new DrawThread(getHolder(), this);
        drawThread.setRunning(true);
        drawThread.start();
    }

    public void stopThread() {
        drawThread.setRunning(false);
        drawThread.interrupt();
    }

    @Override
    public void onDraw(Canvas canvas) {
        //Log.d("Log: ", "On Drawing!");
        canvas.drawColor(Color.BLACK);
        canvas.drawCircle(location.x, location.y, 15, paint);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // Intentionally left empty
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // Intentionally left empty
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // Intentionally left empty
    }

    public void update() {
        location.x = location.x + 10;
        if(location.x > getWidth()) {
            location.x = 0;
        }
    }

    class DrawThread extends Thread {
        // variable definition
        public boolean run = false;
        private SurfaceHolder surfaceHolder;
        MySurfaceView mySurfaceView;

        public DrawThread(SurfaceHolder surfaceHolder, MySurfaceView mySurfaceView) {
            this.surfaceHolder = surfaceHolder;
            this.mySurfaceView = mySurfaceView;
        }

        public void setRunning(boolean run) {
            this.run = run;
        }

        public void run() {
            while (run) {
                // implement fps counter
                Canvas canvas = null;
                try {
                    canvas = surfaceHolder.lockCanvas(null);
                    if (canvas == null) {
                        Log.e("Error: ", "canvas == null");
                        continue;
                    }
                    synchronized (surfaceHolder) {
                        // onDraw method comes from View class and overrided in MySurfaceView class
                        mySurfaceView.onDraw(canvas);
                        // update method in MySurfaceView class, needed to update some object while rendering
                        mySurfaceView.update();
                    }
                }
                catch (Exception e) {
                    Log.e("Error: ", "Failed to create; " + e.getMessage());
                }
                finally {
                    if (canvas != null) {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }
    }

}
