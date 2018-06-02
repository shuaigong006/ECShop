package com.example.ec.main.index;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.ui.recycler.DataConverter;
import com.example.ui.recycler.ItemType;
import com.example.ui.recycler.MultipleFields;
import com.example.ui.recycler.MultipleItem;

import java.util.ArrayList;

//首页数据转换
public class IndexDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItem> convert() {
        final JSONArray dataArray = JSONObject.parseObject(getJson()).getJSONArray("data");
        for (int i = 0, size = dataArray.size(); i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);

            final String imageUrl = data.getString("imageUrl");
            final String text = data.getString("text");
            final int spanSize = data.getInteger("spanSize");
            final int id = data.getInteger("goodsId");
            final JSONArray banners = data.getJSONArray("banners");

            final ArrayList<String> bannerImages = new ArrayList<>();

            int type = 0;
            if (imageUrl == null && text != null) {
                type = ItemType.TEXT;
            } else if (imageUrl != null && text == null) {
                type = ItemType.IMAGE;
            } else if (imageUrl != null) {
                type = ItemType.TEXT_IMAGE;
            } else if (banners != null) {
                type = ItemType.BANNER;
                for (int j = 0, bannerSize = banners.size(); j < bannerSize; j++) {
                    final String banner = banners.getString(j);
                    bannerImages.add(banner);
                }
            }

            final MultipleItem entity = MultipleItem.builder()
                    .setItem(MultipleFields.ITEM_TYPE, type)
                    .setItem(MultipleFields.SPAN_SIZE, spanSize)
                    .setItem(MultipleFields.ID, id)
                    .setItem(MultipleFields.TEXT, text)
                    .setItem(MultipleFields.IMAGE_URL, imageUrl)
                    .setItem(MultipleFields.BANNERS, bannerImages)
                    .build();

            ITEMS.add(entity);
        }
        return ITEMS;
    }
}
