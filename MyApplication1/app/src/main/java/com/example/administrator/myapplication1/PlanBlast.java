package com.example.administrator.myapplication1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.icu.text.DateFormat;
import android.icu.text.NumberFormat;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by xie on 2017/9/28.
 */

public class PlanBlast extends Activity {
    private AnimationDrawable anim;
    //定义一个自定义view，用于播放爆炸效果
    class MyView extends ImageView{
        public MyView(Context context){
            super(context);
        }
        //该方法用于控制myview的显示位置
        public void setLocation(int top,int left){
            this.setFrame(left,top,left+40,top+40);
        }
        //播放到最后一针隐藏该view
        @Override
        protected void onDraw(Canvas canvas){
//            try {
//                NumberFormat.Field field = AnimationDrawable.class.getDeclaredField("mCurFrame");
//            }
        }
    }
}
