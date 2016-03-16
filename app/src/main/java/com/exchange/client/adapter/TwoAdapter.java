package com.exchange.client.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.exchange.client.R;
import com.exchange.client.model.BDJModel;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by qilin on 2016/3/13.
 */
public class TwoAdapter extends BaseAdapter {
    private List<BDJModel.ShowapiResBodyEntity.PagebeanEntity.ContentlistEntity> contentlist;
    private Context context;

    public TwoAdapter(List<BDJModel.ShowapiResBodyEntity.PagebeanEntity.ContentlistEntity> contentlist, Context context) {
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
        View view=LayoutInflater.from(context).inflate(R.layout.listitem_two,null);
        ImageView imageView= (ImageView) view.findViewById(R.id.item_image);
        Picasso.with(context).load(contentlist.get(position).getImage0()).into(imageView);
        return view;
    }
}
