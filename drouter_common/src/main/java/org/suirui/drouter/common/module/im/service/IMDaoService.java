package org.suirui.drouter.common.module.im.service;

import org.suirui.drouter.common.module.BaseService;

public interface IMDaoService extends BaseService {
    String getContact();

    void updateContact(String name);
}
