package com.swufestu.exam1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    Button btn = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {//监听两个按钮
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn:
                    Intent intent = new Intent();
                    intent.setClass(FirstActivity.this, MainActivity.class);
                    startActivity(intent);
                    FirstActivity.this.finish();
                    break;
                    case R.id.btn1:
                        finish();
                        break;
                }
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {//创建菜单
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {//设置菜单栏的事件
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.setting:
                Intent intent = new Intent();
                intent.setClass(FirstActivity.this, SettingActivity.class);
                startActivity(intent);
                FirstActivity.this.finish();

                break;

            case R.id.help:
                break;
        }
        return true;
    }

}