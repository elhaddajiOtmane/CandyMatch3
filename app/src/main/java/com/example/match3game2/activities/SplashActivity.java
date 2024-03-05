package com.example.match3game2.activities;

import static com.example.match3game2.Config.appUpdate;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.match3game2.BuildConfig;
import com.example.match3game2.MainActivity;
import com.example.match3game2.R;
import com.mikhaellopez.circularimageview.CircularImageView;

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

import javax.net.ssl.HttpsURLConnection;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    private CircularImageView logo;
    private String userEmail, userPassword;
    SharedPreferences prefs;
    private ProgressBar progressBar;
    private LinearLayout lytNoConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        logo = findViewById(R.id.app_logo);
        TextView textApp = findViewById(R.id.textApp);
        progressBar = findViewById(R.id.progress_bar);
        lytNoConnection = findViewById(R.id.lyt_no_connection);

        progressBar.setVisibility(View.GONE);
        lytNoConnection.setVisibility(View.GONE);

        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        logo.startAnimation(myanim);
        textApp.startAnimation(myanim);

        prefs = this.getSharedPreferences("User", Context.MODE_PRIVATE);
        userEmail = prefs.getString("userEmail", "");
        userPassword = prefs.getString("userPassword", "");

        int splashTimeOut = 2500;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (isConnectingToInternet(SplashActivity.this)) {
                    new SendRequest().execute();
                } else {
                    showNoInterNet();
                }

            }
        }, splashTimeOut);


        lytNoConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                progressBar.setVisibility(View.VISIBLE);
                lytNoConnection.setVisibility(View.GONE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (isConnectingToInternet(SplashActivity.this)) {
                            getTheData();
                        } else {
                            progressBar.setVisibility(View.GONE);
                            lytNoConnection.setVisibility(View.VISIBLE);
                        }
                    }
                }, 1000);
            }
        });

    }


    private void getTheData() {
        if (userEmail.isEmpty() || userPassword.isEmpty()) {
            Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        }
    }

    private void showNoInterNet() {
        logo.setVisibility(View.GONE);

        progressBar.setVisibility(View.GONE);
        lytNoConnection.setVisibility(View.VISIBLE);

    }

    public static boolean isConnectingToInternet(Context context) {
        ConnectivityManager connectivity =
                (ConnectivityManager) context.getSystemService(
                        Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }

    @SuppressLint("StaticFieldLeak")
    public class SendRequest extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try{
                URL url = new URL(appUpdate);

                String versionName = BuildConfig.VERSION_NAME;
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("versionName", versionName);
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
                    return new String("false : "+responseCode);
                }
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {

            if (result.equals("No Update"))
            {
                getTheData();
            }
            else {
                showDialogNewUpdate();
            }

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

    private void showDialogNewUpdate()
    {
        AlertDialog.Builder build = new AlertDialog.Builder(SplashActivity.this);
        build.setIcon(R.drawable.ic_update);
        build.setTitle(getString(R.string.new_version));
        build.setMessage(getString(R.string.new_update_msg));

        build.setCancelable(false);

        build.setPositiveButton(getString(R.string.close), new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
                finish();
                System.exit(0);
            }
        });

        build.setNegativeButton(getString(R.string.update), new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
                finish();
                try {
                    Uri uri = Uri.parse("market://details?id=" + getPackageName());
                    Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
                }
            }
        });

        AlertDialog olustur = build.create();
        olustur.show();
    }

}