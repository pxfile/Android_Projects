package androidprojects.com.library.rang_seekBar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.DecimalFormat;

import androidprojects.com.library.R;


public class RangeSeekBarActivity extends AppCompatActivity {

    private RangeSeekBar seekbar1;
    private RangeSeekBar seekbar2;
    private TextView tv2;
    private DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_range_seek_bar);

        seekbar1 = (RangeSeekBar) findViewById(R.id.seekbar1);
        seekbar2 = (RangeSeekBar) findViewById(R.id.seekbar2);
        tv2 = (TextView) findViewById(R.id.progress2_tv);

        seekbar1.setValue(10);
        seekbar2.setValue(-0.5f, 0.8f);

        seekbar1.setOnRangeChangedListener(new RangeSeekBar.OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float min, float max, boolean isFromUser) {
                seekbar1.setProgressDescription((int) min + "%");
            }
        });

        seekbar2.setOnRangeChangedListener(new RangeSeekBar.OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float min, float max, boolean isFromUser) {
                if (isFromUser) {
                    tv2.setText(min + "-" + max);
                    seekbar2.setLeftProgressDescription(df.format(min));
                    seekbar2.setRightProgressDescription(df.format(max));
                }
            }
        });

    }

}
