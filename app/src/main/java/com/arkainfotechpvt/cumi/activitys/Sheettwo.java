package com.arkainfotechpvt.cumi.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.arkainfotechpvt.cumi.R;

public class Sheettwo extends AppCompatActivity {
    WebView sheet1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheettwo);

        sheet1=findViewById(R.id.sheet1);

        WebView webview = (WebView)findViewById(R.id.sheet1);
        webview.getSettings().setJavaScriptEnabled(true);
        String pdf = "http://cufest.com/assets/pdf/Sheet2.pdf";
        String url = "http://docs.google.com/gview?embedded=true&url=" + pdf;
        //    Log.i(TAG, "Opening PDF: " + url);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(url);

    }


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
