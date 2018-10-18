package com.xiyoumobile.module.library.l_common.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.xiyoumobile.module.library.R;

public class LyProcessView extends View {

    private Paint progressPaint;//绘制圆弧的画笔

    private Paint bgPaint;//绘制背景圆弧的画笔

    private int progressNum;//可以更新的进度条数值
    private float maxNum;//进度条最大值

    private int defaultSize;//自定义View默认的宽高

    private int frameColor;
    private int contentColor;

    Path mPath;
    Context mContext;

    public LyProcessView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs){

        mContext = context;
        defaultSize = dip2px(context,100);
        mPath = new Path();

        progressNum = 0;
        maxNum = 100;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LyProcessView);
        frameColor = typedArray.getColor(R.styleable.LyProcessView_frame_color, Color.GRAY);
        contentColor = typedArray.getColor(R.styleable.LyProcessView_content_color, Color.BLUE);
        progressNum = typedArray.getInteger(R.styleable.LyProcessView_process, 0);
        typedArray.recycle();//typedArray用完之后需要回收，防止内存泄漏

        progressPaint = new Paint();
        progressPaint.setAntiAlias(true);//设置抗锯齿
        progressPaint.setColor(contentColor);

        bgPaint = new Paint();
        bgPaint.setStyle(Paint.Style.STROKE);//只描边，不填充
        bgPaint.setAntiAlias(true);//设置抗锯齿
        bgPaint.setStrokeWidth(2);
        bgPaint.setColor(frameColor);

    }

    public void setProgressNum(int progressNum, int time) {
        this.progressNum = progressNum;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int d = 20;
        int length = progressNum / 2 * 10;
        int max = 500;
        int height = 20;

        mPath.reset();
        RectF roundRect = new RectF(d, d, d + max, d + height);
        mPath.addRoundRect(roundRect, 20, 20, Path.Direction.CW);
        canvas.drawPath(mPath, bgPaint);

        mPath.reset();
        roundRect = new RectF(d, d, d + length, d + height);
        mPath.addRoundRect(roundRect, 20, 20, Path.Direction.CW);
        canvas.drawPath(mPath, progressPaint);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int height = measureSize(defaultSize, heightMeasureSpec);
        int width = measureSize(dip2px(mContext,200), widthMeasureSpec);

        setMeasuredDimension(width, height);

    }

    private int measureSize(int defaultSize, int measureSpec) {
        int result = defaultSize;
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize = View.MeasureSpec.getSize(measureSpec);

        if (specMode == View.MeasureSpec.EXACTLY) {
            result = specSize;
        } else if (specMode == View.MeasureSpec.AT_MOST) {
            result = Math.min(result, specSize);
        }
        return result;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}