package com.example.ec.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.core.delegate.ShopDelegate;
import com.example.core.net.RestClient;
import com.example.core.net.callback.ISuccess;
import com.example.ec.R;
import com.example.ec.R2;

import java.util.List;

import butterknife.BindView;

public class ContentDelegate extends ShopDelegate {

    private static final String ARG_CONTENT_ID = "CONTENT_ID";

    private int mContentId = -1;

    @BindView(R2.id.rv_list_content)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_list_content;
    }

    //根据传入的分页id来实例化delegate
    public static ContentDelegate getInstance(int contentId) {
        final ContentDelegate delegate = new ContentDelegate();
        final Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_ID, contentId);
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取分页id
        Bundle args = getArguments();
        if (args != null) {
            mContentId = args.getInt(ARG_CONTENT_ID);
        }
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final StaggeredGridLayoutManager manager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        initData();
    }

    private void initData() {
        RestClient.builder()
                .url("sort_content.php?id=" + mContentId)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        List<ContentSection> data = new ContentDataConverter().convert(response);
                        final ContentSectionAdapter adapter = new ContentSectionAdapter(
                                R.layout.item_section_content, R.layout.item_section_header, data
                        );
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .build()
                .get();
    }
}
