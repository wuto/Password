package com.example.password;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends PassWordBaseActivity implements
		OnClickListener {

	private Button setpassword;
	private Button modifypassword;
	private Button delete;

	@Override
	protected void findView() {
		super.findView();
		setpassword = (Button) findViewById(R.id.setpassword);
		modifypassword = (Button) findViewById(R.id.modifypassword);
		delete = (Button) findViewById(R.id.delete);

		toptitle.setText("手势密码");
	}

	@Override
	protected void init() {
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void setListener() {
		super.setListener();
		setpassword.setOnClickListener(this);
		modifypassword.setOnClickListener(this);
		delete.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.setpassword:
			setpassword();
			break;
		case R.id.modifypassword:
			modifypassword();
			break;
		case R.id.delete:
			delete();
			break;

		default:
			break;
		}

	}

	/**
	 * 隐藏所有按钮
	 */
	private void hint() {
		delete.setVisibility(View.GONE);
		setpassword.setVisibility(View.GONE);
		modifypassword.setVisibility(View.GONE);
		// unlock.setVisibility(View.GONE);
	}

	/**
	 * 创建手势密码
	 * 
	 */
	private void setpassword() {
		Intent intent = new Intent(this, PasswordSetting.class);
		intent.putExtra(FlagType.FLAG_TYPE, FlagType.PASSWORD_CREAT);
		intent.putExtra("isneed", false);
		startActivity(intent);
	}

	/**
	 * 修改手势密码
	 * 
	 */
	private void modifypassword() {
		Intent intent = new Intent(this, PasswordSetting.class);
		intent.putExtra(FlagType.FLAG_TYPE, FlagType.PASSWORD_MODIFY);
		intent.putExtra("isneed", true);
		startActivity(intent);
	}

	/**
	 * 删除手势密码
	 * 
	 */
	private void delete() {
		Intent intent = new Intent(this, PasswordActivity.class);
		intent.putExtra(FlagType.FLAG_TYPE, FlagType.PASSWORD_DELETE);
		intent.putExtra("isneed", false);
		startActivity(intent);
	}

	// /**
	// * 解锁
	// *
	// */
	// private void unlock() {
	// Intent intent = new Intent(this, TestActivity.class);
	// intent.putExtra(FlagType.FLAG_TYPE, FlagType.PASSWORD_UNLOCK);
	// intent.putExtra("isneed", true);// 是否需要调到密码界面
	// intent.putExtra("_1", "测试一");
	// intent.putExtra("_2", "测试二");
	// intent.putExtra("_3", "测试三");
	// intent.putExtra("_4", "测试四");
	// startActivity(intent);
	// }

	@Override
	protected void onResume() {

		hint();
		if (PasswordUtil.getlock(this)) {// 有密码
			delete.setVisibility(View.VISIBLE);
			modifypassword.setVisibility(View.VISIBLE);
			// unlock.setVisibility(View.VISIBLE);

		} else {// 第一次进入 , 无密码
			setpassword.setVisibility(View.VISIBLE);
		}
		super.onResume();
	}

}
