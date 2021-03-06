package com.wangzai.latte.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.joanzapata.iconify.widget.IconTextView;
import com.wangzai.latte.delegate.bottom.BottomItemDelegate;
import com.wangzai.latte.ec.R;
import com.wangzai.latte.ec.R2;
import com.wangzai.latte.ui.recycler.BaseDecoration;
import com.wangzai.latte.ui.refresh.RefreshHandler;

import butterknife.BindView;

/**
 * Created by wangzai on 2017/8/12
 */

public class IndexDelegate extends BottomItemDelegate {

    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView = null;

    private RefreshHandler mRefreshHandler;

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRefreshHandler = RefreshHandler.create(mRefreshLayout,mRecyclerView,new IndexDataConvert());
//        RestClient.builder()
//                .url("http://127.0.0.1/index_data")
//                .success(new ISuccess() {
//                    @Override
//                    public void onSuccess(String response) {
//                        Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
//                        IndexDataConvert convert = new IndexDataConvert();
//                        convert.setJsonData(response);
//                        ArrayList<MultipleItemEntity> list = convert.convert();
//                        String imageUrl = list.get(1).getField(MultipleFields.IMAGE_URL);
//                        Toast.makeText(getContext(), imageUrl, Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .build()
//                .get();
    }

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration
                (BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 5));
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mRefreshHandler.firstPage("http://127.0.0.1/index_data");
    }
}

