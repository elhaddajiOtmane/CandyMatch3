package com.example.match3game2.activities;

import static com.example.match3game2.Config.ADS_NETWORK;
import static com.example.match3game2.Config.userAchievementsUrl;
import static com.example.match3game2.Config.userLevelUrl;
import static com.example.match3game2.Config.userProfile;
import static com.example.match3game2.game.Constants.cellWidth;
import static com.example.match3game2.game.Constants.drawX;
import static com.example.match3game2.game.Constants.drawY;
import static com.example.match3game2.game.Constants.screenHeight;
import static com.example.match3game2.game.Constants.screenWidth;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.match3game2.MainActivity;
import com.example.match3game2.R;
import com.example.match3game2.game.GameView;
import com.example.match3game2.game.Levels;
import com.example.match3game2.util.AdsManager;
import com.example.match3game2.util.Tools;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameActivity extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    public static TextView scoreTxt, moveTxt, levelTxt, gameLevelTxt, gameLevelRequireTxt;
    public static String email2;
    public String email ,password;
    ProgressDialog pDialog;
    SharedPreferences prefs;
    public static int levelScore, levelMoves;
    AdsManager adsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
        cellWidth = screenWidth / 9;
        drawX = (float) (screenWidth - cellWidth * 9) / 2;
        drawY = cellWidth * 3;
        setContentView(R.layout.activity_game);

        initViews();

        prefs = getSharedPreferences("User", Context.MODE_PRIVATE);
        email = prefs.getString("userEmail", "");
        email2 = prefs.getString("userEmail", "");
        password = prefs.getString("userPassword", "");

        // Ads
        adsManager = new AdsManager(this); // Initialize AdsManager
        if (ADS_NETWORK.equals("admob")) {
            adsManager.showBannerAdmobAds(); // Initialize and show Banner Ads
        } else if (ADS_NETWORK.equals("applovin")) {
            adsManager.initApplovinAds(); // Initialize and show Banner Ads
        }


        FrameLayout root_layout = findViewById(R.id.root_layout);
        ViewGroup.LayoutParams params =
                new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);

        GameView gameView = new GameView(this);
        root_layout.addView(gameView);
        gameView.setLayoutParams(params);

        openProgressBar();
        getGameLevel();
    }

    protected void initViews()
    {
        scoreTxt = findViewById(R.id.scoreTxt);
        moveTxt = findViewById(R.id.moveTxt);
        levelTxt = findViewById(R.id.levelTxt);
    }

    @SuppressLint("SetTextI18n")
    private void showDialogGameLevel(String level)
    {
        Dialog dialog = new Dialog(this, R.style.CustomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_game_level);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        gameLevelTxt = dialog.findViewById(R.id.gameLevelTxt);
        gameLevelRequireTxt = dialog.findViewById(R.id.gameLevelRequireTxt);

        gameLevelTxt.setText("Level: " + level); // in dialog

        dialog.findViewById(R.id.bt_ok).setOnClickListener(v -> dialog.dismiss());

        dialog.findViewById(R.id.bt_close).setOnClickListener(v -> dialog.dismiss());

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }


    private void getGameLevel()
    {
        RequestQueue myRequestQueue = Volley.newRequestQueue(this);
        StringRequest myStringRequest = new StringRequest(Request.Method.POST, userProfile, response -> {
            Pattern pattern = Pattern.compile("L1(.*?)L2");
            Matcher matcher = pattern.matcher(response);

            if (matcher.find())
            {
                levelTxt.setText(matcher.group(1)); // Level in game board

                showDialogGameLevel(matcher.group(1)); // Level in dialog

                Levels.setGameLevelDetails(getApplicationContext(), Integer.parseInt(matcher.group(1))); // set game level details

                checkAchievements(Integer.parseInt(matcher.group(1))); // check achievements

                closeProgressBar(); // close progress bar

               // Toast.makeText(GameActivity.this, "r:" + levelScore + " - " + levelMoves, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
                Toast.makeText(getApplicationContext(), "error: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("email", email);
                MyData.put("password", password);
                return MyData;
            }
        };

        myRequestQueue.add(myStringRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Tools.goToActivity(this, MainActivity.class);
        finish();
    }

    private void checkAchievements(int gameLevel)
    {
        gameLevel = gameLevel - 1;
        if (gameLevel >= 5)
        {
            sendRequest("1");
        }
        if (gameLevel >= 25) {
            sendRequest("2");
        }
        if (gameLevel >= 50) {
            sendRequest("3");
        }
    }
    public void sendRequest(String achID)
    {
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, userAchievementsUrl, response -> Log.e("SetAchievementsResponse", response), error -> {
            //This code is executed if there is an error.
            Toast.makeText(getApplicationContext(), "error: " + error.getMessage(), Toast.LENGTH_LONG).show();
            //closeProgressBar();
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<>();
                MyData.put("email", email);
                MyData.put("action", "Set");
                MyData.put("achID", achID);
                return MyData;
            }
        };

        MyRequestQueue.add(MyStringRequest);
    }

    protected void openProgressBar() {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage(getString(R.string.loading));
        pDialog.show();
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
    }

    protected void closeProgressBar() {
        pDialog.dismiss();
    }
}