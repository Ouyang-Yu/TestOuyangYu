package com.example.bottomfragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//@SuppressLint(value = {"NewApi", "LocalSuppress"})


		BottomNavigationBar navigationBar = findViewById(R.id.bottom_naviagtion);
		BadgeItem badgeItem = new BadgeItem();
		badgeItem.setHideOnSelect(false)
				.setText("10")
				.setBackgroundColorResource(R.color.colorAccent)
				.setBorderWidth(0);

		//        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
		navigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);


		navigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);


//        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
		navigationBar.setBarBackgroundColor(R.color.colorAccent);//set background color for navigation bar
		navigationBar.setInActiveColor(R.color.colorAccent);//unSelected icon color
		navigationBar.addItem(new BottomNavigationItem(R.drawable.kefu, "R.string.tab_one").setActiveColorResource(R.color.colorAccent).setBadgeItem(badgeItem))
				.addItem(new BottomNavigationItem(R.drawable.kefu, "R.string.tab_two").setActiveColorResource(R.color.colorPrimary))
				.addItem(new BottomNavigationItem(R.drawable.kefu, "R.string.tab_three").setActiveColorResource(R.color.colorAccent))
				.addItem(new BottomNavigationItem(R.drawable.kefu, "R.string.tab_four"))
				.setFirstSelectedPosition(0)
				.initialise();

		navigationBar.setTabSelectedListener(this);
		setDefaultFragment();
	}

	/**
	 * set the default fragment
	 */
	private void setDefaultFragment() {
//		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//		mFragmentOne = FragmentOne.newInstance("First Fragment");
//		transaction.replace(R.id.ll_content, mFragmentOne).commit();
	}

	@Override
	public void onTabSelected(int position) {

	}

	@Override
	public void onTabUnselected(int position) {

	}

	@Override
	public void onTabReselected(int position) {

	}





//						View.OnClickListener l = new View.OnClickListener() {
//							@Override
//							public void onClick(View view) {
//								FragmentManager fm = getFragmentManager();
//								FragmentTransaction ft = fm.beginTransaction();
//								Fragment f = null;
//								switch (view.getId()) {
//									case R.id.view1:
//										f = new com.example.bottomfragment.Fragment();
//										break;
//									case R.id.view2:
//										f = new Fragment1();
//										break;
//									default:
//										break;
//								}
//								ft.replace(R.id.fragment, f);
//								ft.commit();
//
//
//							}
//						};
//		ImageView imageView1 = findViewById(R.id.view1);
//		ImageView imageView2 = findViewById(R.id.view2);
//		imageView1.OnClickListener
//		imageView2

	}

