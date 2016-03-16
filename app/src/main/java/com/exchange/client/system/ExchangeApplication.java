package com.exchange.client.system;

import android.app.Application;

public class ExchangeApplication extends Application {

    private static ExchangeApplication instance = null;

    public static ExchangeApplication getInstance() {
        return instance;
    }

    public ExchangeApplication() {
        super();
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();

//        HttpTasksManager.getInstance().init(this);
    }

}
