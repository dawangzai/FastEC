package com.wangzai.fastec.example;

import com.wangzai.latte.activities.ProxyActivity;
import com.wangzai.latte.delegate.LatteDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
