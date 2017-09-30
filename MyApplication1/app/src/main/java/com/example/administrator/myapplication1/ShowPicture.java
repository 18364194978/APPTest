package com.example.administrator.myapplication1;
//(#143)
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by xie on 2017/9/30.
 */

public class ShowPicture extends Activity {
    Button add;
    Button view;
    ListView show;
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> descs = new ArrayList<>();
    ArrayList<String> fileNames = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showpicture);
        add = (Button)findViewById(R.id.addpicture);
        view = (Button)findViewById(R.id.showpicture);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 清空names、descs、fileNames集合里原有的数据
                names.clear();
                descs.clear();
                fileNames.clear();
                //通过ContentResolver查询所有图片信息
                Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,null);
                while (cursor.moveToNext()){
                    //获取图片的显示名
                    String name = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
                    // 获取图片的详细描述
                    String desc = cursor.getString(cursor
                            .getColumnIndex(MediaStore.Images.Media.DESCRIPTION));
                    // 获取图片的保存位置的数据
                    byte[] data = cursor.getBlob(cursor
                            .getColumnIndex(MediaStore.Images.Media.DATA));
                    // 将图片名添加到names集合中
                    names.add(name);
                    descs.add(desc);
                    // 将图片保存路径添加到fileNames集合中
                    fileNames.add(new String(data, 0, data.length - 1));
                }
                //创建一个List集合
                List<Map<String,Object>> listItems = new ArrayList<Map<String, Object>>();
                // 将names、descs两个集合对象的数据转换到Map集合中
                for (int i = 0;i<names.size();i++){
                    Map<String,Object>listItem = new HashMap<String, Object>();
                    listItem.put("name",names.get(i));
                    listItem.put("desc",descs.get(i));
                    listItems.add(listItem);
                }
                SimpleAdapter simpleAdapter = new SimpleAdapter(ShowPicture.this,listItems,R.layout.activty_pictureline,
                        new String[]{"name","desc"},new int[]{R.id.picturename,R.id.picturedesc});
                show.setAdapter(simpleAdapter);
            }
        });
        // 为show ListView的列表项单击事件添加监听器
        show.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //加载activity_pictureview视图
                View viewDialog = getLayoutInflater().inflate(R.layout.activity_pictureview,null);
                ImageView image = (ImageView)viewDialog.findViewById(R.id.pictureview);
                image.setImageBitmap(BitmapFactory.decodeFile(fileNames.get(position)));
                new AlertDialog.Builder(ShowPicture.this).setView(viewDialog).setPositiveButton("确定",null);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建ContentValues对象，准备插入数据
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DISPLAY_NAME, "jinta");
                values.put(MediaStore.Images.Media.DESCRIPTION, "金塔");
                values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                // 插入数据，返回所插入数据对应的Uri
                Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                // 加载应用程序下的jinta图片
                Bitmap bitmap = BitmapFactory.decodeResource(ShowPicture.this.getResources(),R.drawable.jinta);
                OutputStream os = null;
                try {
                    // 获取刚插入的数据的Uri对应的输出流
                    os = getContentResolver().openOutputStream(uri);
                    // 将bitmap图片保存到Uri对应的数据节点中
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,os);
                    os.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }

}
