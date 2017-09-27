package com.example.administrator.myapplication1;
//(#132)
import android.animation.AnimatorInflater;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by xie on 2017/9/27.
 */

public class BackColorChange extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backgroundcolorchange);
        LinearLayout container = (LinearLayout) findViewById(R.id.container);
        //添加MyAnimationView组件
        container.addView(new MyAnimationView(this));
    }
    public class MyAnimationView extends View{
        public MyAnimationView(Context context){
            super(context);
            //加载动画资源
            ObjectAnimator colorAnim = (ObjectAnimator) AnimatorInflater.loadAnimator(BackColorChange.this,R.animator.color_anim);
            colorAnim.setEvaluator(new ArgbEvaluator());
            //对该view本身应用属性动画
            colorAnim.setTarget(this);
            //开始指定动画
            colorAnim.start();
        }
    }
}
