package com.example.recyclerview;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by OuyangYu on 2019/10/30 20:14
 */
public class DownloadTask extends AsyncTask<String, Integer, Integer> {


	public static final int TYPE_SUCCESS = 0;
	public static final int TYPE_FAILED = 1;
	public static final int TYPE_PAUSED = 2;
	public static final int TYPE_CANCELED = 3;

	public com.example.recyclerview.DownloadListener listener;
	private boolean isCanceled = false;
	private boolean isPaused = false;

	private int lastProgress;

	public DownloadTask(DownloadListener listener) {
		this.listener = listener;
	}

	//4.doInBackground中，从参数中获取URL，解析出文件名，指定下载到某目录下。看它是否存在，若存在读取字节数，后面实现断点续传功能。
	//使用getContentLength来获取文件长度。成功与否可以看出来。接着使用OKhttp发送请求，addHeader指名从哪个字节开始续下载。
	//接着使用Java文件流的方式从网络读取数据，并不断写入本地，一直下载结束为止。中间判断用户是否有取消或暂停操作。调用pubishProgress
	// 进行通知.
	@Override
	protected Integer doInBackground(String... params) {
		InputStream is = null;
		RandomAccessFile savedFile = null;
		File file = null;
		try {
			long downloadLength = 0;//记载已下载的文件长度
			String downloadUrl = params[0];
			String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
			String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
			file = new File(directory + fileName);
			if (file.exists()) {
				downloadLength = file.length();
			}
			long contentLength = 0;
			contentLength = getContentLength(downloadUrl);
			if (contentLength == 0) {
				return TYPE_FAILED;
			} else if (contentLength == downloadLength) {
				//已下载的字节与文字总字节相等，则说明已经下载完成了
				return TYPE_SUCCESS;
			}

			OkHttpClient client = new OkHttpClient();
			Request request = new Request.Builder()
					//断点下载，指定从哪个字节开始下载
					.addHeader("RANGE", "bytes=" + downloadLength + "-")
					.url(downloadUrl).build();

			Response response = client.newCall(request).execute();
			if (response != null) {
				is = response.body().byteStream();
				savedFile = new RandomAccessFile(file, "rw");
				savedFile.seek(downloadLength);
				byte[] b = new byte[1024];
				int total = 0;
				int len;
				while ((len = is.read(b)) != -1) {
					if (isCanceled) {
						return TYPE_CANCELED;
					} else if (isPaused) {
						return TYPE_PAUSED;
					} else {
						total += len;
						savedFile.write(b, 0, len);
						//计算已经下载的百分比
						int progress = (int) ((total + downloadLength) * 100 / contentLength);
						publishProgress(progress);
					}
				}
				response.body().close();
				return TYPE_SUCCESS;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (savedFile != null) {
					savedFile.close();
				}
				if (isCanceled && file != null) {
					file.delete();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		/**
		 * 之前抄的代码return failed 放在了finally里面
		 */
		return TYPE_SUCCESS;
	}
	//5.onProgressUpdate中从参数中获取当前下载进度，若有变化使用onProgress更新进度。
	@Override
	protected void onProgressUpdate(Integer... values) {
		int progress = values[0];
		if (progress > lastProgress) {
			listener.onProgress(progress);
			lastProgress = progress;
		}
	}

	//6.onPostExecute中根据参数中传入的下载状态进行回调各种接口方法。
	@Override
	protected void onPostExecute(Integer integer) {
		switch (integer){
			case TYPE_SUCCESS:
				listener.OnSuccess();
				break;
			case TYPE_FAILED:
				listener.onFailed();
				break;
			case TYPE_PAUSED:
				listener.onPaused();
				break;
			case TYPE_CANCELED:
				listener.onCanceled();
				break;
			default:
				break;
		}
	}

	public void pauseDownload(){
		isPaused = true;
	}

	public void cancelDownload(){
		isCanceled = true;
	}

	private long getContentLength(String downloadUrl) throws IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(downloadUrl).build();
		Response response = client.newCall(request).execute();
		if (response != null && response.isSuccessful()) {
			long contentLength = response.body().contentLength();
			response.close();
			return contentLength;
		}
		return 0;
	}


}
