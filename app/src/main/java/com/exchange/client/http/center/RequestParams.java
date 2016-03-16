package com.exchange.client.http.center;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import android.text.TextUtils;

public class RequestParams {
    private static String ENCODING = "UTF-8";

    private String url;
    private int method;

    private ConcurrentHashMap<String, String> queryParams;
    private ConcurrentHashMap<String, String> headerParams;
    private ConcurrentHashMap<String, String> entityStringParams;

    private Object tag;

    public RequestParams(String url, int method) {
        this.url = url;
        this.method = method;
    }

    public int getMethod() {
        return method;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public ConcurrentHashMap<String, String> getHeaderParams() {
        return headerParams;
    }

    public ConcurrentHashMap<String, String> getEntityStringParams() {
        return entityStringParams;
    }

    public void addQueryParam(String key, long value) {
        String stringValue = String.valueOf(value);
        addQueryParam(key, stringValue);
    }

    public void addQueryParam(String key, float value) {
        String stringValue = String.valueOf(value);
        addQueryParam(key, stringValue);
    }

    public void addQueryParam(String key, double value) {
        String stringValue = String.valueOf(value);
        addQueryParam(key, stringValue);
    }

    public void addQueryParam(String key, int value) {
        String stringValue = String.valueOf(value);
        addQueryParam(key, stringValue);
    }

    public void addQueryParam(String key, boolean value) {
        String stringValue = String.valueOf(value);
        addQueryParam(key, stringValue);
    }

    public void addQueryParam(String key, String value) {
        if (queryParams == null) {
            queryParams = new ConcurrentHashMap<String, String>();
        }

        queryParams.put(key, value);
    }

    public void clearQueryParams() {
        if (queryParams == null) {
            return;
        }

        queryParams.clear();
        queryParams = null;
    }

    public void addHeaderParam(String key, long value) {
        String stringValue = String.valueOf(value);
        addHeaderParam(key, stringValue);
    }

    public void addHeaderParam(String key, float value) {
        String stringValue = String.valueOf(value);
        addHeaderParam(key, stringValue);
    }

    public void addHeaderParam(String key, double value) {
        String stringValue = String.valueOf(value);
        addHeaderParam(key, stringValue);
    }

    public void addHeaderParam(String key, int value) {
        String stringValue = String.valueOf(value);
        addHeaderParam(key, stringValue);
    }

    public void addHeaderParam(String key, boolean value) {
        String stringValue = String.valueOf(value);
        addHeaderParam(key, stringValue);
    }

    public void addHeaderParam(String key, String value) {
        if (headerParams == null) {
            headerParams = new ConcurrentHashMap<String, String>();
        }

        headerParams.put(key, value);
    }

    public void clearHeaderParams() {
        if (headerParams == null) {
            return;
        }

        headerParams.clear();
        headerParams = null;
    }

    public void addEntityStringParam(String key, String value) {
        if (entityStringParams == null) {
            entityStringParams = new ConcurrentHashMap<String, String>();
        }

        entityStringParams.put(key, TextUtils.isEmpty(value) ? "" : value);
    }

    private List<BasicNameValuePair> getQueryParamsList() {
        List<BasicNameValuePair> lparams = new LinkedList<BasicNameValuePair>();

        for (ConcurrentHashMap.Entry<String, String> entry : queryParams.entrySet()) {
            lparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return lparams;
    }

    public String getUrlWithQueryString() {
        String tmpUrl = this.url;
        if (queryParams != null) {
            String paramString = URLEncodedUtils.format(getQueryParamsList(), ENCODING);
            if (tmpUrl.indexOf("?") == -1) {
                tmpUrl += "?" + paramString;
            } else {
                if (tmpUrl.endsWith("&")) {
                    tmpUrl += paramString;
                } else {
                    tmpUrl += "&" + paramString;
                }
            }
        }

        return tmpUrl;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("<url>:" + url);
        result.append(";<method>:" + method);

        if (queryParams != null && queryParams.size() != 0) {
            result.append(";<queryParams>:");
            for (ConcurrentHashMap.Entry<String, String> entry : queryParams.entrySet()) {
                result.append(entry.getKey());
                result.append("=");
                result.append(entry.getValue());
                result.append(";");
            }
        }

        if (headerParams != null && headerParams.size() != 0) {
            result.append("<headerParams>:");
            for (ConcurrentHashMap.Entry<String, String> entry : headerParams.entrySet()) {
                result.append(entry.getKey());
                result.append("=");
                result.append(entry.getValue());
                result.append(";");
            }
        }

        if (entityStringParams != null && entityStringParams.size() != 0) {
            result.append("<entityStringParams>:");
            for (ConcurrentHashMap.Entry<String, String> entry : entityStringParams.entrySet()) {
                result.append(entry.getKey());
                result.append("=");
                result.append(entry.getValue());
                result.append(";");
            }
        }

        return result.toString();
    }

    protected String toShortString() {
        String res = "<url>:" + url;
        return res;
    }
}
