package com.example.password;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class PassWordBaseActivity extends Activity implements OnClickListener {

	
	
	protected LinearLayout topLayout;
	protected TextView toptitle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		init();
		findView();
		setListener();
		loadData();

	}

	protected abstract void init();

	protected void findView() {
		topLayout = (LinearLayout) findViewById(R.id.ll_top_bar_left);
		toptitle = (TextView) findViewById(R.id.titlebar_name_tv);
	}

	protected void setListener() {
		topLayout.setOnClickListener(this);
	}

	protected void loadData() {

	}

	@Override
	public void startActivity(Intent intent) {
		// TODO Auto-generated method stub
		boolean isneed = intent.getBooleanExtra("isneed", false);// 是否需要调到密码界面
		int _s = intent.getIntExtra(FlagType.FLAG_TYPE, -1);
		if (_s == FlagType.PASSWORD_UNLOCK) {// 界面需要开启手势密码
			if (isneed && PasswordUtil.isgoin(getApplicationContext())) {
				Intent _intent = new Intent(this, PasswordActivity.class);
				_intent.putExtra("intentComponent", intent.getComponent()
						.getClassName());
				_intent.putExtras(intent);
				super.startActivity(_intent);
			} else {
				super.startActivity(intent);
			}
		} else {
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
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_top_bar_left:
			finish();
			break;

		default:
			break;
		}
		
	}

}
