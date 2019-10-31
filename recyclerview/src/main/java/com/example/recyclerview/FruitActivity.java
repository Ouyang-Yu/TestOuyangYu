package com.example.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class FruitActivity extends AppCompatActivity {


	public static final String FRUIT_NAME = "fruit_name";
	public static final String FRUIT_IMAGE_ID = "fruit_image_id";



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fruit);
		/**
		 * 从点击事件过来的数据
		 */
		Intent intent = getIntent();
		String fruitName = intent.getStringExtra(FRUIT_NAME);
		int fruitImageID = intent.getIntExtra(FRUIT_IMAGE_ID, 0);



		Toolbar toolbar = findViewById(R.id.toolbar);
		//注意导入的是androidx包里的
		CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapse_toolbar);
		ImageView fruitImageView = findViewById(R.id.fruitImage);
		TextView fruitContent = findViewById(R.id.fruit_content);

		/**
		 * toolbar添加返回按钮
		 */
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
		}


		/**
		 * 加载标题,图像,文字说明
		 */
		collapsingToolbar.setTitle(fruitName);
		Glide.with(this).load(fruitImageID).into(fruitImageView);
		fruitContent.setText(getFruitContent(fruitName));
	}

	private String getFruitContent(String name) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 100; i++) {
			sb.append(name+"\n");
		}
		return sb.toString();
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		switch (item.getItemId()) {
			case R.id.home:
				finish();
				break;
			default:
				finish();
				break;
		}
		return super.onOptionsItemSelected(item);
	}
}
