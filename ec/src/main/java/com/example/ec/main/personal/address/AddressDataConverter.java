package com.example.ec.main.personal.address;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.ui.recycler.DataConverter;
import com.example.ui.recycler.MultipleFields;
import com.example.ui.recycler.MultipleItem;

import java.util.ArrayList;

public class AddressDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItem> convert() {
        final JSONArray array = JSON.parseObject(getJson()).getJSONArray("data");
        for (int i=0,size = array.size();i<size;i++){
            final JSONObject data = array.getJSONObject(i);
            final int id = data.getInteger("id");
            final String name = data.getString("name");
            final String phone = data.getString("phone");
            final String address = data.getString("address");
            final boolean isDefault = data.getBoolean("default");

            final MultipleItem entity = MultipleItem.builder()
                    .setItemType(AddressItemType.ITEM_ADDRESS)
                    .setItem(MultipleFields.ID, id)
                    .setItem(MultipleFields.NAME, name)
                    .setItem(MultipleFields.TAG, isDefault)
                    .setItem(AddressItemFields.ADDRESS, address)
                    .setItem(AddressItemFields.PHONE, phone)
                    .build();
            ITEMS.add(entity);
        }
        return ITEMS;
    }
}
