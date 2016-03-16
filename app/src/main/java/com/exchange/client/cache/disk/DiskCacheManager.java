//package com.exchange.client.cache.disk;
//
//import android.content.Context;
//import android.os.Environment;
//
//import com.diskcachelib.client.DiskLruCacheHelper;
//import com.exchange.client.util.LogUtils;
//
//import java.io.File;
//import java.io.IOException;
//
///**
// * disk存储管理器
// * Created by xiangyutian on 15/11/20.
// * Email: xiangyutian116072@sohu-inc.com
// */
//public class DiskCacheManager extends DiskLruCacheHelper {
//    private static final String TAG = DiskCacheManager.class.getSimpleName();
//
//    /**
//     * param
//     */
//    private static String DISKCACHE_FOLDER = "diskCache";// 磁盘缓存数据文件夹
//    private static String mDataDiskCacheDir = null;// 磁盘缓存完整路径
//    private static volatile DiskCacheManager instance = null;
//
//    private DiskCacheManager(Context context, String dirName) throws IOException {
//        super(context, dirName);
//    }
//
//    public static DiskCacheManager getInstance(Context context) {
//        if (instance == null) {
//            synchronized (DiskCacheManager.class) {
//                try {
//                    setDatabaseDir(context, DISKCACHE_FOLDER);
//                    instance = new DiskCacheManager(context, mDataDiskCacheDir);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    LogUtils.e(TAG, "DiskCacheManager初始化異常!!!", e);
//                }
//            }
//        }
//
//        return instance;
//    }
//
//    /**
//     * 设置数据库路径
//     *
//     * @author xiangyutian
//     * @param context
//     * @param uniqueName
//     *            create at 2014-7-8 上午11:26:07
//     */
//    private static void setDatabaseDir(Context context, String uniqueName) {
//        mDataDiskCacheDir = getDiskCachePath(context, uniqueName);
//        File dir = new File(mDataDiskCacheDir);
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//    }
//
//    private static String getDiskCachePath(Context context, String uniqueName) {
//        String cachePath;
//        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
//                || !Environment.isExternalStorageRemovable()) {
//            cachePath = context.getExternalCacheDir().getPath();
//        } else {
//            cachePath = context.getCacheDir().getPath();
//        }
//        return (cachePath + File.separator + uniqueName);
//    }
//}
