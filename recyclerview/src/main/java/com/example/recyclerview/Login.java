package com.example.recyclerview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.recyclerview.homeFragment.MainActivity;

public class Login extends AppCompatActivity {
	EditText nameET;
	EditText passWordET;
	CheckBox checkBox;
	Button button;
	private SharedPreferences sp;
	private SharedPreferences.Editor editor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		TextView nologin=findViewById(R.id.no_login);
		nologin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(Login.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		});


		TextView clause = findViewById(R.id.tiaokuang);
		clause.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(Login.this, clause.class);
				startActivity(intent);
			}
		});


		nameET = findViewById(R.id.username);
		passWordET = findViewById(R.id.password);
		checkBox = findViewById(R.id.checkbox);
		button = findViewById(R.id.login);
		TextView jizhuwo = findViewById(R.id.jizhuwo);
		jizhuwo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (checkBox.isChecked()) {
					checkBox.setChecked(false);
				} else {
					checkBox.setChecked(true);
				}
			}
		});

		sp = PreferenceManager.getDefaultSharedPreferences(this);

		boolean isRemember = sp.getBoolean("rememberPass", false);
		if (isRemember) {  //如果sp记录了isRemember,自动填写并打钩
			nameET.setText(sp.getString("name", null));
			passWordET.setText(sp.getString("pass", null));
			checkBox.setChecked(true);

		}

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String inputmame = nameET.getText().toString();
				String inputpass = passWordET.getText().toString();


				if (sp.getString("name", null) == null) {   //没有sp记录第一次进
					editor = sp.edit();
					editor.putString("name", inputmame);
					editor.putString("pass", inputpass);
					if (checkBox.isChecked()) {
						editor.putBoolean("rememberPass", true);
					}else 
						editor.putBoolean("rememberPass", false);
					editor.apply();
					Toast.makeText(Login.this,"已经自动注册并登录",Toast.LENGTH_SHORT).show();
					
					Intent intent = new Intent(Login.this, MainActivity.class);
					startActivity(intent);
					finish();
				} else {      //第二次进

//					if (checkBox.isChecked()) {
//						editor.putBoolean("rememberPass", true);
//					}else
//						editor.putBoolean("rememberPass", false);

					if (inputmame.equals(sp.getString("name", ""))
							&& inputpass.equals(sp.getString("pass", ""))) {
						//账号密码填写正确,自动填的也算正确
						Intent intent = new Intent(Login.this, MainActivity.class);
						startActivity(intent);
						finish();
					}else {
						Toast.makeText(Login.this,"账号或密码错误哦╮(╯﹏╰）╭",Toast.LENGTH_SHORT).show();
					}
					
					
				} 
				
			}
		});


	}
}
