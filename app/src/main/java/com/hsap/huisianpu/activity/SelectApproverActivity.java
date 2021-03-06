package com.hsap.huisianpu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.adapter.SelectApproverRecycleAdapter;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.bean.SelectApproverBean;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 选择审批人页面
 */

public class SelectApproverActivity extends BaseBackActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.rlv_select_approver)
    RecyclerView rlvSelectApprover;

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_approver;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        rlvSelectApprover.setLayoutManager(new LinearLayoutManager(SelectApproverActivity.this));
        OkGo.<String>post(NetAddressUtils.getWorkerAdmin).params("id", SpUtils.getInt(ConstantUtils.UserId,SelectApproverActivity.this))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        final SelectApproverBean bean = new Gson().fromJson(response.body().toString(), SelectApproverBean.class);

                            SelectApproverRecycleAdapter adapter = new SelectApproverRecycleAdapter(R.layout.item_select, bean.getUsers());
                            rlvSelectApprover.setAdapter(adapter);
                            Log.e("getWorkerAdmin","aaa");
                            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent();
                                    intent.putExtra("name",bean.getUsers().get(position).getUser().getName());
                                    intent.putExtra("id",bean.getUsers().get(position).getUser().getId());

                                    setResult(101,intent);

                                    finish();
                                }
                            });
                        }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        ToastUtils.showToast(SelectApproverActivity.this,"当前网络不好");
                    }
                });
    }

    @Override
    public void initListener() {
            back.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
