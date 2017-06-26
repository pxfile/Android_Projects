package nianhuibao.com.androidprojects.recyclerview_list_anim;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;


/**
 * 动画相关的工具类
 */

public class AnimationUtils {
    public static void homeTabAnimation(View view) {
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f, 0.8f,
                1.2f, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f, 0.8f,
                1.2f, 1f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhY, pvhZ).setDuration(500).start();
    }
}
