package com.elvis.smssend;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	private Button btSend = null;
	private EditText content = null;
	private EditText phoneNum = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		 btSend = (Button)findViewById(R.id.send_sms_button);
		 btSend.setOnClickListener(this);
		 phoneNum = (EditText) findViewById(R.id.phone_number_editText);
		 content = (EditText) findViewById(R.id.sms_content_editText);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.send_sms_button:
			SendSMS();
			break;

		default:
			break;
		}
	}
	
	private void SendSMS(){
		String phoneNumber = phoneNum.getText().toString().trim();
		String smsContent = content.getText().toString().trim();
		if(TextUtils.isEmpty(phoneNumber)){
			Toast.makeText(MainActivity.this, "号码不能为空", Toast.LENGTH_SHORT).show();
			return;
		}
		if (TextUtils.isEmpty(smsContent)) {
			Toast.makeText(MainActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
			return;
		}
		SmsManager smsManager = SmsManager.getDefault();
		if (smsContent.length() > 70) {
			
		 ArrayList<String> contents = smsManager.divideMessage(smsContent);
		 for(String c:contents){
			 smsManager.sendTextMessage(phoneNumber, null,  c, null, null);
		 }
		}else{
			smsManager.sendTextMessage(phoneNumber, null,  smsContent, null, null);
		}
	}

}
