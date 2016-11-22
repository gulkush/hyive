package com.softkoki.hyive;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;

import com.softkoki.hyive.fragment.EventDetailsFragment;
import com.softkoki.hyive.fragment.UpdatePasswordFragment;

public class FragmentHolderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_holder);
        String fname = getIntent().getStringExtra("fname");
        setMyTitle(fname);
        if(fname.equalsIgnoreCase("Event Details")){
            addFragment(EventDetailsFragment.newInstance("sfd", "dsf"));
        }else if(fname.equalsIgnoreCase("Change Password")){
            addFragment(UpdatePasswordFragment.newInstance("sfd", "sf"));
        }
    }

    public void setMyTitle(String title){
        SpannableString s = new SpannableString(title);
        s.setSpan(new TypefaceSpan(this, "flipside.ttf"), 0, s.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Update the action bar title with the TypefaceSpan instance
        getSupportActionBar().setTitle(s);
    }

    public void addFragment(Fragment fragment){

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.activity_fragment_holder, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}
