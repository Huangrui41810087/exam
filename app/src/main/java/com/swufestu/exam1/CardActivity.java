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
        switch (num) {
            case 0:
                tv.setBackgroundColor(0xffCCC0B3);
                break;
            case 2:
                tv.setBackgroundColor(0xffEEE4DA);
                break;
            case 4:
                tv.setBackgroundColor(0xffEDE0C8);
                break;
            case 8:
                tv.setBackgroundColor(0xffF2B179);
                break;
            case 16:
                tv.setBackgroundColor(0xffF49563);
                break;
            case 32:
                tv.setBackgroundColor(0xffF5794D);
                break;
            case 64:
                tv.setBackgroundColor(0xffF55D37);
                break;
            case 128:
                tv.setBackgroundColor(0xffEEE863);
                break;
            case 256:
                tv.setBackgroundColor(0xffEDB04D);
                break;
            case 512:
                tv.setBackgroundColor(0xffECB04D);
                break;
            case 1024:
                tv.setBackgroundColor(0xffEB9437);
                break;
            case 2048:
                tv.setBackgroundColor(0xffEA7821);
                break;
            default:
                tv.setBackgroundColor(0xffEA7821);
                break;

        }
    }

    public TextView getTvCard(){return tv;}



    public boolean equals(CardActivity o) {
        return this.getNum()==o.getNum();
    }




}
