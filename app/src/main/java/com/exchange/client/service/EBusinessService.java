package com.exchange.client.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

/**
 * Creator:xiangyutian <br>
 * Date:15/7/23 下午5:39
 */
public class EBusinessService extends Service {

    /**
     * params
     */
    WindowManager wm = null;
    WindowManager.LayoutParams ballWmParams = null;
    private View ballView; // 球状态View

    public EBusinessService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ballView = new View(this);
        createView();
    }

    private void createView() {
        wm = (WindowManager) getSystemService("window");
        ballWmParams = new WindowManager.LayoutParams();
        ballWmParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        ballWmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        ballWmParams.gravity = Gravity.LEFT | Gravity.TOP;
        ballWmParams.x = 0;
        ballWmParams.y = 0;
        ballWmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        ballWmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        ballWmParams.format = PixelFormat.RGBA_8888;
        // 添加显示
        wm.addView(ballView, ballWmParams);
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
