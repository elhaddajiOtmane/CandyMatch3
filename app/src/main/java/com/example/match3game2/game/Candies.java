package com.example.match3game2.game;

import android.graphics.Canvas;

public class Candies {
    public int poseX;
    public int poseY;
    public int color;

    public Candies(int poseX, int poseY, int color) {
        this.poseX = poseX;
        this.poseY = poseY;
        this.color = color;
    }

    public void drawCandies(Canvas canvas, SpriteSheet spriteSheet)
    {
         switch (color)
         {
             case 1:
                 canvas.drawBitmap(spriteSheet.candy1, poseX, poseY, null);
                 break;
             case 2:
                 canvas.drawBitmap(spriteSheet.candy2, poseX, poseY, null);
                 break;
             case 3:
                 canvas.drawBitmap(spriteSheet.candy3, poseX, poseY, null);
                 break;
             case 4:
                 canvas.drawBitmap(spriteSheet.candy4, poseX, poseY, null);
                 break;
             case 5:
                 canvas.drawBitmap(spriteSheet.candy5, poseX, poseY, null);
                 break;
             case 6:
                 canvas.drawBitmap(spriteSheet.candy6, poseX, poseY, null);
                 break;

         }
    }
}
