package nianhuibao.com.androidprojects.circle_reveal_loading;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import nianhuibao.com.androidprojects.R;

/**
 * Created by lxf on 2017/8/2.
 */

public class CircleRevealLoadingActivity extends Activity {

    private WaterRippleView mWaterWrv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_reveal_loading);

        mWaterWrv = (WaterRippleView) findViewById(R.id.wrv_water);
    }

    public void onClickStart(View v) {
        mWaterWrv.start();
    }

    public void onClickStop(View v) {
        mWaterWrv.stop();
    }
}
