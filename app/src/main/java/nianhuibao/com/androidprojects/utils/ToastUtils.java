package nianhuibao.com.androidprojects.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by rookie on 2017/2/22.
 */

public class ToastUtils {
    public static void showLToast(Context mContext, String content) {
        Toast.makeText(mContext, content, Toast.LENGTH_LONG).show();
    }

    public static void showSToast(Context mContext, String content) {
        Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show();
    }

}
