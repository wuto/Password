package com.example.password;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.example.password.component.LocusPassWordView;
import com.example.password.component.LocusPassWordView.OnCompleteListener;
import com.example.password.util.Md5Utils;
import com.example.password.util.SharedPreferencesHelper;

/**
 * 
 * 
 * 设置密码 ： 第一次进入直接设置两次密码，相同设置成功 以后进入，先验证一次原密码，再设置两次密码，相同设置成功
 * 
 * @author fantasee
 * 
 */
public class PasswordSetting extends PassWordBaseActivity {

	private LocusPassWordView mPwdView;
	private Context mContext;
	private TextView mTitle;

	private String pswone = "";

	@Override
	protected void init() {
		setContentView(R.layout.setting);
	}

	@Override
	protected void findView() {
		mContext = getApplicationContext();
		mPwdView = (LocusPassWordView) this.findViewById(R.id.mPassWordView);
		mTitle = (TextView) this.findViewById(R.id.multi_tv_token_time_hint);

		mTitle.setText("请输入密码");
	}

	@Override
	protected void setListener() {
		mPwdView.setOnCompleteListener(new OnCompleteListener() {
			@Override
			public void onComplete(String mPassword) {
				SharedPreferencesHelper sph = SharedPreferencesHelper
						.getInstance(getApplicationContext());
				Md5Utils md5 = new Md5Utils();

				if (TextUtils.isEmpty(pswone)) {
					pswone = md5.toMd5(mPassword, "");
				} else {
					if (pswone.equals(md5.toMd5(mPassword, ""))) {
						// 设置成功
						Toast.makeText(mContext,
								mContext.getString(R.string.pwd_setted),
								Toast.LENGTH_SHORT).show();
						sph.putString("password", md5.toMd5(mPassword, ""));
						mTitle.setText("设置成功");
						finish();
					} else {
						mTitle.setText("请输入与第一次相同的密码");
						mPwdView.markError();
					}

				}
				mPwdView.clearPassword();
				mTitle.setText("请再次输入密码");
				return;
			}

		});
	}

}
