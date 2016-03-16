package com.exchange.client.http.center;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.exchange.client.http.OkHttpStack;
import com.exchange.client.util.LogUtils;
import com.squareup.okhttp.OkHttpClient;

import java.util.Collections;
import java.util.Map;

public class AsyncRequestCenter {
    private static final String TAG = "AsyncRequestCenter";

    private RequestQueue mRequestQueue = null;

    private static AsyncRequestCenter instance = null;

    public static synchronized AsyncRequestCenter getInstance() {
        if (instance == null) {
            instance = new AsyncRequestCenter();
        }
        return instance;
    }

    private AsyncRequestCenter() {
    }

    public synchronized void initiate(Context applicationContext) {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(applicationContext, new OkHttpStack(new OkHttpClient()));
//            mRequestQueue = Volley.newRequestQueue(applicationContext);
        }
    }

    public void cancelAll(Object tag) {
        mRequestQueue.cancelAll(tag);
    }

    public synchronized void stop() {
        mRequestQueue.stop();
        mRequestQueue = null;
    }

    public interface ErrorCode {
        int NETWORK_ERROR = 101;
        int NO_CONNECTION_ERROR = 102;
        int PARSE_ERROR = 103;
        int SERVER_ERROR = 104;
        int TIMEOUT_ERROR = 105;
    }

    public interface ErrorMsg {
        String NETWORK_ERROR = "网络数据错误";
        String NO_CONNECTION_ERROR = "网络连接异常";
        String PARSE_ERROR = "数据解析异常";
        String SERVER_ERROR = "网络数据异常";
        String TIMEOUT_ERROR = "数据超时异常";
    }

    public interface RequestListener<T> {
        /**
         * Called when a response is received.
         */
        void onSuccess(T result);

        /**
         * errorCode {@link ErrorCode}
         */
        void onFailure(int errorCode, String errorMsg);
    }

    public void startStringRequest(final RequestParams requestParams, final RequestListener<String> requestListener,
                                   boolean isNetAvailable) {

        Listener<String> listener = new Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (requestListener != null) {
                    requestListener.onSuccess(response);
                }
            }
        };

        ErrorListener errorListener = new ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if (requestListener != null) {
                    requestListener.onFailure(getErrorType(error), getErrorMsg(error));
                }
            }
        };

        LogUtils.d(TAG, "requestParams ======= "+requestParams.getUrlWithQueryString());
        StringRequest request = new StringRequest(requestParams.getMethod(), requestParams.getUrlWithQueryString(),
                listener, errorListener) {


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                if (requestParams.getHeaderParams() == null) {
                    return Collections.emptyMap();
                } else {
                    return requestParams.getHeaderParams();
                }
            }

            protected Map<String, String> getParams() throws AuthFailureError {
                return requestParams.getEntityStringParams();
            }
        };

        if (requestParams.getTag() != null) {
            request.setTag(requestParams.getTag());
        }

        mRequestQueue.add(request);
    }

    private int getErrorType(VolleyError error) {
        int type = ErrorCode.NETWORK_ERROR;

        if (error instanceof NoConnectionError) {
            type = ErrorCode.NO_CONNECTION_ERROR;
        } else if (error instanceof ParseError) {
            type = ErrorCode.PARSE_ERROR;
        } else if (error instanceof ServerError) {
            type = ErrorCode.SERVER_ERROR;
        } else if (error instanceof TimeoutError) {
            type = ErrorCode.TIMEOUT_ERROR;
        }

        return type;
    }

    private String getErrorMsg(VolleyError error) {
        String type = ErrorMsg.NETWORK_ERROR;

        if (error instanceof NoConnectionError) {
            type = ErrorMsg.NO_CONNECTION_ERROR;
        } else if (error instanceof ParseError) {
            type = ErrorMsg.PARSE_ERROR;
        } else if (error instanceof ServerError) {
            type = ErrorMsg.SERVER_ERROR;
        } else if (error instanceof TimeoutError) {
            type = ErrorMsg.TIMEOUT_ERROR;
        }

        return type;
    }

}
