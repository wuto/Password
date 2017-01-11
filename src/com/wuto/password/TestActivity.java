package com.wuto.password;

import com.wuto.password.pas.PasswordUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TestActivity extends Activity implements OnClickListener {

	Button setpassword;
	Button modifypassword;
	Button delete;
	Button unlock;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);

		setpassword = (Button) findViewById(R.id.setpassword);
		modifypassword = (Button) findViewById(R.id.modifypassword);
		delete = (Button) findViewById(R.id.delete);
		unlock = (Button) findViewById(R.id.unlock);

		setpassword.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.setpassword:
			Intent intent = new Intent(this, TestActivity2.class);
			intent.putExtra("_1", "����");
			intent.putExtra("_2", "�Ϻ�");
			intent.putExtra("_3", "����");
			intent.putExtra("_4", "����");
			PasswordUtil.startActivity(this, intent, true);
			break;

		default:
			break;
		}

	}
}
