package com.wangzai.latte.ui.loader;

import android.content.Context;
import android.widget.ProgressBar;

/**
 * Created by wangzai on 2017/8/3.
 */

public class LoaderCreator {

    static ProgressBar create(Context context) {
        return new ProgressBar(context);
    }
}
