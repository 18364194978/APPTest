package com.example.administrator.myapplication1;
//#151在html内ed按钮调用webview,分三步：
//1、调用webview关联的websettings的setJavaScriptEnabled（true）启用JavaScript调用功能
//2、调用webview的addJavaScriptInterface(Object object,String name)方法将object对象暴露给js脚本
//3、在JavaScript脚本中通过刚才暴露的nama对象调用android方法
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by xie on 2017/10/11.
 */

public class HtmlToWebView extends Activity {
    WebView myWebView;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_htmltowebview);
        myWebView = (WebView)findViewById(R.id.htmltowebview);
        //此处为了简化编程，使用file协议加载本地assets目录下的html界面，也可以使用http协议加载远程网站的html界面
        myWebView.loadUrl("file://android_asset/test.html");
        //获取webview的设置对象
        WebSettings webSettings = myWebView.getSettings();
        //开启JavaScript调用
        webSettings.setJavaScriptEnabled(true);
        //将MyObject对象暴露给JavaScript脚本
        //这样test.html页面中的JavaScript可以通过myObject来调用myobjet方法
        myWebView.addJavascriptInterface(new MyObject(this),"myObj");

    }
}
