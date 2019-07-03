package com.suirui.drouter.circle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.suirui.drouter.annotation.Route;
import com.suirui.drouter.core.ZRouter;

import org.suirui.drouter.common.app.BaseCommonActivity;
import org.suirui.drouter.common.module.circle.path.CirclePathContant;
import org.suirui.drouter.common.module.login.LoginPathContants;
import org.suirui.drouter.common.module.login.service.LoginService;

/**
 * description:
 * author: cui.li on 2019/3/15 10:57
 * version: 1.0
 */
@Route(path =CirclePathContant.PATH_VIEW_CIRCLE)
public class CircleActivity extends BaseCommonActivity {
    LoginService loginService;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);
        loginService = (LoginService) ZRouter.getsInstance().build(LoginPathContants.PATH_SERVICE_LOGIN).navigation();
    }

    public void click(View view) {
        System.out.println("LoginService...click: "+loginService);
        if(loginService!=null){
            loginService.startLoginActivity(this);
        }
    }
}
