package nianhuibao.com.androidprojects;

import android.view.View;

import androidprojects.com.library.BaseActivity;
import androidprojects.com.library.circle_reveal_loading.CircleRevealLoadingActivity;
import androidprojects.com.library.circular_reveal_anima.SearchViewActivity;
import androidprojects.com.library.drag_ball_view.DragBallViewActivity;
import androidprojects.com.library.easy_swipe_menu.EasySwipeMenuLayoutActivity;
import androidprojects.com.library.fadeIn_textView.FadeInTextViewActivity;
import androidprojects.com.library.float_action_button.FloatActionActivity;
import androidprojects.com.library.horizontal_progressBar.ProgressBarActivity;
import androidprojects.com.library.max_height_view.MaxHeightViewActivity;
import androidprojects.com.library.pay_psd_inputView.PayPsdViewActivity;
import androidprojects.com.library.property_animation.PropertyActivity;
import androidprojects.com.library.pull_recyclerView.PullRecyclerViewActivity;
import androidprojects.com.library.radar_scan_view.WeiBoRadarScanViewActivity;
import androidprojects.com.library.rang_seekBar.RangeSeekBarActivity;
import androidprojects.com.library.recyclerview_ce_hua.RecyclerCeHuaActivity;
import androidprojects.com.library.recyclerview_list_anim.RecyclerViewListAnimActivity;
import androidprojects.com.library.screen_shot.FakeJianShuActivity;
import androidprojects.com.library.spannableString_imageSpan.SpannableStringAndImageSpanActivity;
import androidprojects.com.library.sticky_decoration.view.StickyDecorationActivity;
import androidprojects.com.library.super_textView.SuperTextViewActivity;
import androidprojects.com.library.titanic.TitanicTextViewActivity;
import androidprojects.com.library.tradition_animation.TraditionActivity;
import androidprojects.com.library.ultraviewpager.UPVDemoActivity;
import androidprojects.com.library.wheel_select.WheelSelectActivity;

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
                findViewById(R.id.bt_FakeJianShuActivity),
                findViewById(R.id.bt_PullRecyclerViewActivity),
                findViewById(R.id.bt_MaxHeightViewActivity),
                findViewById(R.id.bt_WheelSelectActivity),
                findViewById(R.id.bt_FloatActionActivity)
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
            case R.id.bt_PullRecyclerViewActivity:
                startActivity(this, PullRecyclerViewActivity.class);
                break;
            case R.id.bt_MaxHeightViewActivity:
                startActivity(this, MaxHeightViewActivity.class);
                break;
            case R.id.bt_WheelSelectActivity:
                startActivity(this, WheelSelectActivity.class);
            case R.id.bt_FloatActionActivity:
                startActivity(this, FloatActionActivity.class);
                break;
            default:
                break;
        }
    }
}
