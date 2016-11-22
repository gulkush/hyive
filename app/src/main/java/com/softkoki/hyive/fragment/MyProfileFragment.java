package com.softkoki.hyive.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.gson.Gson;
import com.softkoki.hyive.MyDrawerActivity;
import com.softkoki.hyive.R;
import com.softkoki.hyive.RxBus;
import com.softkoki.hyive.pojo.LogoutEvent;
import com.softkoki.hyive.pojo.UserDetails;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @Bind(R.id.et_email)EditText et_email;
    @Bind(R.id.et_fname)EditText et_fname;
    @Bind(R.id.et_lname)EditText et_lname;
    @Bind(R.id.et_mobile)EditText et_mobile;
    @Bind(R.id.rb_male)RadioButton rb_male;
    @Bind(R.id.rb_female)RadioButton rb_female;


    public MyProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyProfileFragment newInstance(String param1, String param2) {
        MyProfileFragment fragment = new MyProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_profile, container, false);
        ButterKnife.bind(this, view);
        populateView();
        return view;
    }

    private void populateView() {

        UserDetails ud = getUserDetails();
        et_email.setText(ud.getEmail());
        et_fname.setText(ud.getFirstName());
        et_lname.setText(ud.getLastName());
        et_mobile.setText(ud.getMobile());
        if(ud.getGender().toLowerCase().charAt(0) == 'm'){
            rb_male.setChecked(true);
        }else{
            rb_female.setChecked(true);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        //((MyDrawerActivity)getActivity()).setMyTitle("Profile");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        menu.clear();
        inflater.inflate(R.menu.logout_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_logout:
                // Do Activity menu item stuff here
                RxBus.getSingleton().send(new LogoutEvent());
                return true;
            default:
                break;
        }

        return false;
    }

    public UserDetails getUserDetails(){
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        Gson gson = new Gson();
        String json = mPrefs.getString("user_details", "");
        UserDetails obj = gson.fromJson(json, UserDetails.class);
        return obj;
    }

}
