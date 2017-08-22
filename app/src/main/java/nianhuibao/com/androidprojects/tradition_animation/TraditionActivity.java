package nianhuibao.com.androidprojects.tradition_animation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import nianhuibao.com.androidprojects.R;
import nianhuibao.com.androidprojects.tradition_animation.blur.BlurActivity;

public class TraditionActivity extends Activity implements View.OnClickListener {

    private Button frame, tweened, blur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tradition);
        InitView();
    }

    private void InitView() {
        frame = (Button) findViewById(R.id.frame);
        frame.setOnClickListener(this);
        tweened = (Button) findViewById(R.id.tween);
        tweened.setOnClickListener(this);

        blur = (Button) findViewById(R.id.blur);
        blur.setOnClickListener(this);
        blur = (Button) findViewById(R.id.blur);
        blur.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.frame:
                intent = new Intent(this, FrameAnimationActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
                break;
            case R.id.tween:
                intent = new Intent(this, TweenedAnimationActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_bottom, R.anim.out_to_bottom);
                break;
            case R.id.blur:
                intent = new Intent(this, BlurActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
                break;
            default:
                break;
        }

    }
}
