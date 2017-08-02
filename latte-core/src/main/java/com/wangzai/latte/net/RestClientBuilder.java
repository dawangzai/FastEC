package com.wangzai.latte.net;

import com.wangzai.latte.net.callback.IError;
import com.wangzai.latte.net.callback.IFailure;
import com.wangzai.latte.net.callback.IRequest;
import com.wangzai.latte.net.callback.ISuccess;

import java.util.WeakHashMap;

/**
 * Created by wangzai on 2017/8/2.
 */

public class RestClientBuilder {
    private String mUrl;
    private WeakHashMap<String, Object> mParams;
    private IRequest mIRequest;
    private ISuccess mISuccess;
    private IFailure mIFailure;
    private IError mIError;

    RestClientBuilder() {
    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        this.mParams = params;
        return this;
    }

    public final RestClientBuilder params(String key, String value) {
        if (mParams == null) {
            mParams = new WeakHashMap<>();
        }
        mParams.put(key, value);
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    public final RestClient build() {
        return new RestClient(mUrl,mParams,mIRequest,mISuccess,mIFailure,mIError);
    }
}
