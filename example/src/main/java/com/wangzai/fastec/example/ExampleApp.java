package com.wangzai.fastec.example;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.wangzai.latte.app.Latte;
import com.wangzai.latte.ec.database.DatabaseManager;
import com.wangzai.latte.net.interceptors.DebugInterceptor;

/**
 * Created by wangzai on 2017/8/1.
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
//        http://127.0.0.1/
                .withApiHost("http://127.0.0.1/")
//                .withApiHost("http://192.168.1.100:8080/RestServer/api/")
                .withInterceptor(new DebugInterceptor("index_data", R.raw.index_data))
                .configure();

        initStetho();
        DatabaseManager.getInstance().init(this);
    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
