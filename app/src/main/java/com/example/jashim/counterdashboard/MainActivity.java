package com.example.jashim.counterdashboard;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    CoordinatorLayout coordinatorLayout;
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;
    LinearLayout linearlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearlayout = (LinearLayout) findViewById(R.id.lin);
        mWebView = (WebView) findViewById(R.id.webview);

        isNetworkAvailable();
    }

    private void isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo != null && activeNetworkInfo.isConnected()){

            mWebView.setVisibility(View.VISIBLE);
            linearlayout.setVisibility(View.GONE);




            WebSettings webSettings = mWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);

            mWebView.setWebViewClient(new WebViewClient());
            mWebView.loadUrl("https://counter-dashboard.herokuapp.com/");
        }
        else{

            linearlayout.setVisibility(View.VISIBLE);
            mWebView.setVisibility(View.GONE);
        }
    }

    public void reload(View view){
        isNetworkAvailable();
    }



    @Override
    public void onBackPressed()
    {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            super.onBackPressed();
            return;
        }
        else {
            coordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinatorlayout);
            Snackbar.make(coordinatorLayout, "Tap back again to exit", Snackbar.LENGTH_LONG).show(); }

        mBackPressed = System.currentTimeMillis();
    }

}
