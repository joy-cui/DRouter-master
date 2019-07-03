package org.suirui.drouter.common.callback;

import android.content.Context;

public interface RouterCallBack {
     void onResult(Context context, String flag, Object... objects);
     void onError();
}
