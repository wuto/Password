package com.wuto.password.pas;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.wuto.password.R;
import com.wuto.password.R.id;
import com.wuto.password.R.layout;
import com.wuto.password.R.string;
import com.wuto.password.component.LocusPassWordView;
import com.wuto.password.component.LocusPassWordView.OnCompleteListener;
import com.wuto.password.util.Md5Utils;
import com.wuto.password.util.SharedPreferencesHelper;

/**
 * 
 * �����������
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
	protected void onResume() {
		super.onResume();
		mTitle.setTextColor(0xffffffff);
		mTitle.setText("�������������");

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

					switch (flag) {
					case FlagType.PASSWORD_DELETE: // ɾ����������
						PasswordUtil.deletePassword(PasswordActivity.this);
						setResult(200);
						PasswordActivity.this.finish();

						break;

					case FlagType.PASSWORD_UNLOCK:// ����
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

					case FlagType.PASSWORD_QUAN:// ȫ��
						PasswordActivity.this.finish();
						break;

					case FlagType.PASSWORD_CREAT:

						break;

					default:
						break;
					}
				}
			}

			@Override
			public void onTooshort(String password) {// ����̫��
				mTitle.setTextColor(0xffff0000);
				mTitle.setText("����������λ");
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
		int id = v.getId();
		if (id == R.id.mForgotPassword) {
			Intent intent = new Intent(PasswordActivity.this,
					ForgotPassword.class);
			startActivity(intent);
			finish();
		} else {
		}

	}

}
