package com.sina.young.jiandandemo1.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sina.young.jiandandemo1.R;
import com.sina.young.jiandandemo1.activities.MainActivity;
import com.sina.young.jiandandemo1.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainMenuFragment extends BaseFragment {

    private MainActivity mainActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof MainActivity) {
            mainActivity = (MainActivity) activity;
        } else {
            throw new IllegalArgumentException("The activity must be a MainActivity !");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


}
