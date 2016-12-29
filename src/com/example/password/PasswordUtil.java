package com.example.password;

import android.content.Context;
import android.text.TextUtils;

import com.example.password.util.SharedPreferencesHelper;

public class PasswordUtil {
	//1.������Ҫ������������
	//2.�����������뿪��
	//3.����������
	//������������

	/**
	 * �ж���û��ԭ����
	 * 
	 * @return
	 */
	public static boolean getlock(Context context) {
		SharedPreferencesHelper sph = SharedPreferencesHelper
				.getInstance(context);
		return TextUtils.isEmpty(sph.getString("password", ""))?false:true;

	}
	
	/**
	 * �ж��Ƿ�����������
	 * 
	 * @return
	 */
	public static boolean getisopen(Context context) {
		SharedPreferencesHelper sph = SharedPreferencesHelper
				.getInstance(context);
			return sph.getBoolean("isopen", true);
	}
	
	
	/**
	 * �ж��Ƿ���������������
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
