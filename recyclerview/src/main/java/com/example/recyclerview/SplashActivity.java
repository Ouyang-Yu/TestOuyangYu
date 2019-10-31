package com.example.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class SplashActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);



		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);




		setContentView(R.layout.activity_splash);
		ImageView imageView = findViewById(R.id.splash_image);
		Glide.with(this).load(R.drawable.piying).into(imageView);

		new Thread(){
			@Override
			public void run() {
				try {
					sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Intent intent = new Intent(SplashActivity.this, Login.class);
				startActivity(intent);
				finish();

			}
		}.start();
	}
}
