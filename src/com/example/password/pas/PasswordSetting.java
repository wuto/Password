package com.example.password.pas;

import android.content.Context;
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
public class PasswordSetting extends PassWordBaseActivity  {

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
		super.findView();
		mContext = getApplicationContext();
		mPwdView = (LocusPassWordView) this.findViewById(R.id.mPassWordView);
		mTitle = (TextView) this.findViewById(R.id.multi_tv_token_time_hint);
		
		setTopTitle("������������");
		mTitle.setText("�������������");
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
						Toast.makeText(mContext,
								mContext.getString(R.string.pwd_setted),
								Toast.LENGTH_SHORT).show();
						PasswordUtil.setPassword(PasswordSetting.this, md5.toMd5(mPassword, ""));
						mTitle.setText("���óɹ�");
						finish();
					} else {
						mTitle.setText("���ϴ����벻һ�£������»���");
						mTitle.setTextColor(Color.RED);
						mPwdView.markError();
					}

				}
				
			}

		});
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
	}

}
