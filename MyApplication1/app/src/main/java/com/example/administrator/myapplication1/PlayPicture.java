package com.example.administrator.myapplication1;
//(#120)
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/9/24 0024.
 */

public class PlayPicture extends Activity {
    int[] imageIds = new int[]{
            R.drawable.alarm_1,R.drawable.android,R.drawable.barcode_reader_128x128,
            R.drawable.bluetooth_128x128,R.drawable.browser_128x128,R.drawable.bump_128x128,
            R.drawable.calculator_128x128,R.drawable.calendar_128x128,R.drawable.camcorder_128x128,
            R.drawable.compass_128x128
    };
    private AdapterViewFlipper flipper;
    @Override
    public void onCreate(Bundle savedInsatnceState){
        super.onCreate(savedInsatnceState);
        setContentView(R.layout.activity_playpicture);
        flipper = (AdapterViewFlipper)findViewById(R.id.flipper);
        //创建一个BaseAdapter对象，负责提供Gallery所显示的列表项
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return imageIds.length;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView imageView = new ImageView(PlayPicture.this);
                imageView.setImageResource(imageIds[position]);
                //设置图片的缩放类型
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                //未图片设置布局参数
                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                return imageView;
            }
        };
        flipper.setAdapter(adapter);
    }
    public void prev(View source){
        flipper.showPrevious();
        flipper.stopFlipping();
    }
    public void next(View source){
        flipper.showNext();
        flipper.stopFlipping();
    }
    public void auto(View souce){
        flipper.startFlipping();
    }
}
