package com.sina.young.jiandandemo1.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

import com.sina.young.jiandandemo1.R;
import com.sina.young.jiandandemo1.base.BaseActivity;
import com.sina.young.jiandandemo1.bean.NetWorkEvent;
import com.sina.young.jiandandemo1.fragments.FreshNewsFragment;
import com.sina.young.jiandandemo1.fragments.JokeFragment;
import com.sina.young.jiandandemo1.fragments.PictureFragment;
import com.sina.young.jiandandemo1.fragments.SettingFragment;
import com.sina.young.jiandandemo1.fragments.SisterFragment;
import com.sina.young.jiandandemo1.fragments.VideoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import utils.NetWorkUtils;
import utils.ShowToast;
import utils.ToastUtil;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.menu)
    NavigationView mNavigationView;


    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private BroadcastReceiver mNetStateReceiver;
    private long exitTime;
    private SharedPreferences mPreferences;

    private Fragment mCurrentFragment = null;

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

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mNavigationView.setNavigationItemSelectedListener(this);

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

        mCurrentFragment = new FreshNewsFragment();
        replaceFragment(R.id.frame_container, mCurrentFragment);
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
    protected void onResume() {
        super.onResume();
        Log.d("zhaoyang", "onResume: ------------");
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //toggle控制DeawLayout
        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_news: //新鲜事
                mCurrentFragment = FreshNewsFragment.newInstance(false);
                replaceFragment(R.id.frame_container, mCurrentFragment);
                break;
            case R.id.item_picture:
                mCurrentFragment = new PictureFragment();
                replaceFragment(R.id.frame_container, mCurrentFragment);
                break;
            case R.id.item_joke:
                mCurrentFragment = new JokeFragment();
                replaceFragment(R.id.frame_container, mCurrentFragment);
                break;
            case R.id.item_sister:
                mCurrentFragment = new SisterFragment();
                replaceFragment(R.id.frame_container, mCurrentFragment);
                break;
            case R.id.item_video:
                mCurrentFragment = new VideoFragment();
                replaceFragment(R.id.frame_container, mCurrentFragment);
                break;
            case R.id.item_setting:
                Intent intent = new Intent(this, SettingActivity.class);
                startActivityForResult(intent, 110);
            default:
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 110 && resultCode == RESULT_OK){
           refreshMenu();
            if (mCurrentFragment instanceof FreshNewsFragment) {
                refreshData();
            }
        }
    }

    private void refreshData() {
        if (mPreferences != null) {
            mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        }

        ShowToast.Short((mPreferences.getBoolean(SettingFragment.ENABLE_FRESH_BIG, false))?"大图模式":"小图模式");
    }

    private void refreshMenu() {
        if (mPreferences != null) {
            mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        }
        mNavigationView.getMenu().findItem(R.id.item_sister).setVisible(mPreferences.getBoolean(SettingFragment.ENABLE_SISTER, false));
    }
}

