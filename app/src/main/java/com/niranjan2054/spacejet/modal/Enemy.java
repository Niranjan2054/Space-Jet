package com.niranjan2054.spacejet.modal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.niranjan2054.spacejet.R;

import java.util.Random;

public class Enemy {
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
    }
    public void update(int playerSpeed){
        x-=playerSpeed;
        x-=speed;
        if(x<minX - bitmap.getWidth()){
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
}
