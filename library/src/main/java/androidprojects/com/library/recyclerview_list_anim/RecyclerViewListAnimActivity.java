package androidprojects.com.library.recyclerview_list_anim;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import androidprojects.com.library.R;


public class RecyclerViewListAnimActivity extends Activity {

    private RecyclerView mRecyclerView;
    private ListAnimAdapter mAdapter;
    private List<ListAnimBean> mListAnimBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_list_anim);
        initData();
        initView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(null);
        mAdapter = new ListAnimAdapter(mListAnimBeanList, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        mListAnimBeanList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            ListAnimBean animBean = new ListAnimBean();
            animBean.have_gift = i % 2 == 0;
            animBean.content = "list item anim " + i;
            mListAnimBeanList.add(animBean);
        }
    }
}
