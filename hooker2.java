package com.ani_nya.reuicc_fix;

import android.os.Build;
import java.util.Collections;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by mark peng on 2023/8/15.
 */

public class hooker2 implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedHelpers.findAndHookMethod("com.android.euicc.service.EuiccServiceImpl", lpparam.classLoader, "onGetEuiccProfileInfoList", int.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                // 将传入的 i 参数设置为 1
                param.args[0] = 1;
            }

            @Override
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                // 获取返回值
                Object result = param.getResult();
                // 将 isRemovable 设置为 true
                XposedHelpers.setBooleanField(result, "isRemovable", true);
                // 更新返回值
                param.setResult(result);
            }
        });
    }
}