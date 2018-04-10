package com.parker.svgpractice;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;

import com.parker.svgpractice.databinding.ActivitySvgAnimStarBinding;

/**
 * Author : zhenh.
 * Created by Orz on 2018/4/3 12:31.
 */

public class SvgAnimStarActivity extends AppCompatActivity {
    private ActivitySvgAnimStarBinding mBinding;
    private boolean checked;
    private boolean isInAnim;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (checked) {
                mBinding.anim.setImageResource(R.drawable.ic_collection_anim_check);
            } else {
                mBinding.anim.setImageResource(R.drawable.ic_collection_anim_uncheck);
            }
            isInAnim = false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_svg_anim_star);
        mBinding.anim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInAnim) {
                    return;
                }
                isInAnim = true;
                ((Animatable) mBinding.anim.getDrawable()).start();
                checked = !checked;
                Message message = handler.obtainMessage();
                handler.sendMessageDelayed(message, 300);
            }
        });
    }
}
