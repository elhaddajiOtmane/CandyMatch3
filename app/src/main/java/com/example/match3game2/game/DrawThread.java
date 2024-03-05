package com.example.match3game2.game;

import android.graphics.Canvas;

public class DrawThread extends Thread{

    public GameView gameView;
    public boolean running = false;
    public static int fps;
    public static int ups;

    public DrawThread(GameView gameView)
    {
        this.gameView = gameView;
    }

    public void setRunning(boolean run)
    {
        this.running = run;
    }

    @Override
    public void run()
    {
        final int MAX_FPS = 60;
        final int MAX_UPS = 60;

        final double fOPTIMAL_TIME = (double) 1000000000 / MAX_FPS;
        final double uOPTIMAL_TIME = (double) 1000000000 / MAX_UPS;

        double uDeltaTime = 0, fDeltaTime = 0;
        int frames = 0, updates = 0;
        long startTime = System.nanoTime();
        long timer = System.currentTimeMillis();

        // Game Loop Starting:
        while (running)
        {
            long currentTime = System.nanoTime();
            uDeltaTime += (currentTime - startTime);
            fDeltaTime += (currentTime - startTime);
            startTime = currentTime;

            if (uDeltaTime >= uOPTIMAL_TIME)
            {
                gameView.update();
                updates++;
                uDeltaTime -= uOPTIMAL_TIME;
            }
            if (fDeltaTime > fOPTIMAL_TIME)
            {
                Canvas canvas = gameView.getHolder().lockCanvas(null);
                if (canvas!= null)
                {
                    synchronized (gameView.getHolder())
                    {
                        gameView.draw(canvas);
                    }
                    gameView.getHolder().unlockCanvasAndPost(canvas);
                }
                frames++;
                fDeltaTime -= fOPTIMAL_TIME;
            }

            if (System.currentTimeMillis() - timer >= 1000)
            {
                fps = frames;
                ups = updates;
                updates = 0;
                frames = 0;
                timer += 1000;
            }
        }


    }
}
