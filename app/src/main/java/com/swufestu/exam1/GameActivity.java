package com.swufestu.exam1;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import android.content.Intent;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;

import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.GridLayout;


import java.util.ArrayList;
import java.util.List;

public class GameActivity extends GridLayout {
    private static final String TAG = "GameActivity";
    private CardActivity[][] cMap=new CardActivity[4][4];//利用二维数组来存放16张卡片
    private List<Point> emptyPoints = new ArrayList<Point>();//利用数组来存放空点位置

    public GameActivity(Context context) {
        super(context);
        initGameActivity();
    }

    public GameActivity(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGameActivity();
    }

    public GameActivity(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initGameActivity();
    }

    private int GetcWidth(){//获取系统宽高
        //屏幕信息的对象
        DisplayMetrics dm = getResources().getDisplayMetrics();
        //获取屏幕信息
        int cWidth;
        cWidth = dm.widthPixels;
        return (cWidth-10)/4;//留出图片和边缘的大小
    }
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {//布局大小随系统自动变化
        super.onSizeChanged(w, h, oldw, oldh);
        startGame();
    }



    private void initGameActivity(){//初始化游戏页面

        setBackgroundColor(0xffE0FFFF);
        setColumnCount(4);//设置游戏布局为4列
        addCards(GetcWidth(),GetcWidth());//添加卡片

        setOnTouchListener(new View.OnTouchListener(){//监听用户触摸动作
            private float X;
            private float Y;
            private float offsetX;
            private float offsetY;

            //触控交互设计
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {//获取四个移动方向开始位置和结束位置
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN://向下滑动
                        X = motionEvent.getX();//获取开始位置X坐标
                        Y = motionEvent.getY();//获取开始位置Y坐标
                        break;
                    case MotionEvent.ACTION_UP://向上滑动
                        offsetX = motionEvent.getX() - X;//获取X方向偏移量
                        offsetY = motionEvent.getY() - Y;//获取Y方向偏移量

                        if (Math.abs(offsetX) > Math.abs(offsetY)) {//说明用户像斜方向滑动时意图是在水平方向上的移动
                            if (offsetX < -5) {//向左移动
                                moveLeft();
                                Log.i(TAG, "onTouch: Left");
                            } else if (offsetX > 5) {//向右移动
                                moveRight();
                                Log.i(TAG, "onTouch: Right");
                            }
                        } else {//说明用户像斜方向滑动时意图是在垂直方向上的移动
                            if (offsetY < -5) {
                                moveUp();
                                Log.i(TAG, "onTouch: Up");
                            } else if (offsetY > 5) {
                                moveDown();
                                Log.i(TAG, "onTouch: Down");
                            }
                        }
                        break;
                    default:
                        break;
                }
                return true;//设置值为true可以监听到上下左右四个方向的动作
            }
        });
    }

    private void startGame(){//开始游戏

        MainActivity.getMainActivity().clearScore();//清空当前分数
        for (int i=0;i<4;i++){
            for (int j=0;j<4;j++){
                cMap[i][j].setNum(0);//游戏开始时都初始为0
            }
        }
        RandomNum();
        RandomNum();
    }


    private void addCards(int cWidth,int cHeight){//添加卡片对象
        CardActivity card;
        //4*4
        for(int j=0;j<4;j++){
            for(int i=0;i<4;i++){
                card = new CardActivity(getContext());//初始化卡片对象
                card.setNum(0);					//初始值为0
                addView(card,cWidth,cHeight);//为卡片增加布局参数
                cMap[i][j] = card;//将卡片位置用数组存放
            }
        }
    }


    private void RandomNum(){//添加随机数
        emptyPoints.clear();//添加随机数之前先清空emptyPoints,然后把每一个空点都添加进来

        for(int j=0;j<4;j++){  //先对所有值进行遍历
            for(int i=0;i<4;i++){
                if(cMap[i][j].getNum()<=0){////如果卡片的值是空值，才能赋值
                    emptyPoints.add(new Point(i,j));
                }
            }
        }
        Point point = emptyPoints.remove((int)(Math.random()*emptyPoints.size()));//选择一个点，随机移除点,仅仅是移除emptyPoints中记录的点
        double P = Math.random();//生成随机概率值
        int num;
        if (P <= 0.9) {
            num = 2;
        } else if (P <= 0.93){
            num= 4;
        } else if(P <= 0.95){
            num = 8;
        } else if(P <= 0.97){
            num = 16;
        } else if(P <= 0.98){
            num = 32;
        } else if(P <= 0.99){
            num = 64;
        } else if(P <= 0.995){
            num = 128;
        } else if(P <= 0.999){
            num = 256;
        } else{
            num = 512;
        }

        cMap[point.x][point.y].setNum(num);//为空点赋值
        ScrollAnim(cMap[point.x][point.y]);//设置缩放动画
    }

    private void moveLeft(){//从左向右遍历
        boolean merge = false;//判断是否有卡片合并，默认是false
        for(int j=0;j<4;j++){
            for(int i=0;i<4;i++){
                for(int i1=i+1;i1<4;i1++){//遍历当前位置右边的值
                    if(cMap[i1][j].getNum()>0){//当前位置右边一个点的值>o,需要考虑当前位置的两种情况
                        if(cMap[i][j].getNum()<=0){
                            cMap[i][j].setNum(cMap[i1][j].getNum());
                            cMap[i1][j].setNum(0);
                            i--;//往左遍历
                            merge = true;
                        }
                        else if(cMap[i][j].equals(cMap[i1][j])){
                            cMap[i][j].setNum(cMap[i][j].getNum()*2);//合并相同的数
                            cMap[i1][j].setNum(0);
                            MainActivity.getMainActivity().addScore(cMap[i][j].getNum());//在卡片合并时进行加分
                            merge = true;
                        }
                        break;
                    }
                }
            }
        }
        if(merge){//只要有合并就添加随机数
            RandomNum();
            endGame();//添加新项后都要检查游戏是否结束：没空位置，而且已经不能再合并
        }
    }

    private void moveRight(){//从右向左遍历
        boolean merge = false;
        for(int j=0;j<4;j++){
            for(int i=3;i>=0;i--){
                for(int i1=i-1;i1>=0;i1--){
                    if(cMap[i1][j].getNum()>0){
                        if(cMap[i][j].getNum()<=0){
                            cMap[i][j].setNum(cMap[i1][j].getNum());
                            cMap[i1][j].setNum(0);
                            i++;//往右遍历
                            merge = true;
                        }
                        else if(cMap[i][j].equals(cMap[i1][j])){
                            cMap[i][j].setNum(cMap[i][j].getNum()*2);
                            cMap[i1][j].setNum(0);
                            merge = true;
                            MainActivity.getMainActivity().addScore(cMap[i][j].getNum());//在卡片合并时进行加分
                        }
                        break;
                    }
                }
            }
        }
        if(merge){
            RandomNum();
            endGame();
        }
    }

    private void moveUp(){//从上向下遍历
        boolean merge = false;
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                for(int j1=j+1;j1<4;j1++){
                    if(cMap[i][j1].getNum()>0){
                        if(cMap[i][j].getNum()<=0){
                            cMap[i][j].setNum(cMap[i][j1].getNum());
                            cMap[i][j1].setNum(0);
                            j--;//往下遍历
                            merge = true;
                        }
                        else if(cMap[i][j].equals(cMap[i][j1])){
                            cMap[i][j].setNum(cMap[i][j].getNum()*2);
                            cMap[i][j1].setNum(0);
                            MainActivity.getMainActivity().addScore(cMap[i][j].getNum());//在卡片合并时进行加分
                            merge = true;
                        }
                        break;
                    }
                }
            }
        }
        if(merge){
            RandomNum();
            endGame();
        }
    }

    private void moveDown(){//从下向上遍历
        boolean merge = false;
        for(int i=0;i<4;i++){
            for(int j=3;j>=0;j--){
                for(int j1=j-1;j1>=0;j1--){
                    if(cMap[i][j1].getNum()>0){
                        if(cMap[i][j].getNum()<=0){
                            cMap[i][j].setNum(cMap[i][j1].getNum());
                            cMap[i][j1].setNum(0);
                            j++;
                            merge = true;
                        }
                        else if(cMap[i][j].equals(cMap[i][j1])){
                            cMap[i][j].setNum(cMap[i][j].getNum()*2);
                            cMap[i][j1].setNum(0);
                            MainActivity.getMainActivity().addScore(cMap[i][j].getNum());//在卡片合并时进行加分
                            merge = true;
                        }
                        break;
                    }
                }
            }
        }
        if(merge){
            RandomNum();
            endGame();
        }
    }

    private void endGame(){//游戏结束
        boolean end = true;
        ALL:
        for(int j=0;j<4;j++){
            for(int i=0;i<4;i++){
                if(cMap[i][j].getNum()==0//当前位置卡片有空值
                        ||(i>0&&cMap[i][j].equals(cMap[i-1][j]))//往左方向判断还有合并项
                        ||(i<3&&cMap[i][j].equals(cMap[i+1][j]))//往右方向判断还有合并项
                        ||(j>0&&cMap[i][j].equals(cMap[i][j-1]))//往上方向判断还有合并项
                        ||(j<3&&cMap[i][j].equals(cMap[i][j+1]))//往下方向判断还有合并项
                ){
                    end = false;//游戏继续
                    break ALL;//退出整个循环

                }
            }

        }
        if(end ){//游戏结束页面出现消息对话框
            AlertDialog.Builder dialog = new AlertDialog.Builder
                    (getContext()).setTitle("你好").setMessage("游戏结束了").setPositiveButton("重新开始", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Log.i(TAG, "onClick: 重新开始");
                    startGame();
                }
            });
            dialog.setNegativeButton("退出游戏",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    MainActivity.getMainActivity().finish();
                }
            });//添加两个按钮，监听动作

            dialog.show();

        }

    }
     private void ScrollAnim(CardActivity tv){//为卡片添加缩放动画
        ScaleAnimation sa = new ScaleAnimation(0.1f, 1, 0.1f, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(100);//持续时间
        tv.setAnimation(null);
        tv.getTvCard().startAnimation(sa);
    }

}
