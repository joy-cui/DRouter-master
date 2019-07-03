package com.suirui.drouter.circle.application;


import com.suirui.drouter.circle.BuildConfig;

import org.suirui.drouter.common.app.BaseCommonApplication;

/**
 * description:
 * author: cui.li on 2019/3/15 10:57
 * version: 1.0
 */
public class CircleApplication extends BaseCommonApplication {
    @Override
    public void onCreate() {
        super.onCreate();
//        DRouter.openDebug();
//        DRouter.getInstance().init(this);
        initRouterAndModule();
    }
}
