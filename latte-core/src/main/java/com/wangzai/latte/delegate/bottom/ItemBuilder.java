package com.wangzai.latte.delegate.bottom;

import java.util.LinkedHashMap;

/**
 * Created by wangzai on 2017/8/11
 */

public final class ItemBuilder {

    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();

    public final ItemBuilder addItem(BottomTabBean bean, BottomItemDelegate delegate) {
        this.items.put(bean, delegate);
        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean, BottomItemDelegate> items) {
        this.items.putAll(items);
        return this;
    }

    static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public final LinkedHashMap<BottomTabBean, BottomItemDelegate> build() {
        return items;
    }
}
