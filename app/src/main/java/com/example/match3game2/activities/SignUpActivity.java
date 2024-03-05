package com.example.match3game2.activities;

import static com.example.match3game2.Config.userRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
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
import com.google.android.material.textfield.TextInputEditText;

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

import es.dmoral.toasty.Toasty;

public class SignUpActivity extends AppCompatActivity {

    private EditText nameTxt, emailTxt, passwordTxt;
    Button signUpBtn;
    TextView signIn;
    private String name, email, password, deviceInfo;
    SharedPreferences prefs;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;
        setContentView(R.layout.activity_sign_up);

        nameTxt = findViewById(R.id.name_edittext);
        emailTxt = findViewById(R.id.email_edittext);
        passwordTxt = findViewById(R.id.password_edittext);
        signUpBtn = (Button) findViewById(R.id.signupBtn);
        signIn = (TextView) findViewById(R.id.signInBtn);

        deviceInfo = "Model: " + Build.MODEL + " /" +
                "Serial: " + Build.SERIAL+ " /" +
                "Hardware: " + Build.HARDWARE + " /" +
                "Id: " + Build.ID;

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = nameTxt.getText().toString().trim();
                email = emailTxt.getText().toString().trim();
                password = passwordTxt.getText().toString();

                if (name.isEmpty())
                {
                    nameTxt.setError("Enter Your Name");
                    return;
                }
                else if (email.isEmpty())
                {
                    emailTxt.setError("Enter Your Email");
                    return;
                }
                else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    emailTxt.setError("Invalid email address");
                    return;
                }
                else if (password.isEmpty())
                {
                    passwordTxt.setError("Enter Your Password");
                    return;
                }

                openProgressBar();
                new SendRequest().execute();
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GoSignIn = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(GoSignIn);
            }
        });

    }

    public class SendRequest extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0)
        {
            try{
                URL url = new URL(userRegister);

                JSONObject postDataParams = new JSONObject();

                postDataParams.put("name", name);
                postDataParams.put("email", email);
                postDataParams.put("password", password);
                postDataParams.put("points", 0);
                postDataParams.put("deviceinfo", deviceInfo);

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

            if (result.contains("Registration Successful!"))
            {
                Toasty.success(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT, true).show();

                prefs = getSharedPreferences("User", Context.MODE_PRIVATE);
                prefs.edit().putString("userEmail", email).apply();
                prefs.edit().putString("userPassword", password).apply();

                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            else if (result.contains("This email is already used!"))
            {
                Toasty.error(getApplicationContext(), "This email is already used !", Toast.LENGTH_LONG, true).show();
            }
            else if (result.contains("Something went wrong. Please try again"))
            {
                Toasty.error(getApplicationContext(), "Something went wrong. Please try again", Toast.LENGTH_LONG, true).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "Something went wrong" + result,
                        Toast.LENGTH_LONG).show();
            }

            closeProgressBar();
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


    protected void openProgressBar(){
        pDialog = new ProgressDialog(this);
        pDialog.setMessage(getString(R.string.loading));
        pDialog.show();
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
    }

    protected void closeProgressBar(){
        pDialog.dismiss();
    }

}