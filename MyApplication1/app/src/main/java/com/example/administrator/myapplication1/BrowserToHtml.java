package com.example.administrator.myapplication1;
//#150
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by xie on 2017/10/11.
 */

public class BrowserToHtml extends Activity {
    WebView show;
    @Override
    public void onCreate(Bundle savedInstatanceState){
        super.onCreate(savedInstatanceState);
        setContentView(R.layout.activity_browsertohtml);
        show = (WebView)findViewById(R.id.browsertohtml);
        StringBuilder sb = new StringBuilder();
        //拼接一段html代码
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>欢迎您</title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<h2>欢迎访问<a href=\"http://www.crazyit.org\">"+"疯狂的java联盟</a></h2>");
        sb.append("</body>");
        sb.append("</html>");
        //使用简单的loadData方法会导致乱码可能是api的问题
        //show.loadData(sb.toString(),"text/html","utf-8");
        //加载并显示html代码
        show.loadDataWithBaseURL(null,sb.toString(),"text/html","utf-8",null);
    }
}
