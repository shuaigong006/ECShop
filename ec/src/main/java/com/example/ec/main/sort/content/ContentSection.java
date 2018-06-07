package com.example.ec.main.sort.content;

import com.chad.library.adapter.base.entity.SectionEntity;

public class ContentSection extends SectionEntity<ContentSectionEntity> {

    private boolean mIsMore;
    private int mId = -1;

    public ContentSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public ContentSection(ContentSectionEntity contentSectionEntity) {
        super(contentSectionEntity);
    }

    public boolean isMore() {
        return mIsMore;
    }

    public void setIsMore(boolean mIsMore) {
        this.mIsMore = mIsMore;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }
}
