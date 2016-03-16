package com.exchange.client.model;

/**
 * Created by xiangyutian on 15/12/9.
 * Email: xiangyutian116072@sohu-inc.com
 */
public class KLineDatasModel extends AbstractBaseModel {

    private KLineItemModel item;

    public KLineItemModel getItem() {
        return item;
    }

    public void setItem(KLineItemModel mItem) {
        item = mItem;
    }
}
