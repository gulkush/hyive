package com.softkoki.hyive.service;


import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;


import com.softkoki.hyive.CancellableCallBack;
import com.softkoki.hyive.pojo.EventStreamResponse;
import com.softkoki.hyive.pojo.LoginResponse;
import com.softkoki.hyive.pojo.RegistrationResponse;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by gulkush on 9/24/2016.
 */

public class DataService {

    private static String baseUrl = "http://hyive.com/dataport_v1/";
    private DataAPI dataAPI;
    private static Context context;
    private static final int cacheSize = 10 * 1024 * 1024; // 10 MiB

    public DataService(Context context) {
        this(baseUrl, context);
    }

    public DataService(String baseUrl, Context context) {
        this.context = context;

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Create Cache
        Cache cache = null;
        cache = new Cache(new File(context.getCacheDir(), "myhyive"), cacheSize);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.cache(cache);
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.addNetworkInterceptor(mCacheControlInterceptor);
        builder.addInterceptor(loggingInterceptor);






        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();

        dataAPI = retrofit.create(DataAPI.class);
    }

    public DataAPI getAPI() {
        return dataAPI;
    }

    public interface DataAPI {



        @FormUrlEncoded
        @Headers("Authorization: Basic MTIzNDphYWJiY2NkZA==")
        @POST("eventstream")
        Observable<EventStreamResponse> getEventStream(@Field("limit") String limit, @Field("offset") String offset, @Field("dataport-key") String key);

        @FormUrlEncoded
        @POST("userlogin")
        //Observable<LoginResponse> login(@Field("email") String email, @Field("password") String password, @Field("dataport-key") String key);
        Observable<LoginResponse> login(@FieldMap Map<String, String> fields);


        @FormUrlEncoded
        @POST("user_registration")
        Observable<RegistrationResponse> register(@FieldMap Map<String, String> fields);


        @GET("banks")
        @Headers("dataport-key: RDqYbbBV3QwxH741yh7s5")
        Observable<Map<String, String>> getBanks();

        @FormUrlEncoded
        @POST("notify_payment")
        Observable<RegistrationResponse> notify_payment(@FieldMap Map<String, String> fields);


    }



   /* //cache
    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            if (isConnected()) {
                int maxAge = 60; // read from cache for 1 minute
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Pragma")
                        .removeHeader("Expires")
                        .removeHeader("Access-Control-Allow-Origin")
                        .removeHeader("Vary")
                        .removeHeader("Age")
                        .removeHeader("Via")
                        .removeHeader("C3-Request")
                        .removeHeader("C3-Domain")
                        .removeHeader("C3-Date")
                        .removeHeader("C3-Hostname")
                        .removeHeader("C3-Cache-Control")
                        .removeHeader("X-Varnish-back")
                        .removeHeader("X-Varnish")
                        .removeHeader("X-Cache")
                        .removeHeader("X-Cache-Hits")
                        .removeHeader("X-Varnish-front")
                        .removeHeader("Connection")
                        .removeHeader("Accept-Ranges")
                        .removeHeader("Transfer-Encoding")
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .removeHeader("Expires")
                        .removeHeader("Access-Control-Allow-Origin")
                        .removeHeader("Vary")
                        .removeHeader("Age")
                        .removeHeader("Via")
                        .removeHeader("C3-Request")
                        .removeHeader("C3-Domain")
                        .removeHeader("C3-Date")
                        .removeHeader("C3-Hostname")
                        .removeHeader("C3-Cache-Control")
                        .removeHeader("X-Varnish-back")
                        .removeHeader("X-Varnish")
                        .removeHeader("X-Cache")
                        .removeHeader("X-Cache-Hits")
                        .removeHeader("X-Varnish-front")
                        .removeHeader("Connection")
                        .removeHeader("Accept-Ranges")
                        .removeHeader("Transfer-Encoding")
                        .build();
            }
        }
    };
*/
    public static boolean isConnected() {

        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        Log.d("isConnected", String.valueOf(isConnected));
        if(isConnected ==false){
            Toast.makeText(context, "You're offline!",
                    Toast.LENGTH_SHORT).show();
        }
        return isConnected;
    }

    private static final Interceptor mCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            // Add Cache Control only for GET methods
            if (request.method().equals("GET")) {
                if (isConnected()) {
                    // 1 day
                    request.newBuilder()
                            .header("Cache-Control", "only-if-cached")
                            .build();
                } else {
                    // 4 weeks stale
                    request.newBuilder()
                            .header("Cache-Control", "public, max-stale=2419200")
                            .build();
                }
            }

            Response response = chain.proceed(request);

            // Re-write response CC header to force use of cache
            return response.newBuilder()
                    .header("Cache-Control", "public, max-age=3600")
                    .removeHeader("Pragma")
                    .removeHeader("Expires")// 1 hour
                    .build();
        }
    };

}
