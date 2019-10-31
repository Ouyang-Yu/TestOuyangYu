package com.example.recyclerview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RecordActivity extends AppCompatActivity {

	public static final int TAKE_PHOTO = 1;
	private ImageView imageView;
	private Uri uri;
	private TextView textView;

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

		switch (requestCode) {
			case TAKE_PHOTO:
				if (resultCode == RESULT_OK) {
					try {
						Bitmap bitmap = BitmapFactory.decodeStream(
								getContentResolver().openInputStream(uri));
						Toast.makeText(this, "照片已保存至" + getExternalCacheDir(), Toast.LENGTH_SHORT).show();
						imageView.setImageBitmap(bitmap);
						//textView.setText(getExternalCacheDir()+"");
					//	Log.d(TAG, "onActivityResult: ");
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}

				}else
					finish();
				break;
			default:
				break;
		}
		super.onActivityResult(requestCode, resultCode, data);


	}

	//	@Override
//	protected void attachBaseContext(Context newBase) {
//		super.attachBaseContext(newBase);
//		MultiDex.in
//	}
	private static int count = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_record);


		Toolbar toolbar = findViewById(R.id.record_toolbar);
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
		actionBar.setDisplayHomeAsUpEnabled(true);
		}



		imageView=findViewById(R.id.record_image);
		//textView=findViewById(R.id.recordTV);



		File image = new File(getExternalCacheDir(), "非遗.jpg");
		count++;
		if (image.exists()) {
			image.delete();
		}
		try {
			image.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (Build.VERSION.SDK_INT >= 24) {
			uri = FileProvider.getUriForFile(
					RecordActivity.this,
					"com.example.recyclerview.fileprovider",
					image
			);
		} else
			uri = Uri.fromFile(image);

		//open camera
		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		startActivityForResult(intent, TAKE_PHOTO);


	}
}
