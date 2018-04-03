package com.parker.svgpractice;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
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
    }

    public void gotoMap(View view) {
        Intent intent = new Intent(MainActivity.this, MapActivity.class);
        startActivity(intent);
    }

    public void gotoAnim(View view) {
        Intent intent = new Intent(MainActivity.this, SvgAnimActivity.class);
        startActivity(intent);
    }

    public void gotoSimple(View view) {
        Intent intent = new Intent(MainActivity.this, SvgSimpleActivity.class);
        startActivity(intent);
    }
}
