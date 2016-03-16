/*
 * JsonParserFactory.java
 * @author xiangyutian
 * V 4.5.0
 * Create at 2014-3-21 下午6:14:59
 */
package com.exchange.client.http.parser;

import com.exchange.client.http.ServerErrorCode;
import com.exchange.client.http.exception.HttpClientApiException;
import com.exchange.client.model.AbstractBaseModel;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;

import java.io.IOException;

/**
 * 数据解析器工厂
 * 
 * @author xiangyutian <br/>
 *         create at 2014-3-21 下午6:14:59
 */
public class JsonParserFactory {
    /**
     * TAG
     */
    private static final String TAG = "JsonParserFactory";

    public static <T extends AbstractBaseModel> T  parseStringJson(Class<T> cls, Object context) throws JSONException,
            HttpClientApiException, IOException {
        final T response;
        try {
            response = new Gson().fromJson((String) context, cls);
        } catch (JsonSyntaxException e) {
            throw new JSONException(e.getMessage());
        } catch (JsonIOException e) {
            throw new IOException(e.getMessage());
        } catch (JsonParseException e) {
            throw new JSONException(e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new JSONException(e.getMessage());
        }

        if (response == null) {
            throw new JSONException(TAG + " JsonParser is null.");
        }

        if (response.getShowapi_res_code() != ServerErrorCode.STATUS_SUCCESS) {
            throw new HttpClientApiException(response.getShowapi_res_code(), response.getShowapi_res_error());
        }

        return response;
    }

    /**
     * 解析基本数据类型
     * 
     * @param cls
     * @return
     */
    public static <T extends AbstractBaseModel> IResultDataParser<T> parseBaseModel(final Class<T> cls) {
        return new IResultDataParser<T>() {
            @Override
            public T parse(Object response) throws JSONException, HttpClientApiException, IOException {
                return parseStringJson(cls, (String) response);
            }
        };
    }

}
