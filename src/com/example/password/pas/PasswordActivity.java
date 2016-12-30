package com.example.password.pas;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.password.R;
import com.example.password.R.id;
import com.example.password.R.layout;
import com.example.password.R.string;
import com.example.password.component.LocusPassWordView;
import com.example.password.component.LocusPassWordView.OnCompleteListener;
import com.example.password.util.Md5Utils;
import com.example.password.util.SharedPreferencesHelper;

/**
 * 
 * 绘制密码界面
 * 
 * 
 */
public class PasswordActivity extends PassWordBaseActivity {

	private LocusPassWordView mPwdView;
	private Context mContext;
	private TextView mTitle;
	private TextView mForgotPassword;

	private String gointent;
	private int flag;

	private Intent mIntent;

	@Override
	protected void init() {
		setContentView(R.layout.activity_password);
	}

	@Override
	protected void findView() {
		mIntent = getIntent();
		gointent = mIntent.getStringExtra("intentComponent");
		flag = mIntent.getIntExtra(FlagType.FLAG_TYPE, -1);

		mContext = getApplicationContext();
		mPwdView = (LocusPassWordView) this.findViewById(R.id.mPassWordView);
		mTitle = (TextView) this.findViewById(R.id.multi_tv_token_time_hint);
		mForgotPassword = (TextView) this.findViewById(R.id.mForgotPassword);
	}

	@Override
	protected void setListener() {
		mForgotPassword.setOnClickListener(this);

		mPwdView.setOnCompleteListener(new OnCompleteListener() {
			@Override
			public void onComplete(String mPassword) {

				String pwd = PasswordUtil.getPassword(PasswordActivity.this);
				Md5Utils md5 = new Md5Utils();
				boolean passed = false;

				String encodedPwd = md5.toMd5(mPassword, "");
				if (encodedPwd.equals(pwd)) {
					passed = true;
				} else {
					mPwdView.markError();
				}

				if (passed) {
					Toast.makeText(mContext,
							mContext.getString(R.string.pwd_correct),
							Toast.LENGTH_SHORT).show();

					switch (flag) {
					case FlagType.PASSWORD_DELETE: // 删除手势密码
						PasswordUtil.deletePassword(PasswordActivity.this);
						Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT)
								.show();
						PasswordActivity.this.finish();
						break;

					case FlagType.PASSWORD_UNLOCK:// 解锁
						try {
							Intent _intent = new Intent(PasswordActivity.this,
									Class.forName(gointent));
							_intent.putExtras(mIntent);
							startActivity(_intent);
							PasswordActivity.this.finish();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
						break;
					case FlagType.PASSWORD_MODIFY:
						try {
							Intent _intent = new Intent(PasswordActivity.this,
									Class.forName(gointent));
							startActivity(_intent);
							PasswordActivity.this.finish();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
						break;

					case FlagType.PASSWORD_QUAN:// 全局
						PasswordActivity.this.finish();
						break;

					case FlagType.PASSWORD_CREAT:

						break;

					default:
						break;
					}
				}
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			switch (flag) {
			case FlagType.PASSWORD_QUAN:
				Intent home = new Intent(Intent.ACTION_MAIN);
				home.addCategory(Intent.CATEGORY_HOME);
				startActivity(home);
				break;

			default:
				break;
			}
		}

		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.mForgotPassword:// 忘记密码
			Intent intent = new Intent(PasswordActivity.this,
					ForgotPassword.class);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}

	}

}
