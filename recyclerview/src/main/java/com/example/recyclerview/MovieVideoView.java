package com.example.recyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by OuyangYu on 2019/10/30 14:53
 */
public class MovieVideoView extends VideoView {
	public MovieVideoView(Context context) {
		super(context);
	}

	public MovieVideoView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MovieVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = getDefaultSize(0, widthMeasureSpec);
		int height = getDefaultSize(0, heightMeasureSpec);
		setMeasuredDimension(width,height);
	}
}
