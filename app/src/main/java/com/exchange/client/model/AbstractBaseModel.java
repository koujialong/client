package com.exchange.client.model;


/**
 * Created by xiangyutian on 15/7/8.
 *
 * @author:xiangyutian
 */

public abstract class AbstractBaseModel {

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {}
     */

    private int showapi_res_code;
    private String showapi_res_error;

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }
}
