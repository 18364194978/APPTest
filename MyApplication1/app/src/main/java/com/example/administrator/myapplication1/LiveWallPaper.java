package com.example.administrator.myapplication1;
//#152动态手机壁纸
//1、开发一个子类继承WallPaperService基类
//2、继承WallPaperService基类时必须重写onCreateEngine()方法，该方法返回WallPaperService.Engine子类对象
//3、开发者需要实现wallpaperservice.Engine子类，并重写其中的public void onVisbilityChanged(boolean visible),
// public void onOffsetsChanged()方法。不仅如此，由于.Engine子类采用了与SurfaceView相同的的绘图机智，
// 因此可以有选择性的重写SurfaceHolder.Callback中的三个方法。重写这些方法可通过surfaceHolder动态的绘制图形
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import java.util.Random;

/**
 * Created by xie on 2017/10/12.
 */

public class LiveWallPaper extends WallpaperService {
    //记录用户触碰点的位图
    private Bitmap heart;
    //实现WallpaperService必须实现的抽象方法
    @Override
    public Engine onCreateEngine(){
        //加载心形图片
        heart = BitmapFactory.decodeResource(getResources(),R.drawable.plane);
        //返回自定义的Engine
        return  new MyEngine();
    }
    class MyEngine extends Engine {
        //记录程序界面是否可见
        private boolean mVisible;
        //记录当前用户动作事件的发生位置
        private float mTouchX = -1;
        private float mTouchY = -1;
        //记录当前需要绘制的矩形的数量
        private int count = 1;
        //记录绘制第一个矩形所需坐标变换的XY坐标的偏移
        private int originX = 50,originY = 50;
        //定义画笔
        private Paint mPaint = new Paint();
        //定义一个Handler
        Handler mHandler = new Handler();
        //定义一个周期性执行的任务
        private final Runnable drawTarget = new Runnable() {
            @Override
            public void run() {
                drawFrame();
            }
        };
        public void onCreate(SurfaceHolder surfaceHolder){
            super.onCreate(surfaceHolder);
            //初始化画笔
            mPaint.setARGB(76,0,0,255);
            mPaint.setAntiAlias(true);
            mPaint.setStyle(Paint.Style.FILL);
            //设置处理触摸事件
            setTouchEventsEnabled(true);
        }
        @Override
        public void onDestroy(){
            super.onDestroy();
            //删除回调
            mHandler.removeCallbacks(drawTarget);
        }
        @Override
        public void onVisibilityChanged(boolean visible){
            mVisible = visible;
            //当界面可见时，执行drawFrame方法（）
            if (visible){
                drawFrame();
            }else {
                //若界面不可见则删除回调
                mHandler.removeCallbacks(drawTarget);
            }
        }
        @Override
        public void onOffsetsChanged(float xOffset,float yOffset ,float xStep
        ,float yStep,int xPixels,int yPixels){
            drawFrame();
        }
        @Override
        public void onTouchEvent(MotionEvent event){
            //如果检测到滑动动作
            if (event.getAction() == MotionEvent.ACTION_MOVE){
                mTouchX = event.getX();
                mTouchY = event.getY();
            }else {
                mTouchX = -1;
                mTouchY = -1;
            }
            super.onTouchEvent(event);
        }
        //定义绘图工具的方法
        private  void drawFrame(){
            //获取该壁纸的SurfaceHolder
            final SurfaceHolder holder = getSurfaceHolder();
            Canvas c = null;
            try {
                //对画布枷锁
                c= holder.lockCanvas();
                if (c!=null){
                    //绘制背景色
                    c.drawColor(0xffffffff);
                    //在触碰点绘制心形
                    drawTouchPoint(c);
                    //设置画笔透明度
                    mPaint.setAlpha(76);
                    c.translate(originX,originY);
                    //采用循环绘制count个矩形
                    for (int i = 0;i<count;i++){
                        c.translate(80,0);
                        c.scale(0.95f,0.95f);
                        c.rotate(20f);
                        c.drawRect(0,0,150,75,mPaint);
                    }
                }
            }finally {
                if (c!=null)holder.unlockCanvasAndPost(c);
            }
            mHandler.removeCallbacks(drawTarget);
            //调度下一次重绘
            if (mVisible){
                count++;
                if (count>=50){
                    Random rand = new Random();
                    count = 1;
                    originX+=(rand.nextInt(60)-30);
                    originY+=(rand.nextInt(60)-30);
                    try {
                        Thread.sleep(500);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                //指定0.1秒后重新执行drawtarget一次
                mHandler.postDelayed(drawTarget,100);
            }
        }
        //在屏幕触碰点绘制圆圈
        private void drawTouchPoint(Canvas c){
            if (mTouchX >= 0 && mTouchY >= 0){
                //设置画笔的透明度
                mPaint.setAlpha(255);
                c.drawBitmap(heart,mTouchX,mTouchY,mPaint);
            }
        }
    }

}
