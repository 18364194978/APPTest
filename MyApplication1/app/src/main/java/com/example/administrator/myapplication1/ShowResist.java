package com.example.administrator.myapplication1;
//(#129)
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.ui.Person;

/**
 * Created by xie on 2017/9/26.
 */

public class ShowResist extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asctivity_getresist);
        TextView name = (TextView)findViewById(R.id.getname);
        TextView password = (TextView)findViewById(R.id.getpassword);
        TextView old = (TextView)findViewById(R.id.getold);
        Intent intent = getIntent();
        Person p = (Person)intent.getSerializableExtra("person");
        name.setText("您的用户名为："+p.getName());
        password.setText("您的密码为："+p.getOld());
        old.setText("您的年龄为："+p.getPasswd());
    }
}
