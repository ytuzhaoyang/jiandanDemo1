package com.sina.young.jiandandemo1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.sina.young.jiandandemo1.R;
import com.sina.young.jiandandemo1.bean.NewsBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import utils.ImageLoadProxy;

/**
 * Created by zhaoyang on 16/8/22.
 */
public class RefreshNewsAdapter extends RecyclerView.Adapter<RefreshNewsAdapter.NewsHolder>{

    private Context mContext;
    private List<NewsBean> mDatas;
    private int page;

    private DisplayImageOptions mOptions;

    public RefreshNewsAdapter(Context context, List<NewsBean> mDatas) {
        mContext = context;
        this.mDatas = mDatas;
        mOptions = ImageLoadProxy.getOptions4PictureList(R.mipmap.ic_loading_small);
    }

    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_news_layout, parent, false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsHolder holder, int position) {

        NewsBean bean = mDatas.get(position);
        //加载图片
        ImageLoadProxy.displayImage(bean.getCustom_fields().getThumb_c().get(0), holder.newsIcon, mOptions);
        holder.newsTitle.setText(bean.getTitle());
        holder.newsInfo.setText(bean.getAuthor().getName() + "@" + bean.getTags().get(0).getTitle());
        holder.newsViews.setText(bean.getComment_count() + "个评论");
    }

    @Override
    public int getItemCount() {
        if (mDatas != null) {
            return mDatas.size();
        }
        return 0;
    }

    public class NewsHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_news_title)
        TextView newsTitle;
        @BindView(R.id.tv_news_info)
        TextView newsInfo;
        @BindView(R.id.tv_news_views)
        TextView newsViews;
        @BindView(R.id.iv_news_img)
        ImageView newsIcon;

        public NewsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
