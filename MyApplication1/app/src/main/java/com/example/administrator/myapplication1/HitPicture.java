package com.example.administrator.myapplication1;
//(#136drawBitmapMesh)
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2017/9/27 0027.
 */

public class HitPicture extends Activity {
    private Bitmap bitmap;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    private class MyView extends View{
        //定义两个常亮，这两个常亮指定该图片横向纵向上划分为20格
        private final int WIDTH = 20;
        private final int HEIGHT = 20;
        //记录该图片上包含441格顶点
        private final int COUNT = (WIDTH+1)*(HEIGHT+1);
        //定义一个数组保存bitmap上的21*21个点的坐标
        private final float[] verts = new float[COUNT*2];
        //定义一个数组，记录Bitmap上的21*21个点经过扭曲后的坐标
        //对图片扭曲的关键是修改该数组里元素的值
        private final float[] orig = new float[COUNT*2];
        public MyView(Context context,int drawableId){
            super(context);
            setFocusable(true);
            bitmap = BitmapFactory.decodeResource(getResources(),drawableId);
            float bitmapWidth = bitmap.getWidth();
            float bitmapHeight = bitmap.getHeight();
            int index = 0;
            for (int y = 0;y<HEIGHT;y++){
                float fy = bitmapHeight*y/HEIGHT;
                for (int x = 0;x<=WIDTH;x++){
                    float fx = bitmapWidth*x/WIDTH;
                    //初始化orig/vert数组初始化后orig、verts两个数组均匀的保存了21*21个点的x、y坐标
                    orig[index*2+0] = verts[index*2+0] = fx;
                    orig[index*2+1] = verts[index*2+1] = fy;
                    index+=1;
                }
            }
            //设置背景色
            setBackgroundColor(Color.WHITE);
        }
        @Override
        protected void onDraw(Canvas canvas){
            //对bitmapanverts数组进行扭曲
            //从第一个点（由第五个参数0控制）开始扭曲
            canvas.drawBitmapMesh(bitmap,WIDTH,HEIGHT,verts,0,null,0,null);
        }
        //工具方法，用于根据触摸事件位置计算verts数组里个元素的值
        private void warp(float cx,float cy){
            for (int i= 0;i<COUNT*2;i+=2){
                float dx = cx - orig[i+0];
                float dy = cy - orig[i+1];
                float dd = dx * dx + dy * dy;
                //计算每个坐标点当前点（cx,cy）之间的距离
                float d = (float)Math.sqrt(dd);
                //计算扭曲度，距离当前点（cx,cy）越远，扭曲度越小
                float pull = 80000 / ((float)(dd * d));
                //对verts数组（保存bitmap上21*21个点经过扭曲后的坐标重亲赋值）
                if(pull>=1){
                    verts[i+0] = cx;
                    verts[i+1] = cy;
                }else {
                    //控制各顶点向触摸点偏移
                    verts[i+0] = orig[i+0]+dx*pull;
                    verts[i+1] = orig[i+1]+dy*pull;
                }
            }
            invalidate();
        }
        @Override
        public boolean onTouchEvent(MotionEvent event){
            warp(event.getX(),event.getY());
            return true;
        }

    }
}
