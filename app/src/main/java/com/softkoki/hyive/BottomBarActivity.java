package com.softkoki.hyive;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.softkoki.hyive.fragment.EventStreamFragment;
import com.softkoki.hyive.fragment.MyProfileFragment;
import com.softkoki.hyive.fragment.NotifyPaymentFragment;
import com.softkoki.hyive.fragment.SignInUpFragment;
import com.softkoki.hyive.fragment.UpdatePasswordFragment;
import com.softkoki.hyive.pojo.ChangePassEvent;
import com.softkoki.hyive.pojo.Entry;
import com.softkoki.hyive.pojo.LogoutEvent;
import com.softkoki.hyive.pojo.UserDetails;

import rx.Observer;

public class BottomBarActivity extends AppCompatActivity {

    Fragment streamFragment;
    static int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_bar);
        setupBottomBar();
        setupBus();

    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d("onResume", String.valueOf(++count));
    }

    private void setupBus() {
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
                            //app.currentEntry = (Entry)object;
                            //addFragment(EventDetailsFragment.newInstance("", ""));
                        }else if(object instanceof UserDetails){
                            saveUserDetails((UserDetails)object);
                            addFragment(MyProfileFragment.newInstance("", ""));
                        }else if(object instanceof LogoutEvent){
                            saveUserDetails(null);
                            addFragment(SignInUpFragment.newInstance("", ""));

                        }else if(object instanceof ChangePassEvent){
                            addFragment(UpdatePasswordFragment.newInstance("", ""));

                        }
                    }
                    //
                });
    }

    private void setupBottomBar() {
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_stream) {
                    // The tab with id R.id.tab_favorites was selected,
                    // change your content accordingly.
                    Toast.makeText(BottomBarActivity.this, "Stream", Toast.LENGTH_SHORT).show();
                    Log.d("Tab:", "Stream");
                    if(streamFragment == null){
                        streamFragment = EventStreamFragment.newInstance("sdf", "dsf");
                    }
                    setMyTitle("Hyive");

                    addFragment(streamFragment);
                    //Toast.makeText(BottomBarActivity.this, "Lolwa", Toast.LENGTH_SHORT).show();
                }else if(tabId ==  R.id.tab_profile){
                    Log.d("Tab:", "Profile");

                    UserDetails ud = getUserDetails();
                    if(ud == null){
                        Toast.makeText(BottomBarActivity.this, "Sign In", Toast.LENGTH_SHORT).show();

                        addFragment(SignInUpFragment.newInstance("sd", "sdf"));
                        return;
                    }
                    Toast.makeText(BottomBarActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                    setMyTitle("Profile");
                    addFragment(MyProfileFragment.newInstance("sd", "sdf"));
                }else if(tabId == R.id.tab_wallet){
                    Log.d("Tab:", "Wallet");

                    UserDetails ud = getUserDetails();
                    if(ud == null){
                        Toast.makeText(BottomBarActivity.this, "Sign In", Toast.LENGTH_SHORT).show();

                        addFragment(SignInUpFragment.newInstance("sd", "sdf"));
                        return;
                    }
                    //Toast.makeText(BottomBarActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                    setMyTitle("Wallet");

                    addFragment(NotifyPaymentFragment.newInstance("sd", "sdf"));

                }
            }
        });
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

    public void addFragment(Fragment fragment){

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }


}
