package com.xiyoumoblie.module.education;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xiyoumoblie.lib.common.base.BaseFragment;
import com.zhy.autolayout.AutoRelativeLayout;

@Route(path = "/education/main")
public class EducationFragment extends BaseFragment implements View.OnClickListener {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_education, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        ((RelativeLayout) view.findViewById(R.id.rl_checking_in_inquire)).setOnClickListener(this);
        ((RelativeLayout) view.findViewById(R.id.rl_CET_grade_inquire)).setOnClickListener(this);
        ((RelativeLayout) view.findViewById(R.id.rl_final_grade_inquire)).setOnClickListener(this);
        ((RelativeLayout) view.findViewById(R.id.rl_computers_grade_inquire)).setOnClickListener(this);
        ((RelativeLayout) view.findViewById(R.id.rl_timetable_inquire)).setOnClickListener(this);
        ((RelativeLayout) view.findViewById(R.id.rl_classroom_inquire)).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.rl_checking_in_inquire) {
            ARouter.getInstance().build("/education/attendance").navigation();
        } else if (id == R.id.rl_CET_grade_inquire) {
            ARouter.getInstance().build("/education/CETGrade").navigation();
        } else if (id == R.id.rl_final_grade_inquire) {
            ARouter.getInstance().build("/education/EduBind").navigation();
           // ARouter.getInstance().build("/education/FinalGrade").navigation();
        } else if (id == R.id.rl_computers_grade_inquire) {
            ARouter.getInstance().build("/education/ComputersGrade").navigation();
        } else if (id == R.id.rl_timetable_inquire) {
            ARouter.getInstance().build("/education/timetable").navigation();
        } else if (id == R.id.rl_classroom_inquire) {
            ARouter.getInstance().build( "/education/ClassroomInquire").navigation();
        }

    }
}
