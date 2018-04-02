package com.parker.svgpractice;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.parker.svgpractice.databinding.ActivityMainBinding;

/**
 * @author zhenh
 */
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.iv.setImageResource(R.drawable.ic_rabbit);
        mBinding.bt.setBackgroundResource(R.drawable.ic_rabbit);
        mBinding.anim.setPivotX(100);
        mBinding.anim.setPivotY(100);
        AnimatedVectorDrawableCompat animatedVectorDrawableCompat = AnimatedVectorDrawableCompat.create(
                this, R.drawable.ic_rabbit_anim);
        mBinding.anim.setImageDrawable(animatedVectorDrawableCompat);
        ((Animatable) mBinding.anim.getDrawable()).start();
    }

    public void gotoMap(View view) {
        Intent intent = new Intent(MainActivity.this, MapActivity.class);
        startActivity(intent);
    }
}
