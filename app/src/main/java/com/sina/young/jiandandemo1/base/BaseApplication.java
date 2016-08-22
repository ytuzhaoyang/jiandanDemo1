package com.sina.young.jiandandemo1.base;

import android.app.Application;
import android.content.Context;

import utils.ImageLoadProxy;

public class BaseApplication extends Application {

	private static Context mContext;

	public static BaseApplication application;

	@Override
	public void onCreate() {
		super.onCreate();
		mContext = this;
		application = this;

		//配置全局的ImageLoader对象
		ImageLoadProxy.initImageLoader(this);
	}

	public static BaseApplication getInstance(){
		return application;
	}

	public static Context getContext() {
		return mContext;
	}
}
