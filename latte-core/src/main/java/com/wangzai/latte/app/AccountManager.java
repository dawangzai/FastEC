package com.wangzai.latte.app;

import com.wangzai.latte.util.storage.LattePreference;

/**
 * Created by wangzai on 2017/8/10
 */

public class AccountManager {

    /**
     * 设置用户登录状态
     *
     * @param state
     */
    public static void setSignState(boolean state) {
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    /**
     * 检查用户是否登录
     *
     * @return
     */
    private static boolean isSignIn() {
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker iUserChecker) {
        if (isSignIn()) {
            iUserChecker.onSignIn();
        } else {
            iUserChecker.onNotSignIn();
        }
    }
}
