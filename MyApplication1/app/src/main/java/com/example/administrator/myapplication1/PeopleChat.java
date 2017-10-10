package com.example.administrator.myapplication1;
//#149
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by xie on 2017/10/10.
 */

public class PeopleChat extends Activity {
    EditText input;
    TextView show;
    Button send;
    Handler handler;
    //定义与服务器通信的子线程
    ClientThread clientThread;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peoplechat);
        input = (EditText)findViewById(R.id.chatinput);
        send = (Button)findViewById(R.id.chatsend);
        show = (TextView)findViewById(R.id.chatshow);
        handler = new Handler(){
          @Override
            public void handleMessage(Message msg){
              //若消息来自子线程
              if (msg.what == 0x123){
                  //将读取的内容追加到文本框中
                  show.append("\n"+msg.obj.toString());
              }
          }
        };
        clientThread = new ClientThread(handler);
        //客户端启动ClientThread线程创建网络连接，读取来自服务器的数据
        new Thread(clientThread).start();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //当用户点击发送按钮后将用户输入发热数据封装成Message然后发送给子线程的handler
                    Message msg = new Message();
                    msg.what = 0x345;
                    msg.obj = input.getText().toString();
                    clientThread.revHandler.sendMessage(msg);
                    input.setText("");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
