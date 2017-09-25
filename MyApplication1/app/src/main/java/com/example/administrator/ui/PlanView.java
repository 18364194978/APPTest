package com.example.administrator.ui;
//(#127改View负责绘制飞机)
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.example.administrator.myapplication1.R;

/**
 * Created by xie on 2017/9/25.
 */

public class PlanView extends View {
    public float currentX;
    public float currentY;
    Bitmap plane;
    public PlanView(Context context){
        super(context);
        //定义飞机图片
        plane = BitmapFactory.decodeResource(context.getResources(), R.drawable.plane);
        setFocusable(true);
    }
    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint p = new Paint();
        canvas.drawBitmap(plane,currentX,currentY,p);
    }
}
