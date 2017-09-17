package androidprojects.com.library.utils;

import android.content.Context;

/**
 * ***********************************************************************
 * Author:pengxiaofang
 * CreateData:2017-09-17 13:19
 * Version:xx
 * Description:xx
 * ***********************************************************************
 */
public class UtilsManager {
    private Context mContext;

    private static UtilsManager mInstance = new UtilsManager();

    public static UtilsManager getInstance() {
        return mInstance;
    }

    private UtilsManager() {
    }

    public void setApplicationContext(Context context) {
        mContext = context.getApplicationContext();
//        DeviceUtils.init(mContext);
//        LocationServiceutils.getInstance().startBaiduLocationService(mContext);
//        SDKInitializer.initialize(mContext);
//        LogUtil.initialize(BuildConfigUtil.getFieldDebug());
//        StorageUtils.init();
    }

    public Context getContext() {
        return mContext;
    }
}
