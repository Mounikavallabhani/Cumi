package com.arkainfotechpvt.cumi.activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Space;

import com.arkainfotechpvt.cumi.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Fragment fragment;
    Toolbar toolbar;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        toolbar.setTitle("Home");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        fragment=new Home();
        if(fragment!=null){

            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction ft=fragmentManager.beginTransaction();

            ft.add(R.id.f1,fragment);
            ft.commit();
        }


        sharedPreferences = getSharedPreferences("logindetails", Context.MODE_PRIVATE);
        String uname = sharedPreferences.getString("username", null);


        if(sharedPreferences!=null){


        }
        else {
            Intent it = new Intent(MainActivity.this, Login.class);
            startActivity(it);

        }


    }
    public  void selfe(View view ){
       startActivity(new Intent(MainActivity.this,PlayQuez.class));
    }
    public void quez(View view){
    startActivity(new Intent(MainActivity.this,SelfieActivity.class));
    }
    public void pole(View view){
     startActivity(new Intent(MainActivity.this,OpinionPoll.class));
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            Intent intent=new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
             startActivity(intent);
             finish();
             System.exit(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        Fragment fragment=null;
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        /*    Intent intent=new Intent(MainActivity.this,Home.class);
            startActivity(intent);
*/ toolbar.setTitle("Home");
           fragment=new Home();

        } else if (id == R.id.nav_about) {
            toolbar.setTitle("About");
          fragment =new About();

        } else if (id == R.id.nav_aboutchenni) {
            toolbar.setTitle("AboutChennai");
            fragment=new AboutChenni();
        } else if (id == R.id.nav_programe) {
            toolbar.setTitle("Programme");
            fragment=new Programme();
        } else if (id == R.id.nav_committee) {
           /* Intent intent=new Intent(MainActivity.this,Committee.class);
            startActivity(intent);*/
            toolbar.setTitle("Committee Members");
           fragment=new Committee();
        } else if (id == R.id.nav_admin) {
            Intent intent=new Intent(MainActivity.this,Admin.class);
            startActivity(intent);
        }else if (id == R.id.nav_activities) {
            Intent intent=new Intent(MainActivity.this,Activityes.class);
            startActivity(intent);
        }else if (id == R.id.nav_contacts) {
            toolbar.setTitle("Contact Us");
            fragment=new AccountUs();
        }else if (id == R.id.nav_logout) {


            Intent intent = new Intent(MainActivity.this, Login.class);
            getApplicationContext().getSharedPreferences("logindetails", 0).edit().clear().commit();


            startActivity(intent);


        }
        if(fragment!=null){

            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction ft=fragmentManager.beginTransaction();

            ft.replace(R.id.f1,fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
