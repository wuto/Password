package com.wuto.password.pas;

import com.wuto.password.R;
import com.wuto.password.R.id;
import com.wuto.password.R.layout;
import com.wuto.password.util.SharedPreferencesHelper;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ForgotPassword extends PassWordBaseActivity  {

	private TextView rest;

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_forgot);
	}

	@Override
	protected void findView() {
		super.findView();
		rest = (TextView) findViewById(R.id.rest);
		
		
		

	}

	@Override
	protected void setListener() {
		super.findView();
		rest.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.rest:
			PasswordUtil.deletePassword(this);
			break;

		default:
			break;
		}

	}

}
