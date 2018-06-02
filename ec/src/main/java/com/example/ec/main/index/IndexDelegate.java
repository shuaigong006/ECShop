package com.example.ec.main.index;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.core.delegate.bottom.BottomItemDelegate;
import com.example.ec.R;
import com.example.ec.R2;
import com.example.ui.refresh.RefreshHandler;

import butterknife.BindView;

public class IndexDelegate extends BottomItemDelegate {

    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;

    private RefreshHandler mRefreshHandler = null;

    //设置首页界面
    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        GridLayoutManager manager = new GridLayoutManager(getContext(),4);
        mRecyclerView.setLayoutManager(manager);
        mRefreshHandler = RefreshHandler.create(mRefreshLayout,mRecyclerView,new IndexDataConverter());
        mRefreshHandler.firstPage("index.php");
    }
}
