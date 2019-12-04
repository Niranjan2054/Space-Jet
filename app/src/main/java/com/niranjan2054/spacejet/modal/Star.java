package com.niranjan2054.spacejet.modal;

import java.util.Random;

public class Star {
    private int x;
    private int y;
    private int speed;

    private int maxX;
    private int maxY;
    private int minX;
    private int minY;

    public Star(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        minX = 0;
        minY = 0;
        Random generator = new Random();
        speed = generator.nextInt(10);

        x = generator.nextInt(maxX);
        y = generator.nextInt(maxY);
    }
    public void update(int playerSpeed){
        x-=playerSpeed;
        x-=speed;
        if (x<0){
            x=this.maxX;
            Random generator = new Random();
            y = generator.nextInt(this.maxY);
            speed = generator.nextInt(15);
        }
    }

    public float getStarWidth(){
        float minX = 1.0f;
        float maxX = 10.0f;
        Random rand = new Random();
        float finalX = rand.nextFloat() *(this.maxX-this.minX) + this.maxX;
        return finalX;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
