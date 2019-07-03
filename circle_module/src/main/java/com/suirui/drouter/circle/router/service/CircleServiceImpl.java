package com.suirui.drouter.circle.router.service;

import android.content.Context;

import com.suirui.drouter.annotation.Route;
import com.suirui.drouter.core.ZRouter;

import org.suirui.drouter.common.module.circle.path.CirclePathContant;
import org.suirui.drouter.common.module.circle.service.CircleService;

@Route(path = CirclePathContant.PATH_SERVICE_CIRCLE)
public class CircleServiceImpl implements CircleService {
    @Override
    public void startToCircle() {
        ZRouter.getsInstance().build(CirclePathContant.PATH_VIEW_CIRCLE).navigation();

    }

    @Override
    public void init(Context context) {

    }
}
