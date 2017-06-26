package nianhuibao.com.androidprojects.recyclerview_list_anim;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nianhuibao.com.androidprojects.R;

/**
 * ***********************************************************************
 * Author:pengxiaofang
 * CreateData:2017-06-26 15:11
 * Version:xx
 * Description:xx
 * ***********************************************************************
 */
public class ListAnimAdapter extends RecyclerView.Adapter {
    private List<ListAnimBean> mListAnimBeanList;
    private Context mContext;
    private List<ImageView> imageViews = new ArrayList<>();
    private Handler handler;
    private Runnable runnable;

    public ListAnimAdapter(List<ListAnimBean> listAnimBeanList, Context context) {
        mListAnimBeanList = listAnimBeanList;
        mContext = context;
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < imageViews.size(); i++) {
                    if (imageViews.get(i).getVisibility() == View.VISIBLE) {
                        AnimationUtils.homeTabAnimation(imageViews.get(i));
                    }
                }
                handler.postDelayed(runnable, 3000);
            }
        };
        handler.post(runnable);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListAnimAdapterViewHolder viewHolder = new ListAnimAdapterViewHolder(View.inflate(mContext, R.layout.item_recyclerview_list_anim, null));
        imageViews.add(viewHolder.imageView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ListAnimAdapterViewHolder viewHolder = (ListAnimAdapterViewHolder) holder;
        viewHolder.textView.setText(mListAnimBeanList.get(position).content);
        viewHolder.imageView.setImageResource(mListAnimBeanList.get(position).have_gift ? R.drawable.icon_sign_gift : R.drawable.icon_sign_gift_got);
    }

    @Override
    public int getItemCount() {
        return null == mListAnimBeanList ? 0 : mListAnimBeanList.size();
    }

    public class ListAnimAdapterViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public ListAnimAdapterViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.listitem_sign_iv_gift);
            textView = (TextView) itemView.findViewById(R.id.listitem_sign_tv_gift);
        }
    }
}
