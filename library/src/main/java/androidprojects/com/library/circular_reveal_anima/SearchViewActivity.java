package androidprojects.com.library.circular_reveal_anima;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import androidprojects.com.library.R;


public class SearchViewActivity extends Activity {
    private Toolbar mToolbar;
    private RelativeLayout mViewSearch;
    private ImageView mIvSearchBack;
    private EditText mEtSearch;
    private RecyclerView mRecycleview;


    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        initView();
        initResultItem();
        initToolbar();
        initListener();
    }

    private void initResultItem() {
        ArrayList<String> list = new ArrayList<>();
        list.add("优酷");
        list.add("土豆");
        list.add("爱奇艺");
        list.add("哔哩哔哩");
        list.add("youtube");
        list.add("斗鱼");
        list.add("熊猫");
        RvAdapter adapter = new RvAdapter(list, new RvAdapter.IListener() {
            @Override
            public void normalItemClick(String s) {
                Toast.makeText(SearchViewActivity.this, s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void clearItemClick() {
                Toast.makeText(SearchViewActivity.this, "清除历史记录", Toast.LENGTH_SHORT).show();
            }
        });
        mRecycleview.setAdapter(adapter);
        mRecycleview.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();
    }

    private void initToolbar() {
        mToolbar.inflateMenu(R.menu.menu_main);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItem = item.getItemId();
                if (menuItem == R.id.action_search) {
                    SearchViewUtils.handleToolBar(getApplicationContext(), mViewSearch, mEtSearch);
                }
                return false;
            }
        });
    }

    private void initListener() {
        mIvSearchBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchViewUtils.handleToolBar(getApplicationContext(), mViewSearch, mEtSearch);
            }
        });
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mViewSearch = (RelativeLayout) findViewById(R.id.search_rl);
        mIvSearchBack = (ImageView) findViewById(R.id.iv_search_back);
        mEtSearch = (EditText) findViewById(R.id.et_search);
        mRecycleview = (RecyclerView) findViewById(R.id.recycleview);
    }
}
