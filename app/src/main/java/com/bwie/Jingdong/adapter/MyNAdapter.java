package com.bwie.Jingdong.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.Jingdong.R;
import com.bwie.Jingdong.bean.News1;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyNAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<News1.TuijianBean.ListBean> list1;

    public MyNAdapter(Context context, ArrayList<News1.TuijianBean.ListBean> list1) {
        this.context = context;
        this.list1 = list1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_layout2, parent, false);
        SubViewHolder holder = new SubViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        String images = list1.get(position).getImages();
        if(images.contains("|"))
        {
            images = images.substring(0,images.indexOf("|"));
        }
        else
        {
            images = list1.get(position).getImages();
        }

        if(holder instanceof SubViewHolder)
        {
            ((SubViewHolder) holder).text1.setText(list1.get(position).getTitle());
            ((SubViewHolder) holder).text2.setText(Integer.toString((int)list1.get(position).getPrice()));
            Picasso.with(context).load(images).into(((SubViewHolder) holder).img1);
        }
    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    private class SubViewHolder extends RecyclerView.ViewHolder {
        private final ImageView img1;
        private final TextView text1;
        private final TextView text2;

        public SubViewHolder(View inflate) {
            super(inflate);
            img1 = (ImageView)inflate.findViewById(R.id.img1);
            text1 = (TextView)inflate.findViewById(R.id.text1);
            text2 = (TextView)inflate.findViewById(R.id.text2);
        }
    }
}
