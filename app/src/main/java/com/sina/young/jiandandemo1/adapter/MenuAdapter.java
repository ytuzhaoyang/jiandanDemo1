package com.sina.young.jiandandemo1.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by zhaoyang on 16/8/18.
 */
public class MenuAdapter {

    private static class MenuHolder extends RecyclerView.ViewHolder {

        public MenuHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

        }
    }
}
