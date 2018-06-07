package com.example.ec.main.sort.list;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ec.R;
import com.example.ec.main.sort.SortDelegate;
import com.example.ec.main.sort.content.ContentDelegate;
import com.example.ui.recycler.ItemType;
import com.example.ui.recycler.MultipleFields;
import com.example.ui.recycler.MultipleItem;
import com.example.ui.recycler.MultipleRecyclerAdapter;

import java.util.List;

import me.yokeyword.fragmentation.SupportHelper;

public class ListAdapter extends MultipleRecyclerAdapter {

    private final SortDelegate DELEGATE;
    //上一个被选择的item
    private int mPrePosition = 0;

    public ListAdapter(List<MultipleItem> data, SortDelegate delegate) {
        super(data);
        this.DELEGATE = delegate;
        addItemType(ItemType.VERTICAL_MENU_LIST, R.layout.item_vertical_menu_list);
    }

    @Override
    protected void convert(final BaseViewHolder holder, final MultipleItem item) {
        super.convert(holder, item);
        switch (holder.getItemViewType()) {
            case ItemType.VERTICAL_MENU_LIST:
                final String text = item.getItem(MultipleFields.TEXT);
                final boolean isClicked = item.getItem(MultipleFields.TAG);
                final AppCompatTextView name = holder.getView(R.id.tv_vertical_item_name);
                final View line = holder.getView(R.id.view_line);
                final View itemView = holder.itemView;
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentPosition = holder.getAdapterPosition();
                        if (mPrePosition != currentPosition) {
                            //将上一个设置为未选择状态
                            getData().get(mPrePosition).setItem(MultipleFields.TAG, false);
                            notifyItemChanged(mPrePosition);

                            //更新选中的item
                            item.setItem(MultipleFields.TAG, true);
                            notifyItemChanged(currentPosition);

                            mPrePosition = currentPosition;
                            final int contentId = getData().get(currentPosition).getItem(MultipleFields.ID);
                            showContent(contentId);
                        }
                    }
                });

                if (!isClicked){
                    line.setVisibility(View.INVISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.we_chat_black));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.item_background));
                }else {
                    line.setVisibility(View.VISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.app_main));
                    line.setBackgroundColor(ContextCompat.getColor(mContext, R.color.app_main));
                    itemView.setBackgroundColor(Color.WHITE);
                }

                holder.setText(R.id.tv_vertical_item_name, text);
                break;
            default:
                break;
        }
    }

    private void showContent(int contentId) {
        final ContentDelegate delegate = ContentDelegate.getInstance(contentId);

        final ContentDelegate content = SupportHelper.findFragment(
                DELEGATE.getChildFragmentManager(), ContentDelegate.class
        );
        if (content != null) {
            content.getSupportDelegate().replaceFragment(delegate, false);
        }
    }
}
