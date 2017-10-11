package com.example.administrator.myapplication1;
//#150
//void goBack()后退
//void goForword()前进
//void loadUrl(String url)加载指定url对应的网页
//boolean zoomIn()放大网页
//boolean zoomOut()缩小网页
//等等
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.EditText;

/**
 * Created by xie on 2017/10/11.
 */

public class SmallBrowser extends Activity {
    EditText url;
    WebView show;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smallbrowser);
        url = (EditText)findViewById(R.id.browserurl);
        show = (WebView)findViewById(R.id.browsershow);
    }
    @Override
    public boolean onKeyDown(int keyCdoe, KeyEvent event){
        if (keyCdoe == KeyEvent.KEYCODE_SEARCH){
            String urlStr = url.getText().toString();
            show.loadUrl(urlStr);
            return true;
        }
        return false;
    }
}
