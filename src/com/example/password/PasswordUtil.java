package com.example.password;

import android.content.Context;
import android.text.TextUtils;

import com.example.password.util.SharedPreferencesHelper;

public class PasswordUtil {
	//1.界面需要开启手势密码
	//2.设置手势密码开启
	//3.有手势密码
	//开启手势密码

	/**
	 * 判断有没有原密码
	 * 
	 * @return
	 */
	public static boolean getlock(Context context) {
		SharedPreferencesHelper sph = SharedPreferencesHelper
				.getInstance(context);
		return TextUtils.isEmpty(sph.getString("password", ""))?false:true;

	}
	
	/**
	 * 判断是否开启手势密码
	 * 
	 * @return
	 */
	public static boolean getisopen(Context context) {
		SharedPreferencesHelper sph = SharedPreferencesHelper
				.getInstance(context);
			return sph.getBoolean("isopen", true);
	}
	
	
	/**
	 * 判断是否进入手势密码界面
	 * 
	 * @return
	 */
	public static boolean isgoin(Context context) {
		if (getisopen(context)&&getlock(context)) {
			return true;
		}else{
			return false;
		}
	}
	
	
	
}
