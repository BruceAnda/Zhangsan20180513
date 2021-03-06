package cn.zhaoliang5156.zhangsan20180513.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import cn.zhaoliang5156.zhangsan20180513.R;
import cn.zhaoliang5156.zhangsan20180513.mvp.ui.bean.MainBean;
import cn.zhaoliang5156.zhangsan20180513.mvp.ui.holder.MainHodler;

public class MainAdapter extends RecyclerView.Adapter<MainHodler> {

    private Context context;
    private List<MainBean.Pois> poisBeans;

    public MainAdapter(Context context, List<MainBean.Pois> poisBeans) {
        this.context = context;
        this.poisBeans = poisBeans;
    }

    @NonNull
    @Override
    public MainHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainHodler(LayoutInflater.from(context).inflate(R.layout.main_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainHodler holder, int position) {
        holder.tvTitle.setText(poisBeans.get(position).getName());
        holder.tvDestance.setText(poisBeans.get(position).getDistance() + "m");
        List<MainBean.Pois.Photo> photos = poisBeans.get(position).getPhotos();
        if (photos != null && photos.size() > 0) {
            Glide.with(context)
                    .load(photos.get(0).getUrl())
                    .into(holder.ivIcon);
        }
    }

    @Override
    public int getItemCount() {
        return poisBeans.size();
    }
}
