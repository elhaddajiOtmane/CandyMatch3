package com.example.match3game2.activities;

import static com.example.match3game2.Config.ADS_NETWORK;
import static com.example.match3game2.Config.userLevelUrl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
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
import com.example.match3game2.util.AdsManager;
import com.example.match3game2.util.Tools;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameOverActivity extends AppCompatActivity {

    TextView txtGameResult;
    Button btnNextLevel, btnMenuMain;
    String status, points;
    private Dialog dialogAds;
    AdsManager adsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        setContentView(R.layout.activity_game_over);

        initViews();

        // Ads
        adsManager = new AdsManager(this); // Initialize AdsManager
        if (ADS_NETWORK.equals("admob")) {
            adsManager.showBannerAdmobAds(); // Initialize and show Banner Ads
            adsManager.setInterAds(); // Initialize and show Interstitial Ads
            adsManager.setRewardedAd(); // Initialize and show Reward Ads
        } else if (ADS_NETWORK.equals("applovin")) {
            adsManager.initApplovinAds(); // Initialize and show Banner Ads
            adsManager.loadMaxInterstitialAd(); // Initialize and show Interstitial Ads
            adsManager.loadMaxRewardsAd(); // Initialize and show Reward Ads
        }

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        status = bundle.getString("gameStatus");
        points = bundle.getString("gamePoints");

        if (status.equals("won"))
        {
            showDialogAds();
        }
        else if(status.equals("lose"))
        {
            txtGameResult.setText(getString(R.string.you_lose));
            btnNextLevel.setText(getString(R.string.try_again));
        }

        btnNextLevel.setOnClickListener(v->
        {
            Tools.goToActivity(this, GameActivity.class);
            finish();
        });

        btnMenuMain.setOnClickListener(v->
        {
            Tools.goToActivity(this, MainActivity.class);
            if (ADS_NETWORK.equals("admob")) {
                adsManager.showInterstitial();
            } else if (ADS_NETWORK.equals("applovin")) {
                adsManager.showMaxInterstitialAd();
            }
            finish();
        });
    }

    private void initViews()
    {
        txtGameResult = findViewById(R.id.txt_game_result);
        btnNextLevel = findViewById(R.id.btn_next_level);
        btnMenuMain = findViewById(R.id.btn_main);
    }

    public void setIncreaseUserLevel(int points)
    {
        ProgressDialog dialog = ProgressDialog.show(GameOverActivity.this, "",
                getString(R.string.loading), true);
        dialog.setCancelable(false);
        dialog.show();

        RequestQueue myRequestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest myStringRequest = new StringRequest(Request.Method.POST, userLevelUrl, response -> {
            txtGameResult.setText(getString(R.string.congrats_you_earned) + " " + points + " " + getString(R.string.points));
            dialog.dismiss();
        }, error -> {
            //This code is executed if there is an error.
            Toast.makeText(getApplicationContext(), "error: " + error.getMessage(), Toast.LENGTH_LONG).show();
            dialog.dismiss();
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("email", GameActivity.email2);
                MyData.put("points", String.valueOf(points));
                return MyData;
            }
        };

        myRequestQueue.add(myStringRequest);
    }

    private void showNetworkAds()
    {
        // Ads
        if (ADS_NETWORK.equals("admob")) {
            adsManager.showRewardsAs();
        } else if (ADS_NETWORK.equals("applovin")) {
            adsManager.showMaxVideoAds();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Tools.goToActivity(this, MainActivity.class);
        finish();
    }

    private void showDialogAds()
    {
        dialogAds = new Dialog(this, R.style.CustomDialog);
        dialogAds.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogAds.setContentView(R.layout.dialog_show_ad);
        dialogAds.setCancelable(false);
        dialogAds.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialogAds.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;


        dialogAds.findViewById(R.id.bt_ok).setOnClickListener(v -> {
            showNetworkAds(); // show ads
            dialogAds.dismiss();
            setIncreaseUserLevel(Integer.parseInt(points));
        });

        dialogAds.show();
        dialogAds.getWindow().setAttributes(lp);
    }

}