package com.example.recyclerview.homeFragment;

import android.Manifest;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.recyclerview.BSTActivity;
import com.example.recyclerview.DownloadActivity;
import com.example.recyclerview.FragmentHome;
import com.example.recyclerview.FragmentThree;
import com.example.recyclerview.FragmentTwo;
import com.example.recyclerview.Fruit;
import com.example.recyclerview.MovieActivity;
import com.example.recyclerview.R;
import com.example.recyclerview.RecordActivity;
import com.example.recyclerview.MyWebActivity;
import com.example.recyclerview.chat.Chat;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

	private Fruit[] fruits = {
			new Fruit("cake", R.drawable.cake),
			new Fruit("milk", R.drawable.milk),
			new Fruit("lemon", R.drawable.lemon),
			new Fruit("animation", R.drawable.gif1),
			new Fruit("gif", R.drawable.gif),
			new Fruit("salad", R.drawable.salad),
			new Fruit("g3", R.drawable.g3),
			new Fruit("g4", R.drawable.g4),
			new Fruit("5", R.drawable.g5),
			new Fruit("6", R.drawable.g7),
			new Fruit("7", R.drawable.g8),
			new Fruit("8", R.drawable.g6),
			new Fruit("6", R.drawable.g9),
			new Fruit("66", R.drawable.g),
			new Fruit("6", R.drawable.g11),

			new Fruit("strawberry", R.drawable.staryberry)
	};
	private List<Fruit> fruitList = new ArrayList<>();
	private DrawerLayout drawer;
	private SwipeRefreshLayout refresh;
	private RecyclerFruitAdapter adapter;
	private static final int CHOOSE_PHOTO = 2;
	private CircleImageView portrait;
	private double exitTime;
	private RelativeLayout portraitback;


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exit();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void exit() {
		if (System.currentTimeMillis() - exitTime > 2000) {
			Toast.makeText(MainActivity.this, "再按一次退出哦(^_−)☆", Toast.LENGTH_SHORT).show();
			exitTime = System.currentTimeMillis();
		} else {
			finish();
			System.exit(0);
		}
	}


	private void replaceFragment(Fragment fragment) {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.zhujiemian, fragment);
		ft.commit();
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

//		Button button = findViewById(R.id.button);
//		button.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
//				startActivity(intent);
//			}
//		}); //跳转到展示listView的Activity

		////////////////一开始加载homeFragment
		replaceFragment(new FragmentHome());


		//////////////下面处理bottomNavigation点击

		BottomNavigationView bottomNav = findViewById(R.id.bottomnaviagtion);
		//bottomNav.setItemBackgroundResource(R.color.googleRed);

		bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

				switch (menuItem.getItemId()) {
					case R.id.navigation_home:
						replaceFragment(new FragmentHome());
						return true;

					case R.id.navigation_two:
						replaceFragment(new FragmentTwo());
						return true;
					case R.id.navigation_three:
						replaceFragment(new FragmentThree());
						return true;
				}
				return false;
			}
		});

//		initFruits();
//
//		RecyclerView recyclerView = findViewById(R.id.recyclerView);
//
//		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//
//		//layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//		//横向recyclerView*/
//
//
//		StaggeredGridLayoutManager stagger = new StaggeredGridLayoutManager(
//				3,
//				StaggeredGridLayoutManager.VERTICAL);
//
//
//
//		GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
//
//		recyclerView.setLayoutManager(gridLayoutManager);
//		//设置布局管理器
//
//		 adapter = new RecyclerFruitAdapter(fruitList);
//		recyclerView.setAdapter(adapter);
//		//设置适配器

		/**
		 * 下面为ToolBar左侧按钮调出Navigation
		 */

		drawer = findViewById(R.id.drawer_layout);

		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
//		ActionBar actionBar = getSupportActionBar();
//		if (actionBar != null) {
//			actionBar.setDisplayHomeAsUpEnabled(true);
//			actionBar.setHomeAsUpIndicator(R.drawable.menu1);
//		}

		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();
		//以上三行是安卓原生导航栏打开图标写法,不需要改返回键的图表和作用


		NavigationView navigation = findViewById(R.id.navigation);
		/**
		 * 用户自定义头像
		 */

		View head = navigation.inflateHeaderView(R.layout.nav_header);
		portrait = head.findViewById(R.id.portrait);
		portraitback = findViewById(R.id.back);
		portrait.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (ContextCompat.checkSelfPermission(MainActivity.this,
						Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
					ActivityCompat.requestPermissions(MainActivity.this,
							new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
				} else {
					openAlbum();
				}
			}
		});


		/**
		 * 下面处理Navigation  Item的点击事件
		 */
		navigation.setCheckedItem(R.id.call);//设置默认选中

		navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
				int ID = menuItem.getItemId();

				if (ID == R.id.location) {
					Intent intent = new Intent(MainActivity.this, BSTActivity.class);
					startActivity(intent);

				}
				if (ID == R.id.kefu) {
					Intent intent = new Intent(MainActivity.this, Chat.class);
					startActivity(intent);
				}
				if (ID == R.id.record) {
					Intent intent = new Intent(MainActivity.this, RecordActivity.class);
					startActivity(intent);
				}
				if (ID == R.id.museum) {
					Intent intent = new Intent(MainActivity.this, MyWebActivity.class);
					startActivity(intent);
				}
				if (ID == R.id.movie) {
					Intent intent = new Intent(MainActivity.this, MovieActivity.class);
					startActivity(intent);
				}
				if (ID == R.id.down_load) {
					Intent intent = new Intent(MainActivity.this, DownloadActivity.class);
					startActivity(intent);
				}

