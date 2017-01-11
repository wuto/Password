package com.wuto.password;

import com.wuto.password.pas.PsMainActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.wuto.password.R;

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
		int id = v.getId();
		if (id == R.id.setpassword) {
			Intent intent=new Intent(this, PsMainActivity.class);
			startActivity(intent);
		} else if (id == R.id.modifypassword) {
			Intent intent2=new Intent(this, TestActivity.class);
			startActivity(intent2);
		} else {
		}
		
	}
	
}
