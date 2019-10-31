package com.example.intent_action_data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏显示


        Button phone = findViewById(R.id.phone);
        Button message = findViewById(R.id.message);
        phone.setOnClickListener(listener);
        message.setOnClickListener(listener);


    }

    View.OnClickListener listener=new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            Button button = (Button) view;
            switch (button.getId()){
                case R.id.phone:
                    intent.setAction(intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:17671426524"));
                    startActivity(intent);
                    break;
                case R.id.message:
                    intent.setAction(intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("smsto:17671426524"));
                    intent.putExtra("sms_body","Android");
                    startActivity(intent);
                    break;
            }
        }
    };

}
