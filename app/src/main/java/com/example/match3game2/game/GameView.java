package com.example.match3game2.game;

import static com.example.match3game2.Config.userLevelUrl;
import static com.example.match3game2.game.Constants.*;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.match3game2.MainActivity;
import com.example.match3game2.R;
import com.example.match3game2.activities.GameActivity;
import com.example.match3game2.activities.GameOverActivity;
import com.example.match3game2.util.Tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    public DrawThread thread;
    public SpriteSheet spriteSheet;
    public Candies candies;
    public Candies [][] board;
    public float oldX;
    public float oldY;
    public int poseI;
    public int poseJ;
    public String direction;
    public int newPoseI;
    public int newPoseJ;
    public boolean move = false;
    public ArrayList<ArrayList<Point>> search;

    enum GameState{
        swapping, checkSwapping, crushing, update, nothing
    }
    public GameState gameState;
    public int swapIndex = 8;

    public boolean drop_stop;

    int score, userMoves;

    // 9*9 elements
    public final int [][] level = {
            {generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies()},
            {generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies()},
            {generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies()},
            {generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies()},
            {generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies()},
            {generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies()},
            {generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies()},
            {generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies()},
            {generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies(), generateNewCandies()},

    };
    public Candies[] topBoard;
    public static int userPoints;


    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new DrawThread(this);
        spriteSheet = new SpriteSheet(getContext());

        candies = new Candies((int)drawX, (int)drawY, 0);

        // Game State
        gameState = GameState.nothing;

        // Game Points
        search = new ArrayList<>();

        init();
    }

    public void init()
    {
        board = new Candies[level.length][level[0].length];

        for(int i = 0; i < level.length; i++)
        {
            for(int j = 0; j < level[0].length; j++)
            {
                board[i][j] = new Candies((int) drawX + (cellWidth*j), (int) drawY + (cellWidth*i), level[i][j]);
            }
        }

        topBoard = new Candies[board[0].length];
        for (int j = 0; j < board.length; j++)
        {
            topBoard[j] = new Candies( (int) (drawX + j * cellWidth), (int) (drawY - cellWidth), 0);
        }
    }

    public void update()
    {
        switch (gameState)
        {
            case swapping:
                swap();
                break;
            case checkSwapping:
                fillCrushing();
                if (search.isEmpty())
                {
                    swap();
                }else {
                    gameState = GameState.crushing;
                }
                break;
            case crushing:
                for (int i = 0; i < search.size(); i++) {
                    for (int j = 0; j < search.get(i).size(); j++) {
                        board[search.get(i).get(j).x][search.get(i).get(j).y].color = 0;

                        // Game Score
                        new Handler(Looper.getMainLooper()).post(new Runnable(){
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void run() {
                                // Game Score
                                int newScore = score++;
                                GameActivity.scoreTxt.setText(getContext().getString(R.string.score) + " " + (newScore));
                            }
                        });
                    }
                    search.remove(i);
                    i--;
                }
                if (search.isEmpty())
                {
                    gameState = GameState.update;
                }
                break;
            case update:
                drop();
                fillTopBoard();
                fillCrushing();
                if (search.isEmpty()) {
                    if(!checkDrop()) {
                        gameState = GameState.nothing;

                        new Handler(Looper.getMainLooper()).post(new Runnable(){
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void run() {
                                // Game Score
                                if (score >= GameActivity.levelScore)
                                {
                                    showYouWonDialog();
                                }
                            }
                        });
                    }
                }else {
                    gameState = GameState.crushing;
                }
                drop_stop = false;
                break;
        }
    }

    private boolean checkDrop()
    {
        boolean drop = false;

        for (Candies[] candies: board) {
            for (Candies candie: candies) {
                if (candie.color == 0) {
                    drop = true;
                    break;
                }
            }
        }

        return drop;
    }

    private void fillTopBoard()
    {
        for (int j = 0; j < topBoard.length; j++) {
            if (topBoard[j].color == 0) {
                // Add new candies after crushing
                topBoard[j].color = generateNewCandies();
                if (j > 0) {
                    if (topBoard[j].color == topBoard[j - 1].color) {
                        topBoard[j].color = topBoard[j].color % 6 + 1;
                    }
                }
            }
        }
    }

    private int generateNewCandies()
    {
        Random random = new Random();

        return random.nextInt(6) % 6 + 1;
    }

    private void drop()
    {
        for (int k = 0; k < topBoard.length; k++)
        {
            if (board[0][k].color == 0)
            {
                topBoard[k].poseY += cellWidth/8;
                if ( (int) drawY - topBoard[k].poseY < cellWidth/8)
                {
                    board[0][k].color = topBoard[k].color;
                    topBoard[k].color = 0;
                    topBoard[k].poseY = board[0][k].poseY - cellWidth;
                    topBoard[k].poseX = (int) drawX + k * cellWidth;
                    drop_stop = true;
                }
            }
        }

        for (int i = 0; i < board.length-1; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].color > 0) {
                    if (board[i+1][j].color == 0) {
                        board[i][j].poseY += cellWidth/8;
                        if (((int) drawY + (i + 1) * cellWidth) - board[i][j].poseY < cellWidth/8) {
                            board[i+1][j].color = board[i][j].color;
                            board[i][j].color = 0;
                            board[i][j].poseY = (int) (drawY) + i * cellWidth;
                            board[i][j].poseX = (int) (drawX) + j * cellWidth;
                            drop_stop = true;
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    public void fillCrushing()
    {
        search.clear();
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[0].length; j++)
            {
                if (board[i][j].color > 0)
                {
                    if (j < board.length - 2 && board[i][j].color == board[i][j+1].color && board[i][j+1].color == board[i][j+2].color)
                    {
                        search.add(new ArrayList<>());
                        search.get(search.size() - 1).add(new Point(i,j));
                        search.get(search.size() - 1).add(new Point(i,j + 1));
                        search.get(search.size() - 1).add(new Point(i,j + 2));
                        j = j + 2;
                    }
                }
            }
        }

        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[0].length; j++)
            {
                if (board[i][j].color > 0)
                {
                    if (i < board.length - 2 && board[i][j].color == board[i + 1][j].color && board[i+1][j].color == board[i+2][j].color)
                    {
                        search.add(new ArrayList<>());
                        search.get(search.size() - 1).add(new Point(i, j));
                        search.get(search.size() - 1).add(new Point(i + 1, j));
                        search.get(search.size() - 1).add(new Point(i + 2, j));
                        i = i + 2;
                    }
                }
            }
        }

        for (int i = 0; i < search.size(); i++) {
            if (!allowCrushing(search.get(i)))
            {
                search.remove(i);
                i--;
            }
        }
    }

    private boolean allowCrushing(ArrayList<Point> points)
    {
        boolean allow = true;

        for (int i = 0; i < points.size(); i++)
        {
            if (points.get(i).x < board.length - 1)
            {
                if (board[points.get(i).x + 1][points.get(i).y].color == 0)
                {
                    allow = false;
                }
            }
        }
        return allow;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    public void swap(){

            if(swapIndex > 0)
            {
                try {
                    switch (direction){
                        case "right":
                            board[poseI][poseJ + 1].poseX -= cellWidth/ 8;
                            board[poseI][poseJ].poseX += cellWidth/ 8;
                            break;
                        case "left":
                            board[poseI][poseJ - 1].poseX += cellWidth/ 8;
                            board[poseI][poseJ].poseX -= cellWidth/ 8;
                            break;
                        case "up":
                            board[poseI - 1][poseJ].poseY += cellWidth/ 8;
                            board[poseI][poseJ].poseY -= cellWidth/ 8;
                            break;
                        case "down":
                            board[poseI + 1][poseJ].poseY -= cellWidth/ 8;
                            board[poseI][poseJ].poseY += cellWidth/ 8;
                            break;
                    }
                    swapIndex--;
                }catch (Exception e)
                {
                    System.out.println(e.getMessage());
                    swapIndex--;
                }

                //Log.e("SWAP_INDEX_1", "Index: " + swapIndex);

            } else{
                //Log.e("SWAP_INDEX_2", "Index: " + swapIndex);

                try {
                    Candies candies;
                    candies = board[poseI][poseJ];
                    board[poseI][poseJ] = board[newPoseI][newPoseJ];
                    board[newPoseI][newPoseJ] = candies;

                    board[poseI][poseJ].poseX = (int) (poseJ * cellWidth + drawX);
                    board[poseI][poseJ].poseY = (int) (poseI * cellWidth + drawY);
                    board[newPoseI][newPoseJ].poseX = (int) (newPoseJ * cellWidth + drawX);
                    board[newPoseI][newPoseJ].poseY = (int) (newPoseI * cellWidth + drawY);

                    swapIndex = 8;
                }catch (Exception e)
                {
                    System.out.println(e.getMessage());
                    swapIndex = 8;
                }

                if (gameState == GameState.swapping)
                {
                    gameState = GameState.checkSwapping;
                    increaseUserMove();

                }else {
                    gameState = GameState.nothing;
                }
            }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawColor(Color.WHITE); // Background Color

        // Create Game top Background
        canvas.drawBitmap(spriteSheet.topBG , 0, - cellWidth * 2, null);
        // Create Game bottom Background
        canvas.drawBitmap(spriteSheet.bottomBG , 0, drawY + cellWidth * 9, null);
        // Create Game middle Background
        canvas.drawBitmap(spriteSheet.bg_middle , 0, drawY, null);

        // Create Game Cells
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.BLACK); // cells color

        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                canvas.drawLine(0, drawY + (i * cellWidth), cellWidth * 10, drawY + (i * cellWidth), p);
                canvas.drawLine(j * cellWidth , drawY, j * cellWidth, drawY + cellWidth * 9, p);
            }
        }

        for (Candies[] candie: board)
        {
            for (Candies candies: candie)
            {
                candies.drawCandies(canvas, spriteSheet);
            }
        }

        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[0].length; j++)
            {
                candies.drawCandies(canvas, spriteSheet);
            }
        }

        //canvas.drawBitmap(spriteSheet.candiesBMP, candies.poseX, candies.poseY, null);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        final int action = event.getAction();

        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                oldX = event.getX();
                oldY = event.getY();
                poseI = (int) (oldY - drawY) / cellWidth;
                poseJ = (int) (oldX - drawX) / cellWidth;

                move = true;
                break;
            case MotionEvent.ACTION_MOVE:
                if(gameState == GameState.nothing)
                {
                    float newX = event.getX();
                    float newY = event.getY();
                    float deltaX = Math.abs(newX - oldX);
                    float deltaY = Math.abs(newY - oldY);

                    if(move && deltaX > 30 || deltaY > 30)
                    {
                        // check how many pixels our fingers moved
                        // if we moved our finger more than 30 pixels we start checking in which direction
                        move = false;

                        if (Math.abs(oldX- newX) > Math.abs(oldY - newY))
                        {
                            // if the first X touch - the end of the X touch is bigger than the
                            // first Y touch - the end of the touch Y so the direction is in the X direction
                            // now we will check which is bigger the oldX or newX for know if its left or right
                            if(newX > oldX)
                            {
                                direction = "right";
                                newPoseJ = (poseJ + 1);

                            }else {
                                direction = "left";
                                newPoseJ = (poseJ - 1);
                            }
                            newPoseI = poseI;
                        }
                        if (Math.abs(oldY - newY) > Math.abs(oldX - newX))
                        {
                            if(newY > oldY)
                            {
                                direction = "down";
                                newPoseI = (poseI + 1);
                            }else {
                                direction = "up";
                                newPoseI = (poseI - 1);
                            }
                            newPoseJ = poseJ;
                        }
                        gameState = GameState.swapping;
                    }
                }
                break;

        }

        return true;
    }


    @SuppressLint("SetTextI18n")
    private void increaseUserMove()
    {
        userMoves++;
        GameActivity.moveTxt.setText(getContext().getString(R.string.move) + " " + userMoves);

        new Handler(Looper.getMainLooper()).post(new Runnable(){
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                // User Moves
                if (userMoves > GameActivity.levelMoves)
                {
                    // Game Over
                    showGameOverDialog();
                }
            }
        });

    }

    @SuppressLint("SetTextI18n")
    private void showYouWonDialog()
    {
        Bundle bundle = new Bundle();
        bundle.putString("gameStatus", "won");
        bundle.putString("gamePoints", String.valueOf(userPoints));
        Intent intent = new Intent(getContext(), GameOverActivity.class);
        intent.putExtras(bundle);
        getContext().startActivity(intent);

    }

    private void showGameOverDialog()
    {
        Bundle bundle = new Bundle();
        bundle.putString("gameStatus", "lose");
        bundle.putString("gamePoints", "0");
        Intent intent = new Intent(getContext(), GameOverActivity.class);
        intent.putExtras(bundle);
        getContext().startActivity(intent);
    }

}
