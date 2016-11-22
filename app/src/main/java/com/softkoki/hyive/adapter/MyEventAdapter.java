package com.softkoki.hyive.adapter;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.softkoki.hyive.EventDetailsActivity;
import com.softkoki.hyive.GPSTracker;
import com.softkoki.hyive.MyApp;
import com.softkoki.hyive.R;
import com.softkoki.hyive.RxBus;
import com.softkoki.hyive.pojo.Entry;

import java.util.List;

/**
 * Created by gulkush on 8/14/2016.
 */
public class MyEventAdapter extends RecyclerView.Adapter<MyEventAdapter.MyViewHolder> {

    List<Entry> entries;
    Activity activity;
    GPSTracker gpsTracker;

    public MyEventAdapter(List<Entry> entries, Activity activity){
        this.activity = activity;
        this.entries = entries;
        gpsTracker = GPSTracker.getInstance(activity);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.myevent_layout, parent, false);
        MyViewHolder mvh = new MyViewHolder(view);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Entry entry = entries.get(position);
        Glide.with(activity).load(entry.getEventImage().getImage())
                .placeholder(R.drawable.hive_ph_600_400)
                .error(R.drawable.hive_ph_600_400)
                .into(holder.imageView);

        Location location = new Location("");
        location.setLatitude(Double.valueOf(entry.getLatitude()));
        location.setLongitude(Double.valueOf(entry.getLongitude()));
        if(gpsTracker != null && gpsTracker.getLocation()!= null ){
            float dist = (gpsTracker.getLocation().distanceTo(location)/1000);
            holder.tv_distance.setText(String.format("%.1f KM", dist));

        }else{
            holder.tv_distance.setText(String.format("%.1f KM", 0.0));

        }
        holder.tv_title.setText(Html.fromHtml(entry.getTitle()));
        holder.tv_address.setText(Html.fromHtml(entry.getAddressText()));
        holder.tv_time.setText(Html.fromHtml(entry.getTimeAvailable()));


    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView tv_title, tv_distance, tv_address, tv_time;


        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);
            tv_time = (TextView)itemView.findViewById(R.id.tv_time);
            tv_address = (TextView)itemView.findViewById(R.id.tv_address);
            tv_title = (TextView)itemView.findViewById(R.id.tv_title);
            tv_distance = (TextView)itemView.findViewById(R.id.tv_distance);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    MyApp.currentEntry = entries.get(getAdapterPosition());
                    //RxBus.getSingleton().send(entries.get(getAdapterPosition()));
                    activity.startActivity(new Intent(activity, EventDetailsActivity.class));

                }
            });

        }


    }
}
