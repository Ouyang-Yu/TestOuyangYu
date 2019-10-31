package com.example.recyclerview;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

/**
 * Created by OuyangYu on 2019/10/30 10:50
 */
public class MyApplication extends Application {
	private static Context context;

	@Override
	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();
	}

	public static Context getContext() {
		return context;
	}

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(this);
	}
}
