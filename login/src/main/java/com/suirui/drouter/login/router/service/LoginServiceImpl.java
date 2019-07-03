package com.suirui.drouter.login.router.service;

import android.content.Context;

import com.suirui.drouter.annotation.Route;
import com.suirui.drouter.core.ZRouter;

import org.suirui.drouter.common.callback.RouterCallBack;
import org.suirui.drouter.common.module.login.LoginPathContants;
import org.suirui.drouter.common.module.login.service.LoginService;


@Route(path = LoginPathContants.PATH_SERVICE_LOGIN)
public class LoginServiceImpl implements LoginService {
      @Override
    public void startLoginActivity(Context context) {
        System.out.println("LoginService.....startMainActivity");

        ZRouter.getsInstance()
                .build(LoginPathContants.PATH_VIEW_LOGIN,"loginGroup")
                .withString("key","startLoginActivity跳转来的")
                .navigation();

    }

    @Override
    public String getLoginName(RouterCallBack routerCallBack) {
        if(routerCallBack!=null){
            routerCallBack.onResult(null,"测试","ceshi");
        }
        return "测试deng传递数据";
    }

    @Override
    public void init(Context context) {
        System.out.println("LoginService.....init");
    }
}
