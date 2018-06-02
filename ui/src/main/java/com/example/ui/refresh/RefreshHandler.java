package com.example.ui.refresh;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.core.configuration.ConfigureType;
import com.example.core.configuration.ShopConfigure;
import com.example.core.net.RestClient;
import com.example.core.net.callback.ISuccess;
import com.example.ui.recycler.DataConverter;
import com.example.ui.recycler.MultipleRecyclerAdapter;

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PagingEntity ENTITY;
    private final RecyclerView RECYCLERVIEW;
    private final DataConverter DATACONVERTER;
    private MultipleRecyclerAdapter mAdapter = null;

    public RefreshHandler(SwipeRefreshLayout refresh_layout, PagingEntity entity, RecyclerView
            recyclerView,DataConverter converter) {
        this.REFRESH_LAYOUT = refresh_layout;
        this.ENTITY = entity;
        this.RECYCLERVIEW = recyclerView;
        this.DATACONVERTER = converter;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    public static RefreshHandler create(SwipeRefreshLayout refresh, RecyclerView
            recyclerView,DataConverter converter) {
        return new RefreshHandler(refresh, new PagingEntity(), recyclerView,converter);
    }

    private void refresh() {
        Handler handler = ShopConfigure.getConfiguration(ConfigureType.HANDLER);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 2000);
    }

    public void firstPage(String url) {
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject object = JSON.parseObject(response);
                        ENTITY.setTotal(object.getInteger("total"))
                                .setPageSize(object.getInteger("page_size"));
                        mAdapter = MultipleRecyclerAdapter.create(DATACONVERTER.setJson(response));
                        RECYCLERVIEW.setAdapter(mAdapter);
                    }
                })
                .build()
                .get();
    }

    @Override
    public void onRefresh() {
        refresh();
    }
}
