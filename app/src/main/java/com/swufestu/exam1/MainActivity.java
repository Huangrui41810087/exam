package com.swufestu.exam1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {
    private TextView cScore;
    private static MainActivity mainActivity = null;//创建静态变量mainActivity
    private int score = 0;//计分器

    public MainActivity() {
        mainActivity = this;//给静态变量mainActivity赋值
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cScore = (TextView) findViewById(R.id.tScore);//获取分数


    }


    public void click(View replay) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("提示：");
        dialog.setMessage("你确定重新开始吗？");
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }

    public void clickb(View end) {
        AlertDialog.Builder dialog1 = new AlertDialog.Builder(this);
        dialog1.setTitle("提示：");
        dialog1.setMessage("你确定退出吗？");
        dialog1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
            }
        });

        dialog1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialog1.show();
    }


    public void clearScore() {//重置分数
        score = 0;
        showScore();
    }

    public void showScore() {//分数呈现
        cScore.setText(score + "");
    }

    public void addScore(int sc) {
        score = score + sc;
        showScore();
    }

    public static MainActivity getMainActivity() {
        return mainActivity;
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId()==R.id.help) {

            AlertDialog.Builder dialog;
            dialog = new AlertDialog.Builder(this);
            dialog.setTitle("hey,guy！");
            dialog.setMessage("这么简单的游戏你确定需要帮助？");
            dialog.setNegativeButton("继续玩~", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dialog.show();

        }
        return true;
    }



}



