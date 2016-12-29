package com.example.password;

import android.app.Application;

public class MyApplication extends Application {
	

	@Override
	public void onCreate() {
		super.onCreate();
		InitApp.init(this);
	}

}