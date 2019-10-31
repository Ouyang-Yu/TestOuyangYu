package com.example.actionbardaohang;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;

//import androidx.fragment.app.Fragment;

/**
 * @author Ouyang Yu
 * @date 2019/10/22 10:12
 */
public class MyTabListener implements ActionBar.TabListener {
	private final Activity activity;//加载Fragment的Activity
	private final Class aClass; //fragemnt对应的类
	private Fragment fragment;


   public MyTabListener(Activity activity, Class aClass) {
	  this.activity = activity;
	  this.aClass = aClass;
   }

   @Override
   public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	  if (fragment == null) {
		 fragment = Fragment.instantiate(activity, aClass.getName());
		 fragmentTransaction.add(android.R.id.content, fragment, null);
	  }
	  fragmentTransaction.attach(fragment);//显示新页面
   }

   @Override
   public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	  if (fragment != null) {
		 fragmentTransaction.detach(fragment);//删除旧页面

	  }
   }

   @Override
   public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

   }
}
