package com.exchange.client.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.exchange.client.R;
import com.exchange.client.ui.BaseActivity;

/**
 * 避免出现多个toast显示的case
 *
 * @author xiangyutian <br/>
 *         create at 2014-4-16 上午11:19:02
 */
public class ToastUtils {

    private static Toast toast;
    private static Toast qilintoast;
    private static Toast getToast;
//    private static Object obj;
    private static int version = android.os.Build.VERSION.SDK_INT;
    private static final int MAX_NEED_CANCEL_VERSION = 10;

    /**
     * 显示toast,Toast.LENGTH_SHORT
     *
     * @param context
     * @param resId
     */
    public static void ToastShort(Context context, int resId) {
        initToast(context, resId);
        toast.show();
    }

    /**
     * 显示toast,Toast.LENGTH_SHORT
     *
     * @param context
     * @param text
     */
    public static void ToastShort(Context context, String text) {
        initToast(context, text);
        toast.show();
    }

    /**
     * 显示toast,Toast.LENGTH_LONG
     *
     * @param context
     * @param resId
     */
    public static void ToastLong(Context context, int resId) {
        initToast(context, resId);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }

    /**
     * 显示toast,Toast.LENGTH_LONG
     *
     * @param context
     * @param text
     */
    public static void ToastLong(Context context, String text) {
        initToast(context, text);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }



    /**
     * 显示toast,Toast.LENGTH_SHORT
     *
     * @param context
     * @param resId
     */
    public static void ToastQilinShort(Context context, int resId) {
        initQilinToast(context, resId);
        qilintoast.show();
    }

    /**
     * 显示toast,Toast.LENGTH_SHORT
     *
     * @param context
     * @param text
     */
    public static void ToastQilinShort(Context context, String text) {
        initQilinToast(context, text);
        qilintoast.show();
    }

    /**
     * 显示toast,Toast.LENGTH_LONG
     *
     * @param context
     * @param resId
     */
    public static void ToastQilinLong(Context context, int resId) {
        initQilinToast(context, resId);
        qilintoast.setDuration(Toast.LENGTH_LONG);
        qilintoast.show();
    }

    /**
     * 显示toast,Toast.LENGTH_LONG
     *
     * @param context
     * @param text
     */
    public static void ToastQilinLong(Context context, String text) {
        initQilinToast(context, text);
        qilintoast.setDuration(Toast.LENGTH_LONG);
        qilintoast.show();
    }

    /**
     * 隐藏toast
     */
    public static void hide() {
        if (toast == null) {
            return;
        }
        toast.cancel();
        if (qilintoast == null) {
            return;
        }
        qilintoast.cancel();
    }
    /**
     * 初始化Toast ，替换为统一样式， R.layout.toast
     *
     * @param resId 需要显示的String 资源ID
     */
    private static void initToast(Context context, int resId) {
        initToast(context, context.getResources().getString(resId));
    }

    /**
     * 初始化Toast ，替换为统一样式， R.layout.toast
     *
     * @param resId 需要显示的String 资源ID
     */
    private static void initQilinToast(Context context, int resId) {
        initQilinToast(context, context.getResources().getString(resId));
    }

    /**
     * 初始化Toast ，替换为统一样式， R.layout.toast
     *
     * @param text 需要显示的Toast 文字
     */
    @SuppressLint("ShowToast")
    private static void initToast(Context context, String text) {
        if (toast == null) {
            if (context != null) {
                toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            }
        }
        toast.setText(text);
        if (version <= MAX_NEED_CANCEL_VERSION) {
            toast.cancel();
        }
    }

    private static void initQilinToast(Context context, String text) {
        Activity activityContext = BaseActivity.getTopActivity();
        if (activityContext != null) {
            qilintoast = Toast.makeText(activityContext, text, Toast.LENGTH_SHORT);
            // 自定义toast
            View toastRoot = activityContext.getLayoutInflater().inflate(R.layout.vw_common_toast, null);
            qilintoast.setView(toastRoot);
            qilintoast.setGravity(Gravity.CENTER, 0, 0);
            TextView toastInfo = (TextView) toastRoot.findViewById(R.id.toast_info);
            toastInfo.setText(text);
        } else {
            if (qilintoast == null) {
                qilintoast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            }
            // 自定义toast
            qilintoast.setText(text);
        }
        if (version <= MAX_NEED_CANCEL_VERSION) {
            qilintoast.cancel();
        }
    }

//    //TODO ----------------------------------------------------------
//
//    /**
//     * 自定义toast可以手动控制显示时间
//     * @param context
//     * @param text
//     */
//    @Deprecated
//    private static void initGetToast(Context context, String text){
//        Activity activityContext = BaseActivity.getTopActivity();
//        Toast toast1 = null;
//        if (activityContext != null) {
//            toast1 = getToast = Toast.makeText(activityContext, text, Toast.LENGTH_SHORT);
//            // 自定义toast
//            View toastRoot = activityContext.getLayoutInflater().inflate(R.layout.vw_common_toast, null);
//            getToast.setView(toastRoot);
//            getToast.setGravity(Gravity.CENTER, 0, 0);
//            TextView toastInfo = (TextView) toastRoot.findViewById(R.id.toast_info);
//            toastInfo.setText(text);
//        } else {
//            if (getToast == null) {
//                toast1 = getToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
//            }
//            // 自定义toast
//            getToast.setText(text);
//        }
//        try{
//            Field field = toast1.getClass().getDeclaredField("mTN");
//            field.setAccessible(true);
//            obj = field.get(toast1);
//            Method method = obj.getClass().getDeclaredMethod("show", null);
//            method.invoke(obj, null);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 开启自定控制显示时间的toast
//     * @param context
//     * @param text
//     */
//    public static void ToastGetShort(Context context, String text) {
//        initGetToast(context, text);
//        getToast.show();
//    }
//
//    /**
//     * 关闭自定义控制显示时间的toast
//     */
//    @Deprecated
//    public static void hide2(){
//        if (getToast == null){
//            return;
//        }
//        try {
//            Method method = obj.getClass().getDeclaredMethod("hide", null);
//            method.invoke(obj, null);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }


}
