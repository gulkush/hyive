package com.softkoki.hyive.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.softkoki.hyive.R;
import com.softkoki.hyive.RxBus;
import com.softkoki.hyive.pojo.LoginResponse;
import com.softkoki.hyive.pojo.RegistrationResponse;
import com.softkoki.hyive.pojo.UserDetails;
import com.softkoki.hyive.service.DataService;

import java.util.ArrayList;
import java.util.HashMap;
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
 * Use the {@link NotifyPaymentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotifyPaymentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @Bind(R.id.sp_bank)Spinner sp_bank;
    @Bind(R.id.bt_notify)Button bt_notify;
    @Bind(R.id.et_teller)EditText et_teller;
    @Bind(R.id.et_date)EditText et_date;
    @Bind(R.id.et_amount)EditText et_amount;
    Subscription subscription;
    Map<String, String> mybankmap = new HashMap<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public NotifyPaymentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotifyPaymentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotifyPaymentFragment newInstance(String param1, String param2) {
        NotifyPaymentFragment fragment = new NotifyPaymentFragment();
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
        View view = inflater.inflate(R.layout.fragment_notify_payment, container, false);
        ButterKnife.bind(this, view);
        populateViews();
        return view;
    }

    private void populateViews() {
        loadBanks();
    }

    private void loadBanks() {


        subscription = new DataService(getActivity()).getAPI().getBanks()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Map<String, String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), "GetBanks:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Map<String, String> bankMap) {
                        mybankmap = bankMap;
                        ArrayAdapter adapter = new ArrayAdapter(getActivity(),
                                android.R.layout.simple_list_item_1,
                                new ArrayList(bankMap.values()));
                        sp_bank.setAdapter(adapter);
                    }
                });
    }

    @OnClick(R.id.bt_notify)
    public void notify_call(View view) {

        String amount = et_amount.getText().toString();
        String date = et_date.getText().toString();
        String teller = et_teller.getText().toString();
        if(TextUtils.isEmpty(amount) ){
            my_request_focus(et_amount);
            et_amount.setError("Amount cannot be blank.");
            return;
        }else if(TextUtils.isEmpty(teller)){
            my_request_focus(et_teller);
            et_teller.setError("Teller cannot be blank.");
            return;
        }else if(TextUtils.isEmpty(date)){
            my_request_focus(et_date);
            et_date.setError("Date cannot be blank.");
            return;
        }



        UserDetails ud = getUserDetails();

        bt_notify.setText("Submitting data ...");
        Map<String, String> fields = new HashMap<>();
        fields.put("bank", getSelectedBank());
        fields.put("amount", amount);
        fields.put("user_id", String.valueOf(ud.getUserId()));
        fields.put("teller", teller);
        fields.put("date_paid", date);
        fields.put("dataport-key", getString(R.string.dataport_key));

        subscription = new DataService(getActivity()).getAPI().notify_payment(fields)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<RegistrationResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), "NotifyPayment: "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                        bt_notify.setText("Notify Payment");

                    }

                    @Override
                    public void onNext(RegistrationResponse registrationResponse) {
                        bt_notify.setText("Notify Payment");
                        Toast.makeText(getActivity(), registrationResponse.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private String getSelectedBank() {
        String bank = "";
        String value = sp_bank.getSelectedItem().toString();
        for(String key : mybankmap.keySet()){
            if(mybankmap.get(key).equalsIgnoreCase(value)){
                return  key;
            }
        }
        return bank;
    }

    public UserDetails getUserDetails(){
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        Gson gson = new Gson();
        String json = mPrefs.getString("user_details", "");
        UserDetails obj = gson.fromJson(json, UserDetails.class);
        return obj;
    }

    private void my_request_focus(EditText et){
        et.requestFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(et, InputMethodManager.SHOW_IMPLICIT);
    }


}
