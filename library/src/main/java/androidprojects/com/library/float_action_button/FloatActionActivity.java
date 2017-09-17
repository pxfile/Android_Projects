package androidprojects.com.library.float_action_button;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import androidprojects.com.library.R;
import androidprojects.com.library.utils.ToastUtils;

public class FloatActionActivity extends Activity {
    private Context mContext;
    //发帖菜单以及按钮
    public FloatingActionMenu mFMCreate;
    public FloatingActionButton mFBLive;
    private FloatingActionButton mFBDiary;
    private FloatingActionButton mFBQuestion;
    private ImageView mIvFloat; //悬浮图标

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
}
