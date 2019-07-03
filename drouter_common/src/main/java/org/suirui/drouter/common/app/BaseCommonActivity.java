package org.suirui.drouter.common.app;

import android.os.Bundle;
import android.support.annotation.Nullable;


import com.suirui.drouter.core.ZRouter;

import org.suirui.drouter.common.base.BaseActivity;

/**
 * 公共业务基础activity
 * @author  cui.li by 2019/03/19
 */
public class BaseCommonActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ZRouter.getsInstance().inject(this);
    }
}
