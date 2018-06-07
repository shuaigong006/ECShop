package com.example.ec.main.cart;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ec.R;
import com.example.ui.recycler.MultipleFields;
import com.example.ui.recycler.MultipleItem;
import com.example.ui.recycler.MultipleRecyclerAdapter;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;

public class ShopCartAdapter extends MultipleRecyclerAdapter {

    private double mTotalPrice = 0.00;
    private boolean mIsSelectedAll = false;
    private ICartItemListener mCartItemListener = null;

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    public ShopCartAdapter(List<MultipleItem> data) {
        super(data);
        for (MultipleItem entity : data) {
            final double price = entity.getItem(ShopCartItemFields.PRICE);
            final int count = entity.getItem(ShopCartItemFields.COUNT);
            final double total = price * count;
            mTotalPrice = mTotalPrice + total;
        }
        addItemType(ShopCartItemType.SHOP_CART_ITEM, R.layout.item_shop_cart);
    }

    public void setCartItemListener(ICartItemListener listener) {
        this.mCartItemListener = listener;
    }

    public void setIsSelectedAll(boolean isSelectedAll) {
        this.mIsSelectedAll = isSelectedAll;
    }

    @Override
    protected void convert(BaseViewHolder holder, final MultipleItem item) {
        super.convert(holder, item);
        switch (holder.getItemViewType()) {
            case ShopCartItemType.SHOP_CART_ITEM:
                final int id = item.getItem(MultipleFields.ID);
                final String thumb = item.getItem(MultipleFields.IMAGE_URL);
                final String title = item.getItem(ShopCartItemFields.TITLE);
                final String desc = item.getItem(ShopCartItemFields.DESC);
                final int count = item.getItem(ShopCartItemFields.COUNT);
                final double price = item.getItem(ShopCartItemFields.PRICE);
                final boolean isSelected = item.getItem(ShopCartItemFields.IS_SELECTED);

                holder.setText(R.id.tv_item_shop_cart_title, title);
                holder.setText(R.id.tv_item_shop_cart_desc, desc);
                holder.setText(R.id.tv_item_shop_cart_price, String.valueOf(price));
                holder.setText(R.id.tv_item_shop_cart_count, String.valueOf(count));
                Glide.with(mContext)
                        .load(thumb)
                        .apply(OPTIONS)
                        .into((ImageView) holder.getView(R.id.image_item_shop_cart));

                item.setItem(ShopCartItemFields.IS_SELECTED, mIsSelectedAll);

                final IconTextView iconPlus = holder.getView(R.id.icon_item_plus);
                final IconTextView iconMinus = holder.getView(R.id.icon_item_minus);
                final IconTextView icon = holder.getView(R.id.icon_item_shop_cart);
                final AppCompatTextView tvCount = holder.getView(R.id.tv_item_shop_cart_count);

                if (isSelected) {
                    icon.setTextColor(
                            ContextCompat.getColor(mContext, R.color.app_main)
                    );
                } else {
                    icon.setTextColor(Color.GRAY);
                }

                icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final boolean currentSelected = item.getItem(ShopCartItemFields.IS_SELECTED);
                        //被选中状态
                        if (currentSelected) {
                            icon.setTextColor(Color.GRAY);
                            item.setItem(ShopCartItemFields.IS_SELECTED, false);
                        } else {
                            icon.setTextColor(
                                    ContextCompat.getColor(mContext, R.color.app_main));
                            item.setItem(ShopCartItemFields.IS_SELECTED, true);
                        }
                    }
                });

                iconMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt(tvCount.getText().toString()) > 1) {
                            //网络请求数据post
                            int countNum = Integer.parseInt(tvCount.getText().toString());
                            countNum--;
                            tvCount.setText(String.valueOf(countNum));
                            if (mCartItemListener != null) {
                                mTotalPrice = mTotalPrice - price;
                                mCartItemListener.onItemClick(mTotalPrice);
                            }
                        }
                    }
                });

                iconPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //网络请求数据post
                        int countNum = Integer.parseInt(tvCount.getText().toString());
                        countNum++;
                        tvCount.setText(String.valueOf(countNum));
                        if (mCartItemListener != null) {
                            mTotalPrice = mTotalPrice + price;
                            mCartItemListener.onItemClick(mTotalPrice);
                        }
                    }
                });
                break;
            default:
                break;
        }
    }

    public double getTotalPrice() {
        return mTotalPrice;
    }
}
