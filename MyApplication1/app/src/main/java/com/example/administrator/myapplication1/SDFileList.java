package com.example.administrator.myapplication1;
//(#1139若不想使用Environment.getExternalStorageDirectory()获取SD卡路径，则可以使用/mnt/sdcard/代替)
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xie on 2017/9/29.
 */

public class SDFileList extends Activity {
    ListView listView;
    TextView textView;
    //记录当前的父文件夹
    File currentParent;
    //记录当前路径下的所有文件的文件数组
    File[] currentFiles;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdfilelist);
        //列出全部文件的listview
        listView = (ListView)findViewById(R.id.sdlist);
        textView = (TextView)findViewById(R.id.path);
        //获取系统SD卡目录
        File root = new File("/mnt/sdcard");
        //若sd卡存在
        if(root.exists()){
            currentParent = root;
            currentFiles = root.listFiles();
            //使用当前目录下的全部文件文件夹来填充listview
            inflateListView(currentFiles);
        }
        //未listview的列表项单击事件绑定监听器
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //若点击文件则直接返回
                if(currentFiles[position].isFile())return;
                //获取单击文件夹下的所有文件
                File[]tmp = currentFiles[position].listFiles();
                if(tmp == null ||tmp.length == 0){
                    android.widget.Toast.makeText(SDFileList.this,"当亲路径下没有文件", Toast.LENGTH_SHORT).show();
                }else {
                    currentParent = currentFiles[position];
                    currentFiles = tmp;
                    inflateListView(currentFiles);
                }
            }
        });
        //获取上一级目录按钮
        Button parent = (Button)findViewById(R.id.parent);
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(!currentParent.getCanonicalPath().equals("/mnt/sdcard")){
                        //获取上一级目录
                        currentParent = currentParent.getParentFile();
                        currentFiles = currentParent.listFiles();
                        inflateListView(currentFiles);
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

    }
    private void inflateListView(File[] files){
        //创建一个List集合，LIst集合的元素是Map
        List<Map<String,Object>>listItems = new ArrayList<Map<String,Object>>();
        for (int i = 0;i<files.length;i++){
            Map<String,Object>listItem = new HashMap<String, Object>();
            //定义图标
            if(files[i].isDirectory()){
                listItem.put("icon",R.drawable.alarm_1);
            }else {
                listItem.put("icon",R.drawable.email);
            }
            listItem.put("fileName",files[i].getName());
            listItems.add(listItem);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,listItems,R.layout.line,new String[]{"icon","fileName"},new int[]{R.id.icon,R.id.file_name});
        listView.setAdapter(simpleAdapter);
        try {
            textView.setText("当前路径为"+currentParent.getCanonicalPath());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
