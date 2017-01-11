package com.wuto.password.pas;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.wuto.password.MyApplication;
import com.wuto.password.util.SharedPreferencesHelper;

public class InitApp {

	private static int count = 0;

	/**
	 * 当用户没有使用MyApplication的registerActivityLifecycleCallbacks回调时，
	 * 在onCreate中InitApp.init(this);
	 * 
	 * @param myApplication
	 */
	public static void init(MyApplication myApplication) {
		final Context mContext = myApplication.getApplicationContext();
		myApplication
				.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
					@Override
					public void onActivityStopped(Activity activity) {
						Log.v("viclee", activity + "onActivityStopped");
						count--;
						if (count == 0) {
							Log.v("viclee",
									">>>>>>>>>>>>>>>>>>>切到后台  lifecycle");
						}
					}

					@Override
					public void onActivityStarted(Activity activity) {
						if (count == 0) {
							if (!activity.getComponentName().getClassName()
									.equals(PasswordActivity.class.getName())) {
								if (PasswordUtil.isgoin(mContext)) {// 开启手势密码并且有密码
									Intent intent = new Intent(mContext,
											PasswordActivity.class);
									intent.putExtra(FlagType.FLAG_TYPE,
											FlagType.PASSWORD_QUAN);
									intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
									mContext.startActivity(intent);
								}
							}

						}
						count++;
					}

					@Override
					public void onActivitySaveInstanceState(Activity activity,
							Bundle outState) {
					}

					@Override
					public void onActivityResumed(Activity activity) {
					}

					@Override
					public void onActivityPaused(Activity activity) {
					}

					@Override
					public void onActivityDestroyed(Activity activity) {
					}

					@Override
					public void onActivityCreated(Activity activity,
							Bundle savedInstanceState) {
					}
				});
	}

	/**
	 * 
	 * 当用户使用了MyApplication的registerActivityLifecycleCallbacks回调时，
	 * 在onActivityStarted回调中添加此方法
	 * 
	 * @param myApplication
	 * @param activity
	 */
	public static void onActivityStarted(MyApplication myApplication,
			Activity activity) {

		final Context mContext = myApplication.getApplicationContext();

		if (count == 0) {
			if (!activity.getComponentName().getClassName()
					.equals(PasswordActivity.class.getName())) {
				if (PasswordUtil.isgoin(mContext)) {// 开启手势密码并且有密码
					Intent intent = new Intent(mContext, PasswordActivity.class);
					intent.putExtra(FlagType.FLAG_TYPE, FlagType.PASSWORD_QUAN);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					mContext.startActivity(intent);
				}
			}

		}
		count++;
	}

	/**
	 * 当用户使用了MyApplication的registerActivityLifecycleCallbacks回调时，
	 * 在onActivityStopped回调中添加此方法
	 * 
	 * @param myApplication
	 * @param activity
	 */
	public static void onActivityStopped(MyApplication myApplication,
			Activity activity) {

		count--;
		if (count == 0) {
			Log.v("viclee", ">>>>>>>>>>>>>>>>>>>切到后台  lifecycle");
		}
	}

}
