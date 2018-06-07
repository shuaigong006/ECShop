package com.example.ec.main.personal.list;

import android.widget.CompoundButton;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.core.delegate.ShopDelegate;

public class ListEntity implements MultiItemEntity {

    private int mItemType = 0;
    private String mImageUrl = null;
    private String mText = null;
    private String mValue = null;
    private int mId = 0;
    private ShopDelegate mDelegate = null;
    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener = null;

    public ListEntity(int mItemType, String mImageUrl, String mText, String mValue,
                      int mId, ShopDelegate mDelegate,CompoundButton.OnCheckedChangeListener listener) {
        this.mItemType = mItemType;
        this.mImageUrl = mImageUrl;
        this.mText = mText;
        this.mValue = mValue;
        this.mId = mId;
        this.mDelegate = mDelegate;
        this.mOnCheckedChangeListener = listener;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getText() {
        if (mText == null) {
            return "";
        }
        return mText;
    }

    public String getValue() {
        if (mValue == null) {
            return "";
        }
        return mValue;
    }

    public CompoundButton.OnCheckedChangeListener getOnCheckedChangeListener() {
        return mOnCheckedChangeListener;
    }

    public int getId() {
        return mId;
    }

    public ShopDelegate getDelegate() {
        return mDelegate;
    }

    @Override
    public int getItemType() {
        return mItemType;
    }

    public static final class Builder {
        private int id = 0;
        private int itemType = 0;
        private String imageUrl = null;
        private String text = null;
        private String value = null;
        private ShopDelegate delegate = null;
        private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = null;

        public final Builder setId(int id) {
            this.id = id;
            return this;
        }

        public final Builder setItemType(int itemType) {
            this.itemType = itemType;
            return this;
        }

        public final Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public final Builder setText(String text) {
            this.text = text;
            return this;
        }

        public final Builder setValue(String value) {
            this.value = value;
            return this;
        }

        public Builder setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
            this.onCheckedChangeListener = onCheckedChangeListener;
            return this;
        }

        public final Builder setDelegate(ShopDelegate delegate) {
            this.delegate = delegate;
            return this;
        }

        public final ListEntity build() {
            return new ListEntity(itemType, imageUrl, text, value, id, delegate,onCheckedChangeListener);
        }
    }
}
