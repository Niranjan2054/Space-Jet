package com.niranjan2054.spacejet.modal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ScrollView;

import com.niranjan2054.spacejet.R;

public class Player {

    //Bitmap to get character from image
    private Bitmap bitmap;

    // boolean bariable to track the ship is boosting or not
    private Boolean boosting;

    //Gravity value to add gravity effect on the ship
    private final int GRAVITY = -10;

    //Min speed
    private final int MIN_SPEED = 1;
    //Max speed
    private final int MAX_SPEED = 20;

    //position of player
    private int x;
    private int y;
    //Motion Speed of player
    private int speed = 0;

    //controlling y Coordinate
    private int maxY;
    private int minY;

    public Player(Context context, int screenX, int screenY){
       x = 75;
       y = 50;
       speed =1;
       //Getting bitmap from drawable resources
       bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.player);
       boosting = false;
       minY = 0;
       maxY = screenY-bitmap.getHeight();
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public void update(){
        if (boosting){
            speed+=2;
        }else{
            speed-=5;
        }
        if (speed>MAX_SPEED){
            speed = MAX_SPEED;
        }else if (speed<MIN_SPEED){
            speed = MIN_SPEED;
        }

        //moving the ship down
        y-= speed + GRAVITY;

        //Controlling the ship so that it doesnot go out of the screen
//        if(y<minY){
//            y = minY;
//        }else if(y>maxY){
//            y = maxY;
//        }
    }
}
