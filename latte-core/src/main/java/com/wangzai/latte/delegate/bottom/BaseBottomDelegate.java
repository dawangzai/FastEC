package com.wangzai.latte.delegate.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.joanzapata.iconify.widget.IconTextView;
import com.wangzai.latte.R;
import com.wangzai.latte.R2;
import com.wangzai.latte.delegate.LatteDelegate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by wangzai on 2017/8/11
 */

public abstract class BaseBottomDelegate extends LatteDelegate implements View.OnClickListener {

    private final List<BottomTabBean> tabBeens = new ArrayList<>();
    private final List<BottomItemDelegate> itemDelegates = new ArrayList<>();
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();

    private int mCurrentDelegate = 0;
    private int mIndexDelegate = 0;
    private int mClickColor = Color.RED;

    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottomBar = null;

    public abstract int setIndexDelegate();

    @ColorInt
    public abstract int setClickColor();

    public abstract LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mIndexDelegate = setIndexDelegate();

        if (setClickColor() != 0) {
            mClickColor = setClickColor();
        }

        final ItemBuilder builder = ItemBuilder.builder();
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> itemsTemp = setItems(builder);
        items.putAll(itemsTemp);
        for (Map.Entry<BottomTabBean, BottomItemDelegate> item : items.entrySet()) {
            final BottomTabBean key = item.getKey();
            final BottomItemDelegate value = item.getValue();
            tabBeens.add(key);
            itemDelegates.add(value);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final int size = items.size();
        for (int i = 0; i < size; i++) {
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout, mBottomBar, false);
            RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);

            item.setOnClickListener(this);

            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            BottomTabBean bean = tabBeens.get(i);
            itemIcon.setText(bean.getIcon());
            itemTitle.setText(bean.getTitle());
            if (i == mIndexDelegate) {
                itemIcon.setTextColor(mClickColor);
                itemTitle.setTextColor(mClickColor);
            }
        }
    }

    @Override
    public void onClick(View v) {

    }
}
