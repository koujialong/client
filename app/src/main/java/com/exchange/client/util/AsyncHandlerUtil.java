package com.exchange.client.util;

import android.content.AsyncQueryHandler;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

/**
 * Created by xiangyutian on 15/7/24.
 * <pre>
 *
 *      AsyncHandlerUtil asyncWorkHandler = new AsyncHandlerUtil(){
 *
 *          @Override protected void onCompleteWork() {
 *              Log.i("bb", "do call back methoid");
 *          }
 *      };
 *      asyncWorkHandler.doAsyncWork(321);
 *
 *  </pre>
 *
 * @author:xiangyutian
 */
public class AsyncHandlerUtil extends Handler {

    private static final String TAG = AsyncHandlerUtil.class.getSimpleName();

    private static Looper sLooper = null;
    private static final int EVENT_ARG_WORK = 1;
    private WorkerHandler mWorkerHanler;

    protected final class WorkerArgs {
        Handler handler;
    }

    public AsyncHandlerUtil() {
        synchronized (AsyncQueryHandler.class) {
            if (sLooper == null) {
                HandlerThread thread = new HandlerThread("AsyncHandlerUtil");
                thread.start();
                sLooper = thread.getLooper();
            }
        }
        mWorkerHanler = new WorkerHandler(sLooper);
    }

    protected class WorkerHandler extends Handler {
        public WorkerHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            WorkerArgs args = (WorkerArgs) msg.obj;
            int info = msg.arg1;
            LogUtils.d(TAG, "worker handler=-------------------" + info);

            Message result = args.handler.obtainMessage();
            result.arg1 = EVENT_ARG_WORK;
            result.sendToTarget();
        }
    }

    /**
     * 需要重写的回调函数
     */
    protected void onCompleteWorkCallBack() {
    }

    public void doAsyncWork(int taskId) {
        Message msg = mWorkerHanler.obtainMessage();

        WorkerArgs workArgs = new WorkerArgs();
        workArgs.handler = this;
        msg.obj = workArgs;
        msg.arg1 = taskId;
        mWorkerHanler.sendMessage(msg);
    }

    @Override
    public void handleMessage(Message msg) {
        LogUtils.i(TAG, "main handler ----------------" + msg.arg1);
        if (EVENT_ARG_WORK == msg.arg1) {
            onCompleteWorkCallBack();
        }
    }
}
