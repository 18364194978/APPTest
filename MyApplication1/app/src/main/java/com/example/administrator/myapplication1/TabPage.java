package com.example.administrator.myapplication1;
//(#125)
import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * Created by xie on 2017/9/25.
 */

public class TabPage extends TabActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabpage);
        TabHost tabHost = getTabHost();
        //创建第一个Tab界面
        TabHost.TabSpec tab1 = tabHost.newTabSpec("tab1")
                .setIndicator("已接电话") //设置标题
                .setContent(R.id.tab01);//设置内容
        tabHost.addTab(tab1);
        TabHost.TabSpec tab2 = tabHost.newTabSpec("tab2")
                //在标题上放置图标
                .setIndicator("呼出电话",getResources()
                .getDrawable(R.drawable.bluetooth_128x128))
                .setContent(R.id.tab02);
        tabHost.addTab(tab2);
        TabHost.TabSpec tab3 = tabHost.newTabSpec("tab3")
                .setIndicator("未接电话")
                .setContent(R.id.tab03);
        tabHost.addTab(tab3);
    }
}
