package com.niranjan2054.spacejet.modal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Printer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameView extends SurfaceView implements Runnable {
    volatile boolean playing;

    //Objects that will be used for drawing
    private Player player;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    private Thread gameThread = null;

    //Adding a Star Array List
    private Star[] stars;
    private int starCount = 50;


    //Adding Enemies object array
    private Enemy[] enemies;
    private int enemyCount = 3;


    public GameView(Context context,int screenX, int screenY) {
        super(context);
        //initializing player object
        player = new Player(context,screenX,screenY);

        //initializing drawing object
        surfaceHolder = getHolder();
        paint = new Paint();

        //Adding 100 stars and can be increase
        stars = new Star[starCount];
        for(int i =0;i< starCount;i++){
           stars[i] = new Star(screenX,screenY);
        }
        //initializing new enemies
        enemies = new Enemy[enemyCount];
        for(int i =0; i<enemyCount;i++){
            enemies[i] = new Enemy(context,screenX,screenY);
        }
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
            //Drawing all stars
            for(int i =0;i<starCount;i++){
                paint.setStrokeWidth(stars[i].getStarWidth());
                canvas.drawPoint(stars[i].getX(),stars[i].getY(),paint);
            }

            //Drawing the player
            canvas.drawBitmap(
                    player.getBitmap(),
                    player.getX(),
                    player.getY(),
                    paint);
            //Unlocking the canvas

            for(int i=0;i<enemyCount;i++){
                canvas.drawBitmap(
                        enemies[i].getBitmap(),
                        enemies[i].getX(),
                        enemies[i].getY(),
                        paint);
            }

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
        for(int i=0;i<starCount;i++){
            stars[i].update(player.getSpeed());
        }

        for(int i=0;i<enemyCount;i++){
            enemies[i].update(player.getSpeed());

            if (Rect.intersects(player.getDetectCollision(),enemies[i].getDetectCollisoin())){
                enemies[i].setX(-200);
            }
        }

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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction() & event.ACTION_MASK){
            case MotionEvent.ACTION_UP:
                //When the user presses on the screen
                //we will do something here
                player.stopBoosting();
                break;
            case MotionEvent.ACTION_DOWN:
                //when the user releases the screen
                //do some thing here
                player.setBoosting();
                break;
        }
        return true;
    }
}
