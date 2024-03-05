package com.example.match3game2.game;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.match3game2.R;
import com.example.match3game2.activities.GameActivity;

public class Levels {

    @SuppressLint("SetTextI18n")
    public static void setGameLevelDetails(Context context, int Level)
    {
        int score, moves, points;

        if (Level == 1)
        {
            // Level 1 Require:
            score = 50; // Score to win
            moves = 30; // Minimum Moves
            points = 10; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 2)
        {
            // Level 2 Require:
            score = 100; // Score to win
            moves = 40; // Minimum Moves
            points = 11; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 3)
        {
            // Level 3 Require:
            score = 130; // Score to win
            moves = 45; // Minimum Moves
            points = 12; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 4)
        {
            // Level 4 Require:
            score = 140; // Score to win
            moves = 50; // Minimum Moves
            points = 13; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 5)
        {
            // Level 5 Require:
            score = 150; // Score to win
            moves = 60; // Minimum Moves
            points = 14; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 6)
        {
            // Level 6 Require:
            score = 160; // Score to win
            moves = 70; // Minimum Moves
            points = 15; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 7)
        {
            // Level 7 Require:
            score = 170; // Score to win
            moves = 80; // Minimum Moves
            points = 16; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 8)
        {
            // Level 8 Require:
            score = 180; // Score to win
            moves = 80; // Minimum Moves
            points = 17; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 9)
        {
            // Level 9 Require:
            score = 190; // Score to win
            moves = 80; // Minimum Moves
            points = 18; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 10)
        {
            // Level 10 Require:
            score = 200; // Score to win
            moves = 80; // Minimum Moves
            points = 19; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 11)
        {
            // Level 11 Require:
            score = 210; // Score to win
            moves = 80; // Minimum Moves
            points = 20; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 12)
        {
            // Level 12 Require:
            score = 220; // Score to win
            moves = 80; // Minimum Moves
            points = 21; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 13)
        {
            // Level 13 Require:
            score = 230; // Score to win
            moves = 85; // Minimum Moves
            points = 22; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 14)
        {
            // Level 14 Require:
            score = 240; // Score to win
            moves = 85; // Minimum Moves
            points = 23; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 15)
        {
            // Level 15 Require:
            score = 250; // Score to win
            moves = 85; // Minimum Moves
            points = 24; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 16)
        {
            // Level 16 Require:
            score = 260; // Score to win
            moves = 90; // Minimum Moves
            points = 25; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 17)
        {
            // Level 17 Require:
            score = 270; // Score to win
            moves = 90; // Minimum Moves
            points = 26; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 18)
        {
            // Level 18 Require:
            score = 280; // Score to win
            moves = 90; // Minimum Moves
            points = 27; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 19)
        {
            // Level 19 Require:
            score = 290; // Score to win
            moves = 90; // Minimum Moves
            points = 28; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 20)
        {
            // Level 20 Require:
            score = 300; // Score to win
            moves = 90; // Minimum Moves
            points = 29; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 21)
        {
            // Level 21 Require:
            score = 310; // Score to win
            moves = 90; // Minimum Moves
            points = 30; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 22)
        {
            // Level 22 Require:
            score = 320; // Score to win
            moves = 90; // Minimum Moves
            points = 31; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 23)
        {
            // Level 23 Require:
            score = 330; // Score to win
            moves = 100; // Minimum Moves
            points = 32; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 24)
        {
            // Level 24 Require:
            score = 340; // Score to win
            moves = 100; // Minimum Moves
            points = 33; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 25)
        {
            // Level 25 Require:
            score = 350; // Score to win
            moves = 120; // Minimum Moves
            points = 34; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 26)
        {
            // Level 26 Require:
            score = 360; // Score to win
            moves = 120; // Minimum Moves
            points = 35; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 27)
        {
            // Level 27 Require:
            score = 370; // Score to win
            moves = 120; // Minimum Moves
            points = 36; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 28)
        {
            // Level 28 Require:
            score = 380; // Score to win
            moves = 120; // Minimum Moves
            points = 37; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 29)
        {
            // Level 29 Require:
            score = 390; // Score to win
            moves = 120; // Minimum Moves
            points = 38; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 30)
        {
            // Level 30 Require:
            score = 400; // Score to win
            moves = 120; // Minimum Moves
            points = 39; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 31)
        {
            // Level 31 Require:
            score = 410; // Score to win
            moves = 120; // Minimum Moves
            points = 40; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 32)
        {
            // Level 32 Require:
            score = 420; // Score to win
            moves = 130; // Minimum Moves
            points = 41; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 33)
        {
            // Level 33 Require:
            score = 430; // Score to win
            moves = 130; // Minimum Moves
            points = 42; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 34)
        {
            // Level 34 Require:
            score = 440; // Score to win
            moves = 130; // Minimum Moves
            points = 43; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 35)
        {
            // Level 35 Require:
            score = 450; // Score to win
            moves = 150; // Minimum Moves
            points = 44; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 36)
        {
            // Level 36 Require:
            score = 460; // Score to win
            moves = 150; // Minimum Moves
            points = 45; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 37)
        {
            // Level 37 Require:
            score = 470; // Score to win
            moves = 150; // Minimum Moves
            points = 46; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 38)
        {
            // Level 38 Require:
            score = 480; // Score to win
            moves = 160; // Minimum Moves
            points = 47; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 39)
        {
            // Level 39 Require:
            score = 490; // Score to win
            moves = 170; // Minimum Moves
            points = 48; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 40)
        {
            // Level 40 Require:
            score = 500; // Score to win
            moves = 200; // Minimum Moves
            points = 49; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 41)
        {
            // Level 41 Require:
            score = 510; // Score to win
            moves = 210; // Minimum Moves
            points = 50; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 42)
        {
            // Level 42 Require:
            score = 520; // Score to win
            moves = 220; // Minimum Moves
            points = 51; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 43)
        {
            // Level 43 Require:
            score = 530; // Score to win
            moves = 230; // Minimum Moves
            points = 52; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 44)
        {
            // Level 44 Require:
            score = 540; // Score to win
            moves = 240; // Minimum Moves
            points = 53; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 45)
        {
            // Level 45 Require:
            score = 550; // Score to win
            moves = 250; // Minimum Moves
            points = 54; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 46)
        {
            // Level 46 Require:
            score = 560; // Score to win
            moves = 250; // Minimum Moves
            points = 55; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 47)
        {
            // Level 47 Require:
            score = 570; // Score to win
            moves = 250; // Minimum Moves
            points = 56; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 48)
        {
            // Level 48 Require:
            score = 580; // Score to win
            moves = 250; // Minimum Moves
            points = 57; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 49)
        {
            // Level 49 Require:
            score = 590; // Score to win
            moves = 250; // Minimum Moves
            points = 58; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 50)
        {
            // Level 50 Require:
            score = 600; // Score to win
            moves = 260; // Minimum Moves
            points = 59; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 51)
        {
            // Level 51 Require:
            score = 610; // Score to win
            moves = 270; // Minimum Moves
            points = 60; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 52)
        {
            // Level 52 Require:
            score = 620; // Score to win
            moves = 280; // Minimum Moves
            points = 61; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 53)
        {
            // Level 53 Require:
            score = 630; // Score to win
            moves = 290; // Minimum Moves
            points = 62; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 54)
        {
            // Level 54 Require:
            score = 640; // Score to win
            moves = 300; // Minimum Moves
            points = 63; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 55)
        {
            // Level 55 Require:
            score = 650; // Score to win
            moves = 315; // Minimum Moves
            points = 64; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 56)
        {
            // Level 56 Require:
            score = 660; // Score to win
            moves = 315; // Minimum Moves
            points = 65; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 57)
        {
            // Level 57 Require:
            score = 670; // Score to win
            moves = 310; // Minimum Moves
            points = 66; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 58)
        {
            // Level 58 Require:
            score = 680; // Score to win
            moves = 310; // Minimum Moves
            points = 67; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 59)
        {
            // Level 59 Require:
            score = 690; // Score to win
            moves = 310; // Minimum Moves
            points = 68; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 60)
        {
            // Level 60 Require:
            score = 700; // Score to win
            moves = 320; // Minimum Moves
            points = 69; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 61)
        {
            // Level 61 Require:
            score = 705; // Score to win
            moves = 330; // Minimum Moves
            points = 70; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 62)
        {
            // Level 62 Require:
            score = 710; // Score to win
            moves = 340; // Minimum Moves
            points = 71; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 63)
        {
            // Level 63 Require:
            score = 715; // Score to win
            moves = 350; // Minimum Moves
            points = 72; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 64)
        {
            // Level 64 Require:
            score = 720; // Score to win
            moves = 360; // Minimum Moves
            points = 73; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 65)
        {
            // Level 65 Require:
            score = 725; // Score to win
            moves = 370; // Minimum Moves
            points = 74; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 66)
        {
            // Level 66 Require:
            score = 730; // Score to win
            moves = 380; // Minimum Moves
            points = 75; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 67)
        {
            // Level 67 Require:
            score = 735; // Score to win
            moves = 390; // Minimum Moves
            points = 76; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 68)
        {
            // Level 68 Require:
            score = 740; // Score to win
            moves = 400; // Minimum Moves
            points = 77; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 69)
        {
            // Level 69 Require:
            score = 750; // Score to win
            moves = 400; // Minimum Moves
            points = 78; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else if (Level == 70)
        {
            // Level 70 Require:
            score = 755; // Score to win
            moves = 400; // Minimum Moves
            points = 79; // Users Points after completed the level
            GameActivity.gameLevelRequireTxt.setText(context.getString(R.string.level_requires) + " " + score + context.getString(R.string.moves) + " " + moves );
            GameActivity.levelScore = score;
            GameActivity.levelMoves = moves;
            GameView.userPoints = points;
        }
        else{
            GameActivity.gameLevelRequireTxt.setText("No Level Available :(");
        }
    }
}
