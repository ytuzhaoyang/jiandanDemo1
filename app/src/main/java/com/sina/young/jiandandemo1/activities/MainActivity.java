package com.sina.young.jiandandemo1.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;

import com.sina.young.jiandandemo1.R;
import com.sina.young.jiandandemo1.base.BaseActivity;
import com.sina.young.jiandandemo1.bean.NetWorkEvent;
import com.sina.young.jiandandemo1.fragments.MainMenuFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import utils.NetWorkUtils;
import utils.ShowToast;
import utils.ToastUtil;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private BroadcastReceiver mNetStateReceiver;
    private long exitTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };

        mActionBarDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);

        replaceFragment(R.id.drawer_container, new MainMenuFragment());
    }

    @Override
    protected void initData() {

        mNetStateReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                    if (NetWorkUtils.isNetWorkConnected(MainActivity.this)) {
                        EventBus.getDefault().post(new NetWorkEvent(NetWorkEvent.AVAILABLE));
                    } else {
                        EventBus.getDefault().post(NetWorkEvent.UNAVAILABLE);
                    }
                }
            }
        };

        //注册检测网络链接状态的光播
        registerReceiver(mNetStateReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mNetStateReceiver);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe
    public void onEvent(NetWorkEvent event){
        //网络不可用
        if (event.getType() == NetWorkEvent.UNAVAILABLE) {
            ToastUtil.show("网络不可用");
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.ACTION_DOWN && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (((System.currentTimeMillis() - exitTime) > 2000)) {
                ShowToast.Short("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

