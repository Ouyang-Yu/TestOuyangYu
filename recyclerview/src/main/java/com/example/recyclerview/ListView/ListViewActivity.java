package com.example.recyclerview.ListView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.recyclerview.Fruit;
import com.example.recyclerview.FruitAdapter;
import com.example.recyclerview.R;

import java.util.ArrayList;
import java.util.List;


public class ListViewActivity extends AppCompatActivity {


	private List<Fruit> fruitList = new ArrayList<>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);

		initFruits();
		ListView listView = findViewById(R.id.list_view);
		FruitAdapter fruitAdapter = new FruitAdapter(ListViewActivity.this,
				R.layout.fruit_item, fruitList);
		listView.setAdapter(fruitAdapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				Fruit fruit = fruitList.get(i);
				Toast.makeText(ListViewActivity.this,fruit.getName(),Toast.LENGTH_SHORT).show();
			}
		});


	}

	private void initFruits() {
		for (int i = 0; i < 20; i++) {
			fruitList.add(new Fruit("Apple", R.drawable.apple));
			fruitList.add(new Fruit("Banana", R.drawable.banana));
		}
	}
}
