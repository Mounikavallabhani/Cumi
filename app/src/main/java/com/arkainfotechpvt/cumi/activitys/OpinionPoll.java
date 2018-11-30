package com.arkainfotechpvt.cumi.activitys;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
import com.arkainfotechpvt.cumi.model.Products;
import com.arkainfotechpvt.cumi.sql.MyDBHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.OptionalDataException;
import java.util.HashMap;
import java.util.Map;


public class OpinionPoll extends AppCompatActivity {

    TextView quizTitle, quizQuestion;
    RadioGroup answerGroup;
    RadioButton option1Btn, option2Btn, option3Btn, option4Btn;
    RadioButton selectedoption;
    Button submitBtn;
   Toolbar toolbar;
   String pollid;
    SharedPreferences sharedPreferences;
     String oempid;
     LinearLayout ol1,ol2;
     String [] questionid,question,op1,op2,op3,op4;
    MyDBHandler dbHandler;
    String[] array1;
    String aa0[],aa1[], aa2[], aa3[], aa4[], aa5[], aa6[], aa7[], aa8[];
    String[] parts;
    public static int j=0;
    int num=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
       // setUpToolbar(R.string.quiz, true);
          j=0;
        ol1=(LinearLayout)findViewById(R.id.ol1);
        ol2=(LinearLayout)findViewById(R.id.ol2);
         ol2.setVisibility(View.GONE);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle("Poll");
        sharedPreferences=getSharedPreferences("logindetails",Context.MODE_PRIVATE);
        oempid=sharedPreferences.getString("username",null);

        dbHandler = new MyDBHandler(this, null, null, 1);
        //    printDatabase();

        dbHandler.deleteAllData();


