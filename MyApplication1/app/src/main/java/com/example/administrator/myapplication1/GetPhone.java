package com.example.administrator.myapplication1;
//(#130)
import android.app.Activity;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Administrator on 2017/9/26 0026.
 */

public class GetPhone extends Activity {
    final int PICK_CONTACT = 0;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getphone);
        Button bn = (Button)findViewById(R.id.bn);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //设置intant的action属性
                intent.setAction(Intent.ACTION_GET_CONTENT);
                //设置intent的type属性
                intent.setType("vnd.android.cursor.item/phone");
                //启动activity，并希望获取该activity的结果
                startActivityForResult(intent,PICK_CONTACT);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        switch (requestCode){
            case (PICK_CONTACT):
                if(resultCode == Activity.RESULT_OK){
                    //获取返回的数据
                    Uri contactData = data.getData();
                    CursorLoader cursorLoader = new CursorLoader(this,contactData,null,null,null,null);
                    //查询联系人信息
                    Cursor cursor = cursorLoader.loadInBackground();
                    //如果查询到指定的联系人
                    if(cursor.moveToFirst()){
                        String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                        //获取联系人名字
                        String name = cursor .getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                        String phoneNumber = "此联系人暂未输入电话号码";
                        //根据联系人查询到该联系人的详细信息
                        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"="+contactId,null,null);
                        if (phones.moveToFirst()){
                            //去除电话号码
                            phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                        }
                        //关闭游标
                        phones.close();
                        EditText show = (EditText)findViewById(R.id.show);
                        show.setText(name);
                        EditText phone = (EditText)findViewById(R.id.phone);
                        phone.setText(phoneNumber);

                    }
                    //关闭游标
                    cursor.close();
                }
        }
    }
}
