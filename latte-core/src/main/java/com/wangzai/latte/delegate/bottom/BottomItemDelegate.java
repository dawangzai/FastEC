package com.wangzai.latte.delegate.bottom;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.wangzai.latte.R;
import com.wangzai.latte.delegate.LatteDelegate;

/**
 * Created by wangzai on 2017/8/11
 */

public abstract class BottomItemDelegate extends LatteDelegate implements View.OnKeyListener {

    private long mExitTime = 0;
    private static final long EXIT_TIME = 2000L;

    @Override
    public void onResume() {
        super.onResume();

        View rootView = getView();
        if (rootView != null) {
            rootView.setFocusableInTouchMode(true);
            rootView.setFocusable(true);
            rootView.setOnKeyListener(this);
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - mExitTime > EXIT_TIME) {
                mExitTime = System.currentTimeMillis();
                Toast.makeText(getContext(), "双击退出" + getString(R.string.app_name), Toast.LENGTH_SHORT).show();
            } else {
                _mActivity.finish();
                if (mExitTime != 0) {
                    mExitTime = 0;
                }
            }
        }
        return true;
    }
}
