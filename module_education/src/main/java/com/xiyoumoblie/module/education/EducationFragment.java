package com.xiyoumoblie.module.education;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xiyoumoblie.lib.common.base.BaseFragment;
import com.zhy.autolayout.AutoRelativeLayout;

@Route(path = "/education/main")
public class EducationFragment extends BaseFragment implements View.OnClickListener {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_educationn, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        ((AutoRelativeLayout) view.findViewById(R.id.rl_checking_in_inquire)).setOnClickListener(this);
        ((AutoRelativeLayout) view.findViewById(R.id.rl_CET_grade_inquire)).setOnClickListener(this);
        ((AutoRelativeLayout) view.findViewById(R.id.rl_final_grade_inquire)).setOnClickListener(this);
        ((AutoRelativeLayout) view.findViewById(R.id.rl_computers_grade_inquire)).setOnClickListener(this);
        ((AutoRelativeLayout) view.findViewById(R.id.rl_timetable_inquire)).setOnClickListener(this);
        ((AutoRelativeLayout) view.findViewById(R.id.rl_class_inquire)).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.rl_checking_in_inquire) {
            Intent intent = new Intent(getActivity(), CheckingInInquireActivity.class);

            ARouter.getInstance().build("/education/attendance").navigation();
        } else if (id == R.id.rl_CET_grade_inquire) {
            Toast.makeText(getActivity(), "查询四六级成绩", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.rl_final_grade_inquire) {
            Toast.makeText(getActivity(), "查询期末成绩", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.rl_computers_grade_inquire) {
            Toast.makeText(getActivity(), "查询计算机等级考试成绩", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.rl_timetable_inquire) {
            Toast.makeText(getActivity(), "查询课表", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.rl_timetable_inquire) {
            Toast.makeText(getActivity(), "查询无课教室", Toast.LENGTH_SHORT).show();
        }
        
    }
}
