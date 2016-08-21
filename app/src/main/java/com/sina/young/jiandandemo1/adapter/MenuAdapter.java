package com.sina.young.jiandandemo1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sina.young.jiandandemo1.R;
import com.sina.young.jiandandemo1.bean.MenuItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhaoyang on 16/8/18.
 */
public class MenuAdapter  extends RecyclerView.Adapter<MenuAdapter.MenuHolder>{

    private Context mContext;
    private List<MenuItem> mItemList;

    public MenuAdapter(Context context, List<MenuItem> itemList) {
        mContext = context;
        mItemList = itemList;
    }

    @Override
    public MenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_menu, parent);
        return new MenuHolder(view);
    }

    @Override
    public void onBindViewHolder(MenuHolder holder, int position) {
        MenuItem menuItem = mItemList.get(position);
        holder.img_menu.setImageResource(menuItem.getResourceId());
        holder.tv_title.setText(menuItem.getTitle());
        holder.rl_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        if (mItemList != null) {
            return mItemList.size();
        }
        return 0;
    }

    public class MenuHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_menu)
         ImageView img_menu;
        @BindView(R.id.tv_title)
         TextView tv_title;
        @BindView(R.id.rl_container)
         RelativeLayout rl_container;

        public MenuHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
