package com.niranjan2054.spacejet.modal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.niranjan2054.spacejet.R;

public class Player {

    //Bitmap to get character from image
    private Bitmap bitmap;

    //position of player
    private int x;
    private int y;
    //Motion Speed of player
    private int speed = 0;

    public Player(Context context){
       x = 75;
       y = 50;
       speed =1;
       //Getting bitmap from drawable resources
       bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.player);
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
        //Updating the coordinate of x
        x++;
    }
}
