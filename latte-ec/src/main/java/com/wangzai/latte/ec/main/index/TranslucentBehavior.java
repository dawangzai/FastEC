package com.wangzai.latte.ec.main.index;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.wangzai.latte.ec.R;
import com.wangzai.latte.ui.recycler.RgbValue;

/**
 * Created by wangzai on 2017/8/15
 */

@SuppressWarnings("unused")
public class TranslucentBehavior extends CoordinatorLayout.Behavior<Toolbar> {

    //Y轴滑动的距离
    private int mDistanceY = 0;
    //最终Toolbar的颜色,橘黄色
    private final RgbValue rgbValue = RgbValue.create(255, 142, 2);

    public TranslucentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        return dependency.getId() == R.id.rv_index;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);

        mDistanceY += dy;
        //Toolbar 的高度
        int targetHeight = child.getBottom();
        if (mDistanceY < targetHeight) { //Y轴滑动的距离小于 Toolbar 的高度，修改 Toolbar 的渐变色
            final float scale = (float) mDistanceY / targetHeight;
            //根据比例换算透明值
            final float alpha = scale * 255;
            child.setBackgroundColor(Color.argb((int) alpha, rgbValue.red(), rgbValue.green(), rgbValue.blue()));
        } else if (mDistanceY > targetHeight) {
            child.setBackgroundColor(Color.rgb(rgbValue.red(), rgbValue.green(), rgbValue.blue()));
        }
    }
}
