package com.xiyoumoblie.module.education;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
@Route(path = "/education/attendance")
public class CheckingInInquireActivity extends AppCompatActivity {

    PieChart mPieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checjing_in_inquire);

        mPieChart = (PieChart) findViewById(R.id.mPieChart);

        // 显示百分比
        mPieChart.setUsePercentValues(true);
        // 设置偏移量
        mPieChart.setExtraOffsets(5, 10, 5, 5);
        // 设置滑动减速摩擦系数
        mPieChart.setDragDecelerationFrictionCoef(0.95f);

        mPieChart.getDescription().setEnabled(false);

        //设置中间文件
        mPieChart.setCenterText(generateCenterSpannableText());

          /*
            设置饼图中心是否是空心的
            true 中间是空心的，环形图
            false 中间是实心的 饼图
         */
        mPieChart.setDrawHoleEnabled(true);
        /*
            设置中间空心圆孔的颜色是否透明
            true 透明的
            false 非透明的
         */
        mPieChart.setHoleColor(Color.WHITE);

        // 设置环形图和中间空心圆之间的圆环的颜色
        mPieChart.setTransparentCircleColor(Color.WHITE);
        // 设置环形图和中间空心圆之间的圆环的透明度
        mPieChart.setTransparentCircleAlpha(110);


        // 设置圆孔半径
        mPieChart.setHoleRadius(50f);
        // 设置空心圆的半径
        mPieChart.setTransparentCircleRadius(61f);
        // 设置是否显示中间的文字
        mPieChart.setDrawCenterText(false);

        // 设置旋转角度
        mPieChart.setRotationAngle(0);
        // 触摸旋转
        mPieChart.setRotationEnabled(true);
        mPieChart.setHighlightPerTapEnabled(true);

        //模拟数据
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry(40, "正常到课"));
        entries.add(new PieEntry(20, "请假8节"));
        entries.add(new PieEntry(39, "迟到4节"));
        entries.add(new PieEntry(1, "旷课1节"));

        //设置数据
        setData(entries);

        mPieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        Legend mLegend =  mPieChart.getLegend(); //设置比例图
        mLegend.setEnabled(false);
    }

    //设置数据
    private void setData(ArrayList<PieEntry> entries) {
        PieDataSet dataSet = new PieDataSet(entries, "考勤情况");
     dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        //数据和颜色
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(0);
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);
        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        mPieChart.setData(data);
        mPieChart.highlightValues(null);


    }

    //设置中间文字
    private SpannableString generateCenterSpannableText() {
        //原文：MPAndroidChart\ndeveloped by Philipp Jahoda
        SpannableString s = new SpannableString("好好学习 天天向上");
        //s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);
        //s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        // s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        //s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        // s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        // s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }
}
