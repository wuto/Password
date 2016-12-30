package com.example.password.pas;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.example.password.util.SharedPreferencesHelper;

public class PasswordUtil {
	// 1.������Ҫ������������
	// 2.�����������뿪��
	// 3.����������
	// ������������

	// �Ƿ�����������
	public static final String ISOPEN = "isopen"; // ֻ�ڽ�����ȫ����Ч

	public static final String ISNEED = "isneed"; // �Ƿ���Ҫ��������

	public static final String ISNEEDQUANJU = "isneedquanju";// ȫ���Ƿ���Ҫ��������

	// �������뱣��
	public static final String PASSWORD = "password";

	/**
	 * �ж���û��ԭ����
	 * 
	 * @return
	 */
	public static boolean getlock(Context context) {
		SharedPreferencesHelper sph = SharedPreferencesHelper
				.getInstance(context);
		return TextUtils.isEmpty(sph.getString(PASSWORD, "")) ? false : true;

	}

	/**
	 * �ж��Ƿ�����������
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
	 * ������������رտ���
	 * 
	 * @return
	 */
	public static void setisopen(Context context, boolean i) {
		SharedPreferencesHelper sph = SharedPreferencesHelper
				.getInstance(context);
		sph.putBoolean(ISOPEN, i);
	}

	/**
	 * �ж��Ƿ���������������
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
	 * �������͵��õķ���
	 * 
	 * @param context
	 * @param intent
	 * @param isneed
	 *            �Ƿ���Ҫ�����������
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
	 * �������͵��õķ��� ,��Ҫ��intent�д���isneed����ֵ��Ĭ��Ϊtrue���������������
	 * 
	 * @param context
	 * @param intent
	 */
	public static void startActivity(Context context, Intent intent) {
		boolean isneed = intent.getBooleanExtra(ISNEED, true);//
		// �Ƿ���Ҫ�����������
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
	 * ɾ����������
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
	 * ������������
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
	 * �õ���������
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
