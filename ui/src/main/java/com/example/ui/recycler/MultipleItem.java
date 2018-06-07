package com.example.ui.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

//网络请求数据存储和获取(建造者模式)
public class MultipleItem implements MultiItemEntity {

    //加载的数据过大可使用软引用或弱引用,否则有可能造成内存溢出
    private final ReferenceQueue<LinkedHashMap<Object,Object>> ITEM_QUEUE = new ReferenceQueue<>();

    private final LinkedHashMap<Object,Object> MULTIPLE_ITEM = new LinkedHashMap<>();

    private final SoftReference<LinkedHashMap<Object,Object>> ITEM_REFERENCE =
            new SoftReference<>(MULTIPLE_ITEM,ITEM_QUEUE);

    public MultipleItem(LinkedHashMap<Object,Object> items) {
        ITEM_REFERENCE.get().putAll(items);
    }

    @Override
    public int getItemType() {
        return (int) ITEM_REFERENCE.get().get(MultipleFields.ITEM_TYPE);
    }

    //获取Item数据
    @SuppressWarnings("unchecked")
    public final <T> T getItem(Object key){
        return (T) ITEM_REFERENCE.get().get(key);
    }

    public final LinkedHashMap<Object,Object> getItems(){
        return ITEM_REFERENCE.get();
    }

    public final MultipleItem setItem(Object key,Object value){
        ITEM_REFERENCE.get().put(key,value);
        return this;
    }

    public static MultipleItemBuilder builder(){
        return new MultipleItemBuilder();
    }

    public static final class MultipleItemBuilder{

        private static final LinkedHashMap<Object,Object> ITEMS = new LinkedHashMap<>();

        MultipleItemBuilder() {
            //清除之前的数据
            ITEMS.clear();
        }

        public final MultipleItemBuilder setItemType(int itemType) {
            ITEMS.put(MultipleFields.ITEM_TYPE, itemType);
            return this;
        }

        //设置Item数据
        public final MultipleItemBuilder setItem(Object key, Object value){
            ITEMS.put(key, value);
            return this;
        }

        public final MultipleItemBuilder setItems(LinkedHashMap<Object,Object> item){
            ITEMS.putAll(item);
            return this;
        }

        public final MultipleItem build(){
            return new MultipleItem(ITEMS);
        }
    }
}
