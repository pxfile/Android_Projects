package nianhuibao.com.androidprojects.circular_reveal_anima;

import android.animation.Animator;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import static nianhuibao.com.androidprojects.sticky_decoration.util.DensityUtil.dip2px;

public class SearchViewUtils {
    public static void handleToolBar(final Context context, final View view, final EditText editText) {
        //隐藏
        if (view.getVisibility() == View.VISIBLE) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                final Animator animatorHide = ViewAnimationUtils.createCircularReveal(view,
                        view.getWidth() - dip2px(context, 56),
                        dip2px(context, 23),
                        //确定元的半径（算长宽的斜边长，这样半径不会太短也不会很长效果比较舒服）
                        (float) Math.hypot(view.getWidth(), view.getHeight()),
                        0);
                animatorHide.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(View.GONE);
                        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                animatorHide.setDuration(300);
                animatorHide.start();
            } else {
//                关闭输入法
                ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), 0);
                view.setVisibility(View.GONE);
            }
            editText.setText("");
            view.setEnabled(false);
        }
        //显示
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                final Animator animator = ViewAnimationUtils.createCircularReveal(view,
                        view.getWidth() - dip2px(context, 56),
                        dip2px(context, 23),
                        0,
                        (float) Math.hypot(view.getWidth(), view.getHeight()));
                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                view.setVisibility(View.VISIBLE);
                if (view.getVisibility() == View.VISIBLE) {
                    animator.setDuration(300);
                    animator.start();
                    view.setEnabled(true);
                }
            } else {
                view.setVisibility(View.VISIBLE);
                view.setEnabled(true);
                //                关闭输入法
                ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
            }
        }
    }
}
