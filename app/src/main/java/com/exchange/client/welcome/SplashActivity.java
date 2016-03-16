package com.exchange.client.welcome;

import android.os.Bundle;

import com.exchange.client.R;
import com.exchange.client.ui.BaseActivity;

/**
 * 启动页
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}
