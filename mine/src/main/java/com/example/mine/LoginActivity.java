package com.example.mine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_login);

	  final EditText username = findViewById(R.id.username);
	  final EditText passWord = findViewById(R.id.password);
	  Button button=findViewById(R.id.login);

	  final SharedPreferences sp = getSharedPreferences("login", MODE_PRIVATE);

	  final String spname = sp.getString("username", null);
	  final String spPassWord = sp.getString("password", null);


	  button.setOnClickListener(new View.OnClickListener() {
	     @Override
		 public void onClick(View view) {

			if (spname == null && spPassWord == null) {//第一次登录,自动保存用户密码

			   String etname = username.getText().toString();
			   String etpass = passWord.getText().toString();

			   SharedPreferences.Editor editor = sp.edit();

			   editor.putString("username", etname);
			   editor.putString("password", etpass);
			   editor.commit();

			   Intent intent = new Intent(LoginActivity.this, MainActivity.class);
			   startActivity(intent);

			   Toast.makeText(LoginActivity.this, "已自动注册并保存了账号密码哦", Toast.LENGTH_SHORT).show();


			} else { //不是第一次,直接进
			   Intent intent = new Intent(LoginActivity.this, MainActivity.class);
			   startActivity(intent);

			}


	     }
	  });




   }
}
