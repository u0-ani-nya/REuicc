package com.ani_nya.reuicc_fix;

import android.os.Build;
import java.util.Collections;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class hooker1 implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.packageName.equals("com.google.android.euicc")) {
            return;
        }
        // hook getSimSlotMappingForDevice 方法
        XposedHelpers.findAndHookMethod("com.google.android.euicc.config.SimSlotMapping", lpparam.classLoader, "getSimSlotMappingForDevice", new XC_MethodReplacement() {
            @Override
            protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                // 定义 SIM 卡插槽映射 JSON 字符串
                String simSlotMappingJson = "{\"sim-slot-mappings\": [{\"devices\": [\"OnePlus8\"],\"esim-slot-ids\": [1],\"psim-slot-ids\": [0]}]}";
                // 获取 SimSlotMapping 类
                Class<?> simSlotMappingClass = XposedHelpers.findClass("com.google.android.euicc.config.SimSlotMapping", lpparam.classLoader);
                // 调用 getSimSlotMapping 方法并传入 JSON 字符串和设备名称作为参数
                Object simSlotMapping = XposedHelpers.callStaticMethod(simSlotMappingClass, "getSimSlotMapping", simSlotMappingJson, "OnePlus8");
                // 如果返回值为 null，则创建一个新的 SimSlotMapping 对象并返回
                if (simSlotMapping == null) {
                    simSlotMapping = XposedHelpers.newInstance(simSlotMappingClass);
                    XposedHelpers.setObjectField(simSlotMapping, "mEsimSlotIds", Collections.singletonList(1));
                    XposedHelpers.setObjectField(simSlotMapping, "mPsimSlotIds", Collections.singletonList(0));
                }
                // 返回结果
                return simSlotMapping;
            }
        });
    }
}