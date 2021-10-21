package com.swufestu.exam1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView cScore;
    private static MainActivity mainActivity = null;//创建静态变量mainActivity
    private int score = 0;//计分器

    public MainActivity(){
        mainActivity = this;//给静态变量mainActivity赋值
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cScore = (TextView) findViewById(R.id.tScore);//获取分数

    }

    public void clearScore(){//重置分数
        score = 0;
        showScore();
    }
    public void showScore(){//分数呈现
        cScore.setText(score+"");
    }
    public void addScore(int sc){
        score = score+sc;
        showScore();
    }

    public static MainActivity getMainActivity() {
        return mainActivity;
    }
}
