/*
 * ServerErrorCode.java
 * classes : com.sh.shvideo.task.ServerErrorCode
 * @author xiangyutian
 * V 4.5.0
 * Create at 2014-3-20 下午6:48:30
 */
package com.exchange.client.http;


import com.exchange.client.http.center.AsyncRequestCenter;

/**
 * 错误码集合
 * 
 * @author xiangyutian <br/>
 *         create at 2014-3-20 下午6:48:30
 */
public class ServerErrorCode implements AsyncRequestCenter.ErrorCode {
    public static final String TAG = "ServerErrorCode";

    /**
     * 返回成功
     */
    public static final int STATUS_SUCCESS = 0;

    /**
     * 返回失败
     */
    public static final int STATUS_EMPTY = 1;
}
