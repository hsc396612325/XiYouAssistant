package com.xiyoumoblie.lib.common.ui;

/**
 * Created by heshu on 2018/7/22.
 */
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.xiyoumoblie.lib.common.base.BaseApplication;

//自定义字体 字体样式：Adobe-Heiti-Std-R
public class MyTextView extends TextView {
    public MyTextView(Context context) {
        super(context);
        //设置字体
        setTypeface(BaseApplication.getInstace().getTypeface());
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //设置字体
        setTypeface(BaseApplication.getInstace().getTypeface());
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //设置字体
        setTypeface(BaseApplication.getInstace().getTypeface());
    }

}
