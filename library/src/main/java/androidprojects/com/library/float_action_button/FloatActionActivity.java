package androidprojects.com.library.float_action_button;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import androidprojects.com.library.R;
import androidprojects.com.library.utils.ScreenUtils;
import androidprojects.com.library.utils.ToastUtils;

public class FloatActionActivity extends Activity {
    private Context mContext;
    //发帖菜单以及按钮
    public FloatingActionMenu mFMCreate;
    public FloatingActionButton mFBLive;
    private FloatingActionButton mFBDiary;
    private FloatingActionButton mFBQuestion;
    private ImageView mIvFloat; //悬浮图标

    //监听手势的滑动，以准确执行「中秋活动入口view」的动画
    private boolean mIsShowFloatImage = false;
    private float mPosY = 0;
    private float mCurPosY = 0;
    private boolean mIsImageViewAtLeft = false; // 「悬浮图标」是否在左侧 ，连续滑动屏幕时，会在手指移动时执行这个方法setImageViewScrollToLeft()，为了使其不出现闪动效果，需要用 mIsImageViewAtLeft 控制
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setImageViewScrollToNormal();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_action);
        mContext = this;
        mFMCreate = (FloatingActionMenu) findViewById(R.id.main_fm_create);
        mFMCreate.setIconAnimated(false);
        mFMCreate.setClosedOnTouchOutside(true);
        mFMCreate.setVisibility(View.VISIBLE);
        mFMCreate.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFMCreate.toggle(true);
            }
        });

        mFBLive = findViewById(R.id.main_fb_live);
        mFBDiary = (FloatingActionButton) findViewById(R.id.main_fb_diary);
        mFBQuestion = (FloatingActionButton) findViewById(R.id.main_fb_question);

        addCreateButton();

        mIvFloat = (ImageView) findViewById(R.id.main_iv_float);
        mIsShowFloatImage = true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPosY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mCurPosY = event.getY();
                setImageViewScrollToLeft();
                break;
            case MotionEvent.ACTION_UP:
                mCurPosY = event.getY();
                if ((!(mCurPosY - mPosY == 0)) && (Math.abs(mCurPosY - mPosY) > 25) && mIsShowFloatImage) { // 垂直方向有滑动
                    Message mMessage = mHandler.obtainMessage();
                    mMessage.what = 1;
                    mHandler.sendMessageDelayed(mMessage, 3000);
                }
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    public void addCreateButton() {
        mFMCreate.removeAllMenuButtons();
        mFBLive = new FloatingActionButton(this);
        mFBQuestion = new FloatingActionButton(this);
        mFBDiary = new FloatingActionButton(this);

        setButtonStyle(mFBLive, getString(R.string.home_create_live), R.drawable.icon_create_live);
        setButtonStyle(mFBDiary, getString(R.string.home_create_diary), R.drawable.icon_create_diary);
        setButtonStyle(mFBQuestion, getString(R.string.home_create_question), R.drawable.icon_create_question);

        mFMCreate.addMenuButton(mFBDiary, 0);
        mFMCreate.addMenuButton(mFBQuestion, 1);
        mFMCreate.addMenuButton(mFBLive, 2);

        mFBLive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showSToast(mContext, "mFBLive");
                mFMCreate.toggle(true);
            }
        });
        mFBDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showSToast(mContext, "mFBDiary");
                mFMCreate.toggle(true);
            }
        });
        mFBQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showSToast(mContext, "mFBQuestion");
                mFMCreate.toggle(true);
            }
        });
        setLabelTextColor();
    }

    /**
     * 设置各种发日记，发问答，发直播按钮样式
     */
    private void setButtonStyle(FloatingActionButton fab, String text, int image) {
        fab.setColorNormalResId((R.color.main));
        fab.setColorPressedResId(R.color.main);
        fab.setColorRippleResId(R.color.main);
        fab.setImageResource(image);
        fab.setButtonSize(FloatingActionButton.SIZE_MINI);
        fab.setLabelText(text);
    }

    /**
     * 设置发帖按钮label的各种颜色
     */
    private void setLabelTextColor() {
        mFBLive.setLabelColors(ContextCompat.getColor(mContext, R.color.transparent),
                ContextCompat.getColor(mContext, R.color.transparent),
                ContextCompat.getColor(mContext, R.color.transparent));
        mFBLive.setLabelTextColor(ContextCompat.getColor(mContext, R.color.f_title));
        mFBDiary.setLabelColors(ContextCompat.getColor(mContext, R.color.transparent),
                ContextCompat.getColor(mContext, R.color.transparent),
                ContextCompat.getColor(mContext, R.color.transparent));
        mFBDiary.setLabelTextColor(ContextCompat.getColor(mContext, R.color.f_title));
        mFBQuestion.setLabelColors(ContextCompat.getColor(mContext, R.color.transparent),
                ContextCompat.getColor(mContext, R.color.transparent),
                ContextCompat.getColor(mContext, R.color.transparent));
        mFBQuestion.setLabelTextColor(ContextCompat.getColor(mContext, R.color.f_title));
    }

    /**
     * 「悬浮view」滑动到左侧
     */
    private void setImageViewScrollToLeft() {
        if (!mIsImageViewAtLeft && mIsShowFloatImage) {
            ObjectAnimator alpha = ObjectAnimator.ofFloat(mIvFloat, View.ALPHA, 1.0f, 0.9f, 0.8f, 0.7f, 0.6f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f);
            ObjectAnimator translate = ObjectAnimator.ofFloat(mIvFloat, View.TRANSLATION_X, 0f, -ScreenUtils.dip2px(53));
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(mIvFloat, View.SCALE_X, 1f, 0.8f, 1.2f, 1f);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(mIvFloat, View.SCALE_Y, 1f, 0.8f, 1.2f, 1f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(alpha, translate, scaleX, scaleY);
            animatorSet.setDuration(500);
            animatorSet.start();

            mIsImageViewAtLeft = true;
        }
    }

    /**
     * 「悬浮view」滑动到正常位置
     */
    private void setImageViewScrollToNormal() {
        if (mIsImageViewAtLeft && mIsShowFloatImage) {
            ObjectAnimator alpha = ObjectAnimator.ofFloat(mIvFloat, View.ALPHA, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f);
            ObjectAnimator translate = ObjectAnimator.ofFloat(mIvFloat, View.TRANSLATION_X, -ScreenUtils.dip2px(53), 0f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(alpha, translate);
            animatorSet.setDuration(500);
            animatorSet.start();

            mIsImageViewAtLeft = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(1);
    }
}
