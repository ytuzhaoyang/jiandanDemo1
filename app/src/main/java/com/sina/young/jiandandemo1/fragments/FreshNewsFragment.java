package com.sina.young.jiandandemo1.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sina.young.jiandandemo1.R;
import com.sina.young.jiandandemo1.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FreshNewsFragment extends BaseFragment {

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fresh_news, container, false);
    }

}
