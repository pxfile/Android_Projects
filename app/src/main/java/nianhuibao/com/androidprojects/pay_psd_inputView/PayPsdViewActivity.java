package nianhuibao.com.androidprojects.pay_psd_inputView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import nianhuibao.com.androidprojects.R;

public class PayPsdViewActivity extends Activity {
    private PayPsdInputView passwordInputView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_psd_view);

        passwordInputView = (PayPsdInputView) findViewById(R.id.password);

        passwordInputView.setComparePassword("123456", new PayPsdInputView.onPasswordListener() {
            @Override
            public void onDifference() {
                // TODO: 2017/5/7   和上次输入的密码不一致  做相应的业务逻辑处理
                Toast.makeText(PayPsdViewActivity.this, "两次密码输入不同", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onEqual(String psd) {
                // TODO: 2017/5/7 两次输入密码相同，那就去进行支付楼
                Toast.makeText(PayPsdViewActivity.this, "密码相同" + psd, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
