package com.softkoki.hyive;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.softkoki.hyive.pojo.Entry;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EventDetailsActivity extends AppCompatActivity {

    @Bind(R.id.iv_details)ImageView iv_details;
    @Bind(R.id.tv_time)TextView tv_time;
    @Bind(R.id.tv_title)TextView tv_title;
    @Bind(R.id.tv_description)TextView tv_description;
    @Bind(R.id.tv_distance)TextView tv_distance;
    @Bind(R.id.tv_address)TextView tv_address;
    MyApp app;
    GPSTracker gpsTracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        ButterKnife.bind(this);
        app = (MyApp)getApplication();
        SpannableString s = new SpannableString("Event Details");
        s.setSpan(new TypefaceSpan(this, "flipside.ttf"), 0, s.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Update the action bar title with the TypefaceSpan instance
        getSupportActionBar().setTitle(s);

        gpsTracker = GPSTracker.getInstance(this);
        Entry entry = app.currentEntry;
        Glide.with(this).load(entry.getEventImage().getImage())
                .placeholder(R.drawable.hive_ph_600_400)
                .error(R.drawable.hive_ph_600_400)
                .into(iv_details);
        Location location = new Location("");
        location.setLatitude(Double.valueOf(entry.getLatitude()));
        location.setLongitude(Double.valueOf(entry.getLongitude()));
        float dist = (gpsTracker.getLocation().distanceTo(location)/1000);
        tv_distance.setText(String.format("%.1f KM", dist));
        tv_title.setText(Html.fromHtml(entry.getTitle()));
        tv_address.setText(Html.fromHtml(entry.getAddressText()));
        tv_time.setText(Html.fromHtml(entry.getTimeAvailable()));
        tv_description.setText(Html.fromHtml(entry.getBody()));


    }
}
