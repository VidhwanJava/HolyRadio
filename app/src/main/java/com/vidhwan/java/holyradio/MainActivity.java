package com.vidhwan.java.holyradio;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    WebView wv;
    ImageView nonet;
    ProgressBar pbar;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
//        getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//        );
        wv = findViewById(R.id.wv);
        nonet = findViewById(R.id.nonet);
        pbar = findViewById(R.id.pbar);

        pbar.getIndeterminateDrawable().setColorFilter(0x006fbb,android.graphics.PorterDuff.Mode.MULTIPLY);

        wv.setWebViewClient(new WebViewClient());
        wv.loadUrl("http://yes2020.in");


        class WebViewClient extends android.webkit.WebViewClient {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                // TODO Auto-generated method stub
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {

                // TODO Auto-generated method stub

                super.onPageFinished(view, url);
                pbar.setVisibility(View.GONE);

            }

        }

        if(isOnline(getApplicationContext())){
        wv.setVisibility(View.VISIBLE);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setWebViewClient(new WebViewClient());
    }
    else{
        pbar.setVisibility(View.GONE);
        Toast.makeText(MainActivity.this, "No internet connection!", Toast.LENGTH_LONG).show();
        nonet.setVisibility(View.VISIBLE);
        wv.setVisibility(View.GONE);
    }

    }
    private boolean isOnline(Context ctx) {

        ConnectivityManager connectivityManager = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if ((connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null && connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED)
                || (connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null && connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .getState() == NetworkInfo.State.CONNECTED)) {
            return true;
        } else {
            return false;
        }

    }


    @Override
    public void onBackPressed() {
        WebView webView=findViewById(R.id.wv);
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
            Toast.makeText(MainActivity.this,"God Bless You!",Toast.LENGTH_SHORT).show();
        }
    }
}



