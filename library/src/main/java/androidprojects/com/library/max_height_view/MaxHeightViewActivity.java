package androidprojects.com.library.max_height_view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import androidprojects.com.library.R;

public class MaxHeightViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_max_height_view);
        Button button = (Button) this.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogMaxHeight dialogMaxHeight = new DialogMaxHeight(MaxHeightViewActivity.this);
                dialogMaxHeight.show();
            }
        });
    }
}