//				if (ID == R.id.weather) {
//					Intent intent = new Intent(MainActivity.this, Chat.class);
//					startActivity(intent);
//				}

				drawer.closeDrawer(GravityCompat.START);
				return true;
			}
		});


		/**
		 *下面实现再按一次返回
		 */


//		/**
//		 * 下面处理FloatingActionButton点击事件
//		 */
//
//		FloatingActionButton fab=findViewById(R.id.fab);
//		fab.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				//Toast.makeText(MainActivity.this,"FAB",Toast.LENGTH_SHORT).show();
//				Snackbar.make(view,"Are you ready to add?", Snackbar.LENGTH_SHORT)
//						.setAction("yes", new View.OnClickListener() {
//							@Override
//							public void onClick(View view) {
//								Toast.makeText(MainActivity.this,"OK",Toast.LENGTH_SHORT).show();
//
//							}
//						})
//						.show();
//			}
//		});

//		/**
//		 * 下面处理刷新事件
//		 *
//		 */
//		refresh=findViewById(R.id.refresh);
//		refresh.setColorSchemeResources(R.color.design_default_color_primary);
//		refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//			@Override
//			public void onRefresh() {
//				refreshView();
//			}
//		});

	}


	/********************oncreate结束*************************/

	/**
	 *
	 */
	private void openAlbum() {
		Intent intent = new Intent("android.intent.action.GET_CONTENT");
		intent.setType("image/*");
		startActivityForResult(intent, CHOOSE_PHOTO);
	}

	/**
	 * 用户选完图片携带数据返回之后
	 *
	 * @param requestCode
	 * @param resultCode
	 * @param data
	 */


	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == CHOOSE_PHOTO) {
			if (resultCode == RESULT_OK) {
				if (data != null)
					handleImageOnKitkat(data);

			}
		}
	}

	private void handleImageOnKitkat(Intent data) {
		String imagePath = null;
		Uri uri = data.getData();
		Log.d("TAG", "handleImageOnKitKat: uri is " + uri);
		if (DocumentsContract.isDocumentUri(this, uri)) {
			// 如果是document类型的Uri，则通过document id处理
			String docId = DocumentsContract.getDocumentId(uri);
			if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
				String id = docId.split(":")[1]; // 解析出数字格式的id
				String selection = MediaStore.Images.Media._ID + "=" + id;
				imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
			} else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
				Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
				imagePath = getImagePath(contentUri, null);
			}
		} else if ("content".equalsIgnoreCase(uri.getScheme())) {
			// 如果是content类型的Uri，则使用普通方式处理
			imagePath = getImagePath(uri, null);
		} else if ("file".equalsIgnoreCase(uri.getScheme())) {
			// 如果是file类型的Uri，直接获取图片路径即可
			imagePath = uri.getPath();
		}
		displayImage(imagePath); // 根据图片路径显示图片

	}

	private String getImagePath(Uri uri, String selection) {
		String path = null;
		// 通过Uri和selection来获取真实的图片路径
		Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
			}
			cursor.close();
		}
		return path;

	}

	private void displayImage(String imagePath) {
		if (imagePath != null) {
			Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
			portrait.setImageBitmap(bitmap);

			//portraitback.setBackgroundResource(R.drawable.apple);


		} else {
			Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
		}

	}




	/***************************以上算是设置好了头像**********************************/


//	private void refreshView() {
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				runOnUiThread(new Runnable() {//切换回主线程
//					@Override
//					public void run() {
//						initFruits();//重新生成数据
//						adapter.notifyDataSetChanged();//通知数据发生了变化
//						refresh.setRefreshing(false);//刷新事件结束,隐藏进度条
//					}
//				});
//			}
//		}).start();
//
//	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.toolbat_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		switch (item.getItemId()) {
			case R.id.home:

				drawer.openDrawer(GravityCompat.START);

				break;
			case R.id.help:
				break;
			case R.id.about:
				break;
			case R.id.setting:
				break;
			default:

		}
		return true;
	}

//	private void initFruits() {
//		fruitList.clear();
//		for (int i = 0; i < 30; i++) {
//			Random random = new Random();
//			int index=random.nextInt(fruits.length);//在fruits里面随机选一个
//			if (i < 10) {//前10张不能有重复
//				if (fruitList.indexOf(fruits[index]) <= 0) {
//					fruitList.add(fruits[index]);
//				}
//			} else {
//				fruitList.add(fruits[index]);
//			}
//
//			//fruitList.add(new Fruit(getRandomName("Apple"), R.drawable.apple));
//			//fruitList.add(new Fruit(getRandomName("Banana"), R.drawable.banana));
//		}
//	}

//	private String getRandomName(String apple) {
//		Random random = new Random();
//		int i = random.nextInt(10) + 1;
//		StringBuilder sb = new StringBuilder();
//		for (int j = 0; j < i; j++) {
//			sb.append(apple);
//			sb.append("\n");
//		}
//		return sb.toString();
//	}

}
