package com.example.ec.main.detail;

import android.os.Bundle;

import com.example.core.delegate.ShopDelegate;
import com.example.ec.R;

public class GoodsDetailDelegate extends ShopDelegate {

    private static final String ARG_GOODS_ID = "ARG_GOODS_ID";

    public static GoodsDetailDelegate create(int goodsId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_GOODS_ID, goodsId);
        final GoodsDetailDelegate delegate = new GoodsDetailDelegate();
        delegate.setArguments(args);
        return delegate;
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_detail;
    }
}
