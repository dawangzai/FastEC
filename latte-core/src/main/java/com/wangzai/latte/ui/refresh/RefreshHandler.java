package com.wangzai.latte.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.wangzai.latte.app.ConfigKeys;
import com.wangzai.latte.app.Latte;
import com.wangzai.latte.net.RestClient;
import com.wangzai.latte.net.callback.ISuccess;

/**
 * Created by wangzai on 2017/8/13
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {

    private final SwipeRefreshLayout mRefreshLayout;

    public RefreshHandler(SwipeRefreshLayout refreshLayout) {

        this.mRefreshLayout = refreshLayout;

        mRefreshLayout.setOnRefreshListener(this);
    }

    private void refresh() {
        mRefreshLayout.setRefreshing(true);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }

    public void firstPage(String url) {
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(Latte.getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .get();
    }

    @Override
    public void onRefresh() {
        refresh();
    }
}
