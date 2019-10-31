package com.example.recyclerview;

/**
 * Created by OuyangYu on 2019/10/30 20:06
 */
public interface DownloadListener {
	void onProgress(int progress);

	void OnSuccess();
	void onFailed();
	void onPaused();
	void onCanceled();
}
