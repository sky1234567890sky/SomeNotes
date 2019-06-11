package com.example.a1.geeknewschr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a1.geeknewschr.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalenderActivity extends AppCompatActivity {

    @BindView(R.id.tv_calender_datatime)
    TextView mTvCalenderDatatime;
    @BindView(R.id.calendarView)
    MaterialCalendarView mCalendarView;
    @BindView(R.id.calender_ok)
    Button mCalenderOk;
    private CalendarDay mDatas;
    private String mS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        ButterKnife.bind(this);

        initListener();
    }

    private void initListener() {
        mCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                mDatas = date;
                int month = mDatas.getMonth();
                String month2 = month + 1 + "";
                if (month < 10) {
                    StringBuilder stringBuilder = new StringBuilder(month2);
                    stringBuilder.insert(0, "0");
                    month2 = stringBuilder.toString();
                }
                int day = mDatas.getDay();
                String day2 = day + "";
                if (day < 10) {
                    StringBuilder stringBuilder = new StringBuilder(day2);
                    StringBuilder insert = stringBuilder.insert(0, "0");
                    day2 = stringBuilder.toString();
                }
                mTvCalenderDatatime.setText(mDatas.getYear() + "" + month2 + "" + day2);
                mS = mDatas.getYear() + "" + month2 + day2;
            }
        });
        mCalenderOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mS != null) {
                    Intent intent = getIntent();
                    intent.putExtra("ms", mS);
                    setResult(300, intent);
                }
                finish();
            }
        });
    }
}
