package com.niranjan2054.spacejet.modal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Printer;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {
    volatile boolean playing;

    //Objects that will be used for drawing
    private Player player;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    private Thread gameThread = null;
    public GameView(Context context) {
        super(context);
        //initializing player object
        player = new Player(context);

        //initializing drawing object
        surfaceHolder = getHolder();
        paint = new Paint();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    @Override
    public void run() {
        while (playing){
            //to update the frame
            update();
            //to draw the frame
            drawobject();

            //to control
            control();

        }
    }

    private void drawobject() {
        if(surfaceHolder.getSurface().isValid()){
            //locking canvas
            canvas = surfaceHolder.lockCanvas();
            //drawing the background color for canvas
            canvas.drawColor(Color.BLACK);
            //Drawing the player
            canvas.drawBitmap(
                    player.getBitmap(),
                    player.getX(),
                    player.getY(),
                    paint);
            //Unlocking the canvas
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control() {
        try{
            gameThread.sleep(17);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private void update() {
        player.update();
    }

    public  void pause(){
        playing= false;
        try{
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume(){
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }
}
