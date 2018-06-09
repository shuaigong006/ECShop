package com.example.ec.main.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.core.delegate.ShopDelegate;
import com.example.ec.R;
import com.example.ec.R2;
import com.example.ui.recycler.ItemType;
import com.example.ui.recycler.MultipleFields;
import com.example.ui.recycler.MultipleItem;

import java.util.ArrayList;

import butterknife.BindView;

public class ImageDelegate extends ShopDelegate {

    private ArrayList<String> pictures = null;
    private static final String ARG_PICTURES = "ARG_PICTURES";

    @BindView(R2.id.rv_image_container)
    RecyclerView mRecyclerView = null;

    public static ImageDelegate create(ArrayList<String> pictures) {
        final Bundle args = new Bundle();
        args.putStringArrayList(ARG_PICTURES, pictures);
        final ImageDelegate delegate = new ImageDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            pictures = args.getStringArrayList(ARG_PICTURES);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_image;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        initImages();
    }

    private void initImages() {
        final ArrayList<MultipleItem> entities = new ArrayList<>();
        for (int i=0,size = pictures.size();i<size;i++){
            final String imageUrl = pictures.get(i);
            MultipleItem entity = MultipleItem.builder()
                    .setItemType(ItemType.SINGLE_BIG_IMAGE)
                    .setItem(MultipleFields.IMAGE_URL,imageUrl)
                    .build();
            entities.add(entity);
        }
        final RecyclerImageAdapter adapter = new RecyclerImageAdapter(entities);
        mRecyclerView.setAdapter(adapter);
    }
}
