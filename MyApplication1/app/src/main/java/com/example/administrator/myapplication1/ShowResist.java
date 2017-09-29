package com.example.administrator.myapplication1;
//(#129)
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.ui.Person;

/**
 * Created by xie on 2017/9/26.
 */

public class ShowResist extends Activity {
    SharedPreferences sharedPreferences;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asctivity_getresist);
        TextView name = (TextView)findViewById(R.id.getname);
        TextView password = (TextView)findViewById(R.id.getpassword);
        TextView old = (TextView)findViewById(R.id.getold);
        Intent intent = getIntent();
        Person p = (Person)intent.getSerializableExtra("person");
        name.setText("您的activty用户名为："+p.getName());
        password.setText("您的activty密码为："+p.getOld());
        old.setText("您的activty年龄为："+p.getPasswd());
        TextView sharename = (TextView)findViewById(R.id.getnameforshare);
        TextView sharepassword = (TextView)findViewById(R.id.getpasswordforshare);
        TextView shareold = (TextView)findViewById(R.id.getoldforshare);
        name.setText("您的share用户名为："+p.getName());
        password.setText("您的share密码为："+p.getOld());
        old.setText("您的share年龄为："+p.getPasswd());
        name.setText(sharedPreferences.getString("name",null));
        password.setText(sharedPreferences.getString("password",null));
        old.setText(sharedPreferences.getString("old",null));
    }
}
