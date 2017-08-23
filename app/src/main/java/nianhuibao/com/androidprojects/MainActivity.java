package nianhuibao.com.androidprojects;

import android.view.View;

import nianhuibao.com.androidprojects.circle_reveal_loading.CircleRevealLoadingActivity;
import nianhuibao.com.androidprojects.circular_reveal_anima.SearchViewActivity;
import nianhuibao.com.androidprojects.drag_ball_view.DragBallViewActivity;
import nianhuibao.com.androidprojects.easy_swipe_menu.EasySwipeMenuLayoutActivity;
import nianhuibao.com.androidprojects.fadeIn_textView.FadeInTextViewActivity;
import nianhuibao.com.androidprojects.horizontal_progressBar.ProgressBarActivity;
import nianhuibao.com.androidprojects.pay_psd_inputView.PayPsdViewActivity;
import nianhuibao.com.androidprojects.property_animation.PropertyActivity;
import nianhuibao.com.androidprojects.radar_scan_view.WeiBoRadarScanViewActivity;
import nianhuibao.com.androidprojects.rang_seekBar.RangeSeekBarActivity;
import nianhuibao.com.androidprojects.recyclerview_ce_hua.RecyclerCeHuaActivity;
import nianhuibao.com.androidprojects.recyclerview_list_anim.RecyclerViewListAnimActivity;
import nianhuibao.com.androidprojects.screen_shot.FakeJianShuActivity;
import nianhuibao.com.androidprojects.spannableString_imageSpan.SpannableStringAndImageSpanActivity;
import nianhuibao.com.androidprojects.sticky_decoration.view.StickyDecorationActivity;
import nianhuibao.com.androidprojects.super_textView.SuperTextViewActivity;
import nianhuibao.com.androidprojects.titanic.TitanicTextViewActivity;
import nianhuibao.com.androidprojects.tradition_animation.TraditionActivity;
import nianhuibao.com.androidprojects.ultraviewpager.UPVDemoActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int setLayoutViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        setOnClickListener(findViewById(R.id.bt_ImageSpanAndSpannableString),
                findViewById(R.id.bt_EasySwipeMenuLayoutActivity),
                findViewById(R.id.bt_RecyclerViewListAnimActivity),
                findViewById(R.id.bt_FadeInTextViewActivity),
                findViewById(R.id.bt_ProgressBarActivity),
                findViewById(R.id.bt_PayPsdInputViewActivity),
                findViewById(R.id.bt_RecyclerCeHuaActivity),
                findViewById(R.id.bt_RangeSeekBarActivity),
                findViewById(R.id.bt_StickyDecorationActivity),
                findViewById(R.id.bt_TitanicTextViewActivity),
                findViewById(R.id.bt_SuperTextViewActivity),
                findViewById(R.id.bt_SearchViewActivity),
                findViewById(R.id.bt_CircleRevealLoadingActivity),
                findViewById(R.id.bt_UltraViewPagerActivity),
                findViewById(R.id.bt_TraditionActivity),
                findViewById(R.id.bt_PropertyActivity),
                findViewById(R.id.bt_WeiBoRadarScanViewActivity),
                findViewById(R.id.bt_DragBallViewActivity),
                findViewById(R.id.bt_FakeJianShuActivity)
        );
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
            case R.id.bt_PropertyActivity:
                startActivity(this, PropertyActivity.class);
                break;
            case R.id.bt_WeiBoRadarScanViewActivity:
                startActivity(this, WeiBoRadarScanViewActivity.class);
                break;
            case R.id.bt_DragBallViewActivity:
                startActivity(this, DragBallViewActivity.class);
                break;
            case R.id.bt_FakeJianShuActivity:
                startActivity(this, FakeJianShuActivity.class);
                break;
            default:
                break;
        }
    }
}
