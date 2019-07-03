package org.suirui.drouter.common.module.login;

import org.suirui.drouter.common.module.BaseModule;
import org.suirui.drouter.common.module.login.service.LoginService;

public abstract class LoginModule extends BaseModule {

    public abstract LoginService getLoginService();
}
