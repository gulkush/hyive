package com.softkoki.hyive.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.softkoki.hyive.MyApp;
import com.softkoki.hyive.MyDrawerActivity;
import com.softkoki.hyive.R;
import com.softkoki.hyive.adapter.MyEventAdapter;
import com.softkoki.hyive.pojo.Entry;
import com.softkoki.hyive.pojo.EventStreamResponse;
import com.softkoki.hyive.service.DataService;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventStreamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventStreamFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Subscription subscription;
    MyEventAdapter adapter;
    @Bind(R.id.pb_loading)ProgressBar pb_loading;
    @Bind(R.id.rv_events)RecyclerView rv_events;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private static final String TAG_EVENTS = "events";


    public EventStreamFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventStreamFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventStreamFragment newInstance(String param1, String param2) {
        EventStreamFragment fragment = new EventStreamFragment();
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
        View view = inflater.inflate(R.layout.fragment_event_stream, container, false);
        ButterKnife.bind(this, view);
        adapter = new MyEventAdapter(MyApp.entries, getActivity());
        rv_events.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv_events.setLayoutManager(manager);

        if(MyApp.entries.size() == 0){
            loadEvents();

        }


        return view;
    }

    private void loadEvents() {
        pb_loading.setVisibility(View.VISIBLE);
        Log.d("Fetching", "Events");
        subscription = new DataService(getContext()).getAPI()
                .getEventStream("10", "2", "RDqYbbBV3QwxH741yh7s5")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<EventStreamResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        pb_loading.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNext(EventStreamResponse esr) {
                        Toast.makeText(getActivity(), String.valueOf(esr.getEntries().size()), Toast.LENGTH_SHORT).show();
                        MyApp.entries.addAll(esr.getEntries());
                        adapter.notifyDataSetChanged();
                        pb_loading.setVisibility(View.GONE);
                    }
                });


    }



    @Override
    public void onResume(){
        super.onResume();
        //((MyDrawerActivity)getActivity()).setMyTitle("Events");
    }

    @Override
    public void onPause(){
        super.onPause();
        subscription.unsubscribe();
    }


}
