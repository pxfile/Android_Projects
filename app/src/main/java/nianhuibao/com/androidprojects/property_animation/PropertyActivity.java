package nianhuibao.com.androidprojects.property_animation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import nianhuibao.com.androidprojects.R;

/**
 * Created by rookie on 2016/8/12.
 */
public class PropertyActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
        InitView();
    }

    private void InitView() {
        findViewById(R.id.property).setOnClickListener(this);
        findViewById(R.id.value).setOnClickListener(this);
        findViewById(R.id.demo).setOnClickListener(this);
        findViewById(R.id.shopcar_add_anim).setOnClickListener(this);
        findViewById(R.id.LayoutAnimations).setOnClickListener(this);
        findViewById(R.id.RevealAnimator).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.property:
                intent = new Intent(this, PropertyAnimationActivity.class);
                break;
            case R.id.value:
                intent = new Intent(this, ValueAnimationActivity.class);
                break;
            case R.id.demo:
                intent = new Intent(this, AliPayAnimActivity.class);
                break;
            case R.id.shopcar_add_anim:
                intent = new Intent(this, ShopCarAddAnimActivity.class);
                break;
            case R.id.LayoutAnimations:
                intent = new Intent(this, LayoutAnimationsActivity.class);
                break;
            case R.id.RevealAnimator:
                intent = new Intent(this, RevealAnimatorActivity.class);
                break;
            default:
                break;
        }
        startActivity(intent);
    }
}
