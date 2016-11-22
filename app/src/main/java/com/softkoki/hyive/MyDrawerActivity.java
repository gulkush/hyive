package com.softkoki.hyive;

import android.Manifest;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.softkoki.hyive.fragment.EventDetailsFragment;
import com.softkoki.hyive.fragment.EventStreamFragment;
import com.softkoki.hyive.fragment.MyProfileFragment;
import com.softkoki.hyive.fragment.SignInUpFragment;
import com.softkoki.hyive.pojo.Entry;
import com.softkoki.hyive.pojo.LogoutEvent;
import com.softkoki.hyive.pojo.UserDetails;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscriber;

public class MyDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    MyApp app;
    //@Bind(R.id.ll_header) LinearLayout ll_header;
    @Bind(R.id.drawer_layout) DrawerLayout drawer;
    @Bind(R.id.fab) FloatingActionButton fab;
    @Bind(R.id.nav_view) NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_drawer);
        ButterKnife.bind(this);
        app = (MyApp)getApplication();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setMyTitle("Hyive");

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if(app.checkAskPermission(this, Manifest.permission.ACCESS_FINE_LOCATION, 1)){
            addFragment(EventStreamFragment.newInstance("", ""));
        }

        setupHeader();


        
        
        RxBus.getSingleton().asObservable()
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        //Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Object object) {
                        if(object instanceof Entry){
                            app.currentEntry = (Entry)object;
                            addFragment(EventDetailsFragment.newInstance("", ""));
                        }else if(object instanceof UserDetails){
                            saveUserDetails((UserDetails)object);
                            setupHeader();
                            addFragment(MyProfileFragment.newInstance("", ""));
                        }else if(object instanceof LogoutEvent){
                            saveUserDetails(null);
                            setupHeader();
                            addFragment(SignInUpFragment.newInstance("", ""));

                        }
                    }
                        //
                });


    }

    private void setupHeader() {
        View headerview = navigationView.getHeaderView(0);
        final TextView tv_name = (TextView)headerview.findViewById(R.id.tv_name);
        final TextView tv_email = (TextView)headerview.findViewById(R.id.tv_email);

        LinearLayout ll_header = (LinearLayout)headerview.findViewById(R.id.ll_header);
        ll_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(GravityCompat.START);
                if(tv_name.getText().toString().equalsIgnoreCase("Login/Register")){
                    addFragment(SignInUpFragment.newInstance("",""));

                }else{
                    addFragment(MyProfileFragment.newInstance("",""));

                }
            }
        });

        UserDetails ud = getUserDetails();
        if(ud != null){
            tv_email.setText(ud.getEmail());
            tv_name.setText(ud.getFirstName() + " " + ud.getLastName());
        }else{
            tv_name.setText("Login/Register");
            tv_email.setText("Hyive");

        }

    }

    public void addFragment(Fragment fragment){

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fl_container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_drawer, menu);
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
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            if(app.checkAskPermission(this, Manifest.permission.ACCESS_FINE_LOCATION, 1)){
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fl_container, EventStreamFragment.newInstance("", ""));
                ft.commit();
            }

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void setMyTitle(String title){
        SpannableString s = new SpannableString(title);
        s.setSpan(new TypefaceSpan(this, "flipside.ttf"), 0, s.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Update the action bar title with the TypefaceSpan instance
        getSupportActionBar().setTitle(s);
    }

    public void saveUserDetails(UserDetails userDetails) {

        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(userDetails);
        prefsEditor.putString("user_details", json);
        prefsEditor.commit();
    }

    public UserDetails getUserDetails(){
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        Gson gson = new Gson();
        String json = mPrefs.getString("user_details", "");
        UserDetails obj = gson.fromJson(json, UserDetails.class);
        return obj;
    }
}
