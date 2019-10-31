package com.example.recyclerview;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class clause extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clause);



		Toolbar toolbar = findViewById(R.id.clausetoolabr);
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
		}


//		if (NavUtils.getParentActivityName( clause.this) != null) {
//			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//			//设置向左的箭头图标
//		}
	}
}
