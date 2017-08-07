package nianhuibao.com.androidprojects.titanic;

import android.app.Activity;
import android.os.Bundle;

import nianhuibao.com.androidprojects.R;

public class TitanicTextViewActivity extends Activity {

    private TitanicTextView mTitanicTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_titanic_text_view);
        mTitanicTextView = (TitanicTextView) findViewById(R.id.titanic_options);
        new Titanic().start(mTitanicTextView);
    }
}
