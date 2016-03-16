package com.exchange.client.module.trade;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exchange.client.R;

/**
 * Created by qilin on 2015/12/12.
 */
public class TradeBillFragment extends Fragment{
    public static TradeBillFragment newInstance(){
       TradeBillFragment fragment=new TradeBillFragment();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bill, container, false);
    }
}
