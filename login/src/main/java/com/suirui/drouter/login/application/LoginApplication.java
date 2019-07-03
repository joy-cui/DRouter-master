package com.suirui.drouter.login.application;

import com.suirui.drouter.login.BuildConfig;

import org.suirui.drouter.common.app.BaseCommonApplication;

public class LoginApplication extends BaseCommonApplication {
    @Override
    public void onCreate() {
        super.onCreate();
//        DRouter.openDebug();
//        DRouter.getInstance().init(this);
        initRouterAndModule();
    }
}
