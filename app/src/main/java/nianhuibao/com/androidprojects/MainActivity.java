package nianhuibao.com.androidprojects;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import nianhuibao.com.androidprojects.circle_reveal_loading.CircleRevealLoadingActivity;
import nianhuibao.com.androidprojects.circular_reveal_anima.SearchViewActivity;
import nianhuibao.com.androidprojects.easy_swipe_menu.EasySwipeMenuLayoutActivity;
import nianhuibao.com.androidprojects.fadeIn_textView.FadeInTextViewActivity;
import nianhuibao.com.androidprojects.horizontal_progressBar.ProgressBarActivity;
import nianhuibao.com.androidprojects.pay_psd_inputView.PayPsdViewActivity;
import nianhuibao.com.androidprojects.rang_seekBar.RangeSeekBarActivity;
import nianhuibao.com.androidprojects.recyclerview_ce_hua.RecyclerCeHuaActivity;
import nianhuibao.com.androidprojects.recyclerview_list_anim.RecyclerViewListAnimActivity;
import nianhuibao.com.androidprojects.spannableString_imageSpan.SpannableStringAndImageSpanActivity;
import nianhuibao.com.androidprojects.sticky_decoration.view.StickyDecorationActivity;
import nianhuibao.com.androidprojects.super_textView.SuperTextViewActivity;
import nianhuibao.com.androidprojects.titanic.TitanicTextViewActivity;
import nianhuibao.com.androidprojects.tradition_animation.TraditionActivity;
import nianhuibao.com.androidprojects.ultraviewpager.UPVDemoActivity;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_ImageSpanAndSpannableString).setOnClickListener(this);
        findViewById(R.id.bt_EasySwipeMenuLayoutActivity).setOnClickListener(this);
        findViewById(R.id.bt_RecyclerViewListAnimActivity).setOnClickListener(this);
        findViewById(R.id.bt_FadeInTextViewActivity).setOnClickListener(this);
        findViewById(R.id.bt_ProgressBarActivity).setOnClickListener(this);
        findViewById(R.id.bt_PayPsdInputViewActivity).setOnClickListener(this);
        findViewById(R.id.bt_RecyclerCeHuaActivity).setOnClickListener(this);
        findViewById(R.id.bt_RangeSeekBarActivity).setOnClickListener(this);
        findViewById(R.id.bt_StickyDecorationActivity).setOnClickListener(this);
        findViewById(R.id.bt_TitanicTextViewActivity).setOnClickListener(this);
        findViewById(R.id.bt_SuperTextViewActivity).setOnClickListener(this);
        findViewById(R.id.bt_SearchViewActivity).setOnClickListener(this);
        findViewById(R.id.bt_CircleRevealLoadingActivity).setOnClickListener(this);
        findViewById(R.id.bt_UltraViewPagerActivity).setOnClickListener(this);
        findViewById(R.id.bt_TraditionActivity).setOnClickListener(this);
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
            case R.id.bt_FadeInTextViewActivity:
                startActivity(this, FadeInTextViewActivity.class);
                break;
            case R.id.bt_ProgressBarActivity:
                startActivity(this, ProgressBarActivity.class);
                break;
            case R.id.bt_PayPsdInputViewActivity:
                startActivity(this, PayPsdViewActivity.class);
                break;
            case R.id.bt_RecyclerCeHuaActivity:
                startActivity(this, RecyclerCeHuaActivity.class);
                break;
            case R.id.bt_RangeSeekBarActivity:
                startActivity(this, RangeSeekBarActivity.class);
                break;
            case R.id.bt_StickyDecorationActivity:
                startActivity(this, StickyDecorationActivity.class);
                break;
            case R.id.bt_TitanicTextViewActivity:
                startActivity(this, TitanicTextViewActivity.class);
                break;
            case R.id.bt_SuperTextViewActivity:
                startActivity(this, SuperTextViewActivity.class);
                break;
            case R.id.bt_SearchViewActivity:
                startActivity(this, SearchViewActivity.class);
                break;
            case R.id.bt_CircleRevealLoadingActivity:
                startActivity(this, CircleRevealLoadingActivity.class);
                break;
            case R.id.bt_UltraViewPagerActivity:
                startActivity(this, UPVDemoActivity.class);
                break;
            case R.id.bt_TraditionActivity:
                startActivity(this, TraditionActivity.class);
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
