package com.xiyoumoblie.module.main;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xiyoumoblie.lib.common.base.BaseActivity;

@Route(path = "/main/main")
public class HomeActivity extends BaseActivity implements View.OnClickListener{

    private TextView mTvEducation;
    private TextView mTvLibrary;
    private TextView mTvEvent;
    private TextView mTvMine;

    private Fragment mFgEducation;
    private Fragment mFgLibrary;
    private Fragment mFgEvent;
    private Fragment mFgMine;

    private FragmentManager mManager;

    int mColorClicked = 0;
    int mColorUnClicked = 0;
    private Drawable mIconEducation;
    private Drawable mIconLibrary;
    private Drawable mIconEvent;
    private Drawable mIconMine;
    private Drawable mIconEducationS;
    private Drawable mIconLibraryS;
    private Drawable mIconEventS;
    private Drawable mIconMineS;

    private Toolbar mToolbar;
    private TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);
        mColorClicked = getResources().getColor(R.color.colorPrimary);
        mColorUnClicked = getResources().getColor(R.color.colorGrey);
        mManager = getSupportFragmentManager();
        initViews();
   }

    private void initViews() {
        mToolbar = findViewById(R.id.home_tool_bar);
        mTvTitle = findViewById(R.id.tool_bar_title);
        mTvTitle.setText("教务");
        setupToolBar(mToolbar, false);

        mTvEducation = findViewById(R.id.tv_jiaowu);
        mTvLibrary = findViewById(R.id.tv_tushu);
        mTvEvent = findViewById(R.id.tv_huodong);
        mTvMine = findViewById(R.id.tv_wode);

        mTvEducation.setOnClickListener(this);
        mTvLibrary.setOnClickListener(this);
        mTvEvent.setOnClickListener(this);
        mTvMine.setOnClickListener(this);

        int size = 70;
        mIconEducation = getResources().getDrawable(R.drawable.education_normal);
        mIconEducation.setBounds(0, 0, size, size);
        mIconLibrary = getResources().getDrawable(R.drawable.library_normal);
        mIconLibrary.setBounds(0, 0, size, size);
        mIconEvent = getResources().getDrawable(R.drawable.event_normal);
        mIconEvent.setBounds(0, 0, size, size);
        mIconMine = getResources().getDrawable(R.drawable.mine_normal);
        mIconMine.setBounds(0, 0, size, size);

        mIconEducationS = getResources().getDrawable(R.drawable.education_select);
        mIconEducationS.setBounds(0, 0, size, size);
        mIconLibraryS = getResources().getDrawable(R.drawable.library_select);
        mIconLibraryS.setBounds(0, 0, size, size);
        mIconEventS = getResources().getDrawable(R.drawable.event_select);
        mIconEventS.setBounds(0, 0, size, size);
        mIconMineS = getResources().getDrawable(R.drawable.mine_select);
        mIconMineS.setBounds(0, 0, size, size);

        mTvEducation.setCompoundDrawables(null, mIconEducationS, null, null);
        mTvLibrary.setCompoundDrawables(null, mIconLibrary, null, null);
        mTvEvent.setCompoundDrawables(null, mIconEvent, null, null);
        mTvMine.setCompoundDrawables(null, mIconMine, null, null);

        FragmentTransaction mTransaction = mManager.beginTransaction();
        mFgEducation = (Fragment) ARouter.getInstance().build("/education/main").navigation();
        mTransaction.add(R.id.home_container, mFgEducation);
        mTransaction.commit();
        changeColor(0);

    }


    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = mManager.beginTransaction();
        int id = v.getId();
        if (id == R.id.tv_jiaowu) {
            hideFragment(transaction, mFgLibrary);
            hideFragment(transaction, mFgEvent);
            hideFragment(transaction, mFgMine);
            mTvTitle.setText("教务");
            setupToolBar(mToolbar, false);
            mTvEducation.setCompoundDrawables(null, mIconEducationS, null, null);
            mTvLibrary.setCompoundDrawables(null, mIconLibrary, null, null);
            mTvEvent.setCompoundDrawables(null, mIconEvent, null, null);
            mTvMine.setCompoundDrawables(null, mIconMine, null, null);
            if (mFgEducation == null) {
                mFgEducation = (Fragment) ARouter.getInstance().build("/education/main").navigation();
                transaction.add(R.id.home_container, mFgEducation);
            } else {
                transaction.show(mFgEducation);
            }
            changeColor(0);
        } else if (id == R.id.tv_tushu) {
            hideFragment(transaction, mFgEducation);
            hideFragment(transaction, mFgEvent);
            hideFragment(transaction, mFgMine);
            mTvTitle.setText("图书");
            setupToolBar(mToolbar, false);
            mTvEducation.setCompoundDrawables(null, mIconEducation, null, null);
            mTvLibrary.setCompoundDrawables(null, mIconLibraryS, null, null);
            mTvEvent.setCompoundDrawables(null, mIconEvent, null, null);
            mTvMine.setCompoundDrawables(null, mIconMine, null, null);
            if (mFgLibrary == null) {
                 mFgLibrary = (Fragment) ARouter.getInstance().build("/event/main").navigation();
                transaction.add(R.id.home_container, mFgLibrary);
            } else {
                transaction.show(mFgLibrary);
            }
            changeColor(1);

        } else if (id == R.id.tv_huodong) {
            hideFragment(transaction, mFgEducation);
            hideFragment(transaction, mFgLibrary);
            hideFragment(transaction, mFgMine);
            mTvTitle.setText("活动");
            setupToolBar(mToolbar, false);
            mTvEducation.setCompoundDrawables(null, mIconEducation, null, null);
            mTvLibrary.setCompoundDrawables(null, mIconLibrary, null, null);
            mTvEvent.setCompoundDrawables(null, mIconEventS, null, null);
            mTvMine.setCompoundDrawables(null, mIconMine, null, null);
            if (mFgEvent == null) {
                mFgEvent = (Fragment) ARouter.getInstance().build("/library/main").navigation();
                transaction.add(R.id.home_container, mFgEvent);
            } else {
                transaction.show(mFgEvent);
            }
            changeColor(2);
        } else if (id == R.id.tv_wode) {
            hideFragment(transaction, mFgEducation);
            hideFragment(transaction, mFgLibrary);
            hideFragment(transaction, mFgEvent);
            mTvTitle.setText("我的");
            setupToolBar(mToolbar, false);
            mTvEducation.setCompoundDrawables(null, mIconEducation, null, null);
            mTvLibrary.setCompoundDrawables(null, mIconLibrary, null, null);
            mTvEvent.setCompoundDrawables(null, mIconEvent, null, null);
            mTvMine.setCompoundDrawables(null, mIconMineS, null, null);
            if (mFgMine == null) {
                mFgMine =  (Fragment) ARouter.getInstance().build("/mine/main").navigation();
                transaction.add(R.id.home_container, mFgMine);
            } else {
                transaction.show(mFgMine);
            }
            changeColor(3);
        }

        transaction.commit();
    }


    private void hideFragment(FragmentTransaction ft, Fragment fg) {
        if (fg != null) {
            ft.hide(fg);
        }
    }

    private void changeColor(int index) {
        mTvEducation.setTextColor(mColorUnClicked);
        mTvLibrary.setTextColor(mColorUnClicked);
        mTvEvent.setTextColor(mColorUnClicked);
        mTvMine.setTextColor(mColorUnClicked);
        switch (index) {
            case 0:
                mTvEducation.setTextColor(mColorClicked);
                break;
            case 1:
                mTvLibrary.setTextColor(mColorClicked);
                break;
            case 2:
                mTvEvent.setTextColor(mColorClicked);
                break;
            case 3:
                mTvMine.setTextColor(mColorClicked);
                break;
        }
    }

}
