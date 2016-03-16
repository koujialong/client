package com.exchange.client.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.exchange.client.R;

/**
 * Created by qilin on 2015/12/12.
 */
public class TradeListAdapter extends BaseAdapter{
    private Context context;
    public TradeListAdapter(Context context) {
        super();
        this.context=context;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return LayoutInflater.from(context).inflate(R.layout.item_goods,null);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_goods,null);
    }
}
