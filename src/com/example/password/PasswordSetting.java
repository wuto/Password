package com.example.password;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
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
public class PasswordSetting extends PassWordBaseActivity implements OnClickListener {

	private LocusPassWordView mPwdView;
	private Context mContext;
	private TextView mTitle;

	private String pswone = "";
	
	
//	private LinearLayout topLayout;
//	private TextView toptitle;

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
		
//		topLayout = (LinearLayout) findViewById(R.id.ll_top_bar_left);
//		toptitle = (TextView) findViewById(R.id.titlebar_name_tv);
		
		toptitle.setText("创建手势密码");
		mTitle.setText("请绘制手势密码");
	}

	@Override
	protected void setListener() {
		super.setListener();
		
		mPwdView.setOnCompleteListener(new OnCompleteListener() {
			@Override
			public void onComplete(String mPassword) {
				SharedPreferencesHelper sph = SharedPreferencesHelper
						.getInstance(getApplicationContext());
				Md5Utils md5 = new Md5Utils();

				if (TextUtils.isEmpty(pswone)) {
					pswone = md5.toMd5(mPassword, "");
					mTitle.setText("请再次绘制手势密码");
					mPwdView.clearPassword();
					return;
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
						mTitle.setText("与上次输入不一致，请重新绘制");
						mTitle.setTextColor(Color.RED);
						mPwdView.markError();
					}

				}
				
			}

		});
	}

//	@Override
//	public void onClick(View v) {
//		switch (v.getId()) {
//		case R.id.ll_top_bar_left:
//			finish();
//			break;
//
//		default:
//			break;
//		}
//		
//	}

}
