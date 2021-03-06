package com.hsap.huisianpu.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.google.gson.Gson;
import com.hsap.huisianpu.R;
import com.hsap.huisianpu.adapter.AccompanyGvidViewAdapter;
import com.hsap.huisianpu.adapter.ApproveGridViewAdapter;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.bean.Bean;
import com.hsap.huisianpu.bean.PushTripBean;
import com.hsap.huisianpu.utils.ConstantUtils;
import com.hsap.huisianpu.utils.NetAddressUtils;
import com.hsap.huisianpu.utils.SpUtils;
import com.hsap.huisianpu.utils.ToastUtils;
import com.hsap.huisianpu.view.MyGridView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhy.android.percent.support.PercentLinearLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 出差界面
 */

public class WorkTripActivity extends BaseBackActivity {
    private static final String TAG = "WorkTripActivity";
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.bt_trip_commit)
    Button btTripCommit;
    @BindView(R.id.et_trip_reason)
    EditText etTripReason;
    @BindView(R.id.et_trip_city)
    EditText etTripCity;
    @BindView(R.id.tv_trip_begin)
    TextView tvTripBegin;
    @BindView(R.id.pll_trip_begin)
    PercentLinearLayout pllTripBegin;
    @BindView(R.id.tv_trip_end)
    TextView tvTripEnd;
    @BindView(R.id.pll_trip_end)
    PercentLinearLayout pllTripEnd;
    @BindView(R.id.tv_trip_day)
    TextView tvTripDay;
    /* @BindView(R.id.et_trip_remark)
     EditText etTripRemark;*/
    @BindView(R.id.bt_leave_again)
    Button btLeaveAgain;
    @BindView(R.id.gv_trip_person)
    MyGridView gvTripPerson;
    /*@BindView(R.id.gv_trip)
    MyGridView gvTrip;*/
    private StringBuilder beginTime = new StringBuilder();
    private StringBuilder endTime = new StringBuilder();
    private StringBuffer Pm = new StringBuffer();//下午
    private StringBuffer Am = new StringBuffer();//上午
    private List<Bean> list = new ArrayList<>(); //审批人
    private List<Integer> idList = new ArrayList<>();//存放 审批人的id
    private List<Bean> personList = new ArrayList<>();//陪同人
    private List<Integer> personIdList = new ArrayList<>();//陪同人id
    private int[] color = {R.mipmap.chengyuan, R.mipmap.fenyuan, R.mipmap.lanyuan,
            R.mipmap.luyuan, R.mipmap.ziyuan, R.mipmap.hongyuan};
    private ApproveGridViewAdapter adapter;
    private AccompanyGvidViewAdapter accompanyGvidViewAdapter;
    StringBuilder begin = new StringBuilder();
    StringBuilder end = new StringBuilder();
    private int id;

    @Override
    public int getLayoutId() {

        return R.layout.activity_work_trip;
    }

    @Override
    public void initView() {
        accompanyGvidViewAdapter = new AccompanyGvidViewAdapter(WorkTripActivity.this, personList);
        gvTripPerson.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gvTripPerson.setAdapter(accompanyGvidViewAdapter);
        gvTripPerson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == personList.size()) {
                    //跳转到选择陪同人
                    Intent intent = new Intent(WorkTripActivity.this, ChooseAccompanyActivity.class);
                    startActivityForResult(intent, 200);
                } else {
                    personList.remove(position);
                    personIdList.remove(position);
                    accompanyGvidViewAdapter.notifyDataSetChanged();
                }
            }
        });

        adapter = new ApproveGridViewAdapter(WorkTripActivity.this, list);
       /* gvTrip.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gvTrip.setAdapter(adapter);
        gvTrip.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == list.size()) {
                    //跳到选择联系人页面
                    startActivityForResult(
                            new Intent(WorkTripActivity.this,
                                    SelectApproverActivity.class), 100);
                } else {
                    list.remove(position);
                    idList.remove(position);
                    adapter.notifyDataSetChanged();
                }
            }
        });*/
    }

    @Override
    public void initData() {
        id = getIntent().getIntExtra("id", 0);
        int state = getIntent().getIntExtra("state", 0);
        if (state == 1) {
            LoadingDailog 获取数据中 = ToastUtils.showDailog(this, "获取数据中");
            获取数据中.show();
            btTripCommit.setVisibility(View.GONE);
            btLeaveAgain.setVisibility(View.VISIBLE);
            dataFormNet(id, 获取数据中);
        } else {
            btTripCommit.setVisibility(View.VISIBLE);
            btLeaveAgain.setVisibility(View.GONE);
        }

    }

    private void dataFormNet(int id, final LoadingDailog 获取数据中) {
        OkGo.<String>post(NetAddressUtils.selectOneIntergration).
                params("id", id).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.e(TAG, "onSuccess: ");
                Log.e(TAG, response.body().toString());
                PushTripBean bean = new Gson().fromJson(response.body().toString(), PushTripBean.class);
                if (bean.isSuccess()) {
                    获取数据中.dismiss();

                    if (bean.getData().getWaIntegration().getStartTime().getHour() == 8) {
                        begin.setLength(0);
                        begin.append("上午");
                    } else {
                        begin.setLength(0);
                        begin.append("下午");
                    }
                    if (bean.getData().getWaIntegration().getEndTime().getHour() == 8) {
                        end.setLength(0);
                        end.append("上午");
                    } else {
                        end.setLength(0);
                        end.append("下午");
                    }
                    etTripReason.setText(bean.getData().getWaIntegration().getReason());
                    etTripCity.setText(bean.getData().getWaIntegration().getType2());

                    tvTripBegin.setText(bean.getData().getWaIntegration().getStartTime().getYear() + "-" +
                            bean.getData().getWaIntegration().getStartTime().getMonthValue() + "-" +
                            bean.getData().getWaIntegration().getStartTime().getDayOfMonth() + "-" + " " + begin);
                    tvTripEnd.setText(bean.getData().getWaIntegration().getEndTime().getYear() + "-" +
                            bean.getData().getWaIntegration().getEndTime().getMonthValue() + "-" +
                            bean.getData().getWaIntegration().getEndTime().getDayOfMonth() + "-" + " " + end);
                    tvTripDay.setText(bean.getData().getWaIntegration().getTotalTime() + "");
                    if (bean.getData().getNameList().size() != 0 && bean.getData().getNameList() != null) {
                        for (int i = 0; i <bean.getData().getNameList().size(); i++) {
                            personList.add(new Bean(bean.getData().getNameList().get(i),color[(int) (Math.random() * 6)]));
                            personIdList.add(bean.getData().getNameId().get(i));
                        }
                        gvTripPerson.setSelector(new ColorDrawable(Color.TRANSPARENT));
                        gvTripPerson.setAdapter(accompanyGvidViewAdapter);
                    }
                } else {
                    获取数据中.dismiss();
                    ToastUtils.showToast(WorkTripActivity.this, "当前没有数据");
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                获取数据中.dismiss();
                ToastUtils.showToast(WorkTripActivity.this, "获取数据失败");
            }
        });
    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
        btTripCommit.setOnClickListener(this);
        btLeaveAgain.setOnClickListener(this);
        pllTripBegin.setOnClickListener(this);
        pllTripEnd.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.bt_trip_commit:
                showCommit();
                break;
            case R.id.pll_trip_begin:
                showBegin();
                break;
            case R.id.pll_trip_end:
                showEnd();
                break;
            case R.id.bt_leave_again:
                showCommitt();
                break;
            default:
        }
    }

    private void showCommitt() {
        if (TextUtils.isEmpty(etTripReason.getText().toString().trim())) {
            ToastUtils.showToast(this, "请输入出差事由");
            return;
        }
        if (TextUtils.isEmpty(etTripCity.getText().toString().trim())) {
            ToastUtils.showToast(this, "请输入出差城市");
            return;
        }
        if (tvTripBegin.getText().toString().trim().equals("请选择（必填）")) {
            ToastUtils.showToast(this, "请选择开始时间");
            return;
        }
        if (tvTripEnd.getText().toString().trim().equals("请选择（必填）")) {
            ToastUtils.showToast(this, "请选择结束时间");
            return;
        }
        if (Float.valueOf(tvTripDay.getText().toString().trim()) < 0.0) {
            ToastUtils.showToast(this, "请选择正确的返回日期");
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确定要提交吗？");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final LoadingDailog 提交中 = ToastUtils.showDailog(WorkTripActivity.this, "提交中");
                提交中.show();
                //TOdo 访问网络
                OkGo.<String>post(NetAddressUtils.insertIntegration).
                        params("activity", "com.hsap.huisianpu.push.PushTirpActivity").
                        params("workersId", SpUtils.getInt(ConstantUtils.UserId, WorkTripActivity.this)).
                        params("reason", etTripReason.getText().toString().trim()).
                        params("type2", etTripCity.getText().toString().trim()).
                        params("type", 2).
                        params("reStart",id).
                        params("startTime", tvTripBegin.getText().toString().trim()).
                        params("endTime", tvTripEnd.getText().toString().trim()).
                        params("ids", new Gson().toJson(personIdList)).
                        params("totalTime", Float.valueOf(tvTripDay.getText().toString().trim())).
                        execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                提交中.dismiss();
                                ToastUtils.showToast(WorkTripActivity.this, "提交成功");
                                Log.e(TAG, "onSuccess: " + response.body().toString());
                            }

                            @Override
                            public void onError(Response<String> response) {
                                super.onError(response);
                                提交中.dismiss();
                                ToastUtils.showToast(WorkTripActivity.this, "提交失败，当前网络不好");
                            }
                        });
            }
        });
        builder.show();
    }

    private void showBegin() {
        beginTime.setLength(0);
        Am.setLength(0);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择上午/下午");
        final int[] choose = {0};
        final String[] item = {"上午", "下午"};
        builder.setSingleChoiceItems(item, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                choose[0] = i;
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Am.append(item[choose[0]]);
                tvTripBegin.setText(beginTime + " " + Am);
            }
        });
        builder.setNegativeButton("取消", null);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                beginTime.append(i + "-" + (i1 + 1) + "-" + i2);
                builder.show();
            }
        }, year, month, day).show();
    }

    private void showEnd() {
        endTime.setLength(0);
        Pm.setLength(0);
        if (tvTripBegin.getText().toString().trim().equals("请选择（必填）")) {
            ToastUtils.showToast(this, "请先选择开始时间");
            return;
        }
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择上午/下午");
        final int[] choose = {0};
        final String[] item = {"上午", "下午"};
        builder.setSingleChoiceItems(item, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                choose[0] = i;
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Pm.append(item[choose[0]]);
                tvTripEnd.setText(endTime + " " + Pm);
                if (tvTripBegin.getText().toString().equals(tvTripEnd.getText().toString())) {
                    tvTripDay.setText("0.5");
                    return;
                }
                if (endTime.toString().equals(beginTime.toString()) && !Pm.toString().equals(Am.toString())) {
                    tvTripDay.setText("1");
                    return;
                }
                if (!endTime.toString().equals(beginTime.toString()) && Pm.toString().equals(Am.toString())) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
                    try {
                        Date begin = sdf.parse(beginTime.toString());
                        Date end = sdf.parse(endTime.toString());
                        int day = (int) ((end.getTime() - begin.getTime()) / (1000 * 3600 * 24));
                        tvTripDay.setText(day + ".5");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
                    try {
                        Date begin = sdf.parse(beginTime.toString());
                        Date end = sdf.parse(endTime.toString());
                        int day = (int) ((end.getTime() - begin.getTime()) / (1000 * 3600 * 24));
                        tvTripDay.setText(day + 1 + "");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        builder.setNegativeButton("取消", null);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                endTime.append(i + "-" + (i1 + 1) + "-" + i2);
                builder.show();
            }
        }, year, month, day).show();
    }

    private void showCommit() {
        if (TextUtils.isEmpty(etTripReason.getText().toString().trim())) {
            ToastUtils.showToast(this, "请输入出差事由");
            return;
        }
        if (TextUtils.isEmpty(etTripCity.getText().toString().trim())) {
            ToastUtils.showToast(this, "请输入出差城市");
            return;
        }
        if (tvTripBegin.getText().toString().trim().equals("请选择（必填）")) {
            ToastUtils.showToast(this, "请选择开始时间");
            return;
        }
        if (tvTripEnd.getText().toString().trim().equals("请选择（必填）")) {
            ToastUtils.showToast(this, "请选择结束时间");
            return;
        }
        if (Float.valueOf(tvTripDay.getText().toString().trim()) < 0.0) {
            ToastUtils.showToast(this, "请选择正确的返回日期");
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确定要提交吗？");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final LoadingDailog 提交中 = ToastUtils.showDailog(WorkTripActivity.this, "提交中");
                提交中.show();
                //TOdo 访问网络
                OkGo.<String>post(NetAddressUtils.insertIntegration).
                        params("activity", "com.hsap.huisianpu.push.PushTirpActivity").
                        params("workersId", SpUtils.getInt(ConstantUtils.UserId, WorkTripActivity.this)).
                        params("reason", etTripReason.getText().toString().trim()).
                        params("type2", etTripCity.getText().toString().trim()).
                        params("type", 2).
                      params("startTime", tvTripBegin.getText().toString().trim()).
                        params("endTime", tvTripEnd.getText().toString().trim()).
                        params("ids", new Gson().toJson(personIdList)).
                        params("totalTime", Float.valueOf(tvTripDay.getText().toString().trim())).
                        execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                提交中.dismiss();
                                ToastUtils.showToast(WorkTripActivity.this, "提交成功");
                                Log.e(TAG, "onSuccess: " + response.body().toString());
                            }

                            @Override
                            public void onError(Response<String> response) {
                                super.onError(response);
                                提交中.dismiss();
                                ToastUtils.showToast(WorkTripActivity.this, "提交失败，当前网络不好");
                            }
                        });
            }
        });
        builder.show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == 101) {
                Bundle bundle = data.getExtras();
                String name = bundle.getString("name");
                int id = bundle.getInt("id");
                Bean bean = new Bean();
                bean.setName(name);
                bean.setPic(color[(int) (Math.random() * 6)]);
                list.add(bean);
                idList.add(id);
                adapter.notifyDataSetChanged();
            }
        }
        if (requestCode == 200) {
            if (resultCode == 201) {
                ArrayList<String> namelist = data.getStringArrayListExtra("namelist");
                ArrayList<Integer> idlist = data.getIntegerArrayListExtra("idlist");
                for (int i = 0; i < namelist.size(); i++) {
                    Bean bean = new Bean();
                    bean.setName(namelist.get(i));
                    bean.setPic(color[(int) (Math.random() * 6)]);
                    personList.add(bean);
                }
                personIdList.addAll(idlist);
                accompanyGvidViewAdapter.notifyDataSetChanged();

            }
        }
    }
}
