package com.sina.young.jiandandemo1.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.sina.young.jiandandemo1.BuildConfig;
import com.sina.young.jiandandemo1.R;

import utils.ActivityManager;
import utils.http.HttpManager;
import utils.logger.LogLevel;
import utils.logger.Logger;

public abstract class BaseActivity extends AppCompatActivity {

    private Context mContext;
    private ActivityManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        if (BuildConfig.DEBUG) {
            Logger.init(getClass().getSimpleName()).setLogLevel(LogLevel.FULL).hideThreadInfo();
        }else {
            Logger.init(getClass().getSimpleName()).setLogLevel(LogLevel.NONE).hideThreadInfo();
        }

        mManager = ActivityManager.getInstance();
        mManager.addActivity(this);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_none, R.anim.trans_center_2_right);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mManager.finishActivity(this);
        HttpManager.getInstance().cancelAll(this);
    }

    protected abstract void initView();

    protected abstract void initData();

    public void replaceFragment(int id_content, Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(id_content, fragment);
        transaction.commit();
    }

    public void executeRequest(Request<?> request) {
        HttpManager.getInstance().doRequest(request);
    }
}
