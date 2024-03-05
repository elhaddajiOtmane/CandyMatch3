package com.example.match3game2.game;

import static com.example.match3game2.game.Constants.*;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;

public class SpriteSheet {
    public Bitmap topBG;
    public Bitmap bottomBG;
    public Bitmap bg_middle;
    public Bitmap candiesBMP;
    public Bitmap candy1;
    public Bitmap candy2;
    public Bitmap candy3;
    public Bitmap candy4;
    public Bitmap candy5;
    public Bitmap candy6;


    public SpriteSheet(Context context)
    {
        AssetManager assetManager = context.getAssets();

        try {
            // Game Top Image

            InputStream inputStream = assetManager.open("playbg_top.png");
            topBG = BitmapFactory.decodeStream(inputStream);
            topBG = Bitmap.createBitmap(topBG, 0 , 0, topBG.getWidth(), topBG.getHeight());
            topBG = Bitmap.createScaledBitmap(topBG, screenWidth, cellWidth * 5, false);

            // Game Bottom Image
            inputStream = assetManager.open("playbg_bottom.png");
            bottomBG = BitmapFactory.decodeStream(inputStream);
            bottomBG = Bitmap.createBitmap(bottomBG, 0 , 0, bottomBG.getWidth(), bottomBG.getHeight());
            bottomBG = Bitmap.createScaledBitmap(bottomBG, screenWidth, bottomBG.getHeight(), false);

            // Game Middle Image
            inputStream = assetManager.open("bg_middle.png");
            bg_middle = BitmapFactory.decodeStream(inputStream);
            bg_middle = Bitmap.createBitmap(bg_middle, 0, 0, bg_middle.getWidth(), bg_middle.getHeight());
            bg_middle = Bitmap.createScaledBitmap(bg_middle, screenWidth, cellWidth * 9, false);

            // Game Candies Images
            inputStream = assetManager.open("candy1.png");
            candiesBMP = BitmapFactory.decodeStream(inputStream);
            candy1 = Bitmap.createBitmap(candiesBMP, 0, 0, candiesBMP.getWidth(), candiesBMP.getHeight());
            candy1 = Bitmap.createScaledBitmap(candy1, cellWidth, cellWidth, false);

            // Game Candies Images
            inputStream = assetManager.open("candy2.png");
            candiesBMP = BitmapFactory.decodeStream(inputStream);
            candy2 = Bitmap.createBitmap(candiesBMP, 0, 0, candiesBMP.getWidth(), candiesBMP.getHeight());
            candy2 = Bitmap.createScaledBitmap(candy2, cellWidth, cellWidth, false);

            // Game Candies Images
            inputStream = assetManager.open("candy3.png");
            candiesBMP = BitmapFactory.decodeStream(inputStream);
            candy3 = Bitmap.createBitmap(candiesBMP, 0, 0, candiesBMP.getWidth(), candiesBMP.getHeight());
            candy3 = Bitmap.createScaledBitmap(candy3, cellWidth, cellWidth, false);

            // Game Candies Images
            inputStream = assetManager.open("candy4.png");
            candiesBMP = BitmapFactory.decodeStream(inputStream);
            candy4 = Bitmap.createBitmap(candiesBMP, 0, 0, candiesBMP.getWidth(), candiesBMP.getHeight());
            candy4 = Bitmap.createScaledBitmap(candy4, cellWidth, cellWidth, false);

            // Game Candies Images
            inputStream = assetManager.open("candy5.png");
            candiesBMP = BitmapFactory.decodeStream(inputStream);
            candy5 = Bitmap.createBitmap(candiesBMP, 0, 0, candiesBMP.getWidth(), candiesBMP.getHeight());
            candy5 = Bitmap.createScaledBitmap(candy5, cellWidth, cellWidth, false);

            // Game Candies Images
            inputStream = assetManager.open("candy6.png");
            candiesBMP = BitmapFactory.decodeStream(inputStream);
            candy6 = Bitmap.createBitmap(candiesBMP, 0, 0, candiesBMP.getWidth(), candiesBMP.getHeight());
            candy6 = Bitmap.createScaledBitmap(candy6, cellWidth, cellWidth, false);


        }catch (Exception e)
        {
            Log.e("spriteSheet", e.getMessage());
        }

    }
}