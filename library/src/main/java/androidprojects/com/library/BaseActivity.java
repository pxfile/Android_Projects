package androidprojects.com.library;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public abstract class BaseActivity extends Activity implements View.OnClickListener {
    // 页面根节点
    protected View mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        initView();
    }

    private void setContentView() {
        // 初始化页面布局
        int res = setLayoutViewId();
        if (res != 0) {
            mRootView = LayoutInflater.from(this).inflate(res, null);
            setContentView(mRootView);
        }
    }

    /**
     * 统一为各种view添加点击事件
     */
    protected void setOnClickListener(View... views) {
        for (View view : views) {
            if (view != null) {
                view.setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View view) {
    }

    /**
     * 设置页面布局
     */
    protected abstract int setLayoutViewId();

    /**
     * 初始化视图
     */
    public abstract void initView();

    /**
     * 自定义开启Activity的方法
     *
     * @param context       上下文
     * @param activityClass 要开启的activity.class
     */
    protected void startActivity(Context context, Class activityClass) {
        Intent intent = new Intent(context, activityClass);
        context.startActivity(intent);
    }
}
