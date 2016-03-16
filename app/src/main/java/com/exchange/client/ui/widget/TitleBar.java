package com.exchange.client.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.exchange.client.R;

/**
 * 标题栏
 */
public class TitleBar extends RelativeLayout {

    private Context mContext;
    private TextView title;
    private TextView leftTitle;
    private TextView rightTitle;

    public TitleBar(Context context) {
        super(context);

        init(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyle) {
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
     * @param rightTitleResId 右边按钮资源id,没有写0
     */
    public void setTitleInfo(int leftTitleResId, int rightTitleResId, OnClickListener listener1,
                             OnClickListener listener2) {
        LayoutInflater.from(mContext).inflate(R.layout.vw_titlebar_leftright_both_title, this, true);
        leftTitle = (TextView) findViewById(R.id.titlebar_lefttxt);
        rightTitle = (TextView) findViewById(R.id.titlebar_righttxt);

        setTitle(leftTitle, leftTitleResId, listener1);
        setTitle(rightTitle, rightTitleResId, listener2);
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
