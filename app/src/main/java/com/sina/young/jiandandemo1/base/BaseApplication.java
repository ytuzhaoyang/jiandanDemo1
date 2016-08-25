package com.sina.young.jiandandemo1.base;

import android.app.Application;

import utils.ImageLoadProxy;

public class BaseApplication extends Application {

	public static BaseApplication application;

	@Override
	public void onCreate() {
		super.onCreate();
		application = this;
		//配置全局的ImageLoader对象
		ImageLoadProxy.initImageLoader(this);
	}

	public static BaseApplication getInstance(){
		return application;
	}

}
