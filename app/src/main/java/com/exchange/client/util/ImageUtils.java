package com.exchange.client.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

///**
// * ImageUtils
// * <ul>
// * convert between Bitmap, byte array, Drawable
// * <li>{@link #bitmapToByte(Bitmap)}</li>
// * <li>{@link #bitmapToDrawable(Bitmap)}</li>
// * <li>{@link #byteToBitmap(byte[])}</li>
// * <li>{@link #byteToDrawable(byte[])}</li>
// * <li>{@link #drawableToBitmap(Drawable)}</li>
// * <li>{@link #drawableToByte(Drawable)}</li>
// * </ul>
// * <ul>
// * get image
// * <li>{@link #getInputStreamFromUrl(String, int)}</li>
// * <li>{@link #getBitmapFromUrl(String, int)}</li>
// * <li>{@link #getDrawableFromUrl(String, int)}</li>
// * </ul>
// * <ul>
// * scale image
// * <li>{@link #scaleImageTo(Bitmap, int, int)}</li>
// * <li>{@link #scaleImage(Bitmap, float, float)}</li>
// * </ul>
// * 
// * @author boyang116245@sohu-inc.com
// * @since 2013-11-18
// */
public class ImageUtils {

    private static final String TAG = "ImageUtils";

    /**
     * convert Bitmap to byte array
     * 
     * @param b
     * @return
     */
    public static byte[] bitmapToByte(Bitmap b) {
        if (b == null) {
            return null;
        }

        ByteArrayOutputStream o = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, o);
        return o.toByteArray();
    }

    /**
     * convert byte array to Bitmap
     * 
     * @param b
     * @return
     */
    public static Bitmap byteToBitmap(byte[] b) {
        return (b == null || b.length == 0) ? null : BitmapFactory.decodeByteArray(b, 0, b.length);
    }

    /**
     * convert Drawable to Bitmap
     * 
     * @param d
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable d) {
        return d == null ? null : ((BitmapDrawable) d).getBitmap();
    }

    /**
     * convert Bitmap to Drawable
     * 
     * @param b
     * @return
     */
    public static Drawable bitmapToDrawable(Bitmap b) {
        return b == null ? null : new BitmapDrawable(b);
    }

    /**
     * convert Drawable to byte array
     * 
     * @param d
     * @return
     */
    public static byte[] drawableToByte(Drawable d) {
        return bitmapToByte(drawableToBitmap(d));
    }

    /**
     * convert byte array to Drawable
     * 
     * @param b
     * @return
     */
    public static Drawable byteToDrawable(byte[] b) {
        return bitmapToDrawable(byteToBitmap(b));
    }

    /**
     * get input stream from network by imageurl, you need to close
     * inputStream
     * yourself
     * 
     * @param imageUrl
     * @param readTimeOutMillis
     *            read time out, if less than 0, not set, in mills
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public static InputStream getInputStreamFromUrl(String imageUrl, int readTimeOutMillis) {
        InputStream stream = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            if (readTimeOutMillis > 0) {
                con.setReadTimeout(readTimeOutMillis);
            }
            stream = con.getInputStream();
        } catch (MalformedURLException e) {
            closeInputStream(stream);
            throw new RuntimeException("MalformedURLException occurred. ", e);
        } catch (IOException e) {
            closeInputStream(stream);
            throw new RuntimeException("IOException occurred. ", e);
        }
        return stream;
    }

    /**
     * get drawable by imageUrl
     * 
     * @param imageUrl
     * @param readTimeOutMillis
     *            read time out, if less than 0, not set, in mills
     * @return
     */
    public static Drawable getDrawableFromUrl(String imageUrl, int readTimeOutMillis) {
        InputStream stream = getInputStreamFromUrl(imageUrl, readTimeOutMillis);
        Drawable d = Drawable.createFromStream(stream, "src");
        closeInputStream(stream);
        return d;
    }

    /**
     * get Bitmap by imageUrl
     * 
     * @param imageUrl
     * @return
     */
    public static Bitmap getBitmapFromUrl(String imageUrl, int readTimeOut) {
        InputStream stream = getInputStreamFromUrl(imageUrl, readTimeOut);
        Bitmap b = BitmapFactory.decodeStream(stream);
        closeInputStream(stream);
        return b;
    }

    /**
     * scale image
     * 
     * @param org
     * @param newWidth
     * @param newHeight
     * @return
     */
    public static Bitmap scaleImageTo(Bitmap org, int newWidth, int newHeight) {
        return scaleImage(org, (float) newWidth / org.getWidth(), (float) newHeight / org.getHeight());
    }

    /**
     * scale image
     * 
     * @param org
     * @param scaleWidth
     *            sacle of width
     * @param scaleHeight
     *            scale of height
     * @return
     */
    public static Bitmap scaleImage(Bitmap org, float scaleWidth, float scaleHeight) {
        if (org == null) {
            return null;
        }

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(org, 0, 0, org.getWidth(), org.getHeight(), matrix, true);
    }

    /**
     * close inputStream
     * 
     * @param s
     */
    private static void closeInputStream(InputStream s) {
        if (s == null) {
            return;
        }

        try {
            s.close();
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        }
    }

    public static Bitmap getBitmapFromRes(Context context, int resId) {
        Bitmap bm = null;
        try {
            BitmapFactory.Options opt = new BitmapFactory.Options();
            opt.inPreferredConfig = Config.RGB_565;
            bm = BitmapFactory.decodeResource(context.getResources(), resId, opt);
        } catch (OutOfMemoryError e) {
            LogUtils.e(TAG, "", e);
        }
        return bm;
    }

    /**
     * 等比缩放图片
     * 
     * @param srcBitmap
     * @param desWidth
     * @param desHeight
     * @return
     */
    public static Bitmap resizeBitmapSmooth(Bitmap srcBitmap, int desWidth, int desHeight) {
        try {
            if (srcBitmap == null || desWidth < 0 || desHeight < 0) {
                return null;
            }
            Bitmap dstBitmap = Bitmap.createScaledBitmap(srcBitmap, desWidth, desHeight, true);
            return dstBitmap;
        } catch (OutOfMemoryError e) {
            return null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static Bitmap getBitmapFromFile(File file) {
        return getBitmapFromFile(file, Config.RGB_565);
    }

    public static Bitmap getBitmapFromFile(File file, Config config) {
        if (file == null || !file.exists()) {
            return null;
        }
        Bitmap bitmap = null;

        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 1;
            options.inJustDecodeBounds = false;
            options.inDither = false;
            options.inPreferredConfig = config;
            bitmap = BitmapFactory.decodeFile(file.getPath(), options);
        } catch (OutOfMemoryError e) {
            LogUtils.e(TAG, "getBitmapFromFile OutOfMemoryError :", e);
        } catch (IllegalArgumentException e) {
            LogUtils.e(TAG, "getBitmapFromFile IllegalArgumentException :", e);
        }

        return bitmap;
    }

    public static Bitmap getBitmapFromByteArray(byte[] response) {
        Bitmap bitmap = null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 1;
            options.inJustDecodeBounds = false;
            options.inDither = false;
            options.inPreferredConfig = Config.RGB_565;
            bitmap = BitmapFactory.decodeByteArray(response, 0, response.length, options);
        } catch (OutOfMemoryError e) {
            LogUtils.e(TAG, "getBitmapFromByteArray OutOfMemoryError :", e);
            return null;
        } catch (IllegalArgumentException e) {
            LogUtils.e(TAG, "getBitmapFromByteArray IllegalArgumentException :", e);
            return null;
        }
        return bitmap;
    }

    public static Bitmap getBitmapFromByteArray(byte[] response, int maxLength) {
        Bitmap bitmap = null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 1;
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(response, 0, response.length, options);

            options.inSampleSize = computeSampleSize(options, maxLength);
            options.inJustDecodeBounds = false;
            options.inDither = false;
            options.inPreferredConfig = Config.RGB_565;
            bitmap = BitmapFactory.decodeByteArray(response, 0, response.length, options);
        } catch (OutOfMemoryError e) {
            LogUtils.e(TAG, "getBitmapFromByteArray OutOfMemoryError :", e);
            return null;
        } catch (IllegalArgumentException e) {
            LogUtils.e(TAG, "getBitmapFromByteArray IllegalArgumentException :", e);
            return null;
        }
        return bitmap;
    }

    /**
     * 计算缩放比例
     */
    private static int computeSampleSize(BitmapFactory.Options options, int target) {
        int w = options.outWidth;
        int h = options.outHeight;

        int length = Math.max(w, h);
        int candidate = length / target;

        if (candidate == 0)
            return 1;

        if (candidate > 1) {
            if ((length > target) && (length / candidate) < target)
                candidate -= 1;
        }

        return candidate;
    }

    /**
     * 获取中间的方形图片，超出时截取两边
     * 
     * @param srcBitmap
     * @param
     * @return
     * @throws OutOfMemoryError
     */
    public static Bitmap getCenterInBitmap(Bitmap srcBitmap, int dstWidth, int dstHeight) {
        Bitmap finalBm = null;
        try {

            if (srcBitmap == null || dstWidth <= 0 || dstHeight <= 0) {
                return null;
            }

            int srcWidth = srcBitmap.getWidth();
            int srcHeight = srcBitmap.getHeight();

            // 对于没有变化的图片不需要裁切缩放
            if (srcWidth == dstWidth && srcHeight == dstHeight) {
                return srcBitmap;
            }

            // 先判断比例相等的
            if (dstWidth * srcHeight == dstHeight * srcWidth) {
                if (dstWidth < srcWidth) {
                    // 仅往小尺寸缩放
                    Bitmap bm = Bitmap.createScaledBitmap(srcBitmap, dstWidth, dstHeight, true);
                    return bm;
                } else {
                    return srcBitmap;
                }
            }

            int tmpWidth = 0;
            int tmpHeight = 0;

            if (dstWidth * srcHeight > dstHeight * srcWidth) {
                // 截取上下两部分
                if (dstWidth < srcWidth) {
                    // 先对原图进行等比缩放
                    tmpWidth = dstWidth;
                    tmpHeight = tmpWidth * dstHeight / dstWidth;
                }
            } else {
                // 截取左右两部分
                if (dstHeight < srcHeight) {
                    // 先对原图进行等比缩放
                    tmpHeight = dstHeight;
                    tmpWidth = dstWidth * tmpHeight / dstHeight;
                }
            }

            Bitmap scaleBitmap = null;
            if (tmpWidth != 0 && tmpHeight != 0) {
                scaleBitmap = Bitmap.createScaledBitmap(srcBitmap, tmpWidth, tmpHeight, true);
            } else {
                scaleBitmap = srcBitmap;
            }

            if (scaleBitmap == null) {
                return null;
            }

            int x = 0;
            int y = 0;

            int sw = scaleBitmap.getWidth();
            int sh = scaleBitmap.getHeight();
            int imgWidth = 0;
            int imgHeight = 0;
            if (sw * dstHeight < sh * dstWidth) {
                imgWidth = sw;
                imgHeight = sw * dstHeight / dstWidth;
                // 切除上下部分
                x = 0;
                y = (sh - imgHeight) / 2;
            } else {
                imgHeight = sh;
                imgWidth = sh * dstWidth / dstHeight;
                y = 0;
                x = (sw - imgWidth) / 2;
            }

            if (x == 0 && y == 0) {
                // 没有缩放的图片
                return scaleBitmap;
            }

            finalBm = Bitmap.createBitmap(scaleBitmap, x, y, imgWidth, imgHeight);
        } catch (OutOfMemoryError e) {
            LogUtils.e(TAG, "getCenterInBitmap OutOfMemoryError :", e);
        }
        return finalBm;
    }

    public static Bitmap getRoundCornerBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float roundPx;
        float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
        if (width <= height) {
            roundPx = width / 2;
            top = 0;
            bottom = width;
            left = 0;
            right = width;
            height = width;
            dst_left = 0;
            dst_top = 0;
            dst_right = width;
            dst_bottom = width;
        } else {
            roundPx = height / 2;
            float clip = (width - height) / 2;
            left = clip;
            right = width - clip;
            top = 0;
            bottom = height;
            width = height;
            dst_left = 0;
            dst_top = 0;
            dst_right = height;
            dst_bottom = height;
        }
        Bitmap output = null;

        try {
            output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
            Canvas canvas = new Canvas(output);
            final int color = 0xff424242;
            final Paint paint = new Paint();
            final Rect src = new Rect((int) left, (int) top, (int) right, (int) bottom);
            final Rect dst = new Rect((int) dst_left, (int) dst_top, (int) dst_right, (int) dst_bottom);
            final RectF rectF = new RectF(dst);
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
            canvas.drawBitmap(bitmap, src, dst, paint);
        } catch (OutOfMemoryError e) {
            LogUtils.e(e);
            System.gc();
            return null;
        } catch (Throwable t) {
            LogUtils.e(t);
            System.gc();
            return null;
        }
        return output;

    }

    /**
     * 用来压缩图片的方法
     * @param bitmap  传进一张图片进来
     * @param howlod  传进图片被压缩的大小
     * @return  返回一个已经压缩的图片
     */
    public static Bitmap getBitmap2M(Bitmap bitmap, int howlod) {
        //定义一个内存缓冲流
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        int count = 100;
        //用来压缩图片
        bitmap.compress(Bitmap.CompressFormat.JPEG, count, stream);
        //通过循环，来压缩图片，当图片压缩到一定大小的时候，接受
        while (stream.toByteArray().length > (howlod * 1024 * 1024)) {
            stream.reset();
            count *= (90 / 100);
            bitmap.compress(Bitmap.CompressFormat.JPEG, count, stream);
            //判断图片压缩的大小小于6的时候，停止压缩
            if (count < 6) {
                break;
            }
        }
        //通过bmobfactory
        bitmap = BitmapFactory.decodeByteArray(stream.toByteArray(), 0,
                stream.toByteArray().length);

        return bitmap;
    }


//    /**
//     * 将Bitmap转换成字符串
//     * @param bitmap
//     * @return
//     */
//    public static String bitmaptoString(Bitmap bitmap){
//        String string= null ;
//        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG,100,bStream);
//        byte[] bytes = bStream.toByteArray();
//        string = com.loopj.android.http.Base64.encodeToString(bytes, com.loopj.android.http.Base64.DEFAULT);
//        return string;
//    }




    /**
     * 获取数据
     * @param inputStream
     * @return
     * @throws IOException
     */
    private static byte[] readInputStream(InputStream inputStream)throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

}
