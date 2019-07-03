package com.suirui.drouter.core.template;

import com.suirui.drouter.annotation.modle.RouteMeta;

import java.util.Map;

/**
 * @author  cui.li by 2019/06/14
 * Description:
 */

public interface IRouteGroup {
    void loadInto(Map<String, RouteMeta> atlas);
}
