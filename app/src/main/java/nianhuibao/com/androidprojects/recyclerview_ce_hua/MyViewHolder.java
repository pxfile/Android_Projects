package nianhuibao.com.androidprojects.recyclerview_ce_hua;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import nianhuibao.com.androidprojects.R;

/**
 * Created by tangyangkai on 16/6/12.
 */
public class MyViewHolder extends RecyclerView.ViewHolder {


    public TextView txt;
    public CeHuaView ceHuaView;
    public LinearLayout layout;
    public MyViewHolder(View itemView) {
        super(itemView);
        txt= (TextView) itemView.findViewById(R.id.item_recycler_txt);
        ceHuaView= (CeHuaView) itemView.findViewById(R.id.item_cehua);
        layout= (LinearLayout) itemView.findViewById(R.id.item_recycler_ll);
    }
}
