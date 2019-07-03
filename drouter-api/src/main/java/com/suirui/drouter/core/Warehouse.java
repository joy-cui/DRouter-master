package com.suirui.drouter.core;

import com.suirui.drouter.annotation.modle.RouteMeta;
import com.suirui.drouter.core.template.IRouteGroup;
import com.suirui.drouter.core.template.IService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author  cui.li by 2019/06/14
 * Description:
 */

public class Warehouse {

    // root 映射表 保存分组信息
    static Map<String, Class<? extends IRouteGroup>> groupsIndex = new HashMap<>();

    // group 映射表 保存组中的所有数据
    static Map<String, RouteMeta> routes = new HashMap<>();

    // group 映射表 保存组中的所有数据
    static Map<Class, IService> services = new HashMap<>();
    // TestServiceImpl.class , TestServiceImpl 没有再反射
}
