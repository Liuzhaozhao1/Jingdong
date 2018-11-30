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
import com.bwie.Jingdong.bean.Users;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MyUserAdapter extends RecyclerView.Adapter<MyUserAdapter.SubviewHolder> {

    Context context;
    ArrayList<Users.DataBean> users;

    public MyUserAdapter(Context context, ArrayList<Users.DataBean> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public SubviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        SubviewHolder holder = new SubviewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubviewHolder holder, int position) {

        holder.text_1.setText(users.get(position).getName());
        Picasso.with(context).load(users.get(position).getIcon()).into(holder.img_view1);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class SubviewHolder extends RecyclerView.ViewHolder {
        private final ImageView img_view1;
        private final TextView text_1;

        public SubviewHolder(View itemView) {
            super(itemView);
            img_view1 = (ImageView)itemView.findViewById(R.id.img_view1);
            text_1 = (TextView)itemView.findViewById(R.id.text_1);
        }
    }
}
