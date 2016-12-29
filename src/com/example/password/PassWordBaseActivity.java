package com.example.password;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public abstract class PassWordBaseActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		init();
		findView();
		setListener();
		loadData();
		
	}
	

	protected void setListener() {
		// TODO Auto-generated method stub
	}


	protected abstract void init();


	protected void loadData() {
		// TODO Auto-generated method stub
		
	}


	protected void findView() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void startActivity(Intent intent) {
		// TODO Auto-generated method stub
		boolean isneed=intent.getBooleanExtra("isneed", false);//是否需要调到密码界面
		int _s=intent.getIntExtra(FlagType.FLAG_TYPE, -1);
		if (_s==FlagType.PASSWORD_UNLOCK) {//界面需要开启手势密码
			if (isneed&&PasswordUtil.isgoin(getApplicationContext())) {
				Intent _intent = new Intent(this, PasswordActivity.class);
				_intent.putExtra("intentComponent", intent.getComponent()
						.getClassName());
				_intent.putExtras(intent);
				super.startActivity(_intent);
			}else {
				super.startActivity(intent);
			}
		}else {
			if (isneed) {
				Intent _intent = new Intent(this, PasswordActivity.class);
				_intent.putExtra("intentComponent", intent.getComponent()
						.getClassName());
				_intent.putExtras(intent);
				super.startActivity(_intent);
			} else {
				super.startActivity(intent);
			}
		}
	}
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
}
