package com.wangzai.fastec.example;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Toast;

import com.wangzai.latte.activities.ProxyActivity;
import com.wangzai.latte.delegate.LatteDelegate;
import com.wangzai.latte.ec.launcher.ILauncherListener;
import com.wangzai.latte.ec.launcher.LauncherDelegate;
import com.wangzai.latte.ec.launcher.OnLauncherFinishTag;
import com.wangzai.latte.ec.main.ECBottomDelegate;
import com.wangzai.latte.ec.sign.ISignListener;

public class ExampleActivity extends ProxyActivity implements
        ISignListener,
        ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }

    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(this, "启动登录成功", Toast.LENGTH_SHORT).show();
                startWithPop(new ECBottomDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动没有登录", Toast.LENGTH_SHORT).show();
                startWithPop(new ECBottomDelegate());
                break;
            default:
                break;
        }
    }
}
