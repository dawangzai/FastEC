package com.wangzai.latte.ui.recycler;

import android.support.annotation.ColorRes;

import com.choices.divider.DividerItemDecoration;

/**
 * Created by wangzai on 2017/8/14
 */

public class BaseDecoration extends DividerItemDecoration {

    private BaseDecoration(@ColorRes int color, int size) {
        setDividerLookup(new DividerLookupImpl(color, size));
    }

    public static BaseDecoration create(@ColorRes int color, int size) {
        return new BaseDecoration(color, size);
    }
}
