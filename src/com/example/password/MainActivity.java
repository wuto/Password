package com.example.password;

import com.example.password.pas.PsMainActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView setpassword=(TextView) findViewById(R.id.setpassword);
		TextView modifypassword=(TextView) findViewById(R.id.modifypassword);
		
		setpassword.setOnClickListener(this);
		modifypassword.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.setpassword:
			Intent intent=new Intent(this, PsMainActivity.class);
			startActivity(intent);
			break;
		case R.id.modifypassword:
			Intent intent2=new Intent(this, TestActivity.class);
			startActivity(intent2);
			break;
		default:
			break;
		}
		
	}
	
}
