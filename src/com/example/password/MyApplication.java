package com.example.password;

import com.example.password.pas.InitApp;

import android.app.Application;

public class MyApplication extends Application {
	

	@Override
	public void onCreate() {
		super.onCreate();
		InitApp.init(this);
	}

}