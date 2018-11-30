package com.arkainfotechpvt.cumi.activitys;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.arkainfotechpvt.cumi.R;

public class Programme extends Fragment {
    WebView programwebview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_programme,container,false);

        final   ProgressDialog progressDialog=new ProgressDialog(getActivity());
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        programwebview=view.findViewById(R.id.programwebview);

        WebSettings webSettings = programwebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        programwebview.loadUrl("http://cufest.com/index.php/welcomeapi/home1_html");
          progressDialog.dismiss();
        return view;


    }
}
