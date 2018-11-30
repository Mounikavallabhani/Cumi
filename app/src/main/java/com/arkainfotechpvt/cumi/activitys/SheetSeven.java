package com.arkainfotechpvt.cumi.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.arkainfotechpvt.cumi.R;

public class SheetSeven extends AppCompatActivity {
    WebView sheet6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet_seven);

        sheet6=findViewById(R.id.sheet6);
        WebView webview = (WebView)findViewById(R.id.sheet6);
        webview.getSettings().setJavaScriptEnabled(true);
        String pdf = "http://cufest.com/assets/pdf/Sheet7.pdf";
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
