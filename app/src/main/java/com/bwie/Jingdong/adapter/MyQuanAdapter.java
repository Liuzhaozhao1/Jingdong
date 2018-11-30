package com.bwie.Jingdong.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.Jingdong.R;
import com.bwie.Jingdong.bean.Urls;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyQuanAdapter extends RecyclerView.Adapter<MyQuanAdapter.SubVIewHolder> {

    Context context;
    List<Urls.DataBean> urls;

    public MyQuanAdapter(Context context, List<Urls.DataBean> urls) {
        this.context = context;
        this.urls = urls;
    }

    @NonNull
    @Override
    public SubVIewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.review_layout,null);
        SubVIewHolder holder = new SubVIewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubVIewHolder holder, int position) {

        //对图片的展示
        String images = urls.get(position).getList().get(0).getImages();
        if(images !=null&&images.contains("|"))
        {
            String[] split = images.split("\\|");
            Picasso.with(context).load(split[0]).into(holder.img_lie);
        }
        else
        {
            Picasso.with(context).load(images).into(holder.img_lie);
        }

        //对文字的展示
        holder.text_show.setText(urls.get(position).getList().get(0).getTitle());
        //获取数据源中的条目信息，做及时的更新
        holder.check_box1.setChecked(urls.get(position).getChecked());
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    class SubVIewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final CheckBox check_box1;
        private final ImageView img_lie;
        private final TextView text_show;

        public SubVIewHolder(View itemView) {
            super(itemView);
            check_box1 = (CheckBox)itemView.findViewById(R.id.check_box1);
            img_lie = (ImageView)itemView.findViewById(R.id.img_lie);
            text_show = (TextView)itemView.findViewById(R.id.text_show);
            check_box1.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //把点击时的位置进行回转
            int layoutPosition = getLayoutPosition();
            //可以把当前条目的CheckBox对应勾选的状态进行返回
            boolean checked = check_box1.isChecked();
            onItemCheckedLisenter.onItemChecked(layoutPosition,checked);
        }
    }

    public interface OnItemCheckedLisenter
    {
        //用于反馈，recyclerview条目中是否选中，并且及时更新外部勾选状态
        void onItemChecked(int layoutPosition,boolean checked);
    }

    OnItemCheckedLisenter onItemCheckedLisenter;

    public void setOnItemCheckedLisenter(OnItemCheckedLisenter onItemCheckedLisenter) {
        this.onItemCheckedLisenter = onItemCheckedLisenter;
    }
}
