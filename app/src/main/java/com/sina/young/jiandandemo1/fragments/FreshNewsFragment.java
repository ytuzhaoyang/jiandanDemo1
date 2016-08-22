package com.sina.young.jiandandemo1.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sina.young.jiandandemo1.R;
import com.sina.young.jiandandemo1.adapter.RefreshNewsAdapter;
import com.sina.young.jiandandemo1.base.BaseFragment;
import com.sina.young.jiandandemo1.view.AutoLoadRecyclerView;
import com.victor.loading.rotate.RotateLoading;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FreshNewsFragment extends BaseFragment {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.recycler_view)
    AutoLoadRecyclerView mRecyclerView;
    @BindView(R.id.loading)
    RotateLoading mLoading;

    private RefreshNewsAdapter mNewsAdapter;

    //是否为大图模式
   public static final String MODE = "isBigMode";
    private boolean isLarge;

    public static FreshNewsFragment newInstance(boolean isLarge) {
        
        Bundle args = new Bundle();
        args.putBoolean(MODE, isLarge);
        FreshNewsFragment fragment = new FreshNewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            isLarge = getArguments().getBoolean(MODE, true);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fresh_news, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mNewsAdapter = new RefreshNewsAdapter(getActivity());
        mNewsAdapter.loadFirst();
        mLoading.start();
    }
}
