package com.exchange.client.model;

import java.util.ArrayList;

/**
 * Created by xiangyutian on 15/12/9.
 * Email: xiangyutian116072@sohu-inc.com
 */
public class KLineItemModel {
    private ArrayList<ArrayList<Float>> datas;
    private float closingPrice;

    public float getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(float mClosingPrice) {
        closingPrice = mClosingPrice;
    }

    public ArrayList<ArrayList<Float>> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<ArrayList<Float>> mDatas) {
        datas = mDatas;
    }
}
