package com.sina.young.jiandandemo1.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.sina.young.jiandandemo1.R;
import com.sina.young.jiandandemo1.adapter.RefreshNewsAdapter;
import com.sina.young.jiandandemo1.base.BaseFragment;
import com.sina.young.jiandandemo1.bean.NewsBean;
import com.sina.young.jiandandemo1.bean.RefreshNewsBean;
import com.sina.young.jiandandemo1.view.AutoLoadRecyclerView;
import com.victor.loading.rotate.RotateLoading;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import utils.NetWorkUtils;
import utils.http.JavaObjRequest;
import utils.http.UrlManager;

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
    private LinearLayoutManager mLayoutManager;
    private int page;
    private List<NewsBean> mNewsBeans;

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
        if (getArguments() != null) {
            isLarge = getArguments().getBoolean(MODE, true);
        }

        mNewsBeans = new ArrayList<>();
        getData();
    }

    @Override
    public void onResume() {
        super.onResume();
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

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        if (mNewsAdapter != null)
            mRecyclerView.setAdapter(mNewsAdapter);
        mLoading.start();
    }

    public void getData() {
        if (NetWorkUtils.isNetWorkConnected(getActivity())) {
            JavaObjRequest objRequest = new JavaObjRequest<RefreshNewsBean>(Request.Method.GET, UrlManager.URL_FRESH_NEWS + page, "", new Response.Listener<RefreshNewsBean>() {
                @Override
                public void onResponse(RefreshNewsBean response) {
                    if (response != null) {
                        mNewsBeans.addAll(response.getPosts());
                        mNewsAdapter = new RefreshNewsAdapter(getActivity(), mNewsBeans);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }, RefreshNewsBean.class, false);
            objRequest.doRequest();
        }
    }
}
