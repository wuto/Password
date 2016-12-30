package com.example.password.pas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
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
 * 
 * �������� �� ��һ�ν���ֱ�������������룬��ͬ���óɹ� �Ժ���룬����֤һ��ԭ���룬�������������룬��ͬ���óɹ�
 * 
 * @author fantasee
 * 
 */
public class PasswordSetting extends PassWordBaseActivity {

	private LocusPassWordView mPwdView;
	private Context mContext;
	private TextView mTitle;

	private String pswone = "";

	private int flag;
	private Intent mIntent;

	@Override
	protected void init() {
		setContentView(R.layout.setting);
	}

	@Override
	protected void findView() {
		super.findView();
		mContext = getApplicationContext();

		mIntent = getIntent();
		flag = mIntent.getIntExtra(FlagType.FLAG_TYPE, -1);

		mPwdView = (LocusPassWordView) this.findViewById(R.id.mPassWordView);
		mTitle = (TextView) this.findViewById(R.id.multi_tv_token_time_hint);

		setTopTitle("������������");
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (TextUtils.isEmpty(pswone)) {
			mTitle.setTextColor(0xffffffff);
			mTitle.setText("�������������");
		} else {
			mTitle.setTextColor(0xffffffff);
			mTitle.setText("���ٴλ�����������");
		}
	}

	@Override
	protected void setListener() {
		super.setListener();

		mPwdView.setOnCompleteListener(new OnCompleteListener() {
			@Override
			public void onComplete(String mPassword) {
				Md5Utils md5 = new Md5Utils();

				if (TextUtils.isEmpty(pswone)) {
					pswone = md5.toMd5(mPassword, "");
					mTitle.setText("���ٴλ�����������");
					mPwdView.clearPassword();
					return;
				} else {
					if (pswone.equals(md5.toMd5(mPassword, ""))) {
						// ���óɹ�
						PasswordUtil.setPassword(PasswordSetting.this,
								md5.toMd5(mPassword, ""));
						if (flag == FlagType.PASSWORD_CREAT) {
							setResult(200);
						}

						mTitle.setText("���óɹ�");
						finish();
					} else {
						mTitle.setText("���ϴ����벻һ�£������»���");
						mTitle.setTextColor(Color.RED);
						mPwdView.markError();
					}

				}

			}

			@Override
			public void onTooshort(String password) {
				mTitle.setTextColor(0xffff0000);
				mTitle.setText("����������λ");
			}

		});
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
	}

}
