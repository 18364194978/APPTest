package com.example.administrator.myapplication1;
//(#142ExpandableListView暂时无法用，用list替代一下)
import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.gesture.GestureLibrary;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

/**
 * Created by xie on 2017/9/30.
 */

public class GetPhoneByProvider extends Activity {
    Button search;
    Button add;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getphonebyprovider);
        search = (Button)findViewById(R.id.phoneseach);
        add = (Button)findViewById(R.id.phoneadd);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View souce) {
                //定义两个list来封装系统联系人信息，指定联系人电话号码等信息
                final ArrayList<String> namas = new ArrayList<String>();
                final ArrayList<ArrayList<String>> details = new ArrayList<>();
                //使用contentresolver查找联系人数据
                Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
                //遍历查询结果，获取系统中所有联系人
                while (cursor.moveToNext()){
                    //获取联系人id
                    String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    //获取联系人名字
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    namas.add(name);
                    //查找联系人号码
                    Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID+ " = " + contactId, null, null);
                    ArrayList<String>detail = new ArrayList<String>();
                    //边路结果，获取该联系人的多个电话号码
                    while (phones.moveToNext()){
                        String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        detail.add("电话号码："+phoneNumber);
                    }
                    phones.close();
                    //查找联系人email
                    Cursor emails = getContentResolver().query(
                            ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                            null, ContactsContract.CommonDataKinds.Email
                                    .CONTACT_ID + " = " + contactId, null, null);
                    // 遍历查询结果，获取该联系人的多个E-mail地址
                    while (emails.moveToNext())
                    {
                        // 获取查询结果中E-mail地址列中数据
                        String emailAddress = emails.getString(emails
                                .getColumnIndex(ContactsContract
                                        .CommonDataKinds.Email.DATA));
                        detail.add("邮件地址：" + emailAddress);
                    }
                    emails.close();
                    details.add(detail);
                }
                cursor.close();
                //加载result.xml界面代表视图
                View resultDialog = getLayoutInflater().inflate(R.layout.activity_phoneresult,null);
                //获取resuleDialog中的折叠界面
//                ExpandableListView list = (ExpandableListView) resultDialog.findViewById(R.id.phonelist);
                new AlertDialog.Builder(GetPhoneByProvider.this)
                        .setView(resultDialog).setPositiveButton("确定", null)
                        .show();
            }
        });
        // 为add按钮的单击事件绑定监听器
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取程序界面中的三个文本框的内容
                String name = ((EditText) findViewById(R.id.phonename))
                        .getText().toString();
                String phone = ((EditText) findViewById(R.id.phonenum))
                        .getText().toString();
                String email = ((EditText) findViewById(R.id.phoneemail))
                        .getText().toString();
                // 创建一个空的ContentValues
                ContentValues values = new ContentValues();
                // 向RawContacts.CONTENT_URI执行一个空值插入
                // 目的是获取系统返回的rawContactId
                Uri rawContactUri = getContentResolver().insert(
                        ContactsContract.RawContacts.CONTENT_URI, values);
                long rawContactId = ContentUris.parseId(rawContactUri);
                values.clear();
                values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
                // 设置内容类型
                values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
                // 设置联系人名字
                values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, name);
                // 向联系人URI添加联系人名字
                getContentResolver().insert(android.provider.ContactsContract
                        .Data.CONTENT_URI, values);
                values.clear();
                values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
                values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                // 设置联系人的电话号码
                values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, phone);
                // 设置电话类型
                values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
                // 向联系人电话号码URI添加电话号码
                getContentResolver().insert(android.provider.ContactsContract
                        .Data.CONTENT_URI, values);
                values.clear();
                values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
                values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE);
                // 设置联系人的E-mail地址
                values.put(ContactsContract.CommonDataKinds.Email.DATA, email);
                // 设置该电子邮件的类型
                values.put(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK);
                // 向联系人E-mail URI添加E-mail数据
                getContentResolver().insert(android.provider.ContactsContract
                        .Data.CONTENT_URI, values);
                android.widget.Toast.makeText(GetPhoneByProvider.this, "联系人数据添加成功",
                        android.widget.Toast.LENGTH_SHORT).show();
            }
        });
    }
}
