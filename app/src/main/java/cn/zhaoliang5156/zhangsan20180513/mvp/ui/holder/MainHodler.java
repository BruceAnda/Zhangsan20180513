package cn.zhaoliang5156.zhangsan20180513.mvp.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.zhaoliang5156.zhangsan20180513.R;

public class MainHodler extends RecyclerView.ViewHolder {

    public ImageView ivIcon;
    public TextView tvTitle;
    public TextView tvDestance;

    public MainHodler(View itemView) {
        super(itemView);

        ivIcon = itemView.findViewById(R.id.iv_icon);
        tvTitle = itemView.findViewById(R.id.tv_title);
        tvDestance = itemView.findViewById(R.id.tv_distance);
    }
}
