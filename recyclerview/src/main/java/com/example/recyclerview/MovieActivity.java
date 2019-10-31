package com.example.recyclerview;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MovieActivity extends AppCompatActivity {


	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		switch (requestCode) {
			case 1:
				if (grantResults.length > 0) {
					for (int result : grantResults) {
						if (result != PackageManager.PERMISSION_GRANTED) {
							Toast.makeText(MyApplication.getContext(), "需要相应权限才能用哦٩(๑❛ᴗ❛๑)۶", Toast.LENGTH_SHORT).show();
							finish();
							return;
						}
					} //一一检查权限请求是否满足

				}
			default:
		}
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
				, WindowManager.LayoutParams.FLAG_FULLSCREEN);//取消状态栏
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉 标题

		/***在布局文件加载之前之前**/

		setContentView(R.layout.activity_movie);









		final VideoView mVideoView = findViewById(R.id.video_view);

		if (ContextCompat.checkSelfPermission(MovieActivity.this,
				Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(MovieActivity.this,
					new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
		}


//		File file = new File(Environment.getExternalStorageState() + "/anna.mp4");
//		if (file.exists()) {
//			mVideoView.setVideoPath(file.getAbsolutePath());
//		}

		//文件名不能加后缀
		MediaController mc = new MediaController(this);
		mVideoView.setMediaController(mc);

		mVideoView.requestFocus();//获得焦点

		mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/raw/anna"));
		mVideoView.start();

		mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mediaPlayer) {
				AlertDialog.Builder dialog = new AlertDialog.Builder(MovieActivity.this);
				dialog.setTitle("视频播放完毕了罒ω罒");
				dialog.setIcon(R.drawable.movie);
				dialog.setMessage("要退出播放器吗(◕ᴗ◕✿)");
				dialog.setCancelable(false);
				dialog.setPositiveButton("退出", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						finish();
					}
				});
				dialog.setNegativeButton("重新播放", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						mVideoView.start();
					}
				});
				dialog.show();


			}
		});

	}
}
