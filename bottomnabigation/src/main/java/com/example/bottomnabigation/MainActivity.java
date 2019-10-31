package com.example.bottomnabigation;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
   private TextView mTextMessage;

   private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
		   = new BottomNavigationView.OnNavigationItemSelectedListener() {

	  @Override
	  public boolean onNavigationItemSelected(@NonNull MenuItem item) {


		  FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

		 switch (item.getItemId()) {
			case R.id.navigation_home:
				ft.replace(R.id.frameLayout, new fra1()).commit();
				mTextMessage.setText("home");
			   return true;
			case R.id.navigation_dashboard:
			   mTextMessage.setText(R.string.title_dashboard);
			   return true;
			case R.id.navigation_notifications:
			   mTextMessage.setText(R.string.title_notifications);
			   return true;
		 }


		 return false;
	  }
   };

   @Override
   protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_main);

	  BottomNavigationView navView = findViewById(R.id.nav_view);
	  mTextMessage = findViewById(R.id.message);


	  navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
   }

}
