package com.arkainfotechpvt.cumi.activitys;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.arkainfotechpvt.cumi.R;
import com.github.barteksc.pdfviewer.PDFView;

import android.support.v7.widget.Toolbar;
public class Admin extends AppCompatActivity {

   // PDFView pdfView;
    LinearLayout contaent_liner,contaent_liner1,contaent_liner2,contaent_liner3,contaent_liner4,contaent_liner5,contaent_liner6;
     Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_admin);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle("Admin Brief");

        contaent_liner=(LinearLayout)findViewById(R.id.contaent_liner);
        contaent_liner1=(LinearLayout)findViewById(R.id.contaent_liner1);
        contaent_liner2=(LinearLayout)findViewById(R.id.contaent_liner2);
        contaent_liner3=(LinearLayout)findViewById(R.id.contaent_liner3);

        contaent_liner4=(LinearLayout)findViewById(R.id.contaent_liner4);
        contaent_liner5=(LinearLayout)findViewById(R.id.contaent_liner5);
        contaent_liner6=(LinearLayout)findViewById(R.id.contaent_liner6);

        contaent_liner1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin.this,Sheettwo.class);
                        startActivity(intent);
            }
        });
        contaent_liner2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin.this,SheetThree.class);
                startActivity(intent);
            }
        });
        contaent_liner3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin.this,SheetFour.class);
                startActivity(intent);
            }
        });
        contaent_liner4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin.this,SheetFive.class);
                startActivity(intent);
            }
        });
        contaent_liner5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin.this,SheetSix.class);
                startActivity(intent);
            }
        });
        contaent_liner6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin.this,SheetSeven.class);
                startActivity(intent);
            }
        });
        contaent_liner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Admin.this,Sheetoneone.class);
                startActivity(intent);
            }
        });




   //     return view ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(Admin.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(Admin.this,MainActivity.class);
           startActivity(intent);
    }
}
