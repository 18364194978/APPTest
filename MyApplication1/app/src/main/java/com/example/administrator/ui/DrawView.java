package com.example.administrator.ui;
//(#134)
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by xie on 2017/9/27.
 */

public class DrawView extends View {
    //定义记录前一个拖动时间发生的地点
    float preX;
    float preY;
    private Path path;
    public Paint paint = null;
    //定义内存中一个图片，该图片作为缓冲区
    Bitmap cacheBitmap = null;
    //定义cacheBitmap上的Canvas对象
    Canvas cacheCanvas = null;
    public DrawView(Context context,int width,int height){
        super(context);
        //创建一个与该view相同大小的缓冲区
        cacheBitmap = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
        cacheCanvas = new Canvas();
        path = new Path();
        //设置cacheCavas将会绘制到内存中的cacheBitmap
        cacheCanvas.setBitmap(cacheBitmap);
        //设置画笔的颜色
        paint = new Paint(Paint.DITHER_FLAG);
        paint.setColor(Color.RED);
        //设置画笔的风格
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        //反锯齿
        paint.setAntiAlias(true);
        paint.setDither(true);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        //获取拖动时间的发声位置
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //从前一个点绘制到当前点后，把当前点定义成下次绘制的前一个点
                path.moveTo(x,y);
                preX = x;
                preY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                //从前一个点绘制到当前点后，把当前点定义成下次绘制的前一个点
                path.quadTo(preX,preY,x,y);
                preX = x;
                preY = y;
                break;
            case MotionEvent.ACTION_UP:
                cacheCanvas.drawPath(path,paint);
        }
        invalidate();
        //返回true表明处理方法已处理该事件
        return true;
    }
    @Override
    public void onDraw(Canvas canvas){
        Paint bmpPaint = new Paint();
        //将cacheBitmap绘制到该view组件上
        canvas.drawBitmap(cacheBitmap,0,0,bmpPaint);
        //沿着path绘制
        canvas.drawPath(path,paint);
    }
}
