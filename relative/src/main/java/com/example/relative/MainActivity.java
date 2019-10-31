package com.example.relative;




import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.actionview, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.copy:
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
                break;
            case R.id.collect:
                Intent intent1 = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent1);
        }
        return super.onOptionsItemSelected(item);
    }

    private long exitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);


        }

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        // super.onCreateContextMenu(menu, v, menuInfo);
        // menu.add("really?");
        // menu.add("I'so excited");
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.copy:
                Toast.makeText(MainActivity.this, " copy", Toast.LENGTH_SHORT).show();
                break;
            case R.id.collect:
                Toast.makeText(MainActivity.this, "collect", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sure = findViewById(R.id.sure);
        sure.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                registerForContextMenu(view);
                openContextMenu(view);
                return false;
            }
        });

        //组件注册上下文菜单
        Button yes = findViewById(R.id.yes);
        registerForContextMenu(yes);

        final ActionBar actionBar = getSupportActionBar();

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionBar.show();
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionBar.hide();
            }
        });


        Button toButton = findViewById(R.id.to);
        toButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ToActivity.class);
                startActivity(intent);
            }
        });
        Button b1 = findViewById(R.id.yesorno);
        Button b2 = findViewById(R.id.list);
        Button b3 = findViewById(R.id.radioButton);
        Button b4 = findViewById(R.id.checkbox);

        b1.setOnClickListener(new View.OnClickListener() {//带取消确定按钮的对话框
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                //在页面中创建对话框
                alertDialog.setIcon(R.drawable.ic_launcher_background);
                alertDialog.setTitle("高锦良");
                alertDialog.setMessage("I love you");
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "不再想想嘛", Toast.LENGTH_SHORT).show();

                    }
                });
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "对你好一辈子", Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.show();

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {//带列表显示的对话框
            @Override
            public void onClick(View view) {
                final String[] items = new String[]{
                        "1", "2", "3", "4"
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(R.drawable.ic_launcher_background);
                builder.setTitle("你喜欢我哪一点");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "你喜欢我" + items[i] + "对吗", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.create().show(); //创建并显示对话框
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {//单选列表
            @Override
            public void onClick(View view) {
                final String[] items = new String[]{
                        "1", "2"
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(R.drawable.ic_launcher_background);
                builder.setTitle(" choose");
                builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "you choose " + items[i] + " right", Toast.LENGTH_SHORT).show();
                    }
                });//添加列表项,设置默认选中,并添加监听器
                builder.setPositiveButton("确定", null);
                builder.create().show();
            }
        });

       b4.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick (View view){
            final boolean[] checkItems = new boolean[]{
                    false, false, false
            };
            final String[] items = new String[]{
                    "gao", "jin", "liang"
            };
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setIcon(R.drawable.ic_launcher_background);
            builder.setTitle("What do you like");
            builder.setMultiChoiceItems(items, checkItems, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                    checkItems[i] = b;//改变是否选中状态数组
                }
            });
            //添加多选列表目录和状态
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String result = "";
                    for (int j = 0; j < checkItems.length; j++) {
                        if (checkItems[j]) {
                            result += items[j] + ",";
                        }
                    }
                    if (!result.equals("")) {
                        Toast.makeText(MainActivity.this, "you like" + result, Toast.LENGTH_SHORT).show();
                    }
                }
            });//点击确定,获取结果,发生事件
            builder.create().show();
        }

    });

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder notification = new Notification.Builder(MainActivity.this);
        notification.setAutoCancel(true);//通知打开后自动消失
        notification.setSmallIcon(R.drawable.ic_launcher_background);
        notification.setContentTitle("大奖来袭");
        notification.setContentText("点击查看详情");
        notification.setWhen(System.currentTimeMillis());//设置通知发送时间
        notification.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
        //设置通知的声音和振动

        Intent intent = new Intent(MainActivity.this, ToActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
        notification.setContentIntent(pendingIntent);
        notificationManager.notify(NOTIFYID, notification.build());
        //点击通知跳转,而不是立即启动intent
}
        final int NOTIFYID=0x123;//通知ID

}
