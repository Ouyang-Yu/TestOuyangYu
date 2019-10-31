package com.example.recyclerview;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.recyclerview.homeFragment.MainActivity;

import java.io.File;

/**
 * Created by OuyangYu on 2019/10/30 20:23
 */
public class DownloadService extends Service {
	public DownloadTask downLoadTask;
	private String downloadUrl;
	private DownloadListener listener = new DownloadListener() {
		@Override
		public void onProgress(int progress) {
			getNotificationManager().notify(1, getNotification("Downloading...", progress));
		}

		@Override
		public void OnSuccess() {
			downLoadTask = null;
			//下载成功时将前台通知关闭，并创建一个下载成功的通知
			stopForeground(true);
			getNotificationManager().notify(1, getNotification("Download success...", -1));
			Toast.makeText(DownloadService.this, "尽情享受吧", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onFailed() {
			downLoadTask = null;
			//下载失败将前台服务通知关闭，并创建一个下载失败的通知
			stopForeground(true);
			getNotificationManager().notify(1, getNotification("Download Failed", -1));
			Toast.makeText(DownloadService.this, "好像下载失败了", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onPaused() {
			downLoadTask = null;
			Toast.makeText(DownloadService.this, "你是不是点暂停了", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onCanceled() {
			downLoadTask = null;
			stopForeground(true);
			Toast.makeText(DownloadService.this, "取消了啊...", Toast.LENGTH_SHORT).show();
		}
	};

	//9.getNotification方法构建通知，设置完setProgress用于设置进度。第一个参数是最大进度，第二个是当前进度。
	// 后端工作已完成，编写前端。


	String CHANNEL_ONE_ID = "com.primedu.cn";
	String CHANNEL_ONE_NAME = "Channel One";
	NotificationChannel notificationChannel;

	private Notification getNotification(String title, int progress) {
		Intent intent = new Intent(this, MainActivity.class);
		PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);

//		notificationChannel = new NotificationChannel(CHANNEL_ONE_ID,
//				CHANNEL_ONE_NAME, NotificationManager.IMPORTANCE_HIGH);
//		notificationChannel.enableLights(true);
//		notificationChannel.setLightColor(Color.RED);
//		notificationChannel.setShowBadge(true);
//		notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		manager.createNotificationChannel(notificationChannel);


		NotificationCompat.Builder notification = new NotificationCompat.Builder(this);


		notification.setChannelId(CHANNEL_ONE_ID);//8.0之后需要这个
		notification.setSmallIcon(R.mipmap.ic_launcher);
		notification.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
		notification.setContentTitle(title);
		notification.setContentIntent(pi);
		if (progress >= 0) {
			//当progress大于0时需要显示下载进度
			notification.setContentText(progress + "%");
			notification.setProgress(100, progress, false);
		}
		return notification.build();
	}

	private NotificationManager getNotificationManager() {
		return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	}

	private DownloadBinder mBinder = new DownloadBinder();

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	//8.创建的DownloadBinder继承自Binder提供了startDowload、pauseDownload和cancelDownload方法。取消下载时
// 注意将文件删除掉


	class DownloadBinder extends Binder {
		public void startDownload(String url) {
			if (downLoadTask == null) {
				downloadUrl = url;
				downLoadTask = new DownloadTask(listener);
				downLoadTask.execute(downloadUrl);
				startForeground(1, getNotification("Downloading", 0));
				Toast.makeText(DownloadService.this, "下载着呢，别催我...", Toast.LENGTH_SHORT).show();
			}
		}

		public void pauseDownload() {
			if (downLoadTask != null) {
				downLoadTask.pauseDownload();
			}
		}

		public void cancelDownload() {
			if (downLoadTask != null) {
				downLoadTask.cancelDownload();
			} else if (downloadUrl != null) {
				//取消下载时将文件删除并将通知取消
				String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
				String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
				File file = new File(directory + fileName);
				if (file.exists()) {
					file.delete();
				}
				getNotificationManager().cancel(1);
				stopForeground(true);
				Toast.makeText(DownloadService.this, "取消了...", Toast.LENGTH_SHORT).show();
			}
		}

	}//inner Class


}
