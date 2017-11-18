package com.hsap.huisianpu.fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.adapter.MineRecycleAdapter;
import com.hsap.huisianpu.base.BaseFragment;
import com.hsap.huisianpu.bean.Bean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zhao on 2017/11/15.
 */

public class MineFragment extends BaseFragment {
    @BindView(R.id.mine_toolbar)
    Toolbar mineToolbar;
    @BindView(R.id.mine_rlv)
    RecyclerView mineRlv;
    Unbinder unbinder;

    @Override
    public View initView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_mine, null);
        return view;
    }

    @Override
    public void initData() {
        setHasOptionsMenu(true);
        mineToolbar.setTitle("我的");
        ((AppCompatActivity) getActivity()).setSupportActionBar(mineToolbar);
        ArrayList<Bean> list = new ArrayList<>();
        list.add(new Bean("我的信息"));
        list.add(new Bean("我的邀请"));
        MineRecycleAdapter adapter = new MineRecycleAdapter(R.layout.item_mine, list);
        mineRlv.setLayoutManager(new LinearLayoutManager(mActivity));
        mineRlv.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    @Override
    public void initListener() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
