package com.hsap.huisianpu.pager.work;

import android.view.View;

import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseFragmentPager;

/**
 * Created by zhao on 2017/11/23.
 */

public class WorkWeekPager extends BaseFragmentPager {
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_work_week, null);
        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}