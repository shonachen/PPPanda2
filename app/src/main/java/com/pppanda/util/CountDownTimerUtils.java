package com.pppanda.util;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;

import com.pppanda.R;

/**
 * 发送验证码按钮点击后倒计时
 */

public class CountDownTimerUtils extends CountDownTimer {
    private Button button;

    public CountDownTimerUtils(Button button,long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.button = button;

    }

    public void onTick(long millisUntilFinished) {
        button.setClickable(false); //设置不可点击
        button.setText("重新发送" + millisUntilFinished / 1000 + "s");  //设置倒计时时间
        button.setBackgroundResource(R.drawable.btn_login); //设置按钮为浅色，这时是不能点击的

        /**
         * 超链接 URLSpan
         * 文字背景颜色 BackgroundColorSpan
         * 文字颜色 ForegroundColorSpan
         * 字体大小 AbsoluteSizeSpan
         * 粗体、斜体 StyleSpan
         * 删除线 StrikethroughSpan
         * 下划线 UnderlineSpan
         * 图片 ImageSpan
         * http://blog.csdn.net/ah200614435/article/details/7914459
         */
        SpannableString mspannableString = new SpannableString(button.getText().toString());  //获取按钮上的文字
        ForegroundColorSpan span = new ForegroundColorSpan(Color.WHITE);
        /**
         * public void setSpan(Object what, int start, int end, int flags) {
         * 主要是start跟end，start是起始位置,无论中英文，都算一个。
         * 从0开始计算起。end是结束位置，所以处理的文字，包含开始位置，但不包含结束位置。
         */
//        mspannableString.setSpan(span, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//将倒计时的时间设置为红色
//        button.setText(mspannableString);
    }

    @Override
    public void onFinish() {
        button.setText("发送验证码");
        button.setClickable(true);//重新获得点击
        button.setBackgroundResource(R.drawable.btn_next);  //还原背景色
    }
}

