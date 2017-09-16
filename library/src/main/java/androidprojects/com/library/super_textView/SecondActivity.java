/*
 * Copyright (C) 2017 CoorChice <icechen_@outlook.com>
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 * <p>
 * Last modified 17-8-2 上午11:08
 */

package androidprojects.com.library.super_textView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidprojects.com.library.R;
import androidprojects.com.library.super_textView.adjuster.RippleAdjuster;

public class SecondActivity extends AppCompatActivity {

    private SuperTextView btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btn = (SuperTextView) findViewById(R.id.btn);

//    btn.setAutoAdjust(true);
        btn.setAdjuster(new RippleAdjuster(getResources().getColor(R.color.opacity_5_a58fed)));

        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(SecondActivity.this, "onTouch", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SecondActivity.this, "onClick", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
