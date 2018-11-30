package com.arkainfotechpvt.cumi.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.arkainfotechpvt.cumi.R;

public class SheetFour extends AppCompatActivity {
    WebView sheet3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet_four);

        sheet3=findViewById(R.id.programwebview);

        WebView webview = (WebView)findViewById(R.id.sheet3);
        webview.getSettings().setJavaScriptEnabled(true);
        String pdf = "http://cufest.com/assets/pdf/Sheet4.pdf";
        String url = "http://docs.google.com/gview?embedded=true&url=" + pdf;
        //    Log.i(TAG, "Opening PDF: " + url);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(url);



        // progressDialog.dismiss();

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
