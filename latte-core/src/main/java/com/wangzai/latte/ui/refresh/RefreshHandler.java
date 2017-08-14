package com.wangzai.latte.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wangzai.latte.app.ConfigKeys;
import com.wangzai.latte.app.Latte;
import com.wangzai.latte.net.RestClient;
import com.wangzai.latte.net.callback.ISuccess;
import com.wangzai.latte.ui.recycler.DataConverter;
import com.wangzai.latte.ui.recycler.MultipleRecyclerAdapter;

/**
 * Created by wangzai on 2017/8/13
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {

    private final SwipeRefreshLayout mRefreshLayout;
    private final PagingBean mPagingBean;
    private final RecyclerView mRecyclerView;
    private MultipleRecyclerAdapter mAdapter = null;
    private final DataConverter mDataConverter;

    public RefreshHandler(SwipeRefreshLayout refreshLayout,
                          RecyclerView recyclerView,
                          DataConverter converter,
                          PagingBean bean) {

        this.mRefreshLayout = refreshLayout;
        this.mRecyclerView = recyclerView;
        this.mDataConverter = converter;
        this.mPagingBean = bean;

        mRefreshLayout.setOnRefreshListener(this);
    }

    public static RefreshHandler create(SwipeRefreshLayout refreshLayout,
                                        RecyclerView recyclerView,
                                        DataConverter converter) {
        return new RefreshHandler(refreshLayout, recyclerView, converter, new PagingBean());
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
                        final JSONObject object = JSON.parseObject(response);
                        mPagingBean.setTotal(object.getInteger("total"))
                                .setPageSize(object.getInteger("page_size"));
                        //设置Adapter
                        mAdapter = MultipleRecyclerAdapter.create(mDataConverter.setJsonData(response));
                        mAdapter.setOnLoadMoreListener(RefreshHandler.this, mRecyclerView);
                        mRecyclerView.setAdapter(mAdapter);
                        mPagingBean.addIndex();
                    }
                })
                .build()
                .get();
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    @Override
    public void onLoadMoreRequested() {

    }
}
