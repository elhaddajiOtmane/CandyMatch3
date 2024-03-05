
package com.example.match3game2.activities;

import static com.example.match3game2.Config.websiteHomeUrl;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.match3game2.MainActivity;
import com.example.match3game2.R;
import com.example.match3game2.util.Constants;
import com.example.match3game2.util.Tools;

public class AboutActivity extends AppCompatActivity {

    TextView appVersionTxt;
    LinearLayout contactBtn, rateBtn, shareBtn, privacyBtn, websiteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;
        setContentView(R.layout.activity_about);

        setToolbar();

        appVersionTxt = findViewById(R.id.appVersionTxt);
        contactBtn = findViewById(R.id.contactBtn);
        rateBtn = findViewById(R.id.rateBtn);
        shareBtn = findViewById(R.id.shareBtn);
        privacyBtn = findViewById(R.id.privacyBtn);
        websiteBtn = findViewById(R.id.websiteBtn);

        // App Version Name
        appVersionTxt.setText(Tools.getVersionName(this));

        contactBtn.setOnClickListener(v ->
        {
            Tools.contactAction(this);
        });

        rateBtn.setOnClickListener(v ->
        {
            Tools.rateAction(this);
        });

        shareBtn.setOnClickListener(v ->
        {
            Tools.shareAction(this);
        });

        privacyBtn.setOnClickListener(v ->
        {
            Tools.goToActivity(this, PrivacyActivity.class);
        });

        websiteBtn.setOnClickListener(v ->
        {
            Intent website = new Intent(Intent.ACTION_VIEW);
            website.setData(Uri.parse(websiteHomeUrl));
            startActivity(website);
        });
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText(R.string.toolbar_about);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
    }
}