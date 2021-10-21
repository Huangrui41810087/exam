package com.swufestu.exam1;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.TextView;

public class CardActivity extends FrameLayout {
    private TextView tv;
    public CardActivity(Context context) {
        super(context);
        tv = new TextView(getContext());
        tv.setTextSize(24);
        //为卡片增加布局参数
        LayoutParams lp = new LayoutParams(-1,-1);//填充满
        addView(tv,lp);

        setNum(0);
    }

    private int num = 0;

    public int getNum(){
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        tv.setText(num+"");//将整形数转换为字符串
    }


    public boolean equals(CardActivity o) {
        return getNum()==o.getNum();
    }




}
