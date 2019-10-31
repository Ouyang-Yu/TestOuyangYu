package com.example.recyclerview;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class BSTActivity extends AppCompatActivity {


	public LocationClient mlocationClient;
	private TextView textView;
	private MapView mapView;

	private BaiduMap baiduMap;   //
	private boolean isFirstLoc = true; //第一次的话,定位到我的位置



	@Override
	protected void onCreate(Bundle savedInstanceState) {

//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//			Window window = getWindow();
//			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//			window.setStatusBarColor(Color.TRANSPARENT);
//			window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//		}
//
//
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN);


		super.onCreate(savedInstanceState);

		mlocationClient = new LocationClient(getApplicationContext());
		mlocationClient.registerLocationListener(new MyLocationListener());

		SDKInitializer.initialize(getApplicationContext());
		
		setContentView(R.layout.activity_bst);//在加载布局之前

		textView=findViewById(R.id.text);
		mapView=findViewById(R.id.mapView);




		Toolbar toolbar =findViewById(R.id.maptoolbar);
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
		}


		baiduMap = mapView.getMap();
		baiduMap.setMyLocationEnabled(true);



		//权限申请
		List<String> permissionList = new ArrayList<>();
		if (ContextCompat.checkSelfPermission(BSTActivity.this,
				Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
		}

		if (ContextCompat.checkSelfPermission(BSTActivity.this,
				Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
			permissionList.add(Manifest.permission.READ_PHONE_STATE);
		}
		if (ContextCompat.checkSelfPermission(BSTActivity.this,
				Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
			permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
		}

		if (!permissionList.isEmpty()) {
			String[] permissions = permissionList.toArray(new String[permissionList.size()]);
			ActivityCompat.requestPermissions(BSTActivity.this, permissions, 1);
		} else {
			initLocation();
			mlocationClient.start();
		}

		
	}

	private void initLocation() {
		LocationClientOption option = new LocationClientOption();
		option.setScanSpan(1000);  //选项设置刷新间隔
		//option.setIsNeedLocationDescribe(true);
		option.setIsNeedAddress(true);
		option.setOpenGps(true);

		option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
		//定位精度
		option.setLocationNotify(true);
		//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
		mlocationClient.setLocOption(option); //应用选项
	}


	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		switch (requestCode) {
			case 1:
				if (grantResults.length > 0) {
					for (int result : grantResults) {
						if (result != PackageManager.PERMISSION_GRANTED) {
							Toast.makeText(BSTActivity.this, "需要相应权限才能用哦٩(๑❛ᴗ❛๑)۶", Toast.LENGTH_SHORT).show();
							finish();
							return;
						}
					}
					mlocationClient.start();
				} else {
					Toast.makeText(BSTActivity.this, "发生了未知的错误(；′⌒`)", Toast.LENGTH_SHORT).show();
					finish();
				}
				default:
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mlocationClient.stop();//活动销毁,停止定位
		mapView.onDestroy();
		baiduMap.setMyLocationEnabled(false);

	}


	private class MyLocationListener implements BDLocationListener {


		@Override
		public void onReceiveLocation(final BDLocation location) {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					StringBuilder sb = new StringBuilder();
					if (location.getLocType() == BDLocation.TypeGpsLocation) {
						sb.append("GPS"+ "\n");
					}
					if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
						sb.append("NET"+ "\n");
					}
					sb.append(location.getLatitude() + "\n");
					sb.append(location.getLongitude() + "\n");
					sb.append(location.getDistrict() + "\n");
					sb.append(location.getStreet() + "\n");

					textView.setText(sb);


				}
			});

			if (isFirstLoc) {
				LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
				MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
				baiduMap.animateMapStatus(update);
				update = MapStatusUpdateFactory.zoomTo(12f);
				baiduMap.animateMapStatus(update);
				isFirstLoc = false;
			}
			MyLocationData locationData = new MyLocationData.Builder()
					.latitude(location.getLatitude())
					.longitude(location.getLongitude())
					.build();




			baiduMap.setMyLocationData(locationData);

		}







	}
}
