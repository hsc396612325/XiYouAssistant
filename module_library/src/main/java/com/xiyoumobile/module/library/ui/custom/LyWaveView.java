package com.xiyoumobile.module.library.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

import com.xiyoumobile.module.library.R;

public class LyWaveView extends View {

    private Paint mCirclePaint;
    private Paint mCircleCenterPaint;

    private Paint mFrontWavePaint;
    private Paint mBackWavePaint;

    private Path mWavePath;

    private float mWaveWidth;
    private float mWaveHeight;
    private int mWaveCount;

    private int mDefaultSize;//wrap_content的默认大小
    private int mMeasureSize;//重新测量后View实际的宽高

    private WaveProgressAnim mWaveProgressAnim;
    private OnAnimationListener onAnimationListener;

    private float percent;
    private float progressNum;//最终定位
    private float maxNum;
    private float waveMovingDistance;//波浪平移距离

    private Bitmap bitmap;//缓存bitmap
    private Canvas bitmapCanvas;

    public LyWaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        // 获取xml中的值
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LyWaveView);
        mWaveWidth = array.getDimension(R.styleable.LyWaveView_wave_width, dip2px(context, 180));
        mWaveHeight = array.getDimension(R.styleable.LyWaveView_wave_height, dip2px(context, 15));
        int mFrontWaveColor = array.getColor(R.styleable.LyWaveView_front_wave_color, Color.BLACK);
        int mBackWaveColor = array.getColor(R.styleable.LyWaveView_back_wave_color, Color.GRAY);
        int mCircleColor = array.getColor(R.styleable.LyWaveView_circle_color, Color.GRAY);
        array.recycle();

        // 外部圆
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(mCircleColor);
        // 圆内部填充
        mCircleCenterPaint = new Paint();
        mCircleCenterPaint.setAntiAlias(true);
        mCircleCenterPaint.setColor(getResources().getColor(R.color.white));
        // 前景波浪
        mFrontWavePaint = new Paint();
        mFrontWavePaint.setAntiAlias(true);
        mFrontWavePaint.setColor(mFrontWaveColor);
        mFrontWavePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        // 后景波浪
        mBackWavePaint = new Paint();
        mBackWavePaint.setAntiAlias(true);
        mBackWavePaint.setColor(mBackWaveColor);
        mBackWavePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));

        // 波浪路线
        mWavePath = new Path();

        mDefaultSize = dip2px(context, 50);

        percent = 0;
        progressNum = 0;
        maxNum = 100;
        waveMovingDistance = 0;

        mWaveProgressAnim = new WaveProgressAnim();
        mWaveProgressAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {}
            @Override
            public void onAnimationRepeat(Animation animation) {
                mWaveProgressAnim.setDuration(2500);

            }
        });

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            mMeasureSize = mDefaultSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            mMeasureSize = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            mMeasureSize = widthSize;
        }

        setMeasuredDimension(mMeasureSize, mMeasureSize);
        mWaveCount = (int) Math.ceil(Double.parseDouble(String.valueOf(mMeasureSize / mWaveWidth / 2)));


    }

    private static final String TAG = "LyWaveView";

    private int measureSize(int defaultSize, int measureSpec) {
        int result;
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize = View.MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.AT_MOST) {
            result = defaultSize;
        } else {
            result = specSize;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        bitmap = Bitmap.createBitmap(mMeasureSize, mMeasureSize, Bitmap.Config.ARGB_8888);

        bitmapCanvas = new Canvas(bitmap);
        bitmapCanvas.drawCircle(mMeasureSize / 2, mMeasureSize / 2, mMeasureSize / 2, mCirclePaint);

        bitmapCanvas.drawCircle(mMeasureSize / 2, mMeasureSize / 2, mMeasureSize / 2 - 1, mCircleCenterPaint);

        bitmapCanvas.drawPath(getFrontWavePath(), mFrontWavePaint);
        bitmapCanvas.drawPath(getBackWavePath(), mBackWavePaint);

        canvas.drawBitmap(bitmap, 0, 0, null);

    }

    private Path getFrontWavePath() {
        mWavePath.reset();
        mWavePath.moveTo(mMeasureSize, (1 - percent) * mMeasureSize);
        mWavePath.lineTo(mMeasureSize, mMeasureSize);
        mWavePath.lineTo(0, mMeasureSize);
        mWavePath.lineTo(-waveMovingDistance, (1 - percent) * mMeasureSize);
        for (int i = 0; i < mWaveCount * 2; i++) {
            mWavePath.rQuadTo(mWaveWidth / 2, mWaveHeight, mWaveWidth, 0);
            mWavePath.rQuadTo(mWaveWidth / 2, -mWaveHeight, mWaveWidth, 0);
        }
        mWavePath.close();
        return mWavePath;
    }

    private Path getBackWavePath() {
        mWavePath.reset();
        mWavePath.moveTo(0, (1 - percent) * mMeasureSize);
        mWavePath.lineTo(0, mMeasureSize);
        mWavePath.lineTo(mMeasureSize, mMeasureSize);
        mWavePath.lineTo(mMeasureSize + waveMovingDistance, (1 - percent) * mMeasureSize);
        for (int i = 0; i < mWaveCount * 2; i++) {
            mWavePath.rQuadTo(-mWaveWidth / 2, -mWaveHeight, -mWaveWidth, 0);
            mWavePath.rQuadTo(-mWaveWidth / 2, mWaveHeight, -mWaveWidth, 0);
        }
        mWavePath.close();
        return mWavePath;
    }


    public class WaveProgressAnim extends Animation {
        public WaveProgressAnim() {}
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            if (percent < progressNum / maxNum) {
                if (onAnimationListener != null) {
                    percent = interpolatedTime * progressNum / maxNum;
                }
            }
            waveMovingDistance = interpolatedTime * mWaveCount * mWaveWidth * 2;
            postInvalidate();
        }
    }

    public void setProgressNum(float progressNum, int time) {
        this.progressNum = progressNum * 100;
        percent = progressNum;
        mWaveProgressAnim.setDuration(time);
        this.startAnimation(mWaveProgressAnim);
        mWaveProgressAnim.setRepeatCount(Animation.INFINITE);//让动画无限循环
        mWaveProgressAnim.setInterpolator(new LinearInterpolator());//让动画匀速播放，不然会出现波浪平移停顿的现象
    }

    public interface OnAnimationListener {
        String howToChangeText(float interpolatedTime, float updateNum, float maxNum);
    }

    public void setOnAnimationListener(OnAnimationListener onAnimationListener) {
        this.onAnimationListener = onAnimationListener;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
