package org.suirui.drouter.common.module;

import com.suirui.drouter.core.template.IModule;

/**
 * 业务组件基础类，用于承载业务组件暴露的{@link BaseService 服务}，
 * </br>通俗地说就是{@link BaseService 服务}容器
 *
 * @author  cui.li by 2019/03/19
 */
public class BaseModule implements IModule {
    public BaseModule() {
//        ARouter.getInstance().inject(this);
    }
}
