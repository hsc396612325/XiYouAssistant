package com.xiyoumoblie.module.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xiyoumoblie.lib.common.base.BaseFragment;



@Route(path = "/mine/main")
public class MineFragment extends BaseFragment implements View.OnClickListener{

    private PersonalInformation information;    //个人信息对象

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_minee, container, false);
        /*TextView tv = view.findViewById(R.id.tvv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/login/main").navigation();
            }
        });*/
        initView(view);
        return view;
    }

    private void initView(View view) {
        view.findViewById(R.id.pyjh).setOnClickListener(this);
        view.findViewById(R.id.grxx).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.pyjh) {
            Intent intent = new Intent(getContext(), TrainingProgramActivity.class);
            startActivity(intent);
        } else if (id == R.id.grxx) {
            initInformation();
            MyDialog myDialog = new MyDialog(getContext(), information);
            myDialog.show();
        }
    }

    private void initInformation() {
        information = new PersonalInformation();
        information.setName("杨佳豪");
        information.setId("04173174");
        information.setBanji("软件1706");
        information.setCollege("计算机学院");
        information.setProfession("软件工程");
        information.setEducation("本科");
        information.setStartYear("2017");
    }

}
