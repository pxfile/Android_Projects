package nianhuibao.com.androidprojects.sticky_decoration.listener;

import android.view.View;

/**
 * Created by gavin
 * Created date 17/5/25
 * 显示自定义View的Group监听
 */

public interface PowerGroupListener {

    String getGroupName(int position);

    View getGroupView(int position);
}
