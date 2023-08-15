package com.ani_nya.reuicc_fix;
import com.ani_nya.reuicc_fix.hooker1;
        import com.ani_nya.reuicc_fix.hooker2;
                import com.ani_nya.reuicc_fix.hooker3;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;


/**
 * Created by mark peng on 2023/8/15.
 */

public class hooker implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        new hooker1().handleLoadPackage(lpparam);
        new hooker2().handleLoadPackage(lpparam);
        new hooker3().handleLoadPackage(lpparam);
    }
}
