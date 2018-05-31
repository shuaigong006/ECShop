package com.example.core.delegate.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.core.R;
import com.example.core.R2;
import com.example.core.delegate.ShopDelegate;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

public abstract class BaseBottomDelegate extends ShopDelegate implements View.OnClickListener {

    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottomBar;

    private int mClickedColor = Color.RED;
    //要隐藏界面的下标
    private int mCurrentDelegate = 0;
    //当前页面下标
    private int mIndexDelegate = 0;

    private final LinkedHashMap<BottomItemBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();
    //存储delegate页面
    private final ArrayList<BottomItemDelegate> ITEM_DELEGATES = new ArrayList<>();
    //存储图标字体Item
    private final ArrayList<BottomItemBean> TAB_BEANS = new ArrayList<>();

    public abstract LinkedHashMap<BottomItemBean, BottomItemDelegate> setItems(ItemBuilder builder);

    //设置主页显示页面
    public abstract int setIndexDelegate();

    //设置点击颜色
    public abstract int setClickedColor();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexDelegate = setIndexDelegate();
        mClickedColor = setClickedColor();
        //设置主页数据
        final ItemBuilder builder = ItemBuilder.Builder();
        final LinkedHashMap<BottomItemBean, BottomItemDelegate> items = setItems(builder);
        ITEMS.putAll(items);
        //取出字体图标和标题
        for (Map.Entry<BottomItemBean, BottomItemDelegate> item : ITEMS.entrySet()) {
            final BottomItemBean key = item.getKey();
            final BottomItemDelegate value = item.getValue();
            TAB_BEANS.add(key);
            ITEM_DELEGATES.add(value);
        }
    }

    //设置主页面
    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final int size = ITEMS.size();
        for (int i = 0; i < size; i++) {
            //设置分页页面并将其加载到LinearLayoutCompat下
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout, mBottomBar);
            //获取分页
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            item.setTag(i);
            item.setOnClickListener(this);
            //设置分页数据
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            final BottomItemBean bean = TAB_BEANS.get(i);
            itemIcon.setText(bean.getIcon());
            itemTitle.setText(bean.getTitle());
            if (i == mIndexDelegate) {
                itemIcon.setTextColor(mClickedColor);
                itemTitle.setTextColor(mClickedColor);
            }
        }
        //将字体item与delegate关联
        final SupportFragment[] delegateArray = ITEM_DELEGATES.toArray(new SupportFragment[size]);
        loadMultipleRootFragment(R.id.bottom_bar_delegate_container, mIndexDelegate, delegateArray);
    }

    //将item设置成灰色
    private void resetColor() {
        for (int i = 0, count = ITEMS.size(); i < count; i++) {
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            itemIcon.setTextColor(Color.GRAY);
            itemTitle.setTextColor(Color.GRAY);
        }
    }

    //设置点击后的颜色与事件
    @Override
    public void onClick(View v) {
        final int tag = (int) v.getTag();
        resetColor();
        final RelativeLayout item = (RelativeLayout) v;
        final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
        itemIcon.setTextColor(mClickedColor);
        final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
        itemTitle.setTextColor(mClickedColor);
        getSupportDelegate().showHideFragment(ITEM_DELEGATES.get(tag), ITEM_DELEGATES.get(mCurrentDelegate));
        mCurrentDelegate = tag;
    }
}
