package com.exchange.client.welcome;

import android.os.Bundle;

import com.exchange.client.R;
import com.exchange.client.ui.BaseActivity;

/**
 * 欢迎页面(新手引导)
 */
public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initView();
        initListener();
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
