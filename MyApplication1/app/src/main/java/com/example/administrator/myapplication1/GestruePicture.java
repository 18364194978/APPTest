package com.example.administrator.myapplication1;
//(#140)
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by xie on 2017/9/29.
 */

public class GestruePicture extends Activity implements GestureDetector.OnGestureListener{
    //定义手势检测器实例
    GestureDetector detector;
    ImageView imageView;
    //初始化图片资源
    Bitmap bitmap;
    //定义图片的宽高
    int width,height;
    //记录当前的缩放比
    float currentScale = 1;
    //控制图片缩放的matrix对象
    Matrix matrix;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesturepic);
        //创建手势检测器
        detector = new GestureDetector(this,this);
        imageView = (ImageView)findViewById(R.id.gestruepicture);
        matrix = new Matrix();
        //获取被缩放的图片
        bitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.shuangta);
        //获取位图的宽高
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        //初始化时image西汉时的图片
        imageView.setImageBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.shuangta));
    }
    @Override
    public boolean onTouchEvent(MotionEvent me){
        //将给activity上的触发事件交给GestureDetector处理
        return detector.onTouchEvent(me);
    }
    @Override
    public boolean onFling(MotionEvent event1,MotionEvent event2,float velocityX,float velocityY){
        velocityX = velocityX >4000?4000:velocityX;
        velocityX = velocityX<-4000?-4000:velocityX;
        //根据手势就算缩放比
        currentScale +=currentScale*velocityX/4000.0f;
        //保证currentScle不会=0
        currentScale = currentScale>0.01?currentScale:0.01f;
        //重置matrix
        matrix.reset();
        //缩放matrix
        matrix.setScale(currentScale,currentScale,160,200);
        BitmapDrawable tmp = (BitmapDrawable)imageView.getDrawable();
        //若图片还未回收先强制回收图片
        if(!tmp.getBitmap().isRecycled()){
            tmp.getBitmap().recycle();
        }
        //创建新的图片
        Bitmap bitmap2 = Bitmap.createBitmap(bitmap,0,0,width,height,matrix,true);
        imageView.setImageBitmap(bitmap2);
        return true;
    }
    @Override
    public boolean onDown(MotionEvent arg0){
        return false;
    }
    @Override
    public void onLongPress(MotionEvent event){

    }
    @Override
    public boolean onScroll(MotionEvent event1,MotionEvent event2,float distanceX,float distanceY){
        return false;
    }
    @Override
    public void onShowPress(MotionEvent event){

    }
    @Override
    public boolean onSingleTapUp(MotionEvent event){
        return false;
    }
}
