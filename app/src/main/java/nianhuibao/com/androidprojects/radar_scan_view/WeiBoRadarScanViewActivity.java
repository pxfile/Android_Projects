package nianhuibao.com.androidprojects.radar_scan_view;

import android.view.View;

import nianhuibao.com.androidprojects.BaseActivity;
import nianhuibao.com.androidprojects.R;

public class WeiBoRadarScanViewActivity extends BaseActivity {
    private RadarScanView mRadarScanView;

    @Override
    protected int setLayoutViewId() {
        return R.layout.activity_wei_bo_radar_scan_view;
    }

    @Override
    public void initView() {
        mRadarScanView = (RadarScanView) findViewById(R.id.radar_scan_view);
        setOnClickListener(findViewById(R.id.start), findViewById(R.id.stop));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start:
                mRadarScanView.startAnim();
                break;
            case R.id.stop:
                mRadarScanView.stopAnim();
                break;
            default:
                break;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mRadarScanView.startAnim();
    }
}
