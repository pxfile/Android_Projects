package androidprojects.com.library.radar_scan_view;

import android.view.View;

import androidprojects.com.library.BaseActivity;
import androidprojects.com.library.R;

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

    public void onClick(View view) {
        if (view.getId() == R.id.start) {
            mRadarScanView.startAnim();
        } else if (view.getId() == R.id.stop) {
            mRadarScanView.stopAnim();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mRadarScanView.startAnim();
    }
}
