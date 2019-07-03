package com.suirui.drouter;



import org.suirui.drouter.common.app.BaseCommonApplication;

/**
 * description:
 * author: cui.li on 2019/3/15 10:57
 * version: 1.0
 */
public class BaseApplication extends BaseCommonApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        initRouterAndModule();
    }
}
