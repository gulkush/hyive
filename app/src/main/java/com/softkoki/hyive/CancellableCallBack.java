package com.softkoki.hyive;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by gulkush on 10/14/2015.
 */

public abstract class CancellableCallBack<T> implements Callback<T> {

    /*private static List<CancellableCallBack> mList = new ArrayList<>();

    private boolean isCanceled = false;
    private String mTag = null;

    public static void cancelAll() {
        for (CancellableCallBack callback : mList) {
            callback.cancel();
        }
    }

    public static void cancel(String tag) {
        if (tag != null)
            for (CancellableCallBack callback : mList) {
                if (tag.equals(callback.mTag))
                    callback.cancel();
            }
    }

    private CancellableCallBack() {
        mList.add(this);
    }

    public CancellableCallBack(String tag) {
        mTag = tag;
        mList.add(this);
    }

    public void cancel() {
        isCanceled = true;
        mList.remove(this);
    }

    @Override
    public final void success(T t, Response response) {
        if (!isCanceled)
            onSuccess(t, response);
        mList.remove(this);
    }

    @Override
    public final void failure(RetrofitError error) {
        if (!isCanceled)
            onFailure(error);
        mList.remove(this);
    }

    public abstract void onSuccess(T t, Response response);

    public abstract void onFailure(RetrofitError error);*/
}