package com.example.tabhost;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = findViewById(android.R.id.tabhost);
        tabHost.setup();


        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.left, tabHost.getTabContentView());
        inflater.inflate(R.layout.right, tabHost.getTabContentView());


        tabHost.addTab(tabHost.newTabSpec("left")
                .setIndicator("高锦良").setContent(R.id.left));
        tabHost.addTab(tabHost.newTabSpec("right")
                .setIndicator("小丑女").setContent(R.id.right));
    }

    /**  R.layout.left
     * <LinearLayout
     *     android:id="@+id/left"
     *     android:orientation="vertical"
     *     android:layout_width="match_parent"
     *     android:layout_height="match_parent"
     *     >
     *
     *     <ImageView
     *         android:layout_width="match_parent"
     *         android:layout_height="match_parent"
     *         android:src="@drawable/gao"/>
     * </LinearLayout>
     */


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}

