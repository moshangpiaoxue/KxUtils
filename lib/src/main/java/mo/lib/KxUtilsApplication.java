package mo.lib;

import android.app.Application;

import org.xutils.x;

/**
 * @ author：mo
 * @ data：2019/3/20:17:25
 * @ 功能：很重要！！！！！！或继承此类。或在自己的application里调用x.Ext.init(this);
 */
public class KxUtilsApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化xutils
        x.Ext.init(this);
        //设置xutils的log工具生效，默认true
        x.Ext.setDebug(true);
    }
}
