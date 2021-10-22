package com.swufestu.exam1;
import android.content.Context;
import android.content.SharedPreferences;



public class MaxScore {
    String flag;
    private SharedPreferences sp;
    SharedPreferences.Editor editor;

    public MaxScore(Context context, String flag) {//flag是缓存的唯一标识
        this.flag = flag;
        //数据只能被本应用程序读写
        sp = context.getSharedPreferences(this.flag, context.MODE_PRIVATE);
        editor = sp.edit();
    }

    //存入key对应的数据
    public void putMaxScore(String key, int mscore) {
        editor.putInt(key, mscore);
        //提交所做的修改
        editor.commit();

    }

    public String getValue(String key, String Mscore) {
        return sp.getString(key,Mscore);
    }
    public void clearmscore(){
        editor.clear();
        //提交所做的修改
        editor.commit();
    }

}
