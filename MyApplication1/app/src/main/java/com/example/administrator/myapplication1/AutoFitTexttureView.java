package com.example.administrator.myapplication1;
//#148
import android.content.Context;
import android.util.AttributeSet;
import android.view.TextureView;

/**
 * Created by xie on 2017/10/10.
 */

public class AutoFitTexttureView extends TextureView {
    private int mRatioWidth = 0;
    private int mRatioHeight = 0;
    public AutoFitTexttureView(Context context, AttributeSet atts){
        super(context,atts);
    }
    public void setAspectRatio(int width,int height){
        mRatioHeight = height;
        mRatioWidth = width;
        requestLayout();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (0 == mRatioWidth || 0 == mRatioHeight){
            setMeasuredDimension(width,height);
        }else {
            if (width<height*mRatioWidth/mRatioHeight){
                setMeasuredDimension(width,width * mRatioHeight / mRatioWidth);
            }else {
                setMeasuredDimension(height * mRatioWidth / mRatioHeight,height);
            }
        }
    }
}
