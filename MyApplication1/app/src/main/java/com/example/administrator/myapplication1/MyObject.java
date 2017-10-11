package com.example.administrator.myapplication1;
//#151

import android.app.AlertDialog;
import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.*;
import android.widget.Toast;

/**
 * Created by xie on 2017/10/11.
 */

public class MyObject {
    Context mContext;
    MyObject(Context c){
        mContext = c;
    }
    //该方法将会暴露给JavaScript脚本调用
    @JavascriptInterface
    public void showToast(String name){
        android.widget.Toast.makeText(mContext,name+",您好!", Toast.LENGTH_SHORT).show();
    }
    @JavascriptInterface
    public void showList(){
        //显示一个普通的列表对话框
        new AlertDialog.Builder(mContext).setTitle("图书列表")
                .setIcon(R.drawable.alarm_1).setItems(new String[]{"疯狂java讲义","疯狂android讲义"},null).create().show();
    }
}
