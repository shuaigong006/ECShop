package com.example.ec.main.cart;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.ui.recycler.DataConverter;
import com.example.ui.recycler.MultipleFields;
import com.example.ui.recycler.MultipleItem;

import java.util.ArrayList;

public class ShopCartDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItem> convert() {
        final JSONArray dataArray = JSON.parseObject(getJson()).getJSONArray("data");
        for (int i = 0, size = dataArray.size(); i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);

            final int id = data.getInteger("id");
            final String desc = data.getString("desc");
            final int count = data.getInteger("count");
            final double price = data.getDouble("price");
            final String title = data.getString("title");
            final String thumb = data.getString("thumb");

            MultipleItem entity = MultipleItem.builder()
                    .setItem(MultipleFields.ITEM_TYPE, ShopCartItemType.SHOP_CART_ITEM)
                    .setItem(MultipleFields.ID, id)
                    .setItem(MultipleFields.IMAGE_URL, thumb)
                    .setItem(ShopCartItemFields.DESC, desc)
                    .setItem(ShopCartItemFields.TITLE, title)
                    .setItem(ShopCartItemFields.COUNT, count)
                    .setItem(ShopCartItemFields.PRICE, price)
                    .setItem(ShopCartItemFields.IS_SELECTED, false)
                    .setItem(ShopCartItemFields.POSITION, i)
                    .build();

            ITEMS.add(entity);
        }
        return ITEMS;
    }
}
