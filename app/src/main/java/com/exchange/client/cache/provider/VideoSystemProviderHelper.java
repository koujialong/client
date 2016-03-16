/*
 * VideoSystemProviderHelper.java
 * classes : com.sohu.sohuvideo.provider.VideoSystemProviderHelper
 * @author xiangyutian
 * V 4.5.0
 * Create at 2014-3-22 下午6:34:24
 */
package com.exchange.client.cache.provider;

/**
 * com.sohu.sohuvideo.provider.VideoSystemProviderHelper
 * 
 * @author xiangyutian <br/>
 *         create at 2014-3-22 下午6:34:24
 */
public class VideoSystemProviderHelper {
    /**
     * TAG
     */
    private static final String TAG = "VideoSystemProviderHelper";

    public VideoSystemProviderHelper() {
    }

//    /**
//     * 清空并插入首页标题列表数据
//     *
//     * @author xiangyutian
//     * @param context
//     * @param columnInfos
//     * @return create at 2014-3-25 下午5:48:23
//     */
//    public boolean insertColumnListInfoWithClearAction(Context context, List<ColumnListModel> columnInfos) {
//        ArrayList<ContentProviderOperation> batch = new ArrayList<ContentProviderOperation>();
//        batch.add(ContentProviderOperation.newDelete(MobileColumnListTable.buildMobileColumnListUri()).build());// 删除标题
//        batch.add(ContentProviderOperation.newDelete(MobileColumnItemTable.buildMobileColumnItemListUri()).build());// 删除item数据
//        return insertColumnListInfo(context, columnInfos, batch);
//    }
//
//    /**
//     * 批量插入首页标题列表
//     *
//     * @author xiangyutian
//     * @param context
//     * @param columnInfos
//     * @param batch
//     * @return create at 2014-3-25 下午5:48:14
//     */
//    private boolean insertColumnListInfo(Context context, List<ColumnListModel> columnInfos,
//            ArrayList<ContentProviderOperation> batch) {
//        final int columnInfosSize = columnInfos.size();
//
//        LogUtils.d(TAG, "insertColumnListInfo size=" + columnInfosSize);
//        for (ColumnListModel info : columnInfos) {
//            if (info == null) {
//                continue;
//            }
//            if (ListUtils.isEmpty(info.getVideo_list())) {
//                continue;
//            }
//
//            // 插入标题数据
//            batch.add(insertColumnListOperation(MobileColumnListTable.buildMobileColumnListUri(), info).build());
//            // 插入videos数据
//            List<ColumnVideoInfoModel> tempList = info.getVideo_list();
//            for (ColumnVideoInfoModel model : tempList) {
//                if (model == null) {
//                    continue;
//                }
//
//                batch.add(insertColumnItemListOperation(MobileColumnItemTable.buildMobileColumnItemListUri(), model,
//                        info.getColumn_id()).build());
//            }
//        }
//        try {
//            return flush(context, batch);
//        } catch (JSONException e) {
//            LogUtils.e(TAG, "插入首页标题列表数据库数据发生异常", e);
//        }
//        return false;
//    }
//
//    /**
//     * 插入首页标题表操作
//     *
//     * @author xiangyutian
//     * @param uri
//     * @param model
//     * @return create at 2014-3-25 下午5:48:04
//     */
//    public ContentProviderOperation.Builder insertColumnListOperation(final Uri uri, ColumnListModel model) {
//        return ContentProviderOperation.newInsert(uri)
//                .withValue(MobileColumnListTable.COLUMN_TYPE, model.getColumn_type())
//                .withValue(MobileColumnListTable.COLUMN_ID, model.getColumn_id())
//                .withValue(MobileColumnListTable.NAME, TextUtils.isEmpty(model.getName()) ? "" : model.getName())
//                .withValue(MobileColumnListTable.JUMP_CATE_CODE, "").withValue(MobileColumnListTable.LAYOUT_TYPE, 0)
//                .withValue(MobileColumnListTable.CONTENT_SIZE, 0).withValue(MobileColumnListTable.COLUMN_TIP, "");
//    }
//
//    /**
//     * 分批次操作数据库，每次执行后，清空集合
//     *
//     * @param batch 操作集合
//     * @throws JSONException
//     */
//    private boolean flush(Context context, final ArrayList<ContentProviderOperation> batch) throws JSONException {
//        if (batch.isEmpty()) {
//            return false;
//        }
//        ContentProviderResult[] results;
//        try {
//            results = context.getContentResolver().applyBatch(VideoSystemContract.CONTENT_AUTHORITY, batch);
//            batch.clear();
//        } catch (android.os.RemoteException e) {
//            LogUtils.e(TAG, "flush RemoteException", e);
//            throw new JSONException(e.getMessage());
//        } catch (OperationApplicationException e) {
//            LogUtils.e(TAG, "flush OperationApplicationException", e);
//            throw new JSONException(e.getMessage());
//        }
//
//        if (results == null || results.length == 0) {
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//    /**
//     * 插入首页ITEM操作
//     *
//     * @author xiangyutian
//     * @param uri
//     * @param model
//     * @return create at 2014-3-25 下午5:47:29
//     */
//    public ContentProviderOperation.Builder insertColumnItemListOperation(final Uri uri, VideoInfoModel model,
//            final long columnId) {
//        return ContentProviderOperation
//                .newInsert(uri)
//                .withValue(MobileColumnItemTable.COLUMN_ID, columnId)
//                .withValue(MobileColumnItemTable.priority, model.getPriority())
//                .withValue(MobileColumnItemTable.ver_high_pic,
//                        TextUtils.isEmpty(model.getVer_high_pic()) ? "" : model.getVer_high_pic())
//                .withValue(MobileColumnItemTable.ver_big_pic,
//                        TextUtils.isEmpty(model.getVer_big_pic()) ? "" : model.getVer_big_pic())
//                .withValue(MobileColumnItemTable.hor_big_pic,
//                        TextUtils.isEmpty(model.getHor_big_pic()) ? "" : model.getHor_big_pic())
//                .withValue(MobileColumnItemTable.hor_high_pic,
//                        TextUtils.isEmpty(model.getHor_high_pic()) ? "" : model.getHor_high_pic())
//                .withValue(MobileColumnItemTable.cate_code,
//                        TextUtils.isEmpty(model.getCate_code()) ? "" : model.getCate_code())
//                .withValue(MobileColumnItemTable.cid, model.getCid())
//                .withValue(MobileColumnItemTable.is_original_code, model.getIs_original_code())
//                .withValue(MobileColumnItemTable.program_id, model.getProgram_id())
//                .withValue(MobileColumnItemTable.aid, model.getAid())
//                .withValue(MobileColumnItemTable.vid, model.getVid())
//                .withValue(MobileColumnItemTable.status, model.getStatus())
//                .withValue(MobileColumnItemTable.ip_limit, model.getIp_limit())
//                .withValue(MobileColumnItemTable.mobile_limit, model.getMobile_limit())
//                .withValue(MobileColumnItemTable.total_video_count, model.getTotal_video_count())
//                .withValue(MobileColumnItemTable.latest_video_count, model.getLatest_video_count())
//                .withValue(MobileColumnItemTable.is_album, model.getIs_album())
//                .withValue(MobileColumnItemTable.album_name, model.getAlbum_name())
//                .withValue(MobileColumnItemTable.album_sub_name, model.getAlbum_sub_name())
//                .withValue(MobileColumnItemTable.label, TextUtils.isEmpty(model.getLabel()) ? "" : model.getLabel())
//                .withValue(MobileColumnItemTable.tip, TextUtils.isEmpty(model.getTip()) ? "" : model.getTip())
//                .withValue(MobileColumnItemTable.video_big_pic, model.getVideo_big_pic())
//                .withValue(MobileColumnItemTable.tv_desc, model.getTv_desc())
//                .withValue(MobileColumnItemTable.site, model.getSite())
//                .withValue(MobileColumnItemTable.score, model.getScore())
//                .withValue(MobileColumnItemTable.publish_time, model.getPublish_time())
//                .withValue(MobileColumnItemTable.video_name, model.getVideoName())
//                .withValue(MobileColumnItemTable.video_sub_name, model.getVideo_sub_name());
//    }
//
//    /**
//     * 获取columnitem列表
//     *
//     * @param context
//     * @param columnid
//     * @return
//     */
//    public ArrayList<VideoInfoModel> getColumnItemList(final Context context, final long columnid) {
//        ContentResolver resolver = context.getContentResolver();
//        ArrayList<VideoInfoModel> lists = new ArrayList<VideoInfoModel>();
//        Cursor cursor = null;
//
//        cursor = resolver.query(MobileColumnItemTable.buildMobileColumnItemListUri(),
//                MobileColumnItemTable.Query.PROJECTION, MobileColumnItemTable.COLUMN_ID + "=?",
//                new String[] { String.valueOf(columnid) }, null);
//
//        try {
//            while (cursor != null && cursor.moveToNext()) {
//                VideoInfoModel bean = new VideoInfoModel();
//                bean.setPriority(cursor.getInt(MobileColumnItemTable.Query.priority));
//                bean.setVer_high_pic(cursor.getString(MobileColumnItemTable.Query.ver_high_pic));
//                bean.setVer_big_pic(cursor.getString(MobileColumnItemTable.Query.ver_big_pic));
//                bean.setHor_big_pic(cursor.getString(MobileColumnItemTable.Query.hor_big_pic));
//                bean.setHor_high_pic(cursor.getString(MobileColumnItemTable.Query.hor_high_pic));
//                bean.setCate_code(String.valueOf(cursor.getInt(MobileColumnItemTable.Query.cate_code)));
//                bean.setCid(cursor.getLong(MobileColumnItemTable.Query.cid));
//                bean.setIs_original_code(cursor.getInt(MobileColumnItemTable.Query.is_original_code));
//                bean.setProgram_id(cursor.getLong(MobileColumnItemTable.Query.program_id));
//                bean.setAid(cursor.getLong(MobileColumnItemTable.Query.aid));
//                bean.setVid(cursor.getLong(MobileColumnItemTable.Query.vid));
//                bean.setStatus(cursor.getLong(MobileColumnItemTable.Query.status));
//                bean.setIp_limit(cursor.getInt(MobileColumnItemTable.Query.ip_limit));
//                bean.setMobile_limit(cursor.getInt(MobileColumnItemTable.Query.mobile_limit));
//                bean.setTotal_video_count(cursor.getInt(MobileColumnItemTable.Query.total_video_count));
//                bean.setLatest_video_count(cursor.getInt(MobileColumnItemTable.Query.latest_video_count));
//                bean.setIs_album(cursor.getInt(MobileColumnItemTable.Query.is_album));
//                bean.setAlbum_name(cursor.getString(MobileColumnItemTable.Query.album_name));
//                bean.setAlbum_sub_name(cursor.getString(MobileColumnItemTable.Query.album_sub_name));
//                bean.setLabel(cursor.getString(MobileColumnItemTable.Query.label));
//                bean.setTip(cursor.getString(MobileColumnItemTable.Query.tip));
//                bean.setVer_big_pic(cursor.getString(MobileColumnItemTable.Query.video_big_pic));
//                bean.setTv_desc(cursor.getString(MobileColumnItemTable.Query.tv_desc));
//                bean.setSite(cursor.getInt(MobileColumnItemTable.Query.site));
//                bean.setScore(cursor.getFloat(MobileColumnItemTable.Query.score));
//                bean.setPublish_time(cursor.getString(MobileColumnItemTable.Query.publish_time));
//                bean.setVideo_name(cursor.getString(MobileColumnItemTable.Query.video_name));
//                bean.setVideo_sub_name(cursor.getString(MobileColumnItemTable.Query.video_sub_name));
//
//                lists.add(bean);
//            }
//        } catch (Exception e) {
//            LogUtils.e(TAG, "getColumnItemList query db break out Exception...", e);
//        } finally {
//            if ((cursor != null) && (!cursor.isClosed())) {
//                cursor.close();
//            }
//        }
//
//        return lists;
//    }
//
//    /**
//     * 清空并插入频道类别列表数据
//     *
//     * @author xiangyutian
//     * @param context
//     * @param columnInfos
//     * @return create at 2014-3-25 下午5:48:23
//     */
//    public boolean insertChannelCategoryListInfoWithClearAction(Context context,
//            List<ChannelCategoryModel> categoryInfos) {
//        ArrayList<ContentProviderOperation> batch = new ArrayList<ContentProviderOperation>();
//        batch.add(ContentProviderOperation.newDelete(ChannelCategoryTable.buildChannelCategoryListUri()).build());
//        return insertChannelCategoryListInfo(context, categoryInfos, batch);
//    }
//
//    /**
//     * 清空并插入自媒体列表
//     *
//     * @param context
//     * @param categoryInfos
//     * @return
//     */
//    public boolean insertPgcCategoryListInfoWithClearAction(Context context, List<ChannelCategoryModel> categoryInfos) {
//        ArrayList<ContentProviderOperation> batch = new ArrayList<ContentProviderOperation>();
//        batch.add(ContentProviderOperation.newDelete(PgcCategoryTable.buildPgcCategoryListUri()).build());
//        return insertPgcCategoryListInfo(context, categoryInfos, batch);
//    }
//
//    /**
//     * 获取PGC频道列表
//     *
//     * @return
//     */
//    public ArrayList<ChannelCategoryModel> getPgcChannelList(final Context context) {
//        ContentResolver resolver = context.getContentResolver();
//        ArrayList<ChannelCategoryModel> lists = new ArrayList<ChannelCategoryModel>();
//        Cursor cursor = null;
//
//        cursor = resolver.query(PgcCategoryTable.buildPgcCategoryListUri(), MobileColumnItemTable.Query.PROJECTION,
//                null, null, null);
//
//        try {
//            while (cursor != null && cursor.moveToNext()) {
//                if (cursor.getInt(ChannelCategoryTable.Query.catecode) < 0) {
//                    LogUtils.e(TAG, "onitemclick catecode < 0");
//                    return lists;
//                }
//
//                ChannelCategoryModel model = new ChannelCategoryModel();
//                model.setCateApi(cursor.getString(ChannelCategoryTable.Query.cateapi));
//                model.setCateCode(cursor.getInt(ChannelCategoryTable.Query.catecode));
//                model.setCid(cursor.getInt(ChannelCategoryTable.Query.cid));
//                model.setLayout(cursor.getInt(ChannelCategoryTable.Query.layout));
//                model.setName(cursor.getString(ChannelCategoryTable.Query.name));
//                model.setChanneled(cursor.getString(ChannelCategoryTable.Query.channeled));
//                model.setMoreListLayoutType(cursor.getInt(ChannelCategoryTable.Query.more_list_layout_type));
//                model.setChannel_id(cursor.getLong(ChannelCategoryTable.Query.channel_id));
//                model.setLocation(cursor.getInt(ChannelCategoryTable.Query.location));
//                lists.add(model);
//            }
//        } catch (Exception e) {
//            LogUtils.e(TAG, "getHomeChannelList query db break out Exception...", e);
//        } finally {
//            if ((cursor != null) && (!cursor.isClosed())) {
//                cursor.close();
//            }
//        }
//
//        return lists;
//    }
//
//    /**
//     * 获取首页频道列表
//     *
//     * @return
//     */
//    public ArrayList<ChannelCategoryModel> getHomeChannelList(final Context context) {
//        ContentResolver resolver = context.getContentResolver();
//        ArrayList<ChannelCategoryModel> lists = new ArrayList<ChannelCategoryModel>();
//        Cursor cursor = null;
//
//        cursor = resolver.query(ChannelCategoryTable.buildChannelCategoryListUri(),
//                MobileColumnItemTable.Query.PROJECTION, null, null, null);
//
//        try {
//            while (cursor != null && cursor.moveToNext()) {
//                if (cursor.getInt(ChannelCategoryTable.Query.catecode) < 0) {
//                    LogUtils.e(TAG, "onitemclick catecode < 0");
//                    return lists;
//                }
//
//                ChannelCategoryModel model = new ChannelCategoryModel();
//                model.setCateApi(cursor.getString(ChannelCategoryTable.Query.cateapi));
//                model.setCateCode(cursor.getInt(ChannelCategoryTable.Query.catecode));
//                model.setCid(cursor.getInt(ChannelCategoryTable.Query.cid));
//                model.setLayout(cursor.getInt(ChannelCategoryTable.Query.layout));
//                model.setName(cursor.getString(ChannelCategoryTable.Query.name));
//                model.setChanneled(cursor.getString(ChannelCategoryTable.Query.channeled));
//                model.setMoreListLayoutType(cursor.getInt(ChannelCategoryTable.Query.more_list_layout_type));
//                model.setChannel_id(cursor.getLong(ChannelCategoryTable.Query.channel_id));
//                model.setLocation(cursor.getInt(ChannelCategoryTable.Query.location));
//                lists.add(model);
//            }
//        } catch (Exception e) {
//            LogUtils.e(TAG, "getHomeChannelList query db break out Exception...", e);
//        } finally {
//            if ((cursor != null) && (!cursor.isClosed())) {
//                cursor.close();
//            }
//        }
//
//        return lists;
//    }
//
//    /**
//     * 批量插入频道类别列表
//     *
//     * @author xiangyutian
//     * @param context
//     * @param columnInfos
//     * @param batch
//     * @return create at 2014-3-25 下午5:48:14
//     */
//    private boolean insertChannelCategoryListInfo(Context context, List<ChannelCategoryModel> categoryInfos,
//            ArrayList<ContentProviderOperation> batch) {
//        final int categoryInfosSize = categoryInfos.size();
//
//        LogUtils.d(TAG, "insertcategoryInfos size=" + categoryInfosSize);
//        for (ChannelCategoryModel info : categoryInfos) {
//            if (info == null) {
//                continue;
//            }
//            // 过滤"首页"标签
//            // if (info.getCateCode() ==
//            // ChannelCategoryModel.CHANNEL_ID_HOMEPAGE) {
//            // continue;
//            // }
//
//            // 过滤"直播"标签
//            if (info.getCateCode() == ChannelCategoryModel.CHANNEL_ID_LIVE) {
//                if (CustomUtils.isLiveEnable()) {
//                    // do nothing
//                } else {
//                    continue; // 将"直播"标签过滤掉
//                }
//            }
//
//            batch.add(insertChannelCategoryListOperation(ChannelCategoryTable.buildChannelCategoryListUri(), info)
//                    .build());
//        }
//        try {
//            return flush(context, batch);
//        } catch (JSONException e) {
//            LogUtils.e(TAG, "插入频道类别列表数据库数据发生异常", e);
//        }
//        return false;
//    }
//
//    /**
//     * 批量插入
//     *
//     * @param context
//     * @param categoryInfos
//     * @param batch
//     * @return
//     */
//    private boolean insertPgcCategoryListInfo(Context context, List<ChannelCategoryModel> categoryInfos,
//            ArrayList<ContentProviderOperation> batch) {
//        final int categoryInfosSize = categoryInfos.size();
//
//        LogUtils.d(TAG, "insertcategoryInfos size=" + categoryInfosSize);
//        for (ChannelCategoryModel info : categoryInfos) {
//            if (info == null) {
//                continue;
//            }
//
//            batch.add(insertPgcCategoryListOperation(PgcCategoryTable.buildPgcCategoryListUri(), info).build());
//        }
//        try {
//            return flush(context, batch);
//        } catch (JSONException e) {
//            LogUtils.e(TAG, "插入频道类别列表数据库数据发生异常", e);
//        }
//        return false;
//    }
//
//    /**
//     * 插入频道类别表操作
//     *
//     * @author xiangyutian
//     * @param uri
//     * @param model
//     * @return create at 2014-3-25 下午5:48:04
//     */
//    public ContentProviderOperation.Builder insertChannelCategoryListOperation(final Uri uri, ChannelCategoryModel model) {
//        return ContentProviderOperation
//                .newInsert(uri)
//                .withValue(ChannelCategoryTable.icon, model.getIcon())
//                .withValue(ChannelCategoryTable.name, model.getName())
//                .withValue(ChannelCategoryTable.cateapi, model.getCateApi())
//                .withValue(ChannelCategoryTable.layout, model.getLayout())
//                .withValue(ChannelCategoryTable.cid, model.getCid())
//                .withValue(ChannelCategoryTable.catecode, model.getCateCode())
//                .withValue(ChannelCategoryTable.iconselected, model.getIconSelected())
//                .withValue(ChannelCategoryTable.channeled,
//                        TextUtils.isEmpty(model.getChanneled()) ? "" : model.getChanneled())
//                .withValue(ChannelCategoryTable.channel_id, model.getChannel_id())
//                .withValue(ChannelCategoryTable.location, model.getLocation());
//    }
//
//    /**
//     * 插入自媒体类别表操作
//     *
//     * @author xiangyutian
//     * @param uri
//     * @param model
//     * @return create at 2014-3-25 下午5:48:04
//     */
//    public ContentProviderOperation.Builder insertPgcCategoryListOperation(final Uri uri, ChannelCategoryModel model) {
//        return ContentProviderOperation
//                .newInsert(uri)
//                .withValue(PgcCategoryTable.icon, model.getIcon())
//                .withValue(PgcCategoryTable.name, model.getName())
//                .withValue(PgcCategoryTable.cateapi, model.getCateApi())
//                .withValue(PgcCategoryTable.layout, model.getLayout())
//                .withValue(PgcCategoryTable.cid, model.getCid())
//                .withValue(PgcCategoryTable.catecode, model.getCateCode())
//                .withValue(PgcCategoryTable.iconselected, model.getIconSelected())
//                .withValue(PgcCategoryTable.channeled,
//                        TextUtils.isEmpty(model.getChanneled()) ? "" : model.getChanneled())
//                .withValue(PgcCategoryTable.channel_id, model.getChannel_id())
//                .withValue(PgcCategoryTable.location, model.getLocation());
//    }
//
//    public boolean insertHotPointTitleListWithClearAction(Context context, List<HotPointColumnModel> columnList) {
//        ArrayList<ContentProviderOperation> batch = new ArrayList<ContentProviderOperation>();
//        batch.add(ContentProviderOperation.newDelete(HotPointTitleTable.buildHotPointTitleListUri()).build());
//        return insertHotPointTitleListInfo(context, columnList, batch);
//    }
//
//    /**
//     * 批量插入热点标题列表
//     *
//     * @author xiangyutian
//     * @param context
//     * @param columnInfos
//     * @param batch
//     * @return create at 2014-3-25 下午5:48:14
//     */
//    private boolean insertHotPointTitleListInfo(Context context, List<HotPointColumnModel> columnList,
//            ArrayList<ContentProviderOperation> batch) {
//        final int categoryInfosSize = columnList.size();
//
//        LogUtils.d(TAG, "insertcategoryInfos size=" + categoryInfosSize);
//        for (HotPointColumnModel info : columnList) {
//            if (info == null) {
//                continue;
//            }
//            batch.add(insertHotPointTitleListOperation(HotPointTitleTable.buildHotPointTitleListUri(), info).build());
//        }
//        try {
//            return flush(context, batch);
//        } catch (JSONException e) {
//            LogUtils.e(TAG, "插入频道类别列表数据库数据发生异常", e);
//        }
//        return false;
//    }
//
//    /**
//     * 批量插入热点标题列表操作
//     *
//     * @author xiangyutian
//     * @param uri
//     * @param model
//     * @return create at 2014-3-25 下午5:48:04
//     */
//    public ContentProviderOperation.Builder insertHotPointTitleListOperation(final Uri uri, HotPointColumnModel model) {
//        return ContentProviderOperation.newInsert(uri).withValue(HotPointTitleTable.columntype, model.getColumnType())
//                .withValue(HotPointTitleTable.columnid, model.getColumnId())
//                .withValue(HotPointTitleTable.jumpcatecode, model.getJumpCateCode())
//                .withValue(HotPointTitleTable.layouttype, model.getLayoutType())
//                .withValue(HotPointTitleTable.morelist, model.getMoreList())
//                .withValue(HotPointTitleTable.name, model.getName());
//    }
//
//    /**
//     * 通过id查询热点maxid minid
//     *
//     * @param context
//     * @param id
//     * @return
//     */
//    public HotPointColumnModel getHotPointTitleModelById(Context context, long columnid) {
//        Cursor cursor = context.getContentResolver().query(HotPointTitleTable.buildHotPointTitleListUri(),
//                HotPointTitleTable.Query.PROJECTION, HotPointTitleTable.columnid + "=?",
//                new String[] { String.valueOf(columnid) }, null);
//        if (cursor == null) {
//            return null;
//        }
//
//        HotPointColumnModel model = null;
//        try {
//            while (cursor != null && cursor.moveToNext()) {
//                model = new HotPointColumnModel();
//                model.setColumnType(cursor.getInt(HotPointTitleTable.Query.columntype));
//                model.setColumnId(cursor.getInt(HotPointTitleTable.Query.columnid));
//                model.setJumpCateCode(cursor.getInt(HotPointTitleTable.Query.jumpcatecode));
//                model.setLayoutType(cursor.getInt(HotPointTitleTable.Query.layouttype));
//                model.setMoreList(cursor.getString(HotPointTitleTable.Query.morelist));
//                model.setName(cursor.getString(HotPointTitleTable.Query.name));
//                model.setMax_id(cursor.getInt(HotPointTitleTable.Query.max_id));
//                model.setMin_id(cursor.getInt(HotPointTitleTable.Query.min_id));
//                model.setSaveTime(cursor.getLong(HotPointTitleTable.Query.savetime));
//            }
//        } catch (Exception e) {
//            LogUtils.e(TAG, "数据库读取getHotPointTitleModelById 发生异常!!!", e);
//        } finally {
//            if (cursor != null) {
//                cursor.close();
//            }
//        }
//
//        return model;
//    }
//
//    /**
//     * 通过id查询热点下属的streamlist
//     *
//     * @param context
//     * @param id
//     * @return
//     */
//    public ArrayList<VideoInfoModel> getHotPointStreamModelById(Context context, long columnid) {
//        ArrayList<VideoInfoModel> lists = new ArrayList<VideoInfoModel>();
//        Cursor cursor = context.getContentResolver().query(HotPointStreamTable.buildHotPointStreamListUri(),
//                HotPointStreamTable.Query.PROJECTION, HotPointTitleTable.columnid + "=?",
//                new String[] { String.valueOf(columnid) }, HotPointStreamTable.position + " ASC");
//        try {
//            while (cursor != null && cursor.moveToNext()) {
//                VideoInfoModel bean = new VideoInfoModel();
//                bean.setColumnId(cursor.getLong(HotPointStreamTable.Query.columnid));
//                bean.setVid(cursor.getLong(HotPointStreamTable.Query.vid));
//                bean.setAid(cursor.getLong(HotPointStreamTable.Query.aid));
//                bean.setProgram_id(cursor.getLong(HotPointStreamTable.Query.program_id));
//                bean.setCid(cursor.getLong(HotPointStreamTable.Query.cid));
//                bean.setCate_code(String.valueOf(cursor.getInt(HotPointStreamTable.Query.cate_code)));
//                bean.setSite(cursor.getInt(HotPointStreamTable.Query.site));
//                bean.setHor_high_pic(cursor.getString(HotPointStreamTable.Query.hor_high_pic));
//                bean.setTotal_duration(cursor.getFloat(HotPointStreamTable.Query.total_duration));
//                bean.setVideo_name(cursor.getString(HotPointStreamTable.Query.video_name));
//                bean.setProgram_name(cursor.getString(HotPointStreamTable.Query.program_name));
//                bean.setDownload_url(cursor.getString(HotPointStreamTable.Query.download_url));
//                bean.setUrl_html5(cursor.getString(HotPointStreamTable.Query.url_html5));
//                bean.setUrl_high(cursor.getString(HotPointStreamTable.Query.url_high));
//                bean.setPosition(cursor.getInt(HotPointStreamTable.Query.position));
//                bean.setAlbum_hor_high_pic(cursor.getString(HotPointStreamTable.Query.album_hor_high_pic));
//                bean.setAlbum_hor_big_pic(cursor.getString(HotPointStreamTable.Query.album_hor_big_pic));
//                bean.setAlbum_hor_small_pic(cursor.getString(HotPointStreamTable.Query.album_hor_small_pic));
//                bean.setAlbum_sub_name(cursor.getString(HotPointStreamTable.Query.album_sub_name));
//                bean.setAlbum_name(cursor.getString(HotPointStreamTable.Query.album_name));
//                lists.add(bean);
//            }
//        } catch (Exception e) {
//            LogUtils.e(TAG, "getColumnItemList query db break out Exception...", e);
//        } finally {
//            if ((cursor != null) && (!cursor.isClosed())) {
//                cursor.close();
//            }
//        }
//
//        return lists;
//    }
//
//    /**
//     * 插入指定columnId的热点标题列表数据
//     *
//     * @author xiangyutian
//     * @param context
//     * @param columnInfos
//     * @return create at 2014-3-25 下午5:48:23
//     */
//    public boolean insertHotPointTitleListAction(Context context, HotPointColumnModel columnInfo) {
//        ArrayList<ContentProviderOperation> batch = new ArrayList<ContentProviderOperation>();
//        return insertHotPointTitleInfo(context, columnInfo, batch);
//    }
//
//    /**
//     * 批量插入热点标题列表
//     *
//     * @author xiangyutian
//     * @param context
//     * @param columnInfos
//     * @param batch
//     * @return create at 2014-3-25 下午5:48:14
//     */
//    private boolean insertHotPointTitleInfo(Context context, HotPointColumnModel column,
//            ArrayList<ContentProviderOperation> batch) {
//        batch.add(insertHotPointTitleOperation(HotPointTitleTable.buildHotPointTitleListUri(), column).build());
//        try {
//            return flush(context, batch);
//        } catch (JSONException e) {
//            LogUtils.e(TAG, "插入热点类别列表数据库数据发生异常", e);
//        }
//        return false;
//    }
//
//    /**
//     * 批量插入热点标题列表操作
//     *
//     * @author xiangyutian
//     * @param uri
//     * @param model
//     * @return create at 2014-3-25 下午5:48:04
//     */
//    public ContentProviderOperation.Builder insertHotPointTitleOperation(final Uri uri, HotPointColumnModel model) {
//        return ContentProviderOperation.newInsert(uri).withValue(HotPointTitleTable.columntype, model.getColumnType())
//                .withValue(HotPointTitleTable.columnid, model.getColumnId())
//                .withValue(HotPointTitleTable.jumpcatecode, model.getJumpCateCode())
//                .withValue(HotPointTitleTable.layouttype, model.getLayoutType())
//                .withValue(HotPointTitleTable.morelist, model.getMoreList())
//                .withValue(HotPointTitleTable.name, model.getName())
//                .withValue(HotPointTitleTable.max_id, model.getMax_id())
//                .withValue(HotPointTitleTable.min_id, model.getMin_id())
//                .withValue(HotPointTitleTable.savetime, model.getSaveTime());
//    }
//
//    /**
//     * 插入指定热点stream列表数据
//     *
//     * @author xiangyutian
//     * @param context
//     * @param columnInfos
//     * @return create at 2014-3-25 下午5:48:23
//     */
//    public boolean insertHotPointStreamListAction(Context context, long columnId, List<VideoInfoModel> columnInfos) {
//        ArrayList<ContentProviderOperation> batch = new ArrayList<ContentProviderOperation>();
//        return insertHotPointStreamListInfo(context, columnId, columnInfos, batch);
//    }
//
//    /**
//     * com.sohu.sohuvideo.provider
//     *
//     * @author xiangyutian
//     * @param context
//     * @param columnInfos
//     * @param batch
//     * @return create at 2014-4-15 下午5:55:03
//     */
//    private boolean insertHotPointStreamListInfo(Context context, long columnid, List<VideoInfoModel> videoList,
//            ArrayList<ContentProviderOperation> batch) {
//        for (VideoInfoModel info : videoList) {
//            if (info == null) {
//                continue;
//            }
//            batch.add(insertHotPointStreamOperation(HotPointStreamTable.buildHotPointStreamUri(columnid), info,
//                    columnid).build());
//        }
//        try {
//            return flush(context, batch);
//        } catch (JSONException e) {
//            LogUtils.e(TAG, "插入热点stream列表数据库数据发生异常", e);
//        }
//        return false;
//    }
//
//    /**
//     * 批量插入热点stream列表操作
//     *
//     * @author xiangyutian
//     * @param uri
//     * @param model
//     * @return create at 2014-3-25 下午5:48:04
//     */
//    public ContentProviderOperation.Builder insertHotPointStreamOperation(final Uri uri, VideoInfoModel model,
//            long columnid) {
//        return ContentProviderOperation.newInsert(uri).withValue(HotPointStreamTable.columnid, columnid)
//                .withValue(HotPointStreamTable.vid, model.getVid()).withValue(HotPointStreamTable.aid, model.getAid())
//                .withValue(HotPointStreamTable.program_id, model.getProgram_id())
//                .withValue(HotPointStreamTable.cid, model.getCid())
//                .withValue(HotPointStreamTable.cate_code, model.getCate_code())
//                .withValue(HotPointStreamTable.site, model.getSite())
//                .withValue(HotPointStreamTable.hor_high_pic, model.getHor_high_pic())
//                .withValue(HotPointStreamTable.total_duration, model.getTotal_duration())
//                .withValue(HotPointStreamTable.video_name, model.getVideoName())
//                .withValue(HotPointStreamTable.program_name, model.getProgram_name())
//                .withValue(HotPointStreamTable.download_url, model.getDownload_url())
//                .withValue(HotPointStreamTable.url_html5, model.getUrl_html5())
//                .withValue(HotPointStreamTable.url_high, model.getUrl_high())
//                .withValue(HotPointStreamTable.position, model.getPosition())
//                .withValue(HotPointStreamTable.album_hor_high_pic, model.getAlbum_hor_high_pic())
//                .withValue(HotPointStreamTable.album_hor_big_pic, model.getAlbum_hor_big_pic())
//                .withValue(HotPointStreamTable.album_hor_small_pic, model.getAlbum_hor_small_pic())
//                .withValue(HotPointStreamTable.album_sub_name, model.getAlbum_sub_name())
//                .withValue(HotPointStreamTable.album_name, model.getAlbum_name())
//                .withValue(HotPointStreamTable.data_type, model.getData_type());
//    }
//
//    /**
//     * 删除指定columnId的热点streamlist数据
//     *
//     * @author xiangyutian
//     * @param columnId
//     * @return create at 2014-4-15 下午6:09:19
//     */
//    public boolean deleteHotPointStreamListById(long columnId) {
//        ArrayList<ContentProviderOperation> batch = new ArrayList<ContentProviderOperation>();
//        return batch.add(ContentProviderOperation.newDelete(HotPointStreamTable.buildHotPointStreamUri(columnId))
//                .build());// 删除对应ID的信息
//    }
//
//    /**
//     * 删除指定columnId的热点title数据
//     *
//     * @author xiangyutian
//     * @param columnId
//     * @return create at 2014-4-15 下午6:09:19
//     */
//    public boolean deleteHotPointTitleById(long columnId) {
//        ArrayList<ContentProviderOperation> batch = new ArrayList<ContentProviderOperation>();
//        return batch
//                .add(ContentProviderOperation.newDelete(HotPointTitleTable.buildHotPointTitleUri(columnId)).build());// 删除对应ID的信息
//    }
//
//    /**
//     * 插入搜索关键字数据
//     *
//     * @author xiangyutian
//     * @param context
//     * @param historyModel
//     * @return create at 2014-5-7 下午4:46:41
//     */
//    public boolean insertSearchHistoryItem(Context context, SearchHistoryModel historyModel) {
//        ArrayList<ContentProviderOperation> batch = new ArrayList<ContentProviderOperation>();
//        return insertSearchHistoryInfo(context, historyModel, batch);
//    }
//
//    /**
//     * 删除对应name的信息
//     *
//     * @author xiangyutian
//     * @param name
//     * @return create at 2014-5-7 下午5:24:11
//     */
//    public boolean deleteSearchHistoryByName(Context context, String name) {
//        ArrayList<ContentProviderOperation> batch = new ArrayList<ContentProviderOperation>();
//        batch.add(ContentProviderOperation.newDelete(SearchHistoryTable.buildSearchKeyWordUri(name)).build());// 删除对应NAME的信息
//
//        try {
//            return flush(context, batch);
//        } catch (JSONException e) {
//            LogUtils.e(TAG, "删除单条搜索历史数据库数据发生异常", e);
//        }
//        return false;
//    }
//
//    /**
//     * 删除所有搜索历史记录
//     *
//     * @author xiangyutian
//     * @return create at 2014-5-7 下午5:25:20
//     */
//    public boolean deleteAllSearchHistory(Context context) {
//        ArrayList<ContentProviderOperation> batch = new ArrayList<ContentProviderOperation>();
//        batch.add(ContentProviderOperation.newDelete(SearchHistoryTable.buildSearchKeywordListUri()).build());// 删除所有信息
//
//        try {
//            return flush(context, batch);
//        } catch (JSONException e) {
//            LogUtils.e(TAG, "删除所有搜索历史数据库数据发生异常", e);
//        }
//        return false;
//    }
//
//    /**
//     * 查询搜索历史的cursor
//     *
//     * @param context
//     * @return
//     */
//    public boolean isSearchHistoryExist(Context context) {
//        Cursor cursor = context.getContentResolver().query(SearchHistoryTable.buildSearchKeywordListUri(),
//                SearchHistoryTable.Query.PROJECTION, null, null, null);
//
//        LogUtils.e(TAG, "cursor  ------------  " + cursor.getCount());
//        if (cursor == null || cursor.getCount() == 0) {
//            LogUtils.e(TAG, "getSearchHistoryCursor cursor is null");
//            cursor.close();
//            return false;
//        }
//
//        final boolean isExist = cursor.moveToFirst();
//        cursor.close();
//
//        return isExist;
//    }
//
//    /**
//     * 插入搜索关键字操作
//     *
//     * @author xiangyutian
//     * @param context
//     * @param historyModel
//     * @param batch
//     * @return create at 2014-5-7 下午4:46:33
//     */
//    private boolean insertSearchHistoryInfo(Context context, SearchHistoryModel historyModel,
//            ArrayList<ContentProviderOperation> batch) {
//        batch.add(insertSearchHistoryInfoOperation(SearchHistoryTable.buildSearchKeywordListUri(), historyModel)
//                .build());
//        try {
//            return flush(context, batch);
//        } catch (JSONException e) {
//            LogUtils.e(TAG, "插入搜索关键字数据库数据发生异常", e);
//        }
//        return false;
//    }
//
//    /**
//     * 插入搜索关键字操作
//     *
//     * @author xiangyutian
//     * @param uri
//     * @param model
//     * @return create at 2014-5-7 下午4:48:19
//     */
//    public ContentProviderOperation.Builder insertSearchHistoryInfoOperation(final Uri uri, SearchHistoryModel model) {
//        return ContentProviderOperation.newInsert(uri).withValue(SearchHistoryTable.SEARCH_WORD, model.getSearchWord())
//                .withValue(SearchHistoryTable.SEARCH_TIME, model.getSearchTime());
//    }
}
