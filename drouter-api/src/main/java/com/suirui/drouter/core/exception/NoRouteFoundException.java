package com.suirui.drouter.core.exception;

/**
 * @author  cui.li by 2019/06/14
 * Description:
 */

public class NoRouteFoundException extends RuntimeException {

    public NoRouteFoundException(String detailMessage) {
        super(detailMessage);
    }
}
