package com.sina.young.jiandandemo1.base;

import android.app.Application;

public class BaseApplication extends Application {
	public static BaseApplication application;

	@Override
	public void onCreate() {
		super.onCreate();
		application = this;
	}

	public static BaseApplication getInstance(){
		return application;
	}
}
