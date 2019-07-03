package com.suirui.drouter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.suirui.drouter.core.ZRouter;

import org.suirui.drouter.common.app.BaseCommonActivity;
import org.suirui.drouter.common.module.circle.path.CirclePathContant;
import org.suirui.drouter.common.module.circle.service.CircleService;
import org.suirui.drouter.common.module.login.LoginPathContants;
import org.suirui.drouter.common.module.login.service.LoginService;


/**
 * description:
 * author: cui.li on 2019/3/15 10:57
 * version: 1.0
 */
public class MainActivity extends BaseCommonActivity {
LoginService loginService;
CircleService circleService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginService=(LoginService)ZRouter.getsInstance().build(LoginPathContants.PATH_SERVICE_LOGIN).navigation();
        circleService=(CircleService)ZRouter.getsInstance().build(CirclePathContant.PATH_SERVICE_CIRCLE).navigation();
    }

    public void jumpLogin(View view) {
//        DRouter.getInstance()
//                .action("login/main")
//                .context(this)
//                .param("key", "测试_demo")
//                .invokeAction();
        if(loginService==null){
            Toast.makeText(this,"loginModule没有配置路由",Toast.LENGTH_LONG).show();
        }else {
            loginService.startLoginActivity(this);

//            loginModule.getLoginService().getLoginName(new RouterCallBack() {
//                @Override
//                public void onResult(Context context, String flag, Object... objects) {
//                    Toast.makeText(MainActivity.this,flag+" : "+objects[0],Toast.LENGTH_LONG).show();
//                }
//                @Override
//                public void onError() {
//                    Toast.makeText(MainActivity.this,"error",Toast.LENGTH_LONG).show();;
//                }
//            });
        }
    }

    public void jumpCircle(View view) {
        if(circleService!=null){
            circleService.startToCircle();
        }else{
            Toast.makeText(MainActivity.this,"circle...null",Toast.LENGTH_LONG).show();
        }
//        DRouter.getInstance()
//                .action("circlemodule/main")
//                .context(this)
//                .param("key", "圈子_demo")
//                .invokeAction(new ActionCallback() {
//                    @Override
//                    public void onInterrupt() {
//                        System.out.println("intercept...被拦截了，哈哈");
////                        Log.e("","被拦截了，哈哈。。。");
////                        Toast.makeText(MainActivity.this, "被拦截了，哈哈。。。", Toast.LENGTH_LONG).show();
////                        DRouter.getInstance()
////                                .action("circlemodule/main")
////                                .context(MainActivity.this)
////                                .invokeAction();
//                    }
//                    @Override
//                    public void onResult(RouterResult result) {
//                        System.out.println("intercept..onResult："+ result.toString());
//                        Log.e("TAG", "result = " + result.toString());
//                        Toast.makeText(MainActivity.this, "result = " + result.toString(), Toast.LENGTH_LONG).show();
//                        if(result.isSucceed()){
//                            System.out.println("intercept..onResult：错误");
//                        }
//                    }
//                });
    }
}
