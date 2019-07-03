package com.suirui.drouter.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.suirui.drouter.annotation.Route;
import com.suirui.drouter.core.ZRouter;

import org.suirui.drouter.common.app.BaseCommonActivity;
import org.suirui.drouter.common.module.business.path.BusinessPathContants;
import org.suirui.drouter.common.module.business.service.BusinessService;

import org.suirui.drouter.common.module.login.LoginPathContants;

import java.util.Map;

/**
 * description:
 * author: cui.li on 2019/3/15 10:57
 * version: 1.0
 */
@Route(path = LoginPathContants.PATH_VIEW_LOGIN)
public class LoginActivity extends BaseCommonActivity {
    BusinessService iTestService;
    TextView textView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         textView=(TextView)this.findViewById(R.id.test);
        textView.setText("测试路由跳转");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        String key = getIntent().getStringExtra("key");
         Toast.makeText(this, key, Toast.LENGTH_LONG).show();
        iTestService=(BusinessService)ZRouter.getsInstance().build(BusinessPathContants.PATH_SERVICE_BINESS).navigation();
    }

    public void click(View view){
        System.out.println("LoginService...startToCircle:"+iTestService);
//        if(circleModule!=null){
//            circleModule.getCircleService().startToCircle();
//        }

//        BusinessService iTestService = ARouter.getInstance().navigation(BusinessService.class);
        if(iTestService!=null) {
            Toast.makeText(this, iTestService.test("startMainActivity")+"注入", Toast.LENGTH_LONG).show();
        }else{
            System.out.println("LoginService.....BusinessService==null: ");
        }

//        DRouter.getInstance()
//                .action("circlemodule/main")
//                .context(this)
//                .param("key", "登录中跳转到圈子")
//                .invokeAction(new ActionCallback() {
//                    @Override
//                    public void onInterrupt() {
//
//                        Log.e("TAG", "被拦截了");
//                        return;
//                    }
//
//                    @Override
//                    public void onResult(RouterResult result) {
//                        Log.e("TAG", "result = " + result.toString());
//                    }
//                });
    }
}
