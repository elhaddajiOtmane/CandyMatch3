package com.example.match3game2.activities;

import static com.example.match3game2.Config.userFeedback;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.match3game2.MainActivity;
import com.example.match3game2.R;
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
import java.util.Iterator;
import java.util.Objects;

import javax.net.ssl.HttpsURLConnection;

public class FeedbackActivity extends AppCompatActivity {

    Button sendFeedbackBtn;
    private static String email, title, message;
    private SharedPreferences prefs;
    private EditText titleEditText, msgEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;
        setContentView(R.layout.activity_feedback);

        sendFeedbackBtn = findViewById(R.id.sendFeedback_Btn);
        titleEditText = findViewById(R.id.title_EditText);
        msgEditText = findViewById(R.id.msg_EditText);


        setToolbar();

        prefs = this.getSharedPreferences("User", Context.MODE_PRIVATE);
        email = prefs.getString("userEmail", "");


        sendFeedbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Objects.requireNonNull(titleEditText.getText()).toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Title is empty !", Toast.LENGTH_LONG).show();
                }
                else if (Objects.requireNonNull(msgEditText.getText()).toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Message is empty !", Toast.LENGTH_LONG).show();
                }
                else {

                    title = titleEditText.getText().toString();
                    message = msgEditText.getText().toString();

                    //openProgressBar();
                    new SendRequest().execute();
                }
            }
        });
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        toolbarTitle.setText(R.string.toolbar_feedback);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
    }


    public class SendRequest extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try{

                URL url = new URL(userFeedback);

                JSONObject postDataParams = new JSONObject();

                postDataParams.put("user", email);
                postDataParams.put("title", title);
                postDataParams.put("message", message);

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

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while((line = in.readLine()) != null) {

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

            if (result.contains("Feedback Send"))
            {
                IbraToast.makeText(getApplicationContext(), "Your Feedback sent Successfully!", Toast.LENGTH_LONG, 1).show();

                Intent intent = new Intent(FeedbackActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            else
            {
                Toast.makeText(FeedbackActivity.this, "error: " + result, Toast.LENGTH_SHORT).show();
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


}