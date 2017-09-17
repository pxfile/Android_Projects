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
public class ScreenUtils {
    public static float dp2Px(float dp) {
        Context context = UtilsManager.getInstance().getContext();
        if (context == null) {
            return -1;
        }
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public static float px2Dp(float px) {
        Context context = UtilsManager.getInstance().getContext();
        if (context == null) {
            return -1;
        }
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static int dip2px(float dp) {
        return (int) (dp2Px(dp) + 0.5f);
    }

    public static int px2DpCeilInt(float px) {
        return (int) (px2Dp(px) + 0.5f);
    }

}
