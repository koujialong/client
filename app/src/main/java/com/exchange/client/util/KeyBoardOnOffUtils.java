package com.exchange.client.util;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by qilin on 2016/2/27.
 */
public class KeyBoardOnOffUtils {

    /**
     * 打开软键盘
     * @date: 2016/2/26 18:23
     * @author: KJL
     * @param:
     * @return:
     */
    public static void openInputMethod(final EditText editText){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                InputMethodManager inputManager = (InputMethodManager) editText
                        .getContext().getSystemService(
                                Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(editText, 0);
            }
        }, 300);

    }
    /**
     * 关闭软键盘
     * @date: 2016/2/26 18:36
     * @author: KJL
     * @param:
     * @return:
     */
    public static void closeInputMethod(Activity context){
        try {
            ((InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) { }finally{ }
    }
}
