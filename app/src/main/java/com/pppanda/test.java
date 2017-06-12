package com.pppanda;

/**
 * Created by Administrator on 2017/6/12.
 */

import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;



 class TimePickerActivity extends Activity {
    private TextView textview = null;
    private Button button = null;
    private int hour,minute;
    // 时间选择对话框的标识
    public static final int SHOW_TIMEPICKER = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfamily);
//
//        button = (Button) findViewById(R.id.changeTimeBtn);
//        textview = (TextView) findViewById(R.id.timepicker_textview);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 显示时间选择对话框。
                showDialog(SHOW_TIMEPICKER);
            }
        });

        // 时间默认是当前的时间。
        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch(id) {
            case SHOW_TIMEPICKER:
                // 24小时时间制。
                return new TimePickerDialog(this, timeSetListener, hour, minute, true);
        }

        return null;
    }

    // 事件监听，当用户选择好时间后，单击Set按钮时触发。
    TimePickerDialog.OnTimeSetListener timeSetListener = new OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour = hourOfDay;
            TimePickerActivity.this.minute = minute;
            updateTime();
        }
    };

    // 在textview更新时间
    private void updateTime() {
        textview.setText(new StringBuilder().append(pad(hour)).append(":").append(pad(minute)));
    }

    // 如果值小于10，则前面补0
    private static String pad(int c) {
        if (c >= 10) {
            return String.valueOf(c);
        }
        else {
            return "0" + String.valueOf(c);
        }
    }
}