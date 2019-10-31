package com.example.first;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView[]img= new ImageView[12];
    private int[] imagePath = {
            R.mipmap.img02,
            R.mipmap.img02,
            R.mipmap.img02,
            R.mipmap.img02,
            R.mipmap.img02,
            R.mipmap.img02,
            R.mipmap.img02,R.mipmap.img02,R.mipmap.img02,R.mipmap.img02,R.mipmap.img02


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout gridLayout = findViewById(R.id.layout);
        for (int i = 0; i < imagePath.length; i++) {
            img[i]=new ImageView(MainActivity.this);
            img[i].setImageResource(imagePath[i]);
            img[i].setPadding(2, 2, 2, 2);
            img[i].setLayoutParams(new ViewGroup.LayoutParams(116,88));
            //设置一个图片的宽和高
            gridLayout.addView(img[i]);//把每个图片加到面边上
        }
    }
}
