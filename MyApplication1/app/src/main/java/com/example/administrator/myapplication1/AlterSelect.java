package com.example.administrator.myapplication1;
//(#119)
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by Administrator on 2017/9/24 0024.
 */

public class AlterSelect extends Activity {
    Spinner spinner;
    @Override
    public void onCreate(Bundle savesInstanceState){
        super.onCreate(savesInstanceState);
        setContentView(R.layout.activity_alterselect);
        spinner  = (Spinner) findViewById(R.id.spinner);
        String[] arr = {"孙悟空","猪八戒","唐僧"};
        //创建ArrayAdpter对象
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,arr);
        spinner.setAdapter(adapter);
    }
}
