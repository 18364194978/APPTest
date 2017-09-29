package com.example.administrator.myapplication1;
//(#112先通过一种方式activity传给另一个activity数据；再通过sharepreferrences记录，再在另一个activity通过读取share获取数据)
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.ui.Person;

/**
 * Created by Administrator on 2017/9/23 0023.
 */

public class ResistModel extends Activity {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginmodel);
        Button bn = (Button)findViewById(R.id.activtyresist);
        Button bn2 = (Button)findViewById(R.id.shareresist);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = (EditText)findViewById(R.id.resname);
                EditText old = (EditText)findViewById(R.id.resname);
                EditText password = (EditText)findViewById(R.id.resname);
                EditText phone = (EditText)findViewById(R.id.resname);
                Person p = new Person(name.getText().toString(),password.getText().toString(),old.getText().toString());
                Bundle data = new Bundle();
                data.putSerializable("person",p);
                Intent intent = new Intent(ResistModel.this,ShowResist.class);
                intent.putExtras(data);
                startActivity(intent);

            }
        });
        bn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = (EditText)findViewById(R.id.resname);
                EditText old = (EditText)findViewById(R.id.resname);
                EditText password = (EditText)findViewById(R.id.resname);
                EditText phone = (EditText)findViewById(R.id.resname);
                editor.putString("name",name.getText().toString());
                editor.putString("old",old.getText().toString());
                editor.putString("password",password.getText().toString());
                editor.putString("phone",phone.getText().toString());
            }
        });
    }
}
