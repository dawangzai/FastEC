package com.wangzai.latte.ec.main.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wangzai.latte.delegate.bottom.BottomItemDelegate;
import com.wangzai.latte.ec.R;

/**
 * Created by wangzai on 2017/8/12
 */

public class ShopCartDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
