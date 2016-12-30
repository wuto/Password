package com.example.password.pas;

import com.example.password.R;
import com.example.password.R.id;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class PassWordBaseActivity extends Activity implements
		OnClickListener {

	private LinearLayout topBackLayout;
	private TextView toptitle;

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
		topBackLayout = (LinearLayout) findViewById(R.id.ll_top_bar_left);
		toptitle = (TextView) findViewById(R.id.titlebar_name_tv);
	}

	protected void setListener() {
		topBackLayout.setOnClickListener(this);
	}

	protected void loadData() {

	}

	public void setTopTitle(CharSequence title) {
		toptitle.setText(title);
	}

	public void BasestartActivity(Intent intent) {
		int _s = intent.getIntExtra(FlagType.FLAG_TYPE, -1);
		switch (_s) {
		case FlagType.PASSWORD_CREAT:
			startActivityForResult(intent, _s);
			break;
			
		case FlagType.PASSWORD_DELETE:
			startActivityForResult(intent, _s);
			break;
		case FlagType.PASSWORD_MODIFY:
			Intent _intent = new Intent(this, PasswordActivity.class);
			_intent.putExtra("intentComponent", intent.getComponent()
					.getClassName());
			_intent.putExtras(intent);
			startActivity(_intent);
			break;
		case FlagType.PASSWORD_QUAN:

			break;

		case FlagType.PASSWORD_UNLOCK:

			break;

		default:

			break;
		}
	}

	@Override
	protected void onResume() {
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
