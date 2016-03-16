/*
 * HttpTaskManager.java
 * classes : com.sh.shvideo.task.HttpTaskManager
 * @author xiangyutian
 * V 1.0.0
 * Create at 2014-3-20 下午2:20:35
 */
package com.exchange.client.http;

import android.os.Handler;
import android.text.TextUtils;

import com.exchange.client.http.center.AsyncRequestCenter;
import com.exchange.client.http.center.RequestParams;
import com.exchange.client.http.exception.HttpClientApiException;
import com.exchange.client.http.parser.IResultDataParser;
import com.exchange.client.http.parser.IResultDbProcess;
import com.exchange.client.http.parser.IResultReceiver;
import com.exchange.client.system.ExchangeApplication;
import com.exchange.client.util.LogUtils;
import com.exchange.client.util.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 视频异步任务的帮助类，負責处理具体业务<br/>
 * 代理UI层发起的网络请求，将任务委派到{@link HttpTaskManager} 使用静态方法发起服务，
 *
 * @author xiangyutian <br/>
 *         create at 2014-3-20 下午2:20:35
 */
public class HttpTaskManager {

    /**
     * TAG
     */
    private static final String TAG = HttpTaskManager.class.getSimpleName();

    /**
     * handler
     */
    private static Handler mHandler;

    private static long mStartTime;
    private static long mEndTime;

    static {
        AsyncRequestCenter.getInstance().initiate(ExchangeApplication.getInstance().getApplicationContext());
        mHandler = new Handler();
    }

    /**
     * volley的StringRequest类型请求
     *
     * @param requestParams
     * @param parserObj
     * @param resultReceiver
     */
    public static <T> void startStringRequest(final RequestParams requestParams, final IResultDataParser<T> parserObj,
                                              final IResultReceiver resultReceiver) {
        mStartTime = System.currentTimeMillis();

        if (requestParams == null) {
            LogUtils.e(TAG, "startStringRequest error!!! requestParams is null!");
            return;
        }

        boolean isNetAvailable = NetworkUtils.isOnline(ExchangeApplication.getInstance());

        // cache无效，可判断网络状况
        // 判断网络是否可用
        if (!isNetAvailable) {
            if (resultReceiver == null) {
                return;
            }

            resultReceiver.onReceiveResult(ServerErrorCode.NO_CONNECTION_ERROR, null);
            return;
        }

        // 开启网络请求
        AsyncRequestCenter.getInstance().startStringRequest(requestParams, new AsyncRequestCenter.RequestListener<String>() {

            @Override
            public void onSuccess(String result) {
                // 解析器或者结果回调器为空则返回，不进行任何网络请求操作
                if ((parserObj == null) || (resultReceiver == null)) {
                    LogUtils.e(TAG, "parserObj or resultReceiver is null...");
                    return;
                }

                // 判断数据返回结果是否为空
                if (TextUtils.isEmpty(result)) {
                    resultReceiver.onReceiveResult(ServerErrorCode.STATUS_EMPTY, null);
                    return;
                }

                // 数据处理逻辑过程
                onContentProviderProcess(result, parserObj, null, resultReceiver);
            }

            @Override
            public void onFailure(int errorCode, String errorMsg) {
                // 转换至UI线程
                // runUiThread(resultReceiver, errorCode, errorMsg);

                LogUtils.d(TAG, "onFailure =====errorCode==== " + errorCode + " ==== errorMsg === " + errorMsg);
                // 数据处理逻辑过程
                onContentProviderProcess(errorMsg, parserObj, null, resultReceiver);
            }
        }, isNetAvailable);
    }

    /**
     * 数据解析和数据处理逻辑
     *
     * @param result
     * @param parserObj
     * @param dbProcess
     * @param resultReceiver create at 2014-4-9 下午3:38:30
     * @author xiangyutian
     */
    @SuppressWarnings("unchecked")
    private static <T> void onContentProviderProcess(final Object result, final IResultDataParser<T> parserObj,
                                                     final IResultDbProcess dbProcess, final IResultReceiver resultReceiver) {
        // 进行解析
        T resultObj = null;
        int statusCode = ServerErrorCode.STATUS_SUCCESS;

        try {
            resultObj = parserObj.parse(result);
        } catch (HttpClientApiException e) {
            // TODO Auto-generated catch block
            LogUtils.e(TAG, "EBusinessApiException break out", e);
            statusCode = e.getCode();
            resultObj = (T) e.getMessage();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            LogUtils.e(TAG, "JSONException break out", e);
            statusCode = ServerErrorCode.PARSE_ERROR;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            LogUtils.e(TAG, "IOException break out", e);
            statusCode = ServerErrorCode.PARSE_ERROR;
        } finally {
            // 数据库逻辑操作
            if ((dbProcess != null) && (resultObj != null)) {
                dbProcess.onDbProcess(resultObj);
            }
            // 转换至UI线程
            runUiThread(resultReceiver, statusCode, resultObj);
        }
    }

    /**
     * 切换至UI线程
     *
     * @param resultReceiver
     * @param statuscode
     * @param resultObj      create at 2014-3-25 上午11:58:57
     * @author xiangyutian
     */
    private static <T> void runUiThread(final IResultReceiver resultReceiver, final int statuscode, final T resultObj) {
        mHandler.post(new Runnable() {

            @Override
            public void run() {
                mEndTime = System.currentTimeMillis();

                LogUtils.d(TAG, "网络请求耗时:   " + (mEndTime - mStartTime));
                // 回调输出
                resultReceiver.onReceiveResult(statuscode, resultObj);
            }
        });
    }

    /**
     * 切换至UI线程
     *
     * @param resultReceiver
     * @param statuscode
     * @param resultObj      create at 2014-3-25 上午11:58:57
     * @author xiangyutian
     */
    private static <T> void runListUiThread(final IResultReceiver resultReceiver, final int statuscode,
                                            final ArrayList<T> resultObj) {
        mHandler.post(new Runnable() {

            @Override
            public void run() {
                // 回调输出
                resultReceiver.onReceiveResult(statuscode, resultObj);
            }
        });
    }

    /**
     * 根据TAG,关闭对应页面http任务
     *
     * @param tag 请求标识 create at 2014-3-27 下午9:05:28
     * @author xiangyutian
     */
    public static void cancelAllRequests(Object tag) {
        AsyncRequestCenter.getInstance().cancelAll(tag);
    }

}
