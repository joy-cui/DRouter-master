package org.suirui.drouter.common.app;



import com.suirui.drouter.core.ZRouter;

import org.suirui.drouter.common.base.BaseApplication;

/**
 * 公共业务基础application
 *
 * @author  cui.li by 2019/03/19
 */
public abstract class BaseCommonApplication extends BaseApplication {

    /**
     * 初始化路由和组件
     *
     */
    protected final void initRouterAndModule() {
        ZRouter.init(this);
    }
}
