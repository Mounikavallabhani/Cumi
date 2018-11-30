package com.arkainfotechpvt.cumi.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arkainfotechpvt.cumi.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class AccountUs extends Fragment  {

    private int[] layouts;
    ViewPager viewPager;
    private BookPageAdapter myViewPagerAdapter;
    Timer timer;
    EditText yourname,phone,youremail,subject,message;
    Button connctdetails;
     String smail;

    private GoogleMap mMap;
   WebView webviewmaps;

   LinearLayout linearLayout1,linearLayout2;
   ImageView imagemap;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          View view=inflater.inflate(R.layout.accountus,container,false);

         linearLayout1=(LinearLayout)view.findViewById(R.id.linearmap);
         linearLayout2=(LinearLayout)view.findViewById(R.id.linerimage);
         imagemap=(ImageView)view.findViewById(R.id.imagemap);
         imagemap.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 linearLayout2.setVisibility(View.VISIBLE);
                 linearLayout1.setVisibility(View.VISIBLE);

                 WebSettings webSettings = webviewmaps.getSettings();
                 webSettings.setJavaScriptEnabled(true);
                 webviewmaps.loadUrl("https://goo.gl/maps/XuChfbX2ALT2");


             }
         });
       /* SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
*/

        webviewmaps=view.findViewById(R.id.webviewmaps);


        yourname=(EditText)view.findViewById(R.id.yourname);
          phone=(EditText)view.findViewById(R.id.phone);
          youremail=(EditText)view.findViewById(R.id.youremail);

          subject=(EditText)view.findViewById(R.id.subject);
          message=(EditText)view.findViewById(R.id.message);
          connctdetails=(Button)view.findViewById(R.id.connctdetails);
         yourname.getText().toString();
         phone.getText().toString();
         youremail.getText().toString();
         subject.getText().toString();
         message.getText().toString();

        connctdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smail=youremail.getText().toString();
                if(phone.length()!=10){
                    Toast.makeText(getActivity(),"Please Provide Proper Phone Number ", Toast.LENGTH_SHORT).show();

                }else  if(smail.contains("@")||smail.contains(".com")){
                    Registration();
                }
                else {
                    Toast.makeText(getActivity(),"Please Provide Proper mail id ", Toast.LENGTH_SHORT).show();


                }

            }
        });
        viewPager = (ViewPager)view.findViewById(R.id.indicator_image);

        layouts = new int[]{
                R.layout.screen1,
                R.layout.screen2,
                R.layout.screen3};


        myViewPagerAdapter = new BookPageAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                viewPager.post(new Runnable(){
                    @Override
                    public void run() {
                        viewPager.setCurrentItem((viewPager.getCurrentItem()+1)%layouts.length);
                    }
                });
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 3000, 3000);




        return view;
    }


    public void mapactivity(View view){

    }
    protected void Registration() {

        RequestQueue rq = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://cufest.com/index.php/welcomeapi/contact",
                /* "http://carshuru.com/tst/grephor/register.php",*/
                new Response.Listener<String>() {

                    public void onResponse(String response1) {
                        yourname.setText("");
                        smail="";
                        youremail.setText("");
                        phone.setText("");
                        subject.setText("");
                        message.setText("");

                        Toast.makeText(getActivity(),"Sucessfully Submited",Toast.LENGTH_LONG).show();

                      };
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                // TODO Auto-generated method stub
                //   pd.hide();
            }
        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                  params.put("name",yourname.getText().toString());
                  params.put("email",smail);
                  params.put("phone",phone.getText().toString());
                  params.put("subject",subject.getText().toString());
                  params.put("message",message.getText().toString());

                return params;
            }
        };
        rq.add(stringRequest);
    }

  /*  @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng manso = new LatLng(17.4573406, 78.35111059999997);
//        String url = "http://maps.google.com/maps/api/staticmap?center=" + manso +"&zoom=15&size=200x200&sensor=false";
        mMap.addMarker(new MarkerOptions().position(manso).title("Mansopresk Pvt Ltd"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(manso));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11),2000,null);
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }
  */  public class BookPageAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;
        public BookPageAdapter() {
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LayoutInflater inflater = getActivity().getLayoutInflater();
            layoutInflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);
            return view;
        }
        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

}
