package com.example.relative;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.os.Bundle;

public class ToActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to);

            //如果父Activity不为空,则设置导航图标显示
        if (NavUtils.getParentActivityName(ToActivity.this) != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //设置向左的箭头图标
        }
    }
}
