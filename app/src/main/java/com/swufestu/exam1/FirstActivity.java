package com.swufestu.exam1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
                     System.exit(0);
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
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("开发者：HR");
                dialog.setMessage("开发时间：2021年10月20日");
                dialog.setPositiveButton("返回首页", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setClass( FirstActivity.this, FirstActivity.class);
                        startActivity(intent);
                    }
                });
                dialog.show();
                break;
        }
        return true;
    }

}