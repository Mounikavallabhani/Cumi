package com.arkainfotechpvt.cumi.activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arkainfotechpvt.cumi.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    Button login_btn;
    EditText email_id_edt,password_edt;

    public static String empid,givenname,id;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    boolean status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email_id_edt=(EditText)findViewById(R.id.email_id_edt);
        password_edt=(EditText)findViewById(R.id.password_edt);

        login_btn=(Button)findViewById(R.id.login_btn);
        login_btn=(Button)findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,MainActivity.class));
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registration();
            }
        });

        sharedPreferences = getSharedPreferences("logindetails", Context.MODE_PRIVATE);

        String uname = sharedPreferences.getString("username", null);
        if (uname != null) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        }



    }


    protected void Registration() {

        RequestQueue rq = Volley.newRequestQueue(Login.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://cufest.com/index.php/welcomeapi/signin",
                /* "http://carshuru.com/tst/grephor/register.php",*/
                new Response.Listener<String>() {

                    public void onResponse(String response) {

                //        Toast.makeText(getApplicationContext(),""+response,Toast.LENGTH_LONG).show();
                       try {
                            JSONObject jsonObject1=new JSONObject(response);
                           String status=jsonObject1.getString("status");

                           if(status.equals("success")) {
                               Toast.makeText(getApplicationContext(), "Login Successfully" , Toast.LENGTH_LONG).show();

                               JSONObject jsonObject2 = jsonObject1.getJSONObject("response");
                               empid=jsonObject2.getString("empid");
                               givenname=jsonObject2.getString("givenname");
                               id=jsonObject2.getString("id");
                            //   Toast.makeText(Login.this,"empid"+empid+"givename"+givenname+"id "+id,Toast.LENGTH_LONG).show();
                          //    System.out.println("ok"+id);
                               sharedPreferences=getSharedPreferences("logindetails",Context.MODE_PRIVATE);
                               SharedPreferences.Editor editor = sharedPreferences.edit();
                               editor.putString("username",empid);
                               editor.putString("password",givenname);
                               editor.putString("id",id);
                               editor.apply();
                               editor.commit();

                               Intent intent=new Intent(Login.this,MainActivity.class);
                               startActivity(intent);
                           }else {
                               Toast.makeText(getApplicationContext(), "Enter Correct Details", Toast.LENGTH_LONG).show();
                               email_id_edt.setText("");
                               password_edt.setText("");
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
            public  Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("empid",email_id_edt.getText().toString());
                params.put("password",password_edt.getText().toString());

                return params;
            }
        };
        rq.add(stringRequest);
    }

}
