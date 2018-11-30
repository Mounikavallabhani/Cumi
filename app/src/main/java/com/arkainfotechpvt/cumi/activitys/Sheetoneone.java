package com.arkainfotechpvt.cumi.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.arkainfotechpvt.cumi.R;

public class Sheetoneone extends AppCompatActivity {
    WebView sheet7;
  Boolean aBoolean=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheetone);
        WebView webview = (WebView)findViewById(R.id.sheet7);
        webview.getSettings().setJavaScriptEnabled(true);
        if(aBoolean) {
            String pdf = "http://cufest.com/assets/pdf/Sheet1.pdf";
            String url = "http://docs.google.com/gview?embedded=true&url=" + pdf;
            //    Log.i(TAG, "Opening PDF: " + url);
            webview.getSettings().setJavaScriptEnabled(true);
            webview.loadUrl(url);
            aBoolean=false;

        }else {
            Intent intent=new Intent(Sheetoneone.this,Admin.class);
            startActivity(intent);
        }

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent intent=new Intent(Sheetoneone.this,Admin.class);
            startActivity(intent);
            aBoolean=false;
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(Sheetoneone.this,Admin.class);
        startActivity(intent);
        aBoolean=false;
    }
}
