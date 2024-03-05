package com.example.match3game2.activities;

import static com.example.match3game2.Config.ADS_NETWORK;
import static com.example.match3game2.Config.appSettingsUrl;
import static com.example.match3game2.Config.userWithdrawal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.match3game2.R;
import com.example.match3game2.util.AdsManager;
import com.example.match3game2.util.Constants;
import com.ibrahimodeh.ibratoastlib.IbraToast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

public class MyWalletActivity extends AppCompatActivity {

    String email ,password, method;
    SharedPreferences prefs;
    TextView withNameTxt, withConvertTxt, withdrawalMethodTxt, withNote;
    Button requestBtn;
    AdsManager adsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;
        setContentView(R.layout.activity_my_wallet);

        setToolbar();
        initViews();

        // Ads
        adsManager = new AdsManager(this); // Initialize AdsManager
        if (ADS_NETWORK.equals("admob")) {
            adsManager.showBannerAdmobAds(); // Initialize and show Banner Ads
        } else if (ADS_NETWORK.equals("applovin")) {
            adsManager.initApplovinAds(); // Initialize and show Banner Ads
        }


        prefs = this.getSharedPreferences("User", Context.MODE_PRIVATE);
        email = prefs.getString("userEmail", "");
        password = prefs.getString("userPassword", "");


        requestBtn.setOnClickListener(view -> showDialogFullscreen());

        //openProgressBar();
        new SendRequest1().execute();
    }

    private void initViews() {
        requestBtn = findViewById(R.id.request_btn);
        withNameTxt = findViewById(R.id.withNameTxt);
        withConvertTxt = findViewById(R.id.withConvertTxt);
        withdrawalMethodTxt = findViewById(R.id.withdrawalMethodTxt);
        withNote = findViewById(R.id.withNote);
    }

    public class SendRequest extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try{
                URL url = new URL(userWithdrawal);

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("email", email);
                postDataParams.put("method", method);

                Log.e("params",postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode=conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK)
                {
                    BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line = "";

                    while((line = in.readLine()) != null)
                    {
                        sb.append(line);
                        break;
                    }
                    in.close();
                    return sb.toString();
                }
                else {
                    return "false : " + responseCode;
                }
            }
            catch(Exception e){
                return "Exception: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {

            Pattern pattern2 = Pattern.compile("M1(.*?)M2");
            Matcher matcher2 = pattern2.matcher(result);

            if (matcher2.find())
            {
                int mocouns = Integer.parseInt(matcher2.group(1));
            }

            if (result.equals("You need more Points"))
            {
                IbraToast.makeText(getApplicationContext(), getString(R.string.no_minimum_withdrawal), Toast.LENGTH_LONG, 2).show();
            }
            if (result.equals("Request Send"))
            {
                IbraToast.makeText(getApplicationContext(), getString(R.string.withdrawal_request_sent), Toast.LENGTH_LONG, 2).show();
                finish();
            }

            if (result.contains("Sorry, your email or password is incorrect"))
            {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }

            Toast.makeText(getApplicationContext(), "r: " + result, Toast.LENGTH_LONG).show();
            //closeProgressBar();
        }
    }

    public class SendRequest1 extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try{
                URL url = new URL(appSettingsUrl);

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("email", email);

                Log.e("params",postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, StandardCharsets.UTF_8));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode=conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK)
                {
                    BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line = "";

                    while((line = in.readLine()) != null)
                    {
                        sb.append(line);
                        break;
                    }
                    in.close();
                    return sb.toString();
                }
                else {
                    return "false : " + responseCode;
                }
            }
            catch(Exception e){
                return "Exception: " + e.getMessage();
            }
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(String result) {

            Pattern pattern = Pattern.compile("N1(.*?)N2");
            Pattern pattern2 = Pattern.compile("M1(.*?)M2");
            Pattern pattern3 = Pattern.compile("E1(.*?)E2");
            Pattern pattern4 = Pattern.compile("I1(.*?)I2");
            Matcher matcher = pattern.matcher(result);
            Matcher matcher2 = pattern2.matcher(result);
            Matcher matcher3 = pattern3.matcher(result);
            Matcher matcher4 = pattern4.matcher(result);

            if (matcher.find() && matcher2.find() && matcher3.find() && matcher4.find())
            {
                withNameTxt.setText(matcher.group(1));
                withConvertTxt.setText(matcher2.group(1));
                withdrawalMethodTxt.setText(matcher3.group(1));
                withNote.setText(matcher4.group(1));
            }

            //closeProgressBar();
        }
    }

    public String getPostDataString(JSONObject params) throws Exception {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText(R.string.toolbar_myWallet);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
    }


    private void showDialogFullscreen() {

        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_redeem, null);

        ImageButton close = dialogView.findViewById(R.id.bt_close);
        Button SendRequest = dialogView.findViewById(R.id.send_request_btn);
        final EditText CoinbaseAddress = dialogView.findViewById(R.id.coinbase_address);

        close.setOnClickListener(view -> dialogBuilder.dismiss());

        SendRequest.setOnClickListener(view -> {
            dialogBuilder.dismiss();

            if (CoinbaseAddress.getText().toString().isEmpty())
            {
                CoinbaseAddress.setError("Enter Your Email");
                return;
            }
            method = "" + CoinbaseAddress.getText().toString();
            // openProgressBar();
            new SendRequest().execute();
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }
}