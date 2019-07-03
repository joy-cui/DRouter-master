package com.suirui.router.business;

import android.content.Context;

import com.suirui.drouter.annotation.Route;

import org.suirui.drouter.common.module.business.path.BusinessPathContants;
import org.suirui.drouter.common.module.business.service.BusinessService;

@Route(path = BusinessPathContants.PATH_SERVICE_BINESS)
public class BusinessServiceImpl implements BusinessService {
    @Override
    public String  test(String path) {
        System.out.println("LoginService...BusinessServiceImpl..test ");
        return "TestServiceImpl调用成功";

    }

    @Override
    public void init(Context context) {

    }
}
