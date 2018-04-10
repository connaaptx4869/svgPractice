package com.parker.svgpractice;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import com.parker.svgpractice.databinding.ActivitySvgAnimTestBinding;

/**
 * Author : zhenh.
 * Created by Orz on 2018/4/3 12:31.
 */

public class SvgAnimTestActivity extends AppCompatActivity {
    private ActivitySvgAnimTestBinding mBinding;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_svg_anim_test);
        mBinding.anim.setPivotX(100);
        mBinding.anim.setPivotY(100);
//        AnimatedVectorDrawableCompat animatedVectorDrawableCompat = AnimatedVectorDrawableCompat.create(
//                this, R.drawable.ic_rabbit_anim);
//        mBinding.anim.setImageDrawable(animatedVectorDrawableCompat);
        ((Animatable) mBinding.anim.getDrawable()).start();
    }
}
