package com.parker.svgpractice;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import com.parker.svgpractice.databinding.ActivitySvgSimpleBinding;

/**
 * Author : zhenh.
 * Created by Orz on 2018/4/3 12:31.
 */

public class SvgSimpleActivity extends AppCompatActivity {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private ActivitySvgSimpleBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_svg_simple);
        mBinding.iv.setImageResource(R.drawable.ic_rabbit);
        mBinding.bt.setBackgroundResource(R.drawable.ic_rabbit);
    }
}
