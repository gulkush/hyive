package com.softkoki.hyive.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.google.gson.Gson;
import com.softkoki.hyive.BottomBarActivity;
import com.softkoki.hyive.MyDrawerActivity;
import com.softkoki.hyive.R;
import com.softkoki.hyive.RxBus;
import com.softkoki.hyive.pojo.LoginResponse;
import com.softkoki.hyive.pojo.RegistrationResponse;
import com.softkoki.hyive.pojo.UserDetails;
import com.softkoki.hyive.service.DataService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignInUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignInUpFragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ViewSwitcher viewSwitcher;
    TabLayout tabLayout;
    @Bind(R.id.et_email_login)EditText et_email_login;
    @Bind(R.id.et_pass_login)EditText et_pass_login;
    @Bind(R.id.bt_login)Button bt_login;
    @Bind(R.id.bt_register)Button bt_register;
    @Bind(R.id.et_email)EditText et_email;
    @Bind(R.id.et_fname)EditText et_fname;
    @Bind(R.id.et_lname)EditText et_lname;
    @Bind(R.id.et_mobile)EditText et_mobile;
    @Bind(R.id.rb_male)RadioButton rb_male;
    @Bind(R.id.et_pass)EditText et_pass;
    @Bind(R.id.et_pass2)EditText et_pass2;
    Subscription subscription;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public SignInUpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignInUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignInUpFragment newInstance(String param1, String param2) {
        SignInUpFragment fragment = new SignInUpFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in_up, container, false);
        ButterKnife.bind(this, view);
        //viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        //setupViewPager(viewPager);
        viewSwitcher = (ViewSwitcher)view.findViewById(R.id.viewswitcher);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);

        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Register"));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getText().toString().equalsIgnoreCase("Login")){
                    viewSwitcher.setDisplayedChild(0);
                }else{
                    viewSwitcher.setDisplayedChild(1);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        //((BottomBarActivity)getActivity()).setMyTitle("Hyive");
    }



    @OnClick(R.id.bt_register)
    public void validate_register() {


        String email = et_email.getText().toString();
        String pass = et_pass.getText().toString();
        String pass2 = et_pass2.getText().toString();
        String fname = et_fname.getText().toString();
        String lname = et_lname.getText().toString();
        String gender = (rb_male.isChecked())?"m":"f";
        String mobile = et_mobile.getText().toString();

        if(TextUtils.isEmpty(email) || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            my_request_focus(et_email);
            et_email.setError("Enter valid email.");
        }else if(TextUtils.isEmpty(fname)){
            my_request_focus(et_fname);
            et_fname.setError("Firstname cannot be blank.");
        }else if(TextUtils.isEmpty(lname)){
            my_request_focus(et_lname);
            et_lname.setError("Lastname cannot be blank.");
        }else if(TextUtils.isEmpty(pass)){
            my_request_focus(et_pass);
            et_pass.setError("Password cannot be blank.");
        }else if(!pass2.equalsIgnoreCase(pass)){
            my_request_focus(et_pass2);
            et_pass2.setError("Passwords do not match.");
        }else {
            //Toast.makeText(getActivity(), "Signing in...", Toast.LENGTH_SHORT).show();
            register(email, fname, lname, gender, mobile, pass);
        }

    }

    private void register(String email, String fname, String lname, String gender, String mobile, String pass) {

        bt_register.setText("Registering...");
        bt_register.setEnabled(false);
        Map<String, String> fields = new HashMap<>();
        fields.put("email", email);
        fields.put("first_name", fname);
        fields.put("last_name", lname);
        fields.put("mobile", mobile);
        fields.put("gender", gender);
        fields.put("password", pass);
        fields.put("dataport-key", getString(R.string.dataport_key));

        subscription = new DataService(getActivity()).getAPI().register(fields)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<RegistrationResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        bt_register.setText("Register");
                        bt_register.setEnabled(true);

                    }

                    @Override
                    public void onNext(RegistrationResponse registerResponse) {
                        bt_register.setText("Register");
                        bt_register.setEnabled(true);

                        String message = registerResponse.getMessage();
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                        switch (registerResponse.getCode()){
                            case 1:
                                et_email.setText("");
                                et_fname.setText("");
                                et_lname.setText("");
                                et_mobile.setText("");
                                et_pass2.setText("");
                                et_pass.setText("");
                                break;
                            case 2:
                                break;
                        }
                    }
                });

    }


    @OnClick(R.id.bt_login)
    public void validate_login() {
        String email = et_email_login.getText().toString();
        String pass = et_pass_login.getText().toString();
        if(TextUtils.isEmpty(email) || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            my_request_focus(et_email_login);
            et_email_login.setError("Enter valid email.");
        }else if(TextUtils.isEmpty(pass)){
            my_request_focus(et_pass_login);
            et_pass_login.setError("Password cannot be blank.");
        }else {
            Toast.makeText(getActivity(), "Signing in...", Toast.LENGTH_SHORT).show();
            login(email, pass);
        }
    }

    private void login(String email, String password){
        bt_login.setText("Logging In ...");
        Map<String, String> fields = new HashMap<>();
        fields.put("email", email);
        fields.put("password", password);
        fields.put("dataport-key", getString(R.string.dataport_key));

        subscription = new DataService(getActivity()).getAPI().login(fields)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<LoginResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        bt_login.setText("Login");

                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        bt_login.setText("Login");
                        String message = loginResponse.getMessage();
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                        switch (loginResponse.getCode()){
                            case 1:
                                RxBus.getSingleton().send(loginResponse.getUserDetails());

                                break;
                            case 2:
                                et_pass_login.setText("");
                                et_email_login.setText("");
                                break;
                        }
                    }
                });
    }



    private void my_request_focus(EditText et){
        et.requestFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(et, InputMethodManager.SHOW_IMPLICIT);
    }


}
