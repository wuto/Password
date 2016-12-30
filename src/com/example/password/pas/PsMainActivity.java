package com.example.password.pas;

import com.example.password.R;
import com.example.password.R.id;
import com.example.password.R.layout;
import com.example.password.util.SharedPreferencesHelper;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PsMainActivity extends PassWordBaseActivity implements
		OnClickListener {

	private Button setpassword;
	private Button modifypassword;
	private Button delete;

	private Button open;

	@Override
	protected void findView() {
		super.findView();
		setpassword = (Button) findViewById(R.id.setpassword);
		modifypassword = (Button) findViewById(R.id.modifypassword);
		delete = (Button) findViewById(R.id.delete);

		open = (Button) findViewById(R.id.open);

		setTopTitle("手势密码");
	}

	@Override
	protected void init() {
		setContentView(R.layout.activity_psmain);
	}

	@Override
	protected void setListener() {
		super.setListener();
		setpassword.setOnClickListener(this);
		modifypassword.setOnClickListener(this);
		delete.setOnClickListener(this);

		open.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
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
		case R.id.open:

			if (PasswordUtil.getisopen(this)) {// 开启
				PasswordUtil.setisopen(this, false);
				open.setText("开启手势密码");
			} else {// 未开启
				PasswordUtil.setisopen(this, true);
				open.setText("关闭手势密码");
			}
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
		open.setVisibility(View.GONE);
	}

	/**
	 * 创建手势密码
	 * 
	 */
	private void setpassword() {
		Intent intent = new Intent(this, PasswordSetting.class);
		intent.putExtra(FlagType.FLAG_TYPE, FlagType.PASSWORD_CREAT);
		BasestartActivity(intent);
	}

	/**
	 * 修改手势密码
	 * 
	 */
	private void modifypassword() {
		Intent intent = new Intent(this, PasswordSetting.class);
		intent.putExtra(FlagType.FLAG_TYPE, FlagType.PASSWORD_MODIFY);
		BasestartActivity(intent);
	}

	/**
	 * 删除手势密码
	 * 
	 */
	private void delete() {
		Intent intent = new Intent(this, PasswordActivity.class);
		intent.putExtra(FlagType.FLAG_TYPE, FlagType.PASSWORD_DELETE);
		BasestartActivity(intent);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == FlagType.PASSWORD_DELETE && resultCode == 200) {
			Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
		} else if (requestCode == FlagType.PASSWORD_CREAT && resultCode == 200) {
			Toast.makeText(this, "密码设置成功", Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	protected void onResume() {
		hint();
		if (PasswordUtil.getlock(this)) {// 有密码
			delete.setVisibility(View.VISIBLE);
			modifypassword.setVisibility(View.VISIBLE);
			open.setVisibility(View.VISIBLE);

			if (PasswordUtil.getisopen(this)) {// 开启
				open.setText("关闭手势密码");
			} else {// 未开启
				open.setText("开启手势密码");
			}
		} else {// 无密码
			setpassword.setVisibility(View.VISIBLE);
		}
		super.onResume();
	}

}
