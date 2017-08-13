package com.wangzai.latte.ec.main.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wangzai.latte.delegate.bottom.BottomItemDelegate;
import com.wangzai.latte.ec.R;

/**
 * Created by wangzai on 2017/8/12
 */

public class DiscoverDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
