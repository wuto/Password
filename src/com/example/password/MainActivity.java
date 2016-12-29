package com.example.password;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends PassWordBaseActivity implements OnClickListener {

	private Button setpassword;
	private Button modifypassword;
	private Button delete;
//	private Button unlock;


	@Override
	protected void findView() {
		setpassword = (Button) findViewById(R.id.setpassword);
		modifypassword = (Button) findViewById(R.id.modifypassword);
		delete = (Button) findViewById(R.id.delete);
//		unlock = (Button) findViewById(R.id.unlock);
	}
	
	@Override
	protected void init() {
		setContentView(R.layout.activity_main);
	}
	
	@Override
	protected void setListener() {
		setpassword.setOnClickListener(this);
		modifypassword.setOnClickListener(this);
		delete.setOnClickListener(this);
//		unlock.setOnClickListener(this);
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
//		case R.id.unlock:
//			unlock();
//			break;

		default:
			break;
		}
		
	}

	
	
	/**
	 * �������а�ť
	 */
	private void hint() {
		delete.setVisibility(View.GONE);
		setpassword.setVisibility(View.GONE);
		modifypassword.setVisibility(View.GONE);
//		unlock.setVisibility(View.GONE);
	}


	/**
	 * ������������
	 * 
	 */
	private void setpassword() {
		Intent intent = new Intent(this, PasswordSetting.class);
		intent.putExtra(FlagType.FLAG_TYPE, FlagType.PASSWORD_CREAT);
		intent.putExtra("isneed", false);
		startActivity(intent);
	}

	/**
	 * �޸���������
	 * 
	 */
	private void modifypassword() {
		Intent intent = new Intent(this, PasswordSetting.class);
		intent.putExtra(FlagType.FLAG_TYPE, FlagType.PASSWORD_MODIFY);
		intent.putExtra("isneed", true);
		startActivity(intent);
	}

	/**
	 * ɾ����������
	 * 
	 */
	private void delete() {
		Intent intent = new Intent(this, PasswordActivity.class);
		intent.putExtra(FlagType.FLAG_TYPE, FlagType.PASSWORD_DELETE);
		intent.putExtra("isneed", false);
		startActivity(intent);
	}

//	/**
//	 * ����
//	 * 
//	 */
//	private void unlock() {
//		Intent intent = new Intent(this, TestActivity.class);
//		intent.putExtra(FlagType.FLAG_TYPE, FlagType.PASSWORD_UNLOCK);
//		intent.putExtra("isneed", true);// �Ƿ���Ҫ�����������
//		intent.putExtra("_1", "����һ");
//		intent.putExtra("_2", "���Զ�");
//		intent.putExtra("_3", "������");
//		intent.putExtra("_4", "������");
//		startActivity(intent);
//	}

	@Override
	protected void onResume() {

		hint();
		if (PasswordUtil.getlock(this)) {// ������
			delete.setVisibility(View.VISIBLE);
			modifypassword.setVisibility(View.VISIBLE);
//			unlock.setVisibility(View.VISIBLE);

		} else {// ��һ�ν��� , ������
			setpassword.setVisibility(View.VISIBLE);
		}
		super.onResume();
	}

	
}
