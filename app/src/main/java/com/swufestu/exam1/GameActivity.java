package com.swufestu.exam1;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

public class GameActivity extends GridLayout {
    private static final String TAG = "GameActivity";

    public GameActivity(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initGameActivity();
    }

    public GameActivity(Context context) {
        super(context);
        initGameActivity();
    }

    public GameActivity(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGameActivity();
    }

    private void initGameActivity(){

        setColumnCount(4);//设置布局为4列
        addCards(GetcWidth(),GetcWidth());//添加卡片
        setOnTouchListener(new OnTouchListener() {//监听用户触摸动作
            private float X;
            private float Y;
            private float offsetX;
            private float offsetY;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {//获取四个移动方向开始位置和结束位置
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN://向下滑动
                        X = motionEvent.getX();
                        Y = motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_UP://向上滑动
                        offsetX = motionEvent.getX() - X;
                        offsetY = motionEvent.getY() - Y;
                        if (Math.abs(offsetX) > Math.abs(offsetY)) {//说明用户意图是在水平方向上的移动
                            if (offsetX < -5) {//向左移动
                                Log.i(TAG, "onTouch: Left");
                            } else if (offsetX > 5) {//向右移动
                                Log.i(TAG, "onTouch: Right");
                            }
                        } else {
                            if (offsetY < -5) {
                                Log.i(TAG, "onTouch: Up");
                            } else if (offsetY > 5) {
                                Log.i(TAG, "onTouch:Down");
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
    private int GetcWidth(){//获取系统宽高
        //屏幕信息的对象
        DisplayMetrics dm = getResources().getDisplayMetrics();
        //获取屏幕信息
        int cWidth;
        cWidth = dm.widthPixels;
        return (cWidth-10)/4;//留出图片和边缘的大小
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {//布局大小随系统自动变化
        super.onSizeChanged(w, h, oldw, oldh);
    }
    private void addCards(int cWidth,int cHeight){
        CardActivity card;
        //4*4
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                card = new CardActivity(getContext());   //初始化卡片对象
                card.setNum(2);					//初始值为0
                addView(card,cWidth,cHeight);//添加

            }
        }
    }
}




