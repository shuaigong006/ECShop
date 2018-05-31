package com.example.ec.main;

import android.graphics.Color;

import com.example.core.delegate.bottom.BaseBottomDelegate;
import com.example.core.delegate.bottom.BottomItemDelegate;
import com.example.core.delegate.bottom.BottomItemBean;
import com.example.core.delegate.bottom.ItemBuilder;
import com.example.ec.main.index.IndexDelegate;

import java.util.LinkedHashMap;

public class ECBottomDelegate extends BaseBottomDelegate {

    //设置主页的分页页面
    @Override
    public LinkedHashMap<BottomItemBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomItemBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomItemBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomItemBean("{fa-sort}", "分类"), new IndexDelegate());
        items.put(new BottomItemBean("{fa-compass}", "发现"), new IndexDelegate());
        items.put(new BottomItemBean("{fa-shopping-cart}","购物车"),new IndexDelegate());
        items.put(new BottomItemBean("{fa-user}","我的"),new IndexDelegate());
        return builder.addItem(items).build();
    }

    //设置默认显示的位置
    @Override
    public int setIndexDelegate() {
        return 0;
    }

    //设置item被选中的颜色
    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
