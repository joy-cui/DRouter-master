package com.suirui.drouter.core.template;

import java.util.Map;

/**
 * @author  cui.li by 2019/06/14
 * Description:
 */

public interface IRouteRoot {
    void loadInto(Map<String, Class<? extends IRouteGroup>> routes);
}
