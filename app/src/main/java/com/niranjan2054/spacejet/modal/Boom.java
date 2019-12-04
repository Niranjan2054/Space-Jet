package com.niranjan2054.spacejet.modal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.niranjan2054.spacejet.R;

public class Boom {
    private Bitmap bitmap;

    private int x;
    private int y;

    public Boom(Context context){
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.boom);
        x = -250;
        y = -250;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
