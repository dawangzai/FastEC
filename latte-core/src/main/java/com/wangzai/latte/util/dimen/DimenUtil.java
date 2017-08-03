package com.wangzai.latte.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.wangzai.latte.app.Latte;

/**
 * Created by wangzai on 2017/8/3.
 */

public final class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
