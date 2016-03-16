package com.exchange.client.module.homepage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.exchange.client.R;
import com.exchange.client.adapter.TwoAdapter;
import com.exchange.client.http.HttpTaskManager;
import com.exchange.client.http.ServerErrorCode;
import com.exchange.client.http.parser.IResultReceiver;
import com.exchange.client.http.parser.JsonParserFactory;
import com.exchange.client.http.url.DataRequestUtils;
import com.exchange.client.model.BDJModel;
import com.exchange.client.system.ExchangeApplication;
import com.exchange.client.util.LogUtils;
import com.exchange.client.util.ToastUtils;

import java.util.List;

/**
 * Created by qilin on 2016/3/15.
 */
public class TwoFragment extends Fragment {
    private static final String TAG = TwoFragment.class.getSimpleName();
    private View view;
    private ListView list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_one,null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list= (ListView) view.findViewById(R.id.list);
        loadData();
    }

    private void loadData() {
        HttpTaskManager.startStringRequest(
                DataRequestUtils.getBDJParam(TAG, "10", "200"),
                JsonParserFactory.parseBaseModel(BDJModel.class), new IResultReceiver() {

                    @Override
                    public void onReceiveResult(int resultCode, Object resultData) {
                        // TODO Auto-generated method stub
                        LogUtils.e("TAG", resultCode + "");
                        if (resultCode == ServerErrorCode.STATUS_SUCCESS) {
                            final BDJModel dataModel = (BDJModel) resultData;
                            if ((dataModel != null) && (dataModel.getShowapi_res_body() != null)) {
                                List<BDJModel.ShowapiResBodyEntity.PagebeanEntity.ContentlistEntity> contentlist = dataModel.getShowapi_res_body().getPagebean().getContentlist();
                                // 赋值
                                list.setAdapter(new TwoAdapter(contentlist,getActivity()));
                                ToastUtils.ToastShort(ExchangeApplication.getInstance().getApplicationContext(),
                                        "获取数据成功");
                            } else {
                                ToastUtils.ToastShort(ExchangeApplication.getInstance().getApplicationContext(),
                                        R.string.hint_network_error);
                            }
                        } else {
                            if (resultData == null) {
                                ToastUtils.ToastShort(ExchangeApplication.getInstance().getApplicationContext(),
                                        R.string.hint_network_error);
                            } else {
                                ToastUtils.ToastShort(ExchangeApplication.getInstance().getApplicationContext(),
                                        (String) resultData);
                            }
                        }

                        LogUtils.d(TAG, "resultCode::: " + resultCode + "resultData ::: " + resultData);
                    }
                });
    }
}
