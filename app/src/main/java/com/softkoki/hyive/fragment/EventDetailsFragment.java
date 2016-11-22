package com.softkoki.hyive.fragment;

import android.content.Context;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.softkoki.hyive.GPSTracker;
import com.softkoki.hyive.MyApp;
import com.softkoki.hyive.MyDrawerActivity;
import com.softkoki.hyive.R;
import com.softkoki.hyive.RxBus;
import com.softkoki.hyive.pojo.Entry;

import butterknife.Bind;
import butterknife.ButterKnife;


public class EventDetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @Bind(R.id.iv_details)ImageView iv_details;
    @Bind(R.id.tv_time)TextView tv_time;
    @Bind(R.id.tv_title)TextView tv_title;
    @Bind(R.id.tv_description)TextView tv_description;
    @Bind(R.id.tv_distance)TextView tv_distance;
    @Bind(R.id.tv_address)TextView tv_address;
    MyApp app;
    GPSTracker gpsTracker;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public EventDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventDetailsFragment newInstance(String param1, String param2) {
        EventDetailsFragment fragment = new EventDetailsFragment();
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
        View view = inflater.inflate(R.layout.fragment_event_details, container, false);
        ButterKnife.bind(this, view);
        gpsTracker = GPSTracker.getInstance(getActivity());

        Entry entry = app.currentEntry;
        Glide.with(getActivity()).load(entry.getEventImage().getImage())
                .placeholder(R.drawable.hive_ph_600_400)
                .error(R.drawable.hive_ph_600_400)
                .into(iv_details);


        Location location = new Location("");
        location.setLatitude(Double.valueOf(entry.getLatitude()));
        location.setLongitude(Double.valueOf(entry.getLongitude()));
        if(gpsTracker != null && gpsTracker.getLocation()!= null ){
            float dist = (gpsTracker.getLocation().distanceTo(location)/1000);
            tv_distance.setText(String.format("%.1f KM", dist));

        }else{
            tv_distance.setText(String.format("%.1f KM", 0.0));

        }
        tv_title.setText(Html.fromHtml(entry.getTitle()));
        tv_address.setText(Html.fromHtml(entry.getAddressText()));
        tv_time.setText(Html.fromHtml(entry.getTimeAvailable()));
        tv_description.setText(Html.fromHtml(entry.getBody()));

        return view;
    }



    @Override
    public void onResume(){
        super.onResume();
        ((MyDrawerActivity)getActivity()).setMyTitle("Event Details");
    }






}
