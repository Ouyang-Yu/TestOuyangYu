package com.example.banner;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnBannerListener {
	private Banner banner;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		banner = findViewById(R.id.banner);//一定要布局文件之后获取ID
		List<Integer> imageList = new ArrayList<>();
		int[] imageIDs = new int[]{R.drawable.gao, R.drawable.yun, R.drawable.img1,R.drawable.lemon};
		List<String> nameList = new ArrayList<>();
		String[] names = new String[]{"1", "2", "3", "4"};
		imageList.add(imageIDs[0]);
		imageList.add(imageIDs[1]);
		imageList.add(imageIDs[2]);
		imageList.add(imageIDs[3]);
		nameList.add(names[0]);
		nameList.add(names[1]);
		nameList.add(names[2]);
		nameList.add(names[3]);

		banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
		//圆点指示器(带 标题inside)


		banner.setBannerTitles(nameList);
		banner.setImages(imageList);

		banner.setImageLoader(new MyLoader());
		//设置图片加载器
		banner.setBannerAnimation(Transformer.Default);
		//动画

		banner.setOnBannerListener(this);
		//点击图片事件
		banner.setDelayTime(1000);
		banner.isAutoPlay(true);
		banner.setIndicatorGravity(BannerConfig.CENTER);
		banner.start();


	}

	@Override
	public void OnBannerClick(int position) {
		Toast.makeText(MainActivity.this, position + 1 + "", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onStart() {
		super.onStart();
		banner.startAutoPlay();
	}

	@Override
	protected void onStop() {
		super.onStop();
		banner.stopAutoPlay();
	}

	private class MyLoader extends ImageLoader {//inner Class

		@Override
		public void displayImage(Context context, Object path, ImageView imageView) {
			Glide.with(MainActivity.this).load(path).into(imageView);

		}


	}
}
