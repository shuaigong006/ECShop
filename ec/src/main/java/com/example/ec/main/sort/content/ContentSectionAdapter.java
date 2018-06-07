package com.example.ec.main.sort.content;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ec.R;

import java.util.List;

public class ContentSectionAdapter extends BaseSectionQuickAdapter<ContentSection,BaseViewHolder> {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();

    public ContentSectionAdapter(int layoutResId, int sectionHeadResId, List<ContentSection> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder holder, ContentSection item) {
        holder.setText(R.id.header,item.header);
        holder.setVisible(R.id.more,item.isMore());
        holder.addOnClickListener(R.id.more);
    }

    @Override
    protected void convert(BaseViewHolder holder, ContentSection item) {
        final int goodsId = item.t.getGoodsId();
        final String goodsName = item.t.getGoodsName();
        final String goodsThumb = item.t.getGoodsThumb();
        holder.setText(R.id.tv,goodsName);
        Glide.with(mContext)
                .load(goodsThumb)
                .apply(OPTIONS)
                .into((ImageView) holder.getView(R.id.iv));
    }
}
