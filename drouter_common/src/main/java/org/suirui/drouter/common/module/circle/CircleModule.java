package org.suirui.drouter.common.module.circle;


import org.suirui.drouter.common.module.BaseModule;
import org.suirui.drouter.common.module.circle.service.CircleService;

public abstract class CircleModule extends BaseModule {

    public abstract CircleService getCircleService();
}
