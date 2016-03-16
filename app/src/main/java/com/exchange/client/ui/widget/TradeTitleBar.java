package com.exchange.client.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.exchange.client.R;

/**
 * 标题栏
 */
public class TradeTitleBar extends RelativeLayout {

    private Context mContext;
    private TextView title;
    private TextView leftTitle;
    private TextView rightTitle1;
    private TextView rightTitle2;

    public TradeTitleBar(Context context) {
        super(context);

        init(context);
    }

    public TradeTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public TradeTitleBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
    }

    /**
     * 初始化标题栏，适用于标题在中间的情况
     *
     * @param leftTitleResId  左边按钮资源id,没有写0
     * @param rightTitleOneResId 右边按钮资源id,没有写0
     * @param rightTitleTwoResId 右边按钮资源id,没有写0
     */
    public void setTitleInfo(int leftTitleResId, int rightTitleOneResId,int rightTitleTwoResId, OnClickListener listener1,
                             OnClickListener listener2,OnClickListener listener3) {
        LayoutInflater.from(mContext).inflate(R.layout.vw_titlebar_leftright_three_title, this, true);
        leftTitle = (TextView) findViewById(R.id.titlebar_lefttxt);
        rightTitle1 = (TextView) findViewById(R.id.titlebar_rightonetxt);
        rightTitle2 = (TextView) findViewById(R.id.titlebar_righttowtxt);
        setTitle(leftTitle, leftTitleResId, listener1);
        setTitle(rightTitle1, rightTitleOneResId, listener2);
        setTitle(rightTitle2, rightTitleTwoResId, listener3);
    }

    private void setTitle(TextView button, int resId, OnClickListener listener) {
        if (resId != 0) {
            button.setText(resId);
            button.setVisibility(View.VISIBLE);
            if (listener != null) {
                button.setOnClickListener(listener);
            }
        }
    }
}
