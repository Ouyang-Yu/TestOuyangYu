package com.example.actionbardaohang;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_main);

	  //设置选项卡模式
	  ActionBar actionBar = getSupportActionBar();
	  actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	  actionBar.setDisplayOptions(0,ActionBar.DISPLAY_SHOW_TITLE);
	  //不显示标题

	  actionBar.addTab(actionBar.newTab().setText("Fragment1")
			  .setTabListener((ActionBar.TabListener) new MyTabListener(this, MainActivity2.class)));
	  actionBar.addTab(actionBar.newTab().setText("Fragment2")
			  .setTabListener((ActionBar.TabListener) new MyTabListener(this, Main3Activity.class)));
   }
}
