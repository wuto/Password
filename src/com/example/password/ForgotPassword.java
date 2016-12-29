package com.example.password;

import com.example.password.util.SharedPreferencesHelper;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ForgotPassword extends PassWordBaseActivity implements OnClickListener {

	
	private TextView rest;
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_forgot);
	}
	
	@Override
	protected void findView() {
		
		rest=(TextView)findViewById(R.id.rest);
		
	}
	
	@Override
	protected void setListener() {
		
		rest.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rest:
			SharedPreferencesHelper shp=SharedPreferencesHelper.getInstance(getApplicationContext());
			shp.putString("password", "");
			
			break;

		default:
			break;
		}
		
	}
	
	
	

}
