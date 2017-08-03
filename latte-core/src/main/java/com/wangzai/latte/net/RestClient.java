package com.wangzai.latte.net;

import android.content.Context;

import com.wangzai.latte.net.callback.IError;
import com.wangzai.latte.net.callback.IFailure;
import com.wangzai.latte.net.callback.IRequest;
import com.wangzai.latte.net.callback.ISuccess;
import com.wangzai.latte.net.callback.RequestCallBack;
import com.wangzai.latte.ui.loader.LatteLoader;

import java.util.WeakHashMap;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by wangzai on 2017/8/2.
 */

public class RestClient {

    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final String URL;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final Context CONTEXT;

    public RestClient(String url,
                      WeakHashMap<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error, Context context) {
        URL = url;
        CONTEXT = context;
        PARAMS.putAll(params);
        REQUEST = request;
        SUCCESS = success;
        FAILURE = failure;
        ERROR = error;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(String httpMethod) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        if (CONTEXT != null) {
            LatteLoader.showLoading(CONTEXT);
        }

        switch (httpMethod) {
            case HttpMethod.GET:
                call = service.get(URL, PARAMS);
                break;
            case HttpMethod.POST:
                call = service.post(URL, PARAMS);
                break;
            case HttpMethod.PUT:
                call = service.put(URL, PARAMS);
                break;
            case HttpMethod.DELETE:
                call = service.delete(URL, PARAMS);
                break;
            default:
                break;
        }

        call.enqueue(getRequestCallBack());
    }

    private Callback<String> getRequestCallBack() {
        return new RequestCallBack(REQUEST, SUCCESS, FAILURE, ERROR, CONTEXT);
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }
}
