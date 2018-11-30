package com.arkainfotechpvt.cumi.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.arkainfotechpvt.cumi.R;

public class SheetSix extends AppCompatActivity {
    WebView sheet5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet_six);
        sheet5=findViewById(R.id.sheet5);

        WebView webview = (WebView)findViewById(R.id.sheet5);
        webview.getSettings().setJavaScriptEnabled(true);
        String pdf = "http://cufest.com/assets/pdf/Sheet6.pdf";
     ///   webview.loadUrl("https://docs.google.com/viewer?url="+pdf);


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
