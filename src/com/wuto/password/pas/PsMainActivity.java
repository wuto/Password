package com.wuto.password.pas;

import com.wuto.password.R;
import com.wuto.password.R.id;
import com.wuto.password.R.layout;
import com.wuto.password.util.SharedPreferencesHelper;

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

		setTopTitle("��������");
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
		int id = v.getId();
		if (id == R.id.setpassword) {
			setpassword();
		} else if (id == R.id.modifypassword) {
			modifypassword();
		} else if (id == R.id.delete) {
			delete();
		} else if (id == R.id.open) {
			if (PasswordUtil.getisopen(this)) {// ����
				PasswordUtil.setisopen(this, false);
				open.setText("������������");
			} else {// δ����
				PasswordUtil.setisopen(this, true);
				open.setText("�ر���������");
			}
		} else {
		}

	}

	/**
	 * �������а�ť
	 */
	private void hint() {
		delete.setVisibility(View.GONE);
		setpassword.setVisibility(View.GONE);
		modifypassword.setVisibility(View.GONE);
		open.setVisibility(View.GONE);
	}

	/**
	 * ������������
	 * 
	 */
	private void setpassword() {
		Intent intent = new Intent(this, PasswordSetting.class);
		intent.putExtra(FlagType.FLAG_TYPE, FlagType.PASSWORD_CREAT);
		BasestartActivity(intent);
	}

	/**
	 * �޸���������
	 * 
	 */
	private void modifypassword() {
		Intent intent = new Intent(this, PasswordSetting.class);
		intent.putExtra(FlagType.FLAG_TYPE, FlagType.PASSWORD_MODIFY);
		BasestartActivity(intent);
	}

	/**
	 * ɾ����������
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
			Toast.makeText(this, "ɾ���ɹ�", Toast.LENGTH_SHORT).show();
		} else if (requestCode == FlagType.PASSWORD_CREAT && resultCode == 200) {
			Toast.makeText(this, "�������óɹ�", Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	protected void onResume() {
		hint();
		if (PasswordUtil.getlock(this)) {// ������
			delete.setVisibility(View.VISIBLE);
			modifypassword.setVisibility(View.VISIBLE);
			open.setVisibility(View.VISIBLE);

			if (PasswordUtil.getisopen(this)) {// ����
				open.setText("�ر���������");
			} else {// δ����
				open.setText("������������");
			}
		} else {// ������
			setpassword.setVisibility(View.VISIBLE);
		}
		super.onResume();
	}

}
