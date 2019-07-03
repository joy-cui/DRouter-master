package org.suirui.drouter.common.module.im.service;

import android.support.v4.app.Fragment;

import org.suirui.drouter.common.module.BaseService;

public interface IMService extends BaseService {
    Fragment createIMEntranceFragment();

    void startIM();
}
