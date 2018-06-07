package com.example.ec.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.example.core.delegate.ShopDelegate;
import com.example.ec.main.detail.GoodsDetailDelegate;
import com.example.ec.main.personal.list.ListEntity;
import com.example.ui.recycler.MultipleFields;
import com.example.ui.recycler.MultipleItem;

public class IndexItemClickListener extends SimpleClickListener {

    private final ShopDelegate DELEGATE;

    private IndexItemClickListener(ShopDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItem item = (MultipleItem) adapter.getData().get(position);
        final int goodsId = item.getItem(MultipleFields.ID);
        final GoodsDetailDelegate delegate = GoodsDetailDelegate.create(goodsId);
        DELEGATE.getSupportDelegate().start(delegate);
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
