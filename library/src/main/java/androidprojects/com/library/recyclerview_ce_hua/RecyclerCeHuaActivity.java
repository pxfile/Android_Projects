package androidprojects.com.library.recyclerview_ce_hua;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import androidprojects.com.library.R;


public class RecyclerCeHuaActivity extends AppCompatActivity {

    private List<Integer> lists = new ArrayList<>();
    private MyRecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_ce_hua);
        initData();
        initViews();
    }

    private void initViews() {
        recyclerView = (MyRecyclerView) findViewById(R.id.my_recycler);
        adapter = new RecyclerAdapter(getApplicationContext(), lists);
        manager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }

    private void initData() {
        for (int i = 1; i < 20; i++) {
            lists.add(i);
        }
    }
}
