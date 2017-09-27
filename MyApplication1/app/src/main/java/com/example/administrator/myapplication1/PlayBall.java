package com.example.administrator.myapplication1;
//(#135gameView.setOnKeyListener看一下这个键盘事件是怎么回事)
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by xie on 2017/9/27.
 */

public class PlayBall extends Activity {
    //桌面的宽度
    private int tableWidth;
    //桌面的高度
    private int tablrHeight;
    //球拍的垂直位置
    private int racketY;
    //下面定义球拍的高度和宽度
    private final int RACKET_HEIGHT = 30;
    private final int RACKET_WIDTH = 90;
    //晓求得大小
    private final int BALL_SIZE = 16;
    //小球纵向的运行速度
    private int ySpeed = 15;
    Random rand = new Random();
    //返回一个-0.5~0.5的比率，用于控制小球的运行方向
    private double xyRate = rand.nextDouble() - 0.5;
    //小球横向速度
    private int xSpeed = (int)(ySpeed*xyRate*2);
    //ballX/ballY代表小球的坐标
    private int ballX = rand.nextInt(200)+20;
    private int ballY = rand.nextInt(10)+20;
    //racketX代表球拍的水平位置
    private int racketX = rand.nextInt(200);
    //游戏是否结束的旗标
    private boolean isLose = false;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //去掉窗口标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏展示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //创建GAME组件
        final GameView gameView = new GameView(this);
        setContentView(gameView);
        //获取窗口管路器
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        //获取屏幕的宽高
        tableWidth = metrics.widthPixels;
        tablrHeight = metrics.heightPixels;
        racketY = tablrHeight -80;
        final Handler handler = new Handler(){
            public void handleMessage(Message msg){
                if(msg.what == 0x123){
                    gameView.invalidate();
                }
            }
        };
        gameView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //获取由那个键出发的事件
                switch (event.getKeyCode()){
                    //左移
                    case KeyEvent.KEYCODE_A:
                        if(racketX>0)racketX-=10;
                        break;
                    case KeyEvent.KEYCODE_D:
                        if(racketX<tableWidth-RACKET_WIDTH)racketX+=10;
                        break;
                }
                gameView.invalidate();
                return true;
            }
        });
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //若小球碰到边框
                if(ballX<=0||ballX>=tableWidth-BALL_SIZE){
                    xSpeed = -xSpeed;
                }
                //若小球高度超出了球拍额位置且横向不在球拍范围之内，游戏结束
                if(ballY>=racketY-BALL_SIZE&&(ballX<racketX||ballX>racketX+RACKET_WIDTH)){
                    timer.cancel();
                    isLose = true;
                }
                //若小球位于球拍之内则反弹
                else if(ballY<=0||(ballY>=racketY-BALL_SIZE&&ballX>racketX&&ballX<=racketX+RACKET_WIDTH)){
                    ySpeed = -ySpeed;
                }
                ballY+=ySpeed;
                ballX+=xSpeed;
                handler.sendEmptyMessage(0x123);
            }
        },0,100);
    }
    class GameView extends View{
        Paint paint = new Paint();
        public GameView(Context context){
            super(context);
            setFocusable(true);
        }
        //重写view的ondiaw，实现绘画
        public void onDraw(Canvas canvas){
            paint.setStyle(Paint.Style.FILL);
            //去锯齿
            paint.setAntiAlias(true);
            //若游戏一结束
            if(isLose){
                paint.setColor(Color.RED);
                paint.setTextSize(40);
                canvas.drawText("游戏结束",tableWidth/2-100,200,paint);
            }else {
                paint.setColor(Color.rgb(255,0,0));
                canvas.drawCircle(ballX,ballY,BALL_SIZE,paint);
                //绘制球拍
                paint.setColor(Color.rgb(80,80,200));
                canvas.drawRect(racketX,racketY,racketX+RACKET_WIDTH,racketY+RACKET_HEIGHT,paint);
            }
        }
    }
}
