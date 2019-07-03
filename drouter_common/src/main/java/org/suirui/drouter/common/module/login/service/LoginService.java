package org.suirui.drouter.common.module.login.service;


import android.content.Context;

import org.suirui.drouter.common.callback.RouterCallBack;
import org.suirui.drouter.common.module.BaseService;

public interface LoginService extends BaseService {
    void startLoginActivity(Context context);
    String getLoginName( RouterCallBack routerCallBack);
}
