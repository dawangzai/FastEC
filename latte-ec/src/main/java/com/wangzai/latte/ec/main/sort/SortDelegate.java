package com.wangzai.latte.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wangzai.latte.delegate.bottom.BottomItemDelegate;
import com.wangzai.latte.ec.R;

/**
 * Created by wangzai on 2017/8/12
 */

public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
