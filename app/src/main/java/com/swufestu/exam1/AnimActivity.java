package com.swufestu.exam1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AnimActivity extends FrameLayout {

    private List<CardActivity> cards = new ArrayList<CardActivity>();

    public AnimActivity(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public AnimActivity(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public AnimActivity(@NonNull Context context) {
        super(context);
    }
    public void creatMoveAnim(final CardActivity from,final CardActivity to,int fromX,int toX,int fromY,int toY){//创建滑动效果
        final CardActivity card = getCard(from.getNum());
        int cWidth=getResources().getDisplayMetrics().widthPixels;
        LayoutParams lp = new LayoutParams(cWidth,cWidth);
        lp.leftMargin = fromX*cWidth;
        lp.topMargin = fromY*cWidth;
        card.setLayoutParams(lp);

        if (to.getNum()<=0) {
            to.getTvCard().setVisibility(View.INVISIBLE);
        }
                //创建移动效果
            TranslateAnimation ta = new TranslateAnimation(0, cWidth * (toX - fromX), 0, cWidth * (toY - fromY));
            ta.setDuration(100);

        ta.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationRepeat(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                to.getTvCard().setVisibility(View.VISIBLE);
                recycleCard(card);
            }
        });
        card.startAnimation(ta);
    }

    private CardActivity getCard(int num){
        CardActivity c;
        if (cards.size()>0) {
            c = cards.remove(0);
        }else{
            c = new CardActivity(getContext());
            addView(c);
        }
        c.setVisibility(View.VISIBLE);
        c.setNum(num);
        return c;
    }

    private void recycleCard(CardActivity card){
        card.setVisibility(View.INVISIBLE);
        card.setAnimation(null);
        cards.add(card);
    }


    public static void showScrollAnim(CardActivity ta){
        ScaleAnimation sa = new ScaleAnimation(0.1f, 1, 0.1f, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(100);
        ta.setAnimation(null);
        ta.getTvCard().startAnimation(sa);
    }
}
