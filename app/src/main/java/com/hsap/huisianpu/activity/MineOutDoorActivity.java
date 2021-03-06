package com.hsap.huisianpu.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hsap.huisianpu.R;
import com.hsap.huisianpu.adapter.ViewPagerFragmentAdapter;
import com.hsap.huisianpu.base.BaseBackActivity;
import com.hsap.huisianpu.base.BaseFragmentPager;
import com.hsap.huisianpu.bean.EventDate;
import com.hsap.huisianpu.pager.mine.MineCarPager;
import com.hsap.huisianpu.pager.mine.MineLeavePager;
import com.hsap.huisianpu.pager.mine.MinePurchasePager;
import com.hsap.huisianpu.pager.mine.MineSummaryPager;
import com.hsap.huisianpu.pager.mine.MineTripPager;
import com.hsap.huisianpu.pager.mine.MineoutPager;
import com.hsap.huisianpu.pager.mine.MineoverTimePager;
import com.hsap.huisianpu.view.MyViewPager;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.WrapPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的外勤
 */

public class MineOutDoorActivity extends BaseBackActivity {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.mic_mine_out_door)
    MagicIndicator micMineOutDoor;
    @BindView(R.id.vp_mine_out_door)
    MyViewPager vpMineOutDoor;
    @BindView(R.id.tv_mine_outdoor)
    TextView tvMineOutdoor;
    @BindView(R.id.tv_checkreport_time)
    TextView tvCheckreportTime;
    @BindView(R.id.ll_checkreport_time)
    LinearLayout llCheckreportTime;
    private List<BaseFragmentPager> frgamentList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine_out_door;
    }

    @Override
    public void initView() {
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH) + 1;
        tvCheckreportTime.setText(year+"-"+month);
        frgamentList.add(new MineLeavePager());
        frgamentList.add(new MineoutPager());
        frgamentList.add(new MineTripPager());
        frgamentList.add(new MineoverTimePager());
        frgamentList.add(new MineCarPager());
        frgamentList.add(new MinePurchasePager());
        frgamentList.add(new MineSummaryPager());

    }

    private void initMic() {
        final ArrayList<String> list = new ArrayList<>();
        list.add("我的请假");
        list.add("我的外出");
        list.add("我的出差");
        list.add("我的加班");
        list.add("我的用车");
        list.add("我的采购");
        list.add("我的总结");
        micMineOutDoor.setBackgroundColor(Color.WHITE);
        CommonNavigator navigator = new CommonNavigator(this);
        navigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {

                return list == null ? 0 : list.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int i) {
                SimplePagerTitleView view = new ColorTransitionPagerTitleView(context);
                view.setTextAppearance(context, R.style.LeaveText);
                view.setTextSize(18);
                view.setText(list.get(i));
                view.setNormalColor(Color.parseColor("#b3b3b3"));
                view.setSelectedColor(Color.parseColor("#1296db"));
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vpMineOutDoor.setCurrentItem(i);
                    }
                });

                return view;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                WrapPagerIndicator indicator = new WrapPagerIndicator(context);
                return indicator;
            }
        });
        micMineOutDoor.setNavigator(navigator);
        ViewPagerHelper.bind(micMineOutDoor, vpMineOutDoor);
    }

    @Override
    public void initData() {
        initMic();
        vpMineOutDoor.setOffscreenPageLimit(7);
        vpMineOutDoor.setAdapter(new ViewPagerFragmentAdapter(getSupportFragmentManager(), frgamentList));
    }

    @Override
    public void initListener() {
        back.setOnClickListener(this);
        llCheckreportTime.setOnClickListener(this);
        vpMineOutDoor.addOnPageChangeListener(getListener());
    }

    private ViewPager.OnPageChangeListener getListener() {
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                micMineOutDoor.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position)
            {   switch (position){
                case 0:
                    tvMineOutdoor.setText("我的请假");
                    break;
                case 1:
                    tvMineOutdoor.setText("我的外出");
                    break;
                case 2:
                    tvMineOutdoor.setText("我的出差");
                    break;
                case 3:
                    tvMineOutdoor.setText("我的加班");
                    break;
                case 4:
                    tvMineOutdoor.setText("我的用车");
                    break;
                default:
            }
                micMineOutDoor.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                micMineOutDoor.onPageScrollStateChanged(state);
            }
        };
    }

    @Override
    public void processClick(View v) {
            switch (v.getId()){
                case R.id.ll_checkreport_time:
                    Calendar calendar = Calendar.getInstance();
                    int year=calendar.get(Calendar.YEAR);
                    int month= calendar.get(Calendar.MONTH);
                    int day =calendar.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                            //发送通知
                            EventBus.getDefault().post(new EventDate(i,i1+1));
                            int a=i1+1;
                            tvCheckreportTime.setText(i+"-"+a);
                        }
                    }, year, month, day);
                    dialog.show();
                    break;
                    default:

            }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
