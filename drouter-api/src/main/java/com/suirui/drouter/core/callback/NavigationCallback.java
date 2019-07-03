package com.suirui.drouter.core.callback;

import com.suirui.drouter.core.Postcard;

/**
 * @author  cui.li by 2019/06/14
 * Description:
 */

public interface NavigationCallback {

    /**
     * 找到跳转页面
     * @param postcard
     */
    void onFound(Postcard postcard);

    /**
     * 未找到
     * @param postcard
     */
    void onLost(Postcard postcard);

    /**
     * 成功跳转
     * @param postcard
     */
    void onArrival(Postcard postcard);


}
