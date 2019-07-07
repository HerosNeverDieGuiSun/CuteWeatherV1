package com.example.cuteweatherv1.ui.notice.library;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.example.cuteweatherv1.R;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 *创建者：zzd
 *时间：2019/7/6
 *功能：灾害信息的绘制函数
 */
public class TipView extends FrameLayout {

    /**  动画间隔  */
    private   int ANIM_DELAYED_MILLIONS = 1500;
    /**  动画持续时长  */
    private static final int ANIM_DURATION = 1 * 1000;
    /**  默认字体颜色  */
    private static final String DEFAULT_TEXT_COLOR = "#2F4F4F";
    /**  默认字体大小  dp  */
    private static final int DEFAULT_TEXT_SIZE = 14;
    private Animation anim_out, anim_in;
    private TextView tv_tip_out, tv_tip_in ;
    /**  循环播放的消息  */
    private List<String> tipList;
    /**  当前轮播到的消息索引  */
    private int curTipIndex = 0;
    private long lastTimeMillis ;
    private Drawable image;

    public TipView(Context context) {
        this(context, null);
    }

    public TipView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TipView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTipFrame();

    }

    private void initTipFrame() {
        image = loadDrawable(R.drawable.notice);
        tv_tip_out = newTextView();
        tv_tip_in = newTextView();
        addView(tv_tip_in);
        addView(tv_tip_out);
    }

    /**
     *  设置要循环播放的信息
     * @param noticeStr
     */
    public void setTipList(String noticeStr) {
        this.ANIM_DELAYED_MILLIONS = 1500;
        initAnimation();
        int i = 0;
        ArrayList<String> list = new ArrayList<String>();
        int k = noticeStr.length() ;
        for(;i < noticeStr.length() ; ){
            if (i+22 < noticeStr.length()){
                //滚动条一行22个字符
                list.add(noticeStr.substring(i,i+22));
            }
            i = i+22;
        }
        list.add(noticeStr.substring(i-22,noticeStr.length()));

        this.tipList = list;
        curTipIndex = 0;
        updateTip(tv_tip_out);
        updateTipAndPlayAnimation();
    }
    //设置正常的预警信息
    public void setNormalTip(){
        //设置滚动的间隔时间
        this.ANIM_DELAYED_MILLIONS = 150000;
        //初始化滚动条
        initAnimation();
        String a = "本城市暂无灾害预警信息";
        //把String转换成list
        ArrayList<String> list = new ArrayList<String>();
        list.add(a);
        this.tipList = list;
        curTipIndex = 0;
        updateTip(tv_tip_out);
        updateTipAndPlayAnimation();
    }
    //初始化
    private void initAnimation() {
        anim_out = newAnimation(0, -1);//向上位移  动画1秒 ，间隔3秒
        anim_in = newAnimation(1, 0);//从下面出来
        anim_in.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                updateTipAndPlayAnimationWithCheck();//动画结束之后
            }
        });
    }

    private void updateTipAndPlayAnimation() {//动画结束之后，更新内容 两个TextView 轮流切换
        if (curTipIndex % 2 == 0) {
            updateTip(tv_tip_out);//更新内容
            tv_tip_in.startAnimation(anim_out);
            tv_tip_out.startAnimation(anim_in);
            this.bringChildToFront(tv_tip_in);
        } else {
            updateTip(tv_tip_in);
            tv_tip_out.startAnimation(anim_out);
            tv_tip_in.startAnimation(anim_in);
            this.bringChildToFront(tv_tip_out);
        }
    }

    private void updateTipAndPlayAnimationWithCheck() {
        if (System.currentTimeMillis() - lastTimeMillis < 1000 ) {
            return ;
        }
        lastTimeMillis = System.currentTimeMillis();
        updateTipAndPlayAnimation();
    }

    private void updateTip(TextView tipView) {//更新内容
//        if (new Random().nextBoolean()) {//头像可以自己设置或去掉
//            tipView.setCompoundDrawables(head_boy, null, null, null);
//        } else {
//            tipView.setCompoundDrawables(head_girl, null, null, null);
//        }
        tipView.setCompoundDrawables(image, null, null, null);
        String tip = getNextTip();
        if(!TextUtils.isEmpty(tip)) {
            tipView.setText(tip);
        }
    }

    /**
     *  获取下一条消息
     * @return
     */
    private String getNextTip() {
        if (isListEmpty(tipList)) return null;
        return tipList.get(curTipIndex++ % tipList.size());
    }

    private TextView newTextView(){
        TextView textView = new TextView(getContext());
        LayoutParams lp = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, Gravity.CENTER_VERTICAL);
        textView.setLayoutParams(lp);
        textView.setCompoundDrawablePadding(10);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setLines(2);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setTextColor(Color.parseColor(DEFAULT_TEXT_COLOR));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_TEXT_SIZE);
        return textView;
    }

    private Animation newAnimation(float fromYValue, float toYValue) {
        Animation anim = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0,
                Animation.RELATIVE_TO_SELF,fromYValue,Animation.RELATIVE_TO_SELF, toYValue);//上下位移动画，
        anim.setDuration(ANIM_DURATION);//动画时长
        anim.setStartOffset(ANIM_DELAYED_MILLIONS);//动画间隔
        anim.setInterpolator(new DecelerateInterpolator());
        return anim;
    }

    /**
     *  将资源图片转换为Drawable对象
     * @param ResId
     * @return
     */
    private Drawable loadDrawable(int ResId) {
        Drawable drawable = getResources().getDrawable(ResId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth() - 10, drawable.getMinimumHeight() - 10);
        return drawable;
    }

    /**
     *  list是否为空
     * @param list
     * @return true 如果是空
     */
    public static boolean isListEmpty(List list) {
        return list == null || list.isEmpty();
    }
}
