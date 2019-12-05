package com.niranjan2054.spacejet.modal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.niranjan2054.spacejet.R;

import java.util.Random;

public class Enemy {
    public static int noOfLive =0;
    private Bitmap bitmap;
    private int x;
    private int y;

    //enemy speed
    private int speed = 1;

    // min and max coordinates to keep the enemy inside the screen
    private int maxX;
    private int minX;
    private int maxY;
    private int minY;

    private Rect detectCollisoin;

    public Enemy(Context context,int screenX, int screenY){
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy);

        maxX = screenX;
        maxY = screenY;
        minX = 0;
        minY = 0;
        //Generating a random coordinate to add enemy
        Random random = new Random();
        speed = random.nextInt(6) + 10;
        x = screenX;
        y = random.nextInt(maxY)-bitmap.getHeight();
        if (y<bitmap.getHeight()){
            y = bitmap.getHeight();
        }
        detectCollisoin = new Rect(x,y,bitmap.getWidth(),bitmap.getHeight());
    }

    public Rect getDetectCollisoin() {
        return detectCollisoin;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void update(int playerSpeed){
//        x-=playerSpeed;
        x-=speed;
        //Adding the top, lef, bottom and right of the rect object
        detectCollisoin.left = x;
        detectCollisoin.right = x +bitmap.getWidth();
        detectCollisoin.top = y;
        detectCollisoin.bottom = y + bitmap.getHeight();
        if(x<=minX - bitmap.getWidth() || y==-2000){
            if(y!=-2000){
                noOfLive++;
            }
            Random gen = new Random();
            speed = gen.nextInt(10)+10;
            x = maxX;
            y = gen.nextInt(maxY)-bitmap.getHeight();
            if(y<bitmap.getHeight()){
                y = bitmap.getHeight();
            }
        }
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

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setX(int x) {
        this.x = x;
    }

    public static int getNoOfLive() {
        return noOfLive;
    }
}
