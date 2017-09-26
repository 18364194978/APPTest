package com.example.administrator.myapplication1;
//(#112)
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.ui.Person;

/**
 * Created by Administrator on 2017/9/23 0023.
 */

public class ResistModel extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginmodel);
        Button bn = (Button)findViewById(R.id.resist);
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
    }
}
