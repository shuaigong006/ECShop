package com.example.core.delegate.bottom;

import java.util.LinkedHashMap;

//存储分页
public final class ItemBuilder {

    private final LinkedHashMap<BottomItemBean,BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    public static ItemBuilder Builder(){
        return new ItemBuilder();
    }

    public final ItemBuilder addItem(BottomItemBean bean, BottomItemDelegate delegate){
        ITEMS.put(bean, delegate);
        return this;
    }

    public final ItemBuilder addItem(LinkedHashMap<BottomItemBean,BottomItemDelegate> items){
        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<BottomItemBean,BottomItemDelegate> build(){
        return ITEMS;
    }
}
