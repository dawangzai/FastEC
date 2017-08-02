package com.wangzai.fastec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wangzai.latte.delegate.LatteDelegate;
import com.wangzai.latte.net.RestClient;
import com.wangzai.latte.net.callback.IError;
import com.wangzai.latte.net.callback.IFailure;
import com.wangzai.latte.net.callback.ISuccess;

/**
 * Created by wangzai on 2017/8/1.
 */

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    private void testRestClient(){
        RestClient.builder()
                .url("")
                .params("","")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build();
    }
}
