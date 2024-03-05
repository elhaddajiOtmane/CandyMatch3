package com.example.match3game2.activities;

import static com.example.match3game2.Config.payLatest;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.match3game2.R;
import com.example.match3game2.adapter.PayListAdapter;
import com.example.match3game2.model.PayListModel;
import com.example.match3game2.util.Constants;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LatestWithActivity extends AppCompatActivity {

    private ArrayList<PayListModel> dataArrayList = new ArrayList<PayListModel>();
    private RecyclerView recyclerView;
    private PayListAdapter mAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;
        setContentView(R.layout.activity_latest_with);

        setToolbar();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        recyclerView = findViewById(R.id.leader_view_recycler);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);

        setProgressBar(View.VISIBLE);


        getServerData();
    }


    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        toolbarTitle.setText(R.string.toolbar_latest_withdrawal);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
    }

    private void getServerData() {

        String urlGetServerData = payLatest;
        System.out.print(urlGetServerData);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlGetServerData,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        setProgressBar(View.GONE);
                        try {
                            Gson gson = new Gson();
                            JSONArray jsonArray = response.getJSONArray("latest");

                            for (int p = 0; p <jsonArray.length(); p++){
                                JSONObject jsonObject = jsonArray.getJSONObject(p);
                                PayListModel data = gson.fromJson(String.valueOf(jsonObject), PayListModel.class);
                                dataArrayList.add(data);
                                Collections.sort(dataArrayList, new Comparator<PayListModel>() {
                                    @Override
                                    public int compare(PayListModel lhs, PayListModel rhs) {
                                        Integer price1 = lhs.getId();
                                        Integer price2 = rhs.getId();
                                        return price2.compareTo(price1);
                                    }
                                });
                            }
                            mAdapter = new PayListAdapter(LatestWithActivity.this, dataArrayList);
                            recyclerView.setAdapter(mAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);


    }



    private void setProgressBar(int view)
    {
        progressBar.setVisibility(view);
    }
}