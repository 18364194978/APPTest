package com.example.administrator.myapplication1;
//(#141)
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by xie on 2017/9/30.
 */

public class AddGesture extends Activity {
    EditText editText;
    GestureOverlayView gestureView;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addgesture);
        //获取去文本编辑框
        editText = (EditText)findViewById(R.id.gesture_name);
        //获取手势编辑图
        gestureView = (GestureOverlayView)findViewById(R.id.gesture);
        //设置手势的绘制颜色宽度
        gestureView.setGestureColor(Color.RED);
        gestureView.setGestureStrokeWidth(4);
        //绑定监听器
        gestureView.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {
            @Override
            public void onGesturePerformed(GestureOverlayView overlay,final Gesture gesture) {
                //加载gesturesave.xml界面布局代表的视图
                View saveDialog = getLayoutInflater().inflate(R.layout.activity_savegesture,null);
                //获取saveDialog里的show组件
                ImageView imageView = (ImageView)saveDialog.findViewById(R.id.gestureshow);
                //获取saveDialog里的gesture_name组件
                final EditText gestureName = (EditText)saveDialog.findViewById(R.id.gesture_name);
                //根据gesture包含的手势创建一个位图
                Bitmap bitmap = gesture.toBitmap(128,128,10,0xffff0000);
                imageView.setImageBitmap(bitmap);
                //使用对话框显示saveDialog组件
                new AlertDialog.Builder(AddGesture.this).setView(saveDialog).setPositiveButton("保存",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog,int which){
                        //获取对应的手势库
                        GestureLibrary gestureLib = GestureLibraries.fromFile("/mnt/sdcard/mygestures");
                        //添加手势
                        gestureLib.addGesture(gestureName.getText().toString(),gesture);
                        //保存手势
                        gestureLib.save();
                    }
                }).setNegativeButton("取消",null).show();
            }
        });
    }
}
