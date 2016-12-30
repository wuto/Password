package com.example.password;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class TestActivity2 extends Activity {

	Button setpassword;
	Button modifypassword;
	Button delete;
	Button unlock;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test2);

		setpassword = (Button) findViewById(R.id.setpassword);
		modifypassword = (Button) findViewById(R.id.modifypassword);
		delete = (Button) findViewById(R.id.delete);
		unlock = (Button) findViewById(R.id.unlock);

		Intent intent = getIntent();
		String _1=intent.getStringExtra("_1");
		String _2=intent.getStringExtra("_2");
		String _3=intent.getStringExtra("_3");
		String _4=intent.getStringExtra("_4");
		
		setpassword.setText(_1);
		modifypassword.setText(_2);
		delete.setText(_3);
		unlock.setText(_4);
		
		

	}
	
}
