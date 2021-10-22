package com.swufestu.exam1;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class CardActivity extends FrameLayout {
    private TextView tv;
    public CardActivity(Context context) {
        super(context);
        tv = new TextView(getContext());
        tv.setTextSize(48);
        tv.setBackgroundColor(0x3300BFFF);
        tv.setGravity(Gravity.CENTER);
        //为卡片增加布局参数
        LayoutParams lp = new LayoutParams(-1,-1);//填充满
        lp.setMargins(10,10,0,0);
        addView(tv,lp);

        setNum(0);
    }

    private int num = 0;

    public int getNum(){
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        if(num<=0) {//如果数字为0，得到空字符串
            tv.setText("");
        }else{
            tv.setText(num+"");//将整形数转换为字符串
        }
    }


    public boolean equals(CardActivity o) {
        return this.getNum()==o.getNum();
    }

    // 设置背景色
    private int defaultBackColor = 0x338B8B00;

    private int getBackColor(int num) {

        int bgcolor = defaultBackColor;
        switch (num) {
            case 0:
                bgcolor = 0xffCCC0B3;
                break;
            case 2:
                bgcolor = 0xffEEE4DA;
                break;
            case 4:
                bgcolor = 0xffEDE0C8;
                break;
            case 8:
                bgcolor = 0xffF2B179;// #F2B179
                break;
            case 16:
                bgcolor = 0xffF49563;
                break;
            case 32:
                bgcolor = 0xffF5794D;
                break;
            case 64:
                bgcolor = 0xffF55D37;
                break;
            case 128:
                bgcolor = 0xffEEE863;
                break;
            case 256:
                bgcolor = 0xffEDB04D;
                break;
            case 512:
                bgcolor = 0xffECB04D;
                break;
            case 1024:
                bgcolor = 0xffEB9437;
                break;
            case 2048:
                bgcolor = 0xffEA7821;
                break;
            default:
                bgcolor = 0xffEA7821;
                break;
        }
        return bgcolor;
    }



}
