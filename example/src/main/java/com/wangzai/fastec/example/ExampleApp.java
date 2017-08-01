package com.wangzai.fastec.example;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.wangzai.latte.app.Latte;

/**
 * Created by wangzai on 2017/8/1.
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withApiHost("")
                .configure();
    }
}
