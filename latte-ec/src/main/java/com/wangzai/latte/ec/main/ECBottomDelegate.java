package com.wangzai.latte.ec.main;

import android.graphics.Color;

import com.wangzai.latte.delegate.bottom.BaseBottomDelegate;
import com.wangzai.latte.delegate.bottom.BottomItemDelegate;
import com.wangzai.latte.delegate.bottom.BottomTabBean;
import com.wangzai.latte.delegate.bottom.ItemBuilder;
import com.wangzai.latte.ec.main.cart.ShopCartDelegate;
import com.wangzai.latte.ec.main.discover.DiscoverDelegate;
import com.wangzai.latte.ec.main.index.IndexDelegate;
import com.wangzai.latte.ec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * Created by wangzai on 2017/8/12
 */

public class ECBottomDelegate extends BaseBottomDelegate {
    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickColor() {
        return Color.parseColor("#ffff8800");
    }

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartDelegate());
        return builder.addItems(items).build();
    }
}
