package com.wangzai.latte.delegate.bottom;

/**
 * Created by wangzai on 2017/8/11
 */

public final class BottomTabBean {

    private final CharSequence icon;
    private final CharSequence title;

    public BottomTabBean(CharSequence icon, CharSequence title) {
        this.icon = icon;
        this.title = title;
    }

    public CharSequence getIcon() {
        return icon;
    }

    public CharSequence getTitle() {
        return title;
    }
}
