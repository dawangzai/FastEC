package com.wangzai.latte.net;

import com.wangzai.latte.net.callback.IError;
import com.wangzai.latte.net.callback.IFailure;
import com.wangzai.latte.net.callback.IRequest;
import com.wangzai.latte.net.callback.ISuccess;

import java.util.WeakHashMap;

/**
 * Created by wangzai on 2017/8/2.
 */

public class RestClient {
    private final String URL;
    private final WeakHashMap<String, Object> PARAMS;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;

    public RestClient(String url,
                      WeakHashMap<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error) {
        URL = url;
        PARAMS = params;
        REQUEST = request;
        SUCCESS = success;
        FAILURE = failure;
        ERROR = error;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }
}
