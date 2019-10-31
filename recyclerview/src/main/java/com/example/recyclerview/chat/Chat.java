package com.example.recyclerview.chat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class Chat extends AppCompatActivity {
	private List<MsgItem> messageList = new ArrayList<>();

	private EditText inputET;
	private Button send;
	private RecyclerView msgRecycler;
	private ChatMsgAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);


		Toolbar toolbar = findViewById(R.id.chat_toolbar);
		setSupportActionBar(toolbar);

		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
		}






		initMsg();
		inputET = findViewById(R.id.input_text);
		send = findViewById(R.id.send);
		msgRecycler=findViewById(R.id.recyclerView);
		msgRecycler.setLayoutManager(new LinearLayoutManager(this));
		adapter = new ChatMsgAdapter(messageList);
		msgRecycler.setAdapter(adapter);






		send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String content = inputET.getText().toString();
				if (!content.equals("")) {




					messageList.add(new MsgItem(content, MsgItem.SEND));

					adapter.notifyItemInserted(messageList.size() - 1);

					//在原来最后一个item插入一个
					/*if (!answerTo(content).equals("")) {

						messageList.add(new MsgItem(answerTo(content), MsgItem.RECEIVE));
						adapter.notifyItemInserted(messageList.size() - 1);
					}*/



					msgRecycler.scrollToPosition(messageList.size() - 1);
					//滚动到插入的那个(最后)
					inputET.setText(answerTo(content));//清空
				}
			}
		});
	}


	/**
	 * 以下想实现自动对话,但是没与成功
	 * @param question
	 * @return
	 */
		public static String answerTo(String question){
			String out = null;
			try {
				String info = URLEncoder.encode(question,"utf-8");
				URL url = new URL("http://www.tuling123.com/openapi/api?key="
						+ "6bf5fdfc37c54f88bd0f201fff2a1376" + "&info=" + info);
				//System.out.println(url);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setConnectTimeout(10 * 1000);
				connection.setRequestMethod("GET");
				int code = connection.getResponseCode();
				if (code == 200){
					InputStream inputStream = connection.getInputStream();
					String result = steamToString(inputStream);
					JSONObject object = new JSONObject(result);
					out = object.getString("text");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return out;
		}

	public static String steamToString(InputStream in) {
		String result = "";
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length = 0;
			while ((length = in.read(buffer)) != -1) {
				out.write(buffer, 0, length);
				out.flush();
			}
			result = new String(out.toByteArray(), "utf-8");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}





	private void initMsg() {
		messageList.add(new MsgItem("客服正在赶来", MsgItem.RECEIVE));
		messageList.add(new MsgItem("好吧...", MsgItem.SEND));
		messageList.add(new MsgItem("您好٩(๑❛ᴗ❛๑)۶", MsgItem.RECEIVE));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.kefu_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		if (item.getItemId() == R.id.call) {
			Intent intent = new Intent();
			intent.setAction(intent.ACTION_DIAL);
			intent.setData(Uri.parse("tel:17671426524"));
			startActivity(intent);
		}
		return true;
	}
}
