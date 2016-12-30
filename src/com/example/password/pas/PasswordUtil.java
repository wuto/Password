package com.example.password.pas;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.example.password.util.SharedPreferencesHelper;

public class PasswordUtil {
	// 1.界面需要开启手势密码
	// 2.设置手势密码开启
	// 3.有手势密码
	// 开启手势密码

	// 是否开启手势密码
	public static final String ISOPEN = "isopen"; // 只在解锁和全局有效

	public static final String ISNEED = "isneed"; // 是否需要解锁界面

	public static final String ISNEEDQUANJU = "isneedquanju";// 全局是否需要解锁界面

	// 手势密码保存
	public static final String PASSWORD = "password";

	/**
	 * 判断有没有原密码
	 * 
	 * @return
	 */
	public static boolean getlock(Context context) {
		SharedPreferencesHelper sph = SharedPreferencesHelper
				.getInstance(context);
		return TextUtils.isEmpty(sph.getString(PASSWORD, "")) ? false : true;

	}

	/**
	 * 判断是否开启手势密码
	 * 
	 * @return
	 */
	public static boolean getisopen(Context context) {
		SharedPreferencesHelper sph = SharedPreferencesHelper
				.getInstance(context);
		return sph.getBoolean(ISOPEN, false);
	}

	/**
	 * 
	 * 设置手势密码关闭开启
	 * 
	 * @return
	 */
	public static void setisopen(Context context, boolean i) {
		SharedPreferencesHelper sph = SharedPreferencesHelper
				.getInstance(context);
		sph.putBoolean(ISOPEN, i);
	}

	/**
	 * 判断是否进入手势密码界面
	 * 
	 * @return
	 */
	public static boolean isgoin(Context context) {
		if (getisopen(context) && getlock(context)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * 解锁类型调用的方法
	 * 
	 * @param context
	 * @param intent
	 * @param isneed
	 *            是否需要进入解锁界面
	 */
	public static void startActivity(Context context, Intent intent,
			boolean isneed) {
		if (isneed && PasswordUtil.isgoin(context)) {
			Intent _intent = new Intent(context, PasswordActivity.class);
			_intent.putExtra("intentComponent", intent.getComponent()
					.getClassName());
			intent.putExtra(FlagType.FLAG_TYPE, FlagType.PASSWORD_UNLOCK);
			_intent.putExtras(intent);
			context.startActivity(_intent);
		} else {
			context.startActivity(intent);
		}
	}

	/**
	 * 
	 * 解锁类型调用的方法 ,需要在intent中传入isneed布尔值，默认为true，即开启密码界面
	 * 
	 * @param context
	 * @param intent
	 */
	public static void startActivity(Context context, Intent intent) {
		boolean isneed = intent.getBooleanExtra(ISNEED, true);//
		// 是否需要调到密码界面
		if (isneed && PasswordUtil.isgoin(context)) {
			Intent _intent = new Intent(context, PasswordActivity.class);
			_intent.putExtra("intentComponent", intent.getComponent()
					.getClassName());
			intent.putExtra(FlagType.FLAG_TYPE, FlagType.PASSWORD_UNLOCK);
			_intent.putExtras(intent);
			context.startActivity(_intent);
		} else {
			context.startActivity(intent);
		}
	}

	/**
	 * 删除手势密码
	 * 
	 * @param context
	 */
	public static void deletePassword(Context context) {
		SharedPreferencesHelper sph = SharedPreferencesHelper
				.getInstance(context);
		sph.putString(PASSWORD, "");
	}

	/**
	 * 
	 * 设置手势密码
	 * 
	 * @param context
	 * @param pass
	 */
	public static void setPassword(Context context, String pass) {
		SharedPreferencesHelper sph = SharedPreferencesHelper
				.getInstance(context);
		sph.putString(PASSWORD, pass);
	}

	/**
	 * 得到手势密码
	 * 
	 * @param context
	 * @return
	 */
	public static String getPassword(Context context) {
		SharedPreferencesHelper sph = SharedPreferencesHelper
				.getInstance(context);
		return sph.getString(PASSWORD, "");
	}

}
