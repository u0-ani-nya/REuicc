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

public class hooker3 implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedHelpers.findAndHookMethod("com.android.euicc.card.BlockingEuiccCardManager$ResultRetriever", lpparam.classLoader, "onComplete", int.class, java.lang.Object.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                param.args[0] = 0;
                XposedBridge.log("obj: " + param.args[1]);
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                XposedHelpers.callMethod(param.thisObject, "set", param.args[1]);
            }
        });
    }
}