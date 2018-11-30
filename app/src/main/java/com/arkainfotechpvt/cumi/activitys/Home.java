package com.arkainfotechpvt.cumi.activitys;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arkainfotechpvt.cumi.R;

import java.time.Instant;


public class Home extends Fragment {
    android.support.v4.app.FragmentManager fragmentManager;
    android.support.v4.app.FragmentTransaction fragmentTransaction;

    LinearLayout t1, t2, t3, t4, t5, t6;

    TextView personname;
    WebView webView;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String nameone;
    LinearLayout l12,l2;
    Button lineradmin;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home, container, false);
        personname=(TextView)view.findViewById(R.id.personname);

        l2=(LinearLayout)view.findViewById(R.id.l2);
        l12=(LinearLayout)view.findViewById(R.id.l12);
        lineradmin=(Button)view.findViewById(R.id.lineradmin);
        lineradmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Admin.class);
                startActivity(intent);
            }
        });

        sharedPreferences=getActivity().getSharedPreferences("logindetails",Context.MODE_PRIVATE);
        nameone=sharedPreferences.getString("password",null);

        personname.setText(" Dear "+nameone);
        webView = (WebView) view.findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("http://cufest.com/index.php/welcomeapi/home1_html");
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (isNetworkConnected()) {
         l2.setVisibility(View.GONE);
            l12.setVisibility(View.VISIBLE);
        } else {
          l2.setVisibility(View.VISIBLE);
            l12.setVisibility(View.GONE);
        }

    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm =
                (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }
}

