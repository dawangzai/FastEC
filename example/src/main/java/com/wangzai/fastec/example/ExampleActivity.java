package com.wangzai.fastec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.wangzai.latte.activities.ProxyActivity;
import com.wangzai.latte.delegate.LatteDelegate;
import com.wangzai.latte.ec.launcher.LauncherDelegate;
import com.wangzai.latte.ec.launcher.LauncherScrollDelegate;
import com.wangzai.latte.ec.sign.SignUpDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
    }

    @Override
    public LatteDelegate setRootDelegate() {
//        return new LauncherDelegate();
        return new SignUpDelegate();
    }
}
