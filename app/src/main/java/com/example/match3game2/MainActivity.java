package com.example.match3game2;

import static com.example.match3game2.Config.ADS_NETWORK;
import static com.example.match3game2.Config.userProfile;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
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
import com.example.match3game2.activities.AboutActivity;
import com.example.match3game2.activities.AchievementsActivity;
import com.example.match3game2.activities.FeedbackActivity;
import com.example.match3game2.activities.GameActivity;
import com.example.match3game2.activities.LatestWithActivity;
import com.example.match3game2.activities.MyWalletActivity;
import com.example.match3game2.util.AdsManager;
import com.example.match3game2.util.Constants;
import com.example.match3game2.util.Tools;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private Button shareBtn, rateBtn, aboutBtn, playBtn, withdrawalBtn, latestWithdrawalBtn, feedbackBtn, achievements_btn;
    private TextView pointsTxt;
    String email ,password;
    SharedPreferences prefs;
    AdsManager adsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;
        setContentView(R.layout.activity_main);

        initViews();

        // Ads
        adsManager = new AdsManager(this); // Initialize AdsManager
        if (ADS_NETWORK.equals("admob")) {
            adsManager.showBannerAdmobAds(); // Initialize and show Banner Ads
        } else if (ADS_NETWORK.equals("applovin")) {
            adsManager.initApplovinAds(); // Initialize and show Banner Ads
        }

        prefs = getSharedPreferences("User", Context.MODE_PRIVATE);
        email = prefs.getString("userEmail", "");
        password = prefs.getString("userPassword", "");

        // Start Button Animation
        Tools.setAnimationBtn(this, playBtn, 1);
        Tools.setAnimationBtn(this,rateBtn, 2);
        Tools.setAnimationBtn(this,shareBtn, 2);
        Tools.setAnimationBtn(this,aboutBtn, 2);

        playBtn.setOnClickListener(v->
        {
            Tools.goToActivity(this, GameActivity.class);
        });

        withdrawalBtn.setOnClickListener(v->
        {
            Tools.goToActivity(this, MyWalletActivity.class);
        });

        latestWithdrawalBtn.setOnClickListener(v->
        {
            Tools.goToActivity(this, LatestWithActivity.class);
        });

        achievements_btn.setOnClickListener(v->
        {
            Tools.goToActivity(this, AchievementsActivity.class);
        });

        feedbackBtn.setOnClickListener(v->
        {
            Tools.goToActivity(this, FeedbackActivity.class);
        });

        aboutBtn.setOnClickListener(v->
        {
            Tools.goToActivity(this, AboutActivity.class);
        });

        rateBtn.setOnClickListener(v->
        {
            Tools.rateAction(this);
        });

        shareBtn.setOnClickListener(v->
        {
            Tools.shareAction(this);
        });


        setVolley();
    }

    private void initViews()
    {
        shareBtn = findViewById(R.id.share_btn);
        rateBtn = findViewById(R.id.rate_btn);
        aboutBtn = findViewById(R.id.about_btn);
        pointsTxt = findViewById(R.id.points_txt);
        playBtn = findViewById(R.id.btn_play);
        withdrawalBtn = findViewById(R.id.withdrawal_btn);
        latestWithdrawalBtn = findViewById(R.id.latestWithdrawal_btn);
        feedbackBtn = findViewById(R.id.feedback_btn);
        achievements_btn = findViewById(R.id.achievements_btn);
    }

    // Volley
    public void setVolley()
    {
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, userProfile, response -> {
            Pattern pattern = Pattern.compile("M1(.*?)M2");
            Matcher matcher = pattern.matcher(response);

            if (matcher.find())
            {
                pointsTxt.setText(String.valueOf(matcher.group(1)));
            }
            if (response.contains("Contact me"))
            {
                finish();
                Toast.makeText(MainActivity.this, "please contact me for this error: support@ibrahimodeh.com", Toast.LENGTH_LONG).show();
            }
            if (response.equals("0"))
            {
                blockedAccountDialog();
            }
            if (response.equals("Not Verified"))
            {
                showDialogNotVer();
            }
        }, error -> {
            //This code is executed if there is an error.
            Toast.makeText(MainActivity.this, "error: " + error.getMessage(), Toast.LENGTH_LONG).show();
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("email", email);
                MyData.put("password", password);
                return MyData;
            }
        };

        MyRequestQueue.add(MyStringRequest);
    }

    private void showDialogNotVer()
    {
        AlertDialog.Builder build = new AlertDialog.Builder(MainActivity.this);
        build.setTitle("Not Verified !");
        build.setMessage("Your App is Not Verified, you must verify it from your admin panel.");
        build.setCancelable(false);

        build.setPositiveButton("Ok", (dialog, which) -> {
            dialog.cancel();
            finish();
        });

        AlertDialog olustur = build.create();
        olustur.show();
    }

    private void blockedAccountDialog()
    {
        AlertDialog.Builder build = new AlertDialog.Builder(MainActivity.this);
        build.setTitle(getString(R.string.sorry));
        build.setMessage(getString(R.string.account_blocked));
        build.setCancelable(false);

        build.setPositiveButton(getString(R.string.Ok), (dialog, which) -> {
            dialog.cancel();
            finish();
        });

        AlertDialog olustur = build.create();
        olustur.show();
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();

            finishAffinity();
            System.exit(0);
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, getString(R.string.press_back_exit), Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }


}