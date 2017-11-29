package com.hsap.huisianpu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.base.BaseActivity;
import com.hsap.huisianpu.bean.LoginBean;
import com.hsap.huisianpu.bean.RegistrationBean;
import com.hsap.huisianpu.utils.ActivityManagerUtils;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhao on 2017/11/16.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bt_zhuce)
    Button btZhuce;
    @BindView(R.id.bt_denglu)
    Button btdenglu;
    private LoginBean bean;

    @Override
    public int getLayoutId() {

        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        ActivityManagerUtils.getInstance().addActivity(this);
        btdenglu.setOnClickListener(this);
        btZhuce.setOnClickListener(this);


    }

    @Override
    public void initData() {
    }

    @Override
    public void initListener() {

    }

    @Override
    public void processClick(View v) {
           switch (v.getId()){
               case R.id.bt_zhuce:
                   startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
                   break;
               case R.id.bt_denglu:
                   String  username = etUsername.getText().toString().trim();
                   XGPushManager.registerPush(getApplicationContext(),username, new XGIOperateCallback() {
                       @Override
                       public void onSuccess(Object data, int i) {
                           Log.e("TPush", "注册成功，设备token为：" + data);
                       }

                       @Override
                       public void onFail(Object data, int errCode, String msg) {
                           Log.d("TPush", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
                       }
                   });
                  String  password = etPassword.getText().toString().trim();
                  denglu(username,password);
                   break;
           }
    }

    private void denglu(String username, String password) {
        if (TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
            ToastUtils.showToast(LoginActivity.this,"账号或密码不能为空");
            return;
        }
        OkGo.<String>post(NetAddressUtils.login).params("username", username).
                params("password", password).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                bean = new Gson().fromJson(response.body().toString(), LoginBean.class);
                if (bean.isSuccess()){
                    SpUtils.putBoolean(ConstantUtils.Login,true,LoginActivity.this);
                    SpUtils.putInt(ConstantUtils.UserId, bean.getData(),LoginActivity.this);
                    setToken(bean.getData());
                }else {
                    ToastUtils.showToast(LoginActivity.this, bean.getMsg()+"");
                    return;
                }
            }
        });




    }

    private void setToken(int data) {
        OkGo.<String>post(NetAddressUtils.setToken)
                .params("id",data)
                .params("token",XGPushConfig.getToken(this)).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        SpUtils.putString(ConstantUtils.Username,etUsername.getText().toString().trim(),LoginActivity.this);
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        finish();
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            finish();
            System.exit(0);
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }
}
