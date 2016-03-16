/*
 * VideoSystemProvider.java
 * classes : com.sohu.sohuvideo.provider.VideoSystemProvider
 * @author xiangyutian
 * V 4.5.0
 * Create at 2014-3-22 下午5:36:10
 */
package com.exchange.client.cache.provider;

import java.util.ArrayList;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.exchange.client.util.LogUtils;

/**
 * 数据库对外的入口，封装了对provider的操作
 * 
 * @author xiangyutian <br/>
 *         create at 2014-3-22 下午5:36:10
 */
public class VideoSystemProvider extends ContentProvider {
    /**
     * TAG
     */
    private static final String TAG = "VideoSystemProvider";

    /**
     * database
     */
    private VideoSystemDatabase mOpenHelper;

    /**
     * mobile column list
     */
    private static final int MOBILE_COLUMN_LIST = 10001;

    /**
     * mobile column item list
     */
    private static final int MOBILE_COLUMN_ITEM_LIST = 10002;

    /**
     * uri匹配
     */
    private static final UriMatcher mUriMatcher = buildUriMatcher();

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = VideoSystemContract.CONTENT_AUTHORITY;

        // 添加整体标题列表uri
        matcher.addURI(authority, VideoSystemContract.PATH_MOBILE_COLUMNLIST, MOBILE_COLUMN_LIST);
        // 添加首页列表的uri
        matcher.addURI(authority, VideoSystemContract.PATH_MOBILE_COLUMN_ITEM, MOBILE_COLUMN_ITEM_LIST);