        bindView();
    }

    private void bindView() {

        answerGroup=(RadioGroup)findViewById(R.id.mehhirerg);
        quizQuestion = findViewById(R.id.question);




     //   answerGroup = findViewById(R.id.optionone);
        option1Btn = findViewById(R.id.optionone);
        option2Btn = findViewById(R.id.optiontwo);
        option3Btn = findViewById(R.id.optionthree);
        option4Btn = findViewById(R.id.optionfour);
      //  option5Btn = findViewById(R.id.optionfive);

           Registration();

        submitBtn = findViewById(R.id.optionsubmit);


    }
    public  void Retriving(){

    }

    protected void Registration() {

        RequestQueue rq = Volley.newRequestQueue(OpinionPoll.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                " http://cufest.com/index.php/welcomeapi/pollall",
                /* "http://carshuru.com/tst/grephor/register.php",*/
                new Response.Listener<String>() {

                    public void onResponse(String response1) {

                        // Toast.makeText(getApplicationContext(),""+response,Toast.LENGTH_LONG).show();
                        try {
                            JSONObject jsonObject1=new JSONObject(response1);
                            String status=jsonObject1.getString("status");

                            if(status.equals("fail")){
                                ol1.setVisibility(View.GONE);
                                ol2.setVisibility(View.VISIBLE);

                            }else if(status.equals("success")) {

                                ol2.setVisibility(View.GONE);
                                ol1.setVisibility(View.VISIBLE);

                              //        Toast.makeText(getApplicationContext(),""+jsonObject1,Toast.LENGTH_LONG).show();
                                JSONArray jsonArray = jsonObject1.getJSONArray("response");

                                questionid=new String[jsonArray.length()];
                                question = new String[jsonArray.length()];
                                op1 = new String[jsonArray.length()];
                                op2 = new String[jsonArray.length()];
                                op3 = new String[jsonArray.length()];
                                op4 = new String[jsonArray.length()];

                                Products product;
                                 //   Toast.makeText(getApplicationContext()," ok "+jsonArray,Toast.LENGTH_LONG).show();
                                 System.out.println("JsonData:"+jsonArray);
                                 for (int i=0;i<jsonArray.length();i++){
                                    JSONObject jsonObject2 = jsonArray.getJSONObject(i);

                                    questionid[i]=jsonObject2.getString("pollid");
                                     question[i] = jsonObject2.getString("question");
                                     op1[i] = jsonObject2.getString("option1");
                                     op2[i] = jsonObject2.getString("option2");
                                     op3[i] = jsonObject2.getString("option3");
                                     op4[i] = jsonObject2.getString("option4");

                                  //   Toast.makeText(getApplicationContext()," ok "+question[i],Toast.LENGTH_LONG).show();

                                    product = new Products( questionid[i].toString(),question[i].toString(), op1[i].toString(), op2[i].toString(), op3[i].toString(), op4[i].toString());

                                   dbHandler.addProduct(product);
                                }
                            /* Products products=new Products("","","","","","");
                              dbHandler.addProduct(products);
*/
                           /*     ans1 = new String[jsonObject2.length()];
                                marks = new String[jsonObject2.length()];
                                timelimit = new String[jsonObject2.length()];*/

                                //    Toast.makeText(getApplicationContext()," ok "+jsonObject1,Toast.LENGTH_LONG).show();
                             /*   pollid = jsonObject2.getString("pollid");
                                String name = jsonObject2.getString("question");
                                String choice1 = jsonObject2.getString("option1");
                                String choice2 = jsonObject2.getString("option2");
                                String choice3 = jsonObject2.getString("option3");
                                String choice4 = jsonObject2.getString("option4");
                           */   //  String choice5 = jsonObject2.getString("option5");

                             //   for (int i = 0; i < jsonObject2.length(); i++) {
                                  /*  JSONObject result_jb1 = jsonObject2.getJSONObject(i);

                                    question[i] = jsonObject2.getString("question");
                                    op1[i] = jsonObject2.getString("op1");
                                    op2[i] = jsonObject2.getString("op2");
                                    op3[i] = jsonObject2.getString("op3");
                                    op4[i] = jsonObject2.getString("op4");
                              */     //    timelimit[i] = result_jb1.getString("time");

                            //        Products product = new Products( question[i].toString(), op1[i].toString(), op2[i].toString(), op3[i].toString(), op4[i].toString());

                             //       dbHandler.addProduct(product);


                               // }

                     //*          printDatabase();
                           /*     quizQuestion.setText("" + name);

                                option1Btn.setText("" + choice1);
                                option2Btn.setText("" + choice2);
                                option3Btn.setText("" + choice3);
                                option4Btn.setText("" + choice4);*/
                            //    option5Btn.setText("" + choice5);


                                System.out.print("ramu" + jsonObject1);
                                printDatabase();
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
       // Toast.makeText(OpinionPoll.this, "answer"+selectedoption.toString()+" pollid"+pollid.toString(), Toast.LENGTH_SHORT).show();

        System.out.println("answer"+selectedoption.toString()+" pollid"+pollid.toString());
        final ProgressDialog progressDialog=new ProgressDialog(OpinionPoll.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
   //  Toast.makeText(OpinionPoll.this,"Kkkkk",Toast.LENGTH_LONG).show();
        RequestQueue rq = Volley.newRequestQueue(OpinionPoll.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://cufest.com/index.php/welcomeapi/pollallresult",
                /* "http://carshuru.com/tst/grephor/register.php",*/
                new Response.Listener<String>() {

                    public void onResponse(String response1) {

                        try {
                            JSONObject  jsonObject = new JSONObject(response1);
                            String status=jsonObject.getString("status");
                            if(status.equals("success")){
                                 progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Sucessfully Submited", Toast.LENGTH_LONG).show();

                            }
                            else if(status.equals("already_given")) {
                               progressDialog.dismiss();
                                //    Toast.makeText(getApplicationContext()," Successfully Submited"+status,Toast.LENGTH_LONG).show();
                                Toast.makeText(getApplicationContext(), "Already Submited", Toast.LENGTH_LONG).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


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



                     params.put("empid", oempid);
                    params.put("answer", selectedoption.getText().toString());
                    params.put("pollid",pollid);

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

                Intent intent = new Intent(OpinionPoll.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



    public void printDatabase() {


        String dbString = dbHandler.databaseToStringTwo();

       //  Toast.makeText(OpinionPoll.this," c  "+dbString,Toast.LENGTH_LONG).show();
    //    System.out.print("eww" + dbString);

     array1 = dbString.split("\n");
        aa0=new String[array1.length];
        aa1 = new String[array1.length];
        aa2 = new String[array1.length];
        aa3 = new String[array1.length];
        aa4 = new String[array1.length];
        aa5 = new String[array1.length];

     //   Toast.makeText(OpinionPoll.this," array size  "+array1.length,Toast.LENGTH_LONG).show();

        for (int j = 0; j < array1.length; j++) {
            String va = array1[j];

            parts = va.split("-");

            aa0[j]=parts[0];
            aa1[j] = parts[1];
            aa2[j] = parts[2];
            aa3[j] = parts[3];
            aa4[j] = parts[4];
            aa5[j] = parts[5];

        }

      //  quizQuestion.setText(aa0[j]);
        //   quation.setText(sr);
        pollid=aa0[j];
//        Toast.makeText(OpinionPoll.this,"pollid  "+pollid,Toast.LENGTH_LONG).show();
        quizQuestion.setText(aa1[j]);
        option1Btn.setText(aa2[j]);
        option2Btn.setText(aa3[j]);
        option3Btn.setText(aa4[j]);
        option4Btn.setText(aa5[j]);
    }

    public void clickdata(View view){

        int selectedId = answerGroup.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        selectedoption = (RadioButton) findViewById(selectedId);


  //      Toast.makeText(OpinionPoll.this,"Answer  "+selectedoption.getText(), Toast.LENGTH_SHORT).show();



        quizSubmitfind();


        if(array1.length!=num){

            num=num+1;
            if(selectedoption.isChecked()){
                selectedoption.setChecked(false);
                //    selectedoption.setEnabled(true);

                selectedoption.setClickable(true);
            }
            selectedoption.setChecked(true);
            j=j+1;
            pollid=aa0[j];
            quizQuestion.setText(aa1[j]);
            option1Btn.setText(aa2[j]);
            option2Btn.setText(aa3[j]);
            option3Btn.setText(aa4[j]);
            option4Btn.setText(aa5[j]);
            //selectedoption.setChecked(false);



        }else {
            //     Toast.makeText(OpinionPoll.this,"J size"+j,Toast.LENGTH_LONG).show();
            quizSubmitfind();
            Intent intent=new Intent(OpinionPoll.this,MainActivity.class);
            startActivity(intent);

        }

    }

}
