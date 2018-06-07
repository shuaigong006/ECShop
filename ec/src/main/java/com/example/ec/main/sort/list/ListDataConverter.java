package com.example.ec.main.sort.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.ui.recycler.DataConverter;
import com.example.ui.recycler.ItemType;
import com.example.ui.recycler.MultipleFields;
import com.example.ui.recycler.MultipleItem;

import java.util.ArrayList;

public class ListDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItem> convert() {
        final JSONArray dataArray = JSON
                .parseObject(getJson())
                .getJSONObject("data")
                .getJSONArray("list");

        for (int i = 0, size = dataArray.size(); i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String name = data.getString("name");

            final MultipleItem entity = MultipleItem.builder()
                    .setItem(MultipleFields.ITEM_TYPE, ItemType.VERTICAL_MENU_LIST)
                    .setItem(MultipleFields.ID, id)
                    .setItem(MultipleFields.TEXT, name)
                    .setItem(MultipleFields.TAG, false)
                    .build();

            ITEMS.add(entity);
            ITEMS.get(0).setItem(MultipleFields.TAG,true);
        }
        return ITEMS;
    }
}
