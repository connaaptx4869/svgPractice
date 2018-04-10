package com.parker.svgpractice;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
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

    public void gotoAnimTest(View view) {
        Intent intent = new Intent(MainActivity.this, SvgAnimTestActivity.class);
        startActivity(intent);
    }

    public void gotoSimple(View view) {
        Intent intent = new Intent(MainActivity.this, SvgSimpleActivity.class);
        startActivity(intent);
    }

    public void gotoStar(View view) {
        Intent intent = new Intent(MainActivity.this, SvgAnimStarActivity.class);
        startActivity(intent);
    }
}
