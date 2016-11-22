package com.softkoki.hyive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.softkoki.hyive.pojo.Entry;
import com.softkoki.hyive.pojo.EventStreamResponse;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    MyApp app;
    private static String TAG_MAIN = "main";
    @Bind(R.id.pb_loading)ProgressBar pb_loading;
    @Bind(R.id.tv_response)TextView tv_response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        app = (MyApp)getApplication();
        //getData();

    }

    /*private void getData() {

        WebService service = app.getRestAdapter(this, false).create(WebService.class);
        service.getEventStream("10", "2", "RDqYbbBV3QwxH741yh7s5", new CancellableCallBack<EventStreamResponse>(TAG_MAIN) {
            @Override
            public void onSuccess(EventStreamResponse eventStreamResponse, Response response) {

                String body = "";
                for(Entry entry : eventStreamResponse.getEntries()){
                    body += entry.getBody();
                }

                tv_response.setText(body);
                pb_loading.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(RetrofitError error) {
                tv_response.setText(error.getMessage());
                pb_loading.setVisibility(View.GONE);

            }
        });
    }*/
}
