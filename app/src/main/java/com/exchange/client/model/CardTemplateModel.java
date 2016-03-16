package com.exchange.client.model;

import java.util.ArrayList;

/**
 * 首页卡片模板
 * Created by xiangyutian on 15/12/9.
 * Email: xiangyutian116072@sohu-inc.com
 */
public class CardTemplateModel {

    private int templateId;
    private ArrayList<String> templateDate;

    public int getTemplateId() {
        return templateId;
    }

    public ArrayList<String> getTemplateDate() {
        return templateDate;
    }

    public void setTemplateId(int mTemplateId) {
        templateId = mTemplateId;
    }

    public void setTemplateDate(ArrayList<String> mTemplateDate) {
        templateDate = mTemplateDate;
    }
}