        return matcher;
    }

    public VideoSystemProvider() {
    }

    /**
     * @return
     * @see ContentProvider#onCreate()
     */
    @Override
    public boolean onCreate() {
        LogUtils.d(TAG, "初始化 VideoSystemProvider onCreate...");
        mOpenHelper = new VideoSystemDatabase(getContext());
        return true;
    }

    /**
     * @return
     * @see ContentProvider#getType(Uri)
     */
    @Override
    public String getType(Uri uri) {
        switch (mUriMatcher.match(uri)) {
//            case MOBILE_COLUMN_LIST:
//                return MobileColumnListTable.CONTENT_TYPE;
//            case MOBILE_COLUMN_ITEM_LIST:
//                return MobileColumnItemTable.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("getType action with Unknown table CONTENT_TYPE: " + uri);
        }
    }

    /**
     * 插入
     *
     * @param uri
     * @param values
     * @return
     * @see ContentProvider#insert(Uri,
     *      ContentValues)
     */
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        SQLiteDatabase db = mOpenHelper.getWritableDatabase();

        int conflictType = SQLiteDatabase.CONFLICT_REPLACE;
        switch (mUriMatcher.match(uri)) {
//            case MOBILE_COLUMN_LIST:
//                final long mobileColumnListId = db.insertWithOnConflict(MobileColumnListTable.TABLE_NAME, null, values,
//                        conflictType);
//                if (mobileColumnListId > 0l) {
//                    // 更新UI
//                    getContext().getContentResolver().notifyChange(uri, null, false);
//                }
//                return MobileColumnListTable.buildMobileColumnListUri(mobileColumnListId);
//            case MOBILE_COLUMN_ITEM_LIST:
//                final long mobileItemListId = db.insertWithOnConflict(MobileColumnItemTable.TABLE_NAME, null, values,
//                        conflictType);
//                if (mobileItemListId > 0l) {
//                    // 更新UI
//                    getContext().getContentResolver().notifyChange(uri, null, false);
//                }
//                return MobileColumnItemTable.buildMobileColumnListUri(mobileItemListId);
            default:
                LogUtils.d(TAG, uri.getPath());
                throw new UnsupportedOperationException("insert action with Unknown uri: " + uri);
        }
    }

    /**
     * 删除
     *
     * @param uri
     * @param selection
     * @param selectionArgs
     * @return
     * @see ContentProvider#delete(Uri,
     *      String, String[])
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();

        switch (mUriMatcher.match(uri)) {
//            case MOBILE_COLUMN_LIST: {
//                int result = db.delete(MobileColumnListTable.TABLE_NAME, selection, selectionArgs);
//                if (result > 0) {
//                    getContext().getContentResolver().notifyChange(uri, null, false);
//                }
//                LogUtils.d(TAG, "删除首页整体标题列表" + result + "条");
//                return result;
//            }
//            case MOBILE_COLUMN_ITEM_LIST: {
//                int result = db.delete(MobileColumnItemTable.TABLE_NAME, selection, selectionArgs);
//                if (result > 0) {
//                    getContext().getContentResolver().notifyChange(uri, null, false);
//                }
//                LogUtils.d(TAG, "删除首页item列表" + result + "条");
//                return result;
//            }
            default:
                final SelectionBuilder builder = buildSimpleSelection(uri);
                final int retVal = builder.where(selection, selectionArgs).delete(db);

                if (retVal > 0) {
                    getContext().getContentResolver().notifyChange(uri, null, false);
                }

                if (LogUtils.isDebug()) {
                    LogUtils.d(TAG, "delete(uri=" + uri + ") retVal：" + retVal);
                }

                return retVal;
        }
    }

    /**
     * 更新
     *
     * @param uri
     * @param values
     * @param selection
     * @param selectionArgs
     * @return
     * @see ContentProvider#update(Uri,
     *      ContentValues, String, String[])
     */
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();

        switch (mUriMatcher.match(uri)) {
//            case MOBILE_COLUMN_LIST: {
//                int result = db.update(MobileColumnListTable.TABLE_NAME, values, selection, selectionArgs);
//                if (result > 0) {
//                    getContext().getContentResolver().notifyChange(uri, null, false);
//                }
//                LogUtils.d(TAG, "首页整体标题列表" + result + "条");
//                return result;
//            }
//            case MOBILE_COLUMN_ITEM_LIST: {
//                int result = db.update(MobileColumnItemTable.TABLE_NAME, values, selection, selectionArgs);
//                if (result > 0) {
//                    getContext().getContentResolver().notifyChange(uri, null, false);
//                }
//                LogUtils.d(TAG, "更新首页item列表" + result + "条");
//                return result;
//            }
            default:
                final SelectionBuilder builder = buildSimpleSelection(uri);
                final int retVal = builder.where(selection, selectionArgs).update(db, values);
                if (retVal > 0) {
                    getContext().getContentResolver().notifyChange(uri, null, false);
                }

                if (LogUtils.isDebug()) {
                    LogUtils.d(TAG, "update(uri=" + uri + ") retVal：" + retVal);
                }
                return retVal;
        }
    }

    /**
     * 查询
     *
     * @param uri
     * @param projection
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     * @return
     * @see ContentProvider#query(Uri,
     *      String[], String, String[],
     *      String)
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        final int match = mUriMatcher.match(uri);

        switch (mUriMatcher.match(uri)) {
//            case MOBILE_COLUMN_LIST: {
//                Cursor cursor = db.query(MobileColumnListTable.TABLE_NAME, null, selection, selectionArgs, null, null,
//                        sortOrder);
//                if (cursor != null) {
//                    cursor.setNotificationUri(getContext().getContentResolver(), uri);
//                }
//                return cursor;
//            }
//            case MOBILE_COLUMN_ITEM_LIST: {
//                Cursor cursor = db.query(MobileColumnItemTable.TABLE_NAME, null, selection, selectionArgs, null, null,
//                        sortOrder);
//                if (cursor != null) {
//                    cursor.setNotificationUri(getContext().getContentResolver(), uri);
//                }
//                return cursor;
//            }
            default:
                final SelectionBuilder builder = buildExpandedSelection(uri, match);
                final Cursor cursor = builder.where(selection, selectionArgs).query(db, projection, sortOrder);

                if (cursor != null) {
                    cursor.setNotificationUri(getContext().getContentResolver(), uri);
                }
                return cursor;
        }
    }

    /**
     * 删除，修改使用 Build a simple {@link SelectionBuilder} to match the requested
     * {@link Uri}. This is usually enough to support {@link #insert},
     * {@link #update}, and {@link #delete} operations.
     */
    private SelectionBuilder buildSimpleSelection(Uri uri) {
        final SelectionBuilder builder = new SelectionBuilder();
        final int match = mUriMatcher.match(uri);
        switch (match) {
//            case HOTPOINT_TITLE_ITEM: {
//                final String columnId = HotPointTitleTable.getHotPointTitleItemUri(uri);
//                return builder.table(HotPointTitleTable.TABLE_NAME)
//                        .where(HotPointTitleTable.columnid + "=? ", columnId);
//            }
//            case HOTPOINT_STREAM_ITEM: {
//                final String columnId = HotPointStreamTable.getHotPointStreamItemUri(uri);
//                return builder.table(HotPointStreamTable.TABLE_NAME).where(HotPointStreamTable.columnid + "=? ",
//                        columnId);
//            }
            default:
                throw new UnsupportedOperationException("update or delete action with Unknown uri: " + uri);
        }
    }

    /**
     * 查询使用 Build an advanced {@link SelectionBuilder} to match the requested
     * {@link Uri}. This is usually only used by {@link #query}, since it
     * performs table joins useful for {@link Cursor} data.
     */
    private SelectionBuilder buildExpandedSelection(Uri uri, int match) {
        final SelectionBuilder builder = new SelectionBuilder();
        switch (match) {
//            case HOTPOINT_TITLE_ITEM: {
//                final String columnId = HotPointTitleTable.getHotPointTitleItemUri(uri);
//                return builder.table(HotPointTitleTable.TABLE_NAME)
//                        .where(HotPointTitleTable.columnid + "=? ", columnId);
//            }
//            case HOTPOINT_STREAM_ITEM: {
//                final String columnId = HotPointStreamTable.getHotPointStreamItemUri(uri);
//                return builder.table(HotPointStreamTable.TABLE_NAME).where(HotPointStreamTable.columnid + "=? ",
//                        columnId);
//            }
            default:
                if (LogUtils.isDebug()) {
                    LogUtils.d(TAG, "Unknown uri: " + uri);
                }
                throw new UnsupportedOperationException("query action with Unknown uri: " + uri);
        }
    }

    @Override
    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> operations)
            throws OperationApplicationException {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            final int numOperations = operations.size();
            final ContentProviderResult[] results = new ContentProviderResult[numOperations];
            for (int i = 0; i < numOperations; i++) {
                results[i] = operations.get(i).apply(this, results, i);
            }
            db.setTransactionSuccessful();
            return results;
        } catch (Exception e) {
            throw new OperationApplicationException(e.getMessage());
        } finally {
            db.endTransaction();
        }
    }

}
