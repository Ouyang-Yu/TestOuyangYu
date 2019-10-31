package com.example.qqalbum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private int[] picture=new int []{
            R.drawable.img1,  R.drawable.img2,  R.drawable.img3,
            R.drawable.img4,  R.drawable.img5,
            R.drawable.img6,  R.drawable.img7,  R.drawable.img8,  R.drawable.img9
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(new ImageAdapter(this));
        //网格视图添加适配器
    }
    public class ImageAdapter extends BaseAdapter{
        private Context mContext;

        public ImageAdapter(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
          return picture.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageView imageView;
            if (view==null){
                imageView = new ImageView(mContext);
                imageView.
                        setLayoutParams(new GridView.LayoutParams(300, 500));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);//缩放
            }
            else {
                imageView = (ImageView)view;
            }
            imageView.setImageResource(picture[i]);
            return imageView;
        }
    }
}
