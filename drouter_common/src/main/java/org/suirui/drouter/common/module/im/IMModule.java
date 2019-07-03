package org.suirui.drouter.common.module.im;


import org.suirui.drouter.common.module.BaseModule;
import org.suirui.drouter.common.module.im.service.IMDaoService;
import org.suirui.drouter.common.module.im.service.IMService;

public abstract class IMModule extends BaseModule {

    public abstract IMService getIMService();

    public abstract IMDaoService getIMDaoService();
}
