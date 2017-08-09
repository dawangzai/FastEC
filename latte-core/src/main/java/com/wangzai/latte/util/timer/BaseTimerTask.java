package com.wangzai.latte.util.timer;

import java.util.TimerTask;

/**
 * Created by wangzai on 2017/8/9
 */

public class BaseTimerTask extends TimerTask{

    private ITimerListener mITimerListener;
    public BaseTimerTask(ITimerListener iTimerListener){
        this.mITimerListener = iTimerListener;
    }
    @Override
    public void run() {
        mITimerListener.onTimer();
    }
}
