package com.wangzai.latte.net.callback;

import android.content.Context;
import android.os.Handler;

import com.wangzai.latte.net.RestCreator;
import com.wangzai.latte.ui.loader.LatteLoader;
import com.wangzai.latte.ui.loader.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wangzai on 2017/8/3.
 */

public class RequestCallBack implements Callback<String> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final Context CONTEXT;
    private static final Handler HANDLER = new Handler(); // TODO: 2017/8/7 测试加载动画需要删除

    public RequestCallBack(IRequest request,
                           ISuccess success,
                           IFailure failure,
                           IError error,
                           Context context) {
        REQUEST = request;
        SUCCESS = success;
        FAILURE = failure;
        ERROR = error;
        CONTEXT = context;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }

        requestFinish();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }

        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }

        requestFinish();
    }

    private void requestFinish() {
        if (CONTEXT != null) {
//            RestCreator.getParams().clear();
//            LatteLoader.stopLoading();
            // TODO: 2017/8/7 测试加载动画需要删除
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    RestCreator.getParams().clear();
                    LatteLoader.stopLoading();
                }
            }, 3000);
        }
    }
}
