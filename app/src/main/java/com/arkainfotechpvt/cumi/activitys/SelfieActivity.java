package com.arkainfotechpvt.cumi.activitys;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.arkainfotechpvt.cumi.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SelfieActivity extends AppCompatActivity {
    ImageView iv1;
    Bitmap bit = null;



    String choice[] = {"CAMERA", "GALLERY"};
    public static final int CAM_REQ_CODE = 123;
    public static final int GAL_REQ_CODE = 321;

    Toolbar toolbar;

    public static final int CAM_PERMISSION_ACCESS_CODE = 111;
    public static final String CAM_PERMISSION_NAME[] = {android.Manifest.permission.CAMERA};
    public static final int GAL_PERMISSION_ACCESS_CODE = 222;
    public static final String GAL_PERMISSION_NAME[] = {android.Manifest.permission.READ_EXTERNAL_STORAGE};
    SharedPreferences sharedPreferences;
    String srid,sremid,sgivenname;
    String img_str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selfie);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle("Selfie");
        sharedPreferences=getSharedPreferences("logindetails",Context.MODE_PRIVATE);
        sremid=sharedPreferences.getString("username",null);
        sgivenname=sharedPreferences.getString("password",null);


        iv1=findViewById(R.id.image_view);



    }




    public void setimage(View v) {

//        AlertDialog.Builder adb = new AlertDialog.Builder(this);
//        adb.setIcon(R.drawable.user);
//        adb.setTitle(" Select One ");
//        adb.setItems(choice, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                switch (i) {
//                    case 0:
        int res = ContextCompat.checkSelfPermission(SelfieActivity.this, android.Manifest.permission.CAMERA);
        if (res == PackageManager.PERMISSION_GRANTED) {
            Intent cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cam, CAM_REQ_CODE);
        } else {
            ActivityCompat.requestPermissions(SelfieActivity.this, CAM_PERMISSION_NAME, CAM_PERMISSION_ACCESS_CODE);
        }
//                        break;
//                    case 1:
//                        int res1 = ContextCompat.checkSelfPermission(Selfy.this, android.Manifest.permission.READ_EXTERNAL_STORAGE);
//
//                        if (res1 == PackageManager.PERMISSION_GRANTED) {
//                            Intent gal = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                            startActivityForResult(gal, GAL_REQ_CODE);
//                        } else {
//                            ActivityCompat.requestPermissions(Selfy.this, GAL_PERMISSION_NAME, GAL_PERMISSION_ACCESS_CODE);
//                        }
//
//                        break;
//                }
//            }
//        });
//        adb.create().show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAM_PERMISSION_ACCESS_CODE:
                if (CAM_PERMISSION_NAME.equals(permissions[0]) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cam, CAM_REQ_CODE);
                }
                break;

//            case GAL_PERMISSION_ACCESS_CODE:
//                if (GAL_PERMISSION_NAME.equals(permissions[0]) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Intent gal = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    startActivityForResult(gal, GAL_REQ_CODE);
//                }
//                break;
        }
    }
  /*  @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                Bitmap lastBitmap = null;
                lastBitmap = bitmap;
                //encoding image to string
                String image = getStringImage(lastBitmap);
                Log.d("image",image);
                //passing the image to volley
                SendImage(image);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;

    }*/
   @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);





        switch (requestCode) {
            case CAM_REQ_CODE:
                if (resultCode == RESULT_OK) {
                    Bundle b = intent.getExtras();
                    bit = (Bitmap) b.get("data");
                    iv1.setImageBitmap(bit);
                }
                break;

//            case GAL_REQ_CODE:
//                if (resultCode == RESULT_OK) {
//                    Uri img = intent.getData();
//                    try {
//                        bit = MediaStore.Images.Media.getBitmap(this.getContentResolver(), img);
//
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    iv1.setImageBitmap(bit);
//                }
//                break;
//

        }

        iv1.buildDrawingCache();

   //*   Bitmap bitmap = ImageLoader.init().from(iv1).requestSize(512, 512).getBitmap();
     //   ivImage.setImageBitmap(bitmap);
//*
    //    Bitmap bitmap = ImageUtils.getInstant().getCompressedBitmap("Your_Image_Path_Here");
       // imageView.setImageBitmap(bitmap);

        Bitmap bitmap = iv1.getDrawingCache();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
       bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        bitmap= Bitmap.createScaledBitmap(bitmap, 256, 256, false);

        byte[] image=stream.toByteArray();
       // Toast.makeText("")
        img_str = Base64.encodeToString(image,Base64.DEFAULT);
       // System.out.print("ByteArry"+img_str);
        //decode string to image
       // String base=img_str;
       // byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
    }

    public  void camera(View view ){
        Registration();
      //  Toast.makeText(SelfieActivity.this,"Thanks For Uploading Your Selfe ",Toast.LENGTH_LONG).show();



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(SelfieActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



    protected void Registration() {

        RequestQueue rq = Volley.newRequestQueue(SelfieActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://cufest.com/index.php/welcomeapi/selfie",
                /* "http://carshuru.com/tst/grephor/register.php",*/
                new Response.Listener<String>() {

                    public void onResponse(String response) {

//                           Toast.makeText(getApplicationContext(),""+response,Toast.LENGTH_LONG).show();
                        try {
                            JSONObject jsonObject1=new JSONObject(response);
                            String status=jsonObject1.getString("status");

                           //  Toast.makeText(getApplicationContext(),"image   "+status,Toast.LENGTH_LONG).show();
                         /*   if(img_str.r){


                            }*/
                       //  if(iv1.setImageResource(R.drawable.placeholder_small)==iv1.setImageResource(R.drawable.placeholder_small);) {
                             toastimage();

                    //     }
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

                params.put("empid",sremid);
                params.put("image",img_str);
                params.put("givenname",sgivenname);


                return params;
            }
        };
        rq.add(stringRequest);
    }

    public void toastimage(){
        LayoutInflater li = getLayoutInflater();
        //Getting the View object as defined in the customtoast.xml file
        View layout = li.inflate(R.layout.toast,(ViewGroup) findViewById(R.id.toast_layout_root));

        //Creating the Toast object
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setView(layout);//setting the view of custom toast layout
        toast.show();
        iv1.setImageResource(R.drawable.placeholder_small);

    }
}
