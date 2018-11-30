package com.arkainfotechpvt.cumi.activitys;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arkainfotechpvt.cumi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PlayQuez extends AppCompatActivity {

    Toolbar toolbar;
    Button quizsubmit;
    public  String textid,contest;
     TextView textView;
     RadioButton c1,c2,c3,c4;
     RadioGroup radioGroup;
     RadioButton answer;
   public  String submitid=Login.id;
     EditText carpenter;
     SharedPreferences sharedPreferences;
     String rid,remid,rgivenname;
   LinearLayout card_view,card_view123;

   Chronometer chronometer;
    private long timeWhenStopped = 0;
    private boolean stopClicked;
    Long seconds;
    String s;
    String s1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_quez);
        toolbar=findViewById(R.id.toolbar);
        quizsubmit=(Button)findViewById(R.id.quizsubmit);


        card_view=(LinearLayout) findViewById(R.id.card_view);
        card_view123=(LinearLayout)findViewById(R.id.card_view12);
         card_view123.setVisibility(View.VISIBLE);
        textView=(TextView)findViewById(R.id.q1);
        c1=(RadioButton)findViewById(R.id.q2);
        c2=(RadioButton)findViewById(R.id.q3);
        c3=(RadioButton)findViewById(R.id.q4);
        c4=(RadioButton)findViewById(R.id.q5);

        carpenter=(EditText)findViewById(R.id.carpenter);
        carpenter.getText().toString();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle("Quiz");
        Registration();
        sharedPreferences=getSharedPreferences("logindetails",Context.MODE_PRIVATE);
        remid=sharedPreferences.getString("username",null);
        rgivenname=sharedPreferences.getString("password",null);
        rid=sharedPreferences.getString("id",null);


        radioGroup=(RadioGroup)findViewById(R.id.mehhirerg);
        quizsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                answer = (RadioButton) findViewById(selectedId);

//              Toast.makeText(PlayQuez.this,"id  "+ submitid.toString(), Toast.LENGTH_SHORT).show();

                quizSubmitfind();

                if (!stopClicked)  {
                   // TextView secondsText = (TextView) findViewById(R.id.hmsTekst);
                    timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
                    seconds = timeWhenStopped ;

                  //  long i=9993939399L;//L is the suffix for long
                   s1=String.valueOf(seconds);
                  s= s1.substring(1);//he
              //      Toast.makeText(PlayQuez.this, ""+seconds,Toast.LENGTH_LONG).show();
                    chronometer.stop();
                    stopClicked = true;
                }

            }
        });



    }


    protected void Registration() {

        RequestQueue rq = Volley.newRequestQueue(PlayQuez.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "http://cufest.com/index.php/welcomeapi/quize",
                /* "http://carshuru.com/tst/grephor/register.php",*/
                new Response.Listener<String>() {

                    public void onResponse(String response1) {

                       // Toast.makeText(getApplicationContext(),""+response,Toast.LENGTH_LONG).show();
                        try {
                            JSONObject jsonObject1=new JSONObject(response1);
                            String status=jsonObject1.getString("status");

                            if(status.equals("fail")){
                                card_view.setVisibility(View.GONE);
                                card_view123.setVisibility(View.VISIBLE);

                            }else if(status.equals("success")){

                                card_view123.setVisibility(View.GONE);
                                card_view.setVisibility(View.VISIBLE);

                           //         Toast.makeText(getApplicationContext(), "" + status, Toast.LENGTH_LONG).show();
                                   JSONObject jsonObject2 = jsonObject1.getJSONObject("response");

                                textid = jsonObject2.getString("testid");

                                contest = jsonObject2.getString("contest");

                                String name = jsonObject2.getString("question");
                                String choice1 = jsonObject2.getString("choice1");
                                String choice2 = jsonObject2.getString("choice2");
                                String choice3 = jsonObject2.getString("choice3");
                                String choice4 = jsonObject2.getString("choice4");

                                textView.setText("" + name);

                                c1.setText("" + choice1);
                                c2.setText("" + choice2);
                                c3.setText("" + choice3);
                                c4.setText("" + choice4);

                                chronometer=(Chronometer)findViewById(R.id.chronometer);
                                chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                                chronometer.start();
                                stopClicked = false;


                                System.out.print("ramu" + jsonObject1);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                       /* System.out.println("Result1:" + jsonObject1.toString());

                        JSONArray result=jsonObject1.getJSONArray("purchaselist");
                 */   };
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

                return params;
            }
        };
        rq.add(stringRequest);
    }

    public void quizSubmitfind(){



        RequestQueue rq = Volley.newRequestQueue(PlayQuez.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://cufest.com/index.php/welcomeapi/quizeresult",
                /* "http://carshuru.com/tst/grephor/register.php",*/
                new Response.Listener<String>() {

                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String status=jsonObject.getString("status");
                            if(status.equals("already_given")) {
                                //    Toast.makeText(getApplicationContext()," Successfully Submited"+status,Toast.LENGTH_LONG).show();
                                Toast.makeText(getApplicationContext(), "Already Submited", Toast.LENGTH_LONG).show();

                            }else if(status.equals("")){
                                Toast.makeText(getApplicationContext(), "Sucessfully Submited", Toast.LENGTH_LONG).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        // Toast.makeText(getApplicationContext(),"narasima "+response,Toast.LENGTH_LONG).show();
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
           //    Toast.makeText(PlayQuez.this,"login id"+Login.id,Toast.LENGTH_LONG).show();
                params.put("id",remid);
                params.put("givenname",rgivenname);
                params.put("contest",contest);
                params.put("review",carpenter.getText().toString());
                params.put("answer", answer.getText().toString());
                params.put("testid",textid);  /*testid*/
                params.put("time",s);

                return params;
            }
        };
        rq.add(stringRequest);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(PlayQuez.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
