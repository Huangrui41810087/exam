package com.swufestu.exam1;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private SharedPreferences sp;
    SharedPreferences.Editor editor;
    private int score = 0;//计分器
    private TextView cScore;
    private TextView mScore;

    private static MainActivity mainActivity = null;//创建静态变量mainActivity

    public MainActivity() {
        mainActivity = this;//给静态变量mainActivity赋值
    }

    public static MainActivity getMainActivity() { return mainActivity; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cScore = (TextView) findViewById(R.id.cScore);//获取当前分数
        cScore.setText("0");
        mScore = (TextView) findViewById(R.id.maxscores);//获取最高分数
        mScore.setText("0");
        showMaxScore();
    }

    public void clearScore() {//重置分数
        score = 0;
        showscore();
    }
    public void showscore(){
        cScore.setText(score + "");
    }

    public void addScore(int num) {//计分
        score = score + num;
        showscore();

        if (score > this.getMaxScore()) {
            this.setMaxscore(score);
            mScore.setText(score + "");
        }
    }

    public int getMaxScore() {//获取最高分
        sp = mScore.getContext().getSharedPreferences("maxScore", Context.MODE_PRIVATE);
        int maxScore = sp.getInt("maxScore",0);
        return maxScore;
    }
    public void setMaxscore(int mscore){
        sp = mScore.getContext().getSharedPreferences("maxScore", Context.MODE_PRIVATE);
        editor = sp.edit();
        editor.putInt("maxScore", mscore);
        editor.commit();
    }

    public void showMaxScore() {//在文本框中显示最高分
        mScore.setText(this.getMaxScore() +"");
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
                MainActivity.getMainActivity().finish();
            }
        });

        dialog1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialog1.show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {//创造菜单
        getMenuInflater().inflate(R.menu.my_menu1, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId()==R.id.help1) {
            AlertDialog.Builder dialog;
            dialog = new AlertDialog.Builder(this);
            dialog.setTitle("hey,guy！");
            dialog.setMessage("这么简单的游戏你确定需要提示？");
            dialog.setNegativeButton("继续玩~", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            dialog.show();
        }
        return true;
    }
    private long exitTime = 0;

    @SuppressLint("WrongConstant")
    public boolean onKeyDown(int keyCode, KeyEvent event) {//添加返回键监听

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", 1000).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //KEYCODE_BACK 对应触发事件：按下返回键；
    //ACTION_DOWN 对应触发事件：手指按下按键；


}




