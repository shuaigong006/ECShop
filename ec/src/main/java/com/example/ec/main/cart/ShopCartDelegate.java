package com.example.ec.main.cart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewStubCompat;
import android.view.View;
import android.widget.Toast;

import com.example.core.delegate.bottom.BottomItemDelegate;
import com.example.core.net.RestClient;
import com.example.core.net.callback.ISuccess;
import com.example.ec.R;
import com.example.ec.R2;
import com.example.ui.recycler.MultipleItem;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ShopCartDelegate extends BottomItemDelegate implements
        ICartItemListener, ISuccess {

    private ShopCartAdapter mAdapter = null;
    private double mTotalPrice = 0.00;
    private boolean selectedAll = false;

    @BindView(R2.id.rv_shop_cart)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.icon_shop_cart_select_all)
    IconTextView mIconSelectAll = null;
    @BindView(R2.id.stub_no_item)
    ViewStubCompat mStubNoItem = null;
    @BindView(R2.id.tv_shop_cart_total_price)
    AppCompatTextView mTvTotalPrice = null;

    @OnClick(R2.id.tv_top_shop_cart_clear)
    void onClickClear(){
        if (mAdapter.getData().size() != 0){
            mAdapter.getData().clear();
            mAdapter.notifyDataSetChanged();
            checkItemCount();
        }
    }

    @OnClick(R2.id.icon_shop_cart_select_all)
    void onClickSelectAll(){
        if (!selectedAll){
            mIconSelectAll.setTextColor
                    (ContextCompat.getColor(getContext(), R.color.app_main));
            mAdapter.setIsSelectedAll(true);
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
            selectedAll = true;
        }else {
            mIconSelectAll.setTextColor(Color.GRAY);
            mAdapter.setIsSelectedAll(false);
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
            selectedAll = false;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("shop_cart.php")
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onItemClick(double itemTotalPrice) {
        mTotalPrice = mAdapter.getTotalPrice();
        mTvTotalPrice.setText(String.valueOf(mTotalPrice));
    }

    @Override
    public void onSuccess(String response) {
        final List<MultipleItem> data =
                new ShopCartDataConverter().setJson(response).convert();
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new ShopCartAdapter(data);
        mAdapter.setCartItemListener(this);
        mRecyclerView.setItemAnimator(null);
        mRecyclerView.setAdapter(mAdapter);
        mTotalPrice = mAdapter.getTotalPrice();
        mTvTotalPrice.setText(String.valueOf(mTotalPrice));
        checkItemCount();
    }

    @SuppressWarnings("RestrictedApi")
    private void checkItemCount() {
        final int count = mAdapter.getItemCount();
        if (count == 0) {
            final View stubView = mStubNoItem.inflate();
            final AppCompatTextView tvToBuy =
                    (AppCompatTextView) stubView.findViewById(R.id.tv_stub_to_buy);
            tvToBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }
}
