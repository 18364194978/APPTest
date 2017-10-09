package com.example.administrator.myapplication1;
//#146
//更换壁纸的apiwei WallpaperManager,提供clear（）方法消除壁纸
import android.app.Service;
import android.app.WallpaperManager;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by xie on 2017/10/9.
 */

public class ChangeBackPicService extends Service {
    //定义定时更换壁纸的资源
    int[] wallpapers = new int[]{
            R.drawable.shuangta,
            R.drawable.lijiang,
            R.drawable.qiao,
            R.drawable.shui
    };
    //定义系统的壁纸管理服务
    WallpaperManager wManager;
    //定义当前所显示的壁纸
    int current = 0;
    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        //到了最后一张重新开始
        if (current>=4){
            current = 0;
        }
        try {
            //改变壁纸
            wManager.setResource(wallpapers[current++]);
        }catch (Exception e){
            e.printStackTrace();
        }
        return START_STICKY;
    }
    @Override
    public void onCreate(){
        super.onCreate();
        //初始化WallpaperManager
        wManager = WallpaperManager.getInstance(this);
    }
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }
}