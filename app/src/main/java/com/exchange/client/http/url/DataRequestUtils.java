/*
 * DataRequestUtils.java
 * classes : com.ledu.ledubuyer.http.url.DataRequestUtils
 * @author xiangyutian
 * V 4.5.0
 * Create at 2014-5-28 下午9:04:31
 */
package com.exchange.client.http.url;

import com.android.volley.Request;
import com.exchange.client.http.center.RequestParams;
import com.exchange.client.util.StringUtils;
import com.exchange.client.util.TimeUtils;

/**
 * com.exchange.client.http.url.DataRequestUtils
 *
 * @author xiangyutian <br/>
 *         create at 2014-5-28 下午9:04:31
 */
public class DataRequestUtils {
    private static final String TAG = "DataRequestUtils";

    /**
     * domain
     */
    // Kline相关接口
    private static final String KLINE_DOMAIN = "/ctrade/quotation/getKLine.do";
    // 不得姐
    private static final String BDJ = "http://route.showapi.com/255-1";

    /**
     * params
     */
    private static final String FILE_SEPARATOR = "/";

    private static String combineRequestUrl(String domain) {
        String url =domain;
        return url;
    }

    public static String combineRequestUrl(String host, String method) {
        if (StringUtils.isNotBlank(host)) {
            if (host.endsWith(FILE_SEPARATOR)) {
                host = host.substring(0, host.length() - 1);
            }
        }
        if (StringUtils.isNotBlank(method)) {
            if (!method.startsWith(FILE_SEPARATOR)) {
                method = FILE_SEPARATOR + method;
            }
        }
        return host + method;
    }

    public static String combineRequestUrlWithParam(String method, long param) {
        String url = String.format(combineRequestUrl(method), param);
        return url;
    }

    public static String combineRequestUrlWithParam(String method, long param1, long param2) {
        String url = String.format(combineRequestUrl(method), param1, param2);
        return url;
    }

    private static void addBaseParams(RequestParams requestParams) {

    }

    /**
     * KLine接口
     *
     * @param tag
     * @param environmentCode
     * @param period
     * @return
     * @author xiangyutian
     */
    public static RequestParams getKLineRequestParam(String tag, String environmentCode, int commodityId, int period) {
        String url = combineRequestUrl(KLINE_DOMAIN);
        RequestParams requestParams = new RequestParams(url, Request.Method.GET);
        requestParams.setTag(tag);
        addBaseParams(requestParams);
        requestParams.addQueryParam("environmentCode", environmentCode);
        requestParams.addQueryParam("commodityId", commodityId);
        requestParams.addQueryParam("period", period);
        return requestParams;
    }

    /**
     * 不得姐
     *
     * @param tag
     * @return
     * @author xiangyutian
     */
    public static RequestParams getBDJParam(String tag, String type, String page) {
        String url = combineRequestUrl(BDJ);
        RequestParams requestParams = new RequestParams(url, Request.Method.GET);
        requestParams.setTag(tag);
        String time = TimeUtils.getTimeOfyyyyMMddHHmmss(TimeUtils.getCurrentTimeInLong());
        requestParams.addQueryParam("showapi_appid", "16662");
        requestParams.addQueryParam("showapi_sign", "f68757dff14a436490537cd13e30f694");
        requestParams.addQueryParam("showapi_timestamp", time);
        requestParams.addQueryParam("type", type);
        requestParams.addQueryParam("page", page);
        return requestParams;
    }


}
