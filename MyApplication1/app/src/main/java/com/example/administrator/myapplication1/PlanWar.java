package com.example.administrator.myapplication1;
//(#127该游戏不需要界面布局，直接使用PlanView作为activity显示内容)
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.administrator.ui.PlanView;

/**
 * Created by xie on 2017/9/25.
 */

public class PlanWar extends Activity {
    //定义飞机的移动速度
    private int speed = 10;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //去掉窗口标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏展示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //创建planeview组件
        final PlanView planView = new PlanView(this);
        setContentView(planView);
        planView.setBackgroundResource(R.drawable.back);
        //获取窗口管理器
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        //获得屏幕宽高
        display.getMetrics(metrics);
        //设置飞机初始位置
        planView.currentX = metrics.widthPixels/2;
        planView.currentY = metrics.heightPixels -40;
        //为plan组件的键盘事件绑定监听器
        planView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //获取由哪个键触发的事件
                switch (event.getKeyCode()){
                    //控制飞机下移
                    case KeyEvent.KEYCODE_S:
                        planView.currentY +=speed;
                        break;
                    case KeyEvent.KEYCODE_W:
                        planView.currentY -=speed;
                        break;
                    case KeyEvent.KEYCODE_A:
                        planView.currentX -=speed;
                        break;
                    case KeyEvent.KEYCODE_D:
                        planView.currentX +=speed;
                        break;
                }
                //重绘飞机
                planView.invalidate();
                return true;
            }
        });

    }
}
