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
    private int enemyCount = 5;

    private int score =0;
    private int totalEnemyEscape = 5;

    private Boom boom;
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
        boom = new Boom(context);
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
            //Drawing all stars
            for(int i =0;i<starCount;i++){
                paint.setStrokeWidth(stars[i].getStarWidth());
                canvas.drawPoint(stars[i].getX(),stars[i].getY(),paint);
            }
            canvas.drawColor(Color.BLACK);

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
            canvas.drawBitmap(
                    boom.getBitmap(),
                    boom.getX(),
                    boom.getY(),
                    paint);
            paint.setColor(Color.WHITE);
            paint.setTextSize(40);
            canvas.drawText("Score: "+score+"                  Enemy Live: "+Enemy.getNoOfLive()+"/"+totalEnemyEscape,40,40,paint);

            if (Enemy.getNoOfLive()>=totalEnemyEscape){
                paint.setColor(Color.WHITE);
                paint.setTextSize(180);
                canvas.drawText("Game Over",200,400,paint);
            }

            surfaceHolder.unlockCanvasAndPost(canvas);
            boom.setX(-250);
            boom.setY(-250);
        }
    }

    private void control() {
        try{
            gameThread.sleep(17);
            if (Enemy.getNoOfLive()==5){
                pause();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private void update() {
        player.update();
        for(int i=0;i<starCount;i++){
            stars[i].update(player.getSpeed());
        }
        boolean b;
        for(int i=0;i<enemyCount;i++){
            enemies[i].update(player.getSpeed());

            if (Rect.intersects(player.getDetectCollision(),enemies[i].getDetectCollisoin())){
                boom.setX(enemies[i].getX());
                boom.setY(enemies[i].getY());
                enemies[i].setY(-2000);
                score++;
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
