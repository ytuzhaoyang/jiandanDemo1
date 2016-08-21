package com.sina.young.jiandandemo1.base;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.sina.young.jiandandemo1.BuildConfig;

import utils.logger.LogLevel;
import utils.logger.Logger;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (BuildConfig.DEBUG) {
            Logger.init(getClass().getSimpleName()).setLogLevel(LogLevel.FULL).hideThreadInfo();
        } else {
            Logger.init(getClass().getSimpleName()).setLogLevel(LogLevel.NONE).hideThreadInfo();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消网络
//        HttpManager.getInstance().cancelAll(this);
    }
}
