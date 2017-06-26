package nianhuibao.com.androidprojects;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import nianhuibao.com.androidprojects.easy_swipe_menu.EasySwipeMenuLayoutActivity;
import nianhuibao.com.androidprojects.recyclerview_list_anim.RecyclerViewListAnimActivity;
import nianhuibao.com.androidprojects.spannableString_imageSpan.SpannableStringAndImageSpanActivity;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_ImageSpanAndSpannableString).setOnClickListener(this);
        findViewById(R.id.bt_EasySwipeMenuLayoutActivity).setOnClickListener(this);
        findViewById(R.id.bt_RecyclerViewListAnimActivity).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_ImageSpanAndSpannableString:
                startActivity(this, SpannableStringAndImageSpanActivity.class);
                break;
            case R.id.bt_EasySwipeMenuLayoutActivity:
                startActivity(this, EasySwipeMenuLayoutActivity.class);
                break;
            case R.id.bt_RecyclerViewListAnimActivity:
                startActivity(this, RecyclerViewListAnimActivity.class);
                break;
            default:
                break;
        }
    }

    /**
     * 自定义开启Activity的方法
     *
     * @param context       上下文
     * @param activityClass 要开启的activity.class
     */
    public static void startActivity(Context context, Class activityClass) {
        Intent intent = new Intent(context, activityClass);
        context.startActivity(intent);
    }
}
