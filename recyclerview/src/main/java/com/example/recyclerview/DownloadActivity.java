package com.example.recyclerview;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class DownloadActivity extends AppCompatActivity implements View.OnClickListener {
	private DownloadService.DownloadBinder downloadBinder;

	private ServiceConnection connection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
			downloadBinder = (DownloadService.DownloadBinder) iBinder;
		}

		@Override
		public void onServiceDisconnected(ComponentName componentName) {

		}
	};//


	/**
	 * 开始
	 */
	private Button mButton1;
	/**
	 * 暂停
	 */
	private Button mButton2;
	/**
	 * 取消
	 */
	private Button mButton3;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_download);
		initView();
		mButton1.setOnClickListener(this);
		mButton2.setOnClickListener(this);
		mButton3.setOnClickListener(this);

		Intent intent = new Intent(this, DownloadService.class);
		startService(intent);
		bindService(intent, connection, BIND_AUTO_CREATE);

		if (ContextCompat.checkSelfPermission(DownloadActivity.this,
				Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(DownloadActivity.this,
					new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
		}

	}

	@Override
	public void onRequestPermissionsResult
			(
					int requestCode,
					@NonNull String[] permissions,
					@NonNull int[] grantResults) {
		switch (requestCode) {
			case 1:
				if (grantResults.length > 0) {
					for (int result : grantResults) {
						if (result != PackageManager.PERMISSION_GRANTED) {
							Toast.makeText(DownloadActivity.this, "需要相应权限才能用哦٩(๑❛ᴗ❛๑)۶", Toast.LENGTH_SHORT).show();
							finish();
							return;
						}
					} //一一检查权限请求是否满足

				}
			default:
		}
	}


	@Override
	public void onClick(View view) {
		if (downloadBinder == null) {
			return;
		}
		if (view.getId() == R.id.button) {
			String url="http://www.ihchina.cn/music_detail/18781.html";
			downloadBinder.startDownload(url);
		}
		if (view.getId() == R.id.button2) {
			downloadBinder.pauseDownload();
		}if (view.getId() == R.id.button3) {
			downloadBinder.cancelDownload();
		}
	}

	private void initView() {
		mButton1 =  findViewById(R.id.button);
		mButton2 =  findViewById(R.id.button2);
		mButton3 =  findViewById(R.id.button3);
	}
}
