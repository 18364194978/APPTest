package com.example.administrator.myapplication1;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by xie on 2017/9/28.
 */

public class ShowWave extends Activity {
    private SurfaceHolder holder;
    private Paint paint;
    final int HEIGHT = 320;
    final int WIDTH = 768;
    final int X_OFFSET = 5;
    private int cx = X_OFFSET;
    //实际Y轴的位置
    int centerY = HEIGHT / 2;
    Timer timer = new Timer();
    TimerTask task = null;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showwave);
        final SurfaceView surface = (SurfaceView)findViewById(R.id.show);
        //初始化Holder对象
        holder = surface.getHolder();
        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(3);
        Button sin = (Button)findViewById(R.id.sin);
        Button cos = (Button)findViewById(R.id.cos);
        View.OnClickListener listener = (new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                //绘制坐标轴
                drawBack(holder);
                cx = X_OFFSET;
                if(task!=null){
                    task.cancel();
                }
                task = new TimerTask() {
                    @Override
                    public void run() {
                        int cy = v.getId() == R.id.sin ? centerY -(int)(100*Math.sin((cx-5)*2*Math.PI/150))
                        :centerY-(int)(100*Math.cos((cx-5)*2*Math.PI/150));
                        Canvas canvas = holder.lockCanvas(new Rect(cx,cy-2,cx+2,cy+2));
                        canvas.drawPoint(cx,cy,paint);
                        cx++;
                        if(cx>WIDTH){
                            task.cancel();
                            task =null;
                        }
                        holder.unlockCanvasAndPost(canvas);
                    }
                };
                timer.schedule(task,0,30);
            }
        });
        sin.setOnClickListener(listener);
        cos.setOnClickListener(listener);
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                drawBack(holder);
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                timer.cancel();
            }
        });
    }
    private void drawBack(SurfaceHolder holder){
        Canvas canvas = holder.lockCanvas();
        canvas.drawColor(Color.WHITE);
        Paint p = new Paint();
        p.setColor(Color.BLACK);
        p.setStrokeWidth(2);
        //绘制坐标轴
        canvas.drawLine(X_OFFSET,centerY,WIDTH,centerY,p);
        canvas.drawLine(X_OFFSET,40,X_OFFSET,HEIGHT,p);
        //一解锁就开始绘制
        holder.unlockCanvasAndPost(canvas);
        holder.lockCanvas(new Rect(0,0,0,0));
        holder.unlockCanvasAndPost(canvas);
    }
}
