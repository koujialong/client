package com.exchange.client.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.exchange.client.R;
import com.exchange.client.model.BDJModel;

import java.util.List;

/**
 * Created by qilin on 2016/3/13.
 */
public class OneAdapter extends BaseAdapter {
    private List<BDJModel.ShowapiResBodyEntity.PagebeanEntity.ContentlistEntity> contentlist;
    private Context context;

    public OneAdapter(List<BDJModel.ShowapiResBodyEntity.PagebeanEntity.ContentlistEntity> contentlist, Context context) {
        this.contentlist = contentlist;
        this.context = context;
    }

    @Override
    public int getCount() {
        return contentlist.size();
    }

    @Override
    public Object getItem(int position) {
        return contentlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=LayoutInflater.from(context).inflate(R.layout.listitem_one,null);
        TextView text= (TextView) view.findViewById(R.id.dz_text);
        text.setText(contentlist.get(position).getText());
        return view;
    }
}
