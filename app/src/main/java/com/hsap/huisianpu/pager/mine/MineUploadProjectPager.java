package com.hsap.huisianpu.pager.mine;

import android.view.View;

import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseFragmentPager;

/**
 * 我的上传项目
 */

public class MineUploadProjectPager extends BaseFragmentPager {
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_mine_upload_project, null);
        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
