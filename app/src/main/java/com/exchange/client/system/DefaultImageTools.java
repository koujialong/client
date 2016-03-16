package com.exchange.client.system;

import android.content.Context;
import android.graphics.Bitmap;

import com.exchange.client.R;
import com.exchange.client.util.ImageUtils;


public class DefaultImageTools {
    private static Bitmap mNoticeErrorBitmap;
    private static Bitmap mNoticeEmptyBitmap;

    public static Bitmap getNoticeErrorBitmap(Context context) {
        if (mNoticeErrorBitmap == null) {
            mNoticeErrorBitmap = ImageUtils.getBitmapFromRes(context, R.mipmap.icon_no_network);
        }
        return mNoticeErrorBitmap;
    }

    public static Bitmap getNoticeEmptyBitmap(Context context) {
        if (mNoticeEmptyBitmap == null) {
            mNoticeEmptyBitmap = ImageUtils.getBitmapFromRes(context, R.mipmap.icon_empty);
        }
        return mNoticeEmptyBitmap;
    }

}
