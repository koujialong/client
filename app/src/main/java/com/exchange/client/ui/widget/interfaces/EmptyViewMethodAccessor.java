/*
 * EmptyViewMethodAccessor.java
 * @author xiangyutian
 * V 4.5.0
 * Create at 2014-5-12 下午4:04:56
 */
package com.exchange.client.ui.widget.interfaces;

import android.view.View;

/**
 * com.exchange.client.widget.EmptyViewMethodAccessor
 * 
 * @author xiangyutian <br/>
 *         create at 2014-5-12 下午4:04:56
 */
public interface EmptyViewMethodAccessor {

    /**
     * Calls upto AdapterView.setEmptyView()
     * 
     * @param emptyView to set as Empty View
     */
    public void setEmptyViewInternal(View emptyView);

    /**
     * Should call PullToRefreshBase.setEmptyView() which will then
     * automatically call through to setEmptyViewInternal()
     * 
     * @param emptyView to set as Empty View
     */
    public void setEmptyView(View emptyView);

}
