package com.exchange.client.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.exchange.client.R;
import com.exchange.client.model.CardTemplateModel;
import com.exchange.client.module.trade.AssetCardFragment;
import com.exchange.client.module.trade.TradeBillFragment;
import com.exchange.client.util.ListUtils;
import com.exchange.client.util.LogUtils;
import com.exchange.client.util.ViewUtils;

import java.util.ArrayList;

/**
 * 首页列表适配器
 * Created by xiangyutian on 15/12/7.
 * Email: xiangyutian116072@sohu-inc.com
 */
public class TradePageAdapter extends BaseAdapter {

    /**
     * tag
     */
    private static final String TAG = TradePageAdapter.class.getSimpleName();

    /**
     * params
     */
    private LayoutInflater mLayoutInflater;
    private static final int CARD_ASSET = 0x00;
    private static final int CARD_BILL = 0x01;
    public static final int[] CARD_LIST = {CARD_ASSET, CARD_BILL};
    private static final int CARD_COUNT = CARD_LIST.length;
    private FragmentActivity mContext;
    private ArrayList<CardTemplateModel> mCardTemplateModels = new ArrayList<>();

    public TradePageAdapter(Context context, ArrayList<CardTemplateModel> list) {
        mContext = (FragmentActivity) context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mCardTemplateModels.clear();
        mCardTemplateModels.addAll(list);
    }

    @Override
    public int getItemViewType(int position) {
        return mCardTemplateModels.get(position).getTemplateId();
    }

    @Override
    public int getViewTypeCount() {
        return CARD_COUNT;
    }

    @Override
    public int getCount() {
        if (!ListUtils.isEmpty(mCardTemplateModels)) {
            return mCardTemplateModels.size();
        }

        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (!ListUtils.isEmpty(mCardTemplateModels)) {
            return mCardTemplateModels.get(position);
        }

        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case CARD_ASSET: {
                return getAssetView(position, convertView, parent);
            }
            case CARD_BILL: {
                return getBillView(position, convertView, parent);
            }
            default: {
                return getBillView(position, convertView, parent);
            }
        }
    }

    private View getAssetView(int position, View convertView, ViewGroup parent) {
        AssetViewHolder holder;

        Object obj = getItem(position);
        if (obj == null) {
            ViewUtils.setVisibility(convertView, View.GONE);
            return convertView;
        }

        if (convertView == null) {
            holder = new AssetViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.listitem_card_asset, null);
            if (!((FragmentActivity) mContext).isFinishing()) {
                FragmentTransaction transaction;
                transaction = mContext.getSupportFragmentManager().beginTransaction();
                AssetCardFragment mAssetCardFragment = (AssetCardFragment) Fragment.instantiate(
                        mContext, AssetCardFragment.class.getName());
                transaction.replace(R.id.card_asset, mAssetCardFragment);
                try {
                    transaction.commitAllowingStateLoss();
                } catch (Exception e) {
                    LogUtils.e(TAG, "获取交易页资产cardview异常", e);
                }
            }
            convertView.setTag(holder);
        } else {
            holder = (AssetViewHolder) convertView.getTag();
        }

        return convertView;

    }

    private View getBillView(int position, View convertView, ViewGroup parent) {
        BillViewHolder holder;

        Object obj = getItem(position);
        if (obj == null) {
            ViewUtils.setVisibility(convertView, View.GONE);
            return convertView;
        }

        if (convertView == null) {
            holder = new BillViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.listitem_card_bill, null);
            if (!((FragmentActivity) mContext).isFinishing()) {
                FragmentTransaction transaction;
                transaction = mContext.getSupportFragmentManager().beginTransaction();
                TradeBillFragment mTradeBillFragment = (TradeBillFragment) Fragment.instantiate(
                        mContext, TradeBillFragment.class.getName());
                transaction.replace(R.id.card_bill, mTradeBillFragment);
                try {
                    transaction.commitAllowingStateLoss();
                } catch (Exception e) {
                    LogUtils.e(TAG, "获取交易页单据cardview异常", e);
                }
            }
            convertView.setTag(holder);
        } else {
            holder = (BillViewHolder) convertView.getTag();
        }

        return convertView;

    }



    public static class AssetViewHolder {
    }

    public static class BillViewHolder {
    }
}
