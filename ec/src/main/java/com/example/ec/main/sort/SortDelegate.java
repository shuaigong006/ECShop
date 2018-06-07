package com.example.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.core.delegate.bottom.BottomItemDelegate;
import com.example.ec.R;
import com.example.ec.main.sort.content.ContentDelegate;
import com.example.ec.main.sort.list.ListDelegate;

public class SortDelegate extends BottomItemDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        //加载分页类型
        final ListDelegate list = new ListDelegate();
        getSupportDelegate().loadRootFragment(R.id.sort_list_container, list);
        //加载分页类型布局
        final ContentDelegate content = ContentDelegate.getInstance(1);
        getSupportDelegate().loadRootFragment(R.id.sort_content_container, content);
    }
}
