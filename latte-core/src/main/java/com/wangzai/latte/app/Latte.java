package com.wangzai.latte.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by wangzai on 2017/8/1.
 */

public final class Latte {
    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getLatteConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT,context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator(){
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key){
        return getConfigurator().getConfiguration(key);
    }

//    public static HashMap<Object, Object> getConfigurations() {
//        return Configurator.getInstance().getLatteConfigs();
//    }

    public static Context getApplicationContext() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }
}
