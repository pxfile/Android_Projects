package androidprojects.com.library.fadeIn_textView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidprojects.com.library.R;


public class FadeInTextViewActivity extends Activity {

    private Context mContext;
    private FadeInTextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fade_in_text_view);
        mContext = this;
        mTextView = (FadeInTextView) findViewById(R.id.fade_in_tv);
        mTextView.setTextString("测试的字符串");
        mTextView.startFadeInAnimation();
        mTextView.setTextAnimationListener(new FadeInTextView.TextAnimationListener() {
            @Override
            public void animationFinish() {
                Toast.makeText(mContext, "动画结束了", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
