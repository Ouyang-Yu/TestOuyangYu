package com.example.recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.example.recyclerview.homeFragment.RecyclerFruitAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by OuyangYu on 2019/10/29 15:27
 */
public class FragmentHome extends Fragment implements OnBannerListener {



	private Fruit[] fruits = {
			new Fruit("cake", R.drawable.cake),
			new Fruit("milk", R.drawable.milk),
			new Fruit("lemon", R.drawable.lemon),
			new Fruit("animation", R.drawable.gif1),
			new Fruit("gif", R.drawable.gif),
			new Fruit("salad",R.drawable.salad),
			new Fruit("g3",R.drawable.g3),
			new Fruit("g4",R.drawable.g4),
			new Fruit("5",R.drawable.g5),
			new Fruit("6",R.drawable.g7),
			new Fruit("7",R.drawable.g8),
			new Fruit("8",R.drawable.g6),
			new Fruit("6",R.drawable.g9),
			new Fruit("66",R.drawable.g),
			new Fruit("6",R.drawable.g11),

			new Fruit("strawberry",R.drawable.staryberry)
	};
	private List<Fruit> fruitList = new ArrayList<>();
	private SwipeRefreshLayout refresh;
	private RecyclerFruitAdapter adapter;

	@Override
	public void OnBannerClick(int position) {

	}
	private class MyLoader extends ImageLoader {//inner Class

		@Override
		public void displayImage(Context context, Object path, ImageView imageView) {
			Glide.with(getContext()).load(path).into(imageView);

		}


	}


	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, container, false);


		Banner banner = view.findViewById(R.id.banner);//一定要布局文件之后获取ID
		List<Integer> imageList = new ArrayList<>();
		int[] imageIDs = new int[]{R.drawable.s1, R.drawable.s2, R.drawable.g5,R.drawable.s3};
		List<String> nameList = new ArrayList<>();
		imageList.add(imageIDs[0]);
		imageList.add(imageIDs[1]);
		imageList.add(imageIDs[2]);
		imageList.add(imageIDs[3]);

//		String[] names = new String[]{"1", "2", "3", "4"};
//		nameList.add(names[0]);
//		nameList.add(names[1]);
//		nameList.add(names[2]);
//		nameList.add(names[3]);

		//banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
		//圆点指示器(带 标题inside)


		banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
		banner.setBannerTitles(nameList);
		banner.setImages(imageList);

		banner.setImageLoader(new MyLoader());
		//设置图片加载器
		banner.setBannerAnimation(Transformer.FlipHorizontal);
		//动画

		banner.setOnBannerListener(this);
		//点击图片事件
		banner.setDelayTime(2000);
		banner.isAutoPlay(true);
		banner.setIndicatorGravity(BannerConfig.CENTER);
		banner.start();





		initFruits();

		RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

		//LinearLayoutManager layoutManager = new LinearLayoutManager(this);

		//layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
		//横向recyclerView*/


		//StaggeredGridLayoutManager stagger = new StaggeredGridLayoutManager(
		//		3,
		//		StaggeredGridLayoutManager.VERTICAL);



		GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2);

		recyclerView.setLayoutManager(gridLayoutManager);
		//设置布局管理器

		adapter = new RecyclerFruitAdapter(fruitList);
		recyclerView.setAdapter(adapter);
		//设置适配器



		FloatingActionButton fab=view.findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//Toast.makeText(MainActivity.this,"FAB",Toast.LENGTH_SHORT).show();
				Snackbar.make(view,"Are you ready to add?", Snackbar.LENGTH_SHORT)
						.setAction("yes", new View.OnClickListener() {
							@Override
							public void onClick(View view) {
								Toast.makeText(getContext(),"OK",Toast.LENGTH_SHORT).show();

							}
						})
						.show();
			}
		});

		/**
		 * 下面处理刷新事件
		 */
		refresh=view.findViewById(R.id.refresh);
		refresh.setColorSchemeResources(R.color.googleRed);
		refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				refreshView();
			}
		});














		return view;
	}


	private void refreshView() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				getActivity().runOnUiThread(new Runnable() {//切换回主线程
					@Override
					public void run() {
						initFruits();//重新生成数据
						adapter.notifyDataSetChanged();//通知数据发生了变化
						refresh.setRefreshing(false);//刷新事件结束,隐藏进度条
					}
				});
			}
		}).start();

	}
	private void initFruits() {
		fruitList.clear();
		for (int i = 0; i < 30; i++) {
			Random random = new Random();
			int index=random.nextInt(fruits.length);//在fruits里面随机选一个
			if (i < 10) {//前10张不能有重复
				if (fruitList.indexOf(fruits[index]) <= 0) {
					fruitList.add(fruits[index]);
				}
			} else {
				fruitList.add(fruits[index]);
			}

			//fruitList.add(new Fruit(getRandomName("Apple"), R.drawable.apple));
			//fruitList.add(new Fruit(getRandomName("Banana"), R.drawable.banana));
		}
	}


}

