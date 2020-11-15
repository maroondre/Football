package com.devdre.football.Fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebView;

import com.devdre.football.R;

public class TeamWebview extends AppCompatActivity {
    WebView webView;
    String weburl;
    private ProgressDialog pdLoading;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_webview);
        getSupportActionBar().setTitle("Team Website");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        pdLoading = new ProgressDialog(this);
        pdLoading.setMessage("\tPlease Wait...");
        pdLoading.setCancelable(false);
        pdLoading.show();
        web();
        pdLoading.dismiss();
    }

    public void web()
    {
        webView = findViewById(R.id.teamWeb);
        weburl = getIntent().getStringExtra("link");
        webView.loadUrl("https://"+weburl);
    }
}