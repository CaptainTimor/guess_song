package com.likeminded.guess_song.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    //定义按钮
    private ImageButton imageButton_play;
    //定义ImageView
    private ImageView imageView_disc;
    private ImageView imageView_bar;
    //定义盘片动画
    private Animation mPanAnim;//命名规范 PanAnim前加m
    //定义盘片动画速度
    private LinearInterpolator mPanLin;
    //定义拨片动画
    private Animation mBarInAnim;
    //定义拨片动画速度
    private LinearInterpolator mBarInLin;
    //定义拨片归位动画
    private Animation mBarOutAnim;
    //定义拨片归位动画速度
    private LinearInterpolator mBarOutLin;
    //定义一个布尔类型的变量用来控制播放按钮当前的状态
    private boolean mIsRunning = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_main);
        //初始化盘片动画文件
        mPanAnim = AnimationUtils.loadAnimation(this, R.anim.pan_animation);
        //初始化盘片动画速度
        mPanLin = new LinearInterpolator();
        //使mPanAnim与mPanLIn关联
        mPanAnim.setInterpolator(mPanLin);
        //设置盘片的动画监听器
        mPanAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            //当盘片动画结束后调用拨片的归位动画
            @Override
            public void onAnimationEnd(Animation animation) {
                imageView_bar.startAnimation(mBarOutAnim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        //初始化拨片拨动动画
        mBarInAnim = AnimationUtils.loadAnimation(this, R.anim.barin_animation);
        //初始化拨片动画的速度
        mBarInLin = new LinearInterpolator();
        //当拨片动画结束后让拨片停留在结束位置
        mBarInAnim.setFillAfter(true);
        //使mBarInAnim与mBarInLin关联
        mBarInAnim.setInterpolator(mBarInLin);
        //设置mBarInAnim的动画监听器
        mBarInAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
//当拨片拨动的动画结束后调用盘片动画
            @Override
            public void onAnimationEnd(Animation animation) {
                imageView_disc.startAnimation(mPanAnim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        //初始化mBarOutAnim拨片的归位动画
        mBarOutAnim = AnimationUtils.loadAnimation(this, R.anim.barout_animation);
        //初始化拨片的动画速度
        mBarOutLin = new LinearInterpolator();
        //当拨片动画结束后让拨片停留在结束位置
        mBarOutAnim.setFillAfter(true);
        //使mBarOutAnim和mBarOutLin关联
        mBarOutAnim.setInterpolator(mBarOutLin);
        //设置mBarOutLin的动画监听器
        mBarOutAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mIsRunning = false;
                imageButton_play.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        imageView_disc = (ImageView) findViewById(R.id.id_game_disc);
        imageView_bar = (ImageView) findViewById(R.id.id_pan_bar_pin);
        imageButton_play = (ImageButton) findViewById(R.id.id_play_button);
        imageButton_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlebutton();
            }
        });
    }
//Play_Button 的点击方法
    private void handlebutton() {
        //判断imageView_bar是否为空，防止程序出现空指针异常
        if(imageView_bar != null){
            //判断Play_Button当前的状态如果为false则可以播放音乐否则不能播放
            if(!mIsRunning){
                mIsRunning = true;
                imageView_bar.startAnimation(mBarInAnim);
                imageButton_play.setVisibility(View.INVISIBLE);
            }
        }
    }
}
